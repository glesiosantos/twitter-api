package services.webplus.twitter.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import services.webplus.twitter.api.entities.Account;
import services.webplus.twitter.api.payload.SignUpRequest;
import services.webplus.twitter.api.repositories.AccountRepository;
import services.webplus.twitter.api.utils.exceptions.DuplicationObjectException;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account createAccount(SignUpRequest request) throws Exception {

        Optional<Account> optional = accountRepository.findByEmail(request.email());
        
        if(optional.isPresent()) {
            throw new DuplicationObjectException("Account is registered with email");
        }

        var account = Account.convertRequestToModel(request, passwordEncoder);
        return accountRepository.save(account);
    }
    
}
