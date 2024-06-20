package services.webplus.twitter.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import services.webplus.twitter.api.services.impl.AccountServiceImpl;

@RestController
@RequestMapping("accounts")
public class AccountController {
    
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> loadAccountsRegistered() {
        return ResponseEntity.ok(accountService.loadAll());
    }
}
