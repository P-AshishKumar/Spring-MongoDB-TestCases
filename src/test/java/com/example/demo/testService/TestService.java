package com.example.demo.testService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.demo.Repository.MyHeroAcademiaDAO;
import com.example.demo.Servcie.MyHeroAcademiaServcie;
import com.example.demo.model.MyHeroAcademia;

@SpringBootTest
public class TestService {

	@Autowired
	MyHeroAcademiaServcie service;

	@MockBean
	MyHeroAcademiaDAO DAO;

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

		when(DAO.getAllHero()).thenReturn(list);
		assertEquals(list, service.getAllHero());
	}

	@Test
	public void testAddhero() {
		MyHeroAcademia hero = new MyHeroAcademia();
		hero.setId(1);
		hero.setName("Midoriya");
		hero.setQuirk("Super Strength");

		when(DAO.addHero(hero)).thenReturn(hero);
		assertEquals(hero, service.addHero(hero));
	}

	@Test
	public void testGetHeroById() throws NotFoundException {
		MyHeroAcademia hero = new MyHeroAcademia();
		hero.setId(1);
		hero.setName("Midoriya");
		hero.setQuirk("Super Strength");

		when(DAO.getHeroById(1L)).thenReturn(hero);
		assertEquals(hero, service.getHeroById(1L));
	}

	@Test
	public void testUpdateHeroById() throws NotFoundException {
		MyHeroAcademia hero = new MyHeroAcademia();
		hero.setId(1);
		hero.setName("Midoriya");
		hero.setQuirk("Super Strength");

		MyHeroAcademia heroDetails = new MyHeroAcademia();
		heroDetails.setName("Izuku Midoriya");
		heroDetails.setQuirk("Strength");

		hero.setName(heroDetails.getName());
		hero.setQuirk(heroDetails.getQuirk());

		when(DAO.updateHeroById(1L, heroDetails)).thenReturn(hero);
		assertEquals(hero, service.updateHeroById(1L, heroDetails));
	}

	@Test
	public void testDeleteHeroById() throws NotFoundException {
		MyHeroAcademia hero = new MyHeroAcademia();
		hero.setId(1);
		hero.setName("Midoriya");
		hero.setQuirk("Super Strength");

		when(DAO.deleteHeroById(1L)).thenReturn("Hero deleted");
		assertEquals("Hero deleted", service.deleteHeroById(1L));
	}

}
