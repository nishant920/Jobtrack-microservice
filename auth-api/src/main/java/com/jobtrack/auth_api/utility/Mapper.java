package com.jobtrack.auth_api.utility;

import com.jobtrack.auth_api.dto.AppUserDto;
import com.jobtrack.auth_api.dto.UserResponseDto;
import com.jobtrack.auth_api.model.AppUser;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public AppUser mapAppUserDtoToAppUser(AppUserDto appUserDto){
          AppUser appUser = new AppUser();
          appUser.setName(appUserDto.getName());
          appUser.setEmail(appUserDto.getEmail());
          return appUser;
    }

    public UserResponseDto mapAppUserToResponseDto(AppUser appUser){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(appUser.getName());
        userResponseDto.setEmail(appUser.getEmail());
        userResponseDto.setVerified(appUser.isVerified());
        return userResponseDto;
    }
}
