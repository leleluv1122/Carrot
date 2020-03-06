package net.lele.service;

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
		s.setName(search.getName());
		searchRepository.save(s);
	}
}
