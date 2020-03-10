package net.lele.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.lele.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	int countByProductId(int pid);
	
	List<Comment> findByProductId(int pid);
	
	void deleteById(int id);
	
	@Query("select new map(c.product.id as id, count(c.product.id) as cnt)"
			+ "from Comment c GROUP BY c.product")
	List<Map<Integer, Integer>> countByProductGroup();
}
