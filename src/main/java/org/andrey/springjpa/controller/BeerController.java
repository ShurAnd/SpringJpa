package org.andrey.springjpa.controller;

import java.net.URI;
import java.util.List;

import org.andrey.springjpa.domain.Beer;
import org.andrey.springjpa.exceptions.EntityNotFoundException;
import org.andrey.springjpa.persistence.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/beers")
public class BeerController {

	private BeerRepository beerRepo;
	
	@Autowired
	public BeerController(BeerRepository beerRepo) {
		this.beerRepo = beerRepo;
	}
	
	@GetMapping
	public ResponseEntity<List<Beer>> findAllBeer(){
		List<Beer> beers = beerRepo.findAll();
		
		return new ResponseEntity<>(beers, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Beer> findBeerById(@PathVariable("id") Long id){
		Beer beer = beerRepo.getOne(id);
		
		return new ResponseEntity<>(beer, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Beer> putBeer(@RequestBody Beer beer, @PathVariable("id") Long id){
		Beer oldBeer = beerRepo.getOne(id);
		if (oldBeer == null) throw new EntityNotFoundException();
		beer = beerRepo.save(beer);
		
		return new ResponseEntity<>(beer, HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Beer> patchBeer(@RequestBody Beer beer, @PathVariable("id") Long id){
		Beer oldBeer = beerRepo.getOne(id);
		if (oldBeer == null) throw new EntityNotFoundException();
		
		if (beer.getName() != null) oldBeer.setName(beer.getName());
		if (beer.getType() != null) oldBeer.setType(beer.getType());
		if (beer.getPrice() != null) oldBeer.setPrice(beer.getPrice());
		
		oldBeer = beerRepo.save(oldBeer);
		
		return new ResponseEntity<>(oldBeer, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Beer> deleteBeer(@PathVariable("id") Long id){
		beerRepo.deleteById(id);
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	public ResponseEntity<Beer> postNewBeer(@RequestBody Beer beer, UriComponentsBuilder ucb){
		beer = beerRepo.save(beer);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create(ucb.path("/beers").path("/"+beer.getId()).toString()));
		
		return new ResponseEntity<>(beer, headers, HttpStatus.CREATED);
	}
}
