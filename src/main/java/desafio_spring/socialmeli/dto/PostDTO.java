package desafio_spring.socialmeli.dto;

import java.util.Calendar;
import java.util.List;

public class PostDTO {

    private String userId;
    private String postId;
    private Calendar postDate;
    private List<ProductDTO> detail;
    private String category;
    private double price;
    private boolean hasPromo;
    private double discount;

    public PostDTO() {
    }

    public PostDTO(String userId, String postId, Calendar postDate, List<ProductDTO> detail, String category, double price, boolean hasPromo, double discount) {
        this.userId = userId;
        this.postId = postId;
        this.postDate = postDate;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public Calendar getPostDate() {
        return postDate;
    }

    public void setPostDate(Calendar postDate) {
        this.postDate = postDate;
    }

    public List<ProductDTO> getDetail() {
        return detail;
    }

    public void setDetail(List<ProductDTO> detail) {
        this.detail = detail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
