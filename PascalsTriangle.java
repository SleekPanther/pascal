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
		
		pascalsTriangle[howManyRows-1][4]=100880;
		int maxLengthInLastRowElement = 1;
		for(int item: pascalsTriangle[howManyRows-1]){
			if(Integer.toString(item).length() > Integer.toString(maxLengthInLastRowElement).length()){
				maxLengthInLastRowElement = item;
			}
		}
		int maxLengthInLastRow = Integer.toString(  maxLengthInLastRowElement  ).length();
		System.out.println("maxLengthInLastRow  " + maxLengthInLastRow);
		System.out.println("maxLengthInLastRowElement  " + maxLengthInLastRowElement);
		
		String[] lines = new String[howManyRows];
		
		for(int i =0; i < howManyRows; i++){
			//System.out.printf("%90s\n", Arrays.toString(pascalsTriangle[i]));
			
			String tempRows = "";
			
			for(int j=0; j < pascalsTriangle[i].length; j++){
				int lengthOfCurrent = Integer.toString(  pascalsTriangle[i][j]  ).length();		//length of current thing in array row's cell
				
				double idealSpacer0 = (maxLengthInLastRow - lengthOfCurrent)/ 2.0;	
				//System.out.println("idealSpacer0:  " +idealSpacer0);
				int idealSpacer = 0;
				if (idealSpacer0 < 1){
					idealSpacer = (int)(Math.ceil(idealSpacer0) +1);	//add 1 more for extra spacing
				}
				else{
					idealSpacer = (int)(Math.ceil(idealSpacer0) +1);	//add 1 more for extra spacing
				}
				
				//System.out.println("idealSpacer:  " +idealSpacer);
				String actualApacer = " ";		//local for the 2 if statements
				
				//Keep these lines for printing 
//				System.out.print(new String(new char[(int)idealSpacer0]).replace("\0", actualApacer));
//				System.out.print(pascalsTriangle[i][j]);
//				System.out.print(new String(new char[idealSpacer]).replace("\0", actualApacer));
				
				String tempElement = "";
				tempElement += (new String(new char[(int)idealSpacer0]).replace("\0", actualApacer));
				tempElement += (pascalsTriangle[i][j]);
				tempElement += (new String(new char[idealSpacer]).replace("\0", actualApacer));
				
				System.out.print(tempElement);		//also works for printing
				
				tempRows += tempElement;
			}
			lines[i] = tempRows;
			System.out.println();
		}
		
		int lenghOfLastStringLine = lines[howManyRows-1].length();		//counts how many characters in the last line, all 1 string now
		System.out.println(lenghOfLastStringLine);
		for(String line: lines){
			System.out.println(line);
		}
	}
}