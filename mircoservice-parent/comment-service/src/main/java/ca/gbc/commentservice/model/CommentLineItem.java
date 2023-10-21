package ca.gbc.commentservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_comment_line_item")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentLineItem {

    @Id
    private Long id;
    private String comment;
}
