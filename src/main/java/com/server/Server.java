package com.server;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            Dicionario dicionario = new DicionarioServant(); // Instancia classe DicionarioServant que implementa Dicionario
            Registry registry = LocateRegistry.createRegistry(1099); // Cria um registro RMI na porta 1099
            registry.rebind("DicionarioRemote", dicionario); // Associa o dicionario instanciado com o nome DicionarioRemote, para ser acessado remotamente
            System.out.println("Servidor online");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}