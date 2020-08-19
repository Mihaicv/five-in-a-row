package com.codecool.fiveinarow;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Game implements GameInterface {

    private int[][] board;
    private int[] lastMove = new int[2];
    Scanner scanner = new Scanner(System.in);


    public Game(int nRows, int nCols) {

        int[][] board = new int[nRows][nCols];
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
        this.setBoard(board);
//        printBoard();
//        getMove(1);

    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getMove(int player) {

        while (true) {
            System.out.printf("Player %s move: ", player);
            String line = scanner.nextLine();

            if (!Character.isDigit(line.charAt(1)) || line.length() != Integer.parseInt(String.valueOf(line.substring(1).length())) + 1 ||
                    Integer.parseInt(line.substring(1)) > board[0].length || !Character.isLetter(line.charAt(0)) || Character.getNumericValue(line.charAt(1)) < 1 ||
                    (Character.toString(line.charAt(0)).toUpperCase().charAt(0)) - 65 >= board.length) {
                System.out.println("Enter a valid move");
                continue;
            } else {
                lastMove[1] = Integer.parseInt(line.substring(1)) - 1;
                lastMove[0] = (int) (Character.toString(line.charAt(0)).toUpperCase().charAt(0)) - 65;
                if (board[lastMove[0]][lastMove[1]] == 1 || board[lastMove[0]][lastMove[1]] == -1) {
                    System.out.println("teapa, locul e este ocupat");
                    continue;
                }
                break;
            }
        }
        return lastMove;
    }

    public int[] getAiMove(int player) {
        return null;
    }

    public void mark(int player, int row, int col) {
        board[row][col] = player;
        lastMove[0] = row;
        lastMove[1] = col;
    }

    public boolean hasWon(int player, int howMany) {
        int count = 1;
        for (int l = lastMove[1] - 1; l >= 0 && board[lastMove[0]][l] == player; l--) {
            count++;
        }
        for (int r = lastMove[1] + 1; r < board[lastMove[0]].length && board[lastMove[0]][r] == player; r++) {
            count++;
        }
        if (count == howMany) {
            return true;
        }
        count = 1;
        for (int u = lastMove[0] - 1; u >= 0 && board[u][lastMove[1]] == player; u--) {
            count++;
        }
        for (int d = lastMove[0] + 1; d < board.length && board[d][lastMove[1]] == player; d++) {
            count++;
        }
        if (count == howMany) {
            return true;
        }
        count = 1;
        int l = lastMove[0] - 1, c = lastMove[1] - 1;
        while (l >= 0 && c >= 0 && board[l][c] == player) {
            count++;
            l--;
            c--;
        }
        l = lastMove[0] + 1;
        c = lastMove[1] + 1;
        while (l < board.length && c < board.length && board[l][c] == player) {
            count++;
            l++;
            c++;
        }
        return false;
    }

    public boolean isFull() {
        int count = 0;
        for(int i = 0 ; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++)
                if(board[i][j] == 0)
                    count++;
        }
        return count == 0;
    }

    public void printBoard() {
        System.out.print(" ");
        for (int i = 1; i <= board[0].length; i++) {
            for (int j = 0; j < 3 - (int) (Math.log10(i)); j++) {
                System.out.print(" ");
            }
            System.out.print(i);
        }
        System.out.print("\n  -");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print("----");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            System.out.print((char) (65 + i) + " ");
            for (int cell : board[i]) {
                switch (cell) {
                    case 0:
                        System.out.print("|   ");
                        break;
                    case 1:
                        System.out.print("| X ");
                        break;
                    case -1:
                        System.out.print("| 0 ");
                        break;
                }
            }
            System.out.print("|\n  -");
            for (int j = 0; j < board[0].length; j++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }

    public void printResult(int player) {
    }

    public void enableAi(int player) {

    }

    public void play(int howMany) {
        int actualPlayer = 1;
        while (true) {
            printBoard();
            getMove(actualPlayer);

            if (lastMove != null) {
                mark(actualPlayer, lastMove[0], lastMove[1]);
            }
            if(isFull()){
                System.out.println("Board is full");
                break;
            }
            if(hasWon(actualPlayer,howMany)){
                System.out.printf("Player %s has won",actualPlayer);
                break;
            }
            actualPlayer = -actualPlayer;

        }
    }
}
