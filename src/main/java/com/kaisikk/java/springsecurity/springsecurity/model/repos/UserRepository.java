package com.kaisikk.java.springsecurity.springsecurity.model.repos;

import com.kaisikk.java.springsecurity.springsecurity.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByName(String userName);

}
