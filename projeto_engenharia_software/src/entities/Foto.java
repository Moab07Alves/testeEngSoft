package entities;

import java.time.LocalDate;
import java.util.Objects;

public class Foto {

    private String tituloDaGaleria;
    private String descricao;
    private String dataFoto; // Pensar em mudar esse localdate para String, por causa do input da data pelo usuário
    private String caminhoFoto;

    public Foto(String tituloDaGaleria, String descrição, String dataFoto, String caminhoFoto) {
        this.tituloDaGaleria = tituloDaGaleria;
        this.descricao = descrição;
        this.dataFoto = dataFoto;
        this.caminhoFoto = caminhoFoto;
    }

    public Foto(String tituloDaGaleria, String dataFoto, String caminhoFoto) {
        this(tituloDaGaleria, "", dataFoto, caminhoFoto);
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

    public String getTituloDaGaleria() {
        return tituloDaGaleria;
    }

    public void setTituloDaGaleria(String tituloDaGaleria) {
        this.tituloDaGaleria = tituloDaGaleria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foto foto = (Foto) o;
        return Objects.equals(tituloDaGaleria, foto.tituloDaGaleria) && Objects.equals(descricao, foto.descricao) && Objects.equals(dataFoto, foto.dataFoto) && Objects.equals(caminhoFoto, foto.caminhoFoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tituloDaGaleria, descricao, dataFoto, caminhoFoto);
    }
}
