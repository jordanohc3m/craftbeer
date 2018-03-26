package com.beerhouse.resources.tests;

import com.beerhouse.domain.Beer;
import com.beerhouse.resources.BeerResource;
import com.beerhouse.services.BeerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by jordano on 26/03/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = BeerResource.class, secure = false)
public class BeerResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BeerService beerService;


    @Test
    public void getBeerId() throws Exception {
        Beer beer = new Beer();
        beer.setId(1);
        beer.setName("Beer1");

        given(beerService.findOne(beer.getId())).willReturn(Optional.of(beer));

        mvc.perform(get("/beers/" + beer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(beer.getName())));
    }

}
