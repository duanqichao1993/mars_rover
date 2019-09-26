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
        initMarsCarWithPointAndDirection(NORTH);
    }

    /**
     *    落地返回信息
     */
    @Test
    public void should_show_area_info_info_when_landing() {
        assertThat(marsCar.getPoint()).isEqualTo(new Point(100, 200));
        assertThat(marsCar.getDirection()).isEqualTo(NORTH);
    }


    /**
     * 前进
     */
    @Test
    public void should_move_forward() {
        marsCar.moveForward(50);
        assertThat(marsCar.getPoint().getX()).isEqualTo(100);
        assertThat(marsCar.getPoint().getY()).isEqualTo(250);
    }


    /**
     * 后退
     */
    @Test
    public void should_move_back() {
        marsCar.moveBack(50);
        assertThat(marsCar.getPoint().getX()).isEqualTo(100);
        assertThat(marsCar.getPoint().getY()).isEqualTo(150);
    }

    /**
     * 左转
     */
    @Test
    public void should_turn_left() {
        marsCar.turnLeft();
        assertThat(marsCar.getPoint().getX()).isEqualTo(100);
        assertThat(marsCar.getPoint().getY()).isEqualTo(200);
        assertThat(marsCar.getDirection()).isEqualTo(WEST);
    }

    /**
     * 右转
     */
    @Test
    public void should_turn_right() {
        marsCar.turnRight();
        assertThat(marsCar.getPoint().getX()).isEqualTo(100);
        assertThat(marsCar.getPoint().getY()).isEqualTo(200);
        assertThat(marsCar.getDirection()).isEqualTo(EAST);
    }

    /**
     *  初始位置 东 ，左转
     */
    @Test
    public void should_show_north_if_land_direction_is_east_when_turn_left() {
        initMarsCarWithPointAndDirection(EAST);
        marsCar.turnLeft();
        assertThat(marsCar.getDirection()).isEqualTo(NORTH);
    }

    /**
     *  初始位置 南 ，左转
     */
    @Test
    public void should_show_east_if_land_direction_is_south_when_turn_left() {
        initMarsCarWithPointAndDirection(SOUTH);
        marsCar.turnLeft();
        assertThat(marsCar.getDirection()).isEqualTo(EAST);
    }


    /**
     *  初始位置 西，左转
     */
    @Test
    public void should_show_south_if_land_direction_is_east_when_turn_left() {
        initMarsCarWithPointAndDirection(WEST);
        marsCar.turnLeft();
        assertThat(marsCar.getDirection()).isEqualTo(SOUTH);
    }

    /**
     *  初始位置 东 ，右转
     */
    @Test
    public void should_show_north_if_land_direction_is_east_when_turn_right() {
        initMarsCarWithPointAndDirection(EAST);
        marsCar.turnRight();
        assertThat(marsCar.getDirection()).isEqualTo(SOUTH);
    }

    /**
     *  初始位置南 ，右转
     */
    @Test
    public void should_show_west_if_land_direction_is_south_when_turn_right() {
        initMarsCarWithPointAndDirection(SOUTH);
        marsCar.turnRight();
        assertThat(marsCar.getDirection()).isEqualTo(WEST);
    }

    /**
     *  初始化火星车的落地坐标和方向
     */
    private void initMarsCarWithPointAndDirection(Direction south) {
        marsCar = new MarsCar();
        marsCar.land(new Point(100, 200), south);
    }

    /**
     *  初始位置 西 ，右转
     */

    @Test
    public void should_show_north_if_land_direction_is_west_when_turn_right() {
        initMarsCarWithPointAndDirection(WEST);
        marsCar.turnRight();
        assertThat(marsCar.getDirection()).isEqualTo(NORTH);
    }

    /**
     * 批量发送指令，验证指正确性
     */
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

    /**
     * 批量发送指令，然后执行指令，报告执行后的结果
     */
    @Test
    public void should_move_after_received_command(){
        Command command = new Command();
        List<Operation> operations = new ArrayList<>();
        operations.add(new Forward( 15));
        operations.add(new BackOperation(50));
        operations.add(new LeftOperation());
        command.send(new Area(1000, 2000), new Point(1100, 200), NORTH, operations);
        marsCar.receivedCommandAndInit(command);

        marsCar.work();

        assertThat(marsCar.getPoint()).isEqualTo(new Point(1100,165));
        assertThat(marsCar.getDirection()).isEqualTo(WEST);

    }



}
