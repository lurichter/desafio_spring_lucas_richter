package desafio_spring.socialmeli.services;

import desafio_spring.socialmeli.dto.RelationshipDTO;
import desafio_spring.socialmeli.dto.UserDTO;
import desafio_spring.socialmeli.dto.UserFollowersCountDTO;
import desafio_spring.socialmeli.dto.UserRequestDTO;
import desafio_spring.socialmeli.repositories.RelationshipRepository;
import desafio_spring.socialmeli.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public UserFollowersCountDTO getUserFollowersCount(String userId) {
        UserDTO user = this.userRepository.getUserById(userId);
        List<RelationshipDTO> relationships = this.relationshipRepository.getRelationshipsByFollowedId(userId);

        UserFollowersCountDTO userFollowersCount = new UserFollowersCountDTO();
        userFollowersCount.setUserId(userId);
        userFollowersCount.setUserName(user.getUserName());
        userFollowersCount.setFollowersCount(relationships.size());

        return userFollowersCount;
    }


}
