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
        // Blue player movements
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

        Integer bestScore = -2;
        Integer bestScorePosition = 0;
        System.out.println("------------------------------");
        for(int i=0; i<GameBoard.BOARD_SIZE; i++){
            Integer score = getScore(currentGame, i);
            System.out.println("Score: " + score);
            if(score > bestScore || (score == bestScore && flipACoin())){
                bestScore = score;
                bestScorePosition = i;
            }
        }

        return bestScorePosition;
    }

    private boolean flipACoin(){
        return Math.random() > 0.5;
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

        Integer badMovements = 0;
        // Red Player movements
        for(int i=0; i<GameBoard.BOARD_SIZE; i++){
            currentGame.addCoin(i);
            // The opponnet will win after this moment
            if(currentGame.getGameState().getGameEnded()){
                badMovements++;
            }
            currentGame.removeCoin(i);
        }
        if(badMovements >0){
            currentGame.removeCoin(column);
            return -1 * badMovements;
        }

        // Blue player movements
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
