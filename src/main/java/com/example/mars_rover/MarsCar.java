package com.example.mars_rover;


import com.example.mars_rover.operation.BackOperation;
import com.example.mars_rover.operation.Forward;
import com.example.mars_rover.operation.LeftOperation;
import com.example.mars_rover.operation.Operation;

import java.util.List;

import static com.example.mars_rover.Direction.*;

public class MarsCar {

    private Point point;
    private Direction direction;
    private Command command;
    private Area area;
    private Operation operation;

    public void land(Point point, Direction direction) {

        this.point = point;
        this.direction = direction;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }

    public void moveForward(int forward) {
        this.point.setY(forward);
    }

    public void moveBack(int back) {
        this.point.setY((back * -1));
    }


    public void turnLeft() {
        directionTurn(NORTH, EAST, SOUTH, WEST);

    }

    private void directionTurn(Direction north, Direction east, Direction south, Direction west) {
        switch (this.direction) {
            case EAST:
                this.direction = north;
                break;
            case SOUTH:
                this.direction = east;
                break;
            case WEST:
                this.direction = south;
                break;
            case NORTH:
                this.direction = west;
                break;
            default:

        }
    }

    public void turnRight() {

        directionTurn(SOUTH, WEST, NORTH, EAST);

    }

    public void receivedCommandAndInit(Command command) {
        this.command = command;
        this.area = command.getArea();
        this.point = command.getPoint();
        this.direction = command.getDirection();

    }

    public Area getArea() {
        area = command.getArea();

        return this.area;

    }

    public List<Operation> getOperations() {
        return this.command.getOperations();
    }

    public void work() {
        printOrderInit();
        List<Operation> operations = command.getOperations();
        operations.forEach(this::executeOperation);
        reportMoveResult();

    }

    private void printOrderInit() {
        System.out.println("work area: " + this.area.getWide() + "  y:" + this.area.getLength());
        System.out.println("current location " + this.point + ",Direction " + this.direction.toString());
        System.out.println("start move");
    }

    private void reportMoveResult() {
        System.out.println("command order execute success: " + this.point + ",Direction " + this.direction.toString());

    }

    private void executeOperation(Operation operation1) {
        int offSet = operation1.getOffSet();
        if (operation1 instanceof Forward) {
            this.moveForward(offSet);
        } else {
            if (operation1 instanceof BackOperation) {
                this.moveBack(offSet);
            }
            if (operation1 instanceof LeftOperation) {
                this.turnLeft();
            } else {
                this.turnRight();
            }
        }
    }
}
