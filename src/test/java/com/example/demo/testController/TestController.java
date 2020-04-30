package com.example.demo.testController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.example.demo.Controller.MyHeroAcademiaController;
import com.example.demo.Repository.MyHeroAcademiaRepository;
import com.example.demo.Servcie.MyHeroAcademiaServcie;
import com.example.demo.model.MyHeroAcademia;

@SpringBootTest
public class TestController {

	@Autowired
	MyHeroAcademiaController controller;

	@MockBean
	MyHeroAcademiaServcie service;

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

		when(service.getAllHero()).thenReturn(list);
		assertEquals(list, controller.getAllHero());
	}

	@Test
	public void testAddHero() {
		MyHeroAcademia hero = new MyHeroAcademia();
		hero.setId(1);
		hero.setName("Midoriya");
		hero.setQuirk("Super Strength");

		when(service.addHero(hero)).thenReturn(hero);
		assertEquals(hero, controller.addHero(hero).getBody());
	}

	@Test
	public void testAddHeroWithExistingName() {
		MyHeroAcademia hero = new MyHeroAcademia();
		hero.setId(1);
		hero.setName("Midoriya");
		hero.setQuirk("Super Strength");

		// existByName is created in MyHeroAcademiaRepository & 409 is http status code for duplicate value
		when(repo.existsByName(hero.getName())).thenReturn(true);
		assertEquals(409, controller.addHero(hero).getStatusCodeValue());
	}

	@Test
	public void testGetHeroById() throws NotFoundException {
		MyHeroAcademia hero = new MyHeroAcademia();
		hero.setId(1);
		hero.setName("Midoriya");
		hero.setQuirk("Super Strength");

		when(service.getHeroById(1)).thenReturn(hero);
		assertEquals(hero, controller.getById(1).getBody());

		when(service.getHeroById(2)).thenThrow(NotFoundException.class);
		assertEquals(404, controller.getById(2).getStatusCodeValue());
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

		when(service.updateHeroById(1, heroDetails)).thenReturn(hero);
		assertEquals(hero, controller.updateById(1, heroDetails).getBody());

		when(service.updateHeroById(2, heroDetails)).thenThrow(NotFoundException.class);
		assertEquals(404, controller.updateById(2, heroDetails).getStatusCodeValue());
	}

	@Test
	public void testDeleteHeroById() throws NotFoundException {
		MyHeroAcademia hero = new MyHeroAcademia();
		hero.setId(1);
		hero.setName("Midoriya");
		hero.setQuirk("Super Strength");

		when(service.deleteHeroById(1)).thenReturn("Hero Deleted");
		assertEquals("Hero Deleted", controller.deleteById(1).getBody());

		when(service.deleteHeroById(2)).thenThrow(NotFoundException.class);
		assertEquals(404, controller.deleteById(2).getStatusCodeValue());
	}
}
