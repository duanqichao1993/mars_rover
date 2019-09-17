package com.example.mars_rover;

import com.example.mars_rover.operation.Operation;

import java.util.List;

public class Command {
    private Area area;
    private Point point;
    private Direction direction;
    private List<Operation> operations;


    public Area getArea() {
        return area;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }

    public List<Operation> getOperations() {
        return operations;
    }



    public void send(Area area, Point point, Direction east, List<Operation> operations) {
        this.area = area;
        this.point = point;
        this.direction = east;
        this.operations = operations;
    }
}
