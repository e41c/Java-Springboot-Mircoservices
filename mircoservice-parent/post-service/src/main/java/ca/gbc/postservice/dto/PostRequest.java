package ca.gbc.postservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequest {
    private String postTitle;
    private String postContent;
    private LocalDateTime postDate;
    private String postAuthor;
}
