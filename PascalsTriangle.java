package week16;		//remove this package (folder) if you don't need it

import java.util.Arrays;

/**
 * This class used a 2d array to calculate & display Pascal's triangle. Don't overload it by calculating 6000 rows or your computer might freeze
 * @author Noah Patullo
 */
public class PascalsTriangle {
	private static int[][] pascalsTriangle;		//2d array hold rows of integersfor the triangle
	
	public static void main(String[] args){		
		printRows(10);		//call the method to actually display
	}

	/**
	 * This method does all the hard work to calculate the numbers
	 * @param howManyRows (needs to know how many rows it should calculate)
	 */
	private static void calculateRows(int howManyRows) {
		pascalsTriangle = new int[howManyRows][];	//initialize how many rows
		
		pascalsTriangle[0] = new int[1];
		pascalsTriangle[0][0]=1;
		
		pascalsTriangle[1] = new int[2];
		pascalsTriangle[1][0]=1;
		pascalsTriangle[1][1]=1;
		
		int rowSize = 3;
		for(int row = 2; row < howManyRows; row++){		//start @ row 2
			pascalsTriangle[row] = new int[rowSize];
			pascalsTriangle[row][0] = 1;
			
			for(int col = 1; col < rowSize-1; col++){	//start at once to skip the 0th element
				//pascalsTriangle[row][col] = 33;
				pascalsTriangle[row][col] = pascalsTriangle[row - 1][col-1] + pascalsTriangle[row - 1][col ];
			}
			pascalsTriangle[row][rowSize-1] = 1;
			
			rowSize++;
		}
		
	}
	
	private static void printRows(int howManyRows) {
		calculateRows(howManyRows);
		System.out.println(Arrays.deepToString(pascalsTriangle));
	}
}