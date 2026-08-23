package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Entity.User;
import com.example.Shopmart_Backend1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        if(userRepository.existsByUsernameIgnoreCase(user.getUsername())){
            throw new RuntimeException("Username Already Taken");
        }
        if(userRepository.existsByEmailIgnoreCase(user.getEmail())){
            throw new RuntimeException("Email Address Already Taken");
        }
        if(!user.getPassword().equals(user.getCpassword())){
            throw new RuntimeException("Password and Confirm Password do not match");
        }
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User loginUser(User user){
        User dbUser = userRepository.findByUsernameIgnoreCase(user.getUsername()).orElse(null);
        if(dbUser == null){
            dbUser = userRepository.findByEmailIgnoreCase(user.getEmail()).orElse(null);
        }
        if(dbUser == null){
            throw new RuntimeException("Invalid Username or Password");
        }
        if(!dbUser.isStatus()){
            return dbUser;
        }
        if(!dbUser.getPassword().equals(user.getPassword())){
            dbUser.setFailedLoginAttempts(dbUser.getFailedLoginAttempts()+1);

            if(dbUser.getFailedLoginAttempts() >=5){
                dbUser.setStatus(false);
                userRepository.save(dbUser);
                return dbUser;
            }
            userRepository.save(dbUser);
            throw new RuntimeException("Invalid Username or Password");
        }
        dbUser.setFailedLoginAttempts(0);
        userRepository.save(dbUser);
        return dbUser;
    }
}
