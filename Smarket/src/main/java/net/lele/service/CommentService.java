package net.lele.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lele.domain.Comment;
import net.lele.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	CommentRepository commentRepository;
	
	public List<Comment> findByProductId(int pid){
		return commentRepository.findByProductId(pid);
	}
	
	public void save(Comment c) {
		Comment co = new Comment();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		co.setContent(c.getContent());
		co.setProduct(c.getProduct());
		co.setUser(c.getUser());
		co.setWritedate(timestamp);
		
		commentRepository.save(co);
	}
	
	public void delete(int id) {
		commentRepository.deleteById(id);
	}
}
