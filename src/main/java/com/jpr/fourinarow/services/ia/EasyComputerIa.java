package com.jpr.fourinarow.services.ia;

import com.jpr.fourinarow.model.GameBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple heuristic to determine computer movemets
 *
 */
public class EasyComputerIa {

    private static Integer FULL_COLUMN_SCORE = -10;

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

        Integer bestScore = FULL_COLUMN_SCORE;
        List<Integer> bestScorePositions = new ArrayList<Integer>();

        for(int i=0; i<GameBoard.BOARD_SIZE; i++){
            Integer score = getScore(currentGame, i);
            if(score > bestScore){
                bestScore = score;
                bestScorePositions.clear();
                bestScorePositions.add(i);
            }
            if(score == bestScore){
                bestScorePositions.add(i);
            }
        }

        return pickPosition(bestScorePositions);

    }

    /**
     * Pick one of the best movements selected by the algorithm at random
     *
     *
     * @param positions List of the best moments picked by the algorithm
     * @return Returns one position at random
     */
    private Integer pickPosition(List<Integer> positions){
        if(positions.size() == 1){
            return positions.get(0);
        }

        if(positions.size() > 1){
            Integer position = (int) Math.min(Math.floor(Math.random() * positions.size()), positions.size()-1);
            return positions.get(position);
        } else {
            // In
            throw new RuntimeException("Unable to generate a movement");
        }

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
            return FULL_COLUMN_SCORE;
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
