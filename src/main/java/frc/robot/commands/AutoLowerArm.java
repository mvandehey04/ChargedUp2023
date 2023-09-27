package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class AutoLowerArm extends CommandBase {
  private Arm arm;
  private Timer timer;
  private double time;

  public AutoLowerArm(Arm arm, double time){
    this.arm = arm;
    addRequirements(arm);
    this.time = time;
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
    arm.Move(.05);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted){
    arm.Move(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timer.get() > time){
      return true;
    }
    return false;
  }
}
