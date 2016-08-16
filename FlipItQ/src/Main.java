import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;

public class Main {

	static int[][] goalState = new int[][]
	{
		{ 1, 1, 1},
		{ 1, 1, 1},
		{ 1, 1, 1}
	};

	static int[][] currentState = new int[][]
	{
		{ 0, 0, 0},
		{ 0, 0, 0},
		{ 0, 0, 0}
	};

	@SuppressWarnings("unused")
	public static void main(String[] args) 
	{

		if(true)
		{
			File outputFile = new File("Results/results.csv");
			outputFile.mkdirs();
			if (!outputFile.exists())
			{
				try 
				{
					outputFile.createNewFile();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				outputFile.delete();
				try 
				{
					outputFile.createNewFile();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			PrintStream out = null;
			try 
			{
				out = new PrintStream(new FileOutputStream(outputFile));
				System.setOut(out);
				System.setErr(out);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
		}

		Policy policy = new Policy();
		RewardFunction rewardFunction = new RewardFunction();
		TransitionFunction transistionFunction = new TransitionFunction();
		QLearningUpdateForEpisode qFunction = new QLearningUpdateForEpisode();
		QMatrix qMatrix = new QMatrix();
		qMatrix.createStates();

		int[][] initialState = new int[][]
		{
				{ 0, 0, 0},
				{ 0, 0, 0},
				{ 0, 0, 0}
		};

		int averageStepsPerEpisode = 0;

		for(int j = 0; j < 10; ++j)
		{
			policy.epsilon = 0.5f;
			int totalEpisodes = 1;
			for(int i = 0; i < 100; i++)
			{
				//System.out.println("-"+i);
				currentState = policy.selectActionFromSetOfStates(transistionFunction, rewardFunction, qFunction, qMatrix, currentState, goalState);
				if(Arrays.deepEquals(currentState, goalState))
				{
					System.out.print((i+1)+", ");
					currentState = initialState;
					
					totalEpisodes++;
					if(totalEpisodes > 200)
					{
						System.out.println();
						break;
					}
					i = -1;
				}
				else
				{
					if(i == 99)
					{
						i = -1;
						System.out.print("100, ");
						currentState = initialState;

						totalEpisodes++;
						if(totalEpisodes > 200)
						{
							System.out.println();
							break;
						}
					}
				}
				policy.epsilon = Math.max(0.5f - (0.01f * totalEpisodes), 0);
			}
	
			qMatrix = new QMatrix();
			qMatrix.createStates();
		}
		
	

	}

	public static void printGridState(int[][] currentState)
	{
		int x = 0;
		for(int y = 0; y < currentState.length; ++y)
		{
			for(x = 0; x < currentState.length; ++x)
			{
				System.out.print(currentState[x][y]+" ");
			}
			System.out.println();
			x = 0;
		}
	}

}
