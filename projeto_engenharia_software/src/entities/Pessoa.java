package entities;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class Pessoa {
    private String usuario;
    private String senha;
    private List<Galeria> galeria = new ArrayList<>();

    public Pessoa(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public Pessoa(String nome, String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public boolean adicionarNovaGaleria(String tituloNovaGaleria) {
        for (Galeria gale: this.galeria) {
            if(gale.getTitulo().equals(tituloNovaGaleria)) {
                return false;
            }
        }

        Galeria novaGaleria = new Galeria(tituloNovaGaleria);
        this.galeria.add(novaGaleria);
        return true;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha(){ return this.senha; }

    public StringBuilder getGaleria() {
        StringBuilder titulosGaleria = new StringBuilder();
        if(!this.galeria.isEmpty()) {
            titulosGaleria.append("\tGalerias do Usuário\n\n");
            for(Galeria gale: this.galeria) {
               titulosGaleria.append(gale.getTitulo() + " ---- QUANTIDADE DE FOTOS:  " + gale.quantidadeFotos() + "\n");
            }
            return titulosGaleria;
        }
        else {
            //titulosGaleria.append("O usuário não possui nenhuma galeria");
            return null;
        }
    }

    public List<Galeria> getListaGaleria(){
        return this.galeria;
    }
    public Galeria procurarGaleriaPorTitulo(String titulo){
        for (Galeria gale: this.galeria) {
            gale.getTitulo().equals(titulo);
            return gale;
        }
        return null;
    }

    public void mostrarTodasFotos(){
        for (Galeria a: this.galeria) {
            for (Foto f: a.getListaFotos()) {
                JOptionPane.showMessageDialog(null, "", "Descrição: " + f.getDescricao()  + " Data: " + f.getDataFoto(), JOptionPane.INFORMATION_MESSAGE, new ImageIcon(f.getCaminhoFoto()));
            }
        }
    }

    public void mostrarFotoPorGaleria(String nomeGaleria) {
        for (Galeria a: this.galeria) {
            if (a.getTitulo().equals(nomeGaleria)){
                for (Foto f: a.getListaFotos()) {
                    JOptionPane.showMessageDialog(null, "", "Descrição: " + f.getDescricao()  + " Data: " + f.getDataFoto(), JOptionPane.INFORMATION_MESSAGE, new ImageIcon(f.getCaminhoFoto()));
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa pessoa)) return false;
        return getUsuario().equals(pessoa.getUsuario()) && getSenha().equals(pessoa.getSenha());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsuario(), getSenha());
    }


}
