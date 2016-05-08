package sparsearray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntegerSparseArray extends SparseArray<Integer> {
	
	public static void main(String[] args){


		Random rand = new Random();
		//Integer[][] array = new Integer[50][100];
		Integer[][] array =new Integer[][]{{0,2,0},{1,0,0},{0,1,0},{0,0,0}};
		//Integer[][] array =new Integer[][]{{2},{1},{1}};
		//Integer[][] array2 = new Integer[100][10];
		Integer[][] array2 =new Integer[][]{{2,0,0},{0,0,1},{1,0,4}};
		//Integer[][] array2 =new Integer[][]{{0,2,1}};
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				array[i][j] = rand.nextInt(2);
			}
		}
		
		for (int i = 0; i < array2.length; i++) {
			for (int j = 0; j < array2[0].length; j++) {
				array2[i][j] = rand.nextInt(2);
			}
		}
		SparseArray<Integer> sp = new IntegerSparseArray(array);
		sp.printSparseData();
		sp.printArray();
		SparseArray<Integer> sp2 = new IntegerSparseArray(array2);
		sp2.printSparseData();
		sp2.printArray();
		IntegerSparseArray a;
		try {
			a = (IntegerSparseArray) sp.arrayMultiplication(sp2);
			a.printSparseData();
			a.printArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IntegerSparseArray(Integer[][] denseArray){
		super(0,denseArray);
	}
	
	public IntegerSparseArray(List<Integer> data,List<Integer> position,int[] offset,int rows,int columns){
		super(data,position,offset,rows,columns,0);
	}
	

	@Override
	public SparseArray<Integer> arrayMultiplication(SparseArray<Integer> sArray) throws Exception{
		
		if (getColumns()!= sArray.getRows()){
			throw new Exception("Dimensions do not match");
		}
		
		List<Integer> data = new ArrayList<Integer>();
		List<Integer> position = new ArrayList<Integer>();
		int[] offset = new int[getRows()+1];
		offset[0]=0;
		List<Integer> rowlist = new ArrayList<Integer>();
		List<Integer> positionlist = new ArrayList<Integer>();
		int counter = 0;
		

		for (int n = 0; n < this.getRows(); n++) {
			
			init(rowlist,positionlist,sArray.getColumns());
			for (int i = getOffset(n); i < getOffset(n+1); i++) {
				
				for (int j = sArray.getOffset(getPosition().get(i)); j < sArray.getOffset(getPosition().get(i)+1); j++) {					
				
					rowlist.set(sArray.getPosition().get(j),add(rowlist.get(sArray.getPosition().get(j)),mult(getData().get(i), sArray.getData().get(j))));
					positionlist.set(sArray.getPosition().get(j),sArray.getPosition().get(j));
				}						
			}

			for (Integer obj : rowlist) {
				if (obj!=this.getZero()){
					data.add(obj);
					counter++;
				}
			}
			for (Integer integer : positionlist) {
				if (integer!=-1){
					position.add(integer);
				
				}
			}
			offset[n+1] = counter;
			init(rowlist,positionlist,sArray.getColumns());
	
		}
		
		return new IntegerSparseArray(data,position,offset,getRows(),sArray.getColumns());

	}
	
	private void init(List<Integer> rowlist,List<Integer> positionlist,int length){
		rowlist.clear();
		positionlist.clear();
		
		for (int i = 0; i <length; i++) {			
			rowlist.add(0);	
			positionlist.add(-1);
		}
	}
	
	@Override
	public Integer mult(Integer obj1, Integer obj2) {
		// TODO Auto-generated method stub
		return (obj1 * obj2);
	}

	@Override
	public Integer add(Integer obj1, Integer obj2) {
		// TODO Auto-generated method stub
		return (obj1+obj2);
	}

}



