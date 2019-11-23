import java.util.Random;

public class NameRecord {
	
	private String input;
	private String name;
	private int[] ranks;
	private String[] values; //change this
	private final int DECADE_INCREMENT = 10;
	private final int START = 1900;
	//decade = 0 and decade = 1 examples can be done using num % 100
	
	public NameRecord (String record) {
		
		name = record.substring(0, record.indexOf(" "));
		record = record.substring(record.indexOf(" ")+1, record.length());
		ranks = new int[11];
		values = new String[11];	
		
		values = record.split(" ");
		
	  for (int i = 0; i < values.length; i++) {
			
			ranks[i] = Integer.parseInt(values[i]);
			
		}
	
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public int getRank(int decade) {
		
		if (decade >= 0 && decade <= 10) {
		
		return ranks[decade];
		
		}
		
		return -1;
		
	}
	
	public int bestYear() {
		
		int max = 0;
		int maxIndex = 0;
		int popularYear = 0;
		
		for (int i = 0; i < ranks.length; i++) {
			
			if (max < ranks[i]) {
					
					max = ranks[i];
					maxIndex = i;
					
			}
	
		}
		
		popularYear = START + (maxIndex*DECADE_INCREMENT);
		return popularYear;
		
	}
	
	public void plot() {
		
		Random randomColor = new Random();
		int randomColorNum = randomColor.nextInt(13);
		
		switch (randomColorNum) {
			
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
				StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE); //should be white
				break;
			case 12:
				StdDraw.setPenColor(StdDraw.YELLOW);
				break;	
			default:
				StdDraw.setPenColor(StdDraw.BLACK);
				System.out.println("AAAAA THERE IS A VERY BIG ISSUE HERE");
				break;
				
		}
		
		System.out.println("color num: " + randomColorNum);
		
		for (int i = 0; i < ranks.length; i++) {
			
			double x = i/11.0;
			double y = 1.0/ranks[i];
			
			if (ranks[i] == 0) {
			
			y = 0;
			StdDraw.setPenRadius(.025);
			StdDraw.point(0.5, 0.7);
			//StdDraw.line(0,0,0.5,0.5);
			
			} else {			
			StdDraw.setPenRadius(.025);
			StdDraw.point(0.2,0.2);
			//StdDraw.line(0,0,x,y);
			
			}
			
			System.out.println("X: " + x);
			System.out.println("Y: " + y);
			
		}
		
	}
	
}