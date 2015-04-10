import java.io.Serializable;
import java.util.ArrayList;


public abstract class VirtualSystem implements Serializable{
	
	protected int totalBlocks;
	protected Directory root;
	protected MemoryManager memoryManager;
	
	public VirtualSystem(int totalBlocks) {
		super();
		root = new Directory("root");
		this.totalBlocks = totalBlocks;
	}

	public int getTotalBlocks() {
		return totalBlocks;
	}

	public void setTotalBlocks(int totalBlocks) {
		this.totalBlocks = totalBlocks;
	}

	public Directory getRoot() {
		return root;
	}

	public void setRoot(Directory root) {
		this.root = root;
	}

	public MemoryManager getMemoryManager() {
		return memoryManager;
	}

	public void setMemoryManager(MemoryManager memoryManager) {
		this.memoryManager = memoryManager;
	}
	
	///////////////////////////
	
public void executeCommand(String input) {
		
		String [] arr = input.split(" ");
		
		String command = arr[0];
		
		if(command.equals("DisplayDiskStatus"))
		{
			System.out.println("Empty space: "+ memoryManager.totalnumOfEmptyBlocks);
			System.out.println("Allocated space: " + (totalBlocks - memoryManager.totalnumOfEmptyBlocks) );
			
			System.out.println("Empty Blocks in the Disk:");
			memoryManager.displayEmptyBlocks();
			
			System.out.println("Allocated Blocks in the Disk:");
			memoryManager.displayAllocatedBlocks();
			
		}
		
		else if(command.equals("DisplayDiskStructure"))
		{
			displayStructure(root, 0);
		}
		
		else if(command.equals("CreateFolder"))
		{
			String []path = arr[1].split("/");
			
			Directory d = root;
			d = getDirectory(path, d);
			
			if(d == null)
			{
				System.out.println("Directory not found");
				return;
			}
			d.addDirectory(new Directory(path[path.length-1]));
			
		}
		
		else if(command.equals("CreateFile"))
		{
			String []path = arr[1].split("/");
			
			Directory d = root;
			d = getDirectory(path, d);
			
			if(d == null)
			{
				System.out.println("Directory not found");
				return;
			}
			
			if(d.getFile(path[path.length-1]) !=null )
			{
				System.out.println("File exists");
				return;
			}
			int fileSize = Integer.parseInt(arr[2]);
			int []location = memoryManager.allocate(fileSize);
			File_ f = new File_(path[path.length-1], fileSize, location);
			d.addFile(f);
			
		}
		
		else if(command.equals("DeleteFile"))
		{
			String []path = arr[1].split("/");
			
			Directory d = root;
			d = getDirectory(path, d);
			
			if(d == null)
			{
				System.out.println("Directory not found");
				return;
			}
			
			deleteFile(path[path.length-1], d);
		
		}
		
		else if(command.equals("DeleteFolder"))
		{
			String []path = arr[1].split("/");
			
			Directory d = root;
			d = getDirectory(path, root);
			
			if(d == null)
			{
				System.out.println("Directory not found");
				return;
			}
			
			deleteDirectory(d);
		}
		else if(command.equals("Save"))
			this.save();
		else 
			System.out.println("Wrong Command");
		
	
	}
	/////////////////////////////////
	
	public void displayStructure (Directory d , int lev)
	{
		ArrayList<Directory>dirctories = d.getSubDirectories();
		System.out.println(getTapes(lev)+d.getName());
		
		for (Directory dir :  dirctories) {
			displayStructure(dir, lev+1);
		}
		
		ArrayList<File_>files = d.getFiles();
		String tap = getTapes(lev+1);
		for (File_ file :  files) {
			System.out.println(tap + file.getName());
		}
		
		
	}
	/////////////////////
	protected String getTapes(int lev)
	{
		String x="";
		while(lev>0)
		{
			x+="  ";
			lev--;
		}
		return x;
	}
	
	public Directory getDirectory(String []path, Directory d)
	{
		boolean notFount = false;
		for(int i = 1 ; i< path.length -1 ; i++)
		{
			notFount = true;
			for(Directory dir : d.getSubDirectories())
			{
				if(dir.getName().equals(path[i]))
				{
					notFount = false;
					d = dir;
					break;
				}
			}
			if(notFount)
				return null;
		}
	
		
		return d;
	}
	
	///////////////////////////////////
	abstract public boolean deleteFile(String f, Directory parent);
	/////////////////////////////
	public void deleteDirectory(Directory d)
	{
		if(d == null)
			return ;
		for (Directory dir: d.getSubDirectories())
				deleteDirectory(dir);
		
		ArrayList<Directory>arr = d.getSubDirectories();
		while (arr.size() >0)
			d.deleteDirectory(arr.get(0).getName());
		for(File_ f: d.getFiles())
		{
			deleteFile(f.getName(), d);
		}
		
	}
	
	/////////////////////////////////////////////////
	abstract public void save();
	
}
