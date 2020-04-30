package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.model.MyHeroAcademia;

@Component
public class MyHeroAcademiaDAO {

	@Autowired
	MyHeroAcademiaRepository repo;

	public MyHeroAcademia addHero(MyHeroAcademia hero) {
		return repo.insert(hero);
	}

	public List<MyHeroAcademia> getAllHero() {
		return repo.findAll();
	}

	public MyHeroAcademia getHeroById(long heroId) throws NotFoundException{
		return repo.findById(heroId).orElseThrow(NotFoundException::new);
	}

	public MyHeroAcademia updateHeroById(long heroId, MyHeroAcademia heroDetails) throws NotFoundException {
		MyHeroAcademia hero = repo.findById(heroId).orElseThrow(NotFoundException::new);
		if (heroDetails.getName() != null)
			hero.setName(heroDetails.getName());

		if (heroDetails.getQuirk() != null)
			hero.setQuirk(heroDetails.getQuirk());
		return repo.save(hero);

	}

	public String deleteHeroById(long heroId) throws NotFoundException {
		repo.findById(heroId).orElseThrow(NotFoundException::new);
		repo.deleteById(heroId);
		return "Hero deleted";
	}

}
