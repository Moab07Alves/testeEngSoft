package entities;

import java.util.ArrayList;
import java.util.List;

public class Galeria {

    private Pessoa pessoa;
    private List<Foto> fotos = new ArrayList<>();

    public Galeria(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void registrarFoto(Foto foto) {
        this.fotos.add(foto);
    }

}
