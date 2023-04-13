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
            usuario = JOptionPane.showInputDialog("Digite o seu novo nome de usuário");
            JPasswordField password = new JPasswordField(10);
            password.setEchoChar('*');
            JPanel entUsuario = new JPanel();
            entUsuario.add(password);
            JOptionPane.showMessageDialog(null, entUsuario, "Digite a sua senha", JOptionPane.PLAIN_MESSAGE);
            senha = gerarSenhaHex(password.getText());
            Pessoa pessoa = new Pessoa(usuario, senha);
            if(sistema.verificarPessoa(usuario, senha)) {
                menu(new Pessoa(usuario, senha));
            }
            else {
                sistema.addPessoa(pessoa);
                sistema.salvarPessoas();
                menu(pessoa);
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
                    "6 - Procurar foto por filho\n" +
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
                        Path original = Paths.get(fotoEscolhida.getAbsolutePath(), "");
                        Path novo = Paths.get("/" + fotoEscolhida.getName());
                        try {
                            Files.move(original, novo, StandardCopyOption.REPLACE_EXISTING);
                            JOptionPane.showMessageDialog(null, "test");
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        //gale.registrarFoto(novaFoto);
                        //Janela mostrarFoto = new Janela(novaFoto.getCaminhoFoto(), novaFoto.getDescricao(), "10/10/20"); // Apenas exemplo
                    }
                    else JOptionPane.showMessageDialog(null, "O usuário não possui nenhuma galeria");
                    break;

                case "3":
                    break;

                case "4":
                    break;

                case "5":
                    break;

                case "6":
                    break;

                case "7":
                    sair = true;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
                    break;
            }
        }
    }
}