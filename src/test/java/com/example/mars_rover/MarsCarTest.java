package com.example.mars_rover;

import com.example.mars_rover.operation.BackOperation;
import com.example.mars_rover.operation.Forward;
import com.example.mars_rover.operation.LeftOperation;
import com.example.mars_rover.operation.Operation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.mars_rover.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MarsCarTest {

    private MarsCar marsCar;

    @Before
    public void marsCarLand() {
        marsCar = new MarsCar();
        marsCar.land(new Point(100, 200), NORTH);
    }


    @Test
    public void should_show_area_info_info_when_landing() {
        assertThat(marsCar.getPoint()).isEqualTo(new Point(100, 200));
        assertThat(marsCar.getDirection()).isEqualTo(NORTH);
    }


    @Test
    public void should_move_forward() {
        marsCar.moveForward(50);
        assertThat(marsCar.getPoint().getX()).isEqualTo(100);
        assertThat(marsCar.getPoint().getY()).isEqualTo(250);
    }

    @Test
    public void should_move_back() {
        marsCar.moveBack(50);
        assertThat(marsCar.getPoint().getX()).isEqualTo(100);
        assertThat(marsCar.getPoint().getY()).isEqualTo(150);
    }


    @Test
    public void should_turn_left() {
        marsCar.turnLeft();
        assertThat(marsCar.getPoint().getX()).isEqualTo(100);
        assertThat(marsCar.getPoint().getY()).isEqualTo(200);
        assertThat(marsCar.getDirection()).isEqualTo(WEST);
    }

    @Test
    public void should_turn_right() {
        marsCar.turnRight();
        assertThat(marsCar.getPoint().getX()).isEqualTo(100);
        assertThat(marsCar.getPoint().getY()).isEqualTo(200);
        assertThat(marsCar.getDirection()).isEqualTo(EAST);
    }

    @Test
    public void should_show_north_if_land_direction_is_east_when_turn_left() {
        marsCar = new MarsCar();
        marsCar.land(new Point(100, 200), EAST);
        marsCar.turnLeft();
        assertThat(marsCar.getDirection()).isEqualTo(NORTH);
    }

    @Test
    public void should_show_east_if_land_direction_is_south_when_turn_left() {
        marsCar = new MarsCar();
        marsCar.land(new Point(100, 200), SOUTH);
        marsCar.turnLeft();
        assertThat(marsCar.getDirection()).isEqualTo(EAST);
    }

    @Test
    public void should_show_south_if_land_direction_is_east_when_turn_left() {
        marsCar = new MarsCar();
        marsCar.land(new Point(100, 200), WEST);
        marsCar.turnLeft();
        assertThat(marsCar.getDirection()).isEqualTo(SOUTH);
    }

    @Test
    public void should_show_north_if_land_direction_is_east_when_turn_right() {
        marsCar = new MarsCar();
        marsCar.land(new Point(100, 200), EAST);
        marsCar.turnRight();
        assertThat(marsCar.getDirection()).isEqualTo(SOUTH);
    }

    @Test
    public void should_show__if_land_direction_is_south_when_turn_right() {
        marsCar = new MarsCar();
        marsCar.land(new Point(100, 200), SOUTH);
        marsCar.turnRight();
        assertThat(marsCar.getDirection()).isEqualTo(WEST);
    }

    @Test
    public void should_show_west_if_land_direction_is_south_when_turn_right() {
        marsCar = new MarsCar();
        marsCar.land(new Point(100, 200), SOUTH);
        marsCar.turnRight();
        assertThat(marsCar.getDirection()).isEqualTo(WEST);
    }

    @Test
    public void should_show_north_if_land_direction_is_west_when_turn_right() {
        marsCar = new MarsCar();
        marsCar.land(new Point(100, 200), WEST);
        marsCar.turnRight();
        assertThat(marsCar.getDirection()).isEqualTo(NORTH);
    }

    @Test
    public void should_show_command_when_received_command() {
        Command command = new Command();
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation( 15));
        operations.add(new Operation( 15));
        command.send(new Area(1000, 2000), new Point(1100, 200), EAST, operations);
        marsCar.receivedCommandAndInit(command);

        assertThat(marsCar.getPoint()).isEqualTo(new Point(1100,200));
        assertThat(marsCar.getDirection()).isEqualTo(EAST);
        assertThat(marsCar.getArea()).isEqualTo(new Area(1000, 2000));
        assertThat(marsCar.getOperations().size()).isEqualTo(2);
    }


    @Test
    public void should_move_after_received_command(){
        Command command = new Command();
        List<Operation> operations = new ArrayList<>();
        operations.add(new Forward( 15));
        operations.add(new LeftOperation());
        operations.add(new BackOperation(50));
        command.send(new Area(1000, 2000), new Point(1100, 200), EAST, operations);
        marsCar.receivedCommandAndInit(command);

        marsCar.work();

        assertThat(marsCar.getPoint()).isEqualTo(new Point(1100,165));
        assertThat(marsCar.getDirection()).isEqualTo(NORTH);

    }









}
