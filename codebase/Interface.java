import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;
public interface Interface extends Remote 
{

	String menuPrincipal() throws RemoteException;
	String menuSec(int y) throws RemoteException;
	String Add(Employe e , int x) throws RemoteException;
	String Delete(int e , int x) throws RemoteException;
	String Modify(Employe e , int x) throws RemoteException;
	List<String> Search(String e , int x) throws RemoteException;
}
