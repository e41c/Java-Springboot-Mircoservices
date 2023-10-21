package ca.gbc.userservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "t_users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // not worth doing this, but ok.
    private List<User> userLineItemList;
    // Constructors, getters, and setters
}
