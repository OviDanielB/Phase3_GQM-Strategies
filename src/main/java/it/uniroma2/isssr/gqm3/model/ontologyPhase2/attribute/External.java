package it.uniroma2.isssr.gqm3.model.ontologyPhase2.attribute;


/**
 * Created by MacH2o on 12/07/17.
 */

public class External extends Attribute {
    private String from;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return super.toString() +  ", External{" +
                "from='" + from + '\'' +
                '}';
    }
}
