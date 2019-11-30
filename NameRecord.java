/***********************************************************************
* Rachael Harvey and Sterly Deracy
* CSC 220-01 
* Project4- NameSurfer
* 
* Compilation: javac MyRandomWalkers.java
* Execution: java MyRandomWalkers N
*
*
*
***********************************************************************/

import java.util.Random;

public class NameRecord {
	
	private String name; // This is the name of the child name in the NameRecord object
	private int[] ranks; // The list of ranks in popularity of the child name
	private String[] rankStringValues; // Since the constructor will pass in a String line, this will be used to get the list of ranks of the child name and then parsed into int[] ranks.
	private final int DECADE_INCREMENT = 10; // 
	private final int START = 1900; // This is the actual beginning year of which the data for the child names were collected.
	private final int DECADES = 11; // This is a constant that defines how many decades we expect to receive from String record, the String line passed into the constructor.
	private final double LOWEST_RANK = 1100.0; // This is the lowest rank that a child name could have.
	private final double PEN_RADIUS = .025; // This is an assigned value that tells the plot method how large it must draw its points. After testing, a value of .025 seemed to be an ideal point size.
	
	public NameRecord (String record) { // Constructor takes the data of the line passed in and assigns variable name with the name of the child and ranks with the ranks in popularity of the child name over 11 decades.
		
		name = record.substring(0, record.indexOf(" ")); // Name is assigned to the value between the beginning of the line and the first space, otherwise known as the name of the child.
		record = record.substring(record.indexOf(" ")+1, record.length()); // Strings are immutable. Essentially, this takes record and excludes the name of the child from the line in order to parse the line for its ranks later on.
		ranks = new int[DECADES]; // Instantiates int array ranks with a size of DECADES, which is how many ranks will be provided since every 10 years the rank is computed.
		rankStringValues = new String[DECADES];	// Instantiates String array rankStringValues with a size of DECADES, which is how many ranks will be provided since every 10 years the rank is computed. 
		
		
		/* 
		* Because we know that the remaining part of String record will have 11 ranks,
		* we are able to use the split function which will use the spaces in between the ranks as a delimeter and populate the array rankStringValues with the ranks of variable record.
		*/
		
		rankStringValues = record.split(" "); 
		
		
		/* 
		* Using the array rankStringValues, we can then store the ranks into an int array.
		* Since both arrays are the same size, we can use a for loop to parse the value of the string array and store the value(which is the rank) into the int array.
		*/
		
	  for (int i = 0; i < rankStringValues.length; i++) {
			
			ranks[i] = Integer.parseInt(rankStringValues[i]);
			
		}
	
	}
	
	public String getName() { // Returns the name of the child.
		
		return name;
		
	}
	
	public int getRank(int decade) { // Returns the rank of the child given the decade. It makes sure the value passed in is a valid value, otherwise a -1 is returned.
		
		if (decade >= 0 && decade <= 10) { // If the decade passed in is in between 0 and 10, then it returns the rank in the decade specified.
		
			return ranks[decade];
		
		}
		
		return -1; // -1 is returned if the user enters an invalid decade.
		
	}
	
	public int bestYear() { // Returns the year of which the child name had its highest popularity.
		
		int max = 0; // Stores the maximum rank found
		int maxIndex = 0; // Stores the index of the maximum rank foound
		int popularYear = 0; // Variable used to return the best year of the child name
		
		/* 
		* This parses the array ranks to determine which index holds the maximum rank. 
		*The index is important because it is used to calculate the variable popularYear.
		*/
		
		for (int i = 0; i < ranks.length; i++) {
			
			if (max < ranks[i]) {
					
					max = ranks[i];
					maxIndex = i;
					
			}
	
		}
		
		popularYear = START + (maxIndex*DECADE_INCREMENT); 
		
		/*
		* Example: if the best year is 1940, the best rank will be held at index 4 because the decades are listed in numerical order. 
		* Because a rank is recorded every 10 years, DECADE_INCREMENT is multiplied by index to get the amount of years(40), which is then added to 1900 to get the year 1940.
		*/
		
		return popularYear; // Once popular year has been calculated, it's reported to the user.
		
	}
	
