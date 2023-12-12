package ca.gbc.postservice.service;

import ca.gbc.postservice.dto.PostRequest;
import ca.gbc.postservice.dto.PostResponse;
import ca.gbc.postservice.dto.UserRequest;
import ca.gbc.postservice.dto.UserResponse;
import ca.gbc.postservice.model.Post;
import ca.gbc.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceimpl implements PostService{

    private final PostRepository postRepository;
    private final MongoTemplate mongoTemplate;

    private final WebClient.Builder webClientBuilder;

    @Value("${user.service.url}")
    private String userApiUri;
    @Override
    public void createPost(PostRequest postRequest) {// when creating a post, call user-service to get user info
        Post post = Post.builder()
                .postTitle(postRequest.getPostTitle())
                .postContent(postRequest.getPostContent())
                .userEmail(postRequest.getUserEmail())
                .postDate(postRequest.getPostDate())
                .build();



        // call user-service to get user info
        UserResponse userResponse = findUser(postRequest.getUserEmail());


        // user exists save post to database
        if(userResponse != null){
            log.info("User exists "+ userResponse.getUsername());
            postRepository.save(post);
        }
        else{
            log.info("User does not exist");
        }
    }

    @Override
    public String updatePost(String id, PostRequest postRequest) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Post post = mongoTemplate.findOne(query, Post.class);

        if(post != null){
            post.setPostTitle(postRequest.getPostTitle());
            post.setPostContent(postRequest.getPostContent());
            post.setUserEmail(postRequest.getUserEmail());
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
    public List<PostResponse> getAllPosts() {// call user-service to get user info for each post
        log.info("Getting all posts");
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapToDto).toList();
    }
    private UserResponse findUser(String userEmail){
        UserRequest userRequest = new UserRequest();
        userRequest.setUserEmail(userEmail);

        UserResponse userResponse = webClientBuilder.build()
                .get()
                .uri(userApiUri + userRequest.getUserEmail())
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();
        return userResponse;
    }

    private PostResponse mapToDto(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .userEmail(post.getUserEmail())
                .postDate(post.getPostDate())
                .build();
    }
}
