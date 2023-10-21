package ca.gbc.commentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentLineItemDto {

    private Long id;
    private String commentTitle;
    private String commentContent;
    private String commentAuthor;
    private String commentDate;
    private String commentPostId;

}
