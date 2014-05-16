package modele.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import modele.jdbc.Jdbc;
import modele.metier.Visiteurs;
import modele.dao.*;
import modele.metier.ListeVisiteurs;
import modele.metier.Visiteurs;


//La classe DaoVisiteur :
//On crée une liste visiteurs
//Ensuite on éxecute la requete 
//Puis on met les résultats de la requête dans la liste

public class DaoVisiteur {
        

        public ArrayList<Visiteurs> getUnVisiteurs(String matricule) throws DaoException {
        ArrayList<Visiteurs> result = new ArrayList<Visiteurs>();

        String requete = "SELECT * FROM VISITEUR WHERE VIS_MATRICULE = ?";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setString(1, matricule);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Visiteurs visiteur = chargerUnVisiteur(rs);
                result.add(visiteur);
            }

        } catch (Exception ex) {
            throw new DaoException("DAO - getListeVisiteurs pour un visiteur : pb JDBC\n" + ex.getMessage());
        }
        return result;
        }
        
        
        public ArrayList<Visiteurs> getListeVisiteurs() throws DaoException {
        ArrayList<Visiteurs> result = new ArrayList<Visiteurs>();

        String requete = "SELECT * FROM VISITEUR";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Visiteurs visiteur = chargerUnVisiteur(rs);
                result.add(visiteur);
            }

        } catch (Exception ex) {
            throw new DaoException("DAO - getListeVisiteurs pour un visiteur : pb JDBC\n" + ex.getMessage());
        }
        return result;
        }
        
     
        
        private Visiteurs chargerUnVisiteur(ResultSet rs) throws DaoException {
        try {
            
            String matricule = rs.getString("VIS_MATRICULE");
            String nom = rs.getString("VIS_NOM");
            String prenom = rs.getString("Vis_PRENOM");
            String adresse = rs.getString("VIS_ADRESSE");
            int cp = rs.getInt("VIS_CP");
            String ville = rs.getString("VIS_VILLE");
            String date_embauche = rs.getString("VIS_DATEEMBAUCHE");
            
            Visiteurs visiteur = new Visiteurs(matricule,nom,prenom,cp,ville,date_embauche);

            return visiteur;
            
        } catch (SQLException ex) {
            throw new DaoException("DaoPresence - chargerUnEnregistrement : pb JDBC\n" + ex.getMessage());
        }

    }
    
    
}
