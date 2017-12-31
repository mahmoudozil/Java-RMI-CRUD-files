import java.rmi.*;
import java.util.*;
import java.lang.*;
import java.rmi.registry.*;

public class ClientCode {

public static String choice = "Enter Your choice and press Enter:";
public String FirstName ;
public String LastName ;
public String keywords;
public int cin ;
public int phone ;

public String readKeywords(Scanner sc)
{
	System.out.println("Enter some keywords\n");
	return(sc.nextLine());
}

public String readLastName(Scanner sc)
{
	System.out.println("Enter Last name \n");
	return(sc.nextLine());
}
public String readFirstName(Scanner sc)
{
	System.out.println("Enter First name \n");
	return(sc.nextLine());
}
public int readCIN(Scanner sc)
{
	do {
		System.out.println("Enter the CIN (exactly 8 digits)\n");
			while (!sc.hasNextInt()) {
				System.out.println("That's not a number!");
				sc.next(); 
				}
			cin = sc.nextInt();
		} while (String.valueOf(cin).length() < 7);
		return cin;
}
public int readPhone(Scanner sc)
{
	do {
	System.out.println("Enter phone (8 digits or more)\n");
	while (!sc.hasNextInt()) {
	System.out.println("That's not a number!");
	sc.next(); 
	}
	 phone = sc.nextInt();
	} while (String.valueOf(phone).length() < 7 );
	return(phone);
}

public int verifyChoice(Scanner s , int max)
{
	System.out.println(choice);
	int n = 0;
	 do 
    {
    	while (!s.hasNextInt()) {
		System.out.println("That's not a number!");
		s.next(); 
		}
        
    	n = s.nextInt();

        //If the number is outside range print an error message.
        if (n < 1 || n > max)
            System.out.println("Please choose a valid number from the menu");

    } while (n < 1 || n > max);

    return n;
	
}

public ClientCode() {
	
    try{
		if (System.getSecurityManager() == null)
                        System.setSecurityManager(new RMISecurityManager());
        Registry reg = LocateRegistry.getRegistry("localhost",1099); 
		FactoryInterface fabrique =(FactoryInterface)reg.lookup("Factory");
		Interface trait= (Interface)fabrique.newTraitement();	  	  	

	  	  	while(true)
	  	  	{
	  	  		//Main Menu : choose institution to EDIT
	  	  		System.out.println(trait.menuPrincipal());
		  	  	Scanner s=new Scanner(System.in);
	          	int x = verifyChoice(s,7);
	          	if(x == 7) 
				{
					System.out.println("GoodBye!!");
					break; // Quit					
				}
	          	
	          			//Second menu of CRUD
	          			while(true)
	  	  				{		
	          			System.out.println(trait.menuSec(x));
	          			Scanner s1=new Scanner(System.in);
	          			int y = verifyChoice(s1,5);
	          			
	          			if(y == 5) break; //Quit
				    
		          			Scanner sc=new Scanner(System.in);
		          			switch(y)
		          			{
		          				case 1:

		          					FirstName = readFirstName(sc);
		          					LastName = readLastName(sc);
		          					cin = readCIN(sc);
		          					phone = readPhone(sc);
		          					Employe eA = new Employe(LastName,FirstName,cin,phone);
			          				try
			          				{
			          					if (System.getSecurityManager() == null)
	                        			System.setSecurityManager(new RMISecurityManager());
	                        			System.out.println(trait.Add(eA,x)) ;

			          				}
			          				catch(Exception e1)
			          				{
			          					System.out.println ("Error while accessing the Remote Object");
		  								System.out.println (e1.toString());
			          				}

		          					break;
		          				case 2:
		          					try
		          					{
		          						if (System.getSecurityManager() == null)
                        				System.setSecurityManager(new RMISecurityManager());
                        				cin = readCIN(sc);
	          							System.out.println(trait.Delete(cin,x)) ;
		          					}
		          					catch(Exception e1)
		          					{
		          						System.out.println ("Error while accessing the Remote Object");
	  									System.out.println (e1.toString());
		          					}
		          					break;
		          				case 3:
		          						try
			          					{
			          						if (System.getSecurityManager() == null)
	                        				System.setSecurityManager(new RMISecurityManager());

	                        				cin = readCIN(sc);
	                        				Scanner sf = new Scanner(System.in);
	                        				Scanner sl = new Scanner(System.in);
	                        				FirstName = readFirstName(sf);
					          				LastName = readLastName(sl);
					          				phone = readPhone(sc);

											Employe eM = new Employe(LastName,FirstName,cin,phone);
	                        				System.out.println(trait.Modify(eM,x)) ;	
	                        	
			          					}
			          					catch(Exception e1)
			          					{
			          						System.out.println ("Error while accessing the Remote Object");
		  									System.out.println (e1.toString());
			          					}
		          						break;
		          				case 4:
		          					try
		          					{
		          						if (System.getSecurityManager() == null)
                        				System.setSecurityManager(new RMISecurityManager());
                        				keywords = readKeywords(sc);
	          							System.out.println(trait.Search(keywords,x)) ;
	          							System.out.println("Results are speared by a comma ,");
		          					}
		          					catch(Exception e1)
		          					{
		          						System.out.println ("Error while accessing the Remote Object");
	  									System.out.println (e1.toString());
		          					}
		          					break;
		          			}	         					
		          			
	          			}
	          		
	  	  	}
     	}    
     catch (Exception e1) {
	  System.out.println ("Error while accessing the Remote Object");
	  System.out.println (e1.toString());
          }
}
}

