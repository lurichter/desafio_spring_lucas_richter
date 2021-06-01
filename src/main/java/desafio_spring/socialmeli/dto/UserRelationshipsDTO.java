package desafio_spring.socialmeli.dto;

import java.util.List;

public class UserRelationshipsDTO {

    private String userId;
    private String userName;
    private List<UserDTO> followers;

    public UserRelationshipsDTO() {
    }

    public UserRelationshipsDTO(String userId, String userName, List<UserDTO> followers) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<UserDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserDTO> followers) {
        this.followers = followers;
    }
}
