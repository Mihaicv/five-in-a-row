package com.codecool.fiveinarow;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getMove(int player) {
        while (true) {
            System.out.printf("Player %s move: ", player == -1 ? "player 2" : "player 1");
            String line = scanner.nextLine();

            if(line.length()==1){
                System.out.println("Please insert a valid move");
            }else if (!Character.isDigit(line.charAt(1)) || line.length() != Integer.parseInt(String.valueOf(line.substring(1).length())) + 1 ||
                    Integer.parseInt(line.substring(1)) > board[0].length || !Character.isLetter(line.charAt(0)) || Character.getNumericValue(line.charAt(1)) < 1 ||
                    (Character.toString(line.charAt(0)).toUpperCase().charAt(0)) - 65 >= board.length) {
                System.out.println("Enter a valid move");
            } else {
                lastMove[1] = Integer.parseInt(line.substring(1)) - 1;
                lastMove[0] = (int) (Character.toString(line.charAt(0)).toUpperCase().charAt(0)) - 65;
                if (board[lastMove[0]][lastMove[1]] == 1 || board[lastMove[0]][lastMove[1]] == 2) {
                    System.out.println("This place is ocupied");
                    continue;
                }
                break;
            }
        }
        return lastMove;
    }

    public int[] getAiMove(int player,int player2) throws InterruptedException {
        System.out.println("Computer turn");
        TimeUnit.SECONDS.sleep(1);
        if (thinkMove(player,4)==null && thinkMove(player2,4)==null){
            if (thinkMove(player,3)!=null){
                return lastMove=thinkMove(player,3);
            }
            else if(thinkMove(player2,3)!=null ){
                return lastMove=thinkMove(player2,3);
            }
            else{
                if(thinkMove(player,2)!=null){
                    return lastMove=thinkMove(player,2);
                }
                else if(thinkMove(player2,2)!=null){
                    return lastMove=thinkMove(player2,2);
                }
                else{
                    if(thinkMove(player,1)!=null){
                        return lastMove=thinkMove(player,1);
                    }
                }
            }
        }
        else if(thinkMove(player,4)!=null){
            return lastMove=thinkMove(player,4);
        }
        else{
            return lastMove=thinkMove(player2,4);
        }
        Random rand = new Random();
        return lastMove= new int[]{rand.nextInt(board.length + 1), rand.nextInt(board.length + 1)};

    }

    public int[] thinkMove(int player,int occurences){
        //check for win on horizontal lines
        for (int i=0;i< board.length;i++){
            for (int j=0;j<board[i].length;j++){
                List<Integer> seq= new ArrayList<>();
                try {
                    seq.add(board[i][j]);
                    seq.add(board[i][j + 1]);
                    seq.add(board[i][j + 2]);
                    seq.add(board[i][j + 3]);
                    seq.add(board[i][j + 4]);
                }
                catch (Exception e){
                    continue;
                }
                if (Collections.frequency(seq,player)==occurences){
                    if(board[i][j]==0){
                        lastMove[0]=i;
                        lastMove[1]=j;
                        return lastMove;
                    }
                    else if(board[i][j+1]==0){
                        lastMove[0]=i;
                        lastMove[1]=j+1;
                        return lastMove;
                    }
                    else if(board[i][j+2]==0){
                        lastMove[0]=i;
                        lastMove[1]=j+2;
                        return lastMove;
                    }
                    else if(board[i][j+3]==0){
                        lastMove[0]=i;
                        lastMove[1]=j+3;
                        return lastMove;
                    }
                    else if(board[i][j+4]==0){
                        lastMove[0]=i;
                        lastMove[1]=j+4;
                        return lastMove;
                    }

                }

            }
        }
        //check for win on vertical lines
        for (int i=0;i< board.length;i++){
            for (int j=0;j<board[i].length;j++){
                List<Integer> seq= new ArrayList<>();
                try {
                    seq.add(board[i][j]);
                    seq.add(board[i+1][j]);
                    seq.add(board[i+2][j]);
                    seq.add(board[i+3][j]);
                    seq.add(board[i+4][j]);
                }
                catch (Exception e){
                    continue;
                }
                if (Collections.frequency(seq,player)==occurences){
                    if(board[i][j]==0){
                        lastMove[0]=i;
                        lastMove[1]=j;
                        return lastMove;
                    }
                    else if(board[i+1][j]==0){
                        lastMove[0]=i+1;
                        lastMove[1]=j;
                        return lastMove;
                    }
                    else if(board[i+2][j]==0){
                        lastMove[0]=i+2;
                        lastMove[1]=j;
                        return lastMove;
                    }
                    else if(board[i+3][j]==0){
                        lastMove[0]=i+3;
                        lastMove[1]=j;
                        return lastMove;
                    }
                    else if(board[i+4][j]==0){
                        lastMove[0]=i+4;
                        lastMove[1]=j;
                        return lastMove;
                    }

                }

            }
        }
        //check win on \ diagonals
        for (int i=0;i< board.length;i++){
            for (int j=0;j<board[i].length;j++){
                List<Integer> seq= new ArrayList<>();
                try {
                    seq.add(board[i][j]);
                    seq.add(board[i+1][j + 1]);
                    seq.add(board[i+2][j + 2]);
                    seq.add(board[i+3][j + 3]);
                    seq.add(board[i+4][j + 4]);
                }
                catch (Exception e){
                    continue;
                }
                if (Collections.frequency(seq,player)==occurences){
                    if(board[i][j]==0){
                        lastMove[0]=i;
                        lastMove[1]=j;
                        return lastMove;
                    }
                    else if(board[i+1][j+1]==0){
                        lastMove[0]=i+1;
                        lastMove[1]=j+1;
                        return lastMove;
                    }
                    else if(board[i+2][j+2]==0){
                        lastMove[0]=i+2;
                        lastMove[1]=j+2;
                        return lastMove;
                    }
                    else if(board[i+3][j+3]==0){
                        lastMove[0]=i+3;
                        lastMove[1]=j+3;
                        return lastMove;
                    }
                    else if(board[i+4][j+4]==0){
                        lastMove[0]=i+4;
                        lastMove[1]=j+4;
                        return lastMove;
                    }

                }

            }
        }
        //check win on / diagonals
        for (int i=0;i< board.length;i++){
            for (int j=0;j<board[i].length;j++){
                List<Integer> seq= new ArrayList<>();
                try {
                    seq.add(board[i][j]);
                    seq.add(board[i-1][j + 1]);
                    seq.add(board[i-2][j + 2]);
                    seq.add(board[i-3][j + 3]);
                    seq.add(board[i-4][j + 4]);
                }
                catch (Exception e){
                    continue;
                }
                if (Collections.frequency(seq,player)==occurences){
                    if(board[i][j]==0){
                        lastMove[0]=i;
                        lastMove[1]=j;
                        return lastMove;
                    }
                    else if(board[i-1][j+1]==0){
                        lastMove[0]=i-1;
                        lastMove[1]=j+1;
                        return lastMove;
                    }
                    else if(board[i-2][j+2]==0){
                        lastMove[0]=i-2;
                        lastMove[1]=j+2;
                        return lastMove;
                    }
                    else if(board[i-3][j+3]==0){
                        lastMove[0]=i-3;
                        lastMove[1]=j+3;
                        return lastMove;
                    }
                    else if(board[i-4][j+4]==0){
                        lastMove[0]=i-4;
                        lastMove[1]=j+4;
                        return lastMove;
                    }

                }

            }
        }
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
        return count == howMany;
    }

    public boolean isFull() {
        int count = 0;
        for (int[] ints : board) {
            for (int anInt : ints)
                if (anInt == 0)
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

    public void play(int howMany) throws InterruptedException {
        int actualPlayer = 1;
        String ans;
        while (true) {
            System.out.println("Please choose your game mode!");
            System.out.println("1. Player vs Player");
            System.out.println("2. Ai vs Player");
            System.out.println("3. Player vs Ai");
            ans = scanner.nextLine();
            try{
                if (Integer.parseInt(ans)<4 && Integer.parseInt(ans)>0){
                    break;
                }
                else{
                    System.out.println("Choose a valid option!");
                }
            }
            catch (Exception e){
                System.out.println("Choose a valid option!");
            }

        }
        label:
        while (true) {

            printBoard();
            if (ans.equals("1") || ans.equals("3")) {
                getMove(actualPlayer);
            }
            else if (ans.equals("2")){
                getAiMove(actualPlayer,-actualPlayer);
            }
            mark(actualPlayer, lastMove[0], lastMove[1]);
            printBoard();
            if (hasWon(actualPlayer, howMany)) {
                switch (ans) {
                    case "1":
                        System.out.println("Player 1 has won");
                        break label;
                    case "2":
                        System.out.println("The Ai has won");
                        break label;
                    case "3":
                        System.out.println("The Human Player has won");
                        break label;
                }

            }
            if (ans.equals("1") || ans.equals("2")) {
                getMove(-actualPlayer);
            }
            else if (ans.equals("3")){
                getAiMove(-actualPlayer,actualPlayer);
            }
            mark(-actualPlayer, lastMove[0], lastMove[1]);
            if (hasWon(-actualPlayer, howMany)) {
                switch (ans) {
                    case "1":
                        System.out.println("Player 2 has won");
                        break label;
                    case "2":
                        System.out.println("The Human Player has won");
                        break label;
                    case "3":
                        System.out.println("The Ai has won");
                        break label;
                }
            }
            if (isFull()) {
                System.out.println("Board is full. Is a TIE");
                break;
            }


        }
        while(true) {
            System.out.println("Play Again?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            ans = scanner.nextLine();
            try{
                if (Integer.parseInt(ans)<3 && Integer.parseInt(ans)>0){
                    if (ans.equals("1")) {
                        for (int[] row : board) {
                            Arrays.fill(row, 0);
                        }
                        this.setBoard(board);
                        play(5);
                    }
                    else if (ans.equals("2")){
                        System.out.println("TEAM TEAM THANKS YOU FOR PLAYING THE GAME!!!");
                        break;
                    }
                }
                else{
                    System.out.println("Choose a valid option!");
                }
            }
            catch (Exception e){
                System.out.println("Choose a valid option!");
            }
        }
    }
}
