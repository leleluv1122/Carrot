package net.lele.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lele.domain.Message;
import net.lele.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	MessageRepository messageRepository;
	
	public void save(Message m) {
		Message ms = new Message();
		
		ms.setReceive(m.getReceive());
		ms.setDetail(m.getDetail());
		ms.setSend(m.getSend());
		ms.setTitle(m.getTitle());
		
		messageRepository.save(ms);
	}
}
