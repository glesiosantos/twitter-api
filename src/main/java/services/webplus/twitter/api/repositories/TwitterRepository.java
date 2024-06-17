package services.webplus.twitter.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import services.webplus.twitter.api.entities.Twitter;

public interface TwitterRepository extends JpaRepository<Twitter, Long> {
    
}
