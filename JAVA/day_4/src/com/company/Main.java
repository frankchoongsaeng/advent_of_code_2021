package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./resources/inputfile"));
        String[] s_numbers = sc.next().split(",");
        Integer[] numbers = new Integer[s_numbers.length];
        for (int i = 0; i < s_numbers.length; ++i) numbers[i] = Integer.parseInt(s_numbers[i]);

        /* parse the boards into a multi dimensional array */
        Vector<Vector[]> allboards = new Vector<>();
        /* create a copy of all boards to be marked on */
        Vector<Vector[]> allboardsCopy = new Vector<>();
        while (sc.hasNext()) {
            Vector[] board = new Vector[5];
            Vector[] boardCopy = new Vector[5];
            for (int r = 0; r < 5; ++r) {
                board[r] = new Vector<Integer>();
                boardCopy[r] = new Vector<Integer>();
                for (int c = 0; c < 5; ++c) {
                    if (sc.hasNext()) {
                        int nxtint = sc.nextInt();
                        board[r].add(nxtint);
                        boardCopy[r].add(nxtint);
                    }
                }
            }
            allboards.add(board);
            allboardsCopy.add(boardCopy);
        }



        /*
        get the next N numbers to check for
        Loop through the boards and mark spots (setting to -1)
        after each board is marked, check if it has won
        if won, return winning board
        else keep marking
        */
        Vector[] winningBoard = new Vector[5];
        boolean foundWinningBoard;
        int numberMarker = 0;
        int sliceSize = 5;
        outside:
        while (true) {
            Integer[] numberSlice = Arrays.copyOfRange(numbers, numberMarker, sliceSize);
            for (Vector[] b : allboardsCopy) {
                for (Integer n : numberSlice) {
                    for (int r = 0; r < b.length; ++r) {
                        for (int c = 0; c < b[r].size(); ++c) {
                            if (b[r].get(c) == n) b[r].set(c, -1);
                        }
                    }
                }
                foundWinningBoard = checkBoardHasWon(b);
                if (foundWinningBoard) {
//                    winningBoard = allboards.get(allboardsCopy.indexOf(b));
                    winningBoard = b;
                    break outside;
                }

            }
            numberMarker = sliceSize++;
            if(numberMarker == numbers.length) {
                System.out.println("No winning board was found");
                break outside;
            }
        }
        printOneBoard(winningBoard);


        /*
        Add up all the unmarked numbers that appear on the board
        multiply that with the number marked at numberMarker
        print the result
         */
        int unmarkedTotal = addUnmarkedNumbers(winningBoard);
        int winningScore = unmarkedTotal * numbers[numberMarker];
        System.out.println(winningScore);
    }

    private static int addUnmarkedNumbers(Vector[] winningBoard) {
        int sum = 0;
        for (Vector<Integer> v : winningBoard) {
            for (Integer n : v) {
                sum += n.intValue() == -1 ? 0 : n.intValue();
            }
        }

        return sum;
    }

    private static boolean checkBoardHasWon(Vector[] b) {
        for (int r = 0; r < 5; ++r) {
            if (b[r].stream().allMatch(n -> n.equals(-1))) return true;
        }
        for (int c = 0; c < 5; ++c) {
            if (b[0].get(c).equals(-1) &&
                    b[1].get(c).equals(-1) &&
                    b[2].get(c).equals(-1) &&
                    b[3].get(c).equals(-1) &&
                    b[4].get(c).equals(-1)) {
                return true;
            }
        }
        return false;
    }


    public static void printBoard(Vector<Vector[]> board) {
        for (Vector[] b : board) {
            for (int r = 0; r < b.length; ++r) {
                for (int c = 0; c < b[r].size(); ++c) {
                    System.out.print(String.format("%d ", b[r].get(c)));
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void printOneBoard(Vector[] b) {
        for (int r = 0; r < b.length; ++r) {
            for (int c = 0; c < b[r].size(); ++c) {
                System.out.print(String.format("%d ", b[r].get(c)));
            }
            System.out.println();
        }
    }
}
