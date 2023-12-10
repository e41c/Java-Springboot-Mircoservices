package ca.gbc.postservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "post")
public class Post {
    @Id
    private String id;

    private String postTitle;
    private String postContent;
    private LocalDateTime postDate;
    private String postAuthor;


}
