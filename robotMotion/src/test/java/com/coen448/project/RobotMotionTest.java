package com.coen448.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*  Please Note:
    All unit tests will be done in the RobotMotionTest class
    no other test classes need to created.
    (Robot class is not tested because it is a simple data class
    and it will be tested in the RobotMotionTest class)
*/
/*
    Please Note:
    "Should --> When" Convention for naming the test method will be used.
     For example: should_lift_pen_when_pen_up_command_is_given()
 */

/*
* The program should process the following commands:
    [U|u]: Pen up command - The robot should lift the pen.
    [D|d]: Pen down command - The robot should lower the pen.
    [R|r]: Turn right command - The robot should turn 90 degrees to the right.
    [L|l]: Turn left command - The robot should turn 90 degrees to the left.
    [M s|m s]: Move forward command - The robot should move s spaces in the current direction (s is a non-negative integer).
    *
    *
    [P|p]: Print the N by N array and display the indices.
    [C|c]: Print the current position of the pen, whether it is up or down, and its facing direction.
    [Q|q]: Stop the program.
    [I n|i n]: Initialize the system command - The robot and the floor array should be reset to their initial states.
               The size of the array should be set to n, where n is an integer greater than zero.

The default format for commands [M s|m s] and [I n|i n] should be command character followed by zero or one space and then an integer greater than zero.
The program should allow overriding the default format for input, and the new requirements must be clearly specified in the project deliverables.
* */

class RobotMotionTest {
    boolean isTerminated = false;
    private Robot robot;
    @BeforeEach
    void setUp() {
        this.robot = new Robot(20);
    }
    // [U|u]: Pen up command - The robot should lift the pen.
    @Test
    void should_lift_pen_when_pen_up_command_is_given() {
        // given
        String command = "U";
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertTrue(robot.mIsPenUp);
    }
    // [D|d]: Pen down command - The robot should lower the pen.
    @Test
    void should_lower_pen_when_pen_down_command_is_given() {
        // given
        String command = "D";
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertFalse(robot.mIsPenUp);
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
        robot.mOrientation = Robot.Orientation.North;
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertEquals(robot.mOrientation, robot.mOrientation.East);
    }
    @Test
    void should_turn_south_when_turn_right_command_applied_to_east() {
        // given
        String command = "R";
        robot.mOrientation = Robot.Orientation.East;
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertEquals(robot.mOrientation, robot.mOrientation.South);
    }
    @Test
    void should_turn_west_when_turn_right_command_applied_to_south() {
        // given
        String command = "R";
        robot.mOrientation = Robot.Orientation.South;
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertEquals(robot.mOrientation, robot.mOrientation.West);
    }
    @Test
    void should_turn_north_when_turn_right_command_applied_to_west() {
        // given
        String command = "R";
        robot.mOrientation = Robot.Orientation.West;
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertEquals(robot.mOrientation, robot.mOrientation.North);
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
        robot.mOrientation = Robot.Orientation.North;
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertEquals(robot.mOrientation, robot.mOrientation.West);
    }
    @Test
    void should_turn_south_when_turn_left_command_applied_to_west() {
        // given
        String command = "L";
        robot.mOrientation = Robot.Orientation.West;
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertEquals(robot.mOrientation, robot.mOrientation.South);
    }
    @Test
    void should_turn_east_when_turn_left_command_applied_to_south() {
        // given
        String command = "L";
        robot.mOrientation = Robot.Orientation.South;
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertEquals(robot.mOrientation, robot.mOrientation.East);
    }
    @Test
    void should_turn_north_when_turn_left_command_applied_to_east() {
        // given
        String command = "L";
        robot.mOrientation = Robot.Orientation.East;
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertEquals(robot.mOrientation, robot.mOrientation.North);
    }

    // [M s|m s]: Move forward command - The robot should move s spaces in the current direction
    /* - should not allow commAnd with s as negative integer.
       - robot should move forward s spaces to north when facing north
       - robot should move forward s spaces to east when facing east
       - robot should move forward s spaces to south when facing south
       - robot should move forward s spaces to west when facing west
     */

