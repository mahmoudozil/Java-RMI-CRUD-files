
import java.rmi.*;
import java.rmi.server.*;
public class Factory  extends UnicastRemoteObject implements FactoryInterface{
    public Factory()throws RemoteException {}

    public Interface newTraitement() throws RemoteException
    {
        return new InterfaceImpl();
    }
}
