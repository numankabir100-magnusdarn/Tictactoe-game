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

    static char[] doToss(){
        char humanSymbol , aiSymbol , humanFirst ; 
        System.out.println("\nTossing.......");
        if(Math.random() < 0.5){
            humanFirst = 'Y';
            System.out.println("You won the toss! ");
            String choice;
// Asking from the human which symbol he want.
            do{
                System.out.print("\nChoose your symbol (X/O) : ");
                choice = sc.next().toUpperCase();
                if(! choice.equals("X") && ! choice.equals("O")){
                    System.out.println("Invalid Choice! Enter X or O only.");
                }
            }while(! choice.equals("X") && ! choice.equals("O"));

            if(choice.equals("X")){
                humanSymbol = 'X';
                aiSymbol = 'O';
                System.out.println("You Choose The Symbol \" "+humanSymbol+" \" AI Symbol \" "+aiSymbol+" \"");
            }
            else{
                humanSymbol = 'O';
                aiSymbol = 'X';
                System.out.println("You Choose The Symbol \" "+humanSymbol+" \" AI Symbol \" "+aiSymbol+" \"");
            }
        }
        else{
            humanFirst = 'N';
            System.out.println("AI won the toss!");
            if(Math.random() < 0.5){
                aiSymbol = 'X';
                humanSymbol = 'O';
                System.out.println("AI Choose The Symbol \" "+aiSymbol+" \" Your Symbol \" "+humanSymbol+" \"");
            }
            else{
                aiSymbol = 'O';
                humanSymbol = 'X';
                System.out.println("AI Choose The Symbol \" "+aiSymbol+" \" Your Symbol \" "+humanSymbol+" \"");
            }
        }
        char[] result = {humanSymbol, aiSymbol , humanFirst};
        return result;  
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
// Tossing.
        char [] tossResult = doToss();
        char humanSymbol = tossResult[0];
        char aiSymbol = tossResult[1];
        boolean humanFirst = (tossResult[2] == 'Y');
// Printing the board
        printBoard(board);
        if(humanFirst){
            do{
                //  Human Turn First
// Asking the row and column from the Player 1 to place human symbol at specific value.
                System.out.println("\nYour Turn!");
                System.out.print("\nEnter Row (1-3) : ");
                int row  = sc.nextInt();
                System.out.print("Enter Column (1-3) : ");
                int column = sc.nextInt();
// InputValidation.
                if(!(row>=1 && row<=3 && column>=1 && column<=3)){
                    System.out.println("Invalid Choice! Try Again. ");
                    continue;
                }
// Checking the cell is empty or not.
                if(checkEmptyCells(board, row-1, column-1)){
// Assigning the Player 1 symbol at the specific place.
                    board [row-1][column-1] = humanSymbol;
                    System.out.println("After Your Turn.");
                    printBoard(board);
// Checking that Player 1 wins or not.
                    if(checkWin(board, humanSymbol)){
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
// Assigning the AI symbol at specific place.
                    board[aiMove[0]][aiMove[1]] = aiSymbol;
                    System.out.println("\nAi Turn!\n");
                    printBoard(board);
// Checking that AI wins or not.
                    if(checkWin(board, aiSymbol)){
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
        }
        else{
            boolean round = true;
            do{
                // AI Turn First
                int [] aiMove = getBestMove(board);
// Assigning the AI symbol at specific place.
                System.out.println("\nAi Turn!\n");
                board[aiMove[0]][aiMove[1]] = aiSymbol;
                printBoard(board);
// Checking that AI wins or not.
                if(checkWin(board, aiSymbol)){
                    System.out.println("\nAI Wins!");
                    break;
                }
// Checking that game is draw or not.
                if(isDraw(board)){
                    System.out.println("\nDraw!");
                    break;
                }
                // Human Turn
                do{
// Asking the row and column from the Player 1 to place human symbol at specific value.
                    System.out.println("\nYour Turn!");
                    System.out.print("\nEnter Row (1-3) : ");
                    int row  = sc.nextInt();
                    System.out.print("Enter Column (1-3) : ");
                    int column = sc.nextInt();
// InputValidation.
                    if(!(row>=1 && row<=3 && column>=1 && column<=3)){
                        System.out.println("Invalid Choice! Try Again. ");
                        continue;
                    }
// Checking the cell is empty or not.
                    if(checkEmptyCells(board, row-1, column-1)){
// Assigning the Player 1 symbol at the specific place.
                        board [row-1][column-1] = humanSymbol;
                        System.out.println("After Your Turn.");
                        printBoard(board);
// Checking that Player 1 wins or not.
                        if(checkWin(board, humanSymbol)){
                            System.out.println("\nYou Win!");
                            round = false;
                            break;
                        }
// Checking that the game is draw or not.
                        if(isDraw(board)){
                            System.out.println("\nDraw!");
                            round = false;
                            break;
                        }
                        break;
                    }
                    else{
                        System.out.println("\nCell is Already Occupied! Try Again.");
                    }
                }while(true);
            }while(round);
        }
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
