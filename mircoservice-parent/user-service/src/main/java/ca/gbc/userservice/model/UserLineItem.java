package ca.gbc.userservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "t_user_line_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLineItem {
    @Id
    private Long id;
    private String username;
    private String email;
}
