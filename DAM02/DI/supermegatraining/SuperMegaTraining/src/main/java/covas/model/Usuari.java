package covas.model;

/**
 *
 * @author Toni Covas
 */
public class Usuari {

    private int id;
    private String nom;
    private String email;
    private String passwordHash;
    private byte[] foto;
    private boolean instructor;

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String Nom) {
        this.nom = Nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String PasswordHash) {
        this.passwordHash = PasswordHash;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] Foto) {
        this.foto = Foto;
    }

    public boolean isInstructor() {
        return instructor;
    }

    public void setInstructor(boolean Instructor) {
        this.instructor = Instructor;
    }

}
