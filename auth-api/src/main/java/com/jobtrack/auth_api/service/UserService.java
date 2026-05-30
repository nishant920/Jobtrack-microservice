package com.jobtrack.auth_api.service;

import com.jobtrack.auth_api.dto.AppUserDto;
import com.jobtrack.auth_api.dto.UserResponseDto;
import com.jobtrack.auth_api.model.AppUser;
import com.jobtrack.auth_api.repository.UserRepository;
import com.jobtrack.auth_api.repository.VerificationRepository;
import com.jobtrack.auth_api.utility.Mapper;
import com.jobtrack.auth_api.verfication.VerificationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Slf4j
@Service
public class UserService {
    @Autowired
    Mapper mapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    VerificationRepository verificationRepository;

    public UserResponseDto saveUser(AppUserDto appUserDto){
        AppUser existingUser = userRepository.findByEmail(appUserDto.getEmail());
        if(existingUser!=null){
            throw new RuntimeException("User Already Exist");
        }
        AppUser appUser = mapper.mapAppUserDtoToAppUser(appUserDto);
        appUser.setVerified(false);
        AppUser savedUser = userRepository.save(appUser);
        UserResponseDto userResponceDto = mapper.mapAppUserToResponceDto(savedUser);

        //Verification token generated here
        String token = UUID.randomUUID().toString();
        log.info("Token generated on registration of user {} ", token);
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));//from this moment it got created to 30 mins from now(after 30 mins gets deleted from database)
        verificationToken.setUser(savedUser);
        verificationRepository.save(verificationToken);

        log.info("calling notification-api to send verification mail with token : " + token);
        return userResponceDto;
    }

    public String verifyToken(String token){
        VerificationToken verificationToken = verificationRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid Token"));

        //if the deadline(the last min at which token remail alive) is earlier than right now (we passed the expiry date of token)
        if(verificationToken.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Verification token is Expired");
        }

        AppUser appUser = new AppUser();
        appUser.setVerified(true);
        userRepository.save(appUser);
        verificationRepository.delete(verificationToken);
        return "Email is Verified successfully";
    }

}
