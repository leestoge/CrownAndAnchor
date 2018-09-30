package crownandanchor;

import java.io.Serializable;

public class Player implements Serializable 
{

	private static final long serialVersionUID = 1L;
	
	private String _name;
	private int _stake;
	private int _banker;
	private int _roundNo;

	
	public Player()
	{
		setName((""));
	}
	
	public Player(String playername, int playerstake)
	{
		_name = playername;
		_stake = playerstake;
		_banker = 100;
		_roundNo = 0;
	}
	
	public void setName(String name)
	{
	    _name = name;
	}
	
    public String getName() 
    {
        return _name;
    }
	
	public void incStake(int playerstake)
	{
		_stake += playerstake;
	}
	
	public void decStake(int playerstake)
	{
		_stake -= playerstake;
	}
	
	public void incBanker(int playerstake)
	{
		_banker=_banker + playerstake;
	}
	public void decBanker(int playerstake)
	{
		_banker=_banker - playerstake;
	}
	
	public void setBanker(int banker) {
		_banker = banker;
	}
    
    public int getStake()
    {
        return _stake;
    }
    
    public int getBanker() 
    {
        return _banker;
    }
    
    public void incRound(int a) 
	{
    	_roundNo += a;
	}
    
    public int getRound() 
	{
    	return _roundNo;
	}
}
