package main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import clientside.MainGame;
import clientside.TitleScreen;


public class Game extends StateBasedGame
{
	//Define state IDs
	public static final int titleScreen = 0;
	public static final int mainGame = 1;
	
	public Game(String gamename)
	{
		super(gamename);
		
		//Add states
		
		this.addState(new TitleScreen(titleScreen));	
		this.addState(new MainGame(mainGame));
		
		enterState(titleScreen);
							
	}



	@Override
	public void initStatesList(GameContainer gc) throws SlickException
	{
		//this.getState(titleScreen);
		this.getState(mainGame);
		this.getState(titleScreen);
		this.addState(new TitleScreen(titleScreen));
		this.addState(new MainGame(mainGame));
		
	}
}
