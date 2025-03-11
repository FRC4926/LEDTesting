package frc.robot;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.InchesPerSecond;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.LEDPattern.GradientType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LEDConstants;

public class LEDSubsystem extends SubsystemBase {
    private final AddressableLED led = new AddressableLED(LEDConstants.port);
    private final AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(LEDConstants.numLeds);
    private int x = 0;
    LEDPattern scrollingGalactechRainbow = (reader, writer) -> {
        SmartDashboard.putNumber("len", reader.getLength()); 
        for (int i = 0; i < LEDConstants.numLeds; i++) {
            if (0 <= (i - x) && (i - x) <= 2) {
                writer.setLED(i, Color.kGreen);
            } else {
                writer.setLED(i, Color.kPurple);
            }
        }
        x++;
        x %= LEDConstants.numLeds;
        SmartDashboard.putNumber("x", x);
    };


    public LEDSubsystem() {

        led.setLength(LEDConstants.numLeds);
        led.setData(ledBuffer);
        led.start();
    }

    @Override
    public void periodic() {
        scrollingGalactechRainbow.applyTo(ledBuffer);
        led.setData(ledBuffer);
    }


    // public void setColor(Color color) {
    //     LEDPattern.solid(color).applyTo(ledBuffer);
    //     led.setData(ledBuffer);
    // }
    // public void doThing() {
    //     LEDPattern.gradient(GradientType.kContinuous, Color.kGreen, Color.kPurple)
    //         .applyTo(ledBuffer);
    // }
    // public Command runRainbow() {
    //     LEDPattern rainbow = LEDPattern.rainbow(255, 100)
    //         .scrollAtAbsoluteSpeed(InchesPerSecond.of(10.0), Inches.of(1.0));
        
    //     return run(() -> rainbow.applyTo(ledBuffer));
    // }
}
