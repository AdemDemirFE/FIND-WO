package com.findwo.backend.user;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;

public interface UserRepository extends JpaRepository <User, Long> {
    User findByEmail(String email);

    // User findByActivationToken(String token);

    // Page<User> findByIdNot(long id, Pageable page);

    // User findByPasswordResetToken(String passwordResetToken);
}
