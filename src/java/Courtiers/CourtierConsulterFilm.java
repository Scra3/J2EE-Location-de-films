package Courtiers;

import Classes.Acteur;
import Classes.Genre;
import Classes.Film;
import Classes.Realisateur;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author scra
 */
public class CourtierConsulterFilm {

    private int j = 0;
    private int idFilm;
    private String titreFilm;
    private String poster;
    private int a = 0;
    // liste
    public List<Film> film = new ArrayList<Film>();
    public List<Acteur> acteurs = new ArrayList<Acteur>();
    public List<Realisateur> realisateurs = new ArrayList<Realisateur>();
    public List<Genre> genres = new ArrayList<Genre>();

    /*LOUER un film*/
    public void louerFilm(String idFilm) throws Exception {
        //Connexion BD
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//dim-oracle.uqac.ca:1521/dimdb.uqac.ca", "trd157_28", "Z?EqVJ57TZrm");
        Statement stmt = conn.createStatement();
        /*Requete*/
        String requete = "UPDATE Film SET qteStock = qteStock - 1 WHERE idFilm ='" + idFilm + "'";

        /*Exécuter requete*/
        ResultSet rset = stmt.executeQuery(requete);
        rset.close();

        stmt.close();

        conn.close();
    }

    public Map rechercheBDSelect(Map listSelect, String nomAttribut, String nomTable) throws SQLException {
        //iterator
        // Afficher le contenu du MAP
        Set listKeys = listSelect.keySet();  // Obtenir la liste des clés
        Iterator iterateur = listKeys.iterator();
        int i = 0;
        //Sans hibernate connexion bd
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@//dim-oracle.uqac.ca:1521/dimdb.uqac.ca", "trd157_28", "Z?EqVJ57TZrm");

        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("Select " + nomAttribut + " FROM " + nomTable);
        // Parcourir les clés et afficher les entrées de chaque clé;
        while (iterateur.hasNext()) {
            listSelect.put(i, "<option>" + rset.getString(nomAttribut) + "</option>");
            i++;
        }
        return listSelect;
    }

    public Film requeteFilmAfficher(String idFilm, Film unFilm) throws Exception {

        /*VARIABLE*/
        int idFilm2 = 0;
        String resume;
        String genre;
        String aventure;

        //Connexion BD
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//dim-oracle.uqac.ca:1521/dimdb.uqac.ca", "trd157_28", "Z?EqVJ57TZrm");
        Statement stmt = conn.createStatement();

        /*Requete*/
        String requete = "SELECT DISTINCT titre,poster,idFilm,resume FROM vueRechercheFilm WHERE idFilm ='" + idFilm + "'";

        /*Exécuter requete*/
        ResultSet rset = stmt.executeQuery(requete);

        /*Trier*/
        if (rset != null) {
            while (rset.next()) {
                titreFilm = rset.getString("titre");
                poster = rset.getString("poster");
                idFilm2 = rset.getInt("idFilm");
                resume = rset.getString("resume");
                BigDecimal id = new BigDecimal(idFilm2);
                unFilm.setIdfilm(id);
                unFilm.setTitre(titreFilm);
                unFilm.setPoster(poster);
                unFilm.setResume(resume);
            }
            /*Requetes*/
            requete = "SELECT DISTINCT nomRealisateur FROM vueRechercheFilm WHERE idFilm ='" + idFilm + "'";
            String requete2 = "SELECT DISTINCT nomActeur FROM vueRechercheFilm WHERE idFilm ='" + idFilm + "'";
            String requete3 = "SELECT DISTINCT genre FROM vueRechercheFilm WHERE idFilm ='" + idFilm + "'";

            String nom;
            // acteur
            rset = stmt.executeQuery(requete2);
            while (rset.next()) {
                nom = rset.getString("nomActeur");
                acteurs.add(new Acteur(nom));
            }
            //realisateur
            rset = stmt.executeQuery(requete);
            while (rset.next()) {
                nom = rset.getString("nomRealisateur");
                realisateurs.add(new Realisateur(nom));
            }
            // genre
            rset = stmt.executeQuery(requete3);
            while (rset.next()) {
                nom = rset.getString("genre");
                genres.add(new Genre(nom));
            }
        } else {
            unFilm = null;
        }

        rset.close();

        stmt.close();

        conn.close();

        return unFilm;
    }

