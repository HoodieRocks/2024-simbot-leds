// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utils.LEDs;
import frc.robot.utils.MotorUtils;
import frc.robot.utils.LEDs.LEDStates;

public class Robot extends TimedRobot {

  private static Robot instance;
  Joystick controller = new Joystick(0);

  TalonSRX motorLeft1 = new TalonSRX(13);
  TalonSRX motorLeft2 = new TalonSRX(5);
  TalonSRX motorLeft3 = new TalonSRX(12);

  TalonSRX motorRight1 = new TalonSRX(3);
  TalonSRX motorRight2 = new TalonSRX(11);
  TalonSRX motorRight3 = new TalonSRX(1);

  PIDController turningPID;

  double turningP = 0.03;
  double turningI = 0.0;
  double turningD = 0.0;

  AHRS gyro = new AHRS(SPI.Port.kMXP);

  public static Robot getInstance() {
    if (instance == null) {
      instance = new Robot();
    }
    return instance;
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    turningPID = new PIDController(turningP, turningI, turningD);
  }

  @Override
  public void robotPeriodic() {
    var leds = LEDs.getInstance();
    leds.update();

    if (controller.getRawButtonPressed(5)){
      leds.setState(leds.getState().previous());
    }
    if (controller.getRawButtonPressed(6)){
      leds.setState(leds.getState().next());
    }
    SmartDashboard.putString("LED State", leds.getState().toString());
  }

  @Override
  public void autonomousInit() {}

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    gyro.reset();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("GYRO YAW", gyro.getYaw());
    SmartDashboard.putNumber("TURNING P", turningP);
    SmartDashboard.putNumber("TURNING I", turningI);
    SmartDashboard.putNumber("TURNING D", turningD);

    double leftXAxis = controller.getRawAxis(0);
    double leftYAxis = controller.getRawAxis(1);

    MotorUtils.setDrive(leftYAxis, leftXAxis);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}

  public TalonSRX[] getLeftSideMotors() {
    return new TalonSRX[] {motorLeft1, motorLeft2, motorLeft3};
  }

  public TalonSRX[] getRightSideMotors() {
    return new TalonSRX[] {motorRight1, motorRight2, motorRight3};
  }
}
