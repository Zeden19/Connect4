/*
 * Author: Arjun Sharma (251240847)
 * This class evaluates the gameboard through a series of checks
 * Date: October 19, 2022
 */
public class Evaluate {

    private int size;
    private int tilesToWin;
    private int maxLevels;
    private char[][] gameBoard;


    /**
     * Constructor class, initializing the gameboard
     * @param size: Size of the gameboard on both columns and rows
     * @param tilesToWin: The number of tiles in a row (horizontally, vertically, diagonally) you need to get to win
     * @param maxLevels: Unused Variable
     */
    public Evaluate(int size, int tilesToWin, int maxLevels) {
        this.size = size;
        this.tilesToWin = tilesToWin;
        this.maxLevels = maxLevels;

        // initializing gameBoard with all values being e
        gameBoard = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameBoard[i][j] = 'e';
            }
        }
    }

    /**
     * Creates a new dictionary class that stores the data
     * @return A new empty dictionary that has 9887 index values
     */
    public Dictionary createDictionary() {
        return new Dictionary(9887);
    }

    /**
     * Checks if a record exists in the inputted dictionary that contains the current gameState
     * @param dict: dictionary that we check to see if it contains the gameState
     * @return The Record object that contains the gamestate or null if no Record exists that has the gamestate
     */
    public Record repeatedState(Dictionary dict) {
        String gameState = gameStateCreate();
        return dict.get(gameState);
    }

    /**
     * Stores a new Record object in the inputted dictionary
     * @param dict: The dictionary that will contain the new Record object
     * @param score: Score of the Record object
     * @param level: Level of the Record object
     */
    public void insertState(Dictionary dict, int score, int level) {
        String gameState = gameStateCreate();
        Record record = new Record(gameState, score, level);
        dict.put(record);
    }

    /**
     * Stores symbol entered at the specific location in gameboard
     * @param row: Row where the symbol should go
     * @param col: Column where the symbol should go
     * @param symbol: Symbol to be entered in the row and column
     */
    public void storePlay(int row, int col, char symbol) {gameBoard[row][col] = symbol;}

    /**
     * Checks if the square is empty at the inputted row and column
     * @param row: Row of check
     * @param col: Column of check
     * @return false if location on gameboard is not empty, true if it is empty
     */
    public boolean squareIsEmpty (int row, int col) {
        return gameBoard[row][col] == 'e';
    }

    /**
     * Checks if square is a computer square at the inputted row and column
     * @param row: Row of check
     * @param col: Column of check
     * @return false if location on gameboard is not computer, true if it is computer
     */
    public boolean tileOfComputer (int row, int col) {
        return gameBoard[row][col] == 'c';
    }

    /**
     * Checks if square is a human square at the inputted row and column
     * @param row: Row of check
     * @param col: Column of check
     * @return false if location on gameboard is not human, true if it is human
     */
    public boolean tileOfHuman (int row, int col) {
        return gameBoard[row][col] == 'h';
    }

    /**
     * Method to see if the inputted player has won
     * @param symbol: The player (human or computer) has won the game
     * @return true if the inputted player has won, false if they did not win
     */
    public boolean wins (char symbol) {
        int horizontal = 0;
        int vertical = 0;
        int diagonal = 0;
        String gameState = gameStateCreate();

        // checking horizontally by analyzing each row to see if it has enough consecutive symbols
        for (int i = 0; i < size; i++) {
            horizontal = 0; // Resetting horizontal after each row to check for another row
            for (int j = 0; j < size; j++) {
                if (gameBoard[i][j] == symbol) {horizontal++;}
                else {horizontal = 0;} // Resetting horizontal value if symbol was not found
                if (horizontal == tilesToWin) {return true;} // Returning True if a horizontal win
            }

        }
        // check vertical by analyzing each columns to see if it has enough consecutive symbols
        for (int i = 0; i < gameState.length(); i++) {
            if (i % size == 0) {vertical = 0;} // Resetting vertical check after each column
            if (gameState.charAt(i) == symbol) {vertical++;}
            else {vertical = 0;} // Resetting vertical check if symbol was not found
            if (vertical == tilesToWin) {return true;} // Returning True if a vertical win

        }

        // Checking horizontal by analyzing each diagonal from top left and bottom left

        // First check: From top left to the bottom right of the middle
        for (int i = 0; i <= size - tilesToWin; i++) {
            diagonal = 0;
            int row = i;
            for (int j = 0; j < size && row < size; j++) {
                if (gameBoard[row][j] == symbol) {diagonal++;}
                else {diagonal = 0;} // resetting diagonal if symbol was not found
                if (diagonal == tilesToWin) {return true;} // Return True if diagonal win
                row++; // continue checking diagonal by incrementing temporary variable in nested for loop
            }
        }

        // Second check: From top left middle to bottom right of the game
        for (int i = 1; i <= size - tilesToWin; i++) {
            diagonal = 0;
            int col = i;
            for (int j = 0; j < size && col < size; j++) {
                if (gameBoard[j][col] == symbol) {diagonal++;}
                else {diagonal = 0;} // resetting diagonal if symbol was not found
                if (diagonal == tilesToWin) {return true;} // Return True if diagonal win
                col++; // continue checking diagonal by incrementing temporary variable in nested for loop
            }
        }

        // Third Check: From the bottom left to the top right of the middle
        for (int i = size - 1; i >= size - tilesToWin - 1; i--) {
            diagonal = 0;
            int row = i;
            for (int j = 0; j < size && row < size && row >= 0; j++) {
                if (gameBoard[row][j] == symbol) {diagonal++;}
                else {diagonal = 0;} // resetting diagonal if symbol was not found
                if (diagonal == tilesToWin) {return true;} // Return True if diagonal win
                row--; // continue checking diagonal by decrementing temporary variable in nested for loop
            }
        }

        // Fourth Check: From bottom left of the middle to top right of the game
        for (int i = 1; i < size; i++) {
            diagonal = 0;
            int col = i;
            for (int j = size - 1; j < size && j >= 0 && col < size & col >= 1; j--) {
                if (gameBoard[j][col] == symbol) {diagonal++;}
                else {diagonal = 0;} // resetting diagonal if symbol was not found
                if (diagonal == tilesToWin) {return true;} // Return True if diagonal win
                col++; // continue checking diagonal by incrementing temporary variable in nested for loop
            }
        }
        return false;
    }

    /**
     * Checking if a draw was found by seeing if any squares are empty
     * @return false if a empty square was found, true if a empty square was not found
     */
    public boolean isDraw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (squareIsEmpty(i,j)) return false;
            }
        }
        return true;
    }

    /**
     * Evaluating the board by calling wins() twice, one for the human and one for the computer, and checking for a draw
     * @return 3 if computer won, 0 if human won, 2 if a draw, and 1 if the game is not finished
     */
    public int evalBoard() {
        if (wins('c')) return 3;
        if (wins('h')) return 0;
        if (isDraw()) return 2;
        else return 1;
    }

    /**
     * Creating String representation gamestate for the current board in this class
     * @return String representation of gamestate for current board
     */
    private String gameStateCreate() {
        StringBuilder gameState = new StringBuilder();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameState.append(gameBoard[j][i]); // Sting representation is top to bottom, left to right
            }
        }
        return String.valueOf(gameState);
    }

}


