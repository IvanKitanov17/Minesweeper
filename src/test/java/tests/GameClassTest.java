package tests;

import minesweeper_game.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;


public class GameClassTest {
    @Test
    @DisplayName("Testing create field method")
    public void createField() {
        Game beginnerGame = new Game(0);
        Game intermediateGame = new Game(1);
        Game advancedGame = new Game(2);
        Assertions.assertTrue(beginnerGame.getField().length == 9 && beginnerGame.getMinesLimit() == 10 && intermediateGame.getField().length == 16
                && intermediateGame.getMinesLimit() == 40 && advancedGame.getField().length == 24 && advancedGame.getMinesLimit() == 99);
    }

    @Test
    @DisplayName("Testing check win condition method")
    public void checkWinCOndition() {
        char board[][] = {{'1', '*'},
                {'1', '1'}};
        Game game = new Game();
        game.setField(board);
        game.setGameRunning(false);
        Assertions.assertTrue(game.checkWinCondition());
        game.setGameRunning(true);
        board = new char[][]{{'1', '*'}, {'-', '-'}};
        game.setField(board);
        Assertions.assertTrue(!game.checkWinCondition());
    }

    @Test
    @DisplayName("Testing select cell method")
    public void selectcell() {
        char board[][] = {{'1', '*', '1'},
                {'-', '-', '1'},
                {'*', '-', '-'}};
        Game game = new Game();
        game.setField(board);
        game.selectCell(1, 1);
        Assertions.assertTrue(game.getField()[1][1] == '2');

    }

    @Test
    @DisplayName("Testing clear cells method")
    public void clearCells() {
        Game game = new Game();
        game.setField(new char[][]{
                {'-', '*'},
                {'-', '*'}
        });
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        game.clearCells();
        Assertions.assertEquals(
                "--\r\n--\r\n"
                , outContent.toString());

    }

}
