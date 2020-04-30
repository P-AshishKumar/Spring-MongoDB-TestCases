package com.example.demo.Servcie;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.MyHeroAcademiaDAO;
import com.example.demo.model.MyHeroAcademia;

@Service
public class MyHeroAcademiaServcie {

	@Autowired
	MyHeroAcademiaDAO heroDAO;

	public MyHeroAcademia addHero(MyHeroAcademia hero) {
		return heroDAO.addHero(hero);
	}

	public List<MyHeroAcademia> getAllHero() {
		return heroDAO.getAllHero();
	}

	public MyHeroAcademia getHeroById(long heroId) throws NotFoundException {
		return heroDAO.getHeroById(heroId);
	}

	public MyHeroAcademia updateHeroById(long heroId, MyHeroAcademia heroDetails) throws NotFoundException {
		return heroDAO.updateHeroById(heroId, heroDetails);

	}

	public String deleteHeroById(long heroId) throws NotFoundException {
		return heroDAO.deleteHeroById(heroId);
	}

}
