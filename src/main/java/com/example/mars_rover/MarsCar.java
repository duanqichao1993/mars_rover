package com.example.mars_rover;


import com.example.mars_rover.operation.*;

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
        switch (this.direction) {
            case EAST:
                this.direction = NORTH;
                break;
            case SOUTH:
                this.direction = EAST;
                break;
            case WEST:
                this.direction = SOUTH;
                break;
            case NORTH:
                this.direction = WEST;
                break;
            default:
        }

    }


    public void turnRight() {

        switch (this.direction) {
            case EAST:
                this.direction = SOUTH;
                break;
            case SOUTH:
                this.direction = WEST;
                break;
            case WEST:
                this.direction = NORTH;
                break;
            case NORTH:
                this.direction = EAST;
                break;
            default:

        }
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

    private void executeOperation(Operation commandOperation) {
        int offSet = commandOperation.getOffSet();
        if (commandOperation instanceof Forward) {
            this.moveForward(offSet);
        } else {
            if (commandOperation instanceof BackOperation) {
                this.moveBack(offSet);
            }
            if (commandOperation instanceof LeftOperation) {
                this.turnLeft();
            }
            if(commandOperation instanceof RightOperation) {
                this.turnRight();
            }
        }
    }
}
