package entities;

import java.io.IOException;
import java.util.*;

public class Pessoa {
    private String usuario;
    private String senha;
    private List<Galeria> galeria = new ArrayList<>();

    public Pessoa(int id, String nome, String usuario, String senha) throws IOException {
        this.usuario = usuario;
        this.senha = senha;
    }

    public Pessoa(String nome, String usuario, String senha) throws IOException{
        this.usuario = usuario;
        this.senha = senha;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;
        return getUsuario().equals(pessoa.getUsuario()) && senha.equals(pessoa.senha) && getGaleria().equals(pessoa.getGaleria());
    }

    @Override
    public int hashCode() {
        return Objects.hash( getUsuario(), senha, getGaleria());
    }
}
