package com.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.server.Dicionario;

public class DIcionarioClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Dicionario dicionario = (Dicionario) registry.lookup("DicionarioRemote");
            dicionario.adicionarPalavra("teste", "significado teste");
            System.out.println(dicionario.consultarSignificado("teste"));
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
