package it.uniroma2.isssr.gqm3.model.ontologyPhase2.attribute;



/**
 * Created by MacH2o on 12/07/17.
 */

public class Internal extends Attribute {
    private String inter;

    public String getInter() {
        return inter;
    }

    public void setInter(String inter) {
        this.inter = inter;
    }

    @Override
    public String toString() {
        return super.toString() + ", Internal{" +
                "inter='" + inter + '\'' +
                '}';
    }
}
