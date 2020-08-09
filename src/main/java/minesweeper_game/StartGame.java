package minesweeper_game;

import java.util.Scanner;

public class StartGame {
    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select Game difficulty:");
        System.out.println("Press 0 for BEGINNER (9*9 Cells and 10 Mines)");
        System.out.println("Press 1 for INTERMEDIATE (16*16 Cells and 40 Mines)");
        System.out.println("Press 2 for ADVANCED (24*24 Cells and 99 Mines)");
        String difficulty = scanner.next();
        Game game = new Game(tryParse(difficulty,0));
        char board[][] = game.getField();
        System.out.println("Game Started Good Luck!\nBoard status:");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print('-');
            }
            System.out.println();
        }
        System.out.println("\nPlease select a cell:");
        String parseRow = scanner.next();
        String parseCol = scanner.next();
        int row = tryParse(parseRow,0);
        int col = tryParse(parseCol,0);
        game.createField(row, col);
        game.clearCells();
        for (int i = 0; i < game.getField().length * game.getField().length + 1; i++) {
            System.out.println("Please select a cell:");
            parseRow = scanner.next();
            parseCol = scanner.next();
            row = tryParse(parseRow,-1);
            col = tryParse(parseCol,-1);
            System.out.println();
            game.selectCell(row, col);
            if (game.isGameRunning() == false) {
                System.out.println("You Lost!\n");
                break;
            }
            game.clearCells();
            if (game.checkWinCondition() == true) {
                System.out.println("Congratulation you won!");
                game.setGameRunning(false);
                break;
            }
            System.out.println();
        }
        game.clearCells();
    }

    public int tryParse(String value, int defaultParam) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultParam;
        }
    }

}
