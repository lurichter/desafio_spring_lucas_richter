package desafio_spring.socialmeli.controllers;


import desafio_spring.socialmeli.dto.*;
import desafio_spring.socialmeli.exceptions.InvalidUserFollowException;
import desafio_spring.socialmeli.exceptions.UserNotFoundException;
import desafio_spring.socialmeli.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/newuser")
    public UserDTO newUser(@RequestBody UserRequestDTO user) {
        return this.userService.insertUser(user);
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public RelationshipDTO followUser(@PathVariable String userId, @PathVariable String userIdToFollow) throws UserNotFoundException, InvalidUserFollowException {
        if (!userService.validUser(userId)) {
            throw new UserNotFoundException("User id " + userId + " not found.");
        }
        if (!userService.validUser(userIdToFollow)) {
            throw new UserNotFoundException("User id " + userIdToFollow + " not found.");
        }
        if (userId.equals(userIdToFollow)) {
            throw new InvalidUserFollowException("User id " + userId + " can't follow itself.");
        }
        if (userService.validRelationship(userId, userIdToFollow)) {
            throw new InvalidUserFollowException("User id " + userId + " already follow user " + userIdToFollow + ".");
        }
        if (userService.sellerUser(userIdToFollow)) {
            throw new InvalidUserFollowException("User id " + userIdToFollow + " is not a seller.");
        }
        return this.userService.followUser(userId, userIdToFollow);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public RelationshipDTO unfollowUser(@PathVariable String userId, @PathVariable String userIdToUnfollow) throws UserNotFoundException {
        if (!userService.validUser(userId)) {
            throw new UserNotFoundException("User id " + userId + " not found.");
        }
        if (!userService.validUser(userIdToUnfollow)) {
            throw new UserNotFoundException("User id " + userIdToUnfollow + " not found.");
        }
        if (!userService.validRelationship(userId, userIdToUnfollow)) {
            throw new UserNotFoundException("User id " + userId + " don't follow user " + userIdToUnfollow + ".");
        }

        return this.userService.unfollowUser(userId, userIdToUnfollow);
    }

    @GetMapping("{userId}/followers/count")
    public UserFollowersCountDTO userFollowersCount(@PathVariable String userId) throws UserNotFoundException {
        if (!userService.validUser(userId)) {
            throw new UserNotFoundException("User id " + userId + " not found.");
        }
        return this.userService.getUserFollowersCount(userId);
    }

    @GetMapping("{userId}/followers/list")
    public UserRelationshipsDTO userFollowers(@PathVariable String userId, @RequestParam(required = false) String order) throws UserNotFoundException {
        if (!userService.validUser(userId)) {
            throw new UserNotFoundException("User id " + userId + " not found.");
        }
        return this.userService.getUserFollowers(userId, order);
    }

    @GetMapping("{userId}/followed/list")
    public UserRelationshipsDTO userFollows(@PathVariable String userId, @RequestParam(required = false) String order) throws UserNotFoundException {
        if (!userService.validUser(userId)) {
            throw new UserNotFoundException("User id " + userId + " not found.");
        }
        return this.userService.getUserFollows(userId, order);
    }

    @ExceptionHandler({UserNotFoundException.class, InvalidUserFollowException.class})
    public ResponseEntity<ErrorDTO> exceptionHandler(Exception exception) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setDescription(exception.getMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

}
