package com.ugromart.platform.user;


import com.ugromart.platform.Security.JwtTokenUtil;
import com.ugromart.platform.configuration.NotFoundException;
import com.ugromart.platform.user.models.Auth;
import com.ugromart.platform.user.models.User;
import com.ugromart.platform.user.models.UserCreateResponse;
import com.ugromart.platform.user.models.UserLogin;
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
@RequestMapping("/api/user")
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
    public ResponseEntity<UserCreateResponse> register(@RequestBody User user){
        userService.save(user);
        User result =userService.findByUsername(user.getUsername());
        return  ResponseEntity.ok().body(new UserCreateResponse(result.getUsername(),result.getId()));
    }
    @PostMapping("/login")
    public ResponseEntity<Auth> login(@RequestBody @Valid UserLogin user){
        try{
            Authentication authentication =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                    user.getPassword()));
            org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User)authentication.getPrincipal();
            String token=jwtTokenUtil.generateAccessToken(principal);
            Auth auth=new Auth(0,principal.getUsername(),token);
            return  ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION,token).body(auth);
        }catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }catch (NotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/")
    public ResponseEntity<List<User>> getUsers(){

        return new ResponseEntity<>(new ArrayList<User>(), HttpStatus.OK);
    }
}
