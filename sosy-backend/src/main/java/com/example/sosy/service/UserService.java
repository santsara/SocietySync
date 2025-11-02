package com.example.sosy.service;

import com.example.sosy.dto.UserProfile;
import com.example.sosy.model.User;
import com.example.sosy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    //Saves a new user to the database.
    public User saveUser(User user)
    {
        System.out.println("Saving user: " + user);
        return userRepository.save(user);
    }
    //Finds a user by their flat number.
    public User findByFlat(String flat)
    {
        return userRepository.findByFlat(flat).orElse(null);
    }
    //Returns an Optional<User> for safe null handling.
    public Optional<User> findOptionalByFlat(String flat)
    {
        return userRepository.findByFlat(flat);
    }
    // NEW METHOD FOR HOMEPAGE PROFILE DATA
    /**
     * Retrieves the profile data for a user and transforms it into a DTO.
     * This method is used by the SignupController for the /api/signup/details endpoint.
     * @param flatNo The flat number (primary key) of the currently logged-in user.
     * @return UserProfile containing name, flat, and mobile details.
     */
    public UserProfile getUserProfile(String flatNo)
    {
        Optional<User> userOptional = userRepository.findByFlat(flatNo);

        if (userOptional.isPresent())
        {
            User user = userOptional.get();
            // Map the User entity fields to the UserProfile DTO
            return new UserProfile(
                    user.getFullname(), // Maps to fullName
                    user.getFlat(),     // Maps to flatNo
                    user.getPhone()     // Maps to mobileNo
                );
        } else
        {  // Throw a specific exception if the user is not found
            throw new RuntimeException("Logged-in user not found for flat: " + flatNo);
        }
    }
}