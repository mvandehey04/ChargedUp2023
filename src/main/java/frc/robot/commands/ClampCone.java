package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Clamp;

public class ClampCone extends CommandBase {
  private Clamp clamp;
  private Timer timer;
  
  public ClampCone(Clamp clamp) {
    this.clamp = clamp;
    addRequirements(clamp);
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
    clamp.Move(.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted){
    clamp.Move(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timer.get() > 2){
      return true;
    }
    return false;
  }
}
