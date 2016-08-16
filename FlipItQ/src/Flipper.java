
public class Flipper 
{

	public int[][] flipDisc(int discToFlipOnGridXPosition, int discToFlipOnGridYPosition, int[][] oldState)
	{

		// Because it is an array that starts at 0 we need
		// to minus one off the arguments so the function reads col 1 row 1

//		discToFlipOnGridXPosition -= 1;
//		discToFlipOnGridYPosition -= 1;

		// I must check which arguments are in bounds so we do not attempt
		// to flip a disc that is off the edge of the grid.
		// To do this I set up two booleans each for the horizontal and the vertical.

		boolean horizontalRightInBounds = ((discToFlipOnGridXPosition+1 >= 0) && (discToFlipOnGridXPosition+1 < oldState.length));
		boolean horizontalLeftInBounds = ((discToFlipOnGridXPosition-1 >= 0) && (discToFlipOnGridXPosition-1 < oldState.length));

		boolean verticalUpInBounds = (discToFlipOnGridYPosition-1 >= 0) && (discToFlipOnGridYPosition-1 < oldState.length);
		boolean verticalDownInBounds = (discToFlipOnGridYPosition+1 >= 0) && (discToFlipOnGridYPosition+1 < oldState.length);

		int discToBeFlippedCurrentState = oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition];

		if(discToBeFlippedCurrentState == 0)
		{
			// We are turning a black disc into white.
//			System.out.println("Flipping a black disc!");

			// First flip the disc passed in
			oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition] = 1;
		}
		else
		{
			// We are turning a white disc into black.
//			System.out.println("Flipping a white disc!");

			// First flip the disc passed in
			oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition] = 0;
		}
		
		// Then flip the adjoining discs in the horizontal positions if they are in bounds
		if(horizontalRightInBounds && horizontalLeftInBounds)
		{
			// Check if the disc on the right is black or white and flip accordingly
			if(oldState[discToFlipOnGridXPosition+1][discToFlipOnGridYPosition] == 0)
			{
				oldState[discToFlipOnGridXPosition+1][discToFlipOnGridYPosition] = 1;
			}
			else
			{
				oldState[discToFlipOnGridXPosition+1][discToFlipOnGridYPosition] = 0;
			}
			// Check if the disc on the left is black or white and flip accordingly
			if(oldState[discToFlipOnGridXPosition-1][discToFlipOnGridYPosition] == 0)
			{
				oldState[discToFlipOnGridXPosition-1][discToFlipOnGridYPosition] = 1;
			}
			else
			{
				oldState[discToFlipOnGridXPosition-1][discToFlipOnGridYPosition] = 0;
			}				
		}
		else if (horizontalRightInBounds)
		{
			// Check if the disc on the right is black or white and flip accordingly
			if(oldState[discToFlipOnGridXPosition+1][discToFlipOnGridYPosition] == 0)
			{
				oldState[discToFlipOnGridXPosition+1][discToFlipOnGridYPosition] = 1;
			}
			else
			{
				oldState[discToFlipOnGridXPosition+1][discToFlipOnGridYPosition] = 0;
			}
		}
		else if (horizontalLeftInBounds)
		{
			// Check if the disc on the left is black or white and flip accordingly
			if(oldState[discToFlipOnGridXPosition-1][discToFlipOnGridYPosition] == 0)
			{
				oldState[discToFlipOnGridXPosition-1][discToFlipOnGridYPosition] = 1;
			}
			else
			{
				oldState[discToFlipOnGridXPosition-1][discToFlipOnGridYPosition] = 0;
			}
		}

		// Then flip the adjoining discs in the vertical positions if they are in bounds
		if(verticalUpInBounds && verticalDownInBounds)
		{
			// Check if the top and bottom discs are black or white and flip accordingly
			if(oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition-1] == 0)
			{
				oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition-1] = 1;
			}
			else
			{
				oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition-1] = 0;
			}

			if(oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition+1] == 0)
			{
				oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition+1] = 1;
			}
			else
			{
				oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition+1] = 0;
			}
		}
		else if (verticalUpInBounds)
		{

			// Check if the top disc is black or white and flip accordingly
			if(oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition-1] == 0)
			{
				oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition-1] = 1;
			}
			else
			{
				oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition-1] = 0;
			}
		}
		else if (verticalDownInBounds)
		{
			// Check if the bottom disc is black or white and flip accordingly
			if(oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition+1] == 0)
			{
				oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition+1] = 1;
			}
			else
			{
				oldState[discToFlipOnGridXPosition][discToFlipOnGridYPosition+1] = 0;
			}
		}
		return oldState;

	}

}
