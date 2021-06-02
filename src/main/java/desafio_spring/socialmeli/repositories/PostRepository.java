package desafio_spring.socialmeli.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import desafio_spring.socialmeli.dto.PostDTO;
import desafio_spring.socialmeli.dto.RelationshipDTO;
import desafio_spring.socialmeli.dto.UserDTO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepository {

    private List<PostDTO> database;

    public PostRepository() {
        this.database = loadDatabase();
    }

    public List<PostDTO> getDatabase() {
        return database;
    }

    public List<PostDTO> getPostsByUserId(String userId) {
        List<PostDTO> posts = loadDatabase();
        if (posts != null) {
            return posts.stream().filter(PostDTO -> PostDTO.getUserId().equals(userId)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<PostDTO> getPostsByListOfUserId(List<String> userIds) {
        List<PostDTO> posts = loadDatabase();
        if (posts != null) {
            return posts.stream().filter(e -> userIds.contains(e.getUserId())).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public void updateDatabase(List<PostDTO> database) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(ResourceUtils.getFile("classpath:posts.json"), database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<PostDTO> loadDatabase () {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:posts.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PostDTO>> typeRef = new TypeReference<>(){};
        List<PostDTO> posts = null;
        try {
            posts = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (posts != null) {
            return posts;
        } else {
            return new ArrayList<PostDTO>();
        }
    }


}
