package services.webplus.twitter.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import services.webplus.twitter.api.entities.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    @Query(value = "SELECT * FROM tweets t WHERE t.account_id = :id", nativeQuery = true)
    List<Tweet> findByAccount(@Param("id") Long id);
}
