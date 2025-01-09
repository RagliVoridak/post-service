package telran.java55.post.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewPostDto {
	String title;
	String content;
	Set<String> tags;
}

//{
//	"title": "JavaEE",
//	"content": "Java is the best for backend",
//	"tags":["Java", "Spring", "backend", "JEE"]
//}