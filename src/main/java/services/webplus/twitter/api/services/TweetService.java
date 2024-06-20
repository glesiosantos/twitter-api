package services.webplus.twitter.api.services;

import java.util.List;
import java.util.UUID;

import services.webplus.twitter.api.entities.Tweet;
import services.webplus.twitter.api.payload.TweetRequest;

public interface TweetService {
    Tweet addTweet(TweetRequest request, UUID email) throws Exception;
    List<Tweet> loadAllTweets();
    List<Tweet> loadTweetByAccount(Long id);
}
