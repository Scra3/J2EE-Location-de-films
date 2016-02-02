package Courtiers;

import Classes.Connect;
import Classes.Membre;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.hibernate.SessionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author scra
 */
public class CourtierBDUtilisateur {

    Membre unMembre;
    int i = 0;
    String courriel;
    String nomFamille;

    public Membre getUtilisateur(String username, String motDePasse) throws Exception {
        /*  Connect con = new Connect();
        Connection conn;
        conn = con.connexion();
        ResultSet resultatSelResultat;
        Statement unEnonceSQL = conn.createStatement();

        /* Création de l'objet gérant les requêtes */
        //HIBERNATE
        /*Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        Session session = sessionFactory.openSession();
        
        resultatSelResultat = unEnonceSQL.executeQuery("SELECT idMembre FROM Membre WHERE courriel =" + username + "AND motDePasse =" + motDePasse);
         /* Récupération des données du résultat de la requête de lecture */
        /* while (resultatSelResultat.next()) {
            int idUtilisateur = resultatSelResultat.getInt("id");
            BigDecimal j = new BigDecimal(idUtilisateur);
            unMembre.setIdmembre(j);
            unMembre.setCourriel(username);
        }*/

        //Sans hibernate
        DriverManager.registerDriver(
                new oracle.jdbc.driver.OracleDriver());
        Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@//dim-oracle.uqac.ca:1521/dimdb.uqac.ca", "trd157_28", "Z?EqVJ57TZrm");

        Statement stmt = conn.createStatement();
        ResultSet rset = stmt
                .executeQuery("select nomFamille,courriel,idMembre FROM Membre WHERE courriel = '" + username + "' AND motDePasse = '" + motDePasse + "'");
        
        if(rset != null){
        while (rset.next()) {
            i = rset.getInt("idMembre");
            courriel = rset.getString("courriel");
            nomFamille = rset.getString("nomFamille");
            BigDecimal l = new BigDecimal(i);
            unMembre = new Membre();
            unMembre.setIdmembre(l);
            unMembre.setCourriel(courriel);
            unMembre.setNomfamille(nomFamille);
        }
        }else{
            unMembre = null;
        }
        rset.close();
        stmt.close();
        conn.close();

        return unMembre;
    }
}
