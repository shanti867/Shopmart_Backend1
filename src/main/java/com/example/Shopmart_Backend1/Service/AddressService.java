package com.example.Shopmart_Backend1.Service;

import com.example.Shopmart_Backend1.Entity.Address;
import com.example.Shopmart_Backend1.Entity.User;
import com.example.Shopmart_Backend1.Repository.AddressRepository;
import com.example.Shopmart_Backend1.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> addAddress(String loggedInUsername, Address address){
        User user = userRepository
                .findByUsernameIgnoreCase(loggedInUsername)
                .orElse(null);

        if(user == null){
            return Map.of(
                    "status", false,
                    "message", "User not found"
            );
        }
        address.setUser(user);
        Address savedAddress = addressRepository.save(address);
        return Map.of(
                "status", true,
                "message", "Address Added Successfully",
                "data", savedAddress
        );
    }

    public Map<String,Object> deleteAddress(
            String loggedInUsername, Long id){
        User user = userRepository.findByUsernameIgnoreCase(loggedInUsername)
                .orElse(null);
        if(user == null) {
            return Map.of(
                    "status", false,
                    "message", "user not Found"
            );
        }
            Address address = addressRepository
                    .findByIdAndUser(id, user)
                    .orElse(null);
            if(address == null){
                return Map.of(
                  "status", false,
                  "message", "Address not found"
                );
            }
            addressRepository.delete(address);
            return Map.of(
              "status", true,
              "message", "Address Deleted Successfully"
            );
        }

    public Map<String, Object> getAddress(String loggedInUsername){

        User user = userRepository
                .findByUsernameIgnoreCase(loggedInUsername)
                .orElse(null);

        if(user == null){
            return Map.of(
                    "status", false,
                    "message", "User not found"
            );
        }
        List<Address> addresses = addressRepository.findByUser(user);

        return Map.of(
                "status", true,
                "data", addresses
        );
        }
    public Map<String,Object> updateAddress(String loggedInUsername, Long id, Address address){
        User user = userRepository
                .findByUsernameIgnoreCase(loggedInUsername)
                .orElse(null);

        if(user == null){
            return Map.of(
                    "status", false,
                    "message", "User not found"
            );
        }
        Address dbAddress = addressRepository
                .findByIdAndUser(id, user)
                .orElse(null);

        if(dbAddress == null){
            return Map.of(
                    "status", false,
                    "message", "Address not found"
            );
        }
        dbAddress.setName(address.getName());
        dbAddress.setEmail(address.getEmail());
        dbAddress.setPhone(address.getPhone());
        dbAddress.setAddress(address.getAddress());
        dbAddress.setPin(address.getPin());
        dbAddress.setCity(address.getCity());
        dbAddress.setState(address.getState());

        Address updatedAddress = addressRepository.save(dbAddress);
        return Map.of(
          "status", true,
          "message", "Address Updated Successfully",
          "data", updatedAddress
        );
    }
}
