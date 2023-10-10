package frc.robot.utils;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Robot;

public class MotorUtils {

  private static Robot robot = Robot.getInstance();

  public static void setDrive(double x, double y) {
    for (TalonSRX leftMotor : robot.getLeftSideMotors()) {
      leftMotor.set(ControlMode.PercentOutput, y - -x);
    }
    for (TalonSRX rightMotor : robot.getRightSideMotors()) {
      rightMotor.set(ControlMode.PercentOutput, y - x);
    }
  }
}
