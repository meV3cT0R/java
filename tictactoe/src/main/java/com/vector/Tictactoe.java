package com.vector;

import java.util.Scanner;

public class Tictactoe {
    private char[][] tictactoe = {
        {' ', ' ',' '},
        {' ', ' ',' '},
        {' ', ' ',' '}
    };

    private char currentUser = 'X';
    private char winner;
    public void run() {
        Scanner scanner=null;
        while(true) {
            printBoard();
            scanner = new Scanner(System.in);
            System.out.print("Enter coordinates:");
            String move = scanner.nextLine();
            
            move(move);
            if(checkWin()) break;
        }
        printBoard();
        System.out.println("Winner : "+ winner);

    }
    public void move(String move) {
        String[] coor = move.split(" ");
        int x = Integer.parseInt(coor[0]);
        int y = Integer.parseInt(coor[1]);
        System.out.println(x);
        System.out.println(y);
        if(tictactoe[x][y] != ' ') return;
        tictactoe[x][y] = currentUser;

        if(currentUser == 'X')
            currentUser = 'O';
        else 
            currentUser = 'X';
    }
    public void printBoard() {
        for(char[] row  : tictactoe) {
            System.out.printf("| ");
            for(char column : row) {
                System.out.printf("%c |",column);
            }
            System.out.println();
        }
    }
    public boolean checkWin() {
        boolean won = false;

        /// check horizontal
        for(int i=0;i<3; i++) {
            char prev = tictactoe[i][0];
            if(prev == ' ') break;
            for(int j=0;j<3;j++) {
                if(tictactoe[i][j] != prev){
                    won =false;
                    break;
                }         
                if(j==2) won = true;
            }
            if(won){
                winner = prev;
                return won;
            } 
        }

        /// check vertical
        for(int i=0;i<3;i++) {
            char prev = tictactoe[0][i];
            if(prev == ' ') break;
            for(int j=0;j<3;j++) {
                if(tictactoe[j][i] != prev){
                    won =false;
                    break;
                } 
                if(j==2) won = true;
            }
            if(won){
                winner = prev;
                return won;
            } 
        }

        // check diagonal right
        char prev = tictactoe[0][0];
        for(int i=1;i<3;i++) {
            if(prev == ' ') break;
            if(tictactoe[i][i] != prev){
                won =false;
                break;
            }
            if(i==2){
                winner = prev;
                return true;
            } 
        }
        // check diagonal right
        prev = tictactoe[0][2];
        for(int i=1;i<3;i++) {
            if(prev == ' ') break;
            if(tictactoe[i][2-i] != prev){
                won =false;
                break;
            }
            if(i==2){
                winner = prev;
                return true;
            } 
        }
        return won;
    }

    public char getWinner() {
        return winner;
    }
    public static void main(String[] args) {
        new Tictactoe().run();
    }
}
