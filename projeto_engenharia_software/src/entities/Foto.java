package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Foto {

    //private String tituloDaGaleria;
    private String descricao;
    private String dataFoto; // Pensar em mudar esse localdate para String, por causa do input da data pelo usuário
    private String caminhoFoto;

    public Foto(String descrição, String dataFoto, String caminhoFoto) {
        this.descricao = descrição;
        this.dataFoto = dataFoto;
        this.caminhoFoto = caminhoFoto;
    }

    public Foto(String dataFoto, String caminhoFoto) {
        this("", dataFoto, caminhoFoto);
    }

    public String getDescricao() {
        return descricao;
    }

    public void mudarDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataFoto() {
        return dataFoto;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Foto foto)) return false;
        return Objects.equals(getDescricao(), foto.getDescricao()) && getDataFoto().equals(foto.getDataFoto()) && getCaminhoFoto().equals(foto.getCaminhoFoto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDescricao(), getDataFoto(), getCaminhoFoto());
    }

}
