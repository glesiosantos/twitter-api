package services.webplus.twitter.api.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import services.webplus.twitter.api.entities.Account;
import services.webplus.twitter.api.entities.Tweet;
import services.webplus.twitter.api.payload.TweetRequest;
import services.webplus.twitter.api.repositories.TweetRepository;

@Service
public class TweetServiceImpl {
    
    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private AccountServiceImpl accountService;

    public Tweet addTweet(TweetRequest request, UUID email) throws Exception {
        Account account = accountService.findByEmail(email.toString());
        return tweetRepository.save(Tweet.convertRequestToModel(request, account));
    }

    @Transactional(readOnly = true)
    public List<Tweet> loadAllTweets() {
        return tweetRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Tweet> loadTweetByAccount(Long id) {
        return tweetRepository.findAll();
    }
}
