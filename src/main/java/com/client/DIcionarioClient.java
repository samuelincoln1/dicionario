package com.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.server.Dicionario;

public class DIcionarioClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Dicionario dicionario = (Dicionario) registry.lookup("DicionarioRemote");
            System.out.println("Conectado ao servidor");
            // dicionario.adicionarPalavra("teste", "significado teste");
            // dicionario.adicionarPalavra("teste", "significado teste2");
            // System.out.println(dicionario.consultarSignificado("naoexisto"));
            // System.out.println(dicionario.consultarSignificado("gato"));
            // dicionario.removerPalavra("falar");
            
        } catch (Exception e) {
            System.err.println("Erro: " + e.toString());
        }
    }
}
