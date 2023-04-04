package entities;

import java.io.IOException;
import java.util.*;

public class Pessoa {
    private int id;

    private String nome;
    private String usuario;
    private String senha;
    private List<Galeria> galeria = new ArrayList<>();

    public Pessoa(int id, String nome, String usuario, String senha) throws IOException {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Pessoa(String nome, String usuario, String senha) throws IOException{
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
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

    public String getSenha(){ return this.senha; }

    public List<Galeria> getGaleria() {
        return galeria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
