package com.example.sosy.service;

import java.util.Optional; // ⭐ IMPORT UPDATED (Renamed DTO)

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sosy.dto.UserProfile;
import com.example.sosy.model.User;
import com.example.sosy.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves a new user to the database.
     * @param user the user to save
     * @return the saved user
     */
    public User saveUser(User user) {
        System.out.println("Saving user: " + user);
        return userRepository.save(user);
    }

    /**
     * Finds a user by their flat number.
     * @param flat the flat number
     * @return the user if found, or null if not
     */
    public User findByFlat(String flat) {
        return userRepository.findByFlat(flat).orElse(null);
    }

    /**
     * Returns an Optional<User> for safe null handling.
     * @param flat the flat number
     * @return Optional containing user if found
     */
    public Optional<User> findOptionalByFlat(String flat) {
        return userRepository.findByFlat(flat);
    }

    // ⭐ NEW METHOD FOR HOMEPAGE PROFILE DATA
    /**
     * Retrieves the profile data for a user and transforms it into a DTO.
     * This method is used by the SignupController for the /api/signup/details endpoint.
     * @param flatNo The flat number (primary key) of the currently logged-in user.
     * @return UserProfile containing name, flat, and mobile details.
     */
    public UserProfile getUserProfile(String flatNo) { // ⭐ RETURN TYPE UPDATED
        // Assumes userRepository has a findByFlat(String) method that finds the user.
        Optional<User> userOptional = userRepository.findByFlat(flatNo); 

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            // Map the User entity fields to the UserProfile DTO
            return new UserProfile( // ⭐ CONSTRUCTOR CALL UPDATED
                    user.getFullname(), // Maps to fullName
                    user.getFlat(),     // Maps to flatNo
                    user.getPhone()     // Maps to mobileNo
            );
        } else {
            // Throw a specific exception if the user is not found
            throw new RuntimeException("Logged-in user not found for flat: " + flatNo);
        }
    }

    public boolean verifyUser(String name, String flat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifyUser'");
    }
}