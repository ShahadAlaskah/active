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

    public void updateUser(MyUser newUser, MyUser myUser) {
        MyUser oldMyUser=myUserRepository.findByID(myUser.getID());
        if (oldMyUser == null) {
            throw new ApiException("userID not found");
        }
        oldMyUser.setName(newUser.getName());
        oldMyUser.setEmail(newUser.getEmail());
        String hashedPassword=new BCryptPasswordEncoder().encode(newUser.getPassword());
        oldMyUser.setPassword(hashedPassword);
        oldMyUser.setClubID(newUser.getClubID());
        oldMyUser.setUsername(newUser.getUsername());
        myUserRepository.save(oldMyUser);

    }

    public void deleteUser(MyUser myUser) {
        MyUser user=myUserRepository.findByID(myUser.getID());
        if (user == null) {
            throw new ApiException("userID not found");
        }
        myUserRepository.delete(user);
    }

    public MyUser getUserByID(MyUser myUser) {

        return myUserRepository.findByID(myUser.getID());
    }
}
