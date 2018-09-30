package crownandanchor;

public abstract class Bets 
{
	
	protected static char _betType;
	protected String _whatOn;
	protected int _amount;
	
	public Bets() {
		_betType = ' ';
		_whatOn = "";
		_amount = 0;
	}

	public Bets(char betType, String symbol, int value) 
	{
		_amount = value;
		_whatOn = symbol;
		_betType = betType;
	}
	
	public Bets(String symbol, int value)
	{
		_whatOn = symbol;
		_amount = value;
	}

	public abstract String displayBet();

	public abstract int workOutWinnings(CADice[] dice);
}