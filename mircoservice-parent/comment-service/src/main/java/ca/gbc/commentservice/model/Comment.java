package ca.gbc.commentservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Table(name = "t_comments")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String commentTitle;
    private String commentContent;
    private String commentAuthor;
    private String commentDate;
    private String commentPostId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> commentLineItemList;

    // Constructor
    public Comment(String commentTitle, String commentContent, String commentAuthor, String commentDate, String commentPostId, List<Comment> commentLineItemList) {
        this.commentTitle = commentTitle;
        this.commentContent = commentContent;
        this.commentAuthor = commentAuthor;
        this.commentDate = commentDate;
        this.commentPostId = commentPostId;
        this.commentLineItemList = commentLineItemList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public void setCommentPostId(String commentPostId) {
        this.commentPostId = commentPostId;
    }

    public void setCommentLineItemList(List<Comment> commentLineItemList) {
        this.commentLineItemList = commentLineItemList;
    }



}
