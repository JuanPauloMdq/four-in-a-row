package com.jpr.fourinarow.model;

import com.jpr.fourinarow.model.exceptions.DiscOutofBoardException;
import com.jpr.fourinarow.model.exceptions.GameEndedException;

import java.util.Arrays;

/**
 * Board with the state of a 4 i a Row game
 *
 */

public class GameBoard {
    public static final int BOARD_BLUE_COIN = 1;
    public static final int BOARD_RED_COIN = 2;

    public static final int PLAYER_BLUE = 0;
    public static final int PLAYER_RED = 1;
    public static final int PLAYER_COMPUTER = 2;

    private Long gameId;
    private GameState gameState = new GameState();
    private byte[][] board;
    private int boardSize;


    public GameBoard(Long gameId, int boardSize) {
        this.boardSize = boardSize;
        this.gameId = gameId;
        board = new byte[boardSize][boardSize];

        for(int i=0; i<boardSize; i++) {
            Arrays.fill(board[i], (byte) 0);
        }

    }

    /**
     *  Adds a coin to a specific @Column, the method checks for the first available row
     *
     * @param column
     */

    public void addCoin(Integer column) {
        if(getGameState().getGameEnded()){
            // The user tried to add a coin when the game was already ended
            throw new GameEndedException();
        }
        // Place the coin
        Integer row = placeCoin(column);
        // Checks if the currentPlayer won
        if(checkFourInARow(column, row)){
            getGameState().setGameEnded(true);
            if(getGameState().getCurrentPlayerBlue()){
                getGameState().setWinner(PLAYER_BLUE);
            } else {
                getGameState().setWinner(PLAYER_RED);
            }

        } else {
            // Change user
            getGameState().setCurrentPlayerBlue(!getGameState().getCurrentPlayerBlue());
        }
    }

    /**
     * Determines if the current player won after add a coin in row/column
     *
     * @param column Colukmn of the lastest coin added
     * @param row Row of the lastest coin added
     * @return Boolean that contains if the current player won
     */
    private Boolean checkFourInARow(Integer column, Integer row) {
        Integer consecutiveOcurrences = 0;
        byte colorForCurrentUser = getColorForCurrentUser();

        // This method checks if a player won with the lastest coin added
        // the complexity of this method is constant O(1)
        // Checks in 4 * 7 cells (directions multiplied the cells around each direction)

        // Horizontal check
        for(int i=0; i<boardSize; i++){
            if(isColor(row, column-3+i , colorForCurrentUser)){
                consecutiveOcurrences++;

                if(consecutiveOcurrences == 4){
                    return true;
                }
            } else {
                consecutiveOcurrences = 0;
            }
        }

        consecutiveOcurrences = 0;
        // Vertical check
        for(int i=0; i<boardSize; i++){
            if(isColor(row-3+i, column, colorForCurrentUser)){
                consecutiveOcurrences++;

                if(consecutiveOcurrences == 4){
                    return true;
                }
            } else {
                consecutiveOcurrences = 0;
            }
        }

        consecutiveOcurrences = 0;
        // Diagonal check
        for(int i=0; i<boardSize; i++){
            if(isColor(row-i+3, column-i+3,colorForCurrentUser)){
                consecutiveOcurrences++;

                if(consecutiveOcurrences == 4){
                    return true;
                }
            } else {
                consecutiveOcurrences = 0;
            }
        }

        consecutiveOcurrences = 0;
        // The Other Diagonal check
        for(int i=0; i<boardSize; i++){
            if(isColor(row+i-3, column-i+3,colorForCurrentUser)){
                consecutiveOcurrences++;

                if(consecutiveOcurrences == 4){
                    return true;
                }
            } else {
                consecutiveOcurrences = 0;
            }
        }

        return false;
    }

    /**
     * Checks if a cell of the board contains a coin of the color @color
     *
     * @param row
     * @param column
     * @param color
     * @return
     */
    private boolean isColor(int row, int column, byte color) {
        // Checks if the position is out of bounds
        if(row<0 || row>=boardSize || column<0 || column>=boardSize){
            return false;
        }
        return board[column][row] == color;
    }

    /**
     * Place a coin in the specific @column
     *
     * @param column
     * @return
     */
    private Integer placeCoin(Integer column) {
        for(int i=0; i<boardSize; i++){
            if(board[column][i] == 0){
                board[column][i] = getColorForCurrentUser();

                return i;
            }
        }

        throw new DiscOutofBoardException();
    }

    /**
     * Returns the color code assigned to the current user
     *
     * @return
     */
    private byte getColorForCurrentUser() {
        if(getGameState().getCurrentPlayerBlue()){
            return BOARD_BLUE_COIN;
        }
        return BOARD_RED_COIN;
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
}
