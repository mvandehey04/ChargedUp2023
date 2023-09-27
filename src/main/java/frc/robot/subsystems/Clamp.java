package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Clamp extends SubsystemBase {
  //private TalonSRX clampMotor;
  private WPI_TalonSRX clampMotor;
  public Clamp(){
    //clampMotor = new TalonSRX(11);
    clampMotor = new WPI_TalonSRX(11);
    clampMotor.setInverted(true);
  }

  public void Move(double speed){
    //clampMotor.set(ControlMode.PercentOutput, speed);
    clampMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

