package desafio_spring.socialmeli.controllers;

import desafio_spring.socialmeli.dto.PostDTO;
import desafio_spring.socialmeli.dto.PostRequestDTO;
import desafio_spring.socialmeli.dto.UserPostsDTO;
import desafio_spring.socialmeli.services.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private PostService postService;

    public ProductController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/newpost")
    public PostDTO newPost(@RequestBody PostRequestDTO post) {
        return this.postService.insertPost(post);
    }

    @GetMapping("/followed/{userId}/list")
    public UserPostsDTO userFollowedRecentPosts(@PathVariable String userId) {
        return this.postService.getUserFollowedRecentPosts(userId);
    }

}
