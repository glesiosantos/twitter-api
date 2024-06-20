package services.webplus.twitter.api.services;

import java.util.List;

import services.webplus.twitter.api.entities.Account;
import services.webplus.twitter.api.payload.SignUpRequest;

public interface AccountService {
    Account createAccount(SignUpRequest request) throws Exception;
    List<Account> loadAll();
    Account findByEmail(String email) throws Exception;
}
