import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Bibliotheque {
    Map<String, Livres> livres;

    public Bibliotheque() {
        livres = new HashMap<>();
    }

    // Ajouter un livre
    public void ajouterLivre(Livres livre) {
        livres.put(livre.getId(), livre);
    }

    // Supprimer un livre
    public void supprimerLivre(String id) throws LivreNonTrouveException {
        livres.remove(id);
    }

    // Rechercher un livre par titre
    public List<Livres> rechercherParTitre(String titre) {
        List<Livres> resultats = new ArrayList<>();
        for (Livres livre : livres.values()) {
            if (livre.getTitre().contains(titre)) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    // Lister les livres par lettre
    public List<Livres> listerParLettre(char lettre) {
        List<Livres> resultats = new ArrayList<>();
        for (Livres livre : livres.values()) {
            if (livre.getTitre().toUpperCase().startsWith(String.valueOf(lettre).toUpperCase())) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    // Afficher le nombre de livres
    public int nombreDeLivres() {
        return livres.size();
    }

    // Afficher les livres par catégorie
    public List<Livres> livresParCategorie(Categorie categorie) {
        List<Livres> resultats = new ArrayList<>();
        for (Livres livre : livres.values()) {
            if (livre.getCategorie() == categorie) {
                resultats.add(livre);
            }
        }
        return resultats;
    }

    // Afficher les détails d'un livre par son identifiant
    public Livres afficherDetails(String id) {
        return livres.get(id);
    }

    // Sauvegarder les livres dans un fichier
    public void sauvegarder(String cheminFichier) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier))) {
            for (Livres livre : livres.values()) {
                writer.write(livre.getId() + "," + livre.getTitre() + "," + livre.getAuteur() + "," + livre.getCategorie());
                writer.newLine();
            }
        }
    }

    // Charger les livres depuis un fichier
    public void charger(String cheminFichier) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] composants = ligne.split(",");
                String id = composants[0];
                String titre = composants[1];
                String auteur = composants[2];
                Categorie categorie = Categorie.valueOf(composants[3]);
                ajouterLivre(new Livres(id, titre, auteur, categorie));
            }
        }
    }
    public void modifierLivre(String id, Livres nouveauLivre) throws LivreNonTrouveException {
        if (livres.containsKey(id)) {
            livres.put(id, nouveauLivre);
        } else {
            throw new LivreNonTrouveException("Livre avec ID " + id + " non trouvé.");
        }
    }

}
