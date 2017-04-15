package red2controler;

import bean.Client;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Serveur {
    /*
     * le serveur a une liste de HashMap par client
     * Chaque client a un HashMap où à une cle on associe une information
     */
    private HashMap<String, HashMap<String, String>> clientsConnectes;

    public Serveur() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        clientsConnectes = new HashMap<String, HashMap<String, String>>();
    }
    /************************** Interface du serveur ********************************/
    /*
     * Authentifie le client courant
     * (côté serveur ça crée le hash de ce client)
     * @param client celui qui s'authentifie
     * @param password le mot de passe du client
     * @return "OK" si c'est bon, "AUTHENTIFICATION IMPOSSIBLE" sinon
     */
    public String auth(Client client, String password){
        return "";
    }

    /*
     * @param client qui demande la valeur d'une cle
     * @param cle : cle pour obtenir l'information associee
     * @return l'information associe à la cle si elle existe, "AUCUNE INFO POUR CETTE CLE" sinon
     */
    public String demanderInformation(Client client, String cle){
        return "";
    }

    /*
     * @param client qui demande à effacer une information
     * @param cle de la donnée à effacer
     * @return "OK" si donnée effacée, "AUCUNE INFO POUR CETTE CLE" sinon
     */
    public String effacerInformation(Client client, String cle){
        return "";
    }

    /*
     * @param client qui demande l'existence de la cle cle
     * @param cle dont on teste l'existence
     * @return true si la cle exist dans le hash du client courant, false sinon
     */
    public boolean exists(Client client, String cle){
        return false;
    }

    /*
     * Incremente la valeur à la cle
     * @param client qui demande l'incrementation
     * @param cle de l'information à incrémenter
     * @return l'information incrémentée si c'était un entier, renvoie "INCREMENTATION IMPOSSIBLE" sinon
     */
    public String incrementerInformation(Client client, String cle){
        return "";
    }


    /*
     * Efface le hash de l'utilisateur qui veut quitter
     * @param client qui veut quitter
     * @return "AU REVOIR" et le client n'a plus de hash, "IMPOSSIBLE DE QUITTER" sinon
     */
    public String quitter(Client client){
        return "";
    }

    /*
     * Renommer la cle donnee en argument si elle existe dans le hash du client
     * @param client qui modifie son hash
     * @param cleavant la cle à renommer
     * @param cleapres la nouvelle cle
     * @return "<NOUVELLE CLE> <INFO>" si c'est bon, "IMPOSSIBLE DE RENOMMER" si cle inexistante
     */
    public String renomeCle(Client client, String cleAvant, String cleApres){
        return "";
    }

    /*
     * Ajoute la cle associée à l'info précisée en argument si la cle n'existait pas encore
     * modifie l'information à cette cle sinon
     * @param client celui qui set une information
     * @param cle de la nouvelle information
     * @param info a mettre a la cle indiquée
     * @return "<CLE> <INFO>"
     */
    public String setInformation(Client client, String cle, String info){
        return "";
    }

}
