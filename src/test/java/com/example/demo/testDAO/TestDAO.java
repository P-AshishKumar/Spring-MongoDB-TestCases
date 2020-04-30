package com.example.demo.testDAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.demo.Repository.MyHeroAcademiaDAO;
import com.example.demo.Repository.MyHeroAcademiaRepository;
import com.example.demo.model.MyHeroAcademia;

@SpringBootTest
public class TestDAO {

	@Autowired
	MyHeroAcademiaDAO DAO;

	@MockBean
	MyHeroAcademiaRepository repo;

	@Test
	public void testGetAllHero() {
		MyHeroAcademia hero1 = new MyHeroAcademia();
		hero1.setId(1);
		hero1.setName("Midoriya");
		hero1.setQuirk("Super Strength");

		MyHeroAcademia hero2 = new MyHeroAcademia();
		hero2.setId(1);
		hero2.setName("Midoriya");
		hero2.setQuirk("Super Strength");

		List<MyHeroAcademia> list = new ArrayList<>();
		list.add(hero1);
		list.add(hero2);

		when(repo.findAll()).thenReturn(list);
		assertEquals(list, DAO.getAllHero());
	}

	@Test
	public void testAddhero() {
		MyHeroAcademia hero = new MyHeroAcademia();
		hero.setId(1);
		hero.setName("Midoriya");
		hero.setQuirk("Super Strength");

		when(repo.insert(hero)).thenReturn(hero);
		assertEquals(hero, DAO.addHero(hero));
	}

	@Test
	public void testGetHeroById() throws NotFoundException {
		MyHeroAcademia hero = new MyHeroAcademia();
		hero.setId(1);
		hero.setName("Midoriya");
		hero.setQuirk("Super Strength");

		when(repo.findById(1L)).thenReturn(Optional.of(hero));
		assertEquals(hero, DAO.getHeroById(1L));

		// This is to test the exception
		Assertions.assertThrows(NotFoundException.class, () -> {
			DAO.getHeroById(2L);
		});
	}

	@Test
	public void testUpdateHeroById() throws NotFoundException {
		MyHeroAcademia hero = new MyHeroAcademia();
		hero.setId(1);
		hero.setName("Midoriya");
		hero.setQuirk("Super Strength");

		when(repo.findById(1L)).thenReturn(Optional.of(hero));
		MyHeroAcademia actualHero = DAO.getHeroById(1L);
		assertEquals(hero, actualHero);

		MyHeroAcademia heroDetails = new MyHeroAcademia();
		heroDetails.setName("Izuku Midoriya");
		heroDetails.setQuirk("Strength");

		actualHero.setName(heroDetails.getName());
		actualHero.setQuirk(heroDetails.getQuirk());

		when(repo.save(hero)).thenReturn(actualHero);
		assertEquals(actualHero, DAO.updateHeroById(1L, heroDetails));
	}

	@Test
	public void testDeleteHeroById() throws NotFoundException {
		MyHeroAcademia hero = new MyHeroAcademia();
		hero.setId(1);
		hero.setName("Midoriya");
		hero.setQuirk("Super Strength");

		when(repo.findById(1L)).thenReturn(Optional.of(hero));
		MyHeroAcademia actualHero = DAO.getHeroById(1L);
		assertEquals(hero, actualHero);

		DAO.deleteHeroById(1L);
		verify(repo, times(1)).deleteById(1L);
	}

}
