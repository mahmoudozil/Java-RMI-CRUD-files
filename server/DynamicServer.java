import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.rmi.server.RMIClassLoader;
import java.util.Properties;
public class DynamicServer {
    public static void main(String[] args) {
try {
	if(System.getSecurityManager() == null)
           System.setSecurityManager(new RMISecurityManager());
//Construction de RMI registry
 Registry registry = LocateRegistry.createRegistry(1099);

  Properties p = System.getProperties();
String url = p.getProperty("java.rmi.server.codebase");
 Class ClasseFactory = RMIClassLoader.loadClass(url,"Factory");
  registry.rebind("Factory",(Remote)ClasseFactory.newInstance());
  System.out.println("attente des invocations des clients ");
   }
    catch (Exception e) {
            System.out.println("Erreur de liaison de l'objet Fabrique");
            System.out.println(e.toString());
}

}
}

           
