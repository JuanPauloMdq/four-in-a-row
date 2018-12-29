package com.jpr.fourinarow.controller;

import com.jpr.fourinarow.services.GameService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created by Juan on 12/27/2018.
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/game")
public class FourGameController {

    GameService gameService = new GameService();

    /**
     * Creates a game and return the Board as a JSON
     * @return
     */
    @PostMapping("/create")
    public Object createGame() {
        return gameService.createGame();
    }

    /**
     * Adds a coin for the game @gameId in the specified column
     *
     * @param gameId
     * @param column
     * @return
     */
    @GetMapping("/addCoin/{gameId}/{column}")
    public Object addCoin(@PathVariable Long gameId, @PathVariable Integer column) {
        return gameService.addCoin(gameId, column);
    }

    /**
     * Returns a suggested movement, can be used to implement the 1vscomputer
     *
     * @param gameId
     * @return
     */
    @GetMapping("/computer/{gameId}")
    public Object computerMovement(@PathVariable Long gameId) {
        return gameService.getComputerMovement(gameId);
    }
}
