/***********************************************************************
* Rachael Harvey and Sterly Deracy
* CSC 220-01 
* Project4- NameSurfer
* 
* Compilation: javac NameSurfer.java
* Execution: java NameSurfer
*
* Simulates how far away after N steps random walk
*
*
***********************************************************************/

import java.util.Scanner;
import java.io.File;

public class NameSurfer {
	
	// Helper method that checks to see if the name entered by the user is within the name_data.txt file and returns the index of the name or -1 if the name is not found.
  private static int checkNames(NameRecord[] names, String nameChoice) {
		
		// Loops through the name_data.txt file.
		for (int i = 0; i < names.length-1; i++) {
					
		  // If the name entered by the user and the name at index i are the same, regardless the case, then the index of the name is returned. 
			if ((names[i].getName()).equalsIgnoreCase(nameChoice)) {
				
				return i;  // Returns the index of the name 
				
			}
					
		}
		
		return -1;  // Returns -1 if the name is not found in the file.
		
	}
	
	// Main method that runs the program continuously until the user enters 5.
	public static void main(String[] args) throws Exception {
		
		File file = new File("name_data.txt");    // Creates an instance of the name_data.txt file text.
		Scanner fileScanner = new Scanner(file);  // Scanner object created to parse the contents of the file.
		Scanner input = new Scanner(System.in);   // Creates a scanner object that will be used to read input from the user. 
		
		int userSelection = 0;                               // Initializes the user's menu selection to 0.
		String nameChoice = "";                              // String nameChoice that will hold the user's input for the name.
    final int LINES_IN_FILE = 4430;                      // final int that contains the number of lines in the name_data.txt file.
		NameRecord[] names = new NameRecord[LINES_IN_FILE];  // Array of NameRecord objects set to the number of lines in the file.
		int count = 0;                                       // Count variable is used to increment the array index to populate the elements of the array with NameRecord objects.
		int namePlace = 0;                                   // Index of the name chosen by the user in the names array.
		
		// While there is still another line in the file. 
		while (fileScanner.hasNextLine()) {
		
		  // Create an object of the line and add it to the array. 
			NameRecord childName = new NameRecord(fileScanner.nextLine());
			
			// If the line in the file is not empty the element of the array at index count is set to the NameRecord object childName and increments count by 1.
			if (!childName.equals(" ")) {
		
				names[count] = childName;  // The element of the array at index count is set to the NameRecord object childName
				count++;                   // Count is incremented by 1.
		
			}
		
		}
		
		// While the user does not enter 5(quit) the menu continues to be prompted to the user. 
		while (userSelection != 5) {
			
			
			// Menu options for the user. 
			System.out.println("1 - Find best year for a name");
			System.out.println("2 - Find best rank for a name");
			System.out.println("3 - Plot popularity of a name");
			System.out.println("4 - Clear plot");
			System.out.println("5 - Quit");
			System.out.print("Enter your selection. ");
			userSelection = input.nextInt();  // Reads in the user's selection and sets it to userSelection.
			System.out.println("");           // By adding a new line after the input the user has a more readible application. 
			input.nextLine();                 // Prevents the scanner from skipping over necessary input.
			
			// If the user's selection option is between 1 and 3 inclusive.
			if (userSelection >= 1 && userSelection <= 3) {
				
				System.out.print("Enter a name: ");         // Prompts the user to enter a name.
				nameChoice = input.nextLine();              // nameChoice is assigned to the user input. 
				
				/* Variable namePlace is assigned to the index of the chosen word in the object array if it is found, otherwise it is assigned -1. The checkNames method is invoked using
				*  the object array and the user's name choice and determines if the name chosen is within the file.
				*/
				namePlace = checkNames(names, nameChoice);  
				
			}
			
			// Switch statements that will be used to determine the program function based on the user input.
			switch (userSelection) { 
				
				// If the user enters 1 the program outputs the best year for the user-specified name.
				case 1: 
				    
						// If the user-specified name is found in the file outputs the best year for the user-specified name.
						if (namePlace != -1) {
					
				      // Displays the best year for the user-specified name.
							System.out.println("The best year for " + nameChoice + " is: " + names[namePlace].bestYear() + "\n");
								
						// If the user-specified name is not found in the file an error message is printed to the user.
						}  else {
							
								System.out.println("Could not find name.\n");
							
						}
							
					break; // End of case 1
				
        // If the user enters 2 the program outputs the best rank for a user-specified name or an error message, if the name is not found in the file.				
				case 2:	
				
					int bestDecade = 0;  // Int variable that will be used to calculate the best rank for the name. This will be needed later since the getRank() method needs to have a decade argument

            // -1 is the default value if a name is not found in the file. If namePlace is not -1 then that means the name is found in the file.
						if (namePlace != -1) {
								
							bestDecade = (names[namePlace].bestYear()%100) / 10;  // Calculates the best decade for a name by taking the remainder of the best year of the name modulo 100 then divides that number by 10.
							// Once we have the best decade, we pass the decade into the method getRank which then outputs the best rank of the name. 
							System.out.println("The best rank for " + nameChoice + " is: " + names[namePlace].getRank(bestDecade) + "\n");
								
						} else {
							
								System.out.println("Could not find name.\n");
							
						}
							
					break;  // End of case 2
					
				// If the user enters 3 the program plots the popularity of the name of 11 decades. If the name has not been found an error message is printed the program does not plot anything. 	
				case 3:
				
				    // If the name has been found in the file the program plots the popularity of the name over 11 decades.
						if (namePlace != -1) {		
								
							names[namePlace].plot();  // Plots popularity 
						
						}  else {
							
								System.out.println("Could not find name.\n");  // Prints an error message telling the user the name was not found.
							
						}
							
					break;  // End of case 3
				
				// If the user enters 4 the program clears the plot.
				case 4:
				
					StdDraw.clear();  // Clears the plot
					System.out.println("Plot cleared.\n");
					
					break;  // End of case 4
				
        // If the user enters 5 then the Scanner object invokes the close method.				
				case 5:
				
					input.close();
					
					break;  // End of case 5
				
        // If the user enters a selection other than 1, 2, 3, 4 or 5.				
				default:
				
					System.out.println("Invalid Entry.\n");  // Displays an error message telling the user that their input is invalid.
					
					break;  // End of default case 
			}
		
		}
		
	}

}
