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
			// number of the tokens cannot be more than 2
			if (tokenizer.countTokens() > 2)
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
		Robot robotHdl = null;
		
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("******Enter the next command below******");
			String command = scanner.nextLine();
			command = command.trim();
			char commandChar = command.charAt(0);
			
			boolean isValidCommand = false;
			boolean isCommandChar = true;
			if (commandChar == 'U' || commandChar == 'u')
			{
				if (parse(command, isCommandChar))
				{
					//TODO: Valid command, API calls to the robot
					isValidCommand = true;
				}
			}
			else if(commandChar == 'D' || commandChar == 'd')
			{
				if (parse(command, isCommandChar))
				{
					//TODO: Valid command, API calls to the robot
					isValidCommand = true;
				}
			}
			else if(commandChar == 'R' || commandChar == 'r')
			{
				if (parse(command, isCommandChar))
				{
					//TODO: Valid command, API calls to the robot
					isValidCommand = true;
				}			
			}
			else if(commandChar == 'L' || commandChar == 'l')
			{
				if (parse(command, isCommandChar))
				{
					//TODO: Valid command, API calls to the robot
					isValidCommand = true;
				}		
			}
			else if(commandChar == 'M' || commandChar == 'm')
			{
				isCommandChar = false;
				if (parse(command, isCommandChar))
				{
					//TODO: Valid command, API calls to the robot
					isValidCommand = true;
				}			
			}
			else if(commandChar == 'P' || commandChar == 'p')
			{
				if (parse(command, isCommandChar))
				{
					//TODO: Valid command, API calls to the robot
					isValidCommand = true;
				}				
			}
			else if(commandChar == 'C' || commandChar == 'c')
			{
				if (parse(command, isCommandChar))
				{
					//TODO: Valid command, API calls to the robot
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
					//TODO: Valid command, API calls to the robot
					isValidCommand = true;
					StringTokenizer tokenizer = new StringTokenizer(command);
					tokenizer.nextToken();
					String secondArg = tokenizer.nextToken();
					robotHdl = new Robot(Integer.parseInt(secondArg));
				}			
			}
			else if(commandChar == 'H' || commandChar == 'h')
			{
				//TODO: Print out available commands
				isValidCommand = true;
			}
			
			
			System.out.println("Command entered: " + command);
			if (!isValidCommand)
			{
				System.out.println("Invalid command. H or h to see the available commands");
			}
			
		}while(!isTerminated);

		System.out.println("Program ends");

	}

}
