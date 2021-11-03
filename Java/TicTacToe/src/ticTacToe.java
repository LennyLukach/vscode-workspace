import java.util.Scanner;
import java.util.ArrayList;

class ticTacToe {
    public static void main(String[] args)  {
        
        ArrayList<Location> locations = new ArrayList<Location>();
        Scanner in = new Scanner(System.in);
        String board[][] = new String[3][3];
        boolean gameActive = true;


        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                board[x][y] = ".";
                locations.add(new Location(x, y));
            }
        }
        
        while (gameActive) {
            printBoard(board);
            if (true) {
                int pos = in.nextInt();
                
                int x = locations.get(pos - 1).getX();
                int y = locations.get(pos - 1).y;

                if (board[x][y] != ".") {
                    System.out.println("You can't go there. For being that stupid, you lose your turn.");
                }
                else {
                    board[x][y] = "x";
                }
            }
            System.out.println();


        }

        in.close();
    }

    public static void printBoard(String[][] board) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                System.out.print(board[x][y] +  " ");
            }
            System.out.println();
        }
    }

    public static boolean checkRow(String[][] board, String icon) {
        int count = 0;
        for (int x = 0; x < board.length; x++) {
            count = 0;
            for (int y = 0; y < board[0].length; y++) {
                if (board[x][y] == icon) {
                    count++;
                }
            }
            if  (count == 3) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDiag(String[][] board, String icon) {
        if (board[0][0] == icon && board[1][1] == icon && board[2][2] == icon) {
            return true;
        }
        if (board[0][2] == icon && board[1][1] == icon && board[2][0] == icon) {
            return true;
        }
        return false;
    }

    public static boolean checkCol(String[][] board, String icon) {
        int count = 0;
        for (int x = 0; x < board.length; x++) {
            count = 0;
            for (int y = 0; y < board[0].length; y++) {
                if (board[y][x] == icon) {
                    count++;
                }
            }
            if  (count == 3) {
                return true;
            }
        }
        return false;
    }

}

class Location {

    int x;
    int y;

    Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}