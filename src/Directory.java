import java.io.Serializable;
import java.util.ArrayList;


public class Directory implements Serializable{
	private String name;
	private ArrayList<Directory>subDirectories;
	private ArrayList<File_>files;
	
	
	public Directory(String name) {
		super();
		this.name = name;
		subDirectories = new ArrayList<Directory>();
		files = new ArrayList<File_>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Directory> getSubDirectories() {
		return subDirectories;
	}

	public void setSubDirectories(ArrayList<Directory> subDirectories) {
		this.subDirectories = subDirectories;
	}

	public ArrayList<File_> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<File_> files) {
		this.files = files;
	}



	public void addFile(File_ f)
	{
		files.add(f);
	}
	
	public void addDirectory(Directory d)
	{
		subDirectories.add(d);
	}
	
	public boolean deleteFile (String fileName)
	{
		for (int i = 0; i < files.size(); i++) {
			if(files.get(i).getName().equals(fileName))
			{
				files.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteDirectory (String directoryName)
	{
		for (int i = 0; i < subDirectories.size(); i++) {
			if(subDirectories.get(i).getName().equals(directoryName))
			{
				subDirectories.remove(i);
				return true;
			}
		}
		return false;
	}
	///////////////////////////////////////////////////////////////////////
	
	public File_ getFile(String f)
	{
		for (int i = 0; i < files.size(); i++) {
			if(files.get(i).getName().equals(f))
			{
				return files.get(i);
			}
		}
		return null;
	}
	
	public Directory getDirectory(String d)
	{
		for (int i = 0; i < subDirectories.size(); i++) {
			if(subDirectories.get(i).getName().equals(d))
			{
				return subDirectories.get(i);
			}
		}
		return null;
	}
	
}
