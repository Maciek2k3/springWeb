package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {
    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void mapToTrelloBoard(){
        //Given
        TrelloListDto trelloListDto=new TrelloListDto("1","name1",true);
        List<TrelloListDto> trelloListDtos= Arrays.asList(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("testBoard", "1", trelloListDtos);
        List<TrelloBoardDto> boardDto = Arrays.asList(trelloBoardDto);
        //When
        List<TrelloBoard> boards=trelloMapper.mapToBoards(boardDto);
        //Then
        Assert.assertEquals(boardDto.get(0).getId(), boards.get(0).getId());
        Assert.assertEquals(boardDto.get(0).getName(), boards.get(0).getName());
        Assert.assertEquals(boardDto.get(0).getLists().get(0).getId(),
                boards.get(0).getLists().get(0).getId());
        Assert.assertEquals(boardDto.get(0).getLists().get(0).getName(),
                boards.get(0).getLists().get(0).getName());
        Assert.assertEquals(boardDto.get(0).getLists().get(0).isClosed(),
                boards.get(0).getLists().get(0).isClosed());
    }
    @Test
    public void mapToTrelloBoardDto(){
        //Given
        TrelloList trelloList=new TrelloList("1","name1",true);
        List<TrelloList> lists= Arrays.asList(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("testBoard", "1", lists);
        List<TrelloBoard> board = Arrays.asList(trelloBoard);

        //When
        List<TrelloBoardDto> mappedList=trelloMapper.mapToBoardsDto(board);
        //Then
        Assert.assertEquals(board.get(0).getId(), mappedList.get(0).getId());
        Assert.assertEquals(board.get(0).getName(), mappedList.get(0).getName());
        Assert.assertEquals(board.get(0).getLists().get(0).getId(),
                mappedList.get(0).getLists().get(0).getId());
        Assert.assertEquals(board.get(0).getLists().get(0).getName(),
                mappedList.get(0).getLists().get(0).getName());
        Assert.assertEquals(board.get(0).getLists().get(0).isClosed(),
                mappedList.get(0).getLists().get(0).isClosed());
    }
    @Test
    public void mapToList(){
        //Given
        TrelloListDto trelloListDto=new TrelloListDto("1","name1",true);
        List<TrelloListDto> trelloListDtos=Arrays.asList(trelloListDto);
        //When
        List<TrelloList> mapList=trelloMapper.mapToList(trelloListDtos);
        //Then
        Assert.assertEquals(mapList.get(0).getId(), trelloListDtos.get(0).getId());
        Assert.assertEquals(mapList.get(0).getName(), trelloListDtos.get(0).getName());
        Assert.assertEquals(mapList.get(0).isClosed(), trelloListDtos.get(0).isClosed());
    }
    @Test
    public void mapToListDto(){
        //Given
        TrelloList trelloList=new TrelloList("1","name1",true);
        List<TrelloList> trelloLists=Arrays.asList(trelloList);
        //When
        List<TrelloListDto> mapListDto=trelloMapper.mapToListDto(trelloLists);
        //Then
        Assert.assertEquals(trelloLists.get(0).getId(), mapListDto.get(0).getId());
        Assert.assertEquals(trelloLists.get(0).getName(), mapListDto.get(0).getName());
        Assert.assertEquals(trelloLists.get(0).isClosed(), mapListDto.get(0).isClosed());

    }
    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card1","des1","position1","1");

        //When
        TrelloCard mappCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals(trelloCardDto.getName(), mappCard.getName());
        Assert.assertEquals(trelloCardDto.getDescription(), mappCard.getDescription());
        Assert.assertEquals(trelloCardDto.getPos(), mappCard.getPos());
        Assert.assertEquals(trelloCardDto.getListId(), mappCard.getListId());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card1","des1","position1","1");

        //When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals(trelloCard.getName(), mappedCard.getName());
        Assert.assertEquals(trelloCard.getDescription(), mappedCard.getDescription());
        Assert.assertEquals(trelloCard.getPos(), mappedCard.getPos());
        Assert.assertEquals(trelloCard.getListId(), mappedCard.getListId());
    }
}

