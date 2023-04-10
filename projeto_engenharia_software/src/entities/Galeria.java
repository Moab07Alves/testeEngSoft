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
           dadosFotos.add(foto.getDescricao() + "#" + foto.getDataFoto() + "#" + foto.getCaminhoFoto());
       }
       gravadorDadosFotos.gravaTextoEmArquivo(dadosFotos);
    }

    public void recuperarFotos() throws IOException {
        List<String> dadosFotos = gravadorDadosFotos.recuperarTextoDeArquivo();
        for (String dados: dadosFotos) {
            String[] linha = dados.split("#");
            Foto foto = new Foto(linha[0], LocalDate.parse(linha[1]), linha[2]);
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

}