	public void plot() { // Plots the popularity of the name's ranks using a line graph over 11 decades.
		
		Random randomColor = new Random(); // randomColor is used to choose the color the graph is plotted in at random.
		int randomColorNum = randomColor.nextInt(13); // Because there are 13 possible colors, randomColor will have 13 possible values, 0 to 12.
		
		switch (randomColorNum) { // Switch statement that chooses the color based on the value of randomColorNum.
			
			case 0:
				StdDraw.setPenColor(StdDraw.BLACK);
				break;
			case 1:
				StdDraw.setPenColor(StdDraw.BLUE);
				break;
			case 2:
				StdDraw.setPenColor(StdDraw.CYAN);
				break;
			case 3:
				StdDraw.setPenColor(StdDraw.DARK_GRAY);
				break;
			case 4:
				StdDraw.setPenColor(StdDraw.GRAY);
				break;
			case 5:
				StdDraw.setPenColor(StdDraw.GREEN);
				break;
			case 6:
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				break;
			case 7:
				StdDraw.setPenColor(StdDraw.MAGENTA);
				break;
			case 8:
				StdDraw.setPenColor(StdDraw.ORANGE);
				break;
			case 9:
				StdDraw.setPenColor(StdDraw.PINK);
				break;
			case 10:
				StdDraw.setPenColor(StdDraw.RED);
				break;
			case 11:
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE); // Substituted Book Light Blue for white.
				break;
			case 12:
				StdDraw.setPenColor(StdDraw.YELLOW);
				break;	
			default:
				StdDraw.setPenColor(StdDraw.BLACK); // This should never be reached, but if it does, it will print an error statement, and set the default value to black.
				System.out.println("Random color number invalid. Setting color to black.");
				break;
				
		}
		
		// Because the graph is plotted using a line graph, there are two sets of variables, the old point and the new point which is used to draw the line.
		
		double oldX = 0; // Saves the data of the last point(or rank popularity) of coordinate X.
		double oldY = 0; // Saves the data of the last point(or rank popularity) of coordinate Y.
		double newX = 0; // Set to the current rank popularity of coordinate X. 
		double newY = 0; // Stores the current rank popularity of coordinate Y.
		
		for (int i = 0; i < ranks.length; i++) { // This for loop plots all of the points on the graph. It also draws the line from point to point.
			
			newX = i / (double)DECADES; // Because there are 11 decades/points being plotted on the graph, we will divide the graph into 11 areas to plot each point.
			
			/*
			* Since we need to stay within the bounds of 0 and 1, dividing the child name's rank by 1100 creates a fraction that represents how far down/up it is in relation to other points. 
			* This assumes that 1100 is the highest value, when in reality it is the lowest.
			* So in order to counteract that, the value is subtracted from 1 which results in an accurate representation of the rank on the screen given its popularity that decade.
			*/
			
			newY = 1 - (ranks[i]/LOWEST_RANK); 
								
			if (ranks[i] == 0) { // the rank being 0 is a special case, in which it didn't even show up on the top 1000 most popular names. It is set to the very bottom of the graph by default.
				
				newY = 0;
				StdDraw.setPenRadius(PEN_RADIUS);
				StdDraw.point(newX, 0);
				
			} else {	// If the rank is not 0, we want to actually plot the rank in relation to its popularity so it uses the values newX and newY that we compute.		
			
					StdDraw.setPenRadius(PEN_RADIUS);
					StdDraw.point(newX, newY);
					
			}
			
			/* 
			* Notice that oldX and oldY are assigned 0 by default. 
			* Because we do not want to have an issue where a line is created when there is only one point, 
			* we check to make sure that at least one of the values oldX or oldY not equal to 0, which would indicate that we now have plotted more than one point.
			* There is the possibility of not drawing a line if the first point has a ranking of 0, since the x would be 0 and the y would be 0. This is where the second check comes in.
			* We only want to draw the line if there is more than point on the screen, so we check if i-1 is equal to 0, which is really just
			* asking if we have plotted more than one point. This assures that even if the first point has a ranking of 0,
			* we will still be able to draw a line from one point to the next.
			*/
			
			if ((oldX != 0 || oldY != 0) || ((i - 1) == 0)) { 
				
				StdDraw.line(oldX, oldY, newX, newY);
				
			}
			
			oldX = newX; // Before the loop re-enters, we set oldX to newX so that the "current value" x coordinate then becomes the old value with the next iteration.
			oldY = newY; // Before the loop re-enters, we set oldX to newX so that the "current value" x coordinate then becomes the old value with the next iteration.
			
			
		}
		
	}
	
}