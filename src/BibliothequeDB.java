import java.sql.*;

import static java.lang.Class.forName;

public class BibliothequeDB {
    private Connection connection;

    public BibliothequeDB(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public void ajouterLivre(Livres livre) throws SQLException {
        String sql = "INSERT INTO livres (id, titre, auteur, categorie) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, livre.getId());
            stmt.setString(2, livre.getTitre());
            stmt.setString(3, livre.getAuteur());
            stmt.setString(4, livre.getCategorie().toString());
            stmt.executeUpdate();
        }
    }

    // Implémentez les autres opérations CRUD (Supprimer, Modifier, Rechercher)
}
