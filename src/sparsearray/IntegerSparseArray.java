package sparsearray;

import java.util.ArrayList;
import java.util.List;

public class IntegerSparseArray extends SparseArray<Integer> {
		
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
	
	@Override
	public SparseArray<Integer> transpose() throws Exception {
		
		//System.out.println(getColumns());
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] groupData = (ArrayList<Integer>[])new ArrayList[getColumns()];
		for (int n = 0; n < getColumns(); n++) {
			groupData[n] = new ArrayList<>();
		}
		
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] groupPos = (ArrayList<Integer>[])new ArrayList[getColumns()];
		for (int n = 0; n < getColumns(); n++) {
			groupPos[n] = new ArrayList<>();
		}
		
		for (int n = 0; n < getRows(); n++) {
			
			
			for (int i = getOffset(n); i < getOffset(n+1); i++) {
					
				
				groupData[getPosition().get(i)].add(getData().get(i));
				groupPos[getPosition().get(i)].add(n);
					
				
			}
		}
		
		//build sparse array lists and arrays
		int[] offset = new int[getColumns()+1];
		offset[0]=0;
		
		for (int n = 1; n < getColumns()+1; n++) {
			
			offset[n] = offset[n-1] + groupData[n-1].size();
		}
			
		ArrayList<Integer> data = new ArrayList<Integer>();
		for (int n = 0; n < getColumns(); n++) {
			
			data.addAll(groupData[n]);
		}
		
		ArrayList<Integer> position = new ArrayList<Integer>();
		for (int n = 0; n < getColumns(); n++) {
			position.addAll(groupPos[n]);
		}
		
		return new IntegerSparseArray(data,position,offset,getColumns(),getRows());
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



