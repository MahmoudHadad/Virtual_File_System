import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class Main {
	public static void main(String []args)
	{
		// save files for the first time 
//		VirtualSystem cont = new Virtual_Contiguous(1000);
//		VirtualSystem indx = new Virtual_Indexing(1000);
//		cont.save();
//		indx.save();
//		cont.executeCommand("DisplayDiskStatus");
//		
		// view menue to choose from two systems
		
		int choice = 0;
		Scanner  sc= new Scanner(System.in);
		VirtualSystem vs = null;
		
		System.out.println("1- Contiguous Virtual System");
		System.out.println("2- Indexing Virtual System");
		
		
		// load virtual system
		
		while (choice != 1 && choice!=2 )
			choice = sc.nextInt();
		
		if(choice == 1)
		{

//			vs = new Virtual_Contiguous(1000);
			try
		      {
		         FileInputStream fileIn = new FileInputStream("Contiguous.ser");
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         vs = (Virtual_Contiguous) in.readObject();
		         in.close();
		         fileIn.close();
		      }catch(IOException i)
		      {
		         i.printStackTrace();
		         return;
		      }catch(ClassNotFoundException c)
		      {
		         System.out.println("File not found");
		         c.printStackTrace();
		         return;
		      }
		}
		
		else if(choice == 2)
		{
			vs = new  Virtual_Indexing(1000);
//			try
//		      {
//		         FileInputStream fileIn = new FileInputStream("Indexing.ser");
//		         ObjectInputStream in = new ObjectInputStream(fileIn);
//		         vs = (Virtual_Indexing) in.readObject();
//		         in.close();
//		         fileIn.close();
//		      }catch(IOException i)
//		      {
//		         i.printStackTrace();
//		         return;
//		      }catch(ClassNotFoundException c)
//		      {
//		         System.out.println("File not found");
//		         c.printStackTrace();
//		         return;
//		      }
		}
		 
		// loop take command then execute 
		
		while(true)
		{
			System.out.println("Enter Your Command");
			String command = sc.nextLine();
			if(command.equals(""))
				command = sc.nextLine();
			vs.executeCommand(command);
		}
		
	}
}
