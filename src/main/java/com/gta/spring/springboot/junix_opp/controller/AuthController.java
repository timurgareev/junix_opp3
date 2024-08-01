package com.gta.spring.springboot.junix_opp.controller;


import com.gta.spring.springboot.junix_opp.payload.request.LoginRequest;
import com.gta.spring.springboot.junix_opp.payload.request.SignupRequest;
import com.gta.spring.springboot.junix_opp.payload.request.UpdatePasswordRequest;
import com.gta.spring.springboot.junix_opp.payload.response.JWTTokenSuccessResponse;
import com.gta.spring.springboot.junix_opp.payload.response.MessageResponse;
import com.gta.spring.springboot.junix_opp.security.JWTTokenProvider;
import com.gta.spring.springboot.junix_opp.security.SecurityConstants;
import com.gta.spring.springboot.junix_opp.servise.UserService;
import com.gta.spring.springboot.junix_opp.validation.ResponseErrorValidation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@PreAuthorize("permitAll()")
public class AuthController {

    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    @Autowired
    private UserService userService;

    public static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(
            @Valid @RequestBody LoginRequest loginRequest,
            BindingResult bindingResult
    ) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }


    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(
            @Valid @RequestBody SignupRequest signupRequest,
            BindingResult bindingResult
    ) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest, Authentication authentication) {
        try {
            userService.updatePassword(updatePasswordRequest, authentication);
            LOG.info("Password updated successfully for user {}", authentication.getName());
            return ResponseEntity.ok("Password updated successfully");
        } catch (IllegalArgumentException e) {
            LOG.error("Error updating password: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOG.error("Unexpected error: {}", e.getMessage());
            return ResponseEntity.status(500).body("An unexpected error occurred");
        }
    }

}
