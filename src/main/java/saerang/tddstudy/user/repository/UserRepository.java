package saerang.tddstudy.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saerang.tddstudy.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
