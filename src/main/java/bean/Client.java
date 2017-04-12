package bean;

/**
 * Created by alarreine on 12/04/2017.
 */
public class Client {

    private String nome;
    private String ip;

    public Client(String nome, String ip) {
        this.nome = nome;
        this.ip = ip;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
