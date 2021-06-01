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
import java.util.stream.Collectors;

@Repository
public class RelationshipRepository {

    private List<RelationshipDTO> database;

    public RelationshipRepository() {
        this.database = loadDatabase();
    }

    public List<RelationshipDTO> getDatabase() {
        return database;
    }

    public List<RelationshipDTO> getRelationshipsByFollowerId (String userId) {
        List<RelationshipDTO> relationships = loadDatabase()
                                                .stream()
                                                .filter(RelationshipDTO -> RelationshipDTO.getFollowerId().equals(userId))
                                                .collect(Collectors.toList());

        return relationships;
    }

    public List<RelationshipDTO> getRelationshipsByFollowedId (String userId) {
        List<RelationshipDTO> relationships = loadDatabase()
                                                .stream()
                                                .filter(RelationshipDTO -> RelationshipDTO.getFollowedId().equals(userId))
                                                .collect(Collectors.toList());

        return relationships;
    }

    public void updateDatabase(List<RelationshipDTO> database) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(ResourceUtils.getFile("classpath:relationships.json"), database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<RelationshipDTO> loadDatabase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:relationships.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<RelationshipDTO>> typeRef = new TypeReference<>(){};
        List<RelationshipDTO> relationships = null;
        try {
            relationships = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (relationships != null) {
            return relationships;
        } else {
            return new ArrayList<RelationshipDTO>();
        }
    }

}
