package com.example.mars_rover;

import com.example.mars_rover.operation.Operation;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.mars_rover.Direction.EAST;

public class CommandTest {

    @Test
    public void should_show_command_when_command_send() {
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation( 15));
        Command command = new Command();
        command.send(new Area(1000, 2000), new Point(1100, 200), EAST, operations);
        Assertions.assertThat(command.getOperations().size()).isEqualTo(1);
        Assertions.assertThat(command.getOperations().get(0)).isEqualTo(new Operation( 15));
    }


}