package com.coen448.project;

public class Robot {
	// All the possible orientations of the robot
	enum Orientation
	{
		North,
		South,
		East,
		West
	}
	int [][] mFloor;
	// Size of the floor matrix
	int mFloorDimension;
	// True if the pen is up, false if the pen is down
	boolean mIsPenUp;
	Orientation mOrientation;
	// Current position of the robot on the floor matrix
	int mColumn, mRow;

	// Initialize the robot, Constructor
	public Robot(int sizeN)
	{
		Init(sizeN);
	}
	// setup pen up
	public boolean penUp()
	{
		mIsPenUp = true;
		return true;
	}
	// setup pen down
	public boolean penDown()
	{
		mIsPenUp = false;
		return true;
	}
	// setup turn right
	public boolean turnRight()
	{
		if(mOrientation == Orientation.North)
		{
			mOrientation = Orientation.East;
		}
		else if(mOrientation == Orientation.South)
		{
			mOrientation = Orientation.West;
		}
		else if(mOrientation == Orientation.East)
		{
			mOrientation = Orientation.South;
		}
		else if(mOrientation == Orientation.West)
		{
			mOrientation = Orientation.North;
		}
		return true;
	}
	// setup turn left
	public boolean turnLeft()
	{
		if(mOrientation == Orientation.North)
		{
			mOrientation = Orientation.West;
		}
		else if(mOrientation == Orientation.South)
		{
			mOrientation = Orientation.East;
		}
		else if(mOrientation == Orientation.East)
		{
			mOrientation = Orientation.North;
		}
		else if(mOrientation == Orientation.West)
		{
			mOrientation = Orientation.South;
		}
		return true;
	}
	// setup move forward
	public boolean moveForward(int spaceS)
	{
		// Move forward in the direction the robot is facing
		if(mOrientation == Orientation.North)
		{
			// Avoid going outside of the floor area
			if(mRow == mFloorDimension -1)
			{
				return false;
			}
			if (mRow + spaceS -1 > mFloorDimension -1)
			{
				return false;
			}
			int destination = mRow + spaceS;
			if(!mIsPenUp)
			{
				for (int i = mRow; i <= destination; i++)
				{
					mFloor[i][mColumn] = 1;
				}
			}
			mRow = destination;
		}
		else if(mOrientation == Orientation.South)
		{
			// Avoid going outside of the floor area
			if(mRow == 0)
			{
				return false;
			}
			if (mRow - spaceS < 0)
			{
				return false;
			}
			int destination = mRow - spaceS;
			if(!mIsPenUp)
			{
				for (int i = mRow; i >= destination; i--)
				{
					mFloor[i][mColumn] = 1;
				}
			}
			mRow = destination;
		}
		else if(mOrientation == Orientation.East)
		{
			// Avoid going outside of the floor area
			if(mColumn == mFloorDimension -1)
			{
				return false;
			}
			if (mColumn + spaceS -1 > mFloorDimension -1)
			{
				return false;
			}
			int destination = mColumn + spaceS;
			if(!mIsPenUp)
			{
				for (int i = mColumn; i <= destination; i++)
				{
					mFloor[mRow][i] = 1;
				}
			}
			mColumn = destination;
		}
		else if(mOrientation == Orientation.West)
		{
			// Avoid going outside of the floor area
			if(mColumn == 0)
			{
				return false;
			}
			if (mColumn - spaceS < 0)
			{
				return false;
			}
			int destination = mColumn - spaceS;
			if(!mIsPenUp)
			{
				for (int i = mColumn; i >= destination; i--)
				{
					mFloor[mRow][i] = 1;
				}
			}
			mColumn = destination;
		}
		return true;
	}
	public String printCurrentPosition()
	{	
		String output ="";
		//	Display the current position of the robot
		output = " Position:" + mColumn + "," + mRow;
		System.out.print(" Position:" + mColumn + "," + mRow);
		// Display the current state of the pen
		if (mIsPenUp)
		{
			output +=  " Pen:" + " up";
			System.out.print(" Pen:" + " up");
		}
		else
		{
			output += " Pen:" + " down";
			System.out.print(" Pen:" + " down");
		}
		// Display the current orientation of the robot
		if(mOrientation == Orientation.North)
		{
			output += " Facing:" + " North";
			System.out.print(" Facing:" + " North");
		}
		else if(mOrientation == Orientation.South)
		{
			output += " Facing:" + " South";
			System.out.print(" Facing:" + " South");
		}
		else if(mOrientation == Orientation.East)
		{
			output += " Facing:" + " East";
			System.out.print(" Facing:" + " East");
		}
		else if(mOrientation == Orientation.West)
		{
			output += " Facing:" + " West";
			System.out.print(" Facing:" + " West");
		}
		System.out.print("\n");
		return output;
	}
	//	Display the floor marking, star for marked, space for unmarked
	public String printFloor()
	{
		String output = "";
		for (int i = mFloorDimension -1; i >= 0; i--)
		{
			for (int j = 0; j < mFloorDimension; j++)
			{	// if marked, print star, else print space
				if(mFloor[i][j] == 1)
				{
					output += '*';
					System.out.print('*');
				}
				else
				{
					output += ' ';
					System.out.print(' ');
				}
				output += ' ';
				System.out.print(' ');
			}
			output += '\n';
			System.out.print('\n');
		}

		return output;
	}
	// Initialize the floor using the sizeN, set all the elements to 0 and pen up
	public void Init(int sizeN)
	{
		// validate the size of the floor, not less than 0
		if (sizeN < 0)
		{
			System.out.println("Size of the floor cannot be less than ZERO");
		}
		mFloorDimension = sizeN;
		mFloor = new int [mFloorDimension][mFloorDimension];
		for (int i = 0; i < mFloorDimension; i++)
		{
			for (int j = 0; j < mFloorDimension; j++)
			{
				mFloor[i][j] = 0;
			}
		}
		// Initialize the pen
		mIsPenUp = true;

		// Initialize the orientation
		mOrientation = Orientation.North;

		// Initialize the position
		mRow = 0;
		mColumn = 0;
	}
}