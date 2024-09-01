import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
//import App from './App.jsx'
//import './index.css'
import { SignUp } from './pages/SignUp/index.jsx'
import "./styles.scss";
import i18n from "i18next";
import { initReactI18next } from "react-i18next";

i18n
  .use(initReactI18next)
  .init({
    resources: {
      en: {
        translation: {
          signUp: 'Sign Up',
          username: 'Username',
          email: 'Email',
          password: 'Password',
          passwordRepeat: 'Password Repeat',
          passwordMismatch: 'Password mismatch',
          genericError: 'Unexpected error occurred. Please try again',
          submit: 'Submit',
          resetPassword: 'Reset Password',
          forgotPassword: 'Forgot Password?',
        },
      },
      tr: {
        translation: {
          signUp: 'Kayit Ol',
          username: 'Kullanici Adi',
          email: 'E-posta',
          password: 'Sifre',
          passwordRepeat: 'Şifre Tekrar',
          passwordMismatch: 'Şifre eşleşmiyor',
          genericError: 'Beklenmedik bir hata oluştu. Lutfen tekrar deneyin',
          submit: 'Gonder',
          resetPassword: 'Sifreyi Sifirla',
          forgotPassword: 'Sifrenizi mi Unuttunuz?',
        },
      },
    },
    lng: "en",
    fallbackLng: "en",

    interpolation: {
      escapeValue: false, // react already safes from xss => https://www.i18next.com/translation-function/interpolation#unescape
    },
  });
  createRoot(document.getElementById('root')).render(
    <StrictMode>
      <SignUp />
    </StrictMode>,
  )
