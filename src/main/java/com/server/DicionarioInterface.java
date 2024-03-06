package com.server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DicionarioInterface extends Remote {
    public String consultarSignificado(String palavra) throws RemoteException;
    public void adicionarPalavra(String palavra, String significado) throws RemoteException;
    public void removerPalavra(String palavra) throws RemoteException;
} 