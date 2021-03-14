package com.ugromart.platform;

import com.ugromart.platform.user.models.User;
import com.ugromart.platform.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Override
    public void run(String... args) throws Exception {
        User user =new User();
        user.setUsername("kstephen4@gmail.com");
        user.setPassword("12345");
        userService.save(user);
 }
}
