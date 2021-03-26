package com.crud.tasks.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.facde.TrelloFacade;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {
    @InjectMocks
    TrelloFacade trelloFacade;

    @Mock
    private TrelloMapper trelloMapper;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Test
    public void shouldFetchList() {
        //Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(new TrelloListDto("1", "list1", true));

        List<TrelloBoardDto> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoardDto("1", "board1", trelloList));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "list1", true));

        List<TrelloBoard> mappedTrelloBoardList = new ArrayList<>();
        mappedTrelloBoardList.add(new TrelloBoard("1", "board1", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoard);
        when(trelloMapper.mapToBoards(trelloBoard)).thenReturn(mappedTrelloBoardList);
        when(trelloMapper.mapToBoards(anyList())).thenReturn(new ArrayList<>());
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void shouldFetchListDto() {
        //Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(new TrelloListDto("1", "list1", false));

        List<TrelloBoardDto> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoardDto("1", "board1", trelloList));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "list1", false));

        List<TrelloBoard> mappedTrelloBoardList = new ArrayList<>();
        mappedTrelloBoardList.add(new TrelloBoard("1", "board1", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoard);
        when(trelloMapper.mapToBoards(trelloBoard)).thenReturn(mappedTrelloBoardList);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoard);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoardList)).thenReturn(mappedTrelloBoardList);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());
    }

}
