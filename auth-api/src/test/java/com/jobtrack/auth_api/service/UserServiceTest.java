package com.jobtrack.auth_api.service;

import com.jobtrack.auth_api.repository.UserRepository;
import com.jobtrack.auth_api.utility.Mapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest {


    @Mock
    Mapper mapper;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;



}
