package com.thermomix.server.models.enums;

public enum DishDificulty {
    EASY("Łatwe"), MEDIUM("Średnie"), HARD("Trudne");

    private final String name;

    DishDificulty(String s) { name = s; }

    @Override
    public String toString() { return this.name; }
}
