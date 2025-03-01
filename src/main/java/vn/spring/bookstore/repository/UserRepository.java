package vn.spring.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.spring.bookstore.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
