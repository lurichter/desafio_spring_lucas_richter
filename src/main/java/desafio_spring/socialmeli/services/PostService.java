package desafio_spring.socialmeli.services;

import desafio_spring.socialmeli.dto.*;
import desafio_spring.socialmeli.repositories.PostRepository;
import desafio_spring.socialmeli.repositories.RelationshipRepository;
import desafio_spring.socialmeli.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private RelationshipRepository relationshipRepository;
    private UserRepository userRepository;

    public PostService(PostRepository postRepository, RelationshipRepository relationshipRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.relationshipRepository = relationshipRepository;
        this.userRepository = userRepository;
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
        newPost.setHasPromo(false);
        newPost.setDiscount(0.0);

        posts.add(newPost);
        this.postRepository.updateDatabase(posts);
        return newPost;
    }

    public PostDTO insertPromoPost(PromoPostRequestDTO post) {
        List<PostDTO> posts = this.postRepository.getDatabase();
        String newPostId = String.valueOf(posts.size() + 1);

        PostDTO newPost = new PostDTO();
        newPost.setUserId(post.getUserId());
        newPost.setPostId(newPostId);
        newPost.setPostDate(Calendar.getInstance());
        newPost.setDetail(post.getDetail());
        newPost.setCategory(post.getCategory());
        newPost.setPrice(post.getPrice());
        newPost.setHasPromo(post.isHasPromo());
        newPost.setDiscount(post.getDiscount());

        posts.add(newPost);
        this.postRepository.updateDatabase(posts);
        return newPost;
    }

    public UserPostsDTO getUserFollowedRecentPosts(String userId, String order) {
        UserDTO user = this.userRepository.getUserById(userId);
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

        if (order != null) {
            if (order.equals("date_asc")) {
                postsResult.sort(Comparator.comparing(PostDTO::getPostDate));
            } else if (order.equals("date_desc")) {
                postsResult.sort(Comparator.comparing(PostDTO::getPostDate).reversed());
            }
        }

        UserPostsDTO userPosts = new UserPostsDTO();
        userPosts.setUserId(user.getUserId());
        userPosts.setUserName(user.getUserName());
        userPosts.setPosts(postsResult);

        return userPosts;
    }

    public PromoPostCountDTO getUserPromoPostsCount(String userId) {
        UserDTO user = this.userRepository.getUserById(userId);
        List<PostDTO> posts = this.postRepository.getPostsByUserId(userId);
        List<PostDTO> promoPosts = posts.stream().filter(PostDTO -> PostDTO.isHasPromo()).collect(Collectors.toList());

        PromoPostCountDTO promoPostCount = new PromoPostCountDTO();
        promoPostCount.setUserId(user.getUserId());
        promoPostCount.setUserName(user.getUserName());
        promoPostCount.setPromoProductsCount(promoPosts.size());

        return promoPostCount;
    }

    public UserPostsDTO getUserPromoPosts(String userId) {
        UserDTO user = this.userRepository.getUserById(userId);
        List<PostDTO> posts = this.postRepository.getPostsByUserId(userId);
        List<PostDTO> promoPosts = posts.stream().filter(PostDTO -> PostDTO.isHasPromo()).collect(Collectors.toList());

        UserPostsDTO userPromoPosts = new UserPostsDTO();
        userPromoPosts.setUserId(user.getUserId());
        userPromoPosts.setUserName(user.getUserName());
        userPromoPosts.setPosts(promoPosts);

        return userPromoPosts;
    }

    public boolean validDiscount(PromoPostRequestDTO post) {
        boolean promo = post.isHasPromo();
        double discount = post.getDiscount();

        if (discount < 0.0 && discount > 100.0) {
            return false;
        }
        else if (discount > 0.0 && !promo) {
            return false;
        }
        else {
            return true;
        }
    }

}
