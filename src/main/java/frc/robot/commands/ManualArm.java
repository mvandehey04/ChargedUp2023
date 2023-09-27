package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class ManualArm extends CommandBase {
  private Arm arm;
  private Joystick operator;
  private Timer timer;
  private boolean hasMoved = false;

  public ManualArm(Arm arm, Joystick operator) {
    this.arm = arm;
    addRequirements(arm);
    this.operator = operator;
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
    double x = operator.getRawAxis(Constants.armUp);
    //SmartDashboard.putNumber("operator", x);
    //SmartDashboard.putNumber("timer", timer.get());
    //SmartDashboard.putBoolean("boolean", hasMoved);
    // if trigger pressed
    if(x < .1){
      // check if trigger moved
      if(hasMoved){
        timer.reset();
        timer.start();
        hasMoved = false;
      }
      // slows arm if timer is less than 2 seconds
      if(timer.get() < 2 && timer.get() != 0){
        arm.Move(.05);
      }
      // otherwise timer and arm stop
      else{
        timer.stop();
        timer.reset();
        arm.Move(0);
      }
    }
    // otherwise arm moves like normal
    else{
      arm.Move(x * .75);
      hasMoved = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted){
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
