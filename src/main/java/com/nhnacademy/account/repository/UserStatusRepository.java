package com.nhnacademy.account.repository;

import com.nhnacademy.account.domain.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatus,Integer> {



}
