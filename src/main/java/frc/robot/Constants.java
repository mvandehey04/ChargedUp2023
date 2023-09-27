package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

public final class Constants {
  // buttons
  // driver buttons
  public static final int forward = 3;
  public static final int backward = 2;
  public static final int turn = 0;

  // operator buttons
  // clamp
  // manual
  // left joystick, up = close, down = open
  public static final int manualClamp = 1;
  // non manual
  // x = cone in, y = cone out, a = cube in, b = cube out
  public static final int clampConeIn = 3;
  public static final int clampConeOut = 4;
  public static final int clampCubeIn = 1;
  public static final int clampCubeOut = 2;

  // arm
  // left trigger
  public static final int armUp = 2;
}

