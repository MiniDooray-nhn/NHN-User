package com.nhnacademy.account.repository;

import com.nhnacademy.account.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {


    Optional<User> findByIdAndPassword(String userId, String userPassword);

}
