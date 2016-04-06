package week16;		//remove this package (folder) if you don't need it

import java.util.Arrays;

/**
 * This class used a 2d array to calculate & display Pascal's triangle. Don't overload it by calculating 6000 rows or your computer might freeze
 * @author Noah Patullo
 */
public class PascalsTriangle {
	private static int[][] pascalsTriangle;		//2d array hold rows of integers for the triangle
	
	public static void main(String[] args){		
		printRows(10);		//call the method to actually display
	}

	/**
	 * This method does all the hard work to calculate the numbers
	 * Each subsequent row increases in by 1 in length
	 * The 1st & last elements in a row are always 1
	 * The other elements are found by looking at the previous row & summing the 2 adjacent "parent" cells  
	 * @param howManyRows (needs to know how many rows it should calculate)
	 */
	private static void calculateRows(int howManyRows) {
		pascalsTriangle = new int[howManyRows][];	//initialize how many rows
		
		//The 1st 2 rows are hard coded so the rest have somewhere to start
		pascalsTriangle[0] = new int[1];	//first row (only holds 1 element)
		pascalsTriangle[0][0]=1;			//set the value to 1
		
		pascalsTriangle[1] = new int[2];	//the second row contains [1,1], so it's initialized with length 2
		pascalsTriangle[1][0]=1;
		pascalsTriangle[1][1]=1;
		
		
		int rowSize = 3;		//this counter increased the row size for each iteration (starting @ 3 since the 1st 2 rows are hard coded)
		for(int row = 2; row < howManyRows; row++){		//start @ row index 2, go until reached "howManyRows", & increment by 1
			pascalsTriangle[row] = new int[rowSize];	//create a rows based on the current size. Starts @ 3, then 4, 5, 6 ...
			pascalsTriangle[row][0] = 1;		//set the 0th element in the row to 1 (a rule of pascal's triangle) 
			
			for(int col = 1; col < rowSize-1; col++){	//start at 1 to skip the 0th element, "< rowSize-1" really goes until 2 less than rowSize. 1 less so that the last element is filled in as a 1 & the other "1 less" is because arrays start indexes @ 0  
				pascalsTriangle[row][col] = pascalsTriangle[row - 1][col-1] + pascalsTriangle[row - 1][col];
				//value is sum of 2 items in the previous row hence [row - 1] for both. Since the current column is 1 to the right of the preceeding row the first element in the above row is [col-1] and the 2nd is just [col] (right above)
			}
			pascalsTriangle[row][rowSize-1] = 1;		//set the last element in the row to 1 (a rule of pascal's triangle)
			
			rowSize++;		//increment the size of the row
		}
	}
	
	/**
	 * This method simply calls calculateRows, & prints the array when it has all the values 
	 * @param howManyRows
	 */
	private static void printRows(int howManyRows) {
		calculateRows(howManyRows);				//simply pass on the parameter to the next method
		System.out.println(Arrays.deepToString(pascalsTriangle));
	}
}