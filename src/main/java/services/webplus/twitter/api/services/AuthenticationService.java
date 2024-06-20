package services.webplus.twitter.api.services;

import services.webplus.twitter.api.payload.SignInRequest;
import services.webplus.twitter.api.payload.SignInResponse;

public interface AuthenticationService {
    SignInResponse authenticated(SignInRequest request);
}
