package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaPessoas {
    // Sistema para gerenciar todos os usuários presentes no arquivo txt, serve para salvar os usuarios
    // e recuperar todos a partir de métodos
    private List<Pessoa> pessoas;

    private GravadorDeDados gravadorDeDados;

    public SistemaPessoas(int tamanho){
        this.pessoas = new ArrayList<>(tamanho);
        this.gravadorDeDados = new GravadorDeDados("testeEngSoft/projeto_engenharia_software/usuarios.txt");
    }

    public void addPessoa(Pessoa pessoa){
        this.pessoas.add(pessoa);
    }

    public void salvarPessoas() throws IOException {
        List<String> texto = new ArrayList<>();
        for (Pessoa a : this.pessoas){
            String linha = a.getId()+ "#" + a.getNome() + "#" + a.getUsuario() + "#" + a.getSenha() + "#" + a.getGaleria();
            texto.add(linha);
        }
        gravadorDeDados.gravaTextoEmArquivo(texto);
    }

    public void recuperarPessoas() throws IOException {
        List<String> texto = gravadorDeDados.recuperarTextoDeArquivo();
        String[] array = new String[4];
        for (String a : texto) {
            array = a.split("#");
            pessoas.add(new Pessoa(Integer.parseInt(array[0]), array[1], array[2], array[3]));
        }
    }
}