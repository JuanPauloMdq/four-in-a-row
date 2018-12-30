package com.jpr.fourinarow.services;

import com.jpr.fourinarow.model.GameBoard;
import com.jpr.fourinarow.model.exceptions.GameNotFountException;
import com.jpr.fourinarow.services.ia.EasyComputerIa;

import java.util.HashMap;
import java.util.Random;

/**
 * Services to create or progress in the 4 in a Row game
 */

public class GameService {

    private Long gameNumber = 0l;
    private HashMap<Long, GameBoard> games = new HashMap<Long, GameBoard>();
    private Random startingPlayerRandom = new Random();

    private EasyComputerIa easyComputerIa = new EasyComputerIa();

    /**
     * Create a game and return it
     * @return
     */
    public GameBoard createGame(){
        Long boardId = gameNumber;
        // Sets an incremental Id for the games, in case we want multiple servers
        // we would need to store the games in a database and change the way the id is generated
        gameNumber++;

        GameBoard board = new GameBoard(boardId);
        // Choose the starting player at random
        board.getGameState().setCurrentPlayerBlue(startingPlayerRandom.nextBoolean());
        games.put(boardId, board);

        return board;
    }


    /**
     * Adds a coin to the game with the id @boardId to the specified column
     *
     * @param boardId
     * @param column
     * @return
     */
    public GameBoard addCoin(Long boardId, Integer column) {
        if(games.containsKey(boardId)){
            GameBoard currentGame = games.get(boardId);
            currentGame.addCoin(column);

            // Removes the game from the Map once is ended
            if(currentGame.getGameState().getGameEnded()){
                games.remove(boardId);
            }
            return currentGame;

        } else {
            throw new GameNotFountException();
        }
    }

    /**
     * Returns the computer movement for the specified game
     *
     * @param boardId
     * @return
     */
    public Object getComputerMovement(Long boardId) {
        if(games.containsKey(boardId)){
            GameBoard currentGame = games.get(boardId);

            return easyComputerIa.getComputerMovement(currentGame);

        } else {
            throw new GameNotFountException();
        }
    }
}
