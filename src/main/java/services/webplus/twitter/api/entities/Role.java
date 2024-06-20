package services.webplus.twitter.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role{

    ADMIN("Admin"), USER("User");

    private String name;
}
