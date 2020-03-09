package net.lele.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.lele.domain.Search;
import net.lele.repository.SearchRepository;

@Service
public class SearchService {
	@Autowired
	SearchRepository searchRepository;

	public void save(Search search) {
		Search s = new Search();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		s.setName(search.getName());
		s.setSearchdate(timestamp);
		
		searchRepository.save(s);
	}
}
