package com.gta.spring.springboot.junix_opp.servise;


import com.gta.spring.springboot.junix_opp.dto.user.UserReadDTO;
import com.gta.spring.springboot.junix_opp.dto.user.UserReadMapper;
import com.gta.spring.springboot.junix_opp.entity.User;
import com.gta.spring.springboot.junix_opp.entity.enums.ERole;
import com.gta.spring.springboot.junix_opp.exceptions.UserExistException;
import com.gta.spring.springboot.junix_opp.payload.request.SignupRequest;
import com.gta.spring.springboot.junix_opp.payload.request.UpdatePasswordRequest;
import com.gta.spring.springboot.junix_opp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final UserReadMapper userReadMapper;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, UserReadMapper userReadMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userReadMapper = userReadMapper;
    }

    public User createUser(SignupRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setMiddlename(userIn.getMiddlename());
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.ROLE_USER);

        try {
            LOG.info("Saving User {}", userIn.getEmail());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }

//    public User updateUser(UserDTO userDTO, Principal principal) {
//        User user = getUserByPrincipal(principal);
//        user.setName(userDTO.getFirstname());
//        user.setLastname(userDTO.getLastname());
//        user.setBio(userDTO.getBio());
//
//        return userRepository.save(user);
//    }
//

//

    public void updatePassword(UpdatePasswordRequest updatePasswordRequest, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));

        if (!passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
        userRepository.save(user);
        LOG.info("Password updated successfully for user {}", username);
    }
    public User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));

    }

    public UserReadDTO getUserDTOByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .map(userReadMapper::map)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));

    }



    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<UserReadDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }


}
