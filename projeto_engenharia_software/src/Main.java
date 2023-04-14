import entities.GravadorDeDados;
import entities.Janela;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

import entities.*;

public class Main {
    static final int MAX = 1000;
    static SistemaPessoas sistema = new SistemaPessoas(MAX);
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        try {
            sistema.recuperarPessoas();
        } catch(FileNotFoundException e) {
        }

        String usuario;
        String senha;
        //Janela paisagem = new Janela("/paisagem.jpg", "Foto de paisagem", "22/04/23");
        int novoUsuario = JOptionPane.showConfirmDialog(null,"Novo Usuário?(Yes = Sim, No = Não)","Escolha um",JOptionPane.YES_NO_OPTION);

        if(novoUsuario == 0) { // Opcao = 0 - Sim, o usuário não tem acesso ao nosso sistema
            do {
                usuario = JOptionPane.showInputDialog("Digite o seu novo nome de usuário");
                JPasswordField password = new JPasswordField(10);
                password.setEchoChar('*');
                JPanel entUsuario = new JPanel();
                entUsuario.add(password);
                JOptionPane.showMessageDialog(null, entUsuario, "Digite a sua senha", JOptionPane.PLAIN_MESSAGE);
                senha = gerarSenhaHex(password.getText());
                Pessoa pessoa = new Pessoa(usuario, senha);
                if(sistema.verificarPessoa(usuario, senha)){
                    JOptionPane.showMessageDialog(null, "Usuário já cadastrado, escolha outro nome de usuário");
                }
            }while(sistema.verificarPessoa(usuario, senha));
            menu(new Pessoa(usuario, senha));
        }
        else{ // Opcao = 1 - Não, o usuário já tem acesso ao nosso sistema
            usuario = JOptionPane.showInputDialog("Digite o seu novo nome de usuário");
            JPasswordField password = new JPasswordField(10);
            password.setEchoChar('*');
            JPanel entUsuario = new JPanel();
            entUsuario.add(password);
            JOptionPane.showMessageDialog(null, entUsuario, "Digite a sua senha", JOptionPane.PLAIN_MESSAGE);
            senha = gerarSenhaHex(password.getText());

            if(sistema.verificarPessoa(usuario, senha)) {
                menu(new Pessoa(usuario, senha));
            }
            else {
                JOptionPane.showMessageDialog(null, "O usuário não é cadastrado no sistema");
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
    public static File mostrarEscolhaFoto() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Arquivos de Imagem", "jpg", "jpeg", "png", "gif");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = chooser.getSelectedFile();
            return arquivoSelecionado;
        }
        return null;
    }

    public static void menu(Pessoa pessoa) throws IOException {
        boolean sair = false;
        while (!sair) {
            String opcao = JOptionPane.showInputDialog(null, "Escolha uma opção:\n" +
                    "1 - Criar uma nova galeria\n" +
                    "2 - Adicionar fotos\n" +
                    "3 - Remover foto\n" +
                    "4 - Ver fotos\n" +
                    "5 - Procurar foto por data\n" +
                    "6 - Procurar foto por galeria\n" +
                    "7 - Sair\n");

            switch (opcao) {
                case "1":
                    String titulo = JOptionPane.showInputDialog("Digite o nome que será dado a galeria: ");
                    pessoa.adicionarNovaGaleria(titulo);
                    break;

                case "2":
                    if(pessoa.getGaleria() != null) {
                        String nomeGaleria = JOptionPane.showInputDialog(null, pessoa.getGaleria() + "Qual galeria você escolhe para adicionar a foto: ");
                        Galeria gale = pessoa.procurarGaleriaPorTitulo(nomeGaleria);
                        File fotoEscolhida = mostrarEscolhaFoto();
                        String descricao = JOptionPane.showInputDialog(null, "Digite qual a descrição da foto");
                        String data = JOptionPane.showInputDialog(null, "Digite qual a data que a foto foi tirada?");
                        JOptionPane.showMessageDialog(null, "", "Descrição: " + descricao  + " Data: " + data, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(fotoEscolhida.getAbsolutePath()));
                        Foto novaFoto = new Foto(gale.getTitulo(), descricao, data, fotoEscolhida.getAbsolutePath());
                        gale.registrarFoto(novaFoto);
                    }
                    else JOptionPane.showMessageDialog(null, "O usuário não possui nenhuma galeria");
                    break;

                case "3":
                    if(pessoa.getGaleria() != null){
                        String nomeGaleria = JOptionPane.showInputDialog(null, pessoa.getGaleria() + "Qual galeria você escolhe para remover a foto: ");
                        Galeria gale = pessoa.procurarGaleriaPorTitulo(nomeGaleria);
                        String descricaoFoto = JOptionPane.showInputDialog(null, gale.getFotos()+ "Digite a descrição da foto que você quer apagar: ");
                        gale.apagarFoto(descricaoFoto);
                    }
                    else JOptionPane.showMessageDialog(null, "O usuário não possui nenhuma galeria");
                    break;

                case "4":
                    if(pessoa.getGaleria() != null){
                        pessoa.mostrarTodasFotos();
                    }
                    else JOptionPane.showMessageDialog(null, "O usuário não possui nenhuma galeria");
                    break;

                case "5":
                    break;

                case "6":
                    if(pessoa.getGaleria() != null){
                        String nomeGaleria = JOptionPane.showInputDialog(null, pessoa.getGaleria() + "Qual galeria você escolhe para mostrar as fotos: ");
                        pessoa.mostrarFotoPorGaleria(nomeGaleria);
                    }
                    else JOptionPane.showMessageDialog(null, "O usuário não possui nenhuma galeria");
                    break;

                case "7":
                    sair = true;
                    sistema.salvarPessoas();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
                    break;
            }
        }
    }
}