    public String qtMaxFilm(String idFilm) throws Exception {
        String bool = "";
        String requete = null;
        int qtMax = 0;
        int qt = 0;
        //Connexion BD
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//dim-oracle.uqac.ca:1521/dimdb.uqac.ca", "trd157_28", "Z?EqVJ57TZrm");
        Statement stmt = conn.createStatement();
        requete = "SELECT qteStock,qteStockMax FROM Film where idFilm ='" + idFilm + "'";
        /*Exécuter requete*/
        ResultSet rset = stmt.executeQuery(requete);

        /*Trier requete*/
        if (rset != null) {
            while (rset.next()) {
                qt = rset.getInt("qteStock");
                qtMax = rset.getInt("qteStockMax");
            }

            if ((qtMax - qt) < qtMax) {
                bool = "true";
            }
        }
        return bool;
    }

    public List<Film> getFilmsBD(String titre, String intervalle1, String intervalle2, String paysProduction, String langue, String genre, String realisateur, String acteur) throws Exception {

        //Connexion BD
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//dim-oracle.uqac.ca:1521/dimdb.uqac.ca", "trd157_28", "Z?EqVJ57TZrm");
        Statement stmt = conn.createStatement();

        /*REQUETE*/
        String requete = null;
        if (titre == null && intervalle1 == null && intervalle2 == null && paysProduction.equals("Pays de production") && langue.equals("Langue originale du film") && genre.equals("Genre") && realisateur == null && acteur == null) {
            requete = "SELECT DISTINCT idFilm, poster,titre FROM vueRechercheFilm";
        } else {
            String[] tabChamp = new String[7];
            tabChamp[0] = titre;
            tabChamp[1] = intervalle1;
            tabChamp[2] = paysProduction;
            tabChamp[3] = langue;
            tabChamp[4] = genre;
            tabChamp[5] = realisateur;
            tabChamp[6] = acteur;
            String[] tabNom = new String[7];
            tabNom[0] = "titre";
            tabNom[1] = "annee";
            tabNom[2] = "pays";
            tabNom[3] = "langue";
            tabNom[4] = "genre";
            tabNom[5] = "nomRealisateur";
            tabNom[6] = "nomActeur";

            for (int i = 0; i < 7; i++) {
                if (tabChamp[i] != null && !tabChamp[i].equals("Pays de production") && !tabChamp[i].equals("Langue originale du film") && !tabChamp[i].equals("Genre")) {
                    if (requete == null) {
                        if (tabNom[i].equals("annee")) {
                            requete = "SELECT DISTINCT idFilm, poster, titre FROM vueRechercheFilm WHERE annee BETWEEN '" + intervalle1 + "' AND ' " + intervalle2 + "'  ";
                        } else if (tabNom[i].equals("titre") || tabNom[i].equals("nomActeur") || tabNom[i].equals("nomRealisateur")) {
                            requete = "SELECT DISTINCT idFilm, poster,titre FROM vueRechercheFilm WHERE " + tabNom[i] + " LIKE '%" + tabChamp[i] + "%'";
                        } else {
                            requete = "SELECT DISTINCT idFilm, poster,titre FROM vueRechercheFilm WHERE " + tabNom[i] + "='" + tabChamp[i] + "'";
                        }
                    } else if (tabNom[i].equals("annee")) {
                        requete = requete + " AND  annee BETWEEN '" + intervalle1 + "' AND ' " + intervalle2 + "'";
                    } else if (tabNom[i].equals("titre") || tabNom[i].equals("nomActeur") || tabNom[i].equals("nomRealisateur")) {
                        requete = requete + " AND  " + tabNom[i] + " LIKE '%" + tabChamp[i] + "%'";
                    } else {
                        requete = requete + " AND  " + tabNom[i] + "='" + tabChamp[i] + "'";
                    }
                }
            }
        }

        /*Exécuter requete*/
        ResultSet rset = stmt.executeQuery(requete);

        /*Trier requete*/
        if (rset != null) {
            while (rset.next()) {
                titreFilm = rset.getString("titre");
                poster = rset.getString("poster");
                idFilm = rset.getInt("idFilm");
                BigDecimal l = new BigDecimal(idFilm);
                film.add(new Film(l, titreFilm, poster));
            }
        } else {
            film = null;
        }

        rset.close();

        stmt.close();

        conn.close();

        return film;
    }
}
