package com.coding.exersise.bookservice;

import com.coding.exersise.bookservice.controller.BooksController;
import com.coding.exersise.bookservice.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookServiceApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BooksController booksController;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void accessPort() {
        assertThat(port).isNotNull();
        assertThat(port).isPositive();
    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(booksController).isNotNull();
    }


    @Test
    void whenPostInputIsInvalid_thenReturnsStatus400() throws Exception {
        Book input =new Book();
        String body = objectMapper.writeValueAsString(input);

        this.mockMvc.perform(post("/api/v1/books")
                .contentType("application/json")
                .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetBookForISBN() throws Exception {
        this.mockMvc.perform(get("/api/v1/books?isbn=isbn1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"isbn\":\"isbn1\",\"title\":\"title 1\",\"author\":\"author\",\"datePublished\":\"1999-01-20\",\"rating\":20}"));
    }

    @Test
    public void testGetBooksAll() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/books")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"isbn\":\"isbn1\",\"title\":\"title 1\",\"author\":\"author\",\"datePublished\":\"1999-01-20\",\"rating\":20},{\"isbn\":"+
                        "\"isbn2\",\"title\":\"title 2\",\"author\":\"author\",\"datePublished\":\"2000-05-20\",\"rating\":30}]"));

    }


    @Test
    public void testPostBookSuccess() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/books")
               .content(objectMapper
                       .writeValueAsString(new Book("isbn5", "title 5",
                               "author", LocalDate.of(1999, JANUARY,
                               20), 20)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").exists());
    }

    @Test
    public void testPostBooksISBNalreadyExist() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/books")
                .content(objectMapper
                        .writeValueAsString(new Book("isbn1", "title 5",
                                "author", LocalDate.of(1999, JANUARY,
                                20), 20)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAlreadyReported());
    }

    @Test
    public void testPostBooksBookAlreadyExist() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("this.mockMvc")
                .content(objectMapper
                        .writeValueAsString(new Book("isbn7", "title 1",
                                "author", LocalDate.of(1999, JANUARY, 20), 20)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAlreadyReported());
    }



}