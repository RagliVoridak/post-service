package telran.java55.post.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import telran.java55.post.dto.NewPostDto;
import telran.java55.post.dto.PostDto;

public interface PostService {
	PostDto addNewPost(String author, NewPostDto newPostDto);

	PostDto findPostById(String id);

	PostDto removePost(String id);

	PostDto updatePost(String id, NewPostDto newPostDto);

	PostDto findPostByAuthor(String id);

	PostDto addComment(String id, String user, String message);

	List<PostDto> findPostByTags(Set<String> tags);

	List<PostDto> findPostByPeriod(LocalDateTime start, LocalDateTime end);

	PostDto addLike(String id);
}
