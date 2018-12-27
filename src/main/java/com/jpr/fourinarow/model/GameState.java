package com.jpr.fourinarow.model;

/**
 * Created by Juan on 12/27/2018.
 */

public class GameState {

    private Boolean gameEnded;
    private Boolean playerAWin;
    private Boolean currentPlayerA;


    public Boolean getGameEnded() {
        return gameEnded;
    }

    public Boolean getPlayerAWin() {
        return playerAWin;
    }

    public void setGameEnded(Boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public void setPlayerAWin(Boolean playerAWin) {
        this.playerAWin = playerAWin;
    }

    public Boolean getCurrentPlayerA() {
        return currentPlayerA;
    }

    public void setCurrentPlayerA(Boolean currentPlayerA) {
        this.currentPlayerA = currentPlayerA;
    }
}
