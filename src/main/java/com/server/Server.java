package com.server;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Dicionario dicionario = new DicionarioServant();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("DicionarioRemote", dicionario);
            System.out.println("Servidor online");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}