    @Test
    void should_not_allow_forward_command_with_negative_integer(){
        // given
        String command = "M -5";
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertFalse(RobotMotion.isValidCommand);
    }
    @Test
    void should_move_forward_s_spaces_when_move_forward_command_is_applied_to_north(){
        // given
        String command = "M 5";
        robot.mOrientation = Robot.Orientation.North;
        assertAll(
                // assert the robot's initial position
                () -> assertEquals(robot.mRow, 0),
                () -> assertEquals(robot.mColumn, 0)
        );
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertAll(
                // assert that robot moved 5 steps forward in the right direction
                () -> assertEquals(robot.mRow, 5),
                () -> assertEquals(robot.mColumn, 0)
        );
    }
    @Test
    void should_move_forward_s_spaces_when_move_forward_command_is_applied_to_east(){
        // given
        String command = "M 5";
        robot.mOrientation = Robot.Orientation.East;
        assertAll(
                // assert the robot's initial position
                () -> assertEquals(robot.mRow, 0),
                () -> assertEquals(robot.mColumn, 0)
        );
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertAll(
                // assert that robot moved 5 steps forward in the right direction
                () -> assertEquals(robot.mRow, 0),
                () -> assertEquals(robot.mColumn, 5)
        );
    }
    @Test
    void should_move_forward_s_spaces_when_move_forward_command_is_applied_to_south(){
        // given
        String command = "M 5";
        robot.mOrientation = Robot.Orientation.South;
        robot.mRow = 5;
        assertAll(
                // assert the robot's initial position
                () -> assertEquals(robot.mRow, 5),
                () -> assertEquals(robot.mColumn, 0)
        );
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertAll(
                // assert that robot moved 5 steps forward in the right direction
                () -> assertEquals(robot.mRow, 0),
                () -> assertEquals(robot.mColumn, 0)
        );
    }
    @Test
    void should_move_forward_s_spaces_when_move_forward_command_is_applied_to_west(){
        // given
        String command = "M 5";
        robot.mOrientation = Robot.Orientation.West;
        robot.mColumn = 5;
        assertAll(
                // assert the robot's initial position
                () -> assertEquals(robot.mRow, 0),
                () -> assertEquals(robot.mColumn, 5)
        );
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertAll(
                // assert that robot moved 5 steps forward in the right direction
                () -> assertEquals(robot.mRow, 0),
                () -> assertEquals(robot.mColumn, 0)
        );
    }
    @Test
    void should_not_move_forward_when_move_forward_command_is_applied_to_north_and_robot_is_at_the_north_boundary(){
        // given
        String command = "M 5";
        robot.mOrientation = Robot.Orientation.North;
        robot.mRow = 19;
        robot.mColumn = 0;
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(robot.mRow, 19),
                () -> assertEquals(robot.mColumn, 0)
        );
    }
    @Test
    void should_not_move_forward_when_move_forward_command_is_applied_to_east_and_robot_is_at_the_east_boundary(){
        // given
        String command = "M 5";
        robot.mOrientation = Robot.Orientation.East;
        robot.mRow = 0;
        robot.mColumn = 19;
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(robot.mRow, 0),
                () -> assertEquals(robot.mColumn, 19)
        );
    }
    @Test
    void should_not_move_forward_when_move_forward_command_is_applied_to_south_and_robot_is_at_the_south_boundary(){
        // given
        String command = "M 5";
        robot.mOrientation = Robot.Orientation.South;
        robot.mRow = 0;
        robot.mColumn = 0;
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(robot.mRow, 0),
                () -> assertEquals(robot.mColumn, 0)
        );
    }
    @Test
    void should_not_move_forward_when_move_forward_command_is_applied_to_west_and_robot_is_at_the_west_boundary(){
        // given
        String command = "M 5";
        robot.mOrientation = Robot.Orientation.West;
        robot.mRow = 0;
        robot.mColumn = 0;
        // when
        RobotMotion.processCommand(command, robot);
        // then
        assertAll(
                // assert that robot did not move forward
                () -> assertEquals(robot.mRow, 0),
                () -> assertEquals(robot.mColumn, 0)
        );
    }
}