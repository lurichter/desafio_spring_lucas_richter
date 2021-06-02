package desafio_spring.socialmeli.dto;

public class ErrorDTO {

    private String description;

    public ErrorDTO() {
    }

    public ErrorDTO(String errorName) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
