package com.coen448.project;

public class Robot {

	// Inner class
	enum Orientation
	{
		North,
		South,
		Easet,
		West
	}
	
	// Member variables
	int [][] floor; 
	int floorDimention;
	boolean isPenUp;
	Orientation orientation;
	int xPos;
	int yPos;
	
	// Member methods
	public Robot(int sizeN)
	{
		Init(sizeN);
	}
	
	public boolean penUp()
	{
		return true;
	}
	
	public boolean penDown()
	{
		return true;
	}
	
	public boolean trurnRight()
	{
		return true;
	}
	
	public boolean moveForward(int spaceS)
	{
		if (spaceS < 0)
		{
			System.out.println("S spaces to move cannot be less than ZERO");
			return false;
		}
		return true;
	}
	
	public boolean printCurrentPosition()
	{
		System.out.println("The current position of the robot: " + " x: " + xPos + " y:" + yPos);
		return true;
	}
	
	public boolean printFloor()
	{
		for (int i = floorDimention -1; i >= 0; i--)
		{
			for (int j = 0; j < floorDimention; j++)
			{
				if(floor[i][j] == 1)
				{ 
					System.out.print('*');
				}
				else 
				{
					System.out.print(' ');
				}
			}
			System.out.print('\n');
		}	
		
		return true;
	}
	
	private void Init(int sizeN)
	{
		// Initialize the floor
		if (sizeN < 0)
		{
			System.out.println("Size of the floor cannot be less than ZERO");
		}
		floorDimention = sizeN;
		floor = new int [floorDimention][floorDimention];
		for (int i = 0; i < floorDimention; i++)
		{
			for (int j = 0; j < floorDimention; j++)
			{
				floor[i][j] = 0;
			}
		}
		
		// Initialize the pen
		isPenUp = true;
		
		// Initialize the orientation
		orientation = Orientation.North;
		
		// Initialize the position
		xPos = 0;
		yPos = 0;
	}
}
