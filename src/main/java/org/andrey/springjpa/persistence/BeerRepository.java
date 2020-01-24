package org.andrey.springjpa.persistence;

import org.andrey.springjpa.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long>{
	Beer findBeerByName(String name);
}
