package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Location_Films extends HttpServlet {

    public static final String ATT_USER = "utilisateur";
    public static final String ATT_FORM = "form";
    public static final String ACCES_PUBLIC = "/";
    public static final String ACCES = "/Location_Films";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";

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
            /* Affichage de la page restreinte */
            this.getServletContext().getRequestDispatcher(ACCES).forward(request, response);
        }
    }
}
