package ca.gbc.postservice.service;

import ca.gbc.postservice.dto.PostRequest;
import ca.gbc.postservice.dto.PostResponse;
import ca.gbc.postservice.model.Post;
import ca.gbc.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceimpl implements PostService{

    private final PostRepository postRepository;
    private final MongoTemplate mongoTemplate;
    @Override
    public void createPost(PostRequest postRequest) {
        Post post = Post.builder()
                .postTitle(postRequest.getPostTitle())
                .postContent(postRequest.getPostContent())
                .postAuthor(postRequest.getPostAuthor())
                .postDate(postRequest.getPostDate())
                .build();
        postRepository.save(post);
        log.info("Post {} is saved", post.getId());
    }

    @Override
    public String updatePost(String id, PostRequest postRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Post post = mongoTemplate.findOne(query, Post.class);

        if(post != null){
            post.setPostTitle(postRequest.getPostTitle());
            post.setPostContent(postRequest.getPostContent());
            post.setPostAuthor(postRequest.getPostAuthor());
            post.setPostDate(postRequest.getPostDate());
            log.info("Post {} is updated", post.getId());
            return postRepository.save(post).getId();
        }
        return id;
    }

    @Override
    public void deletePost(String id) {
        log.info("Post {} is deleted", id);
        postRepository.deleteById(id);
    }

    @Override
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return mapPostToDto(posts);
    }
    private ModelMapper modelMapper;

    public List<PostResponse> mapPostToDto(List<Post> posts) {
        return posts.stream()
                .map(post -> modelMapper.map(post, PostResponse.class))
                .collect(Collectors.toList());
    }
}
