package crownandanchor;
import java.io.*;
import java.util.ArrayList;


public class Game 
{
	
	private CADice[] dice;
	private ArrayList<Bets> bets;
	private Player aPlayer;
	private UserInterface ui = new UserInterface();
		
	public void play() throws ClassNotFoundException, IOException
	{
		
		aPlayer = restartGame();
		boolean cont = false;
		char roundResult;
		
		do 
		{			
			aPlayer.incRound(1);
			placeBets();
			rolldice();
			ui.displayMessageln("Rolling dice...");
			ui.displayMessageln("Dice 1:- " + dice[0].getCADice());
			ui.displayMessageln("Dice 2:- " + dice[1].getCADice());
			ui.displayMessageln("Dice 3:- " + dice[2].getCADice());

			int winnings = workOutWinnings(dice);
			updatePlayerStake(0, winnings);			
			System.out.println("");
			ui.displayMessageln("You won: £"+winnings);
			ui.displayMessageln("Banker now has: £" + aPlayer.getBanker());
			ui.displayMessageln("Stake is now at: £" + aPlayer.getStake());
			System.out.println("");
		
			roundResult = endRound();//checks if banker, stake == 0
			
			if(roundResult == 'c' && ui.getAnswer("Play another round? (Y/N)") == 'y') //if game not over and user wants to play another round
			{ 
				cont = true;
			}
			else 
			{
				cont = false;
			}		
		}while (cont);
		
		if(roundResult == 'c') //if user doesn't want to continue continue and game not over
		{
			saveGame(aPlayer); // save their data
		}	
	}
	
	private Player restartGame() throws IOException, ClassNotFoundException 
	{
		String name = ui.getPlayerName();
		
		try 
		{
			
			FileInputStream inFile = new FileInputStream(name + ".ser");
			ObjectInputStream inPlayer = new ObjectInputStream(inFile);
			aPlayer = (Player) inPlayer.readObject();
			inPlayer.close();
			inFile.close();
			ui.displayMessageln("Previous game found for this player with:"
							+ "\nStake: £" + aPlayer.getStake()
							+ "\nBanker: £" + aPlayer.getBanker()
							+ "\nRounds played: " + aPlayer.getRound());
			if(ui.getAnswer("Load this game? (Y/N)") == 'n') 
			{
				aPlayer = new Player(name, ui.getPlayerStake());
			}
			else 
			{
				ui.displayMessageln(aPlayer.getName() + "'s game loaded.\nStake: " + aPlayer.getStake() + "\nBanker: " + aPlayer.getBanker() + "\nRounds played: " + aPlayer.getRound());
			}
		} catch (FileNotFoundException e) 
		{
			ui.displayMessageln("No save file found for this name");
			ui.displayMessageln("Creating new save game...");
			aPlayer = new Player(name, ui.getPlayerStake());
		}
		catch (IOException | ClassNotFoundException e) 
		{
			ui.displayMessageln("No save file found for this name");
			ui.displayMessageln("Creating new save game...");
			aPlayer = new Player(name, ui.getPlayerStake());
		}
		return aPlayer;	
	}
	
	private int workOutWinnings(CADice[] dice) 
	{
		int winnings = 0;

		for (int i=0; i < bets.size(); i++) 
		{			
			Bets temp = bets.get(i);
			winnings = temp.workOutWinnings(dice);
		}		
		return winnings;
	}
		
	private void placeBets() //get bets and add them to array
	{
		
		bets = new ArrayList<Bets>(); //initialise array every time method called
		
		char proceed = 'y';
		
		do //get bet details from user
		{			
			char betType = ui.getBetType();
			String whatOn = ui.getWhatOn();
			int amt = ui.getAmount(aPlayer.getStake(), betType, whatOn);
			
			switch(betType) //Add bet to array and decrement stake by bet amount
			{
			case '1': Ordinary tempOrd = new Ordinary(whatOn, amt);			  		  
					  bets.add(tempOrd); updatePlayerStake(amt, 0); 
			break;
			case '2': DoubleOrNothing tempDoN = new DoubleOrNothing(whatOn, amt);
					  bets.add(tempDoN); updatePlayerStake(amt, 0); 
			break;			
			case '3': AllIn tempAi = new AllIn(whatOn, amt);					 
			  		  bets.add(tempAi); updatePlayerStake(amt, 0); 
			break;
			default:
			}
			ui.displayMessageln("");
			ui.displayMessageln("bet of £" + amt + " placed on " + whatOn);
			ui.displayMessageln("Your stake is now £" + aPlayer.getStake());
			ui.displayMessageln("");
			proceed = ui.getAnswer("Would you like to place any more bets? (Y/N)");//asks user if any more bets

		}while (proceed == 'y');
	}
	
	private void updatePlayerStake(int amt, int winnings) //edits bet/winnings from player stake and banker
	{
		
		if (aPlayer.getBanker() - winnings <=0) //banker can't pay out more than available
		{
			aPlayer.incStake(aPlayer.getBanker());
			aPlayer.decBanker(aPlayer.getBanker());
		}
		else 
		{
			aPlayer.decStake(amt);
			aPlayer.incBanker(amt);
			aPlayer.incStake(winnings);
			aPlayer.decBanker(winnings);	
		}
	}

	private CADice[] rolldice() //randomise values in dice array
	{
		 dice = new CADice[3];
		 
		 for (int i = 0; i < dice.length; i++) 
		 {
			 dice[i] = new CADice();
			 dice[i].throwDice();
			 dice[i].getCADice();
		 }
		 
		return dice;
	}

	private char endRound() 
	{
		int stake = aPlayer.getStake();
		int banker = aPlayer.getBanker();
		char result = '0';
				
		if (stake <= 0 ) 
		{
			ui.displayMessageln("YOU LOSE - you've run out of money.\nGAME OVER");
			result = 'l';
		}
		else if (banker <=0) 
		{
			ui.displayMessageln("YOU WON - Banker has run out of money.\nGAME OVER");
			aPlayer.incStake(100);
			result = 'w';
		}
		else 
		{
			ui.displayMessageln("End of the round. You have: £" + stake +"\nBanker has: £" + banker);
			result = 'c';
		}
		
		return result;
	
	}

	private void saveGame(Player aPlayer) 
	{	
	    if (ui.getAnswer("Save before quitting? (Y/N)") == 'y') 
	    {
	    	try 
	    	{
	    		FileOutputStream fileOut = new FileOutputStream(aPlayer.getName().toLowerCase() + ".ser");
	    		ObjectOutputStream outPlayer = new ObjectOutputStream(fileOut);
	    		outPlayer.writeObject(aPlayer);
	    		outPlayer.close();
	    		fileOut.close();
	    	}
	    	catch (IOException e) 
	    	{
	      		e.printStackTrace();
	      	}
	    }
	    else 
	    {
	    	return;
	    }
	
	}
}