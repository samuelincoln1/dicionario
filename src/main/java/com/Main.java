package com;

import com.server.DicionarioServant;

public class Main {
    public static void main(String[] args) throws Exception{
        DicionarioServant dicionario = new DicionarioServant();
        dicionario.adicionarPalavra("teste", "significado teste");
        System.out.println(dicionario.consultarSignificado("testado"));
    }
  
}