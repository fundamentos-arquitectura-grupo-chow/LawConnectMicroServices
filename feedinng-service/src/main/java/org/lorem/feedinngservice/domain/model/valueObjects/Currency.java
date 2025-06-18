package org.lorem.feedinngservice.domain.model.valueObjects;

public enum Currency {
    PEN(1),
    USD(2);

    private final int id;

    Currency(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Currency fromId(int id) {
        for (Currency type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid currency id: " + id);
    }
}
