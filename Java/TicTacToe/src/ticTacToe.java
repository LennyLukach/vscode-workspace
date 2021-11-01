
class ticTacToe {
    public static void main(String[] args)  {
        
        String board[][] = new String[3][3];


        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                board[x][y] = ".";
            }
        }

        printBoard(board);


    }

    public static void printBoard(String[][] board) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                System.out.print(board[x][y]);
            }
            System.out.println();
        }
    }
}