package entities;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Galeria {

    private String titulo;
    private List<Foto> fotos;
    private GravadorDeDados gravadorDadosFotos = new GravadorDeDados("dadosFotos.txt");
    

    public Galeria(String titulo) {
        this.titulo = titulo;
       this.fotos = new ArrayList<>();
    }

    public void registrarFoto(Foto foto) {
        this.fotos.add(foto);
    }

    public void salvarFotos() throws IOException {
       List<String> dadosFotos = new ArrayList<>();
       for (Foto foto: this.fotos) {
           dadosFotos.add(this.titulo + "#" + foto.getDescricao() + "#" + foto.getDataFoto() + "#" + foto.getCaminhoFoto());
       }
       gravadorDadosFotos.gravaTextoEmArquivo(dadosFotos);
    }

    public void recuperarFotos() throws IOException {
        List<String> dadosFotos = gravadorDadosFotos.recuperarTextoDeArquivo();
        for (String dados: dadosFotos) {
            String[] linha = dados.split("#");
            Foto foto = new Foto(linha[0], linha[1], linha[2], linha[3]);
            this.fotos.add(foto);
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int quantidadeFotos() {
        return this.fotos.size();
    }

    public StringBuilder getFotos(){
        StringBuilder descricaoFoto = new StringBuilder();
        if(!this.fotos.isEmpty()){
            descricaoFoto.append("\tFotos do Usuário\n\n");
            for (Foto f: this.fotos ) {
                descricaoFoto.append(f.getDescricao() + " " + f.getDataFoto() + "\n");
            }
            return descricaoFoto;
        }
        else return null;
    }

    public List<Foto> getListaFotos() {
        return fotos;
    }

    public void apagarFoto(String descricao){
        for (Foto f: this.fotos) {
            if(f.getDescricao().equals(descricao)){
                fotos.remove(f);
                break;
            }
        }
    }
}
