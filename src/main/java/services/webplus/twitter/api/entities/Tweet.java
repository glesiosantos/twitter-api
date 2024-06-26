package services.webplus.twitter.api.entities;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import services.webplus.twitter.api.payload.TweetRequest;

@Getter
@Setter
@Entity
@Table(name = "tweets")
public class Tweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Account account;

    private String context;

    @CreationTimestamp
    @Column(name="created_at")
    private Instant createdAt;

    public static Tweet convertRequestToModel(TweetRequest request, Account account) {
        Tweet tweet = new Tweet();
        tweet.setContext(request.context());
        tweet.setAccount(account);
        tweet.setCreatedAt(Instant.now());
        return tweet;
    }
}
