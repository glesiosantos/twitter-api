package services.webplus.twitter.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import services.webplus.twitter.api.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
    
}
