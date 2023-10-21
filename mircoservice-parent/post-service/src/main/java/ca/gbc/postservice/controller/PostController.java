package ca.gbc.postservice.controller;

import ca.gbc.postservice.dto.PostRequest;
import ca.gbc.postservice.dto.PostResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostRequest postRequest){
        //productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getAllPosts(){
        //return productService.getAllProducts();
        return null;
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updatePost(@PathVariable("id") String id,
                                           @RequestBody PostRequest postRequest){

        //String updatedProductId = productService.updateProduct(productId,productRequest);
        //HttpHeaders headers = new HttpHeaders();
        //headers.add("Location", "api/product/"+ updatedProductId);

        //return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deletePost(@PathVariable("id") String id){
        //productService.deleteProduct(productId);
        //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return null;
    }


}
