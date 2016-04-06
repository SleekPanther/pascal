package week16;

import java.util.ArrayList;
import java.util.Arrays;

public class PascalsTriangle {
	//private ArrayList<Double> pascalsTriangle = new ArrayList<Double>();
	private static int[][] pascalsTriangle;
	private static ArrayList<ArrayList<Double>> listOfLists = new ArrayList<ArrayList<Double>>();
	
	public static void main(String[] args){		
		printRows(10);
	}

	private static void printRows(int howManyRows) {
		pascalsTriangle = new int[howManyRows][];
		
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
		System.out.println(Arrays.deepToString(pascalsTriangle));
	}
}