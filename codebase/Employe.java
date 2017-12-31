import java.rmi.*;
import java.util.*;
import java.lang.*;

import java.rmi.registry.*;

public class Employe implements java.io.Serializable{
	public String nom;
	public String prenom;
	public int cin;
	public int tel;

	public Employe(String nom, String prenom, int cin , int tel)
	{
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.tel = tel;
	
	}
	@Override
	public String toString()
	{
		return("nom = " + nom + " ; prenom = " + prenom + " ; cin = " +  cin + " ; tel = " + tel);
	}
}