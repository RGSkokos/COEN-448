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
	int mXPos, mYPos;

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
			if(mXPos == mFloorDimension -1)
			{
				return false;
			}
			if (mXPos + spaceS -1 > mFloorDimension -1)
			{
				return false;
			}
			int destination = mXPos + spaceS;
			if(!mIsPenUp)
			{
				for (int i = mXPos; i <= destination; i++)
				{
					mFloor[i][mYPos] = 1;
				}
			}
			mXPos = destination;
		}
		else if(mOrientation == Orientation.South)
		{
			// Avoid going outside of the floor area
			if(mXPos == 0)
			{
				return false;
			}
			if (mXPos - spaceS < 0)
			{
				return false;
			}
			int destination = mXPos - spaceS;
			if(!mIsPenUp)
			{
				for (int i = mXPos; i >= destination; i--)
				{
					mFloor[i][mYPos] = 1;
				}
			}
			mXPos = destination;
		}
		else if(mOrientation == Orientation.East)
		{
			// Avoid going outside of the floor area
			if(mYPos == mFloorDimension -1)
			{
				return false;
			}
			if (mYPos + spaceS -1 > mFloorDimension -1)
			{
				return false;
			}
			int destination = mYPos + spaceS;
			if(!mIsPenUp)
			{
				for (int i = mYPos; i <= destination; i++)
				{
					mFloor[mXPos][i] = 1;
				}
			}
			mYPos = destination;
		}
		else if(mOrientation == Orientation.West)
		{
			// Avoid going outside of the floor area
			if(mYPos == 0)
			{
				return false;
			}
			if (mYPos - spaceS < 0)
			{
				return false;
			}
			int destination = mYPos - spaceS;
			if(!mIsPenUp)
			{
				for (int i = mYPos; i >= destination; i--)
				{
					mFloor[mXPos][i] = 1;
				}
			}
			mYPos = destination;
		}
		return true;
	}
	public boolean printCurrentPosition()
	{	//	Display the current position of the robot
		System.out.print(" Position:" + mXPos + "," + mYPos);
		// Display the current state of the pen
		if (mIsPenUp)
		{
			System.out.print(" Pen:" + " up");
		}
		else
		{
			System.out.print(" Pen:" + " down");
		}
		// Display the current orientation of the robot
		if(mOrientation == Orientation.North)
		{
			System.out.print(" Facing:" + " North");
		}
		else if(mOrientation == Orientation.South)
		{
			System.out.print(" Facing:" + " South");
		}
		else if(mOrientation == Orientation.East)
		{
			System.out.print(" Facing:" + " East");
		}
		else if(mOrientation == Orientation.West)
		{
			System.out.print(" Facing:" + " West");
		}
		System.out.print("\n");
		return true;
	}
	//	Display the floor marking, star for marked, space for unmarked
	public boolean printFloor()
	{
		for (int i = mFloorDimension -1; i >= 0; i--)
		{
			for (int j = 0; j < mFloorDimension; j++)
			{	// if marked, print star, else print space
				if(mFloor[i][j] == 1)
				{
					System.out.print('*');
				}
				else
				{
					System.out.print(' ');
				}
				System.out.print(' ');
			}
			System.out.print('\n');
		}

		return true;
	}
	// Initialize the floor using the sizeN, set all the elements to 0 and pen up
	private void Init(int sizeN)
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
		mXPos = 0;
		mYPos = 0;
	}
}