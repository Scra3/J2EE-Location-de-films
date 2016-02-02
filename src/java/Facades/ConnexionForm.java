package Facades;

import Courtiers.CourtierBDUtilisateur;
import Classes.Membre;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public final class ConnexionForm {

    private static final String CHAMP_EMAIL = "identifiant";
    private static final String CHAMP_PASS = "mdp";
    private static final String BDUSER = "membre";
    private CourtierBDUtilisateur cBDUtilisateur;

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Membre connecterUtilisateur(HttpServletRequest request) {
        /* Récupération des champs du formulaire */
        String email = getValeurChamp(request, CHAMP_EMAIL);
        String motDePasse = getValeurChamp(request, CHAMP_PASS);

        Membre unMembre = new Membre();

        /* Validation du champ email. */
        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        unMembre.setCourriel(email);

        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse(motDePasse);
        } catch (Exception e) {
            setErreur(CHAMP_PASS, e.getMessage());
        }
        unMembre.setMotdepasse(motDePasse);

        /*Authentification de l'utilisateur*/
        /*PROBLEME HIBERNATE*/
        cBDUtilisateur = new CourtierBDUtilisateur();
        if (erreurs.isEmpty()) {
            try {
              unMembre = cBDUtilisateur.getUtilisateur(unMembre.getCourriel(), unMembre.getMotdepasse());
              if(unMembre == null){
                setErreur(BDUSER, "<div class=\"alert alert-warning\" role=\"alert\">Merci de saisir des identifiants valident </div>");
              }
            } catch (Exception e) {
                setErreur(BDUSER, "<div class=\"alert alert-warning\" role=\"alert\">Merci de saisir des identifiants valident </div>");
            }
        }
        return unMembre;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void validationEmail(String email) throws Exception {

        if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            throw new Exception("<div class=\"alert alert-danger\" role=\"alert\">Merci de saisir une adresse mail valide.</div>");

        } else if (email == null) {
            throw new Exception("<div class=\"alert alert-danger\" role=\"alert\">Merci de saisir votre identifiant  </div>");
        }

    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse(String motDePasse) throws Exception {
        if (motDePasse != null) {
            if (motDePasse.length() < 5) {
                throw new Exception("<div class=\"alert alert-danger\" role=\"alert\">Le mot de passe doit contenir au moins 5 caractères.</div>");
            }
        } else {
            throw new Exception("<div class=\"alert alert-danger\" role=\"alert\">Merci de saisir votre mot de passe.</div>");
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
