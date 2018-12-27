package com.jpr.fourinarow.model;

import com.jpr.fourinarow.model.exceptions.DiscOutofBoardException;

import java.util.Arrays;

/**
 * Created by Juan on 12/27/2018.
 */

public class GameBoard {

    private Long gameId;
    private GameState gameState = new GameState();
    private byte[][] board;
    private int boardSize;


    public GameBoard(Long gameId, int boardSize) {
        this.boardSize = boardSize;
        this.gameId = gameId;
        board = new byte[boardSize][boardSize];
        Arrays.fill(board, 0);
    }

    public Long getGameId() {
        return gameId;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void addDisc(Integer position) {
        // Place the disk
        Integer positionY = placeDisk(position);
        // Checks if the currentPlayer won
        checkFourInARow();
        // Change user
        getGameState().setCurrentPlayerA(!getGameState().getCurrentPlayerA());
    }

    private void checkFourInARow() {
        // TODO:
    }

    private Integer placeDisk(Integer position) {
        for(int i=0; i<boardSize; i++){
            if(board[position][i] == 0){
                board[position][i] = getColorForCurrentUser();

                return i;
            }
        }

        throw new DiscOutofBoardException();
    }

    private byte getColorForCurrentUser() {
        if(getGameState().getCurrentPlayerA()){
            return 1;
        }
        return 2;
    }
}
