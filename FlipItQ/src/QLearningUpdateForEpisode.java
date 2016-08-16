import java.util.ArrayList;


public class QLearningUpdateForEpisode 
{
	public static float alpha = 0.9f;
	public static float gamma = 0.8f;
	
	public void temporalDifferenceUpdateFunction(int[][] currentState, int[][] nextState, QMatrix qMatrix, TransitionFunction tFunction, RewardFunction rFunction, int[][] goalState)
	{
		float reward = rFunction.immediateReward(currentState, nextState, goalState);
		
		ArrayList<int[][]> actions = tFunction.getPossibleActions(nextState, qMatrix);
		
		float maxA = 0;
		for(int[][] action : actions)
		{
			float qValue = qMatrix.getQValueFromMatrix(nextState, action);
			if(qValue > maxA)
			{
				maxA = qValue;
			}
		}
		
		float qSA = reward + (gamma * maxA);
		
		float delta = qSA - qMatrix.getQValueFromMatrix(currentState, nextState);
		
		float newQValue = qMatrix.getQValueFromMatrix(currentState, nextState) + alpha * delta;
		
		qMatrix.setQValueInMatrix(currentState, nextState, newQValue);
		
	}
}
