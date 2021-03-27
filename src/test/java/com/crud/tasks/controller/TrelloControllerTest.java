package com.crud.tasks.controller;



import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.facde.TrelloFacade;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringJUnitWebConfig
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    public void shouldFetchEmptyTrelloBoards() throws Exception {

        // Given
        List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "Test list", false));
        List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "Test Task", trelloLists));
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/trello/getTrelloBoards")
                        .contentType(MediaType.APPLICATION_JSON))
                // Trello board fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is("1")))
                .andExpect(jsonPath("$[0].name", Matchers.is("Test Task")))
                // Trello list fields
                .andExpect(jsonPath("$[0].lists", hasSize(1)))
                .andExpect(jsonPath("$[0].lists[0].id", Matchers.is("1")))
                .andExpect(jsonPath("$[0].lists[0].name", Matchers.is("Test list")))
                .andExpect(jsonPath("$[0].lists[0].closed", Matchers.is(false)));
    }

    @Test
   public void shouldCreateTrelloCard() throws Exception {
        // Given
        TrelloCardDto trelloCardDto =
                new TrelloCardDto("Test", "Test description", "top", "1");

        CreatedTrelloCardDto createdTrelloCardDto =
                new CreatedTrelloCardDto("232", "Test", "http://test.com");

        when(trelloFacade.createCard(any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(trelloCardDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/trello/createTrelloCard")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("232")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shortUrl", Matchers.is("http://test.com")));
    }
}