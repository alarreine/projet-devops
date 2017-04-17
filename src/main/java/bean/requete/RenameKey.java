package bean.requete;

/**
 * Created by alarreine on 12/04/2017.
 */
public class RenameKey {

    private String key;
    private String newKey;

    public RenameKey(String key, String newKey) {

        this.key = key;
        this.newKey = newKey;
    }

    public RenameKey() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNewKey() {
        return newKey;
    }

    public void setNewKey(String newKey) {
        this.newKey = newKey;
    }

    @Override
    public String toString() {
        return "RenameKey{" +
                "key='" + key + '\'' +
                ", newKey='" + newKey + '\'' +
                '}';
    }
}
