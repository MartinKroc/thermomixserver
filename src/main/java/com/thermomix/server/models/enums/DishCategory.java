package com.thermomix.server.models.enums;

public enum DishCategory {
    HIGH("Wysoko kaloryczne"), LOW("Nisko kaloryczne"), SOUP("Zupy"), MEAT("Mięsne"), WEGE("Wegetariańskie");

    private final String name;

    DishCategory(String s) { name = s; }

    @Override
    public String toString() { return this.name; }
}
