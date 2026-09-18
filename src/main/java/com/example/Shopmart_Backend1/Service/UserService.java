package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Entity.User;
import com.example.Shopmart_Backend1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

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
    public Map<String,Object> loginUser (User user){
        User dbUser = userRepository.findByUsernameIgnoreCase(user.getUsername()).orElse(null);
        if(dbUser == null){
            dbUser = userRepository.findByEmailIgnoreCase(user.getUsername()).orElse(null);
        }
        if(dbUser == null){
            throw new RuntimeException("Invalid Username or Password");
        }
        if(!dbUser.isStatus()){
            return Map.of(
                    "status",false,
                    "message", "Account is blocked"
            );
        }
        if(!dbUser.getPassword().equals(user.getPassword())){
            dbUser.setFailedLoginAttempts(dbUser.getFailedLoginAttempts()+1);

            if(dbUser.getFailedLoginAttempts() >=5){
                dbUser.setStatus(false);
                userRepository.save(dbUser);
                return Map.of(
                        "status",false,
                        "message", "Account is blocked"
                );
            }
            userRepository.save(dbUser);
            throw new RuntimeException("Invalid Username or Password");
        }
        dbUser.setFailedLoginAttempts(0);
        userRepository.save(dbUser);

        String token = jwtService.generateToken(
                dbUser.getUsername()
        );
        return Map.of(
                "status",true,
                "data", dbUser,
                "token", token
        );
    }
    public Map<String, Object> getProfile(String username){
        User dbUser = userRepository.findByUsernameIgnoreCase(username).orElse(null);

        if(dbUser == null){
            return Map.of(
                    "status", false,
                    "message", "user not found"
            );
        }
        return Map.of(
                "status", true,
                "data",dbUser
        );
    }
    public Map<String, Object> updateProfile(String loggedInUsername, User user){
        User dbUser = userRepository.findByUsernameIgnoreCase(loggedInUsername).orElse(null);

        if(dbUser == null){
            return Map.of(
                    "status",false,
                     "message", "user not found"
            );
        }
        if(!dbUser.getUsername().equalsIgnoreCase(user.getUsername())){
            if(userRepository.existsByUsernameIgnoreCase((user.getUsername()))){
                return Map.of(
                        "status",false,
                        "message", "username already Taken"
                );
            }
            dbUser.setUsername(user.getUsername());
        }
        if(!dbUser.getEmail().equalsIgnoreCase(user.getEmail())){
            if(userRepository.existsByEmailIgnoreCase(user.getEmail())){
                return Map.of(
                        "status", false,
                        "message", "email already Taken"
                );
            }
            dbUser.setEmail(user.getEmail());
        }
        dbUser.setName(user.getName());
        dbUser.setPhone(user.getPhone());

        User updatedUser = userRepository.save(dbUser);

        String newToken = jwtService.generateToken(
                updatedUser.getUsername()
        );
        return Map.of(
                "status", true,
                "message", "Profile Updated Successfully",
                "data", updatedUser,
                "token", newToken
        );
    }

    public User updateStatus(Long id, boolean status){
        User user = userRepository.findById(id).orElseThrow();
        user.setStatus(status);
        return userRepository.save(user);
    }
}
