package com.example.active.service;

import com.example.active.exception.ApiException;
import com.example.active.model.MyUser;
import com.example.active.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {
    private final MyUserRepository myUserRepository;

    public List<MyUser> getUsers() {
        return myUserRepository.findAll();
    }

    public void register(MyUser myUser) {
        String hashedPassword=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashedPassword);
        //myUser.setRole("USER");
        myUserRepository.save(myUser);
    }

    public void updateUser(MyUser myUser, Integer userID) {
        MyUser oldMyUser=myUserRepository.findByID(userID);
        if (oldMyUser == null) {
            throw new ApiException("userID not found");
        }
        oldMyUser.setName(myUser.getName());
        oldMyUser.setEmail(myUser.getEmail());
        String hashedPassword=new BCryptPasswordEncoder().encode(myUser.getPassword());
        oldMyUser.setPassword(hashedPassword);
        oldMyUser.setClubID(myUser.getClubID());
        oldMyUser.setUsername(myUser.getUsername());
        myUserRepository.save(oldMyUser);

    }

    public void deleteUser(Integer userID) {
        MyUser myUser=myUserRepository.findByID(userID);
        if (myUser == null) {
            throw new ApiException("userID not found");
        }
        myUserRepository.delete(myUser);
    }

    public MyUser getUserByID(Integer userID) {
        return myUserRepository.getById(userID);
    }
}
