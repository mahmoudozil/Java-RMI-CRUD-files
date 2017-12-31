import java.rmi.*;
import java.util.*;
import java.lang.*;

import java.rmi.registry.*;
public class ClientCode {

public static String choice = "Enter Your choice and press Enter:";
public String FirstName ;
public String LastName ;
public int cin ;
public int phone ;

public String readLastName(Scanner s)
{

}
public String readFirstName(Scanner s)
{

}
public int readCIN(Scanner s)
{

}
public int readPhone(Scanner s)
{

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
	  	  		System.out.println(trait.menuPrincipal());
		  	  	Scanner s=new Scanner(System.in);
	          	System.out.println(choice);
	          	int x = s.nextInt();
	          	if(x == 7) break;
	          	
	          		if(trait.sendChoice(x,7))
	          		{
	          			while(true)
	  	  			{		
	          			System.out.println(trait.menuSec(x));
	          			Scanner s1=new Scanner(System.in);
	          			System.out.println(choice);
	          			int y = s1.nextInt();
	          			if(y == 5) break;
		          			
		          			if(trait.sendChoice(y,5))
		          			{
		          				Scanner sc=new Scanner(System.in);
		          				if(y == 2)
		          				{
		          					int cin;
		          					do {
									    System.out.println("Enter the CIN you want to delete (exactly 8 digits)\n");
									    while (!sc.hasNextInt()) {
									        System.out.println("That's not a number!");
									        sc.next(); 
									    }
									    cin = sc.nextInt();
									} while (String.valueOf(cin).length() < 7);
	          						System.out.println(trait.Delete(cin,x)) ;
		          				}
		          				else if(y == 4)
		          				{
		          					System.out.println("Enter some keywords\n");
	          						String keywords = sc.nextLine();
	          						System.out.println(trait.Search(keywords,x)) ;

		          				}
		          				else //we need the full object to add or modidy
		          				{
		          					int cin;
		          					int tel;
		          					System.out.println("Enter Last name \n");
		          					String nom = sc.nextLine();

		          					System.out.println("Enter First name \n");
		          					String prenom = sc.nextLine();
		          					do {
									    System.out.println("Enter the CIN (exactly 8 digits)\n");

									    while (!sc.hasNextInt()) {
									        System.out.println("That's not a number!");
									        sc.next(); 
									    }
									    cin = sc.nextInt();
									} while (String.valueOf(cin).length() < 7);

									do {
									    System.out.println("Enter phone (8 digits or more)\n");
									    while (!sc.hasNextInt()) {
									        System.out.println("That's not a number!");
									        sc.next(); // this is important!
									    }
									    tel = sc.nextInt();
									} while (String.valueOf(tel).length() < 7 );

		          					Employe e = new Employe(nom,prenom,cin,tel);
		          					if(y == 1) 
		          						System.out.println(trait.Add(e,x)) ;
		          					else 
		          						System.out.println(trait.Modify(e,x)) ;
		          				}         					
		          			}
		          			else
		          			{
		          				trait.validChoice();
		          			}
	          			}
	          		}
	          		else
	          		{
	          			System.out.println(trait.validChoice());
	          		}
	  	  	}
     	}    
     catch (Exception e) {
	  System.out.println ("Erreur d'acces a l'objet distant.");
	  System.out.println (e.toString());
          }
}
}

