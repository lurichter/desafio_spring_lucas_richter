package desafio_spring.socialmeli.dto;

import java.util.List;

public class UserPostsDTO {

    private String userId;
    private List<PostDTO> posts;

    public UserPostsDTO() {
    }

    public UserPostsDTO(String userId, List<PostDTO> posts) {
        this.userId = userId;
        this.posts = posts;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}
