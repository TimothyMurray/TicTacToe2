package management;

import uiobjects.GameBoard;


public class GameManager
{
	private final int GAME_OVER_BOARD_FULL = 3;
	private final int GAME_STILL_IN_PROGRESS = 0;
	private int turn = 0;
	
	public GameManager()
	{
		
	}

	
	/**Method to return the value of turn
	 * @return the turn
	 */
	public int getTurn()
	{
		return turn;
	}

	
	/**Method to return the value of turn
	 * @param turn the turn to set
	 */
	public void setTurn(int turn)
	{
		this.turn = turn;
	}
	
	
	
	/**Method to check for a win condition.
	 * @return The piece ID of the winner (X = 1, O = 2). Returns 0 if there is no winner yet. Returns 3 if the board is full with no winner.
	 */
	public int checkIfGameOver(int[] moves)
	{
		boolean boardFull = true;
		
		//Check for win conditions
		for (int i = 0; i < 6; i++)
		{
			//Vertical column win: If the 2 squares under the first row are the same as the first square...
			if (i <= 2 && moves[i] == moves[i+3] && moves[i+3] == moves[i+6] && moves[i] != GameBoard.EMPTY)
			{
				
				return moves[i];
			}
			
			//Horizontal row win: If the 2 squares to the right of the first column are the same as the first square...
			else if (moves[i+1] == moves[i] && moves[i+2] == moves[i] && 
					( i==0 || i==3 || i==6) && (moves[i] != GameBoard.EMPTY) )
			{
				return moves[i];
			}
			
			//Diagonal win (0, 4, 8) or (2, 4, 6)
			else if ( ( (i == 0) && (moves[i] == moves[i+4]) && (moves[i] == moves[i+8])) || 
					(i == 2) && (moves[i] == moves[i+2]) && (moves[i] == moves[i+4]) && (moves[i] != GameBoard.EMPTY))
			{
				return moves[i];
			}	
			
			//Check to see if the board is full
			else if (moves[i] == GameBoard.EMPTY)
			{
				boardFull = false;
			}
		}
		
		if (boardFull == false)
			return GAME_STILL_IN_PROGRESS;
		
		else
			return GAME_OVER_BOARD_FULL;		
	}
	
	
}
