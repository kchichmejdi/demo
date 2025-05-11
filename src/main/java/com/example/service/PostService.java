package com.example.service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Post;
import com.example.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post updatePost(Long id, Post postDetails) {
        Post post = getPostById(id);
        post.setTitle(postDetails.getTitle());
        post.setBody(postDetails.getBody());
        return postRepository.save(post);
    }

    public Post patchPost(Long id, Post postDetails) {
        Post post = getPostById(id);
        if (postDetails.getTitle() != null) {
            post.setTitle(postDetails.getTitle());
        }
        if (postDetails.getBody() != null) {
            post.setBody(postDetails.getBody());
        }
        return postRepository.save(post);
    }
}