import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class Virtual_Contiguous extends VirtualSystem implements Serializable{

	public Virtual_Contiguous(int totalBlocks) {
		super(totalBlocks);
		memoryManager = new ContiguousMemoryManager(totalBlocks);
	}


	
	//////////////////////////////////////////////////////////////
	public boolean deleteFile(String f, Directory parent)
	{
		parent.deleteFile(f);
		File_ file = parent.getFile(f);
		if(file == null)
			return false;
		int []arr = new int [2];
		
		arr[0] = file.getLocation()[0];
		arr[1] = file.getSize();
		
		return memoryManager.deAllocate(arr);
		
	}

	
	@Override
	public void save() {
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("Contiguous.ser");
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
