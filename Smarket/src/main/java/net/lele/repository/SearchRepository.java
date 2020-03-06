package net.lele.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.lele.domain.Search;

public interface SearchRepository extends JpaRepository<Search, Integer> {

}
