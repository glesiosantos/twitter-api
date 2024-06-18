package services.webplus.twitter.api.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import services.webplus.twitter.api.entities.Account;
import services.webplus.twitter.api.entities.Role;
import services.webplus.twitter.api.repositories.AccountRepository;

@Configuration
public class AdminConfig implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        accountRepository.findByEmail("glesioss@gmail.com")
            .ifPresentOrElse(
                acc -> System.out.println("Account exists"),
                () -> {
                    Account account = new Account();
                    account.setEmail("glesioss@gmail.com");
                    account.setUsername("glesio.santos");
                    account.setRoles(Set.of(Role.A));
                    account.setPassword(passwordEncoder.encode("102030"));
                    accountRepository.save(account);
                    System.out.println("Create with success!");
                } 
            );

        
    }
}
