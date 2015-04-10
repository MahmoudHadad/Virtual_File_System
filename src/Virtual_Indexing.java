import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class Virtual_Indexing extends VirtualSystem implements Serializable{
	public Virtual_Indexing(int totalBlocks) {
		super(totalBlocks);
		memoryManager = new IndexingMemoryManager(totalBlocks);
	}

	
	
	////////////////
	public boolean deleteFile(String f, Directory parent)
	{
		parent.deleteFile(f);
		
		File_ file = parent.getFile(f);
		
		return memoryManager.deAllocate(file.getLocation());
		
	}



	@Override
	public void save() {
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("Indexing.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	       
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		
	}
}
