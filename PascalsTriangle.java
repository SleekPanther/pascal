package week16;		//remove this package (folder) if you don't need it

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
		calculateRows(howManyRows);				//this calls another method which populates the array with the correct numbers
		
		pascalsTriangle[howManyRows-1][4]=100880;
		int maxLengthInLastRowElement = 1;		//this holds the actual element with the greatest numerical value. Initialized to 1
		for(int item: pascalsTriangle[howManyRows-1]){		//find the largest element by comparing string length of the integers (temporarily casts to string) 
			if(Integer.toString(item).length() > Integer.toString(maxLengthInLastRowElement).length()){
				maxLengthInLastRowElement = item;
			}
		}
		int maxLengthInLastRow = Integer.toString(  maxLengthInLastRowElement  ).length();		//this is how many digits to longest number is
		System.out.println("maxLengthInLastRowElement  " + maxLengthInLastRowElement);
		System.out.println("maxLengthInLastRow  " + maxLengthInLastRow + "\n");
		
		String[] lines = new String[howManyRows];		//this string array will hold all the values in the triangle, but 1 line at a time (for centering later)
		
		for(int i =0; i < howManyRows; i++){			//loop through each row in the array, calculate a spacer so each number gets extra space on each side & add to string array
			String tempRow = "";	//this holds the contents of each row each iteration & is added to the string array "lines" before tempRow is destroyed
			
			for(int j=0; j < pascalsTriangle[i].length; j++){	//loop through elements in each row
				int lengthOfCurrent = Integer.toString(  pascalsTriangle[i][j]  ).length();		//length of current thing in array row's cell
				
				double idealSpacer0 = (maxLengthInLastRow - lengthOfCurrent)/ 2.0;	
				//System.out.println("idealSpacer0:  " +idealSpacer0);
				int idealSpacer = (int)(Math.ceil(idealSpacer0) +2);
//				if (idealSpacer0 < 1){
//					idealSpacer = (int)(Math.ceil(idealSpacer0) +1);	//add 1 more for extra spacing
//				}
//				else{
//					idealSpacer = (int)(Math.ceil(idealSpacer0) +1);	//add 1 more for extra spacing
//				}
				
				//System.out.println("idealSpacer:  " +idealSpacer);
				String actualSpacer = " ";		//local for the 2 if statements. This is the "space" on either side of the number. Could be any character, but space is best here
				
				String tempElement = "";
				tempElement += (new String(new char[(int)idealSpacer0]).replace("\0", actualSpacer));
				tempElement += (pascalsTriangle[i][j]);
				tempElement += (new String(new char[idealSpacer]).replace("\0", actualSpacer));
				
				System.out.print(tempElement);		//comment out when not printing by individual elements (only helpful for testing)
				
				tempRow += tempElement;
			}
			lines[i] = tempRow;
			System.out.println();		//comment out when not printing by individual elements
		}
		
		
		int lenghOfLastStringLine = lines[howManyRows-1].length();		//counts how many characters in the last line, all 1 string now
		
		for(int i = 0; i < lines.length; i++){		//this prints out the actual data & used only 1 spacer
			String line = lines[i];		//temporary string to hold the current line
			double idealSpacer = (lenghOfLastStringLine - line.length()) / 2.0;		//calculate spacer: legth of the last line (the longest) minus length of the current line, divided by 2
			
			String actualSpacer = "-";		//string spacer on either end of the lines
			
			System.out.print(new String(new char[ (int)idealSpacer ] ).replace("\0", actualSpacer));		//print the left spacer
			System.out.print(line);		//print the actual number
			System.out.print(new String(new char[ (int)idealSpacer ] ).replace("\0", actualSpacer) + " Row " + i);		//print the right spacer followed by the row number
			
			System.out.println();	//print a new line after the spacer's are done
		}
	}
}