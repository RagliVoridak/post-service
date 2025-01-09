package telran.java55.post.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import telran.java55.post.model.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	List<Post> findByAuthor(String author);
    List<Post> findByTagsIn(Set<String> tags);
    List<Post> findByDateCreatedBetween(LocalDateTime start, LocalDateTime end);
	
}
