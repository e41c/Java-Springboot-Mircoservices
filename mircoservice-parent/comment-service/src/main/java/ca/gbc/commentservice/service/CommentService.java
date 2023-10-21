package ca.gbc.commentservice.service;

import ca.gbc.commentservice.dto.CommentRequest;
import ca.gbc.commentservice.dto.CommentResponse;

import java.util.List;

public interface CommentService {

    void createComment(CommentRequest commentRequest);

    String updateComment(String commentId, CommentRequest commentRequest);

    void deleteComment(String commentId);

    List<CommentResponse> getAllComments();
}
