package net.porcel.examen_presencial_ad.dto;
import java.io.Serializable;
import java.util.Objects;


public class Notificacio implements Serializable{
    private String id;
    private String texte;
    private Integer idUsuari;
    private boolean isenviat;
    private String metodoEnvio;

    public Notificacio() {
    }

    public Notificacio(String id, String texte, Integer idUsuari, boolean isenviat, String metodoEnvio) {
        this.id = id;
        this.texte = texte;
        this.idUsuari = idUsuari;
        this.isenviat = isenviat;
        this.metodoEnvio = metodoEnvio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Integer getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(Integer idUsuari) {
        this.idUsuari = idUsuari;
    }

    public boolean isIsenviat() {
        return isenviat;
    }

    public void setIsenviat(boolean isenviat) {
        this.isenviat = isenviat;
    }

    public String getMetodoEnvio() {
        return metodoEnvio;
    }

    public void setMetodoEnvio(String metodoEnvio) {
        this.metodoEnvio = metodoEnvio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.texte);
        hash = 29 * hash + Objects.hashCode(this.idUsuari);
        hash = 29 * hash + (this.isenviat ? 1 : 0);
        hash = 29 * hash + Objects.hashCode(this.metodoEnvio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Notificacio other = (Notificacio) obj;
        if (this.isenviat != other.isenviat) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.texte, other.texte)) {
            return false;
        }
        if (!Objects.equals(this.metodoEnvio, other.metodoEnvio)) {
            return false;
        }
        return Objects.equals(this.idUsuari, other.idUsuari);
    }

    @Override
    public String toString() {
        return "Notificacio{" + "id=" + id + ", texte=" + texte + ", idUsuari=" + idUsuari + ", isenviat=" + isenviat + ", metodoEnvio=" + metodoEnvio + '}';
    }
    
    
    

    

    

}