import java.io.Serializable;


public class File_ implements Serializable{
	private String name;
	private int size;
	private int indexBlock;
	
	private int []location;

		
	public File_(String name, int size, int[] location) {
		super();
		this.name = name;
		this.size = size;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int[] getLocation() {
		return location;
	}

	public void setLocation(int[] location) {
		this.location = location;
	}

	public int getIndexBlock() {
		return indexBlock;
	}

	public void setIndexBlock(int indexBlock) {
		this.indexBlock = indexBlock;
	}
	
	
}
