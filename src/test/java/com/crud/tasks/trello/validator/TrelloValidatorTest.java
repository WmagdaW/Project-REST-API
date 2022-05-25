package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloValidatorTest {

    @InjectMocks
    TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    public void shouldValidateTrelloBoards() {
        //Given
        List<TrelloBoard> boardList = new ArrayList<>();
        TrelloBoard board1 = new TrelloBoard("1", "Board1", new ArrayList<>());
        TrelloBoard board2 = new TrelloBoard("2", "Board2", new ArrayList<>());
        boardList.add(board1);
        boardList.add(board2);
        List<TrelloBoard> emptyList = new ArrayList<>();

        //When
        List<TrelloBoard> testList = trelloValidator.validateTrelloBoards(boardList);
        List<TrelloBoard> emptyTestList = trelloValidator.validateTrelloBoards(emptyList);

        //Then
        assertEquals(boardList.size(), testList.size());
        assertEquals("1", testList.get(0).getId());
        assertEquals("Board1", testList.get(0).getName());
        assertEquals(emptyList.size(), emptyTestList.size());
    }

//    @Test
//    public void shouldValidateCard() {
//        //Given
//        TrelloCard testCard = new TrelloCard("Test card", "CardOne", "1", "1");
//        //When
//        trelloValidator.validateCard(testCard);
//        //Then
//        assertThat("Someone is testing my application!", trelloValidator.validateCard(testCard));
//
//    }
}
