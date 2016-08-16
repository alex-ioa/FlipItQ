public class RewardFunction 
{

	public int immediateReward(int[][] currentState, int[][] nextState, int[][] goalState)
	{
		// The reward function takes the current state and the next state and compares them
		// to the goal state. The more elements that match the goal state the higher the reward.
		int oldValue = 0;
		for(int y = 0; y < goalState.length; y++)
		{
			for(int x = 0; x < goalState.length; x++)
			{
			// The old value is calculated based on the current states comparison to the goal state
			// we check how many elements are the same, this gives us a rough idea of how close we are.
			if(currentState[x][y] == goalState[x][y])
				oldValue++;
			}
		}
		
		int newValue = 0;
		for(int y = 0; y < goalState.length; y++)
		{
			for(int x = 0; x < goalState.length; x++)
			{
			// The new value is calculated based on the next states comparison to the goal state
			// we check how many elements are the same, this gives us a rough idea of how close we are.
			if(nextState[x][y] == goalState[x][y])
				newValue++;
			}
		}
		
//		 If all of the discs on the board are white, we return a huge reward.
		if(newValue == 9)
		{
			return 500;
		}
		
		return newValue - oldValue;
	}
	
}
