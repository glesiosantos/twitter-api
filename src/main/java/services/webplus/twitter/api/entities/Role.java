package services.webplus.twitter.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role{

    A("Admin"), U("User");

    private String name;
}
