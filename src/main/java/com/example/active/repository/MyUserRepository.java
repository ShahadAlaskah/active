package com.example.active.repository;

import com.example.active.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
    MyUser findMyUserByUsername(String username);
    MyUser findByID(Integer userID);
}
