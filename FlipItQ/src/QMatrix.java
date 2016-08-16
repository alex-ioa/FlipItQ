import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

public class QMatrix 
{

	private int stateSpace = 512;
	private ArrayList<int[][]> setOfStates = new ArrayList<int[][]>();
	private float[][] Qmatrix = new float[stateSpace][stateSpace];
	
	public void setQValueInMatrix(int[][] fromState, int[][] toState, float newQValue)
	{
		int fromStateIndexInQMatrix = -1;
		int toStateIndexInQMatrix = -1;
		for(int i = 0; i < stateSpace; i++)
		{
			int[][] state = setOfStates.get(i);
			if(Arrays.deepEquals(fromState, state))
			{
				fromStateIndexInQMatrix = i;
				break;
			}
		}
		
		for(int i = 0; i < stateSpace; i++)
		{
			int[][] state = setOfStates.get(i);
			if(Arrays.deepEquals(toState, state))
			{
				toStateIndexInQMatrix = i;
				break;
			}
		}
		Qmatrix[fromStateIndexInQMatrix][toStateIndexInQMatrix] = newQValue;
	}
	
	public float getQValueFromMatrix(int[][] fromState, int[][] toState)
	{
		int fromStateIndexInQMatrix = -1;
		int toStateIndexInQMatrix = -1;
		
		for(int i = 0; i < stateSpace; i++)
		{
			int[][] state = setOfStates.get(i);
			if(Arrays.deepEquals(fromState, state))
			{
				fromStateIndexInQMatrix = i;
				break;
			}
		}
		for(int i = 0; i < stateSpace; i++)
		{
			int[][] state = setOfStates.get(i);
			if(Arrays.deepEquals(toState, state))
			{
				toStateIndexInQMatrix = i;
				break;
			}
		}
		return Qmatrix[fromStateIndexInQMatrix][toStateIndexInQMatrix];
	}
	
	public void createStates()
	{
		// Create the initial vector of 2 elements (0 and 1 to represent black and white)
		ICombinatoricsVector<Integer> originalVector = Factory.createVector(new Integer[] { 0, 1 });

		// Set the second parameter as 9, since we are permutating with 9 discs.
		Generator<Integer> gen = Factory.createPermutationWithRepetitionGenerator(originalVector, 9);
		
		for (ICombinatoricsVector<Integer> permuation : gen) 
		{
			int[][] generatedState = new int[3][3];
			List<Integer> listOfGeneratedStates = permuation.getVector();
			int numOfState = 0;
			for(int y = 0; y < 3; y++)
			{
				for(int x = 0; x < 3; x++)
				{
				generatedState[x][y] = listOfGeneratedStates.get(numOfState);
				
				numOfState++;
				}
			}
			setOfStates.add(generatedState);
		}
	}

	public ArrayList<int[][]> getSetOfStates() 
	{
		return setOfStates;
	}
	
}
