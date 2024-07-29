public class Livres {
    private String id;
    private String titre;
    private String auteur;
    private Categorie categorie;

    public Livres(String id, String titre, String auteur, Categorie categorie) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
    }

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }
    public Categorie getCategorie() { return categorie; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }

    @Override
    public String toString() {
        return "ID: " + id + ", Titre: " + titre + ", Auteur: " + auteur + ", Catégorie: " + categorie;
    }
}
