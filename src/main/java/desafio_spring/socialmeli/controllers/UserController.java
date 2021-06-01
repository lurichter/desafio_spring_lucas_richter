package desafio_spring.socialmeli.controllers;


import desafio_spring.socialmeli.dto.RelationshipDTO;
import desafio_spring.socialmeli.dto.UserDTO;
import desafio_spring.socialmeli.dto.UserFollowersCountDTO;
import desafio_spring.socialmeli.dto.UserRequestDTO;
import desafio_spring.socialmeli.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserRequestDTO user) {
        return this.userService.insertUser(user);
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public RelationshipDTO followUser(@PathVariable String userId, @PathVariable String userIdToFollow) {
        return this.userService.followUser(userId, userIdToFollow);
    }

    @GetMapping("{userId}/followers/count")
    public UserFollowersCountDTO userFollowersCount(@PathVariable String userId) {
        return this.userService.getUserFollowersCount(userId);
    }

}
