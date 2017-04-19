package red2controler;

import application.Application;
import bean.Client;
import bean.reponse.Information;
import bean.requete.Auth;
import com.google.gson.Gson;
import controller.exception.BadAuthenticationException;
import controller.exception.IncreaseKeyException;
import controller.exception.KeyNotFoundException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Serveur {
    /*
     * le serveur a une liste de HashMap par client
     * Chaque client a un HashMap où à une cle on associe une information
     */
    private HashMap<String, HashMap<String, String>> clientsConnectes;
    private HashMap<String, String> bdMotDePasse;

    public Serveur() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        clientsConnectes = new HashMap<String, HashMap<String, String>>();
        bdMotDePasse = new HashMap<String, String>();
    }


    /************************** Interface du serveur ********************************/
    /*
     * Authentifie le client courant
     * (côté serveur ça crée le hash de ce client)
     * @param client celui qui s'authentifie
     * @param password le mot de passe du client
     * @return "VOUS N'AVEZ PAS DE COMPTE, VEUILLEZ EN CREER UN" si le client n'a pas de compte
     * AUTHENTIFICATION REUSSIE" si c'est le bon mdp, "MAUVAIS MOT DE PASSE" sinon
     */
    public boolean auth(Client client, Auth auth) {
        if (Application.getPassword().compareTo(auth.getPassword()) == 0) {
            String pass = bdMotDePasse.get(client.clientToHash());
            //Si est null, ça veut dire que il est un nouveau client. On crée une bd
            if (pass == null) {
                bdMotDePasse.put(client.clientToHash(), client.getNom());
                HashMap<String, String> hashClient = new HashMap<>();
                clientsConnectes.put(client.clientToHash(), hashClient);
            }

        } else {
            throw new BadAuthenticationException();
        }

        return true;
    }

    /*
     * Permet de renvoyer l'information associee à la cle donnee en argument si elle existe
     * @param client qui demande la valeur d'une cle
     * @param cle : cle pour obtenir l'information associee
     * @return "VEUILLEZ VOUS AUTHENTIFIER D'ABORD" si le client n'est pas authentifie
     * l'information associe à la cle si elle existe, "AUCUNE INFO POUR CETTE CLE" sinon
     */
    public String demanderInformation(Client client, String cle) {
        String result = "";
        HashMap<String, String> hashClient = clientsConnectes.get(client.clientToHash());
        if (hashClient != null) {
            if (hashClient.containsKey(cle)) {
                result = hashClient.get(cle);
            } else {
                throw new KeyNotFoundException(cle);
            }
        } else {
            throw new BadAuthenticationException("VEUILLEZ VOUS AUTHENTIFIER D'ABORD");

        }
        return result;
    }

    /*
     * @param client qui demande à effacer une information
     * @param cle de la donnée à effacer
     * @return "OK" si donnée effacée, "AUCUNE INFO POUR CETTE CLE" sinon
     */
    public boolean effacerInformation(Client client, String cle) {
        String resultat = "";
        HashMap<String, String> hashClient = clientsConnectes.get(client.clientToHash());
        if (hashClient != null) {
            hashClient.remove(cle);
        } else {
            throw new BadAuthenticationException("VEUILLEZ VOUS AUTHENTIFIER D'ABORD");
        }
        return true;
    }

    /*
     * @param client client qui demande l'existence de la cle
     * @param cle cle dont on teste l'existence
     * @return true si la cle exist dans le hash du client courant, false sinon ou si le client n'est pas authentifie
     */
    public boolean exists(Client client, String cle) {
        boolean result = false;
        HashMap<String, String> hashClient = clientsConnectes.get(client.clientToHash());
        if (hashClient != null) {
            if ((clientsConnectes.get(client.clientToHash())).containsKey(cle)) {
                result = true;
            } else {
                throw new KeyNotFoundException(cle);
            }


        }
        return result;
    }

    /*
     * Incremente la valeur à la cle
     * @param client qui demande l'incrementation
     * @param cle de l'information à incrémenter
     * @return "VEUILLEZ VOUS AUTHENTIFIER D'ABORD" si le client n'est pas authentifie
     * "AUCUNE INFO POUR CETTE CLE" s'il n'y a pas d'information associee à la cle
     * "IMPOSSIBLE D'INCREMENTER" si la valeur à cette clé n'est pas un entier
     * <cle> <nouvelle valeur> si information incrémentée
     */
    public String incrementerInformation(Client client, String cle) {
        String result;
        if (clientsConnectes.containsKey(client.clientToHash())) {
            HashMap<String, String> hashClient = clientsConnectes.get(client.clientToHash());
            if (hashClient.containsKey(cle)) {
                try {
                    Gson gson = new Gson();
                    Information info = gson.fromJson(hashClient.get(cle), Information.class);
                    int oldValue = Integer.parseInt(info.getInfo().get(0));
                    oldValue++;
                    info.getInfo().set(0, String.valueOf(oldValue));
                    hashClient.put(cle, gson.toJson(info));
                    result = gson.toJson(info);
                } catch (NumberFormatException e) {
                    throw new IncreaseKeyException("IMPOSSIBLE D'INCREMENTER");
                }
            } else {
                throw new KeyNotFoundException("AUCUNE INFO POUR CETTE CLE");
            }
        } else {
            throw new BadAuthenticationException("VEUILLEZ VOUS AUTHENTIFIER D'ABORD");
        }
        return result;
    }


    /*
     * Efface le hash de l'utilisateur qui veut quitter
     * @param client qui veut quitter
     * @return "VEUILLEZ VOUS AUTHENTIFIER D'ABORD" si client non authentifié
     * "CACHE NETTOYE, AU REVOIR" sinon
     */
    public String quitter(Client client) {
        String result = "";
        if (clientsConnectes.containsKey(client.clientToHash())) {
            clientsConnectes.remove(client.clientToHash());
            result = "CACHE NETTOYE, AU REVOIR";
        } else {
            throw new BadAuthenticationException("VEUILLEZ VOUS AUTHENTIFIER D'ABORD");
        }
        return result;
    }

    /*
     * Renommer la cle donnee en argument si elle existe dans le hash du client
     * Ceci implique qu'à la nouvelle clé sera associée l'info de l'ancienne cle
     * Si le hash du client contennait déjà la nouvelle cle, son info sera changé par l'info de l'ancienne cle
     * et l'ancienne cle et son information associée seront supprimees
     * @param client qui modifie son hash
     * @param cleavant la cle à renommer
     * @param cleapres la nouvelle cle
     * @return "VEUILLEZ VOUS AUTHENTIFIER D'ABORD" si cleint non authentifié
     * "AUCUNE INFO POUR CETTE CLE" si la cle à renommer n'est pas dans la table
     * <nouvelle cle> <information> si renommage réussie
     * @return true si l'operation a été fait
     * @exception BadAuthenticationException
     */
    public boolean renomeCle(Client client, String cleAvant, String cleApres) {

        if (clientsConnectes.containsKey(client.clientToHash())) {
            HashMap<String, String> hashClient = clientsConnectes.get(client.clientToHash());
            if (hashClient.containsKey(cleAvant)) {
                hashClient.put(cleApres, hashClient.get(cleAvant));
                hashClient.remove(cleAvant);
                return true;
            } else {
                throw new KeyNotFoundException("AUCUNE INFO POUR CETTE CLE");
            }
        } else {
            throw new BadAuthenticationException("VEUILLEZ VOUS AUTHENTIFIER D'ABORD");
        }

    }

    /*
     * Ajoute la cle associée à l'info précisée en argument si la cle n'existait pas encore
     * modifie l'information à cette cle sinon
     * @param client celui qui set une information
     * @param cle de l'information
     * @param info a mettre a la cle indiquée
     * @return true si l'operation a été fait
     * @exception BadAuthenticationException
     * "<CLE> <INFO>" si set réussi
     */
    public boolean setInformation(Client client, String cle, String info) {
        boolean result = false;
        HashMap<String, String> hashClient = clientsConnectes.get(client.clientToHash());
        if (hashClient != null) {
            hashClient.put(cle, info);
            result = true;
        } else {

            throw new BadAuthenticationException("VEUILLEZ VOUS AUTHENTIFIER D'ABORD");
        }
        return result;
    }


}
