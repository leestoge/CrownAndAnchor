package crownandanchor;

public class CADice extends Dice
{	
	public String getCADice()
	{
		String dicePicture="";
			    
        switch (getDice()) 
        {
            case 1:  dicePicture = "Crown";
            break;
            case 2:  dicePicture = "Anchor";
            break;
            case 3:  dicePicture = "Diamond";
            break;
            case 4:  dicePicture = "Club";
            break;
            case 5:  dicePicture = "Spade";
            break;
            case 6:  dicePicture = "Heart";
            break;
            default: dicePicture = "";
            break;         
        }
        return dicePicture;
	}
}