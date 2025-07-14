package porcel.workout2success.dto;

public class Usuari {

    private int id;
    private String nom;
    private String email;
    private String passwordHash;
    private String foto;
    private String fotoFilename;
    private boolean instructor;
    private int assignedInstructor;

    public Usuari(int id, String nom, String email, String passwordHash, String foto, String fotoFilename, boolean instructor, int assignedInstructor) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.passwordHash = passwordHash;
        this.foto = foto;
        this.fotoFilename = fotoFilename;
        this.instructor = instructor;
        this.assignedInstructor = assignedInstructor;
    }

    public Usuari(String nom, String email, String passwordHash, boolean instructor) {
        this.nom = nom;
        this.email = email;
        this.passwordHash = passwordHash;
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFotoFilename() {
        return fotoFilename;
    }

    public void setFotoFilename(String fotoFilename) {
        this.fotoFilename = fotoFilename;
    }

    public boolean isInstructor() {
        return instructor;
    }

    public void setInstructor(boolean instructor) {
        this.instructor = instructor;
    }

    public int getAssignedInstructor() {
        return assignedInstructor;
    }

    public void setAssignedInstructor(int assignedInstructor) {
        this.assignedInstructor = assignedInstructor;
    }

    @Override
    public String toString() {
        return nom;
    }
}
