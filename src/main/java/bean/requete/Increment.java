package bean.requete;

/**
 * Created by alarreine on 12/04/2017.
 */
public class Increment {

    private String key;
    private Integer number;

    public Increment(String key, Integer number) {
        this.key = key;
        this.number = number;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
