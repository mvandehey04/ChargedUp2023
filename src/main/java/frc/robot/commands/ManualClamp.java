package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Clamp;

public class ManualClamp extends CommandBase {
  private Clamp clamp;
  private Joystick operator;

  public ManualClamp(Clamp clamp, Joystick operator) {
    this.clamp = clamp;
    addRequirements(clamp);
    this.operator = operator;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
    double x = operator.getRawAxis(Constants.manualClamp);
    clamp.Move(-x);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted){}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
