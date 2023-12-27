package com.example.sinhnhat.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.MessagingException;

import com.example.sinhnhat.entity.ERole;
import com.example.sinhnhat.entity.Role;
import com.example.sinhnhat.entity.User;
import com.example.sinhnhat.exception.BadRequestException;
import com.example.sinhnhat.exception.NotFoundException;
import com.example.sinhnhat.model.request.ChangePasswordRequest;
import com.example.sinhnhat.model.request.CreateUserRequest;
import com.example.sinhnhat.model.request.UpdateProfileRequest;
import com.example.sinhnhat.repository.RoleRepository;
import com.example.sinhnhat.repository.UserRepository;
import com.example.sinhnhat.service.UserService;
import com.example.sinhnhat.util.EmailUtil;
import com.example.sinhnhat.util.OtpUtil;



@Service
public class UserServiceImpl implements UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	  private OtpUtil otpUtil;
	  @Autowired
	  private EmailUtil emailUtil;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void register(CreateUserRequest request) {
        // TODO Auto-generated method stub
    	String verificationCode = otpUtil.generateOtp();
        try {
          emailUtil.sendOtpEmail(request.getEmail(), verificationCode);
        } catch (MessagingException e) {
          throw new RuntimeException("Unable to send otp please try again");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setVerificationCode(verificationCode);
        user.setOtpGeneratedTime(LocalDateTime.now());
        Set<String> strRoles = request.getRole();
          Set<Role> roles = new HashSet<>();
      
          if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
          } else {
            strRoles.forEach(role -> {
              switch (role) {
              case "admin":
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(adminRole);
      
                break;
              case "mod":
                Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(modRole);
      
                break;
              default:
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
              }
            });
          }
          user.setRoles(roles);
          userRepository.save(user);
    }

    public String verifyAccount(String email, String otp) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        if (user.getVerificationCode().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
            LocalDateTime.now()).getSeconds() < (1 * 60 * 3)) {
          user.setEnabled(true);
          userRepository.save(user);
          return "OTP verified you can login";
        }
        return "Please regenerate otp and try again";
      }
    public String regenerateOtp(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again", e);
        }

        
        
        user.setVerificationCode(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Email sent... please verify account within 1 minute";
      }

    @Override
    public User getUserByUsername(String username) {
      // TODO Auto-generated method stub
      User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Not Found User"));
      return user;
    }

    @Override
    public User updateUser(UpdateProfileRequest request) {
      // TODO Auto-generated method stub
      User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new NotFoundException("Not Found User"));
      user.setFirstname(request.getFirstname());
      user.setLastname(request.getLastname());
      user.setEmail(request.getEmail());
      user.setCountry(request.getCountry());
      user.setState(request.getState());
      user.setAddress(request.getAddress());
      user.setPhone(request.getPhone());
      userRepository.save(user);
      return user;
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
      // TODO Auto-generated method stub
      // User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new NotFoundException("Not Found User"));

      // if(encoder.encode(request.getOldPassword()) != user.getPassword()){
      //   throw new BadRequestException("Old Passrword Not Same");
      // }
      // user.setPassword(encoder.encode(request.getNewPassword()));

      // userRepository.save(user);
      
    }


    
}
