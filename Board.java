//public class Board implements BoardADT{
public class Board {
    private char[][] theBoard; // instance variable to store the game board. 
    private int empty_positions;  //the number of positions on the board that must remain empty
    private int max_levels; // specifies the playing quality of the program
    private int board_size; // specifies the size of the game-board


    public Board (int board_size, int empty_positions, int max_levels) {
    	
    	theBoard = new char[board_size][board_size];
    			
        this.empty_positions = empty_positions;
        this.max_levels = max_levels;
        this.board_size = board_size;

       //Make every entry of theBoard store ’e’. 
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                theBoard[i][j] = 'e';
            }
        }
    }

    public Dictionary makeDictionary() {
    	
    	//return an empty Dictionary of the size selected (9967).
    	return new Dictionary(9967);
    }
    
    public int repeatedLayout(Dictionary dict) {

        String s = ""; 
        
        //represent the content of the 2-dimensional array theBoard as a String s 
        for(int i = 0; i < theBoard.length; i++)
        {
            for(int j = 0; j < theBoard.length; j++)
            {
                s += theBoard[i][j];
            }
        }
        
        //check whether there is a data item in the dictionary referenced by dict with key s
        
        //If there is such a data item, this method returns the associated score
        if((dict.getScore(s)) != -1) {
            return dict.getScore(s);
        }
        
        //otherwise return the value -1.
        else {
            return -1;
        }

    }

    public void storeLayout(Dictionary dict, int score) {
        String s = "";

        try {
        	// represent the content of theBoard as a String s 
            for(int i = 0; i < theBoard.length; i++)
            {
                for(int j = 0; j < theBoard.length; j++)
                {
                    s += theBoard[i][j];
                }
            }
            
            //create a Layout object storing s and score 

            Layout data = new Layout(s, score);

            //Store the object in dict (the Dictionary).
            dict.put(data);

        } catch (DictionaryException e) {} // might throw exception if data is already in the dictionary
    }

    public void saveTile(int row, int col, char symbol) {
        
    	//stores symbol in the tile of theBoard[row][col]
    	theBoard[row][col] = symbol;
    }

	public boolean positionIsEmpty (int row, int col)
    {
		
		//check of the tile in theBoard[row][col] is ’e’ (empty) 
        if(theBoard[row][col] == 'e')
        {
        	//returns true if the tile is empty
            return true;
        }
        
        //otherwise it returns false (not empty)
        else {
            return false;
        }
    }

    public boolean isComputerTile (int row, int col) {
    	
    	//check of the tile in theBoard[row][col] is ’c’ (tile taken by the computer) 
        if(theBoard[row][col] == 'c')
        {
        	//returns true if the tile is played by the computer 
            return true;
        }
        
        //otherwise it returns false (not taken by the computer)
        else {
            return false;
        }

    }

    public boolean isHumanTile (int row, int col) {
    	
    	//check of the tile in theBoard[row][col] is ’h’ (tile taken by the human) 

        if(theBoard[row][col] == 'h')
        {
        	//returns true if the tile is played by the human 
            return true;
        }
        
        else {
            //otherwise it returns false (not taken by the human)
            return false;
        }

    }

    public boolean winner (char symbol) {
        
    	int count = 0;

        //check for rows in the board
        
        //Check each tile in each row 
        for(int i = 0; i < theBoard.length ; i++)
        {
            count = 0;
            
            for(int j = 0; j < theBoard.length; j++)
            {
            	//If the tile has a symbol
                if(theBoard[i][j] == symbol)
                {
                	//Increment the counter that checks if there are n adjacent tiles of type symbol in the same row
                    count++;
                }
                
                //if there are n (size of the game-board) adjacent tiles of type symbol in the same row then return true
                if(count == board_size) {
                    return true;
                }
            }
        }

        //check for columns in the board
        
        //Check each tile in each column 
        for(int i = 0; i < theBoard.length ; i++)
        {
            count = 0;
            for(int j = 0; j < theBoard.length; j++)
            {
            	//If the tile has a symbol
                if(theBoard[j][i] == symbol)
                {
                	//Increment the counter that checks if there are n adjacent tiles of type symbol in the same column
                    count++;
                }
                
                //if there are n (size of the game-board) adjacent tiles of type symbol in the same column then return true
                if(count == board_size) {
                    return true;
                }
            }
        }

        /*CHECK FOR THE DIAGONALS*/
        
        // Check right bottom to left top diagonal

		int LeftDiaginal = 0;
		
		//Check each tile in the right bottom to left top diagonal
		for(int i = 0; i < board_size; i++) {
        	
			//If the tile has a symbol
			if(theBoard[i][i] == symbol) 
			{
            	//Increment the counter that checks if there are n adjacent tiles of type symbol in the same diagonal
				LeftDiaginal++;
			}
			
            //if there are n (size of the game-board) adjacent tiles of type symbol in the same diagonal then return true
			if(LeftDiaginal == board_size) 
				return true;
		}

        // Check left bottom to right top diagonal
		int RighDiagonal = 0;
		
		//Check each tile in the left bottom to right top diagonal
		for(int i = board_size - 1, j = 0; j < board_size; i--, j++) {
			
			//If the tile has a symbol
			if(theBoard[i][j] == symbol) 
			{
            	//Increment the counter that checks if there are n adjacent tiles of type symbol in the same diagonal
				RighDiagonal++;
			}
			
            //if there are n (size of the game-board) adjacent tiles of type symbol in the same diagonal then return true
			if(RighDiagonal == board_size) 
				return true;
		}
		
		// if there are no n adjacent tiles of the symbol in the same row, column, or diagonal of theBoard
		//Then return false
        return false;
    }

    public boolean isDraw(char symbol, int empty_positions) {
    	
    	int empty = 0;
    	
    	//Check for empty positions left on the game board
    	for(int i = 0; i < board_size; i++)
    	{
    		for(int j = 0; j < board_size; j++)
    		{
    			//If the tile in the board is "e" then it means it is empty
    			if(theBoard[i][j] == 'e')
    			{
    				//Therefore, increments the counter that identifies the # of empty positions left on the game board
    				empty++;
    			}
    		}
    	}
    	
    	//The game is a draw if empty positions = 0 and there are no empty positions left on the game board
    	
    	//if empty positions is 0 and there are no empty positions left on the board 
        if(empty_positions == 0 && empty == 0)
        {
        	//then return true to indicate that the game is a draw.
            return true;
        }
        
        //if no player has won, empty positions > 0, the number of empty positions on the game-board is equal to empty positions
        if(winner(symbol) == false && empty_positions > 0 && empty == empty_positions) {
        	
        	//check none of the empty positions on the game-board has a tile of the type specified by symbol adjacent to it.
        	for(int i = 0; i < board_size; i++)
        	{
        		for(int j = 0; j < board_size; j++)
        		{
        			//If the tile is empty then check all the adjacent tiles to it
        			if(theBoard[i][j] == 'e' )
        			{
        				//if the empty positions on the game-board has a tile of the type specified by symbol adjacent to it 
        				//then return false indicating that the game is not a draw.
        				
        				try { // Use try - catch to avoid referencing a position outside the array and causing an Exception.
        					if(theBoard[i+1][j] == symbol) 
        						return false;
        				}catch (IndexOutOfBoundsException e) {}
        					
    					try {
    						if(theBoard[i-1][j] == symbol) 
    							return false;
    					}catch (IndexOutOfBoundsException e) {}
    					
    					try{
    						if(theBoard[i][j+1] == symbol) 
    							return false;
    					}catch (IndexOutOfBoundsException e) {}

    					try {
    						if(theBoard[i][j-1] == symbol) 
    							return false;
    					}catch (IndexOutOfBoundsException e) {}
    					
    					try {
    						if(theBoard[i+1][j+1] == symbol) 
    							return false;
    					}catch (IndexOutOfBoundsException e) {}
						
    					try {
							if(theBoard[i-1][j-1] == symbol) 
								return false;
    					}catch (IndexOutOfBoundsException e) {}
    					
    					try {
    						if(theBoard[i+1][j-1] == symbol) 
    							return false;
    					}catch (IndexOutOfBoundsException e) {}

    						
    					try {
    						if(theBoard[i-1][j+1] == symbol) 
    							return false;
    					}catch (IndexOutOfBoundsException e) {}
        			}
        		}	
        	}
        	
        	/*if empty positions > 0, the number of empty positions on the game-board is equal to
        	empty positions and none of the empty positions on the game-board has a tile of the
        	type specified by symbol adjacent to it.
        	
        	Then return true indicating that the game is a draw */
				return true;
        }

        return false;
    }

    public int evaluate(char symbol, int empty_positions) {
        
    	//if the computer has won
    	if(winner('c'))
        {
    		//return 3
            return 3;
        }
    	
    	//if the human player has won
        else if(winner('h'))
        {
        	//return 0
            return 0;
        }
    	
    	//if the game is a draw
        else if(isDraw(symbol, empty_positions))
        {
        	//return 2
            return 2;
        }
    	
    	//if the game is still undecided
        else
        {
        	//return 1
            return 1;
        }
    }
}


