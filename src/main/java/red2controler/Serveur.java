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
    private HashMap<String, String> bdMotDePasse;

    public Serveur() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        clientsConnectes = new HashMap<String, HashMap<String, String>>();
        bdMotDePasse = new HashMap<String, String>();
        initialiserBDMotDePasse();
    }

    public void initialiserBDMotDePasse(){
        // Les adresses IP seront fournies par Agustin
        bdMotDePasse.put("127.0.1.0","1234");
        bdMotDePasse.put("127.0.0.0","AZERTY");
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
    public String auth(Client client, String password){
        String resultat = "";
        if (bdMotDePasse.containsKey(client.getIp())){
            if ((bdMotDePasse.get(client.getIp())).equals(password)){
                resultat = "AUTHENTIFICATION REUSSIE";
                if (!clientsConnectes.containsKey(client.getIp())){
                    HashMap<String, String> hashClient = new HashMap<>();
                    clientsConnectes.put(client.getIp(), hashClient);
                }
                else{
                    resultat = "MAUVAIS MOT DE PASSE";
                }
            }
        }
        else{
            resultat = "VOUS N'AVEZ PAS DE COMPTE, VEUILLEZ EN CREER UN";
        }
        return resultat;
    }

    /*
     * Permet de renvoyer l'information associee à la cle donnee en argument si elle existe
     * @param client qui demande la valeur d'une cle
     * @param cle : cle pour obtenir l'information associee
     * @return "VEUILLEZ VOUS AUTHENTIFIER D'ABORD" si le client n'est pas authentifie
     * l'information associe à la cle si elle existe, "AUCUNE INFO POUR CETTE CLE" sinon
     */
    public String demanderInformation(Client client, String cle){
        String result = "";
        if (clientsConnectes.containsKey(client.getIp())){
            HashMap<String, String> hashClient = clientsConnectes.get(client.getIp());
            if (hashClient.containsKey(cle)){
                result = hashClient.get(cle);
            }
            else{
                result = "AUCUNE INFO POUR CETTE CLE";
            }
        }
        else{
            result = "VEUILLEZ VOUS AUTHENTIFIER D'ABORD";
        }
        return result;
    }

    /*
     * @param client qui demande à effacer une information
     * @param cle de la donnée à effacer
     * @return "OK" si donnée effacée, "AUCUNE INFO POUR CETTE CLE" sinon
     */
    public String effacerInformation(Client client, String cle){
        String resultat = "";
        if (clientsConnectes.containsKey(client.getIp())){
            HashMap<String, String> hashClient = clientsConnectes.get(client.getIp());
            hashClient.remove(cle);
        }
        else{
            resultat = "VEUILLEZ VOUS AUTHENTIFIER D'ABORD";
        }
        return resultat;
    }

    /*
     * @param client client qui demande l'existence de la cle
     * @param cle cle dont on teste l'existence
     * @return true si la cle exist dans le hash du client courant, false sinon ou si le client n'est pas authentifie
     */
    public boolean exists(Client client, String cle){
        boolean result = false;
        if (clientsConnectes.containsKey(client.getIp())){
            result = (clientsConnectes.get(client.getIp())).containsKey(cle);
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
    public String incrementerInformation(Client client, String cle){
        String result = "";
        if (clientsConnectes.containsKey(client.getIp())){
            HashMap<String, String> hashClient = clientsConnectes.get(client.getIp());
            if (hashClient.containsKey(cle)){
                try{
                    int oldValue = Integer.parseInt(hashClient.get(cle));
                    hashClient.put(cle, String.valueOf(oldValue++));
                    result = cle + " " + hashClient.get(cle);
                }
                catch (NumberFormatException e){
                    result = "IMPOSSIBLE D'INCREMENTER";
                }
            }
            else{
                result = "AUCUNE INFO POUR CETTE CLE";
            }
        }
        else{
            result = "VEUILLEZ VOUS AUTHENTIFIER D'ABORD";
        }
        return result;
    }


    /*
     * Efface le hash de l'utilisateur qui veut quitter
     * @param client qui veut quitter
     * @return "VEUILLEZ VOUS AUTHENTIFIER D'ABORD" si client non authentifié
     * "CACHE NETTOYE, AU REVOIR" sinon
     */
    public String quitter(Client client){
        String result = "";
        if (clientsConnectes.containsKey(client.getIp())){
            clientsConnectes.remove(client.getIp());
            result = "CACHE NETTOYE, AU REVOIR";
        }
        else{
            result = "VEUILLEZ VOUS AUTHENTIFIER D'ABORD";
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
     */
    public String renomeCle(Client client, String cleAvant, String cleApres){
        String result = "";
        if (clientsConnectes.containsKey(client.getIp())){
            HashMap<String, String> hashClient = clientsConnectes.get(client.getIp());
            if (hashClient.containsKey(cleAvant)){
                hashClient.put(cleApres, hashClient.get(cleAvant));
                hashClient.remove(cleAvant);
                result = cleApres + " " + hashClient.get(cleApres);
            }
            else{
                result = "AUCUNE INFO POUR CETTE CLE";
            }
        }
        else{
            result = "VEUILLEZ VOUS AUTHENTIFIER D'ABORD";
        }
        return result;
    }

    /*
     * Ajoute la cle associée à l'info précisée en argument si la cle n'existait pas encore
     * modifie l'information à cette cle sinon
     * @param client celui qui set une information
     * @param cle de l'information
     * @param info a mettre a la cle indiquée
     * @return "VEUILLEZ VOUS AUTHENTIFIER D'ABORD" si client non-authentifié
     * "<CLE> <INFO>" si set réussi
     */
    public String setInformation(Client client, String cle, String info){
        String result = "";
        if (clientsConnectes.containsKey(client.getIp())){
            HashMap<String, String> hashClient = clientsConnectes.get(client.getIp());
            hashClient.put(cle, info);
            result = cle + " " + hashClient.get(cle);
        }
        else{
            result = "VEUILLEZ VOUS AUTHENTIFIER D'ABORD";
        }
        return result;
    }

}
