package Facades;

import Classes.Film;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import Courtiers.CourtierConsulterFilm;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import static oracle.jrockit.jfr.events.Bits.intValue;

public final class FacadeConsulterFilms {

    private static final String CHAMP_TITRE = "titreFilm";
    private static final String CHAMP_INTERVALLE = "intervalleAnnee";
    private static final String CHAMP_PAYS = "production";
    private static final String CHAMP_LANGUE = "langue";
    private static final String CHAMP_GENRE = "genre";
    private static final String CHAMP_REALISATEUR = "nomRealisateur";
    private static final String CHAMP_ACTEUR = "nomActeur";
    private static final String BDUSER = "membre";

    List<Film> Films;
    List<String> listSelect;
    String[] tab = new String[2]; // sauvegarder les deux années d'intervalle
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public void louerFilm(HttpServletRequest request){
        
        CourtierConsulterFilm monUpdate = new CourtierConsulterFilm();
        String idFilm = request.getParameter("idFilm");
        try{
        monUpdate.louerFilm(idFilm);
        }catch(Exception e ){
            
        }
    }
    public void verificationChamp(HttpServletRequest request) {

        /* Récupération des champs du formulaire */
        String titre = getValeurChamp(request, CHAMP_TITRE);
        String intervalle = getValeurChamp(request, CHAMP_INTERVALLE);
        String paysProduction = getValeurChamp(request, CHAMP_PAYS);
        String langue = getValeurChamp(request, CHAMP_LANGUE);
        String genre = getValeurChamp(request, CHAMP_GENRE);
        String realisateur = getValeurChamp(request, CHAMP_REALISATEUR);
        String acteur = getValeurChamp(request, CHAMP_ACTEUR);

        /* Validation du champ titre. */
        if (titre != null) {
            try {
                validationNombreCaratere(titre, "Titre");

            } catch (Exception e) {
                setErreur(CHAMP_TITRE, e.getMessage());
            }
        }

        /* Validation du champ intervalle */
        if (intervalle != null) {
            try {
                validationIntervalle(intervalle);

            } catch (Exception e) {
                setErreur(CHAMP_INTERVALLE, e.getMessage());
            }
        }

        /* Validation nom réalisateur  */
        if (realisateur != null) {
            try {
                validationNombreCaratere(realisateur, "Réalisateur");

            } catch (Exception e) {
                setErreur(CHAMP_REALISATEUR, e.getMessage());
            }
        }

        /* Validation nom acteur  */
        if (acteur != null) {
            try {
                validationNombreCaratere(acteur, "Acteur");

            } catch (Exception e) {
                setErreur(CHAMP_ACTEUR, e.getMessage());
            }
        }

    }

    /*Recherche les pays de production*/
    public Map implementerSelectPaysDeProd(Map listSelectPays) throws SQLException {
        //requete base de donnée 
        CourtierConsulterFilm maRecherche = new CourtierConsulterFilm();
        listSelectPays = maRecherche.rechercheBDSelect(listSelectPays, "pays", "Pays");
        return listSelectPays;
    }

    /*Recherche les langues des films*/
    public Map implementerSelectLangue(Map listSelectLangue) throws SQLException {
        //requete base de donnée 
        CourtierConsulterFilm maRecherche = new CourtierConsulterFilm();
        listSelectLangue = maRecherche.rechercheBDSelect(listSelectLangue, "Langue", "Film");
        return listSelectLangue;
    }

    /*Recherche les langues des Acteurs*/
    public Map implementerSelectActeur(Map listSelectActeur) throws SQLException {
        //requete base de donnée 
        CourtierConsulterFilm maRecherche = new CourtierConsulterFilm();
        listSelectActeur = maRecherche.rechercheBDSelect(listSelectActeur, "nom", "Acteur");
        return listSelectActeur;
    }

    /*Rechercher la fiche d'un film*/
    public String rechercherFicheFilm(HttpServletRequest request) {
        Film unFilm = new Film();
        String film = null;
        String idFilm = request.getParameter("idFilm");
        String boutton ="";
        CourtierConsulterFilm courtier = new CourtierConsulterFilm();
        try {
            unFilm = courtier.requeteFilmAfficher(idFilm, unFilm);
            if (unFilm == null || unFilm.getTitre() == null) {
                setErreur(BDUSER, "<div  class=\"alert alert-info\" role=\"alert\">Aucun films ne correspondent à vos critères </div>");
            } else {
                String nomActeur = "Acteurs : ";
                String nomRealisateur = "Réalisateurs : ";
                for (int i = 0; i < courtier.acteurs.size(); i++) {
                    nomActeur = nomActeur + "<li>"+courtier.acteurs.get(i).getNom()+"</li>";
                }
                for (int i = 0; i < courtier.realisateurs.size(); i++) {
                    nomRealisateur = nomRealisateur + "<li>"+courtier.realisateurs.get(i).getNom() +"</li>";
                }
                // vérifier qtMax
                try{
                boutton = courtier.qtMaxFilm( idFilm);
                }
                catch(Exception e){
                    
                }
                if (boutton.equals("true")) {
                    boutton = "<h2><a href=\"?louer=true&idFilm="+idFilm+"\" ><button  type=\"button\" class=\"btn btn-primary btn-lg\">LOUER</button></a></h2>";
                }
                film = "<div class =\"center-block\"><h2>" + unFilm.getTitre() + "</h2><img src=images/" + unFilm.getPoster() + " alt=\"affiche du film\"  class=\"img-thumbnail img-responsive\">"
                        + boutton
                        + "<div><h3>" + unFilm.getResume() + "</h3></div><h3> Réalisateur : " + courtier.realisateurs.get(0).getNom() + "</h3><h3>"+nomActeur+"</h3><h3>"+nomRealisateur+"</h3><div>";
                
                
            }
        } catch (Exception e) {
            setErreur(BDUSER, "<div  class=\"alert alert-info\" role=\"alert\">Aucun films ne correspondent à vos critères erreurs</div>");
        }
        return film;
    }

