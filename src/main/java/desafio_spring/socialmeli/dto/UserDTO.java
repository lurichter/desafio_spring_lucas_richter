package desafio_spring.socialmeli.dto;

public class UserDTO {

    private String userId;
    private String userName;
    private Boolean isSeller;

    public UserDTO() {
    }

    public UserDTO(String userId, String userName, Boolean isSeller) {
        this.userId = userId;
        this.userName = userName;
        this.isSeller = isSeller;
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

    public Boolean getSeller() {
        return isSeller;
    }

    public void setSeller(Boolean seller) {
        isSeller = seller;
    }
}
