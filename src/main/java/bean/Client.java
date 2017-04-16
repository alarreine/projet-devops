package bean;

/**
 * Created by alarreine on 12/04/2017.
 */
public class Client {
    private String nom;
    private String ip;

    public Client(String nome, String ip) {
        this.nom = nome;
        this.ip = ip;
    }

    public Client() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String clientToHash(){

        return getNom()+"-"+getIp();
    }
}
