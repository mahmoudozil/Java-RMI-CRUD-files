import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.lang.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;

public class InterfaceImpl extends UnicastRemoteObject implements Interface 
{
    public String[] Fac = {"FST","ENIT","ISI","INSAT","ENSIT","ENSI"};

    public InterfaceImpl() throws RemoteException
    {
		super();
    }
	 

    public String menuPrincipal() throws RemoteException
    {
       	return(" 1: FST\n 2: ENIT\n 3: ISI\n 4: INSAT\n 5: ENSIT\n 6: ENSI\n 7: Quit\n");
    }

    public String menuSec(int y) throws RemoteException
    {
    	return("you are editing " + Fac[y-1] + " now: \n 1: Add\n 2: Delete\n 3: Modify\n 4: Search\n 5: Quit");	
    }

    public String Add(Employe e , int x) throws RemoteException
    {
    	String file = "./Carnets/" + Fac[x-1]+".txt";
    	try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			for (String line; (line = reader.readLine()) != null;) 
			{
				String cin = String.valueOf(e.cin);
    			if(line.contains(cin))	
    			{
    				return("CIN already exists!!");
    			}
    		}
			Writer output;
			output = new BufferedWriter(new FileWriter(file,true));  //append = true
			output.append(e+"\n");
			output.close();
			
    		return(e + " \nAdded Successfully");
		} 
		catch (Exception e1) 
		{
			return(e1.getMessage());
		}
    }

    public String Delete(int e , int x) throws RemoteException
    {
    	String file = "./Carnets/" + Fac[x-1]+".txt";

    	try
		{
			File inputFile = new File(file);
			File tempFile = new File("myTempFile.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String currentLine;	
			boolean test = false;

			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.contains(String.valueOf(e))) 
			    {
			    	test = true;
			    	continue;
			    }
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			boolean successful = tempFile.renameTo(inputFile);
			if(test) 
				return(e + " Deleted Successfully");
			else
				return(e + " does not exist");
		} 
		catch (Exception e1) 
		{
			return(e1.getMessage());
		}
    }


    public List<String> Search(String e , int x) throws RemoteException
    {
    	String file = "./Carnets/" + Fac[x-1]+".txt";
		List<String> res = new ArrayList<String>();
		res.clear();
    	try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			boolean test = false;
			for (String line; (line = reader.readLine()) != null;) 
			{
    			if(line.contains(e))	
    			{
    				res.add(line);
    				test = true;
				}

			}
			if(test)
				return(res);
			else
			{
				res.add(e + " does not exist");
				return(res);
			}
			
		} 
		catch (Exception e1) 
		{
			res.add(e1.getMessage());
			return(res);
		}
    }


public String Modify(Employe e , int x) throws RemoteException
    {
    	String file = "./Carnets/" + Fac[x-1]+".txt";

    	try
		{
			File inputFile = new File(file);
			File tempFile = new File("myTempFile.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String currentLine;
			boolean test = false;

			while((currentLine = reader.readLine()) != null) 
			{
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.contains(String.valueOf(e.cin)))
			    {
			    	currentLine = e.toString();
			    	test = true;
			    } 

			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			boolean successful = tempFile.renameTo(inputFile);
			
			if(!test)
			{
				return(String.valueOf(e.cin) + " does not exist");
			}
			else
			{
				return(e + " Modified Successfully"); 
			}
		} 
		catch (Exception e1) 
		{
			return(e1.getMessage());
		}
    }
}
