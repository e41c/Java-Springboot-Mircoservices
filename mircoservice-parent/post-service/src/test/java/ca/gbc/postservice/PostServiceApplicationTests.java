package ca.gbc.postservice;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import ca.gbc.postservice.dto.PostRequest;

import ca.gbc.postservice.model.Post;
import ca.gbc.postservice.repository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
class PostServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    private PostRequest getPostRequest(){
        return PostRequest.builder()
                .postTitle("Post Title")
                .postContent("Post Content")
                .postDate(LocalDateTime.now())
                .postAuthor("Post Author")
                .build();
    }

    private List<Post> getPosts(){

        return  List.of(
                Post.builder()
                        .postTitle("Post Title 1")
                        .postContent("Post Content 1")
                        .postDate(LocalDateTime.now())
                        .postAuthor("Post Author 1")
                        .build(),
                Post.builder()
                        .postTitle("Post Title 2")
                        .postContent("Post Content 2")
                        .postDate(LocalDateTime.now())
                        .postAuthor("Post Author 2")
                        .build(),
                Post.builder()
                        .postTitle("Post Title 3")
                        .postContent("Post Content 3")
                        .postDate(LocalDateTime.now())
                        .postAuthor("Post Author 3")
                        .build()
        );
    }
    @Test
    void getAllPosts() throws Exception {
        postRepository.saveAll(getPosts());

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/post")
                .accept(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andDo(MockMvcResultHandlers.print());

        MvcResult result = response.andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        JsonNode jsonNodes = new ObjectMapper().readTree(jsonResponse);

        int actualSize = jsonNodes.size();
        int expectedSize = getPosts().size();

        assertTrue(actualSize >= expectedSize);
    }

    @Test
    public void createPost() throws Exception {
        PostRequest postRequest = getPostRequest();
        String postRequestJsonString = objectMapper.writeValueAsString(postRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postRequestJsonString))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Assertions.assertTrue((postRepository.findAll().size() > 0));

        Query query = new Query();
        query.addCriteria(Criteria.where("postTitle").is("Post Title"));
        List<Post> posts = mongoTemplate.find(query, Post.class);
        Assertions.assertTrue((posts.size() > 0));
    }



    @Test
    void updatePosts() throws Exception{
        //create a post to update
        Post savedPost = Post.builder()
                .postTitle("Initial Post Title")
                .postContent("Initial Post Content ")
                .postDate(LocalDateTime.now())
                .postAuthor("Initial Post Author ")
                .build();
        // save post to db
        postRepository.save(savedPost);

        PostRequest postRequest = getPostRequest();
        String postRequestJsonString = objectMapper.writeValueAsString(postRequest);
        //turn postRequest into json string

        //pass json string to endpoint
        mockMvc.perform(MockMvcRequestBuilders.put("/api/post/" + savedPost.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(postRequestJsonString))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(savedPost.getId()));
        Post post = mongoTemplate.findOne(query, Post.class);

        Assertions.assertEquals(postRequest.getPostTitle(), post.getPostTitle());
        Assertions.assertEquals(postRequest.getPostContent(), post.getPostContent());
        Assertions.assertEquals(postRequest.getPostAuthor(), post.getPostAuthor());
        Assertions.assertEquals(postRequest.getPostDate(), post.getPostDate());
    }

}
