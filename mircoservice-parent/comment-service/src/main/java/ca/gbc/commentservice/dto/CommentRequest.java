package ca.gbc.commentservice.dto;

import ca.gbc.commentservice.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequest {

        private String commentTitle;
        private String commentContent;
        private String commentAuthor;
        private String commentDate;
        private String commentPostId;

        private List<CommentLineItemDto> commentLineItemDtos= new
                ArrayList<>();
}
