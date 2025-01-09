package telran.java55.post.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java55.post.dto.NewPostDto;
import telran.java55.post.dto.PostDto;
import telran.java55.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forum")
public class PostController {
	
	final PostService postService;

	@GetMapping("/author/{author}")
	public PostDto findPostsByAuthor(@PathVariable String author) {
	    return postService.findPostByAuthor(author);
	}
	
	@GetMapping("/post/{id}")
	public PostDto findPostById(@PathVariable String id) {
		return postService.findPostById(id);
	}
	
	@DeleteMapping("/post/{id}")
	public PostDto removePost(@PathVariable String id) {
		return postService.removePost(id);
	}
	
	@PutMapping("/post/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto newPostDto) {
		return postService.updatePost(id, newPostDto);
	}

	
	@PutMapping("/{id}/comment")
	public PostDto addComment(@PathVariable String id, @RequestParam String user, @RequestParam String message) {
		return postService.addComment(id, user, message);
	}
	
	@GetMapping("/tags")
	public List<PostDto> findPostsByTags(@RequestParam("tags") String tags) {
	    Set<String> tagSet = Set.of(tags.split(","));
	    return postService.findPostByTags(tagSet);
	}
	
	@GetMapping("/period")
	public List<PostDto> findPostsByPeriod(
	        @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
	        @RequestParam @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
	    return postService.findPostByPeriod(start, end);
	}

	
	@PutMapping("/post/{id}/like")
	public PostDto addLike(@PathVariable String id) {
	    return postService.addLike(id);
	}

}
