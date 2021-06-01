package desafio_spring.socialmeli.dto;

public class UserFollowersCountDTO {

    private String userId;
    private String userName;
    private Integer followersCount;

    public UserFollowersCountDTO() {
    }

    public UserFollowersCountDTO(String userId, String userName, Integer followersCount) {
        this.userId = userId;
        this.userName = userName;
        this.followersCount = followersCount;
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

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }
}
