
class ticTacToe {
    public static void main(String[] args)  {
        
        String board[][] = new String[3][3];


        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                board[x][y] = ".";
            }
        }

        printBoard(board);
        System.out.println(checkRow(board, "x"));

        System.out.println("\n");

        board[2][0] = "x";
        board[2][1] = "x";
        board[2][2] = "x";

        printBoard(board);
        System.out.println(checkRow(board, "x"));


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
}