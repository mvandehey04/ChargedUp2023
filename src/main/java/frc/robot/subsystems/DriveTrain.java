package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  // motors
  private CANSparkMax frontRight;
  private CANSparkMax backRight;
  private CANSparkMax frontLeft;
  private CANSparkMax backLeft;

  // groups
  private MotorControllerGroup left;
  private MotorControllerGroup right;

  private DifferentialDrive drive;

  public DriveTrain(){
    // motors
    frontRight = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushed);
    backRight = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushed);
    frontLeft = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushed);
    backLeft = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushed);

    //grouping
    left = new MotorControllerGroup(frontLeft, backLeft);
    right = new MotorControllerGroup(frontRight, backRight);
    left.setInverted(true);

    drive = new DifferentialDrive(left, right);
  }

  public void Drive(double x, double z){
    drive.arcadeDrive(x,-z);
    //SmartDashboard.putNumber("z", z);
    //SmartDashboard.putNumber("x", x);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
