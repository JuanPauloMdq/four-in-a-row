import com.jpr.fourinarow.model.GameBoard;
import com.jpr.fourinarow.model.exceptions.DiscOutofBoardException;
import com.jpr.fourinarow.services.GameService;
import org.junit.Test;

/**
 * Created by Usuario on 12/29/2018.
 */
public class WinConditionsTest {


    /**
     * Test if the horizontal detection of a winning state working
     */
    @Test
    public void testHorizontallRed(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(false);

        testHorizontall(board);

        assert board.getGameState().getGameEnded() && board.getGameState().getWinner().equals(GameBoard.PLAYER_RED);
    }

    @Test
    public void testHorizontallBlue(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(true);

        testHorizontall(board);

        assert board.getGameState().getGameEnded() && board.getGameState().getWinner().equals(GameBoard.PLAYER_BLUE);
    }

    @Test
    public void testHorizontallRed2(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(false);

        testHorizontall2(board);

        assert board.getGameState().getGameEnded() && board.getGameState().getWinner().equals(GameBoard.PLAYER_RED);
    }

    @Test
    public void testHorizontallBlue2(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(true);

        testHorizontall2(board);

        assert board.getGameState().getGameEnded() && board.getGameState().getWinner().equals(GameBoard.PLAYER_BLUE);
    }

    @Test
    public void testHorizontallRed3(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(true);

        testHorizontall3(board);

        assert board.getGameState().getGameEnded() && board.getGameState().getWinner().equals(GameBoard.PLAYER_RED);
    }

    @Test
    public void testHorizontallBlue3(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(false);

        testHorizontall3(board);

        assert board.getGameState().getGameEnded() && board.getGameState().getWinner().equals(GameBoard.PLAYER_BLUE);
    }

    private void testHorizontall(GameBoard board) {
        // B B B
        // R R R R

        board.addCoin(0);
        board.addCoin(0);
        board.addCoin(1);
        board.addCoin(1);
        board.addCoin(2);
        board.addCoin(2);
        board.addCoin(3);
    }

    private void testHorizontall2(GameBoard board) {
        //   B B B
        // R R R R

        board.addCoin(3);
        board.addCoin(3);
        board.addCoin(2);
        board.addCoin(2);
        board.addCoin(1);
        board.addCoin(1);
        board.addCoin(0);
    }

    private void testHorizontall3(GameBoard board) {
        //     B B
        // R R R R B

        board.addCoin(4);
        board.addCoin(3);
        board.addCoin(3);
        board.addCoin(2);
        board.addCoin(2);
        board.addCoin(1);
        board.addCoin(1);
        board.addCoin(0);
    }


    /**
     * Test if the vertical detection of a winning state working
     */
    @Test
    public void testVerticalRed(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(false);

        testVerticall(board);

        assert board.getGameState().getGameEnded() && board.getGameState().getWinner().equals(GameBoard.PLAYER_RED);
    }

    @Test
    public void testVerticalBlue(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(true);

        testVerticall(board);

        assert board.getGameState().getGameEnded() && board.getGameState().getWinner().equals(GameBoard.PLAYER_BLUE);
    }

    private void testVerticall(GameBoard board) {
        // R
        // R B
        // R B
        // R B

        board.addCoin(0);
        board.addCoin(1);
        board.addCoin(0);
        board.addCoin(1);
        board.addCoin(0);
        board.addCoin(1);
        board.addCoin(0);
    }

    /**
     * Test if the diagonal detection of a winning state working
     */
    @Test
    public void testDiagonalRed(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(false);

        testVerticall(board);

        assert board.getGameState().getGameEnded() && board.getGameState().getWinner().equals(GameBoard.PLAYER_RED);
    }

    @Test
    public void testDiagonalBlue(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(true);

        testDiagonal(board);

        assert board.getGameState().getGameEnded() && board.getGameState().getWinner().equals(GameBoard.PLAYER_BLUE);
    }

    @Test
    public void testDiagonal2Red(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(false);

        testDiagonal2(board);

        assert board.getGameState().getGameEnded() && board.getGameState().getWinner().equals(GameBoard.PLAYER_RED);
    }

    @Test
    public void testDiagonal2Blue(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(true);

        testDiagonal2(board);

        assert board.getGameState().getGameEnded() && board.getGameState().getWinner().equals(GameBoard.PLAYER_BLUE);
    }

    private void testDiagonal(GameBoard board) {
        // R
        // R R
        // B B R
        // B R B R    B
        board.addCoin(3);
        board.addCoin(2);
        board.addCoin(2);
        board.addCoin(0);
        board.addCoin(1);
        board.addCoin(1);
        board.addCoin(1);
        board.addCoin(0);
        board.addCoin(0);
        board.addCoin(5);
        board.addCoin(0);
    }

    private void testDiagonal2(GameBoard board) {
        //         R
        //       R R
        //   B R R B
        //   R B B B
        board.addCoin(1);
        board.addCoin(2);
        board.addCoin(2);
        board.addCoin(3);
        board.addCoin(3);
        board.addCoin(4);
        board.addCoin(3);
        board.addCoin(4);
        board.addCoin(4);
        board.addCoin(1);
        board.addCoin(4);
    }





    /**
     * Checks that add a coin in a full column is not allowed
     */
    @Test
    public void testColumnFull(){
        GameBoard board = new GameBoard(1l, GameService.BOARD_SIZE);
        board.getGameState().setCurrentPlayerBlue(true);

        try{
            board.addCoin(0);
            board.addCoin(0);
            board.addCoin(0);
            board.addCoin(0);
            board.addCoin(0);
            board.addCoin(0);
            board.addCoin(0);
            board.addCoin(0);
            assert false;
        } catch(DiscOutofBoardException ex){
            assert true;
        }

    }
}
