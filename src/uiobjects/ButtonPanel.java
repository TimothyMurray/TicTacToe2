package uiobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import utilities.ScreenPosition;


public class ButtonPanel
{

	private Button sendButton;
	private Button disconnectButton;
	
	private ScreenPosition s = new ScreenPosition();
	
	public ButtonPanel(GameContainer gc, Graphics g)
	{
		sendButton = new Button(gc, "buttons/yellowButtonOff.png",s.screenX(1600, gc.getWidth()), s.screenY(900, gc.getHeight()), s.screenX(200, gc.getWidth()), s.screenY(80, gc.getHeight()), "SEND" );
		disconnectButton = new Button(gc, "buttons/yellowButtonOff.png",s.screenX(20, gc.getWidth()), s.screenY(900, gc.getHeight()), s.screenX(200, gc.getWidth()), s.screenY(80, gc.getHeight()), "DISCONNECT" );
	}

	
	/**Method to return the value of sendButton
	 * @return the sendButton
	 */
	public Button getSendButton()
	{
		return sendButton;
	}

	
	/**Method to return the value of sendButton
	 * @param sendButton the sendButton to set
	 */
	public void setSendButton(Button sendButton)
	{
		this.sendButton = sendButton;
	}

	
	/**Method to return the value of disconnectButton
	 * @return the disconnectButton
	 */
	public Button getDisconnectButton()
	{
		return disconnectButton;
	}

	
	/**Method to return the value of disconnectButton
	 * @param disconnectButton the disconnectButton to set
	 */
	public void setDisconnectButton(Button disconnectButton)
	{
		this.disconnectButton = disconnectButton;
	}
	
	public void render (GameContainer gc, Graphics g, TrueTypeFont f)
	{
		sendButton.renderWithText(gc, g, f);
		disconnectButton.renderWithText(gc, g, f);
	}
}
