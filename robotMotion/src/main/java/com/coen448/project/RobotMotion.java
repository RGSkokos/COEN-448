package com.coen448.project;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RobotMotion {
	//String errorMsg = "";

	public static void printError(String errorMessage)
	{
		System.out.println(errorMessage);
	}

	public static boolean parse(String command, boolean isChar)
	{
		StringTokenizer tokenizer = new StringTokenizer(command);
		String firstArg = tokenizer.nextToken();

		// Command should be 1 character long. Example: returning false if C!2d
		if (firstArg.length() != 1)
		{
			return false;
		}

		if (isChar)
		{
			// Number of the tokens cannot be more than 1
			if (tokenizer.countTokens() > 1)
			{
				return false;
			}
		}
		else
		{
			// number of the tokens cannot be anything other than 2
			int tokenCount = tokenizer.countTokens();
			if (tokenCount != 1)
			{
				return false;
			}
			String secondArg = tokenizer.nextToken();

			int secondArgInt = 0;
			try
			{
				secondArgInt = Integer.parseInt(secondArg);
			}
			catch(NumberFormatException e)
			{

				return false;
			}

			// The second argument needs to be positive integer
			if (secondArgInt < 0)
			{
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		System.out.println("Program starts");
		boolean isTerminated = false;
		//floor size initialization to 20, this can be changed to any positive integer
		//requirement does not specify the size of the floor,
		// so I am assuming it can be any NxN positive integer
		Robot robotHdl = new Robot(20);
		Scanner scanner = new Scanner(System.in);
		do {

			System.out.println("******Enter the next command below******");
			String command = scanner.nextLine();
			command = command.trim();
			char commandChar = command.charAt(0);

			boolean isValidCommand = false;
			boolean isCommandChar = true;

			try
			{
				if (commandChar == 'U' || commandChar == 'u')
				{
					if (parse(command, isCommandChar))
					{
						isValidCommand = true;
						robotHdl.penUp();
					}
				}
				else if(commandChar == 'D' || commandChar == 'd')
				{
					if (parse(command, isCommandChar))
					{
						isValidCommand = true;
						robotHdl.penDown();
					}
				}
				else if(commandChar == 'R' || commandChar == 'r')
				{
					if (parse(command, isCommandChar))
					{
						isValidCommand = true;
						robotHdl.turnRight();
					}
				}
				else if(commandChar == 'L' || commandChar == 'l')
				{
					if (parse(command, isCommandChar))
					{
						isValidCommand = true;
						robotHdl.turnLeft();
					}
				}
				else if(commandChar == 'M' || commandChar == 'm')
				{
					isCommandChar = false;
					if (parse(command, isCommandChar))
					{
						isValidCommand = true;
						StringTokenizer tokenizer = new StringTokenizer(command);
						tokenizer.nextToken();
						String secondArg = tokenizer.nextToken();
						robotHdl.moveForward(Integer.parseInt(secondArg));
					}
				}
				else if(commandChar == 'P' || commandChar == 'p')
				{
					if (parse(command, isCommandChar))
					{
						isValidCommand = true;
						robotHdl.printFloor();
					}
				}
				else if(commandChar == 'C' || commandChar == 'c')
				{
					if (parse(command, isCommandChar))
					{
						isValidCommand = true;
						robotHdl.printCurrentPosition();
					}
				}
				else if(commandChar == 'Q' || commandChar == 'q')
				{
					if (parse(command, isCommandChar))
					{
						isValidCommand = true;
						isTerminated = true;
					}
				}
				else if(commandChar == 'I' || commandChar == 'i')
				{
					isCommandChar = false;
					if (parse(command, isCommandChar))
					{
						isValidCommand = true;
						StringTokenizer tokenizer = new StringTokenizer(command);
						tokenizer.nextToken();
						String secondArg = tokenizer.nextToken();
						robotHdl = new Robot(Integer.parseInt(secondArg));
					}
				}
				else if(commandChar == 'H' || commandChar == 'h')
				{

					System.out.println("Available commands are: ");
					System.out.println("I|i: Initialize the robot");
					System.out.println("U|u: Pen up");
					System.out.println("D|d: Pen down");
					System.out.println("R|r: Turn right");
					System.out.println("L|l: Turn left");
					System.out.println("M|m: Move forward");
					System.out.println("P|p: Print the floor");
					System.out.println("C|c: Print the current position");
					System.out.println("Q|q: Quit the program");

					isValidCommand = true;
				}
			}
			catch (NullPointerException e)
			{
				System.out.println("Warning Robot has not been initialized yet!");
			}

			System.out.println("Command entered: " + command);
			if (!isValidCommand)
			{
				System.out.println("Warning Invalid command type. H or h to see the available commands");
			}

		}while(!isTerminated);
		//Scanner scanner = new Scanner(System.in);
		scanner.close();
		System.out.println("Program ends");


	}

}