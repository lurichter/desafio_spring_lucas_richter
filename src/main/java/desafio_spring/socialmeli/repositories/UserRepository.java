package desafio_spring.socialmeli.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import desafio_spring.socialmeli.dto.RelationshipDTO;
import desafio_spring.socialmeli.dto.UserDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private List<UserDTO> database;

    public UserRepository() {
        this.database = loadDatabase();
    }

    public List<UserDTO> getDatabase() {
        return database;
    }

    public UserDTO getUserById(String userId) {
        List<UserDTO> users = loadDatabase();
        UserDTO user = null;
        if (users != null) {
            user = users.stream().filter(UserDTO -> UserDTO.getUserID().equals(userId)).findFirst().orElse(null);
        }
        return user;
    }

    public void updateDatabase(List<UserDTO> database) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(ResourceUtils.getFile("classpath:users.json"), database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<UserDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:users.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<UserDTO>> typeRef = new TypeReference<>(){};
        List<UserDTO> users = null;
        try {
            users = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (users != null) {
            return users;
        } else {
            return new ArrayList<UserDTO>();
        }

    }

}
