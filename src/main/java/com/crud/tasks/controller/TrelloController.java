package com.crud.tasks.controller;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    //@Autowired
    private final TrelloService trelloService;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
return trelloService.fetchTrelloBoards();
    }

   // @GetMapping("getTrelloFillterBoards")
    //public List<TrelloBoardDto> getTrelloFilterBoards() {
     //   List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

     //   trelloBoards.stream().filter(trelloBoardDto -> trelloBoards.contains(trelloBoardDto.getId())).
       //         filter(trelloBoardDto -> trelloBoards.contains(trelloBoardDto.getName())).
         //       filter(trelloBoardDto -> trelloBoards.contains(trelloBoardDto.getName().contains("kodilla"))).
           //     collect(Collectors.toList());

        //return trelloBoards;

      //trelloBoards .stream()
        //        .filter(p -> Objects.nonNull(p.getId()) && Objects.nonNull(p.getName()))
          //      .filter(p -> p.getName().contains("Kodilla"))
            //    .collect(Collectors.toList());

      //return trelloBoards;
    //}
    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createTrelloCard(trelloCardDto);
    }
}