package com.coen448.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotMotionTest {
    boolean isTerminated = false;

    @BeforeEach
    void setUp() {
        RobotMotion.robotHdl = new Robot(20);
    }

    // [U|u]: Pen up command - The robot should lift the pen.
    @Test
    void should_lift_pen_when_pen_up_command_is_given() {
        // given
        String command = "U";
        // when
        RobotMotion.processCommand(command);
        // then
        assertTrue(RobotMotion.robotHdl.mIsPenUp);
    }

    // [D|d]: Pen down command - The robot should lower the pen.
    @Test
    void should_lower_pen_when_pen_down_command_is_given() {
        // given
        String command = "D";
        // when
        RobotMotion.processCommand(command);
        // then
        assertFalse(RobotMotion.robotHdl.mIsPenUp);
    }

    // [R|r]: Turn right command - The robot should turn 90 degrees to the right.
    /*
     *  North --> East
     *  East --> South
     *  South --> West
     *  West --> North
     */
    @Test
    void should_turn_east_when_turn_right_command_applied_to_north() {
        // given
        String command = "R";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.North;
        // when
        RobotMotion.processCommand(command);
        // then
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.East);
    }

    @Test
    void should_turn_south_when_turn_right_command_applied_to_east() {
        // given
        String command = "R";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.East;
        // when
        RobotMotion.processCommand(command);
        // then
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.South);
    }

    @Test
    void should_turn_west_when_turn_right_command_applied_to_south() {
        // given
        String command = "R";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.South;
        // when
        RobotMotion.processCommand(command);
        // then
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.West);
    }

    @Test
    void should_turn_north_when_turn_right_command_applied_to_west() {
        // given
        String command = "R";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.West;
        // when
        RobotMotion.processCommand(command);
        // then
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.North);
    }

    // [L|l]: Turn left command - The robot should turn 90 degrees to the left.
    /*
     *  North --> West
     *  West --> South
     *  South --> East
     *  East --> North
     */
    @Test
    void should_turn_west_when_turn_left_command_applied_to_north() {
        // given
        String command = "L";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.North;
        // when
        RobotMotion.processCommand(command);
        // then
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.West);
    }

    @Test
    void should_turn_south_when_turn_left_command_applied_to_west() {
        // given
        String command = "L";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.West;
        // when
        RobotMotion.processCommand(command);
        // then
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.South);
    }

    @Test
    void should_turn_east_when_turn_left_command_applied_to_south() {
        // given
        String command = "L";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.South;
        // when
        RobotMotion.processCommand(command);
        // then
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.East);
    }

    @Test
    void should_turn_north_when_turn_left_command_applied_to_east() {
        // given
        String command = "L";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.East;
        // when
        RobotMotion.processCommand(command);
        // then
        assertEquals(RobotMotion.robotHdl.mOrientation, RobotMotion.robotHdl.mOrientation.North);
    }

    // [M s|m s]: Move forward command - The robot should move s spaces in the current direction
    /*
       - robot should move forward s spaces to north when facing north
       - robot should move forward s spaces to east when facing east
       - robot should move forward s spaces to south when facing south
       - robot should move forward s spaces to west when facing west
     */
    @Test
    void should_move_forward_s_spaces_when_move_forward_command_is_applied_to_north() {
        // given
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.North;
        assertAll(
                // assert the robot's initial position
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
        // when
        RobotMotion.processCommand(command);
        // then
        assertAll(
                // assert that robot moved 5 steps forward in the right direction
                () -> assertEquals(RobotMotion.robotHdl.mRow, 5),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }

    @Test
    void should_move_forward_s_spaces_when_move_forward_command_is_applied_to_east() {
        // given
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.East;
        assertAll(
                // assert the robot's initial position
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
        // when
        RobotMotion.processCommand(command);
        // then
        assertAll(
                // assert that robot moved 5 steps forward in the right direction
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 5)
        );
    }

    @Test
    void should_move_forward_s_spaces_when_move_forward_command_is_applied_to_south() {
        // given
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.South;
        RobotMotion.robotHdl.mRow = 5;
        assertAll(
                // assert the robot's initial position
                () -> assertEquals(RobotMotion.robotHdl.mRow, 5),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
        // when
        RobotMotion.processCommand(command);
        // then
        assertAll(
                // assert that robot moved 5 steps forward in the right direction
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }

    @Test
    void should_move_forward_s_spaces_when_move_forward_command_is_applied_to_west() {
        // given
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.West;
        RobotMotion.robotHdl.mColumn = 5;
        assertAll(
                // assert the robot's initial position
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 5)
        );
        // when
        RobotMotion.processCommand(command);
        // then
        assertAll(
                // assert that robot moved 5 steps forward in the right direction
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }

    @Test
    void should_not_move_forward_when_move_forward_command_is_applied_to_north_and_robot_is_at_the_north_boundary() {
        // given
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.North;
        RobotMotion.robotHdl.mRow = 19;
        RobotMotion.robotHdl.mColumn = 0;
        // when
        RobotMotion.processCommand(command);
        // then
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(RobotMotion.robotHdl.mRow, 19),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }

    @Test
    void should_not_move_forward_when_move_forward_command_is_applied_to_east_and_robot_is_at_the_east_boundary() {
        // given
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.East;
        RobotMotion.robotHdl.mRow = 0;
        RobotMotion.robotHdl.mColumn = 19;
        // when
        RobotMotion.processCommand(command);
        // then
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 19)
        );
    }

    @Test
    void should_not_move_forward_when_move_forward_command_is_applied_to_south_and_robot_is_at_the_south_boundary() {
        // given
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.South;
        RobotMotion.robotHdl.mRow = 0;
        RobotMotion.robotHdl.mColumn = 0;
        // when
        RobotMotion.processCommand(command);
        // then
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }

    @Test
    void should_not_move_forward_when_move_forward_command_is_applied_to_west_and_robot_is_at_the_west_boundary() {
        // given
        String command = "M 5";
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.West;
        RobotMotion.robotHdl.mRow = 0;
        RobotMotion.robotHdl.mColumn = 0;
        // when
        RobotMotion.processCommand(command);
        // then
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0)
        );
    }
    // [Q|q]: Stop the program.
    @Test
    void should_set_IsTerminated_when_quit_command_is_given() {
        // given
        String command = "Q";
        // when
        RobotMotion.processCommand(command);
        // then
        assertTrue(RobotMotion.isTerminated);
    }

    // [I n|i n]: Initialize the system command
    /*
       - The robot and the floor array should be reset to their initial states.
       - The size of the array should be set to the given n.
       - The robot should be placed at the bottom left corner of the array facing North.
    */
    @Test
    void should_reset_the_robot_and_the_floor_array_when_initialize_command_is_given() {
        // given
        String command = "I 5";
        RobotMotion.robotHdl.mRow = 1;
        RobotMotion.robotHdl.mColumn = 1;
        RobotMotion.robotHdl.mOrientation = Robot.Orientation.East;
        RobotMotion.robotHdl.mFloor = new int[5][5];
        RobotMotion.robotHdl.mFloor[1][1] = 1;
        // when
        RobotMotion.processCommand(command);
        // then
        assertAll(
                // assert that robot is reset to its initial state
                () -> assertEquals(RobotMotion.robotHdl.mRow, 0),
                () -> assertEquals(RobotMotion.robotHdl.mColumn, 0),
                () -> assertEquals(RobotMotion.robotHdl.mOrientation, Robot.Orientation.North),
                // assert that floor array is reset to its initial state
                () -> assertEquals(RobotMotion.robotHdl.mFloor.length, 5),
                () -> assertEquals(RobotMotion.robotHdl.mFloor[1][1], 0)
        );
    }


    @Test
    void is_valid_command() {
        // given
        String command = "I 5";
        // when
        Boolean result = RobotMotion.processCommand(command);
        // then
        assertAll(
                // assert that robot is reset to its initial state
                () -> assertTrue(result)

        );
    }
}