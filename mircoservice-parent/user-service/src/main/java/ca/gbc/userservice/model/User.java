package ca.gbc.userservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Table(name = "t_users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class User {
    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<User> userLineItemList;


    // Constructor
    public User(String username, String email, List<User> userLineItemList) {
        this.username = username;
        this.email = email;
        this.userLineItemList = userLineItemList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserLineItemList(List<User> userLineItemList) {
        this.userLineItemList = userLineItemList;
    }

}
