package desafio_spring.socialmeli.dto;

public class UserRequestDTO {

    private String userName;
    private Boolean isSeller;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String userName, Boolean isSeller) {
        this.userName = userName;
        this.isSeller = isSeller;
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
