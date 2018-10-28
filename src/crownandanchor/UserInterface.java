package crownandanchor;

import java.util.Scanner;

public class UserInterface 
{

	private Scanner sc = new Scanner(System.in);

	public String getPlayerName() //get player name and ensure that it's valid
	{
		String name = "";

		do 
		{
			System.out.print("Please enter your name:- ");
			if (sc.hasNext()) 
			{
				name = sc.next().toLowerCase();
				sc.nextLine();
			}
			else 
			{
				System.out.println("ERROR: Invalid input.");
			}			
		}while (name.length() < 1);

		return name;
	}

	public int getPlayerStake() 
	{
		int stake = 0;
		boolean valid = true;
		
		do 
		{
			valid = true;
			System.out.println("");
			System.out.println("Please enter your stake: (Minimum amount is 3)");
			if (sc.hasNextInt()) 
			{
				stake = sc.nextInt();
				sc.nextLine();
				
				if(stake < 3) // check if input less than 3 - display error if so
				{
					System.out.println("ERROR: Amount entered is lower than the minimum amount");
					valid = false;
				}
			}
			else 
			{
				System.out.println("ERROR: Invalid input.");
				valid = false;
				sc.next();
			}			
		}while (!valid);
		
		return stake;
	}
	
	public char getBetType() //get bet type
	{
		char input = '0';
		do 
		{	
			System.out.println("");
			System.out.println("Please choose your bet type by entering the number next to your desired bet type:- ");
			System.out.println("");
			System.out.println("Ordinary:-		1\n"
					+ "Double Or Nothing:-	2\n"
					+ "All In:-		3");
					
			if (sc.hasNext()) 
			{
				 input = sc.next().charAt(0);
				 
				 if (input > '3' || input < '1') 
				 {
					 System.out.println("Invalid input.");
				 }
			}
			else 
			{
				System.out.println("Invalid input.");
			}
		} while (false);
		
		return input;		
	}

	public String getWhatOn() 
	{	
		boolean valid = true;
			char input = 0;
			String whatOn = "";
			
			do 
			{		
				valid = true;
				System.out.println("");
				System.out.println("Please place your bets by entering the number next to your desired suit:-");
				System.out.println("");
				System.out.println("Crown:-		1\n"
						+ "Anchor:-	2\n"
						+ "Diamond:-	3\n"
						+ "Club:-		4\n"
						+ "Spade:-		5\n"
						+ "Heart:- 	6\n");
				
				if (sc.hasNext()) 
				{
					 input = sc.next().charAt(0);
					 
					 if (input > '6' || input < '1') 
					 {
						 System.out.println("ERROR: Invalid input");
							valid = false;
					 }					 
				}
				else 
				{
					System.out.println("ERROR: Invalid input");
					valid = false;
					sc.next();
				}
			} while (valid == false);
			
			switch(input) {//converts input choice into string
			case '1': whatOn =  "Crown"; break;
			case '2': whatOn =  "Anchor"; break;
			case '3': whatOn =  "Diamond"; break;
			case '4': whatOn =  "Club"; break;
			case '5': whatOn =  "Spade"; break;
			case '6': whatOn =  "Heart"; break;	
			default:
			}
			return whatOn;
	}

	public int getAmount(int stake, char type, String whatOn) 
	{
		int amount = 0;
		int min = 1;
		boolean valid = true;
		
		switch(type) //sets minimum bet amount according to bet type
		{ 
		case '1': 
		break;
		case '2': min = 2; 
		break;
		case '3': min = 3; 
		break;
		default:
		}
		
		do 
		{
			valid = true;
			amount = 0;
			System.out.println("You're betting on " + whatOn + 
								"\nThe minimum amount for this type of bet is " + "£" + min);
			System.out.println("");
			System.out.print("Please enter the amount you would like to bet: ");
			
			if(sc.hasNextInt()) 
			{
				amount = sc.nextInt();
				if(amount >= min) 
				{
					if(amount - stake > 0) 
					{
						System.out.println("ERROR: You don't have that much in your stake.");
						valid = false;		
					}
				}
				else 
				{
					System.out.println("Amount entered is less than the minimum bet amount for this bet type.");
					valid = false;
				}
			}
			else 
			{
				System.out.println("ERROR: Invalid input.");
				valid = false;
				sc.next();
			}		
	}while (!valid);
		return amount;
	}

	public void displayMessageln(String s) //println
	{
		System.out.println(s);
	}
		
	public char getAnswer(String message) 
	{
		
	char input = '0';
	boolean valid = true;
	
		do
		{
			valid = true;
			System.out.println(message);
			
			if(sc.hasNext()) 
			{
				input = sc.next().charAt(0);
				
				if(input != 'y' && input != 'n') 
				{
					valid = false;
					System.out.println("ERROR: Invalid input");
				}
			}
			else 
			{
				valid = false;
				System.out.println("ERROR: Invalid input");
			}
		
		}while (!valid);
		
		return input;
	}
}