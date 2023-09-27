package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class Drive extends CommandBase {
  private DriveTrain driveTrain;
  private Joystick driver;
  
  public Drive(DriveTrain driveTrain, Joystick driver){
    this.driveTrain = driveTrain;
    addRequirements(driveTrain);
    this.driver = driver;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
    double x = driver.getRawAxis(Constants.forward);
    double x2 = driver.getRawAxis(Constants.backward);
    double z = driver.getRawAxis(Constants.turn);

    // cubing z to make turns less jarring
    double z2 = Math.pow(z, 3);
    z2 = z2 * .65;
    driveTrain.Drive(((x-x2) * .85) ,z2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted){
    driveTrain.Drive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

