package com.example.mars_rover;

import java.util.Objects;

public class Area {
    private int wide;
    private int length;

    public Area(int wide, int length) {

        this.wide = wide;
        this.length = length;
    }

    public int getWide() {
        return wide;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return wide == area.wide &&
                length == area.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wide, length);
    }
}
