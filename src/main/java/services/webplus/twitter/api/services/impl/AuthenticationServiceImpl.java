package services.webplus.twitter.api.services.impl;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import services.webplus.twitter.api.payload.SignInRequest;
import services.webplus.twitter.api.payload.SignInResponse;
import services.webplus.twitter.api.repositories.AccountRepository;
import services.webplus.twitter.api.services.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtEncoder jwtEncoder;

    public SignInResponse authenticated(SignInRequest request) {
        var account = accountRepository.findByEmail(request.email()).get();
        
        if(account == null || account.checkedPassword(request.password(), passwordEncoder)){
            throw new RuntimeException("Account or password is invalid");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var scopes = account.getRoles()
                .stream()
                .map(role -> role.toString())
                .collect(Collectors.toSet());

        System.out.println("****** *********** ************ "+scopes);

        var claims = JwtClaimsSet.builder()
                        .issuer("twitter-api")
                        .subject(account.getEmail())
                        .expiresAt(now.plusSeconds(expiresIn))
                        .claim("scope", scopes)
                        .issuedAt(now).build();
        
        String jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new SignInResponse(jwtToken, expiresIn);
    }
}
