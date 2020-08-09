package minesweeper_game;

public class Game {
    private boolean gameRunning = true;
    private int difficulty;
    private int minesLimit;
    private char[][] field;

    public Game() {
    }

    public Game(int difficulty) {
        this.difficulty = difficulty;
        if (difficulty != 1 && difficulty!= 2) {
            minesLimit = 10;
            field = new char[9][9];
        }
        if (difficulty == 1) {
            minesLimit = 40;
            field = new char[16][16];
        }
        if (difficulty == 2) {
            minesLimit = 99;
            field = new char[24][24];
        }
    }

    public void createField(int fieldRow, int fieldCol) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = '-';
            }
        }
        int numOfMines;
        for (numOfMines = 0; numOfMines < minesLimit; numOfMines++) {
            int row, col;
            do {
                row = (int) (Math.random() * (field.length));
                col = (int) (Math.random() * (field.length));
            }
            while (field[row][col] == '*' || (row == fieldRow && col == fieldCol));
            field[row][col] = '*';
        }
        selectCell(fieldRow, fieldCol);
    }

    public void selectCell(int row, int col) {
        if (row < 0 || col < 0 || row > field.length - 1 || col > field[0].length - 1) return;
        if (field[row][col] == '*') {
            gameRunning = false;
            return;
        }
        int bombCount = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (!(j < 0 || i < 0 || j > field[0].length - 1 || i > field.length - 1) && field[i][j] == '*')
                    bombCount++;
            }
        }
        field[row][col] = (char) ('0' + bombCount);
        if (bombCount == 0) {
            for (int i = row - 1; i < row + 1; i++) {
                for (int j = col - 1; j < col + 1; j++) {
                    if (!(j < 0 || i < 0 || j > field.length - 1 || i > field.length - 1)) {
                        if (i != row || j != col) selectCell(i, j);
                    }
                }
            }
        } else field[row][col] = (char) ('0' + bombCount);

    }

    public void clearCells() {
        int i, j;
        for (i = 0; i < field.length; i++) {
            for (j = 0; j < field.length; j++) {
                if (field[i][j] == '*' && isGameRunning()) System.out.print('-');
                else System.out.print(field[i][j]);
            }
            System.out.println();

        }
    }

    public boolean checkWinCondition() {
        boolean result = true;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == '-') {
                    result = false;
                    break;
                }
            }
            if (!result) break;
        }
        return result;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean game_state) {
        this.gameRunning = game_state;
    }

    public int getMinesLimit() {
        return minesLimit;
    }

    public void setMinesLimit(int minesLimit) {
        this.minesLimit = minesLimit;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public char[][] getField() {
        return field;
    }

    public void setField(char[][] field) {
        this.field = field;
    }
}
