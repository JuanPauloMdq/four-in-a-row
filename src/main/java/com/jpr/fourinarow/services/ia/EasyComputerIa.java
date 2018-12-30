package com.jpr.fourinarow.services.ia;

import com.jpr.fourinarow.model.GameBoard;

/**
 * Simple heuristic to determine computer movemets
 *
 */
public class EasyComputerIa {

    /**
     * Returns a suggest movement for the computer, it only analyse two steps ahead
     *
     * @param currentGame
     * @return
     */
    public Integer getComputerMovement(GameBoard currentGame) {
        for(int i=0; i<GameBoard.BOARD_SIZE; i++){
            if(currentGame.addCoin(i)){
                if(currentGame.getGameState().getGameEnded()){
                    // Winner movement detected
                    currentGame.removeCoin(i);

                    return i;
                } else {
                    currentGame.removeCoin(i);
                }
            }
        }

        return (int) Math.floor(Math.random() * GameBoard.BOARD_SIZE);

        /*Integer bestScore = 0;
        Integer bestScorePosition = 0;
        for(int i=0; i<GameBoard.BOARD_SIZE; i++){
            Integer score = getScore(currentGame, i);
            if(score > bestScore){
                bestScore = score;
                bestScorePosition = i;
            }
        }*/

        //return bestScorePosition;
    }

    /**
     * Returns a score for each posible movement. If the next moment would produce a losing situation
     * the method returns -1 score. Otherwise returns the movement that may produce the max amount of winning
     * scenarios in the next movement
     *
     * @param currentGame
     * @param column
     * @return
     */
    private Integer getScore(GameBoard currentGame, int column) {
        currentGame.addCoin(column);

        for(int i=0; i<GameBoard.BOARD_SIZE; i++){
            currentGame.addCoin(i);
            // The opponnet will win after this moment
            if(currentGame.getGameState().getGameEnded()){
                currentGame.removeCoin(i);
                currentGame.removeCoin(column);

                return -1;
            }
            currentGame.removeCoin(i);
        }

        Integer winningMovements = 0;
        for(int i=0; i<GameBoard.BOARD_SIZE; i++){
            winningMovements += countWinningMovements(currentGame, i);
        }

        currentGame.removeCoin(column);
        return winningMovements;
    }

    private Integer countWinningMovements(GameBoard currentGame, Integer column){
        currentGame.addCoin(column);
        Integer winningMovements = 0;
        for(int i=0; i<GameBoard.BOARD_SIZE; i++){
            currentGame.addCoin(i);
            if(currentGame.getGameState().getGameEnded()){
                winningMovements++;
            }
            currentGame.removeCoin(i);
        }
        currentGame.removeCoin(column);
        return winningMovements;
    }

}
