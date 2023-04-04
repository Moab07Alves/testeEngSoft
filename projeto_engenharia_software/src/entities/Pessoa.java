package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pessoa {

    GravadorDeDados gravadorDeDados = new GravadorDeDados("testeEngSoft/projeto_engenharia_software/usuarios.txt");
    private int id;
    private String nome;
    private String usuario;
    private String senha;
    private List<Galeria> galeria = new ArrayList<>();

    public Pessoa(String nome, String usuario, String senha) throws IOException {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;

        int id = -1;
        List<String> usuarios = gravadorDeDados.recuperarTextoDeArquivo();
        for (String a: usuarios)  {
            id++;
        }
        System.out.println(id);
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Galeria> getGaleria() {
        return galeria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;
        return getNome().equals(pessoa.getNome()) && getUsuario().equals(pessoa.getUsuario()) && senha.equals(pessoa.senha) && getGaleria().equals(pessoa.getGaleria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getUsuario(), senha, getGaleria());
    }
}
