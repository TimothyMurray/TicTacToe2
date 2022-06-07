package utilities;

import java.io.Serializable;


public class Move implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7700776279921939259L;
	private int turn;
	private int[] moves = new int[9];
	
	public Move()
	{
		this.turn = 1;
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

	
	/**Method to return the value of moves
	 * @return the moves
	 */
	public int[] getMoves()
	{
		return moves;
	}

	
	/**Method to return the value of moves
	 * @param moves the moves to set
	 */
	public void setMoves(int[] moves)
	{
		this.moves = moves;
	}
	
	
}
