package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.MyHeroAcademiaRepository;
import com.example.demo.Servcie.MyHeroAcademiaServcie;
import com.example.demo.model.MessageResponse;
import com.example.demo.model.MyHeroAcademia;

@RestController
@RequestMapping("/")
public class MyHeroAcademiaController {

	@Autowired
	MyHeroAcademiaServcie heroServcie;

	@Autowired
	MyHeroAcademiaRepository repo;

	@GetMapping("/getall")
	public List<MyHeroAcademia> getAllHero() {
		return heroServcie.getAllHero();
	}

	@PostMapping("/add")
	public ResponseEntity<?> addHero(@RequestBody MyHeroAcademia hero) {
		if (repo.existsByName(hero.getName())) {
			return ResponseEntity.status(409).body(new MessageResponse("Hero Name Already Exist"));
		}
		return ResponseEntity.ok().body(heroServcie.addHero(hero));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") long heroId) throws NotFoundException {
		try {
			return ResponseEntity.ok().body(heroServcie.getHeroById(heroId));
		} catch (NotFoundException e) {
			return ResponseEntity.status(404).body(new MessageResponse("Id does not Exist"));
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") long heroId, @RequestBody MyHeroAcademia heroDetails)
			throws NotFoundException {

		try {
			MyHeroAcademia hero = heroServcie.updateHeroById(heroId, heroDetails);
			return ResponseEntity.ok().body(hero);
		} catch (NotFoundException e) {
			return ResponseEntity.status(404).body(new MessageResponse("Id does not Exist"));
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") long heroId) throws NotFoundException {
		try {
			return ResponseEntity.ok().body(heroServcie.deleteHeroById(heroId));
		} catch (NotFoundException e) {
			return ResponseEntity.status(404).body(new MessageResponse("Id does not Exist"));
		}
	}
}
