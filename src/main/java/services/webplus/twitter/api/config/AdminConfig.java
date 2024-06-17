package services.webplus.twitter.api.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import services.webplus.twitter.api.entities.Account;
import services.webplus.twitter.api.repositories.AccountRepository;
import services.webplus.twitter.api.repositories.RoleRepository;

@Configuration
public class AdminConfig implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;
    
    public void populationAdminAccount() {
        
    }

    @Override
    public void run(String... args) throws Exception {

        var role = roleRepository.findByProfile("ADMIN").get();

        System.out.println(role.getProfile());

        accountRepository.findByEmail("glesioss@gmail.com")
            .ifPresentOrElse(
                acc -> System.out.println("Account exists"),
                () -> {
                    Account account = new Account();
                    account.setEmail("glesioss@gmail.com");
                    account.setUsername("glesio.santos");
                    account.setRoles(Set.of(role));

                    accountRepository.save(account);
                    System.out.println("Create with success!");
                } 
            );

        
    }
}
