package crownandanchor;

public class DoubleOrNothing extends Bets 
{
	
	public DoubleOrNothing() 
	{
		super();
		_betType = 'D';
	}

	public DoubleOrNothing(String whatOn,int amount) 
	{
		super(_betType, whatOn,amount);
		_betType = 'D';
	}

	public String displayBet() 
	{
		return ("You've placed a Double or Nothing bet of " + _amount + " on " + _whatOn +"!");
	}

	public int workOutWinnings(CADice[] dice) {
		
		int matches=0;
		
		int winAmount=0;
		
		for(int i = 0;i < 3;i++) // loop 3 times
		{
			if(_whatOn.equalsIgnoreCase(dice[i].getCADice()))
			{
				matches++;
			}
		}
			if (matches == 2)
			{
				winAmount = (_amount + _amount * 4);
			}
			else if (matches == 3)
			{
				winAmount = (_amount + _amount * 4);
			}
		return winAmount;
	}
}