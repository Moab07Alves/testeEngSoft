import entities.GravadorDeDados;
import entities.Janela;

import javax.swing.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import entities.*;

public class Main {
    static final int MAX = 1000;
    static SistemaPessoas sistema = new SistemaPessoas(MAX);
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String usuario;
        String senha;
        //Janela paisagem = new Janela("/paisagem.jpg", "Foto de paisagem", "22/04/23");

        int novoUsuario = JOptionPane.showConfirmDialog(null,"Novo Usuário?(Yes = Sim, No = Não)","Escolha um",JOptionPane.YES_NO_OPTION);

        if(novoUsuario == 0){ // Opcao = 0 - Sim, o usuário não tem acesso ao nosso sistema
            usuario = JOptionPane.showInputDialog("Digite o seu novo nome de usuário");
            JPasswordField password = new JPasswordField(10);
            password.setEchoChar('*');
            JPanel entUsuario = new JPanel();
            entUsuario.add(password);
            JOptionPane.showMessageDialog(null, entUsuario, "Digite a sua senha", JOptionPane.PLAIN_MESSAGE);
            senha = gerarSenhaHex(password.getText());
            Pessoa pesoa = new Pessoa(usuario, senha);
            sistema.addPessoa(pesoa);
            String opcao = JOptionPane.showInputDialog(null, "Escolha uma opção:\n" +
                    "1 - Adicionar fotos\n" +
                    "2 - Remover foto\n" +
                    "3 - Ver fotos\n" +
                    "4 - Procurar foto por data\n" +
                    "5 - Procurar foto por filho\n");

            switch (opcao) {
                case "1":
                    JOptionPane.showMessageDialog(null, pesoa.getGaleria());
                    break;

                case "2":
                    break;

                case "3":
                    break;

                case "4":
                    break;

                case "5":
                    break;
            }
        }
        else{ // Opcao = 1 - Não, o usuário já tem acesso ao nosso sistema
            usuario = JOptionPane.showInputDialog("Digite o seu novo nome de usuário");
            JPasswordField password = new JPasswordField(10);
            password.setEchoChar('*');
            JPanel entUsuario = new JPanel();
            entUsuario.add(password);
            JOptionPane.showMessageDialog(null, entUsuario, "Digite a sua senha", JOptionPane.PLAIN_MESSAGE);
            senha = gerarSenhaHex(password.getText());
            int confirmacaoUsuario = confirmacaoUsuario(usuario, senha);
            if(confirmacaoUsuario == -1) {
                JOptionPane.showMessageDialog(null, "Usuario e senha não estão no sistema");
                return;
                }
            
            }
        }

    public static String gerarSenhaHex(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        String senhahex = hexString.toString();

        return senhahex;
    }

    public static int confirmacaoUsuario(String usuario, String senha) {
        List<Pessoa> pessoas = sistema.retornarPessoas();
        for (Pessoa p: pessoas) {
            if(p.getUsuario().equals(usuario) && p.getSenha().equals(senha)){return 1;}
        }
        return -1;
    }
}