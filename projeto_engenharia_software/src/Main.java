import entities.GravadorDeDados;
import entities.Janela;

import javax.swing.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import entities.*;

public class Main {
    GravadorDeDados gravador = new GravadorDeDados("testeEngSoft/projeto_engenharia_software/usuarios.txt");

    public static void main(String[] args) throws IOException {


        Janela paisagem = new Janela("/paisagem.jpg", "Foto de paisagem", "22/04/23");

        int opcao = JOptionPane.showConfirmDialog(null,"Novo Usuário?(Yes = Sim, No = Não)","Escolha um",JOptionPane.YES_NO_OPTION);

        Pessoa a = new Pessoa("a", "b", "a");
        if(opcao == 0){ // Opcao = 0 - Sim, o usuário não tem acesso ao nosso sistema
            String usuario = JOptionPane.showInputDialog("Digite o seu novo nome de usuário");
        }
        else{ // Opcao = 1 - Não, o usuário já tem acesso ao nosso sistema

        }

    }

    public String gerarSenhaHex(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        String senhahex = hexString.toString();

        return senhahex;
    }

}