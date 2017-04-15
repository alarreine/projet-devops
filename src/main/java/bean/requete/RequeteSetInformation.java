package bean.requete;

/**
 * Created by alarreine on 11/04/2017.
 */

import enumerate.TypeEviction;


public class RequeteSetInformation {

    private String key;
    private String info;
    private Integer ttl;
    private TypeEviction typeEviction;

    public RequeteSetInformation(String key, String info, Integer ttl, TypeEviction typeEviction) {

        this.key = key;
        this.info = info;
        this.ttl = ttl;
        this.typeEviction = typeEviction;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public TypeEviction getTypeEviction() {
        return typeEviction;
    }

    public void setTypeEviction(TypeEviction typeEviction) {
        this.typeEviction = typeEviction;
    }


}
