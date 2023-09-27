package frc.robot;

import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoDrive2;
import frc.robot.commands.AutoDriveTurn;
import frc.robot.commands.AutoLowerArm;
import frc.robot.commands.AutoRaiseArm;
import frc.robot.commands.ClampCone;
import frc.robot.commands.ClampCube;
import frc.robot.commands.Drive;
import frc.robot.commands.ManualArm;
import frc.robot.commands.ManualClamp;
import frc.robot.commands.ReleaseCube;
import frc.robot.commands.Wait;
import frc.robot.commands.ReleaseCone;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Clamp;
import frc.robot.subsystems.DriveTrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  // joysticks
  private static Joystick driver;
  private static Joystick operator;

  // joystick buttons
  private static JoystickButton clampInConeButton;
  private static JoystickButton clampOutConeButton;
  private static JoystickButton clampInCubeButton;
  private static JoystickButton clampOutCubeButton;

  // subsystems
  private static DriveTrain driveTrain;
  private static Clamp clamp;
  private static Arm arm;

  // manual commands
  private static Drive drive;
  private static ManualClamp manualClamp;
  private static ManualArm manualArm;

  // auto commands
  // clamp
  private static ClampCone clampCone;
  private static ClampCube clampCube;
  private static ReleaseCone releaseCone;
  private static ReleaseCube releaseCube;
  // drive
  // drive auto 1 
  private static AutoDrive R1driveBack1;
  private static AutoDrive R1driveForward1;
  private static AutoDrive R1driveBack2;
  private static AutoDriveTurn R1turnLeft1;
  private static AutoDrive R1driveForward2;
  private static AutoDriveTurn R1turnRight1;
  private static AutoDrive R1driveForward3;
  // drive auto 2
  private static AutoDrive R2driveBack1;
  private static AutoDrive R2driveForward1;
  private static AutoDrive R2driveBack2;
  private static AutoDriveTurn R2turnRight1;
  private static AutoDrive R2driveForward2;
  private static AutoDriveTurn R2turnLeft1;
  private static AutoDrive R2driveForward3;
  // drive auto 3
  private static AutoDrive R3driveBack1;
  private static AutoDrive R3driveForward1;
  private static AutoDrive R3driveBack2;
  private static AutoDrive R3driveForward2;
  // drive auto 4
  private static AutoDrive R4driveBack1;
  private static AutoDrive R4driveForward1;
  private static AutoDrive R4driveBack2;
  // drive auto 4
  private static AutoDrive R5driveForward1;
  // drive auto 6
  private static AutoDrive2 R6forward1;
  // drive auto 7
  private static AutoDrive2 R7back1;
  private static AutoDrive2 R7forward1;
  private static AutoDrive2 R7back2;
  
  // arm auto 1
  private static AutoRaiseArm R1armUp1;
  private static AutoLowerArm R1armDown1;
  // arm auto 2
  private static AutoRaiseArm R2armUp1;
  private static AutoLowerArm R2armDown1;
  // arm auto 3
  private static AutoRaiseArm R3armUp1;
  private static AutoLowerArm R3armDown1;
  // arm auto 4
  private static AutoRaiseArm R4armUp1;
  private static AutoLowerArm R4armDown1;
  // arm auto 4
  private static AutoRaiseArm R5armUp1;
  private static AutoLowerArm R5armDown1;
  // arm auto 6
  private static AutoRaiseArm R6armUp1;
  private static AutoLowerArm R6armDown1;
  // arm auto 7
  private static AutoRaiseArm R7armUp1;
  private static AutoLowerArm R7armDown1;
  private static AutoRaiseArm R7armUp2;
  private static AutoLowerArm R7armDown2;
  // wait auto 
  private static Wait wait;

  // grouped commands for auto 1
  private final SequentialCommandGroup autonomous1;
  private static ParallelCommandGroup R1backAndArm1;
  // grouped commands for auto 2
  private final SequentialCommandGroup autonomous2;
  private static ParallelCommandGroup R2backAndArm1;
  // grouped commands for auto 3
  private final SequentialCommandGroup autonomous3;
  private static ParallelCommandGroup R3backAndArm1;
  // grouped commands for auto 4
  private final SequentialCommandGroup autonomous4;
  private static ParallelCommandGroup R4backAndArm1;
  // grouped commands for auto 5
  private final SequentialCommandGroup autonomous5;
  private static ParallelCommandGroup R5forwardAndArm1;
  // grouped commands for auto 6
  private final ParallelCommandGroup autonomous6;
  private static SequentialCommandGroup R6armMovement;
  // grouped commands for auto 7
  private final SequentialCommandGroup autonomous7;
  private static ParallelCommandGroup R7backArmUp1;
  private static ParallelCommandGroup R7backArmUp2;
  
  // to test gyro auto
  private final AutoDriveTurn test;

  // navX MXP using SPI
  private static AHRS gyro;

  // a chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();
 
  public RobotContainer() {
    // gyro
    gyro = new AHRS(SPI.Port.kMXP);
    gyro.reset();
    Shuffleboard.getTab("gyro").add(gyro);
    
    // controllers
    driver = new Joystick(0);
    operator = new Joystick(1);

    // subsytems
    clamp = new Clamp();
    driveTrain = new DriveTrain();
    arm = new Arm();

    // commands
    drive = new Drive(driveTrain, driver);
    clampCone = new ClampCone(clamp);
    clampCube = new ClampCube(clamp);
    releaseCone = new ReleaseCone(clamp);
    releaseCube = new ReleaseCube(clamp);
    manualClamp = new ManualClamp(clamp, operator);
    manualArm = new ManualArm(arm, operator);
    
    // auto clamp buttons
    clampInConeButton = new JoystickButton(operator, Constants.clampConeIn);
    clampOutConeButton = new JoystickButton(operator, Constants.clampConeOut);
    clampInCubeButton = new JoystickButton(operator, Constants.clampCubeIn);
    clampOutCubeButton = new JoystickButton(operator, Constants.clampCubeOut);

    // set default commands
    driveTrain.setDefaultCommand(drive);
    clamp.setDefaultCommand(manualClamp);
    arm.setDefaultCommand(manualArm);

    // auto
    /////////////////////////////////////////////////////////////////////////////////////////////////
    // route #1
    /* lift arm to release cube and drive back, push cube into space, retreat back, turn 90 degrees, 
    drive forward, turn 90 degrees, drive forward onto charging station */

    // drive back command (combine with arm)
    R1driveBack1 = new AutoDrive(driveTrain, 2, -.5, 0, gyro);
    // raise arm command (combine with drive back)
    R1armUp1 = new AutoRaiseArm(arm, 2);
    // back and arm up at same time (realeases cube)
    R1backAndArm1 = new ParallelCommandGroup(R1driveBack1, R1armUp1);
    // arm lower slowly (bring arm back down to push cube in)
    R1armDown1 = new AutoLowerArm(arm, 1);
    // drive forward (pushes cube in)
    R1driveForward1 = new AutoDrive(driveTrain, 2, .5, 0, gyro);
    // straight back (get out of community)
    R1driveBack2 = new AutoDrive(driveTrain, 3, -.5, 0, gyro);
    // turn 90 degrees (turn toward charging station)
    R1turnLeft1 = new AutoDriveTurn(driveTrain, -90, gyro);
    // drive forward (drive to be in front of charging station)
    R1driveForward2 = new AutoDrive(driveTrain, 2, .5, 0, gyro);
    // turn 90 degrees (turn to face charging station)
    R1turnRight1 = new AutoDriveTurn(driveTrain, 90, gyro);
    // drive forward (drive onto charging station)
    R1driveForward3 = new AutoDrive(driveTrain, 3, .5, 0, gyro);

    // final auto command
    autonomous1 = new SequentialCommandGroup(R1backAndArm1, R1armDown1, R1driveForward1, R1driveBack2,
    R1turnLeft1, R1driveForward2, R1turnRight1, R1driveForward3);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // route #2
    /* drive back and open arm to release cube, drive forward to push cube in, drive back, turn 90 degrees,
    drive forward, turn 90 degrees, drive onto charging station */

    // drive back command (combine with arm)
    R2driveBack1 = new AutoDrive(driveTrain, 2, -.5, 0, gyro);
    // raise arm command (combine with drive back)
    R2armUp1 = new AutoRaiseArm(arm, 2);
    // back and arm up at same time (realeases cube)
    R2backAndArm1 = new ParallelCommandGroup(R2driveBack1, R2armUp1);
    // arm lower slowly (bring arm back down to push cube in)
    R2armDown1 = new AutoLowerArm(arm, 1);
    // drive forward (pushes cube in)
    R2driveForward1 = new AutoDrive(driveTrain, 2, .5, 0, gyro);
    // straight back (get out of community)
    R2driveBack2 = new AutoDrive(driveTrain, 3, -.5, 0, gyro);
    // turn 90 degrees (turn toward charging station)
    R2turnRight1 = new AutoDriveTurn(driveTrain, 90, gyro);
    // drive forward (drive to be in front of charging station)
    R2driveForward2 = new AutoDrive(driveTrain, 2, .5, 0, gyro);
    // turn 90 degrees (turn to face charging station)
    R2turnLeft1 = new AutoDriveTurn(driveTrain, -90, gyro);
    // drive forward (drive onto charging station)
    R2driveForward3 = new AutoDrive(driveTrain, 3, .5, 0, gyro);

    // final auto command
    autonomous2 = new SequentialCommandGroup(R2backAndArm1, R2armDown1, R2driveForward1, R2driveBack2,
    R2turnRight1, R2driveForward2, R2turnLeft1, R2driveForward3);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // route #3
    /* raise arm to release cube and drive back, push cube into space, drive back over charging 
    station, then drive forward to dock on station */
    
    // drive back (combine with arm)
    R3driveBack1 = new AutoDrive(driveTrain, 2, -.5, 0, gyro);
    // raise arm command (combine with drive back)
    R3armUp1 = new AutoRaiseArm(arm, 2);
    // back and arm up at same time (to release cube)
    R3backAndArm1 = new ParallelCommandGroup(R3driveBack1, R3armUp1);
    // lower arm (to be able to push cube in)
    R3armDown1 = new AutoLowerArm(arm, 2);
    // drive forward (push cube in)
    R3driveForward1 = new AutoDrive(driveTrain, 2, .5, 0, gyro);
    // drive back (drive over charging station to get out of community area)
    R3driveBack2 = new AutoDrive(driveTrain, 4, -.5, 0, gyro);
    // drive forward (onto charging station)
    R3driveForward2 = new AutoDrive(driveTrain, 2, .5, 0, gyro);

    // final auto command
    autonomous3 = new SequentialCommandGroup(R3backAndArm1, R3armDown1, R3driveForward1, R3driveBack2, 
    R3driveForward2);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // route #4
    /* open arm and back up, lower arm, drive forward, drive backward */

    // drive back (combine with arm)
    R4driveBack1 = new AutoDrive(driveTrain, 2, -.5, 0, gyro);
    // raise arm command (combine with drive back)
    R4armUp1 = new AutoRaiseArm(arm, 2);
    // back and arm up at the same time (to release cube)
    R4backAndArm1 = new ParallelCommandGroup(R4driveBack1, R4armUp1);
    // lower arm
    R4armDown1 = new AutoLowerArm(arm, 2);
    // drive forward (to push cone in)
    R4driveForward1 = new AutoDrive(driveTrain, 2, .5, 0, gyro);
    // drive back (to get out of community)
    R4driveBack2 = new AutoDrive(driveTrain, 4, -.5, 0, gyro);

    // final auto command
    autonomous4 = new SequentialCommandGroup(R4backAndArm1, R4armDown1, R4driveForward1, R4driveBack2);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // route # 5
    /* drive forward and raise arm to lower clamp */
    
    // drive forward (combine with arm)
    R5driveForward1 = new AutoDrive(driveTrain, 3, .5, 0, gyro);
    // arm up (combine with drive)
    R5armUp1 = new AutoRaiseArm(arm, 2);
    // drive forward and arm up
    R5forwardAndArm1 = new ParallelCommandGroup(R5driveForward1, R5armUp1);
    // lower arm
    R5armDown1 = new AutoLowerArm(arm, 2);

    // final auto command
    autonomous5 = new SequentialCommandGroup(R5forwardAndArm1, R5armDown1);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // auto 6 no gyro
    /* drive forward and lower clamp*/
    // forward
    R6forward1 = new AutoDrive2(driveTrain, 3.5, .6, 0);
    // arm up
    R6armUp1 = new AutoRaiseArm(arm, 2);
    // arm down
    R6armDown1 = new AutoLowerArm(arm, 2);
    // arm up and down
    R6armMovement = new SequentialCommandGroup(R6armUp1, R6armDown1);

    // final auto command
    autonomous6 = new ParallelCommandGroup(R6forward1, R6armMovement);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // auto 7 no gyro

    // back up
    R7back1 = new AutoDrive2(driveTrain, 1.5, -.5, 0);
    // arm up
    R7armUp1 = new AutoRaiseArm(arm, 1);
    // back and arm
    R7backArmUp1 = new ParallelCommandGroup(R7back1, R7armUp1);
    // arm down
    R7armDown1 = new AutoLowerArm(arm, 1);
    // forward
    R7forward1 = new AutoDrive2(driveTrain, 2, .5, 0);
    // wait 
    wait = new Wait(2);
    //back
    R7back2 = new AutoDrive2(driveTrain, 3.5, -.6, 0);
    // arm up
    R7armUp2 = new AutoRaiseArm(arm, 1);
    // second back and arm
    R7backArmUp2 = new ParallelCommandGroup(R7back2, R7armUp2);
    // arm back down
    R7armDown2 = new AutoLowerArm(arm, 1);

    // final auto command
    autonomous7 = new SequentialCommandGroup(R7backArmUp1, R7armDown1, R7forward1, wait, R7backArmUp2, R7armDown2);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // test route
    test = new AutoDriveTurn(driveTrain, 90, gyro);
    //////////////////////////////////////////////////////////////////////////////////////////////////
    // add commands to the autonomous command chooser
    m_chooser.setDefaultOption("route #1", autonomous1);
    m_chooser.addOption("route #2", autonomous2);
    m_chooser.addOption("route #3", autonomous3);
    m_chooser.addOption("route #4", autonomous4);
    m_chooser.addOption("route #5", autonomous5);
    m_chooser.addOption("route #6", autonomous6);
    m_chooser.addOption("route #7", autonomous7);
    m_chooser.addOption("test", test);

    // put the chooser on the dashboard
    SmartDashboard.putData(m_chooser);

    // configure button bindings
    configureBindings();
  }
  
  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    clampInConeButton.onTrue(clampCone);
    clampOutConeButton.onTrue(releaseCone);
    clampInCubeButton.onTrue(clampCube);
    clampOutCubeButton.onTrue(releaseCube);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();

  }
}
