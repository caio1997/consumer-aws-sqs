package com.example.listener.controller;

import com.example.listener.entity.response.AddressResponse;
import com.example.listener.entity.response.PeopleResponse;
import com.example.listener.service.PeopleService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest
public class PeopleControllerTest {

    @MockBean
    private PeopleService peopleService;

    @Autowired
    private PeopleController peopleController;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(peopleController);
    }

    @Test
    public void retornaTodasAsPessoas() {

        List<PeopleResponse> peoples = new ArrayList<>();

        when(peopleService.getAll())
                .thenReturn(peoples);

        RestAssuredMockMvc
                .given()
                    .accept(MediaType.APPLICATION_JSON)
                .when()
                    .get("/people/find-all")
                .then()
                    .status(HttpStatus.OK);
    }

    @Test
    public void retornaPeoplePeloId() throws Exception {

        String id = "1";

        PeopleResponse peopleResponse = new PeopleResponse("TEST", "TESTE", 18,
                new AddressResponse("TESTE", "TESTE", 1),
                new ArrayList<>());

        when(peopleService.getById(id))
                .thenReturn(peopleResponse);

        RestAssuredMockMvc
                .given()
                    .accept(MediaType.APPLICATION_JSON)
                .when()
                    .get("/people/{id}", id)
                .then()
                    .status(HttpStatus.OK);

    }

    @Test
    public void naoRetornaPeoplePeloId() throws Exception {

        String id = "2";

        when(peopleService.getById(id))
                .thenThrow(Exception.class);

        RestAssuredMockMvc
                .given()
                    .accept(MediaType.APPLICATION_JSON)
                .when()
                    .get("/people/{id}", id)
                .then()
                    .status(HttpStatus.BAD_REQUEST);

    }

    @Test
    public void retornaPeoplePeloNome() throws Exception {

        String name = "TESTE";

        when(peopleService.getByName(name))
                .thenReturn(new ArrayList());

        RestAssuredMockMvc
                .given()
                    .accept(MediaType.APPLICATION_JSON)
                .when()
                    .get("/people/find-by-name?name={name}", name)
                .then()
                    .status(HttpStatus.OK);

    }

    @Test
    public void removePeople() throws Exception {

        String id = "1";

        doNothing()
                .when(peopleService).deleteById(id);

        RestAssuredMockMvc
                .given()
                .accept(MediaType.APPLICATION_JSON)
                .when()
                .delete("/people/{id}", id)
                .then()
                .status(HttpStatus.OK);

    }

    @Test
    public void naoRetornaPeoplePeloNome() throws Exception {

        String id = "1";

        doNothing()
                .doThrow(Exception.class);

        RestAssuredMockMvc
                .given()
                .accept(MediaType.APPLICATION_JSON)
                .when()
                .delete("/people/{id}", id)
                .then()
                .status(HttpStatus.BAD_REQUEST);

    }

}
