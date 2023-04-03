package entities;

import java.util.Objects;

public class Pessoa {

    private String nome;
    private String usuario;
    private String senha;
    private Galeria galeria;

    public Pessoa(String nome, String usuario, String senha) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.galeria = new Galeria(this);
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

    public Galeria getGaleria() {
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
