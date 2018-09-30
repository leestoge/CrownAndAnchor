package crownandanchor;

public class AllIn extends Bets 
{
	
	public AllIn() 
	{
		super();
		_betType = 'A';
	}

	public AllIn(String whatOn,int amount) 
	{
		super(_betType,whatOn,amount);
		_betType = 'A';
	}

	public String displayBet() 
	{
		return ("You've placed an All-in bet of " + _amount + " on " + _whatOn +"!");
	}

	public int workOutWinnings(CADice[] dice) 
	{		
		int matches = 0;		
		int winAmount = 0;
		
		for(int i = 0;i < 3;i++) // loop 3 times
		{
			if(_whatOn.equalsIgnoreCase(dice[i].getCADice()))
			{
				matches++;
			}
		}
			if (matches == 3)
			{
				winAmount = (_amount * 10);
			}
		return winAmount;
	}
}