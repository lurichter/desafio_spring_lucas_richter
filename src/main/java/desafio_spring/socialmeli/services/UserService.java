package desafio_spring.socialmeli.services;

import desafio_spring.socialmeli.dto.*;
import desafio_spring.socialmeli.exceptions.InvalidUserFollowException;
import desafio_spring.socialmeli.exceptions.UserNotFoundException;
import desafio_spring.socialmeli.repositories.RelationshipRepository;
import desafio_spring.socialmeli.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private RelationshipRepository relationshipRepository;

    public UserService(UserRepository userRepository, RelationshipRepository relationshipRepository) {
        this.userRepository = userRepository;
        this.relationshipRepository = relationshipRepository;
    }

    public UserDTO insertUser(UserRequestDTO user) {
        List<UserDTO> users = this.userRepository.getDatabase();
        String newUserId = String.valueOf(users.size() + 1);
        UserDTO newUser = new UserDTO(newUserId, user.getUserName(), user.getSeller());
        users.add(newUser);
        this.userRepository.updateDatabase(users);
        return newUser;
    }

    public RelationshipDTO followUser(String userId, String userIdToFollow) {
        List<RelationshipDTO> relationships = this.relationshipRepository.getDatabase();
        RelationshipDTO relationship = new RelationshipDTO(userId, userIdToFollow);
        relationships.add(relationship);
        this.relationshipRepository.updateDatabase(relationships);
        return relationship;
    }

    public RelationshipDTO unfollowUser(String userId, String userIdToUnfollow) {
        List<RelationshipDTO> relationships = this.relationshipRepository.getDatabase();
        RelationshipDTO relationship = relationships.stream()
                                                    .filter(r -> r.getFollowerId().equals(userId)
                                                            & r.getFollowedId().equals(userIdToUnfollow))
                                                    .findFirst()
                                                    .orElse(null);
        relationships.remove(relationship);
        this.relationshipRepository.updateDatabase(relationships);
        return relationship;
    }

    public UserFollowersCountDTO getUserFollowersCount(String userId) {
        UserDTO user = this.userRepository.getUserById(userId);
        List<RelationshipDTO> followers = this.relationshipRepository.getRelationshipsByFollowedId(userId);

        UserFollowersCountDTO userFollowersCount = new UserFollowersCountDTO();
        userFollowersCount.setUserId(userId);
        userFollowersCount.setUserName(user.getUserName());
        userFollowersCount.setFollowersCount(followers.size());

        return userFollowersCount;
    }

    public UserRelationshipsDTO getUserFollowers(String userId, String order) {
        UserDTO user = this.userRepository.getUserById(userId);
        List<RelationshipDTO> relationships = this.relationshipRepository.getRelationshipsByFollowedId(userId);
        List<UserDTO> followers = this.userRepository.getUsersByListOfId(relationships.stream().map(RelationshipDTO::getFollowerId).collect(Collectors.toList()));

        if (order != null) {
            if (order.equals("name_asc")) {
                followers.sort(Comparator.comparing(UserDTO::getUserName));
            } else if (order.equals("name_desc")) {
                followers.sort(Comparator.comparing(UserDTO::getUserName).reversed());
            }
        }

        UserRelationshipsDTO userFollowers = new UserRelationshipsDTO();
        userFollowers.setUserId(userId);
        userFollowers.setUserName(user.getUserName());
        userFollowers.setFollowers(followers);

        return userFollowers;
    }

    public UserRelationshipsDTO getUserFollows(String userId, String order) {
        UserDTO user = this.userRepository.getUserById(userId);
        List<RelationshipDTO> relationships = this.relationshipRepository.getRelationshipsByFollowerId(userId);
        List<UserDTO> follows = this.userRepository.getUsersByListOfId(relationships.stream().map(RelationshipDTO::getFollowedId).collect(Collectors.toList()));

        if (order != null) {
            if (order.equals("name_asc")) {
                follows.sort(Comparator.comparing(UserDTO::getUserName));
            } else if (order.equals("name_desc")) {
                follows.sort(Comparator.comparing(UserDTO::getUserName).reversed());
            }
        }

        UserRelationshipsDTO userFollows = new UserRelationshipsDTO();
        userFollows.setUserId(userId);
        userFollows.setUserName(user.getUserName());
        userFollows.setFollowers(follows);

        return userFollows;
    }

    public boolean validUser (String userId) {
        if (this.userRepository.getUserById(userId) == null) {
            return false;
        } else {
            return true;}
    }

    public boolean sellerUser (String userId) {
        return this.userRepository.getUserById(userId).isSeller();
    }

    public boolean validRelationship (String followerId, String followedId) {
        List<RelationshipDTO> relationships = this.relationshipRepository.getRelationshipsByFollowerId(followerId);
        RelationshipDTO relationship = relationships.stream()
                                                    .filter(RelationshipDTO -> RelationshipDTO.getFollowedId()
                                                                                                .equals(followedId))
                                                    .findFirst()
                                                    .orElse(null);
        if (relationship != null) {
            return true;
        } else {
            return false;
        }
    }
}
