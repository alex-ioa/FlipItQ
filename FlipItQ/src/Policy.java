import java.util.ArrayList;


public class Policy 
{

	float epsilon = 0.5f;
	
	public int[][] selectActionFromSetOfStates(TransitionFunction tFunction, RewardFunction rFunction, QLearningUpdateForEpisode qFunction, QMatrix qMatrix, int[][] currentState, int[][] goalState)
	{
		ArrayList<int[][]> actionsFromCurrentState = tFunction.getPossibleActions(currentState, qMatrix);

		if(Math.random() < epsilon)
		{
			int randomIndexToChooseNextState = (int)(Math.random() * actionsFromCurrentState.size());
			qFunction.temporalDifferenceUpdateFunction(currentState, actionsFromCurrentState.get(randomIndexToChooseNextState), qMatrix, tFunction, rFunction, goalState);
			return currentState = actionsFromCurrentState.get(randomIndexToChooseNextState);
		}
		else
		{
			int[][] actionWithHighestValue = null;
			float highestValue = -100;

			for(int[][] action : actionsFromCurrentState)
			{
				float value = findPredictedStateActionValue(currentState, action, qMatrix, tFunction, rFunction, goalState);
				if(value >= highestValue)
				{
					highestValue = value;
					actionWithHighestValue = action;
				}

			}

			qFunction.temporalDifferenceUpdateFunction(currentState, actionWithHighestValue, qMatrix, tFunction, rFunction, goalState);
			return currentState = actionWithHighestValue;
		}
	}

	private float findPredictedStateActionValue(int[][] currentState,int[][] nextState, QMatrix qMatrix, TransitionFunction tFunction, RewardFunction rFunction, int[][] goalState) 
	{
		
		ArrayList<int[][]> actions = tFunction.getPossibleActions(nextState, qMatrix);

		float maxA = 0;
		
		float reward = rFunction.immediateReward(currentState, nextState, goalState);
		
		for(int[][] action : actions)
		{
			float qValue = qMatrix.getQValueFromMatrix(nextState, action);
			if(qValue > maxA)
			{
				maxA = qValue;
			}
		}

		float qSA = reward + (QLearningUpdateForEpisode.gamma * maxA);
		return qSA;
	}
	
}
