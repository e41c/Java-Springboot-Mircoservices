package ca.gbc.commentservice.controller;

import ca.gbc.commentservice.dto.CommentRequest;
import ca.gbc.commentservice.dto.CommentResponse;
import ca.gbc.commentservice.service.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestBody CommentRequest commentRequest) {
        commentService.createComment(commentRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponse> getAllComments() {
        return commentService.getAllComments();
    }

    // http:localhost:8080/api/comment/1
    @PutMapping({"/{commentId}"})
    public ResponseEntity<?> updateComment(@PathVariable("commentId") String commentId,
                                           @RequestBody CommentRequest commentRequest) {

        String updateCommentId = commentService.updateComment(commentId, commentRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/comment/" + updateCommentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //http://localhost:8080/api/comment/1
    @DeleteMapping({"/{commentId}"})
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") String commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