    public String[] rechercherFilm(HttpServletRequest request) {
        String titre = getValeurChamp(request, CHAMP_TITRE);
        String intervalle = getValeurChamp(request, CHAMP_INTERVALLE);
        String paysProduction = getValeurChamp(request, CHAMP_PAYS);
        String langue = getValeurChamp(request, CHAMP_LANGUE);
        String genre = getValeurChamp(request, CHAMP_GENRE);
        String realisateur = getValeurChamp(request, CHAMP_REALISATEUR);
        String acteur = getValeurChamp(request, CHAMP_ACTEUR);
        String[] tabFilm = null;
        /*Requète base de données*/
        if (erreurs.isEmpty()) {
            try {
                // récupérer les intervalles
                String intervalle1 = null;
                String intervalle2 = null;
                if (intervalle != null) {
                    validationIntervalle(intervalle);
                    intervalle1 = tab[0];
                    intervalle2 = tab[1];
                }

                CourtierConsulterFilm maRecherche = new CourtierConsulterFilm();
                Films = maRecherche.getFilmsBD(titre, intervalle1, intervalle2, paysProduction, langue, genre, realisateur, acteur);
                if (Films == null || Films.isEmpty()) {
                    setErreur(BDUSER, "<div  class=\"alert alert-info\" role=\"alert\">Aucun films ne correspondent à vos critères </div>");
                } else {

                    tabFilm = new String[Films.size()];
                    for (int i = 0; i < tabFilm.length; i++) {
                        tabFilm[i] = "<a href =\"?idFilm=" + Films.get(i).getIdfilm() + "\"> <img  id ='" + Films.get(i).getIdfilm() + "' onMouseOver=\"document.getElementById('" + Films.get(i).getIdfilm() + "').style.height = '310' \" onMouseOut=\"document.getElementById('" + Films.get(i).getIdfilm() + "').style.height = '' \" src=images/" + Films.get(i).getPoster() + " alt=\"affiche du film\"  class=\"img-thumbnail img-responsive\"></a>"; // 
                    }
                }
            } catch (Exception e) {
                setErreur(BDUSER, "<div class=\"alert alert-info\" role=\"alert\">La base de données à rencontrée un problème</div>");
            }

        }
        return tabFilm;
    }

    /**
     * Valide du titre saisie.
     */
    private void validationNombreCaratere(String champ, String nomChamp) throws Exception {

        if (champ.length() < 4) {
            throw new Exception(" <div class=\"alert alert-danger\"role=\"alert\">Merci de saisir un " + nomChamp + " avec au moins 4 caractère </div>");
        }
    }

    /**
     * Valide intervalle année.
     */
    private void validationIntervalle(String intervalle) throws Exception {

        if (intervalle != null && !intervalle.matches("[1-9][0-9]{3}/[0-9]{4}")) {
            throw new Exception(" <div class=\"alert alert-danger\"role=\"alert\">Merci de saisir un intervalle correspondant au format : dateMin/dateMax et avec 4 chiffres\n</div>");
        } else if (intervalle != null && intervalle.matches("[1-9][0-9]{3}/[0-9]{4}")) {

            Pattern pattern = Pattern.compile("(.*)/(.*)");
            String chaine = intervalle;
            Matcher m = pattern.matcher(chaine);
            int premier = 0;
            int second = 0;
            while (m.find()) {
                premier = Integer.parseInt(m.group(1));
                second = Integer.parseInt(m.group(2));
            }
            if (premier > second) {
                throw new Exception(" <div class=\"alert alert-danger\"role=\"alert\">Merci de saisir un intervalle correspondant au format : dateMin/dateMax  : ATTENTION " + premier + " > " + second + "\n</div>");
            } else {
                tab[0] = Integer.toString(premier);
                tab[1] = Integer.toString(second);
            }
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur;
        }
    }
}
