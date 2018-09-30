package crownandanchor;

public class Ordinary extends Bets 
{

	public Ordinary() 
	{
		super();
		_betType = 'O';
	}
	
	public Ordinary(String whatOn, int amount)
	{
		super(_betType, whatOn, amount);
		_betType = 'O';
	}

	public String displayBet() 
	{
		return ("You've placed an Ordinary bet of " + _amount + " on " + _whatOn +"!");
	}

	public int workOutWinnings(CADice[] dice) 
	{		
		int matches = 0;
		
		int winAmount = 0;
		
		for(int count = 0; count < 3; count++) //loop 3 times
		{
			if(_whatOn.equalsIgnoreCase(dice[count].getCADice()))
			{
				matches++;
			}
		}
			if (matches == 1)
			{
				winAmount =  _amount * 2;
			}
			else if (matches == 2)
			{
				winAmount =  _amount * 3;
			}
			else if (matches == 3)
			{
				winAmount =  _amount * 4;
			}
		return winAmount;
	}
}