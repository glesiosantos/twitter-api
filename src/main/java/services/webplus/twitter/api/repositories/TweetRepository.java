package services.webplus.twitter.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import services.webplus.twitter.api.entities.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    
}
