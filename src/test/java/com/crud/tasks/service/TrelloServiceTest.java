package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TrelloServiceTest {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void fetchTrelloBoardsTest() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        TrelloListDto listDto = new TrelloListDto("1", "List1", false);
        trelloListDto.add(listDto);

        TrelloBoardDto boardDto = new TrelloBoardDto("Board 1", "1", trelloListDto);
        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(boardDto);

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDto);

        //When
        List<TrelloBoardDto> testTrelloBoardDto = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, testTrelloBoardDto.size());
        assertEquals("1", testTrelloBoardDto.get(0).getId());
        assertEquals("Board 1", testTrelloBoardDto.get(0).getName());
    }
//
    @Test
    public void fetchEmptyTrelloBoardsTest() {
        //Given
        List<TrelloBoardDto> emptyTrelloBoardDto = new ArrayList<>();

        when(trelloClient.getTrelloBoards()).thenReturn(emptyTrelloBoardDto);

        //When
        List<TrelloBoardDto> testEmptyTrelloBoardDto = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(0, testEmptyTrelloBoardDto.size());
    }

    @Test
    public void createTrelloCardTest() {
        //Given
        TrelloDto trelloDto = new TrelloDto(1, 2);
        TrelloBadgesDto badgesDto = new TrelloBadgesDto(1, new TrelloAttachmentsByTypeDto(trelloDto));

        CreatedTrelloCardDto theCard = new CreatedTrelloCardDto("1", "Test card", "url", badgesDto);
        TrelloCardDto trelloCardDto = new TrelloCardDto("Card1", "1", "1", "1");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(theCard);

        //when
        CreatedTrelloCardDto testCreatedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertThat(testCreatedTrelloCardDto.getId()).isEqualTo("1");
        assertThat(testCreatedTrelloCardDto.getName()).isEqualTo("Test card");
        assertThat(testCreatedTrelloCardDto.getShortUrl()).isEqualTo("url");
        assertThat(testCreatedTrelloCardDto.getBadges().getVotes()).isEqualTo(1);
        assertEquals(1, testCreatedTrelloCardDto.getBadges().getAttachments().getTrello().getBoard());
        assertEquals(2, testCreatedTrelloCardDto.getBadges().getAttachments().getTrello().getCard());
        assertEquals("1", testCreatedTrelloCardDto.getId());
        assertEquals("Test card", testCreatedTrelloCardDto.getName());
        assertEquals("url", testCreatedTrelloCardDto.getShortUrl());
    }
}
