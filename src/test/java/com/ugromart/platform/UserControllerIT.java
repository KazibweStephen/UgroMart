package com.ugromart.platform;

import com.ugromart.platform.configuration.NotFoundException;
import com.ugromart.platform.product.models.Product;
import com.ugromart.platform.user.models.Auth;
import com.ugromart.platform.user.models.User;
import com.ugromart.platform.user.models.UserCreateResponse;
import com.ugromart.platform.user.models.UserLogin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.remote.client.HttpHeaderInterceptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.remoting.httpinvoker.HttpInvokerClientInterceptor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @LocalServerPort
    private int localPort;
    private URL baseUrl;
    RestTemplate restTemplate;
    @BeforeEach
    public void setUp() throws Exception{
        restTemplate=new RestTemplate();
        this.baseUrl=new URL("http://localhost:"+localPort+"/api/");
    }

    @Test
    public void whenAUserIsRegistered_ThenItShouldRespondWith200() throws Exception
    {
        UserLogin user= new UserLogin( "stephen4@gmail.com","12345");
        ResponseEntity<UserCreateResponse> response=restTemplate.postForEntity(baseUrl.toString()+"user/register",user, UserCreateResponse.class);
        assertThat(response.getStatusCode().is2xxSuccessful());
    }
    @Test
    public void whenNoUserHasBeenCreated_itShouldThrowUnAuthenticationException() throws Exception{
        assertThatThrownBy(()->{
            UserLogin loginUser=new UserLogin( "stephen4@gmail.com","12345");
            ResponseEntity<Auth> response=restTemplate.postForEntity(baseUrl.toString()+"user/login",loginUser, Auth.class);
            assertThat(response.getStatusCode().is4xxClientError());
        }).isInstanceOf(HttpClientErrorException.Unauthorized.class);//.hasMessage("User with username stephen4@gmail.com not found");
    }

    @Test
    public void whenAuthenticated_thenCanFetchProductsFromProductsMs() throws Exception{
        registerUser();
        String token =loginAndGetToken();
        assertThat(String.valueOf(token)!= "null");
        String bearer= "Bearer "+token;
        List<ClientHttpRequestInterceptor> interceptors =new ArrayList<>();
        interceptors.add(new HttpHeaderInterceptor("Authorization",bearer));
        interceptors.add(new HttpHeaderInterceptor("Accept", MediaType.APPLICATION_JSON_VALUE));
        interceptors.add(new HttpHeaderInterceptor("Content-Type", MediaType.APPLICATION_JSON_VALUE));
        restTemplate.setInterceptors(interceptors);
        ResponseEntity<Product[]> response= restTemplate.getForEntity(baseUrl.toString()+"products/", Product[].class);
        assertThat(response.getStatusCode().is2xxSuccessful());
        assertThat(response.getBody().length>1);

    }

    public void registerUser() throws Exception{
        UserLogin user= new UserLogin( "stephen5@gmail.com","12345");
        ResponseEntity<UserCreateResponse> response=restTemplate.postForEntity(baseUrl.toString()+"user/register",user, UserCreateResponse.class);
    }
    public String loginAndGetToken(){
        UserLogin loginUser=new UserLogin( "stephen5@gmail.com","12345");
        ResponseEntity<Auth> response=restTemplate.postForEntity(baseUrl.toString()+"user/login",loginUser, Auth.class);
        return response.getBody().getToken();
    }
}
