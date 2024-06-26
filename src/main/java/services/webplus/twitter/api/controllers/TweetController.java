package services.webplus.twitter.api.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import services.webplus.twitter.api.payload.TweetRequest;
import services.webplus.twitter.api.services.impl.TweetServiceImpl;

@RestController
@RequestMapping("tweets")
public class TweetController {

    @Autowired
    private TweetServiceImpl tweetService;
    
    @PostMapping
    public ResponseEntity<?> addTweet(@RequestBody TweetRequest request, JwtAuthenticationToken token) throws Exception {
        var tweet = tweetService.addTweet(request, token.getName()); 
        return ResponseEntity.ok(tweet);
    }

    @GetMapping
    public ResponseEntity<?> loadTweets() {
        return ResponseEntity.ok(tweetService.loadAllTweets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> loadTweetByAccount(@PathVariable Long id) {
        var tweets = tweetService.loadAllTweetsByAccount(id);
        return ResponseEntity.ok().body(tweets);
    }
}
