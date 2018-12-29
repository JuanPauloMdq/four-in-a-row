package com.jpr.fourinarow.services;

import com.jpr.fourinarow.model.GameBoard;
import com.jpr.fourinarow.model.exceptions.GameNotFountException;

import java.util.HashMap;
import java.util.Random;

/**
 * Services to create or progress in the 4 in a Row game
 */

public class GameService {

    public static final int BOARD_SIZE = 7;

    private Long gameNumber = 0l;
    private HashMap<Long, GameBoard> games = new HashMap<Long, GameBoard>();
    private Random startingPlayerRandom = new Random();


    /**
     * Create a game and return it
     * @return
     */
    public GameBoard createGame(){
        Long gameId = gameNumber;
        // Sets an incremental Id for the games, in case we want multiple servers
        // we would need to store the games in a database and change the way the id is generated
        gameNumber++;

        GameBoard board = new GameBoard(gameId, BOARD_SIZE);
        // Choose the starting player at random
        board.getGameState().setCurrentPlayerBlue(startingPlayerRandom.nextBoolean());
        games.put(gameId, board);

        return board;
    }


    /**
     * Adds a coin to the game with the id @gameId to the specified column
     *
     * @param gameId
     * @param column
     * @return
     */
    public GameBoard addCoin(Long gameId, Integer column) {
        if(games.containsKey(gameId)){
            GameBoard currentGame = games.get(gameId);
            currentGame.addCoin(column);

            // Removes the game from the Map once is ended
            if(currentGame.getGameState().getGameEnded()){
                games.remove(gameId);
            }
            return currentGame;

        } else {
            throw new GameNotFountException();
        }
    }

    /**
     * Returns the computer movement for the specified game
     *
     * @param gameId
     * @return
     */
    public Object getComputerMovement(Long gameId) {
        if(games.containsKey(gameId)){
            GameBoard currentGame = games.get(gameId);

            // First movement at random
            if(currentGame.getAmountOfCoins() <=4){
                return Math.floor(Math.random() * BOARD_SIZE);
            } else {
                return Math.floor(Math.random() * BOARD_SIZE);
            }

        } else {
            throw new GameNotFountException();
        }
    }
}
