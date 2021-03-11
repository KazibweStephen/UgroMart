package com.ugromart.platform.user;


import com.ugromart.platform.Security.JwtTokenUtil;
import com.ugromart.platform.user.models.User;
import com.ugromart.platform.user.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Tag(name="Authentication")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    //@Autowired
    //private UserViewMapper userViewMapper;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        userService.save(user);
        return  ResponseEntity.ok().body(user);
    }
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid User user){
        try{
            Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword()));
            org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();

            return  ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION,jwtTokenUtil.generateAccessToken(principal)).body(user);
        }catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){

        return new ResponseEntity<>(new ArrayList<User>(), HttpStatus.OK);
    }
}
