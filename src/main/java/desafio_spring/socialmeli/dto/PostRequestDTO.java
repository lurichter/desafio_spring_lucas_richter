package desafio_spring.socialmeli.dto;

import java.util.Calendar;
import java.util.List;

public class PostRequestDTO {

    private String userId;
    private List<ProductDTO> detail;
    private String category;
    private double price;

    public PostRequestDTO() {
    }

    public PostRequestDTO(String userId, List<ProductDTO> detail, String category, double price) {
        this.userId = userId;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
