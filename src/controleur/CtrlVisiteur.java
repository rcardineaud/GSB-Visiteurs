package controleur;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modele.dao.*;
import modele.dao.DaoVisiteur;
import modele.metier.Visiteurs;

import vue.VueVisiteur;

/**
 * Contrôleur de la fenêtre VuePresence
 *
 * @author nbourgeois
 * @version 1 20 novembre 2013
 */
public class CtrlVisiteur extends CtrlAbstrait {

    private DaoVisiteur daoVisiteur = new DaoVisiteur();

    public CtrlVisiteur(CtrlPrincipal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new VueVisiteur(this);
        chargerVisiteurJComboBox();

    }

    public void fichierQuitter() {
        // Confirmer avant de quitter
        int rep = JOptionPane.showConfirmDialog(getVue(), "Quitter l'application\nEtes-vous sûr(e) ?", "Ambulances", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (rep == JOptionPane.YES_OPTION) {
            // mettre fin à l'application
            this.getCtrlPrincipal().action(EnumAction.MENU_VISITEUR_QUITTER);
        }
    }

    
       public void visiteurSelectionne() {
       
          // On prend l'item selectionné et on le met dans l'objet unVisiteur
          Visiteurs unVisiteur = (Visiteurs) getVue().getjComboBoxVisiteurs().getSelectedItem();
           
               DefaultTableModel tableau = this.getVue().getModeleJtableVisiteur();
          // DefaultTableModel tableau = (DefaultTableModel) ((VueVisiteur) vue).getTableau().getModel();
        tableau.setRowCount(0);// VIDE LE CONTENU
        
        //tableau.setRowCount(i + 1);//modification des ligne tu tableau en fonction du nombre de lignes à entré  
            Object[] ligne = new Object[5];          
            ligne[0]=unVisiteur.getNom(); //Insertion dans le tableau 
            ligne[1]=unVisiteur.getPrenom();//Insertion dans le tableau
            ligne[2]=unVisiteur.getCp();//Insertion dans le tableau
            ligne[3]=unVisiteur.getVille();//Insertion dans le tableau
            ligne[4]=unVisiteur.getDate_embauche();//Insertion dans le tableau
            tableau.addRow(ligne);
    }

       
    
     public void chargerVisiteurJComboBox() /*throws DaoException*/ {

        List<Visiteurs> lesVisiteurs = null;
        try {
            lesVisiteurs = daoVisiteur.getListeVisiteurs();
        } catch (DaoException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getVue().getModeleJComboBoxVisiteur().removeAllElements();
        
        for (Visiteurs unVisiteur : lesVisiteurs) {
        
            getVue().getModeleJComboBoxVisiteur().addElement(unVisiteur);
            
        }
    }
     
     
     
    public void chargerVisiteur() /*throws DaoException*/ {

        List<Visiteurs> lesVisiteurs = null;
        try {
            lesVisiteurs = daoVisiteur.getListeVisiteurs();
        } catch (DaoException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel tableau = this.getVue().getModeleJtableVisiteur();
        tableau.setRowCount(0);// VIDE LE CONTENU
        for (Visiteurs unVisiteur : lesVisiteurs) {

            
            Object[] ligne = new Object[5];          
            ligne[0]=unVisiteur.getNom(); //Insertion dans le tableau 
            ligne[1]=unVisiteur.getPrenom();//Insertion dans le tableau
            ligne[2]=unVisiteur.getCp();//Insertion dans le tableau
            ligne[3]=unVisiteur.getVille();//Insertion dans le tableau
            ligne[4]=unVisiteur.getDate_embauche();//Insertion dans le tableau
            tableau.addRow(ligne);

        }
    }

    public void chargerUnVisiteur() /*throws DaoException*/ {

        List<Visiteurs> lesVisiteurs = null;
        
        String matricule= null;
        
        try {
            lesVisiteurs = daoVisiteur.getUnVisiteurs(matricule);
        } catch (DaoException ex) {
            Logger.getLogger(CtrlVisiteur.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel tableau = this.getVue().getModeleJtableVisiteur();
//        DefaultTableModel tableau = (DefaultTableModel) ((VueVisiteur) vue).getTableau().getModel();
        tableau.setRowCount(0);// VIDE LE CONTENU
        for (Visiteurs unVisiteur : lesVisiteurs) {

            String matricules = unVisiteur.getMatricule();
            String nom = unVisiteur.getNom();// récupération des donnée a mettre dans le tablau
            String prenom = unVisiteur.getPrenom();// récupération des données a mettre dans le tablau
            int cp = unVisiteur.getCp();// récupération des données a mettre dans le tablau
            String ville = unVisiteur.getVille();// récupération des données a mettre dans le tablau
            String telephone = unVisiteur.getDate_embauche();// récupération des données a mettre dans le tablau



//            tableau.setRowCount(i + 1);//modification des ligne tu tableau en fonction du nombre de lignes à entré  
            Object[] ligne = new Object[5];          
            ligne[0]=nom; //Insertion dans le tableau 
            ligne[1]=prenom;//Insertion dans le tableau
            ligne[2]=cp;//Insertion dans le tableau
            ligne[3]=ville;//Insertion dans le tableau
            ligne[4]=telephone;//Insertion dans le tableau
            tableau.addRow(ligne);

        }
    }

    
    @Override
    public VueVisiteur getVue() {
        return (VueVisiteur) vue;
    }

    public void visiteurAfficher() {
        String msg = "erreur"; // message à afficher en cas d'erreur
        // récupérer les valeurs saisies
        if (getVue() != null) {
        }
    }
}
