package bean;

import enumerate.TypeEviction;

/**
 * Created by alarreine on 11/04/2017.
 */
public class StockerInformation {

    private String cle;
    private String information;
    private Integer ttl;
    private TypeEviction typeEviction;

    public StockerInformation(String cle, String information, Integer ttl, TypeEviction typeEviction) {
        this.cle = cle;
        this.information = information;
        this.ttl = ttl;
        this.typeEviction = typeEviction;
    }

    public StockerInformation() {

    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
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
