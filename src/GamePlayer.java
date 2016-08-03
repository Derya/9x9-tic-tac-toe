package src;
import java.util.Scanner;

public class GamePlayer
{
	// creates game, drawer, and scanner objects
	Game thegame;
	Scanner myscan;
	CLIBoardDrawer boardDrawer;

	// temporary variable holding latest user input
	String input;
	// other temporary variables
	byte primaryinput = 0;
	byte secondaryinput = 0;
	boolean isvalid;

	// stores who has won this game, 0 if no one has won, 1 if x has won, -1 if o has won, 2 if it is a tie, 3
	// if user initiated quit
	byte gamestatus;

	public GamePlayer()
	{
		thegame = new Game();
		myscan = new Scanner( System.in );
		boardDrawer = new CLIBoardDrawer( thegame );
		gamestatus = 0;
	}

	public void playGame()
	{
		// prints introduction
		System.out.println( "An interesting game. Unless you would prefer chess, enter any key to begin." );
		System.out.println( "Enter q to exit. During the game, simply enter the square to play in it." );
		System.out.println( "Note that if you can select your box as well, enter that first." );
		System.out.println( "You can enter u to undo a move or r to redo a move. Enter p to print the board." );

		// waits for game start from user
		input = myscan.nextLine();

		// psyche lol
		actuallyPlayGame();
	}

	private void actuallyPlayGame()
	{
		// some temporary variables that will be used in the upcoming loop
		String playertoplay;
		String boxtoplay;

		// draws board
		boardDrawer.drawboard();

		// continues game
		while( gamestatus == 0 )
		{
			// prints info to user
			if( thegame.xToPlay() )
				playertoplay = "Player X to play in ";
			else
				playertoplay = "Player O to play in ";

			if( thegame.anybox() )
				boxtoplay = "any box.";
			else
				boxtoplay = "box " + ( thegame.boxtoplay() ) + ".";
			System.out.println( "It is move " + thegame.movenumber() + ". " + playertoplay + boxtoplay );
			if( thegame.anybox() )
				System.out
						.println( "Enter the box to be played in and the board to be played in. Alternatively enter u to undo, p to print the board, r to redo, or q to quit." );
			else
				System.out
						.println( "Enter the board to be played in. Alternatively enter u to undo, p to print the board, r to redo, or q to quit" );

			// gets next user input
			input = myscan.nextLine();

			// acts appropriately
			if( input.equals( "u" ) )
			{
				// undoes move if possible
				if( !thegame.undo() )
					System.out.println( "It is move one you numbskull." );
			}
			else if( input.equals( "r" ) )
			{
				// redoes move if possible
				if( !thegame.redo() )
					System.out.println( "No more moves to redo." );
			}
			else if( input.equals( "q" ) )
			{
				// sets gamestatus to 3
				gamestatus = 3;
			}
			else if( input.equals( "p" ) )
			{
				boardDrawer.drawboard();
			}
			else
			{
				makemove();
			}
			// checks whether game is over
			gamestatus = thegame.win();
		}
		// after while loop, at this point game has ended through some or another means
		System.out.println( "Game over." );
	}
	
	
	private void makemove()
	{

		// user input is either the tile to be played in or garbage
		try
		{
			primaryinput = ( Byte.parseByte( input ) );
			isvalid = true;
		}
		catch( NumberFormatException tim )
		{
			isvalid = false;
		}
		if( thegame.anybox() && isvalid )
		{
			// user input is box to play in
			// takes the second part of the user input
			try
			{
				secondaryinput = ( Byte.parseByte( myscan.nextLine() ) );
				// attempts move, prints board if successful and error if it fails
				if( thegame.move( primaryinput, secondaryinput ) )
					boardDrawer.drawboard();
				else
					System.out.println( "Invalid move." );
			}
			catch( NumberFormatException tim )
			{
				// if second input was garbage, prints this
				System.out.println( "Invalid input." );
			}
		}
		else if( isvalid )
		{
			// user input is board to play in
			// attempts move, prints board if successful and error if it fails
			if( thegame.move( thegame.boxtoplay(), primaryinput ) )
				boardDrawer.drawboard();
			else
				System.out.println( "Invalid move." );
		}
		else
		{
			// user input is garbage
			System.out.println( "Invalid input." );
		}
	
	}
}