package com.coen448.project;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RobotMotion {
	static boolean isValidCommand;
	static boolean isTerminated;
	//floor size initialization to 20, this can be changed to any positive integer
	//requirement does not specify the size of the floor,
	// so I am assuming it can be any NxN positive integer.
	// User can change the size of the floor using the command "[I n|i n]"
	static Robot robotHdl = new Robot(20);
	//used to print error messages, depending on the error
	public static void printError(String errorMessage)
	{
		System.out.println(errorMessage);
	}

	//used to receive some string as input,
	// extracts the necessary information from it and converts it into an object of the calling class.
	// extracts the first character of the string and checks if it is a valid command
	// Or if the command is valid, it extracts the second character and checks if it is a valid integer.
	public static boolean parse(String command, boolean isChar)
	{
		// Tokenize users command
		StringTokenizer tokenizer = new StringTokenizer(command);
		// Get the first argument
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

	public static void printHelp()
	{
		System.out.println("Available commands are: ");
		System.out.println("U|u: Pen up");
		System.out.println("D|d: Pen down");
		System.out.println("R|r: Turn right");
		System.out.println("L|l: Turn left");
		System.out.println("M s|m s: Move forward s spaces (s is a non-negative integer)");
		System.out.println("P|p: Print the floor");
		System.out.println("C|c: Print the current position");
		System.out.println("Q|q: Quit the program");
		System.out.println("I n|i n: Initialize the system: " +
				"The values of the array floor are zeros and the robot\n" +
				"\t\t is back to [0, 0], pen up and facing north. n size of the array, an integer\n " +
				"\t\t greater than zero.");
	}

	public static void processCommand(String command){
		if (command == null) {
			isValidCommand = false;
			return;
		}
		char commandChar = command.charAt(0);
		// used to check if the command is valid
		// used to check if the command is a command character
		boolean isCommandChar = true;
		isValidCommand = false;

		try
		{ //pen up command
			if (commandChar == 'U' || commandChar == 'u')
			{
				if (parse(command, isCommandChar))
				{
					isValidCommand = true;
					robotHdl.penUp();
				}
			}
			//pen down command
			else if(commandChar == 'D' || commandChar == 'd')
			{
				if (parse(command, isCommandChar))
				{
					isValidCommand = true;
					robotHdl.penDown();
				}
			}
			//turn right command
			else if(commandChar == 'R' || commandChar == 'r')
			{
				if (parse(command, isCommandChar))
				{
					isValidCommand = true;
					robotHdl.turnRight();
				}
			}
			// turn left command
			else if(commandChar == 'L' || commandChar == 'l')
			{
				if (parse(command, isCommandChar))
				{
					isValidCommand = true;
					robotHdl.turnLeft();
				}
			}
			// move forward command
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
			// print floor command
			else if(commandChar == 'P' || commandChar == 'p')
			{
				if (parse(command, isCommandChar))
				{
					isValidCommand = true;
					robotHdl.printFloor();
				}
			}
			// print current position command
			else if(commandChar == 'C' || commandChar == 'c')
			{
				if (parse(command, isCommandChar))
				{
					isValidCommand = true;
					robotHdl.printCurrentPosition();
				}
			}
			// quit command
			else if(commandChar == 'Q' || commandChar == 'q')
			{
				if (parse(command, isCommandChar))
				{
					isValidCommand = true;
					isTerminated = true;
				}
			}
			//	initialize floor command
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
				printHelp();
				isValidCommand = true;
			}
		}
		// catch null pointer exception when the robot has not been initialized yet
		catch (NullPointerException e)
		{
			System.out.println("Warning Robot has not been initialized yet!");
		}
		System.out.println("Command entered: " + command);
		if (!isValidCommand)
		{
			System.out.println("Warning Invalid command type. H or h to see the available commands");
		}
	}

	public static void main(String[] args)
	{
		System.out.println("Program starts");
		Scanner scanner = new Scanner(System.in);
		do {

			System.out.println("******Enter the next command below******");
			String command = scanner.nextLine();
			command = command.trim();

			processCommand(command);

		}while(!isTerminated);
		scanner.close();
		System.out.println("Program ends");
	}

}