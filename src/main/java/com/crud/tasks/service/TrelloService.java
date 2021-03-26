package com.crud.tasks.service;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrelloService {
    @Autowired
    private final TrelloClient trelloClient;
    @Autowired
    private final SimpleEmailService emailService;
    private static final String SUBJECT="Task for you";
    @Autowired
    private AdminConfig adminConfig;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);


        Optional.ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "New card: " + trelloCardDto.getName() + " has been created on your Trello account",
                null
        )));
        return newCard;
    }
}
