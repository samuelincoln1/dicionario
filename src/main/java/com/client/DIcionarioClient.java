package com.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.*;

import com.server.Dicionario;

public class DIcionarioClient {
    // Menu com as opcoes de consultar uma palavra, adicionar e remover
    public static void menuInicial(Dicionario dicionario) {
        JFrame frame = new JFrame("Dicionário");
        frame.setSize(400, 200);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        JLabel welcomeLabel = new JLabel("Bem-vindo ao Dicionário!");
        welcomeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        frame.add(welcomeLabel);

        JButton consultarButton = new JButton("Consultar palavra");
        consultarButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        frame.add(consultarButton);

        JButton adicionarButton = new JButton("Adicionar palavra");
        adicionarButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        frame.add(adicionarButton);

        JButton removerButton = new JButton("Remover palavra");
        removerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        frame.add(removerButton);

        consultarButton.addActionListener(e -> {
           String palavra = JOptionPane.showInputDialog(frame, "Digite a palavra a ser pesquisada: ");
            if (palavra != null && !palavra.isEmpty()) {
                try {
                    String resultado = dicionario.consultarSignificado(palavra);
                    JOptionPane.showMessageDialog(frame, "Significado: " + resultado);
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(frame, "Erro: " + err.getMessage());
                    err.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Insira uma palavra válida");
            }
        });

        adicionarButton.addActionListener(e -> {
            String palavra = JOptionPane.showInputDialog(frame, "Digite a palavra a ser adicionada: ");
            if (palavra != null && !palavra.isEmpty()) {
                String significado = JOptionPane.showInputDialog(frame, "Digite o significado da palavra: ");
                try {
                    dicionario.adicionarPalavra(palavra, significado);
                    JOptionPane.showMessageDialog(frame, "Palavra adicionada com sucesso");
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(frame, "Erro: " + err.getMessage());
                    err.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Insira uma palavra válida");
            }
             
        });

        removerButton.addActionListener(e -> {
            String palavra = JOptionPane.showInputDialog(frame, "Digite a palavra a ser removida: ");
            if (palavra != null && !palavra.isEmpty()) {
                try {
                    dicionario.removerPalavra(palavra);
                    JOptionPane.showMessageDialog(frame, "Palavra removida com sucesso");
                } catch (Exception err) {
                    JOptionPane.showMessageDialog(frame, "Erro: " + err.getMessage());
                    err.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Insira uma palavra válida");
            }
        });

        frame.setVisible(true);
    }

    public static void menuErro(String mensagem) {
        JFrame frame = new JFrame("Erro");
        frame.setSize(400, 200);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        JLabel errorlLabel = new JLabel(mensagem);
        errorlLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        frame.add(errorlLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099); // Pega referência para um registro rmi no localhost e na porta 1099
            Dicionario dicionario = (Dicionario) registry.lookup("DicionarioRemote"); // Localiza o objeto do tipo Remote registrado com o nome "DicionarioRemote", que é uma implementacao de Dicionario e salva no objeto dicionario do tipo Dicionario
            System.out.println("Conectado ao servidor");
            menuInicial(dicionario); // Mostra o menu da aplicação
        } catch (Exception e) { // Caso ocorra algum erro na conexão, mostra uma mensagem de erro
            e.printStackTrace();
            menuErro("Ocorreu um erro ao se conectar com o servidor!");
            
            
        }
       
    }
}
