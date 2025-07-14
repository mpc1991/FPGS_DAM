package porcel.workout2success.dto;

/**
 * Clase dto para los Usuarios
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public class Usuari {

    private int id;
    private String nom;
    private String email;
    private String passwordHash;
    private String foto;
    private String fotoFilename;
    private boolean instructor;
    private int assignedInstructor;

    /**
     * Constructor con todos los atributos
     * 
     * @param id del usuario
     * @param nom del usuario
     * @param email del usuario
     * @param passwordHash del usuario
     * @param foto del usuario
     * @param fotoFilename del usuario
     * @param instructor Es instructor o no
     * @param assignedInstructor Instructor asignado al usuario
     */
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

    /**
     * Constructor sin ID, Foto, Filename, assignedInstructor
     * @param nom del usuario
     * @param email del usuario
     * @param passwordHash del usuario
     * @param instructor  es instructor o no
     */
    public Usuari(String nom, String email, String passwordHash, boolean instructor) {
        this.nom = nom;
        this.email = email;
        this.passwordHash = passwordHash;
        this.instructor = instructor;
    }

    /**
     * Getter del id del usuario
     * 
     * @return id del usuario 
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del id del usuario
     * 
     * @param id id del usuario a implantar 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter del nombre de usuario
     * 
     * @return devuelve el nombre del usuario 
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter del nombre del usuario
     * 
     * @param nom nombre a implantar
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter del mail del usuario
     * 
     * @return devuelve el mail del usuario
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter del mail del usuario
     * 
     * @param email mail a implementar
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter del hash del password del usuario
     * 
     * @return devuelve el hash de la password del usuario 
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Setter del hash del password del usuario
     * 
     * @param passwordHash hash del password del usuario
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Getter de la foto del usuario
     * 
     * @return devuelve la foto del usuario 
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Setter de la foto del usuario
     * 
     * @param foto foto a implementar
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * getter de nombre del archivo de la foto del usuario
     * 
     * @return devuelve el nombre del archivo de la foto del usuario
     */
    public String getFotoFilename() {
        return fotoFilename;
    }

    /**
     * Setter del nombre de la foto del usuario
     * 
     * @param fotoFilename nombre a implementar
     */
    public void setFotoFilename(String fotoFilename) {
        this.fotoFilename = fotoFilename;
    }

    /**
     * Getter del booleano para saber si el usuario es o no instructor
     * 
     * @return false si no lo es, true si lo es
     */
    public boolean isInstructor() {
        return instructor;
    }

    /**
     * Setter del booleano para saber si el usuario es instructor o no
     * 
     * @param instructor booleano a implementar
     */
    public void setInstructor(boolean instructor) {
        this.instructor = instructor;
    }

    /**
     * Getter del instructor asignado al usuario
     * 
     * @return devuelve el instructor asignado al usuario 
     */
    public int getAssignedInstructor() {
        return assignedInstructor;
    }

    /**
     * Setter del instructor asignado al usuario
     * 
     * @param assignedInstructor instructor a implementar
     */
    public void setAssignedInstructor(int assignedInstructor) {
        this.assignedInstructor = assignedInstructor;
    }

    @Override
    public String toString() {
        return nom;
    }
}
