package com.jpr.fourinarow.model;

/**
 * State of the 4 in a Row Game, is used to keep tracking if the game ended and which player won
 */

public class GameState {

    private Boolean gameEnded = false;
    private Integer winner;
    private Boolean currentPlayerBlue;


    public Boolean getGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(Boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public Boolean getCurrentPlayerBlue() {
        return currentPlayerBlue;
    }

    public void setCurrentPlayerBlue(Boolean currentPlayerBlue) {
        this.currentPlayerBlue = currentPlayerBlue;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Integer getWinner() {
        return winner;
    }
}
