import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try{
            BibliothequeDB db = new BibliothequeDB("jdbc:mysql://localhost/joshbk","root","");
            System.out.println("connection réussi");
        }catch (SQLException e){

            e.printStackTrace();
        }

        // Création de l'instance de la bibliothèque
        Bibliotheque bibliotheque = new Bibliotheque();

        // Ajouter des livres
        bibliotheque.ajouterLivre(new Livres("1", "Le Seigneur des Anneaux", "J.R.R. Tolkien", Categorie.ROMAN));
        bibliotheque.ajouterLivre(new Livres("2", "Dune", "Frank Herbert", Categorie.SCIENCE_FICTION));
        bibliotheque.ajouterLivre(new Livres("3", "Steve Jobs", "Walter Isaacson", Categorie.BIOGRAPHIE));

        // Afficher les livres après ajout
        System.out.println("Livres après ajout :");
        afficherLivres(bibliotheque);

        // Sauvegarder les livres dans un fichier CSV
        try {
            bibliotheque.sauvegarder("livres.csv");
            System.out.println("Livres sauvegardés dans 'livres.csv'.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Charger les livres depuis un fichier CSV
        try {
            bibliotheque.charger("livres.csv");
            System.out.println("Livres chargés depuis 'livres.csv'.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Afficher tous les livres
        System.out.println("Tous les livres :");
        afficherLivres(bibliotheque);

        // Rechercher un livre par titre
        System.out.println("Recherche par titre 'Dune' :");
        List<Livres> resultats = bibliotheque.rechercherParTitre("Dune");
        afficherLivres(resultats);

        // Lister les livres par lettre
        System.out.println("Livres commençant par 'L' :");
        resultats = bibliotheque.listerParLettre('L');
        afficherLivres(resultats);

        // Afficher le nombre de livres
        System.out.println("Nombre de livres : " + bibliotheque.nombreDeLivres());

        // Afficher les livres par catégorie
        System.out.println("Livres de catégorie ROMAN :");
        resultats = bibliotheque.livresParCategorie(Categorie.ROMAN);
        afficherLivres(resultats);

        // Afficher les détails d'un livre
        System.out.println("Détails du livre avec ID '1' :");
        Livres livreDetails = bibliotheque.afficherDetails("1");
        System.out.println(livreDetails);

        // Modifier un livre
        try {
            bibliotheque.modifierLivre("1", new Livres("1", "Le Hobbit", "J.R.R. Tolkien", Categorie.ROMAN));
            System.out.println("Livre avec ID '1' modifié.");
        } catch (LivreNonTrouveException e) {
            e.printStackTrace();
        }

        // Afficher les livres après modification
        System.out.println("Livres après modification :");
        afficherLivres(bibliotheque);

        // Supprimer un livre
        try {
            bibliotheque.supprimerLivre("2");
            System.out.println("Livre avec ID '2' supprimé.");
        } catch (LivreNonTrouveException e) {
            e.printStackTrace();
        }

        // Afficher les livres après suppression
        System.out.println("Livres après suppression :");
        afficherLivres(bibliotheque);
    }

    // Méthode pour afficher les livres
    private static void afficherLivres(Bibliotheque bibliotheque) {
        for (Livres livre : bibliotheque.livres.values()) {
            System.out.println(livre);
        }
        System.out.println();
    }

    // Méthode pour afficher une liste de livres
    private static void afficherLivres(List<Livres> livres) {
        for (Livres livre : livres) {
            System.out.println(livres);
        }
        System.out.println();
    }
}
