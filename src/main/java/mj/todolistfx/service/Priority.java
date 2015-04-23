package mj.todolistfx.service;

/**
 * Created by martynasjankauskas on 11/03/15.
 */
public enum  Priority {

    HIGH ("High"),
    NORMAL ("Normal"),
    LOW ("Low");

    private String value;

    Priority(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
