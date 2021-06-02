package desafio_spring.socialmeli.controllers;

import desafio_spring.socialmeli.dto.*;
import desafio_spring.socialmeli.exceptions.UserNotFoundException;
import desafio_spring.socialmeli.services.PostService;
import desafio_spring.socialmeli.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {

    private PostService postService;
    private UserService userService;

    public ProductController(PostService postService, UserService userService) {

        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/newpost")
    public PostDTO newPost(@RequestBody PostRequestDTO post) throws UserNotFoundException {
        String userId = post.getUserId();
        if (!userService.validUser(userId)) {
            throw new UserNotFoundException("User id " + userId + " not found.");
        }
        return this.postService.insertPost(post);
    }

    @PostMapping("/newpromopost")
    public PostDTO newPromoPost(@RequestBody PromoPostRequestDTO post) throws UserNotFoundException {
        String userId = post.getUserId();
        if (!userService.validUser(userId)) {
            throw new UserNotFoundException("User id " + userId + " not found.");
        }
        return this.postService.insertPromoPost(post);
    }

    @GetMapping("/followed/{userId}/list")
    public UserPostsDTO userFollowedRecentPosts(@PathVariable String userId, @RequestParam(required = false) String order) throws UserNotFoundException {
        if (!userService.validUser(userId)) {
            throw new UserNotFoundException("User id " + userId + " not found.");
        }
        return this.postService.getUserFollowedRecentPosts(userId, order);
    }

    @GetMapping("/{userId}/countpromo")
    public PromoPostCountDTO userPromoPostsCount(@PathVariable String userId) throws UserNotFoundException {
        if (!userService.validUser(userId)) {
            throw new UserNotFoundException("User id " + userId + " not found.");
        }
        return this.postService.getUserPromoPostsCount(userId);
    }

    @GetMapping("/{userId}/list")
    public UserPostsDTO userPromoPosts(@PathVariable String userId) throws UserNotFoundException {
        if (!userService.validUser(userId)) {
            throw new UserNotFoundException("User id " + userId + " not found.");
        }
        return this.postService.getUserPromoPosts(userId);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorDTO> exceptionHandler(Exception exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setDescription(exception.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
