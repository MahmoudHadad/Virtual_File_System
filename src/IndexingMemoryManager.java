import java.io.Serializable;


public class IndexingMemoryManager extends MemoryManager implements Serializable{
	private int totalNumOfBlocks;

	private int[] allBlocks;
	
	
	public IndexingMemoryManager(int totalNumOfBlocks){
		super();
		this.totalNumOfBlocks = totalNumOfBlocks;
		this.totalnumOfEmptyBlocks = totalNumOfBlocks;
		System.out.println(totalNumOfBlocks + " " + this.totalnumOfEmptyBlocks);
		this.allBlocks = new int[totalNumOfBlocks];
		for (int i = 0; i < allBlocks.length; i++) {
			allBlocks[i] = 1;
		}
	}
	
	public int[] allocate(int numOfBlocks) {
		numOfBlocks++;
		if(numOfBlocks > totalnumOfEmptyBlocks){
			System.out.println("Faild");
			return null;
		}
		int []arr = new int[numOfBlocks];
		int j=0;
		for (int i = 0; i < allBlocks.length; i++) {
			if(allBlocks[i] == 1){
				allBlocks[i]=0;
				arr[j] = i;
				j++;
				totalnumOfEmptyBlocks--;
			}
		}
		return arr;
	}

	public boolean deAllocate(int[] blocks) {
		if(blocks.length > allBlocks.length-totalnumOfEmptyBlocks){
			System.out.println("Failed DeAllocation");
			return false;
		}
		for (int i = 0; i < blocks.length; i++) {
			allBlocks[blocks[i]] = 1;
		}
		return true;
	}
	
	
	public void displayEmptyBlocks() {
		for (int i = 0; i < allBlocks.length; i++) {
			if(allBlocks[i] == 1)
				System.out.print(i+" ");
		}
	}
	
	public void displayAllocatedBlocks() {
		for (int i = 0; i < allBlocks.length; i++) {
			if(allBlocks[i] == 0)
				System.out.print(i+" ");
		}
	}

}
