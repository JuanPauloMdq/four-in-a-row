package com.jpr.fourinarow.controller;

import com.jpr.fourinarow.services.GameService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created by Juan on 12/27/2018.
 */

@RestController
@RequestMapping("/game")
public class FourGameController {

    GameService gameService = new GameService();

    @GetMapping("/create")
    public Object createGame() {
        return gameService.createGame();
    }

    @GetMapping("/addMovement/{gameId}/{position}")
    public Object addDisc(@PathParam("gameId") Long gameId, @PathParam("position") Integer position) {
        return gameService.addDisc(gameId, position);
    }
}
