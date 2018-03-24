package com.beerhouse.repositories;

import com.beerhouse.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jordano on 24/03/2018.
 */
@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {

}
