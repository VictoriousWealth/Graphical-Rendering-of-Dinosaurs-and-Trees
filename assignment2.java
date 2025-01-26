import sheffield.EasyGraphics;
import sheffield.EasyReader;

public class Assignment2 {
	
	final static int WIDTH = 246 * 3, HEIGHT = 70 * 2, MARGIN = 15, 
	TREE_WIDTH = 19 * 2;
	
	public static void main(String[] args) {
	
		EasyReader reader = new EasyReader("dino.txt");
		int[][] arrayForDisplayingDinosaurs = new int[HEIGHT][WIDTH], 
		arrayForTreeImage = new int[HEIGHT][TREE_WIDTH];
		int rowForBigDino = 0, colForBigDino = 0, rowForTree = 0, colForTree = 0;
		
		for (int rowForSmallDino = 0; rowForSmallDino < HEIGHT / 2;
		rowForSmallDino++) {
			for(int colForSmallDino = WIDTH - 1; colForSmallDino >= WIDTH / 3 * 2; 
			colForSmallDino--)/* Decreasing loop as I want the smaller dinasour to 
			face the leftside of the screen */ {
				
				int charInNumericFormat = ((int) (reader.readChar()));
				
				// First filling the info for the smaller dinasour
				//The use of (HEIGHT/2-1-rowForSmallDino) inverts the dinasour upside down
				arrayForDisplayingDinosaurs[HEIGHT / 2 - 1 - rowForSmallDino]
				[colForSmallDino] = charInNumericFormat;
				
				// Now filling the info for the bigger dinasour  
				// The use of (HEIGHT-1-rowForBigDino) inverts the dinasour upside down
				arrayForDisplayingDinosaurs[HEIGHT - 1 - rowForBigDino][colForBigDino] = 
				charInNumericFormat;
				arrayForDisplayingDinosaurs[HEIGHT - 1 - (rowForBigDino + 1)]
				[colForBigDino] = charInNumericFormat;
				arrayForDisplayingDinosaurs[HEIGHT - 1 - (rowForBigDino + 1)]
				[colForBigDino + 1] = charInNumericFormat;
				arrayForDisplayingDinosaurs[HEIGHT - 1 - rowForBigDino][colForBigDino + 1]
				= charInNumericFormat;
				
				colForBigDino+=2;
				
				// now filling the tree array
				//  The use of (HEIGHT-1-rowForTree) inverts the dinasour upside down
				if (WIDTH-1-colForSmallDino < TREE_WIDTH / 2) {
					arrayForTreeImage[HEIGHT - 1 - rowForTree][colForTree] = 
					charInNumericFormat;
					arrayForTreeImage[HEIGHT - 1 - rowForTree][colForTree + 1] = 
					charInNumericFormat;
					arrayForTreeImage[HEIGHT - 1 - (rowForTree + 1)][colForTree + 1] = 
					charInNumericFormat;
					arrayForTreeImage[HEIGHT - 1 - (rowForTree + 1)][colForTree] = 
					charInNumericFormat;
					colForTree += 2;
				}
			}
			
			colForBigDino = 0;
			colForTree = 0;
			
			rowForBigDino += 2;
			rowForTree += 2;
		}
		
		displayingToScreen(arrayForDisplayingDinosaurs, arrayForTreeImage);
	}
	
	/* The reason why MARGIN*2 is a thing is because we have a left, right, top 
	and bottom margin, each 15px big */
	/* The reason why -21 is a thing is because that's the rightmost pixel of the
	tree's trunk if there isn't any shift applied and after reading the QnA I 
	noticed this was meant to be done.*/
	private static void displayingToScreen(int[][] arrayForDisplayingDinosaurs, 
	int[][] arrayForTreeImage) {
		
		EasyGraphics g = new EasyGraphics(WIDTH + MARGIN * 2, HEIGHT + MARGIN * 2);
		
		// do background
		g.setColor(173, 216, 230); // light-blue for sky
		g.fillRectangle(0, 0, WIDTH + MARGIN * 2, HEIGHT + MARGIN * 2);
		
		// do grass
		g.setColor(50, 89, 0); // green version 1 for grass
		g.fillRectangle(0, 0, WIDTH + MARGIN * 2, 30);
		
		// do tree
		int offset, count = -1;
		while(++count < 20) {
			offset = (int) (Math.random() * (WIDTH + MARGIN * 2)) + 1;
			for(int row = 0; row < HEIGHT; row++) {
				for (int col = 0; col < TREE_WIDTH; col++) {
					if(arrayForTreeImage[row][col] % 3 == 0) {
						g.setColor(165, 42, 42); // brown for trunk
						g.plot(col - 21 + offset, row);
					} else if(arrayForTreeImage[row][col] % 5 == 0) {
						g.setColor(79, 121, 66); // green version 2 for leaves
						g.plot(col - 21 + offset, row);
					}
				}
			}	
		}
		
		// do dinasours
		for(int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				if(arrayForDisplayingDinosaurs[row][col] % 2 == 0 && 
					arrayForDisplayingDinosaurs[row][col] != 0) {
						g.setColor(150, 150, 150); // grey for dinasours
						g.plot(col + MARGIN, row + MARGIN);
				}
			}
		}
	}

}