package services.webplus.twitter.api.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import services.webplus.twitter.api.payload.SignUpRequest;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "account_role")
    public Set<Role> roles = new HashSet<>();

    public boolean checkedPassword(String password, PasswordEncoder encoder) {
        return encoder.matches(this.password, password);
    }

    public static Account convertRequestToModel(SignUpRequest request, PasswordEncoder passwordEncoder) {
        Account account = new Account();
        account.setUsername(request.username());
        account.setEmail(request.email());
        account.setPassword(passwordEncoder.encode(request.password()));
        account.setRoles(Set.of(Role.U));

        return account;
    }
}
