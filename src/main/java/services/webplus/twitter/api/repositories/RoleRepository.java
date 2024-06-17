package services.webplus.twitter.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import services.webplus.twitter.api.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByProfile(String string);
    
}
