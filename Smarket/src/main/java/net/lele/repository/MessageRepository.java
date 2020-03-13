package net.lele.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.lele.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
	
}
