package services.webplus.twitter.api.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import services.webplus.twitter.api.payload.SignInRequest;
import services.webplus.twitter.api.payload.SignUpRequest;
import services.webplus.twitter.api.services.impl.AccountServiceImpl;
import services.webplus.twitter.api.services.impl.AuthenticationServiceImpl;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private AccountServiceImpl accountService;
    
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
        var result = authenticationService.authenticated(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) throws Exception {
        var account = accountService.createAccount(request);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/{id}")
                        .buildAndExpand(account.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
