package com.example.demo.entity;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



public interface coinRepository extends JpaRepository<Todo, String> {

	Todo save(Todo todo);
	
	@Modifying
	@Query(value = "select * from Todo",nativeQuery = true)
	List<Todo> findByAll();
	
	
	@Transactional
	@Modifying
	@Query(value = "update Todo set rate = ?1 where currency = ?2",nativeQuery = true)
	int updateRate(Double rate , String currency);
	
	@Transactional
	@Modifying
	@Query(value = "delete from Todo where currency = ?1",nativeQuery = true)
	int deleteCurrency(String currency);
	
	
	
}
