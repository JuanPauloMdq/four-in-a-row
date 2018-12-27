package com.jpr.fourinarow.services;

import com.jpr.fourinarow.model.GameBoard;
import com.jpr.fourinarow.model.exceptions.GameNotFountException;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Juan on 12/27/2018.
 */

public class GameService {

    public static final int BOARD_SIZE = 7;

    private Long gameNumber = 0l;
    private HashMap<Long, GameBoard> games = new HashMap<Long, GameBoard>();
    private Random startingPlayerRandom = new Random();


    // Creates a game and returns it
    public GameBoard createGame(){
        Long gameId = gameNumber;
        gameNumber++;

        GameBoard board = new GameBoard(gameId, BOARD_SIZE);
        // Choose the starting player at random
        board.getGameState().setCurrentPlayerA(startingPlayerRandom.nextBoolean());
        games.put(gameId, board);

        return board;
    }


    public GameBoard addDisc(Long gameId, Integer position) {
        if(games.containsKey(gameId)){
            GameBoard currentGame = games.get(gameId);
            currentGame.addDisc(position);
            return currentGame;

        } else {
            throw new GameNotFountException();
        }
    }
}
