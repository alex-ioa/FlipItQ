import java.util.ArrayList;
import java.util.Arrays;


public class TransitionFunction 
{
	// A new flipper which is used to carry out the flip operation and transition function.
	Flipper flipper = new Flipper();

	public ArrayList<int[][]> getPossibleActions(int[][] oldState, QMatrix qMatrix)
	{
		// An array list of 2D arrays or states of the board. We will use this to store
		// all the possible states we could reach from our current state.
		ArrayList<int[][]> possibleNewStates = new ArrayList<int[][]>();	
		
		// We should also consider the previous state as a choice.
		possibleNewStates.add(oldState);

		// Now we loop through the entire state space (512 states) to find the states
		// that we can reach through flipping a disc.
		for(int stateSpaceIndex = 0; stateSpaceIndex < 512; stateSpaceIndex++)
		{
			// Nested for loop for the 2D array.
			for(int y = 0; y < 3; y++)
			{
				for(int x = 0; x < 3; x++)
				{
					// If the state we are looking at on this loop is equal to a state that is produced from a flip action
					// then add it to our possible states.
					if(Arrays.deepEquals(qMatrix.getSetOfStates().get(stateSpaceIndex), flipper.flipDisc(x, y, oldState)))
					{
						possibleNewStates.add(qMatrix.getSetOfStates().get(stateSpaceIndex));
					}
				}
			}
		}
		return possibleNewStates;
	}
}
