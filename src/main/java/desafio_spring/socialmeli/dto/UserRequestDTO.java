package desafio_spring.socialmeli.dto;

public class UserRequestDTO {

    private String userName;
    private boolean isSeller;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String userName, boolean isSeller) {
        this.userName = userName;
        this.isSeller = isSeller;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean getSeller() {
        return isSeller;
    }

    public void setSeller(boolean seller) {
        this.isSeller = seller;
    }
}
