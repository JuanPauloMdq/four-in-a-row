package com.jpr.fourinarow.controller;

import com.jpr.fourinarow.services.GameService;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.Logger;

/**
 * Created by Juan on 12/27/2018.
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/game")
public class FourGameController {

    final static Logger logger = LogManager.getLogger(FourGameController.class);

    GameService gameService = new GameService();

    /**
     * Creates a game and return the Board as a JSON
     * @return
     */
    @PostMapping("/create")
    public Object createGame() {
        logger.debug("Creating game");
        return gameService.createGame();
    }

    /**
     * Adds a coin for the game @boardId in the specified column
     *
     * @param boardId
     * @param column
     * @return
     */
    @GetMapping("/addCoin/{boardId}/{column}")
    public Object addCoin(@PathVariable Long boardId, @PathVariable Integer column) {
        logger.debug("Adding coin to column: " + column);
        return gameService.addCoin(boardId, column);
    }

    /**
     * Returns a suggested movement, used to implement the 1 vs Computer
     *
     * @param boardId
     * @return
     */
    @GetMapping("/computer/{boardId}")
    public Object computerMovement(@PathVariable Long boardId) {
        logger.debug("Getting computer movement");
        return gameService.getComputerMovement(boardId);
    }
}
