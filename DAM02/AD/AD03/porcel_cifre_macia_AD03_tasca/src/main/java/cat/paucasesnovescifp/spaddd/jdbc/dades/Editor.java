/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.paucasesnovescifp.spaddd.jdbc.dades;

import cat.paucasesnovescifp.spaddd.jdbc.auxiliars.JDBCException;
import java.util.Objects;

/**
 *
 * @author seek_
 */
public class Editor {
    int identificador;
    String nom;
    String correu;

    public Editor(int identificador, String nom, String correu) throws JDBCException {
        setIdentificador(identificador);
        setNom(nom);
        setCorreu(correu);
    }

    public void setIdentificador(int identificador) throws JDBCException {
        if (identificador > 0) {
            this.identificador = identificador;
        } else {
            throw new JDBCException("L'identificador no pot ser menys de '0'");
        }
    }

    public void setNom(String nom) throws JDBCException {
        if (nom != "" && !nom.trim().isEmpty() && nom.length() <= 50) {
            this.nom = nom;
        } else {
            throw new JDBCException("El nom no pot estar buit ni tenir més de 50 carácters");
        }
    }

    public void setCorreu(String correu) throws JDBCException {
        if (!correu.trim().equals("") && correu.length() <= 75 ) {
            this.correu = correu;
        } else {
            throw new JDBCException("El correu no pot estar format per espais en blanc ni tenir més de 74 carácters");
        }
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNom() {
        return nom;
    }

    public String getCorreu() {
        return correu;
    }

    @Override
    public String toString() {
        return "POJO{" + "identificador=" + identificador + ", nom=" + nom + ", correu=" + correu + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.identificador;
        hash = 17 * hash + Objects.hashCode(this.nom);
        hash = 17 * hash + Objects.hashCode(this.correu);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        final Editor other = (Editor) obj;
        return Objects.equals(this.nom, other.nom);
    }
}
