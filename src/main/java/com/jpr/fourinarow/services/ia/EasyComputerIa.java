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
        // First movements at random
        if(currentGame.getAmountOfCoins() <=4){
            return (int) Math.floor(Math.random() * GameBoard.BOARD_SIZE);
        }

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
        for(int i=0; i<GameBoard.BOARD_SIZE; i++){
            Integer score = getScore(currentGame, i);
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
     * the method returns a negative score. Otherwise returns the movement that may produce the max amount of winning
     * scenarios in the next movement
     *
     * @param currentGame
     * @param column
     * @return
     */
    private Integer getScore(GameBoard currentGame, int column) {
        if(currentGame.addCoin(column)){
            Integer badMovements = 0;
            // Red Player movements
            // Detects if the opponent can win after this movement
            for(int i=0; i<GameBoard.BOARD_SIZE; i++){
                if(currentGame.addCoin(i)){
                    if(currentGame.getGameState().getGameEnded()){
                        badMovements++;
                    }
                    currentGame.removeCoin(i);
                }
            }
            if(badMovements >0){
                currentGame.removeCoin(column);
                return -1 * badMovements;
            }

            // Blue player movements
            // Detects if how many winning situation will generate this movement
            Integer winningMovements = 0;
            for(int i=0; i<GameBoard.BOARD_SIZE; i++){
                winningMovements += countWinningMovements(currentGame, i);
            }

            currentGame.removeCoin(column);
            return winningMovements;
        } else {
            // The column is full
            return -10;
        }
    }

    /**
     * Count the amount of winning chaces two steps ahead
     *
     * @param currentGame
     * @param column
     * @return
     */
    private Integer countWinningMovements(GameBoard currentGame, Integer column){
        if(currentGame.addCoin(column)){
            Integer winningMovements = 0;
            for(int i=0; i<GameBoard.BOARD_SIZE; i++){
                if(currentGame.addCoin(i)){
                    if(currentGame.getGameState().getGameEnded()){
                        winningMovements++;
                    }
                    currentGame.removeCoin(i);
                }
            }
            currentGame.removeCoin(column);
            return winningMovements;
        }

        return 0;
    }

}
