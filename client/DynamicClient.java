import java.rmi.server.RMIClassLoader;
import java.util.Properties;
import java.util.*;
import java.lang.reflect.Constructor;
import java.rmi.RemoteException;
import java.rmi.RMISecurityManager;

public class DynamicClient {
    public DynamicClient() throws Exception {
        Properties p = System.getProperties();
        String url = p.getProperty("java.rmi.server.codebase");
        Class ClasseClient = RMIClassLoader.loadClass(url, "ClientCode");
        //Lancer la Fabrique
        Constructor C = ClasseClient.getConstructor();
        C.newInstance(new Object[] {});

    }

    public static void main(String[] args) {
        System.setSecurityManager(new RMISecurityManager());
        try {
            DynamicClient client = new DynamicClient();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

}
