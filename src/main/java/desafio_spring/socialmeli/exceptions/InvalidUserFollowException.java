package desafio_spring.socialmeli.exceptions;

public class InvalidUserFollowException extends Exception{

    public InvalidUserFollowException(String errorMessage) {
        super(errorMessage);
    }
}
