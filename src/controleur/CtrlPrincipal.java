package controleur;

import static controleur.EnumAction.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modele.dao.DaoException;
import modele.jdbc.Jdbc;

/**
 * Controleur principal (ou frontal) - un lien vers chaque contrôleur de base
 *
 * @author nbourgeois
 * @version 1 20 novembre 2013
 */
public class CtrlPrincipal {

    private CtrlVisiteur ctrlVisiteur = null;
    private CtrlMenu ctrlMenu = null;

    /**
     * action par défaut action au démarrage de l'application
     */
    public void action() {
        if (ctrlMenu == null) {
            ctrlMenu = new CtrlMenu(this);
        }
        ctrlMenu.getVue().setEnabled(true);
        ctrlMenu.getVue().setVisible(true);
    }

    public void action(EnumAction action) {
        switch (action) {
            case MENU_VISITEUR_AFFICHER: // activation de vuePresence depuis vueMenu
                menuVisiteurAfficher();
                break;
            case VISITEUR_QUITTER:    // retour à vueMenu depuis la vuePresence
                presenceQuitter();
                break;
            case MENU_VISITEUR_QUITTER: // fin de l'application depuis vueMenu
                menuFichierQuitter();
                break;
        }

    }

    /**
     * Fin définitive de l'application La demande de confirmation est gérée par
     * le contrôleur spécialisé
     */
    private void menuFichierQuitter() {
        try {
            Jdbc.getInstance().deconnecter();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "CtrlPrincipal - fermeture connexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            System.exit(0);
        }
    }

    /**
     * Transition vueMenu / vuePresence
     */
    private void menuVisiteurAfficher() {
        if (ctrlVisiteur == null) {
            ctrlVisiteur = new CtrlVisiteur(this);
        } 
        // vuPresence est une fenêtre modale :
        // -> vueMenu reste visible, mais n'est pas active
        ctrlMenu.getVue().setEnabled(false);
//        try {
            ctrlVisiteur.chargerVisiteur();
//        } catch (DaoException ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage(), "CtrlPrincipal - echec de la liste des visiteurs", JOptionPane.ERROR_MESSAGE);
//        }
        ctrlVisiteur.getVue().setVisible(true);
    }

    /**
     * Transition vuePresence / vueMenu
     */
    private void presenceQuitter() {
        if (ctrlMenu == null) {
            ctrlMenu = new CtrlMenu(this);
        }
        ctrlVisiteur.getVue().setVisible(false);
        ctrlMenu.getVue().setEnabled(true);
        ctrlMenu.getVue().setVisible(true);
    }
    
 
}
