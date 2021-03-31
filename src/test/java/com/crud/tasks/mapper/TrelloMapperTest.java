package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class TrelloMapperTest {

    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToTrelloBoard() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "name1", true);
        List<TrelloListDto> trelloListDtos = Arrays.asList(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("testBoard", "1", trelloListDtos);
        List<TrelloBoardDto> boardDto = Arrays.asList(trelloBoardDto);

        //When
        List<TrelloBoard> boards = trelloMapper.mapToBoards(boardDto);
        //Then
        final TrelloBoardDto resulttrelloBoardDto = boardDto.get(0);
        final TrelloBoard boardResult = boards.get(0);
        final TrelloListDto firstElementTrelloListDto = resulttrelloBoardDto.getLists().get(0);
        final TrelloList boardResultList = boards.get(0).getLists().get(0);

        Assert.assertEquals(resulttrelloBoardDto.getId(), boardResult.getId());
        Assert.assertEquals(resulttrelloBoardDto.getName(), boardResult.getName());
        Assert.assertEquals(firstElementTrelloListDto.getId(), boardResultList.getId());
        Assert.assertEquals(firstElementTrelloListDto.getName(), boardResultList.getName());
        Assert.assertEquals(firstElementTrelloListDto.isClosed(), boardResultList.isClosed());
    }

    @Test
    public void mapToTrelloBoardDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "name1", true);
        List<TrelloList> lists = Arrays.asList(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("testBoard", "1", lists);
        List<TrelloBoard> board = Arrays.asList(trelloBoard);

        //When
        List<TrelloBoardDto> mappedList = trelloMapper.mapToBoardsDto(board);
        //Then
        final TrelloBoard resultBoard = board.get(0);
        final TrelloBoardDto trelloBoardDto = mappedList.get(0);
        final TrelloList trelloResultList = board.get(0).getLists().get(0);
        final TrelloListDto trelloListDto = mappedList.get(0).getLists().get(0);

        Assert.assertEquals(resultBoard.getId(), trelloBoardDto.getId());
        Assert.assertEquals(resultBoard.getName(), trelloBoardDto.getName());
        Assert.assertEquals(trelloResultList.getId(), trelloListDto.getId());
        Assert.assertEquals(trelloResultList.getName(), trelloListDto.getName());
        Assert.assertEquals(trelloResultList.isClosed(), trelloListDto.isClosed());
    }

    @Test
    public void mapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "name1", true);
        List<TrelloListDto> trelloListDtos = Arrays.asList(trelloListDto);
        //When
        List<TrelloList> mapList = trelloMapper.mapToList(trelloListDtos);
        //Then
        final TrelloList trelloList = mapList.get(0);
        final TrelloListDto trelloListResult = trelloListDtos.get(0);
        Assert.assertEquals(trelloList.getId(), trelloListResult.getId());
        Assert.assertEquals(trelloList.getName(), trelloListResult.getName());
        Assert.assertEquals(trelloList.isClosed(), trelloListResult.isClosed());
    }

    @Test
    public void mapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "name1", true);
        List<TrelloList> trelloLists = Arrays.asList(trelloList);
        //When
        List<TrelloListDto> mapListDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        final TrelloList trelloListResult = trelloLists.get(0);
        final TrelloListDto trelloListDto = mapListDto.get(0);
        Assert.assertEquals(trelloListResult.getId(), trelloListDto.getId());
        Assert.assertEquals(trelloListResult.getName(), trelloListDto.getName());
        Assert.assertEquals(trelloListResult.isClosed(), trelloListDto.isClosed());

    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card1", "des1", "position1", "1");

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
        TrelloCard trelloCard = new TrelloCard("card1", "des1", "position1", "1");

        //When
        TrelloCardDto mappedCard = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals(trelloCard.getName(), mappedCard.getName());
        Assert.assertEquals(trelloCard.getDescription(), mappedCard.getDescription());
        Assert.assertEquals(trelloCard.getPos(), mappedCard.getPos());
        Assert.assertEquals(trelloCard.getListId(), mappedCard.getListId());
    }
}

