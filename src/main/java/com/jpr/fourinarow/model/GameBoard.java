package com.jpr.fourinarow.model;

import com.jpr.fourinarow.model.exceptions.DiscOutofBoardException;
import com.jpr.fourinarow.model.exceptions.GameEndedException;

import java.util.Arrays;

/**
 * Board with the state of a 4 i a Row game
 *
 */

public class GameBoard {
    public static final int BOARD_SIZE = 7;

    public static final byte BOARD_EMPTY_COIN = 0;
    public static final byte BOARD_BLUE_COIN = 1;
    public static final byte BOARD_RED_COIN = 2;

    public static final int PLAYER_BLUE = 0;
    public static final int PLAYER_RED = 1;

    private Long boardId;
    private GameState gameState = new GameState();
    private byte[][] board;

    private int amountOfCoins = 0;


    /**
     * Returns the Board corresponding to the @boardId
     *
     * @param boardId
     */
    public GameBoard(Long boardId) {
        this.boardId = boardId;
        board = new byte[BOARD_SIZE][BOARD_SIZE];

        for(int i=0; i<BOARD_SIZE; i++) {
            Arrays.fill(board[i], (byte) 0);
        }

    }

    /**
     *  Adds a coin to a specific @Column, the method checks for the first available row
     *
     * @param column
     * @return determines if was able to add the coin
     */

    public Boolean addCoin(Integer column) {
        if(getGameState().getGameEnded()){
            // The user tried to add a coin when the game was already ended
            throw new GameEndedException();
        }
        // Place the coin
        Integer row;
        try{
        row = placeCoin(column);
        } catch (DiscOutofBoardException ex){
            return false;
        }

        // Checks if the currentPlayer won
        if(checkFourInARow(column, row)){
            getGameState().setGameEnded(true);
            if(getGameState().getCurrentPlayerBlue()){
                getGameState().setWinner(PLAYER_BLUE);
            } else {
                getGameState().setWinner(PLAYER_RED);
            }

        }

        // Switch user
        getGameState().setCurrentPlayerBlue(!getGameState().getCurrentPlayerBlue());

        return true;
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
        for(int i=0; i<BOARD_SIZE; i++){
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
        for(int i=0; i<BOARD_SIZE; i++){
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
        for(int i=0; i<BOARD_SIZE; i++){
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
        for(int i=0; i<BOARD_SIZE; i++){
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
        if(row<0 || row>=BOARD_SIZE || column<0 || column>=BOARD_SIZE){
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
        for(int i=0; i<BOARD_SIZE; i++){
            if(board[column][i] == BOARD_EMPTY_COIN){
                board[column][i] = getColorForCurrentUser();

                amountOfCoins ++;

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

    /**
     * Removes a coin from the board
     *
     * @param column
     */
    public void removeCoin(Integer column){
        getGameState().setGameEnded(false);
        amountOfCoins--;

        // Switch the user back
        getGameState().setCurrentPlayerBlue(!getGameState().getCurrentPlayerBlue());

        for(int i=BOARD_SIZE-1; i>=0; i--){
            if(board[column][i] != BOARD_EMPTY_COIN){
                board[column][i] = BOARD_EMPTY_COIN;
                return;
            }
        }
    }


    public Long getBoardId() {
        return boardId;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getAmountOfCoins() {
        return amountOfCoins;
    }
}
