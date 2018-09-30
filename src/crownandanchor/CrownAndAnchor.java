package crownandanchor;

public class CrownAndAnchor 
{

	public static void main(String[] args) 
	{
		Game app = new Game();
		try 
		{
			app.play();	//try to run the game
		} 
		
		catch (Exception e)
		{
			System.out.println("ERROR - can't run application"); //if it can't - display error.
		}
	}
}