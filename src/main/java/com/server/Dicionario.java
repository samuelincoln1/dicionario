package com.server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Dicionario extends Remote {
    public String consultarSignificado(String palavra) throws RemoteException, DicionarioException;
    public void adicionarPalavra(String palavra, String significado) throws RemoteException, DicionarioException;
    public void removerPalavra(String palavra) throws RemoteException, DicionarioException;
} 