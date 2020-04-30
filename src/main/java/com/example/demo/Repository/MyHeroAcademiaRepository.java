package com.example.demo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.MyHeroAcademia;

public interface MyHeroAcademiaRepository extends MongoRepository<MyHeroAcademia, Long> {

	// to find if Hero name already exist
	Boolean existsByName(String Name);
}
