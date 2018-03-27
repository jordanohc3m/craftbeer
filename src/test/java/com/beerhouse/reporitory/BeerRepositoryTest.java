package com.beerhouse.reporitory;

import com.beerhouse.domain.Beer;
import com.beerhouse.services.BeerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by jordano on 24/03/2018.
 */

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BeerRepositoryTest {

    @Autowired
    private BeerService beerService;

    @Test
    public void whenFindAll() {

        Beer beer = new Beer();
        beer.setName("Bauru's Beer");
        beerService.create(beer);

        Beer beer2 = new Beer();
        beer2.setName("Campina's Beer");
        beerService.create(beer2);

        List<Beer> beerList = beerService.findAll();

        assertThat(beerList.size()).isEqualTo(2);
        assertThat(beerList.get(0)).isEqualTo(beer);
        assertThat(beerList.get(1)).isEqualTo(beer2);
    }

}
