import java.util.Scanner;

public class Tic_Tac_Toe {

    static Scanner sc = new Scanner(System.in);

    static void printBoard(char[][] board){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    static boolean checkWin(char [][] board, char player){
        if(board[0][0] == player && board[0][1] == player && board[0][2] == player){
            return true;
        }
        else if(board[1][0] == player && board[1][1] == player && board[1][2] == player){
            return true;
        }
        else if(board[2][0] == player && board[2][1] == player && board[2][2] == player){
            return true;
        }
        else if(board[0][0] == player && board[1][0] == player && board[2][0] == player){
            return true;
        }
         else if(board[0][1] == player && board[1][1] == player && board[2][1] == player){
            return true;
        }
         else if(board[0][2] == player && board[1][2] == player && board[2][2] == player){
            return true;
        }
         else if(board[0][0] == player && board[1][1] == player && board[2][2] == player){
            return true;
        }
         else if(board[0][2] == player && board[1][1] == player && board[2][0] == player){
            return true;
        }
        return false;
    }

    static boolean isDraw(char [][] board ){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j] == '_'){
                    return false;
                }
            }
        }
        return true;
    }
    
    static boolean checkEmptyCells(char [][] board, int row , int column){
        if(board[row][column] == '_'){
            return true;
        }
        return false;
    }
    
    static int minimax(char [][] board, boolean isAI){
        if(checkWin(board, 'O')){
            return +10;
        }
        if(checkWin(board, 'X')){
            return -10;
        }
        if(isDraw(board)){
            return 0;
        }
        int best;
        if(isAI){
            best = -100;
        }
        else{
            best = +100;
        }
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j] == '_'){
                    if(isAI){
                        board[i][j] = 'O';
                    }
                    else{
                        board[i][j] = 'X';
                    }
                    int score = minimax(board, ! isAI);

                    board[i][j] = '_';
                    if(isAI){
                        if(score>best){
                            best = score;
                        }
                    }
                    else{
                        if(score<best){
                            best = score;
                        }
                    }
                }
            }
        }
        return best;                          
    }

    static int [] getBestMove(char[][] board){
        int bestScore = -100;
        int bestRow = -1;
        int bestColumn = -1;

        
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i][j] == '_'){
                    board[i][j] = 'O';
                    int score = minimax(board, false);
                    board[i][j] = '_';

                    if(score>bestScore){
                        bestScore = score;
                        bestRow = i;
                        bestColumn = j; 
                    }
                }
            }
        }
        int [] bestMove = {bestRow,bestColumn};
        return bestMove;
    }

    static char [][] board = new char[3][3];
    
    public static void main(String[] args){

    boolean game_is_not_over = true;

    while(game_is_not_over){
// Assigning '_' in all cells of 2-D array.
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                board[i][j] = '_';
            }
        }
// Printing the board
         printBoard(board);

        do{
            //    Human Turn
// Asking the row and column from the Player 1 to place 'X' at specific value.
            System.out.println("\nYour Turn!");
            System.out.print("\nEnter Row (1-3) : ");
            int row  = sc.nextInt();
            System.out.print("Enter Column (1-3) : ");
            int column = sc.nextInt();

// Input Validation.
            if(!(row>=1 && row<=3 && column>=1 && column<=3)){
                System.out.println("Invalid Choice! Try Again. ");
                continue;
            }
// Checking the cell is empty or not.
            if(checkEmptyCells(board, row-1, column-1)){
// Assigning the Player 1 symbol 'X' at the specific place.
                board [row-1][column-1] = 'X';
                System.out.println("After Your Turn.");
                printBoard(board);
// Checking that Player 1 wins or not.
                if(checkWin(board,  'X')){
                    System.out.println("\nYou Win!");
                    break;
                }
// Checking that the game is draw or not.
                if(isDraw(board)){
                    System.out.println("\nDraw!");
                    break;
                }

                // AI Turn  
                int [] aiMove = getBestMove(board);
// Assigning the AI symbol 'O' at specific place.
                board[aiMove[0]][aiMove[1]] = 'O';
                System.out.println("\nAi Turn!\n");
                printBoard(board);
// Checking that AI wins or not.
                if(checkWin(board, 'O')){
                    System.out.println("\nAI Wins!");
                    break;
                }
// Checking that game is draw or not.
                if(isDraw(board)){
                    System.out.println("\nDraw!");
                    break;
                }
            }
            else{
                System.out.print("\nCell is Already Occupied! Try Again.");
            }
        }while(true);
// Asking from the user that he/she want to play again or not.
        do{
            System.out.print("\nDo you want to play again (y/n) : ");
            String input = sc.next().toLowerCase();
// Input Validation.
            if(input.equals("y")){
            System.out.println("\nNew Game.\n");
                break;
            }
            else if(input.equals("n")){
                System.out.println("\nGood Bye!\n");
                game_is_not_over = false;
                break;
            }
            else{
                System.out.println("Invalid Choice! Try Again.");
            }
        }while (true);
    }
    }
}
