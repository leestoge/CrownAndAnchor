package crownandanchor;

public class Dice 
{

	private int diceFace; //store value of dice face
			
	public Dice()
	{
		throwDice();
	}
	
	public Dice(int value) 
	{
		diceFace = value;
	}

	public void throwDice()
	{
		double randomNo=Math.random(); 
		randomNo = 6 * randomNo;
		randomNo++;
		diceFace = (int) randomNo;
	}
	
	public int getDice()
	{
		return diceFace;
	}
}