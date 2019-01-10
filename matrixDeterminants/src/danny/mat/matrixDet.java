package danny.mat;

import java.util.Random;
import java.util.Scanner;

public class matrixDet {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean valid = false;
		
		int dimension = 0;
		
		while(!valid) {
			try {
				System.out.println("Insert a dimension i.e: 2 (for a 2x2)");
				dimension=scan.nextInt();
				if(dimension<0) {
					dimension=dimension*-1;
				}
				valid=true;
			} catch(Exception e) {
				System.out.println("Invalid dimension - try again");
				valid=false;
			}
		}
		scan.close();	
		
		int[][] mat = new int[dimension][dimension];
		Random rand = new Random();
		
		System.out.println();		
		for(int x=0;x<dimension;x++) {
			for(int y=0;y<dimension;y++) {
				mat[x][y] = rand.nextInt() % 10;
				System.out.print("	"+mat[x][y]+"	");
			}
			System.out.println();
		}
		System.out.println();	

		System.out.println("Loading... Please wait.");
		System.out.println("Det is: "+det(mat));
	}
	
	//splits to make a new mat
	private static int[][] newMat(int[][] mat,int x){
		try {
			boolean hasPassedX = false;
			int[][] temp = new int[mat[0].length-1][mat[0].length-1];
			
			for (int i=0;i<mat[0].length;i++) {
				if(i==x) {
					hasPassedX=true;
				} else {
					if(!hasPassedX) {
						for(int y = 0;y<mat[0].length-1;y++) {
							temp[i][y] = mat[i][y];
						}
					} else {
						for(int y = 0;y<mat[0].length-1;y++) {
							temp[i-1][y] = mat[i][y];
						}
					}
				}
			}

			return temp;
		} catch(Exception e) {
			throw new Error("Dimension error for matrix");
		}
			
	}
	
	public static int det(int[][] mat) {
		int mult = 1;
		int out = 0;
		
		if(mat[0].length==2) {
			return (mat[0][0]*mat[1][1]) - (mat[0][1]*mat[1][0]);
		}
		
		for(int x = 0;x < mat[0].length; x++) {			
			out += mult * mat[x][mat[0].length-1] * det(newMat(mat,x));
			mult = mult * - 1;
		}
		
		return out;
	}

}
