package com.seveneleven.mycontactsapp.model;

import java.util.Objects;

public class Tag {
    private final String name;

    private Tag(String name) {
        this.name = name.toLowerCase();
    }

    public static Tag of(String name) {
        return new Tag(name.trim());
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "#" + name;
    }
}
