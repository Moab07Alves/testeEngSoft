package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Galeria {

    private List<Foto> fotos = new ArrayList<>();
    private List<String> caminhoFotos = new ArrayList<>();
    private List<String> descricaoFotos = new ArrayList<>();
    private List<String> dataFotos = new ArrayList<>();
    

    public Galeria() {
       
    }

    public void registrarFoto(Foto foto) {
        this.fotos.add(foto);
        this.caminhoFotos.add(foto.getCaminhoFoto());
        this.descricaoFotos.add(foto.getDescricao());
        this.dataFotos.add("" + foto.getDataFoto());
    }

    public void salvarFotos() throws IOException {
       
        
    }

    public void recuperarFotos() throws IOException {
        List<String> pathRecuperadoFoto = gravadorCaminhoFoto.recuperarTextoDeArquivo();
        for (String pathImagem: pathRecuperadoFoto) {
            this.caminhoFotos.add(pathImagem);
        }

        List<String> descricaoImagem = gravadorCaminhoFoto.recuperarTextoDeArquivo();
        for (String descricaoImag: descricaoImagem) {
            this.caminhoFotos.add(descricaoImag);
        }


    }

}
