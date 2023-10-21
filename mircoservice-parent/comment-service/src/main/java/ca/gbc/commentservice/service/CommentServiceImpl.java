package ca.gbc.commentservice.service;

import ca.gbc.commentservice.dto.CommentRequest;
import ca.gbc.commentservice.dto.CommentResponse;
import ca.gbc.commentservice.model.Comment;
import ca.gbc.commentservice.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Override
    public void createComment(CommentRequest commentRequest) {
        Comment comment = Comment.builder()
                .commentTitle(commentRequest.getCommentTitle())
                .commentContent(commentRequest.getCommentContent())
                .commentAuthor(commentRequest.getCommentAuthor())
                .commentDate(commentRequest.getCommentDate())
                .commentPostId(commentRequest.getCommentPostId())
                .build();

        commentRepository.save(comment);

    }

    @Override
    public String updateComment(String commentId, CommentRequest commentRequest) {
        Optional<Comment> optionalComment = commentRepository.findById(Long.parseLong(commentId));

        if(optionalComment.isPresent()){
            Comment comment = optionalComment.get();
            comment.setCommentTitle(commentRequest.getCommentTitle());
            comment.setCommentContent(commentRequest.getCommentContent());
            comment.setCommentAuthor(commentRequest.getCommentAuthor());
            comment.setCommentDate(commentRequest.getCommentDate());
            comment.setCommentPostId(commentRequest.getCommentPostId());
            commentRepository.save(comment);
            return "Comment updated successfully";
        }
        else{
            return "Comment not found";
        }
    }

    @Override
    public void deleteComment(String commentId) { commentRepository
            .deletebyId(Long.parseLong(commentId));

    }

    @Override
    public List<CommentResponse> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
