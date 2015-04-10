
abstract public class MemoryManager {
	public int totalnumOfEmptyBlocks ;
	
	public MemoryManager() {
		super();
	}
	abstract public int [] allocate(int numOfBlocks);
	abstract public boolean deAllocate(int []blocks);
	abstract public void displayEmptyBlocks();
	abstract public void displayAllocatedBlocks();
}
