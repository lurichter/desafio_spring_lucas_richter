package desafio_spring.socialmeli.services;

import desafio_spring.socialmeli.dto.PostDTO;
import desafio_spring.socialmeli.dto.PostRequestDTO;
import desafio_spring.socialmeli.dto.RelationshipDTO;
import desafio_spring.socialmeli.dto.UserPostsDTO;
import desafio_spring.socialmeli.repositories.PostRepository;
import desafio_spring.socialmeli.repositories.RelationshipRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private RelationshipRepository relationshipRepository;

    public PostService(PostRepository postRepository, RelationshipRepository relationshipRepository) {
        this.postRepository = postRepository;
        this.relationshipRepository = relationshipRepository;
    }

    public PostDTO insertPost(PostRequestDTO post) {
        List<PostDTO> posts = this.postRepository.getDatabase();
        String newPostId = String.valueOf(posts.size() + 1);

        PostDTO newPost = new PostDTO();
        newPost.setUserId(post.getUserId());
        newPost.setPostId(newPostId);
        newPost.setPostDate(Calendar.getInstance());
        newPost.setDetail(post.getDetail());
        newPost.setCategory(post.getCategory());
        newPost.setPrice(post.getPrice());

        posts.add(newPost);
        this.postRepository.updateDatabase(posts);
        return newPost;
    }

    public UserPostsDTO getUserFollowedRecentPosts(String userId) {
        List<String> followeds = this.relationshipRepository.getRelationshipsByFollowerId(userId)
                                                            .stream()
                                                            .map(RelationshipDTO::getFollowedId)
                                                            .collect(Collectors.toList());
        List<PostDTO> posts = this.postRepository.getPostsByListOfUserId(followeds);

        Calendar filterDate = Calendar.getInstance();
        filterDate.add(Calendar.DAY_OF_MONTH, -14);

        List<PostDTO> postsResult = posts.stream()
                                        .filter(PostDTO -> PostDTO.getPostDate().getTime().after(filterDate.getTime()))
                                        .collect(Collectors.toList());
        postsResult.sort(Comparator.comparing(PostDTO::getPostDate).reversed());

        UserPostsDTO userPosts = new UserPostsDTO();
        userPosts.setUserId(userId);
        userPosts.setPosts(postsResult);

        return userPosts;
    }

}
