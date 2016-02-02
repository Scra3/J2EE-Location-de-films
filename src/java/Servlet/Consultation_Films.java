package Servlet;

import Classes.Film;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Facades.FacadeConsulterFilms;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consultation_Films extends HttpServlet {

    public static final String ATT_USER = "utilisateur";
    public static final String ATT_ACTEUR = "acteur";
    public static final String ATT_LANGUE = "langue";
    public static final String ATT_PAYS = "pays";
    public static final String ATT_FILMS = "films";
    public static final String ATT_FORM = "form";
    public static final String ATT_GET = "form";
    public static final String ATT_AFFICHE = "affiche";
    public static final String ACCES_PUBLIC = "/";
    public static final String ACCES = "/WEB-INF/interface/consulterFilm.jsp";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String REQUETE_FILMS = "resultats";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        /*
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */

        if (session.getAttribute(ATT_SESSION_USER) == null) {
            /* Redirection vers la page publique */
            response.sendRedirect(request.getContextPath() + ACCES_PUBLIC);
        } else {
            /* Affichage de la page restreinte */
 /*Charger les formulaires genre , pays de production et  langue originale du film*/
 /* Préparation de l'objet ConsulterFilm */
            FacadeConsulterFilms formGet;
            formGet = new FacadeConsulterFilms();
            if (request.getParameter("idFilm") != null) {

                /* Stockage du formulaire  */
                String film = formGet.rechercherFicheFilm(request);
                request.setAttribute(ATT_AFFICHE, film);
            }
            if (request.getParameter("louer") !=null) {

                /* Stockage du formulaire  */
                formGet.louerFilm(request);
                request.setAttribute(ATT_AFFICHE, "<h1>Film loué</h1>");
            }
            
            request.setAttribute(ATT_FORM, formGet);

            this.getServletContext().getRequestDispatcher(ACCES).forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        /*
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if (session.getAttribute(ATT_SESSION_USER) == null) {
            /* Redirection vers la page publique */
            response.sendRedirect(request.getContextPath() + ACCES_PUBLIC);
        } else {

            /* Préparation de l'objet ConsulterFilm */
            FacadeConsulterFilms form;
            form = new FacadeConsulterFilms();

            /* Traitement de la requête  */
            form.verificationChamp(request);

            // Implémentation des select JSP 
            /*FacadeConsulterFilms film = new FacadeConsulterFilms();
                Map listSelectPays = new java.util.HashMap();
                Map listSelectLangue = new java.util.HashMap();
                Map listSelectActeur = new java.util.HashMap();*/
            // pays de prod
            //listSelectPays = film.implementerSelectPaysDeProd(listSelectPays);
            // langue
            //listSelectLangue = film.implementerSelectLangue(listSelectLangue);
            //genre
            //listSelectActeur = film.implementerSelectActeur(listSelectActeur);
            // FIN implémentation des selects
            // Rechercher films 
            String[] tabFilm = form.rechercherFilm(request);


            /* Stockage du formulaire  */
            //request.setAttribute(ATT_ACTEUR, listSelectActeur);
            //request.setAttribute(ATT_LANGUE, listSelectLangue);
            //request.setAttribute(ATT_PAYS, listSelectPays);
            request.setAttribute(ATT_FORM, form);
            /*FILM*/
            request.setAttribute(ATT_FILMS, tabFilm);

            this.getServletContext().getRequestDispatcher(ACCES).forward(request, response);

        }
    }
}
