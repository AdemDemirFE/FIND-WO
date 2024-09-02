package com.findwo.backend.email;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.findwo.backend.configration.FindWoProperties;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    JavaMailSenderImpl mailSender;

    @Autowired
    FindWoProperties findWoProperties;

    @Autowired
    MessageSource messageSource;

    @PostConstruct
    public void initialize(){
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(findWoProperties.getEmail().host());
        mailSender.setPort(findWoProperties.getEmail().port());
        mailSender.setUsername(findWoProperties.getEmail().username());
        mailSender.setPassword(findWoProperties.getEmail().password());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", "true");

    }

    String activationEmail = """
            <html>
                <body>
                    <h1>${title}</h1>
                    <a href="${url}">${clickHere}</a>
                </body>
            </html>
            """;

    public void sendActivationEmail(String email, String activationToken) {
        var activationUrl = findWoProperties.getClient().host() + "/activation/" + activationToken;
        var title = messageSource.getMessage("findwo.mail.user.created.title", null, LocaleContextHolder.getLocale());
        var clickHere = messageSource.getMessage("findwo.mail.click.here", null, LocaleContextHolder.getLocale());


        var mailBody = activationEmail
            .replace("${url}", activationUrl)
            .replace("${title}", title)
            .replace("${clickHere}", clickHere);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            message.setFrom(findWoProperties.getEmail().from());
            message.setTo(email);
            message.setSubject(title);
            message.setText(mailBody, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        this.mailSender.send(mimeMessage);
    }

    public void sendPasswordResetEmail(String email, String passwordResetToken) {
        String passwordResetUrl = findWoProperties.getClient().host() + "/password-reset/set?tk=" + passwordResetToken;
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        var title = "Reset your password";
        var clickHere = messageSource.getMessage("findwo.mail.click.here", null, LocaleContextHolder.getLocale());
        var mailBody = activationEmail.replace("${url}", passwordResetUrl).replace("${title}", title).replace("${clickHere}", clickHere);
        try {
          message.setFrom(findWoProperties.getEmail().from());
          message.setTo(email); 
          message.setSubject(title); 
          message.setText(mailBody, true);
        } catch (MessagingException e) {
          e.printStackTrace();
        }
        this.mailSender.send(mimeMessage);
      }

    
}
