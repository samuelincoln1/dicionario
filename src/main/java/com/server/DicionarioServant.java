package com.server;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class DicionarioServant extends UnicastRemoteObject implements Dicionario{
    // Map do tipo String:String
    Map<String, String> dicionario;
    // Remover warning desnecessário
    @SuppressWarnings("unchecked")
    // Utilizando a classe Gson, este construtor lê o arquivo dicionario.json e o transforma em um HashMap na memória
    public DicionarioServant() throws RemoteException {
        super();
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("src/main/java/com/dicionario.json")){
            dicionario = gson.fromJson(reader, HashMap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Consulta uma palavra no dicionário. Se a palavra existir, retorna o significado dela. Se não existir, lança a execeção DicionarioException
    @Override
    public String consultarSignificado(String palavra) throws RemoteException, DicionarioException {
        if (dicionario.get(palavra) != null) {
            return dicionario.get(palavra).toString();
        } else {
            throw new DicionarioException("Palavra inexistente");
        }
   
    }

    // Adiciona uma palavra no dicionário. Se a palavra já estiver adicionada, lança a execeção DicionarioException. Caso contrário, adiciona a palavra e o significado no HashMap e salva no arquivo Json
    @Override
    public void adicionarPalavra(String palavra, String significado) throws RemoteException, DicionarioException {
        if (dicionario.get(palavra) != null) {
            throw new DicionarioException("Essa palavra ja esta cadastrada no dicionario!");
        } else {
            dicionario.put(palavra, significado);
            atualizarDicionario();
            System.out.println("Palavra adicionada com sucesso");
        }
    }

    // Remove uma palavra do dicionário. Se a palavra não existir, lança a execeção DicionarioException. Caso contrário, remove a palavra do HashMap e do arquivo Json
    @Override
    public void removerPalavra(String palavra) throws RemoteException, DicionarioException{
        if (dicionario.get(palavra) != null) {
            dicionario.remove(palavra);
            atualizarDicionario();
            System.out.println("Palavra removida com sucesso");
        } else {
            throw new DicionarioException("Palavra inexistente");
        }
    }

    // Atualiza o arquivo dicionario.json com os dados no HashMap da memória
    public void atualizarDicionario() {
        Gson gson = new Gson();
        String json = gson.toJson(dicionario);
         try (FileWriter writer = new FileWriter("src/main/java/com/dicionario.json")) {
            writer.write(json);
            System.out.println("Arquivo JSON salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
