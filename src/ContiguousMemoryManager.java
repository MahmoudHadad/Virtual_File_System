import java.io.Serializable;
import java.util.ArrayList;


public class ContiguousMemoryManager extends MemoryManager implements Serializable{
	int  totalNumberOfBlocks;
	
	ArrayList<Integer> startBlocks;
	ArrayList<Integer> numOfEmptyBlocks;
	
	
//	ArrayList<Node> 
	
	public ContiguousMemoryManager(int totalNumberOfBlocks) {
		super();
		this.totalNumberOfBlocks = totalNumberOfBlocks;
		this. totalnumOfEmptyBlocks= totalNumberOfBlocks;
		System.out.println(totalNumberOfBlocks + " " + this.totalnumOfEmptyBlocks);
		startBlocks = new ArrayList<Integer>();
		numOfEmptyBlocks = new ArrayList<Integer>();
		startBlocks.add(0);
		numOfEmptyBlocks.add(totalNumberOfBlocks);
	
	}

	public int[] allocate(int numOfBlocks) {
		int indexOfMax = 0;
		int []arr = new int[1];
		for (int i = 0; i < numOfEmptyBlocks.size() ; i++) {
			if(numOfEmptyBlocks.get(i) > numOfEmptyBlocks.get(indexOfMax)){
				indexOfMax = i;
			}
		}
		if (numOfEmptyBlocks.get(indexOfMax) < numOfBlocks) {
			System.out.println("Faild Allocation");
			 arr[0] = -1;
			return arr;
		}else if(numOfEmptyBlocks.get(indexOfMax) == numOfBlocks){
			totalnumOfEmptyBlocks -=numOfBlocks;
			arr[0] = indexOfMax;
			startBlocks.remove(indexOfMax);
			numOfEmptyBlocks.remove(indexOfMax);
			return arr;
		}else if(numOfEmptyBlocks.get(indexOfMax) > numOfBlocks){
			totalnumOfEmptyBlocks -=numOfBlocks;
			
			int newSize = numOfEmptyBlocks.get(indexOfMax)-numOfBlocks;
			numOfEmptyBlocks.set(indexOfMax, newSize);
			newSize = startBlocks.get(indexOfMax)+numOfBlocks;
			startBlocks.set(indexOfMax, newSize);
			arr[0] = indexOfMax;
			return arr;
		}
		
		return null;
	}

	/*
	 * function DeAllocation take as an argument array
	 * the index0 is the index of the start block
	 * the index 1 in the number of blocks 
	 * it should increament the number of empty blocks and add index of the start block to startBlock array
	 * and add number of blocks to number of empty blocks array 
	 * */
	public boolean deAllocate(int[] blocks) {
		
		int indexOfBlock = blocks[0];
		int numOfBlocks = blocks[1];
		if(numOfBlocks > totalNumberOfBlocks-totalnumOfEmptyBlocks){
			System.out.println("Failed");
			return false;
		}
		totalnumOfEmptyBlocks+=numOfBlocks;
		startBlocks.add(indexOfBlock);
		numOfEmptyBlocks.add(numOfBlocks);
		return true;
	}

	

	//////////////////////////////////////////
	public void displayEmptyBlocks() {
		
		for (int i = 0; i < startBlocks.size(); i++) {
			int startB = startBlocks.get(i);
			for (int j = 0; j < numOfEmptyBlocks.get(i); j++) {
				System.out.print(startB + j+" ");
			}
		}
		System.out.println(1);
	}

	
	public void displayAllocatedBlocks() {
		
		int count = 0;
		
		for (int i = 0; i < startBlocks.get(0); i++) {
			System.out.print(i+" ");
		}
		
		for (int i = 0; i < startBlocks.size()-1; i++) {
			int startB = startBlocks.get(i);
			for (int j = startB + numOfEmptyBlocks.get(i)+1; j < startBlocks.get(i+1); j++) {
				System.out.println(j);
			}
		}
		int lastIndx = startBlocks.size()-1;
		int lastB = startBlocks.get(lastIndx);
		int lastNumOfBlocks = numOfEmptyBlocks.get(lastIndx);
		
		for (int i = lastB + lastNumOfBlocks+1; i < totalNumberOfBlocks; i++) {
			System.out.println(i);
		}
	}


}
