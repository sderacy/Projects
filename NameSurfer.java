import java.util.Scanner;
import java.io.File;

public class NameSurfer {
	 
	public static void main(String[] args) throws Exception {
		
		File file = new File("name_data.txt");
		Scanner fileScanner = new Scanner(file);
		Scanner input = new Scanner(System.in);
		int userSelection = 0;
		String nameChoice = "";
		NameRecord[] names = new NameRecord[4430];
		int count = 0;
		
		
		while (fileScanner.hasNextLine()) {
		
		NameRecord childName = new NameRecord(fileScanner.nextLine());
		if (!childName.equals(" ")) {
		
		names[count] = childName;
		count++;
		
		}
		
		}
		
		while (userSelection != 5) {
		
		System.out.println("1 - Find best year for a name");
		System.out.println("2 - Find best year for a name");
		System.out.println("3 - Find best year for a name");
		System.out.println("4 - Find best year for a name");
		System.out.println("5 - Quit");
		System.out.println("Enter your selection.");
		userSelection = input.nextInt();
		input.nextLine();
		
		switch (userSelection) {
			
			case 1: 
				System.out.println("Enter a name: ");
				nameChoice = input.nextLine();
				System.out.println(nameChoice);
				for (int i = 0; i < names.length-1; i++) {
					
					if ((names[i].getName()).equals(nameChoice)) {
							
							System.out.println(names[i].bestYear());
							
					}
						
				}
				break;
			case 2:	//add boolean flag
				System.out.println("Enter a name: ");
				nameChoice = input.nextLine();
				System.out.println(nameChoice);
				int bestDecade = 0;
				for (int i = 0; i < names.length-1; i++) {
					
					if ((names[i].getName()).equals(nameChoice)) {
							
							bestDecade = names[i].bestYear()%100/10;
							System.out.println(names[i].getRank(bestDecade));
							
					}
						
				}
				break;
			case 3:
				System.out.println("Enter a name: ");
				nameChoice = input.nextLine();
				System.out.println(nameChoice);
				for (int i = 0; i < names.length-1; i++) {
					
					if ((names[i].getName()).equalsIgnoreCase(nameChoice)) {
							
							names[i].plot();
							
					}
						
				}
				break;
			case 4:
				StdDraw.clear();
				break;
			case 5:
				break;
		}
		
		}
		
	}
	
}