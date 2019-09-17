package com.example.mars_rover.operation;

import java.util.Objects;

public class Operation {


    private int offSet;

    public Operation() {
    }

    public Operation(int offSet) {
        this.offSet = offSet;
    }




    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return offSet == operation.offSet;
    }

    @Override
    public int hashCode() {
        return Objects.hash(offSet);
    }
}
