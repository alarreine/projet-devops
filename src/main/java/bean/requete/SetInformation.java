package bean.requete;

/**
 * Created by alarreine on 11/04/2017.
 */

import enumerate.TypeEviction;

import java.util.ArrayList;
import java.util.List;


public class SetInformation {

    private String key;
    private List<String> info;
    private Integer ttl;
    private TypeEviction typeEviction;

    public SetInformation(String key, List<String> info, Integer ttl, TypeEviction typeEviction) {
        this.key = key;
        this.info = new ArrayList<>();
        this.ttl = ttl;
        this.typeEviction = typeEviction;
    }

    public SetInformation() {

        ttl=100;
        typeEviction = TypeEviction.NOEVICTION;

    }

    public SetInformation(String key, List<String> info) {
        this.key = key;
        this.info = info;
        ttl=100;
        typeEviction = TypeEviction.NOEVICTION;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getListInfo() {
        return info;
    }

    public void setListInfo(List<String> info) {
        this.info = info;
    }

    public void addToListInfo(String info) {
        getListInfo().add(info);
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
