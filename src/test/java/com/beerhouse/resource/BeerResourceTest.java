package com.beerhouse.resource;

import com.beerhouse.domain.Beer;
import com.beerhouse.resources.BeerResource;
import com.beerhouse.services.BeerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

    @Test
    public void createStudentCourse() throws Exception {
        Beer beer = new Beer();
        beer.setId(1);
        beer.setName("Beer1");

        beerService.create(Mockito.any(Beer.class));
        Optional<Beer> newBeer = beerService.findOne(beer.getId());

        Mockito.when(newBeer).thenReturn(Optional.of(beer));

//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/students/Student1/courses")
//                .accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
//                .contentType(MediaType.APPLICATION_JSON);
//
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//
//        MockHttpServletResponse response = result.getResponse();
//
//        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//
//        assertEquals("http://localhost/students/Student1/courses/1",
//                response.getHeader(HttpHeaders.LOCATION));

    }

}
