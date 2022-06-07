package clientside;

import uiobjects.GameBoard;


public class Player
{
	
	private int gamePiece = GameBoard.EMPTY;
	private String name = null;
	
	public Player(String username)
	{
		
	}
	
	/**Method to return the value of gamePiece
	 * @return the gamePiece
	 */
	public int getGamePiece()
	{
		return gamePiece;
	}

	
	/**Method to return the value of gamePiece
	 * @param gamePiece the gamePiece to set
	 */
	public void setGamePiece(int gamePiece)
	{
		this.gamePiece = gamePiece;
	}

	
	/**Method to return the value of name
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	
	/**Method to return the value of name
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	
}
