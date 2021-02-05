package com.crud.tasks.controller;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {
    private final TrelloClient trelloClient;

    @GetMapping("/getTrelloBoards")
    public void getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();



        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());


        });
    }
    @GetMapping("/getTrelloFillterBoards")
    public List<TrelloBoardDto> getTrelloFilterBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.stream().filter(trelloBoardDto -> trelloBoards.contains(trelloBoardDto.getId())).
                filter(trelloBoardDto -> trelloBoards.contains(trelloBoardDto.getName())).
                filter(trelloBoardDto -> trelloBoards.contains(trelloBoardDto.getName().contains("kodilla"))).collect(Collectors.toList());

        return trelloBoards;
    }
}