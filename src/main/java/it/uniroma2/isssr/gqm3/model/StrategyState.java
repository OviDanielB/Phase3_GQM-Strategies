package it.uniroma2.isssr.gqm3.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * @author emanuele
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StrategyState implements Serializable {
    MODIFIED(0), VIEWED(1), NOT_MODIFIED(2);

    private int value;

    private StrategyState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @JsonCreator
    public static StrategyState create(int value) {
        for (StrategyState v : values()) {
            if (value == v.getValue()) {
                return v;
            }
        }
        return null;
    }
}
