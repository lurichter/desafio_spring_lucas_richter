package desafio_spring.socialmeli.dto;

import java.util.List;

public class UserPostsDTO {

    private String userId;
    private String userName;
    private List<PostDTO> posts;

    public UserPostsDTO() {
    }

    public UserPostsDTO(String userId, String userName, List<PostDTO> posts) {
        this.userId = userId;
        this.userName = userName;
        this.posts = posts;
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

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}
