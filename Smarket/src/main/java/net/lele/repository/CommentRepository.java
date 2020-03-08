package net.lele.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.lele.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	int countByProductId(int pid);
	
	List<Comment> findByProductId(int pid);
	
	void deleteById(int id);
}
