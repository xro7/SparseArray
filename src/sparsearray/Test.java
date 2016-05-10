package sparsearray;

import java.util.Random;

public class Test {

	public static void main(String[] args){


		Random rand = new Random();
		Integer[][] array = new Integer[3][3];
		//Integer[][] array =new Integer[][]{{0,2,0},{1,0,0},{0,1,0},{0,0,0}};
		//Integer[][] array =new Integer[][]{{2},{1},{1}};
		Integer[][] array2 = new Integer[3][3];
		//Integer[][] array2 =new Integer[][]{{2,0,0},{0,0,1},{1,0,3}};
		//Integer[][] array2 =new Integer[][]{{0,2,1}};
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				//array[i][j] = rand.nextInt(9);
				if (rand.nextInt(10)>4){
					array[i][j] = 0;
				}else{
					array[i][j] = rand.nextInt(10);
				}
			}
		}
		
		for (int i = 0; i < array2.length; i++) {
			for (int j = 0; j < array2[0].length; j++) {
				if ( rand.nextInt(10)>4){
					array2[i][j] = 0;
				}else{
					array2[i][j] = rand.nextInt(10);
				}
			}
		}
		IntegerSparseArray sp = new IntegerSparseArray(array);
	
		//sp.printSparseData();
		sp.printArray();
		IntegerSparseArray  sp2 = new IntegerSparseArray(array2);
		//sp2.printSparseData();
		sp2.printArray();
		IntegerSparseArray a;
		try {
			a = (IntegerSparseArray) sp.arrayMultiplication(sp2);
			//a.printSparseData();
			a.printArray();
			IntegerSparseArray b = (IntegerSparseArray) a.transpose();
			b.printArray();
			//b.printSparseData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		IntegerSparseArray add;
		try {
			add = (IntegerSparseArray) sp.arrayAddition(sp2);
			//add.printSparseData();
			add.printArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		
	}
}
