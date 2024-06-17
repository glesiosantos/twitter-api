package services.webplus.twitter.api.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.nimbusds.jwt.JWTClaimsSet;

import services.webplus.twitter.api.payload.SignInRequest;
import services.webplus.twitter.api.payload.SignInResponse;
import services.webplus.twitter.api.repositories.AccountRepository;

@Service
public class AuthenticationService {
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtEncoder jwtEncoder;

    public SignInResponse authenticated(SignInRequest request) {
        var account = accountRepository.findByEmail(request.email()).get();

        if(account == null || account.checkedPassword(request.password(), bCryptPasswordEncoder)){
            throw new RuntimeException("Account or password is invalid");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var claims = JwtClaimsSet.builder()
                        .issuer("twitter-api")
                        .subject(account.getEmail())
                        .expiresAt(now.plusSeconds(expiresIn))
                        .issuedAt(now).build();
        
        String jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new SignInResponse(jwtToken, expiresIn);
    }
}