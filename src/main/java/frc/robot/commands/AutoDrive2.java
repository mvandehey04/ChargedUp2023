package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AutoDrive2 extends CommandBase {
  private Timer timer;
  private double time;
  private DriveTrain driveTrain;
  private double speed;
  private double angle;
  
  public AutoDrive2(DriveTrain driveTrain, double time, double speed, double angle){
    this.driveTrain = driveTrain;
    this.time = time;
    this.speed = speed;
    this.angle = angle;
    timer = new Timer();
    addRequirements(driveTrain);
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
    driveTrain.Drive(speed, angle);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted){
    driveTrain.Drive(0,0);
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
