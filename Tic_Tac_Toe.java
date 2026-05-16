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
    public static void main(String[] args){
        char [][] board = new char[3][3];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                board[i][j] = '_';
            }
        }
    boolean game_is_not_over = true;
    while(game_is_not_over){
         printBoard(board);

        do{
            System.out.print("\nEnter Row (1-3) : ");
            int row  = sc.nextInt();
            System.out.print("Enter Column (1-3) : ");
            int column = sc.nextInt();
            if(!(row>=1 && row<=3 && column>=1 && column<=3)){
                System.out.println("Invalid Choice! Try Again. ");
                continue;
            }
            if(board [row-1][column-1] == '_'){
                board [row-1][column-1] = 'X';

                if(checkWin(board,  'X')){
                    printBoard(board);
                    System.out.println("\nYou Win!");
                    game_is_not_over = false;
                    break;
                }
                if(isDraw(board)){
                    System.out.println("\nDraw!");
                    game_is_not_over = false;
                    break;
                }
                break;
            }
            else{
                System.out.print("\nCell is Empty! Try Again.");
            }
        }while(true);
    }

    }
}
