package com.beerhouse.resources;

import com.beerhouse.Exception.IdConflictException;
import com.beerhouse.domain.Beer;
import com.beerhouse.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;

/**
 * Created by jordano on 24/03/2018.
 */
@RestController
@RequestMapping(value = "/beers")
public class BeerResource {

    @Autowired
    private BeerService beerService;

    final static String DEFAULT_PAGE = "0";
    final static String DEFAULT_LINES = "10";
    final static String DEFAULT_ORDERBY = "id";
    final static String DEFAULT_DIRECTION = "ASC";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Beer>> findAll(@RequestParam(value = "page", defaultValue = DEFAULT_PAGE) Integer page,
                                              @RequestParam(value = "lines", defaultValue = DEFAULT_LINES) Integer lines,
                                              @RequestParam(value = "orderBy", defaultValue = DEFAULT_ORDERBY) String orderBy,
                                              @RequestParam(value = "direction", defaultValue = DEFAULT_DIRECTION) String direction) {
        return ResponseEntity.ok().body(beerService.findAll(page, lines, orderBy, direction));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody @Valid Beer beer) {
        beerService.create(beer);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(beer.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findOne(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(beerService.findOne(id).orElseThrow(EntityNotFoundException::new));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") Integer id,
                                       @RequestBody @Valid Beer beer) {

        if (!Objects.equals(beer.getId(), id)) {
            throw new IdConflictException();
        }

        beerService.update(beer);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> patch(@PathVariable("id") Integer id,
                                      @RequestBody @Valid Beer beer) {
        if (!Objects.equals(beer.getId(), id)) {
            throw new IdConflictException();
        }
        beerService.patch(beer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        beerService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
