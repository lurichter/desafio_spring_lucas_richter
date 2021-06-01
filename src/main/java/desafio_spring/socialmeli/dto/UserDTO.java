package desafio_spring.socialmeli.dto;

import java.util.List;

public class UserDTO {

    private String userID;
    private String userName;
    private Boolean isSeller;

    public UserDTO() {
    }

    public UserDTO(String userID, String userName, Boolean isSeller) {
        this.userID = userID;
        this.userName = userName;
        this.isSeller = isSeller;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
