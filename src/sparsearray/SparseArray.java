package sparsearray;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class SparseArray<T> {
	
	private List<T> data;
	private List<Integer> position;
	private int[] offset;
	private int rows;
	private int columns;
	private T zero;
		
	
	public SparseArray(T zero,T[][] denseArray){
		initArrays(denseArray.length);
		this.zero = zero;
		this.rows = denseArray.length;
		this.columns = denseArray[0].length;
		buildArrays(denseArray);
	}
	
	public SparseArray(List<T> data,List<Integer> position,int[] offset,int rows,int collumns,T zero){
		
		this.data = data;
		this.position = position;
		this.offset = offset;
		this.rows = rows;
		this.columns = collumns;
		this.zero = zero;
		
	}
	
	private void initArrays(int length){
		setData(new ArrayList<T>());
		setPosition(new ArrayList<Integer>());
		setOffset(new int[length+1]);
		for (int i = 0; i < offset.length; i++) {
			offset[i] = 0;
		}
	}
	
	private void buildArrays(T[][] denseArray){
		int counter=0;
		for (int i = 0; i < denseArray.length; i++) {
			
			for (int j = 0; j < denseArray[0].length; j++) {
				if (denseArray[i][j] != zero){
					data.add(denseArray[i][j] );
					position.add(j);
					counter++;
				}
			}
			offset[i+1] = counter;		
		}
	}
	
	public T[][] getArray(){
		
		@SuppressWarnings("unchecked")
		T[][] array = (T[][])new Object[getRows()][getColumns()];// Array.newInstance(SparseArray.class, getRows(),getColumns());//new T[getRows()][getColumns()];
		
		for (int i = 0; i < getRows(); i++) {
			
			for (int j = 0; j < getColumns(); j++) {
				
				array[i][j] =getZero();
				
			}
			
		}
		
		for (int n = 0; n < getRows(); n++) {
			
			for (int i = getOffset(n); i < getOffset(n+1); i++) {
				
					array[n][getPosition().get(i)] =  getData().get(i);
				
			}
		}	
		return(array);
		
	}
	
	public void printArray(){
		
		T[][] array = getArray();
		
		for (int i = 0; i < getRows(); i++) {
			
			System.out.println();
			
			for (int j = 0; j < getColumns(); j++) {
				
				System.out.print(array[i][j]+" ");
				
			}
			
		}
		System.out.println();
		
	}
	
	
	public void printSparseData(){
		System.out.println("data");
		for (Iterator<T> iterator = data.iterator(); iterator.hasNext();) {
			//System.out.format("%5d ",(T) iterator.next());	
			System.out.println(iterator.next()+" ");	
		}
		System.out.println();
		System.out.println("position");
		for (Integer p : position) {
			//System.out.format("%5d ",p);
			System.out.println(p+" ");	
		}		
		System.out.println();
		System.out.println("offset");
		for (int i = 0; i < offset.length; i++) {
			System.out.print(offset[i]+" ");
		}
		System.out.println();
	}
	
	public abstract SparseArray<T>  arrayMultiplication(SparseArray<T> sArray) throws Exception;
	public abstract SparseArray<T>  transpose() throws Exception;
	public abstract SparseArray<T>  arrayAddition(SparseArray<T> sArray) throws Exception;
	
	public abstract T mult(T obj1,T obj2);
	
	public abstract T add(T obj1,T obj2);


	public List<Integer> getPosition() {
		return position;
	}

	public void setPosition(List<Integer> position) {
		this.position = position;
	}

	public int[] getOffset() {
		return offset;
	}
	
	public int getOffset(int val) {
		return offset[val];
	}

	public void setOffset(int[] offset) {
		this.offset = offset;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	public int getColumns(){
		return this.columns;
	}
	
	public int getRows(){
		return this.rows;
	}

	public T getZero(){
		return this.zero;
	}

}
