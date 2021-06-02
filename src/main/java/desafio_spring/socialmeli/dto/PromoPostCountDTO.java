package desafio_spring.socialmeli.dto;

public class PromoPostCountDTO {

    private String userId;
    private String userName;
    private Integer promoProductsCount;

    public PromoPostCountDTO() {
    }

    public PromoPostCountDTO(String userId, String userName, Integer promoProductsCounts) {
        this.userId = userId;
        this.userName = userName;
        this.promoProductsCount = promoProductsCounts;
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

    public Integer getPromoProductsCount() {
        return promoProductsCount;
    }

    public void setPromoProductsCount(Integer promoProductsCount) {
        this.promoProductsCount = promoProductsCount;
    }
}
