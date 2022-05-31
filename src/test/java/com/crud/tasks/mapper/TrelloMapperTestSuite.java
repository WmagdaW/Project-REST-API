package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoardsTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "List 1", false);
        List<TrelloListDto> list1 = new ArrayList<>();
        list1.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("Board 1", "1", list1);
        List<TrelloBoardDto> boardList = new ArrayList<>();
        boardList.add(trelloBoardDto);

        //When
        List<TrelloBoard> testList = trelloMapper.mapToBoards(boardList);

        //Then
        Assertions.assertEquals(boardList.get(0).getName(), testList.get(0).getName());
    }
    @Test
    public void mapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "List 1", false);
        List<TrelloList> list1 = new ArrayList<>();
        list1.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("1", "Board 1", list1);
        List<TrelloBoard> boardList = new ArrayList<>();
        boardList.add(trelloBoard);

        //When
        List<TrelloBoardDto> testList = trelloMapper.mapToBoardsDto(boardList);

        //Then
        Assertions.assertEquals(boardList.get(0).getName(), testList.get(0).getName());
    }
    @Test
    public void mapToListTest() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "List 1", false);
        List<TrelloListDto> list1 = new ArrayList<>();
        list1.add(trelloListDto);

        //When
        List<TrelloList> testList = trelloMapper.mapToList(list1);

        //Then
        Assertions.assertEquals(list1.get(0).getName(), testList.get(0).getName());
    }
    @Test
    public void mapToListDtoTest() {
        //Given
        TrelloList trelloList = new TrelloList("1", "List 1", false);
        List<TrelloList> list1 = new ArrayList<>();
        list1.add(trelloList);

        //When
        List<TrelloListDto> testList = trelloMapper.mapToListDto(list1);

        //Then
        Assertions.assertEquals(list1.get(0).getName(), testList.get(0).getName());
    }
    @Test
    public void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card 1", "First card", "1", "1");
        //When
        TrelloCard testCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assertions.assertEquals(trelloCardDto.getName(), testCard.getName());
    }
    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Card 1", "First card", "1", "1");
        //When
        TrelloCardDto testCard = trelloMapper.mapToCardDto(trelloCard);
        //Then
        Assertions.assertEquals(trelloCard.getName(), testCard.getName());
    }
}
