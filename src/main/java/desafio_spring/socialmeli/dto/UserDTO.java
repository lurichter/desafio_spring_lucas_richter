package desafio_spring.socialmeli.dto;

public class UserDTO {

    private String userId;
    private String userName;
    private boolean seller;

    public UserDTO() {
    }

    public UserDTO(String userId, String userName, boolean seller) {
        this.userId = userId;
        this.userName = userName;
        this.seller = seller;
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

    public boolean isSeller() {
        return seller;
    }

    public void setSeller(boolean seller) {
        this.seller = seller;
    }
}
