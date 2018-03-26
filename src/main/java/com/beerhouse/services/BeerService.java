package com.beerhouse.services;

import com.beerhouse.domain.Beer;
import com.beerhouse.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by jordano on 24/03/2018.
 */
@Service
public class BeerService {

    @Autowired
    private BeerRepository beerRepository;

    public List<Beer> findAll() {
        return beerRepository.findAll();
    }

    public Optional<Beer> findOne(Integer id) {
        return Optional.ofNullable(beerRepository.findOne(id));
    }

    public void create(Beer beer) {
        beerRepository.save(beer);
    }

    public void update(Beer beer) {
        this.findOne(beer.getId()).orElseThrow(EntityNotFoundException::new);
        beerRepository.save(beer);
    }

    public void patch(Beer beer) {
        Beer newBeer = this.findOne(beer.getId()).orElseThrow(EntityNotFoundException::new);

        if (validateNewObject(beer.getName(), newBeer.getName()))
            newBeer.setName(beer.getName());

        if (validateNewObject(beer.getIngredients(), newBeer.getIngredients()))
            newBeer.setIngredients(beer.getIngredients());

        if (validateNewObject(beer.getAlcoholContent(), newBeer.getAlcoholContent()))
            newBeer.setAlcoholContent(beer.getAlcoholContent());

        if (validateNewObject(beer.getPrice(), newBeer.getPrice()))
            newBeer.setPrice(beer.getPrice());

        if (validateNewObject(beer.getCategory(), newBeer.getCategory()))
            newBeer.setCategory(beer.getCategory());

        beerRepository.save(newBeer);

    }

    public void delete(Integer id) {
        final Beer beer = this.findOne(id).orElseThrow(EntityNotFoundException::new);
        beerRepository.delete(beer);
    }

    private Boolean validateNewObject(Object object, Object newObject) {
        return Objects.nonNull(object) && !object.equals(newObject);
    }
}
