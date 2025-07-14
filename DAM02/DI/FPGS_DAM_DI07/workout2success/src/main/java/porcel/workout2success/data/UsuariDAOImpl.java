package porcel.workout2success.data;

import porcel.workout2success.data.UsuariDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import porcel.workout2success.data.DataAccess;
import porcel.workout2success.dto.Usuari;

/**
 * <p>Interfaz para realizar las interacciones con la BBDD de {@link UsuariDAO}</p>
 * <p>Contiene los métodos que interactuan con la BBDD</p>
 * 
 * @author Macia Porcel Cifre
 * @version 1.0
 */
public class UsuariDAOImpl implements UsuariDAO {

    /**
     * Obtiene una lista de todos los usuarios almacenados en la BBDD
     * 
     * @param email filtro por el que vamos a obtener el usuario
     * @return Objeto usuario
     * @throws SQLException si da err la consulta
     */
    @Override
    public Usuari get(String email) throws SQLException {
        Connection con = DataAccess.getConnection();
        Usuari usuari = null;

        String sql = "SELECT id, Nom, Email, PasswordHash, Foto, fotoFilename, Instructor, AssignedInstructor FROM usuaris WHERE Email = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("Nom");
            String oemail = rs.getString("Email");
            String pwHash = rs.getString("PasswordHash");
            String foto = rs.getString("Foto");
            String fotoFilename = rs.getString("FotoFilename");
            Boolean instructor = rs.getBoolean("Instructor");
            int assignedInstructor = rs.getInt("AssignedInstructor");

            usuari = new Usuari(id, nom, oemail, pwHash, foto, fotoFilename, instructor, assignedInstructor);
        }

        DataAccess.closeResultSet(rs);
        DataAccess.closePreparedStatement(ps);
        DataAccess.closeConnection(con);

        return usuari;
    }

    /**
     * Obtiene una lista de todos los usuarios almacenados en la BBDD
     * 
     * @return Lista de todos los usuarios de la BBDD
     * @throws SQLException si salta un error en la consulta
     */
    @Override
    public List<Usuari> getAll() throws SQLException {
        Connection con = DataAccess.getConnection();

        String sql = "SELECT * FROM Usuaris;";

        List<Usuari> usuarisList = new ArrayList<>();

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int oid = rs.getInt("id");
            String nom = rs.getString("Nom");
            String email = rs.getString("Email");
            String pwHash = rs.getString("PasswordHash");
            String foto = rs.getString("Foto");
            String fotoFilename = rs.getString("FotoFilename");
            Boolean instructor = rs.getBoolean("Instructor");
            int assignedInstructor = rs.getInt("AssignedInstructor");

            Usuari usuari = new Usuari(oid, nom, email, pwHash, foto, fotoFilename, instructor, assignedInstructor);

            usuarisList.add(usuari);
        }

        DataAccess.closeResultSet(rs);
        DataAccess.closeConnection(con);
        return usuarisList;
    }

    /**
     * Método para obtener los usuarios del instructor que ha iniciado sesiçon
     * 
     * @param mail identificador del usuario a obtener
     * @return Lista de usuarios
     * @throws SQLException si da error la consulta
     */
    @Override
    public List<Usuari> getMyUsers(String mail) throws SQLException {

        String sql = "SELECT * FROM usuaris WHERE AssignedInstructor = (SELECT Id FROM usuaris WHERE email = ?);";
        List<Usuari> usuarisList = new ArrayList<>();

        try (Connection con = DataAccess.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, mail);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nom = rs.getString("Nom");
                    String email = rs.getString("Email");
                    String pwHash = rs.getString("PasswordHash");
                    Boolean instructor = rs.getBoolean("Instructor");
                    int assignedInstructor = rs.getInt("AssignedInstructor");

                    Usuari usuari = new Usuari(id, nom, email, pwHash, null, null, instructor, assignedInstructor);
                    usuarisList.add(usuari);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return usuarisList;
    }

    /**
     * Método para guardar un usuario en la BBDD (todavía no implementado
     * )
     * @param usuari
     * @return
     * @throws SQLException 
     */
    @Override
    public int save(Usuari usuari) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Método para añadir un usuario en la BBDD
     * 
     * @param u usuario a añadir
     * @return 
     * @throws SQLException Si ocurre un error en la consulta
     */
    @Override
    public int insert(Usuari u) throws SQLException {
        String sql = "INSERT INTO dbo.Usuaris (Nom, Email, PasswordHash, Instructor)"
                + " VALUES (?,?,?,?)"
                + " SELECT CAST(SCOPE_IDENTITY() as int)";
        try (Connection conn = DataAccess.getConnection(); PreparedStatement insertStatement = conn.prepareStatement(sql)) {
            insertStatement.setString(1, u.getNom());
            insertStatement.setString(2, u.getEmail());
            insertStatement.setString(3, u.getPasswordHash());
            insertStatement.setBoolean(4, u.isInstructor());

            int newUserId = insertStatement.executeUpdate();
            return newUserId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Actualizar la información de un usuario en la BBDD
     * @param usuari usuario a actualizar
     * @return número de filas afectadas
     * @throws SQLException si falla la consulta
     */
    @Override
    public int update(Usuari usuari) throws SQLException {
        Connection con = DataAccess.getConnection();

        String sql = "Update employees set id = ?, nom = ?, email = ?, passwordHash = ?, foto = ?, fotoFilename = ?, instructor = ?, assignedInstructor = ? WHERE id = ?;";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, usuari.getId());
        ps.setString(2, usuari.getNom());
        ps.setString(3, usuari.getEmail());
        ps.setString(4, usuari.getFoto());
        ps.setString(5, usuari.getFotoFilename());
        ps.setBoolean(6, usuari.isInstructor());
        ps.setInt(7, usuari.getAssignedInstructor());

        int result = ps.executeUpdate();

        DataAccess.closePreparedStatement(ps);
        DataAccess.closeConnection(con);
        return result;
    }

    /**
     * Elimina un usuario de la BBDD
     * 
     * @param usuari usuario a eliminar
     * @return filas afectadas
     * @throws SQLException si falla la consulta
     */
    @Override
    public int delete(Usuari usuari) throws SQLException {
        Connection con = DataAccess.getConnection();

        String sql = "DELETE FROM usuarios WHERE id = ?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, usuari.getId());

        int result = ps.executeUpdate();

        DataAccess.closePreparedStatement(ps);
        DataAccess.closeConnection(con);

        return result;
    }
}
