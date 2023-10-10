package frc.robot.utils;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LEDs {
    private final AddressableLED led;
    private final AddressableLEDBuffer buffer;

    private static LEDs LEDS;
    private LEDs(){
        led = new AddressableLED(9);
        buffer = new AddressableLEDBuffer(86);
        led.setLength(buffer.getLength());
        this.update();
        led.start();
    }

    public static LEDs getInstance(){
        if (LEDS == null){
            LEDS = new LEDs();
        }
        return LEDS;
    }

    public enum LEDStates {
        PARKER,
        RED,
        ENZO,
        JOSH,
        JOSH_GLASSES,
        SARAH,;

        public LEDStates previous() {
            var states = LEDStates.values();
            var index = this.ordinal() - 1;
            return states[index<0?states.length-1:index];
        }

        public LEDStates next() {
            var states = LEDStates.values();
            return states[(this.ordinal() + 1) % states.length];
        }
    }

    private LEDStates ledState = LEDStates.PARKER;

    public void update() {
        var time = System.currentTimeMillis();
        switch (this.ledState){
            case ENZO:
                break;
            case JOSH:
                break;
            case JOSH_GLASSES:
                for (int i =0; i < buffer.getLength(); i++ ){
                    
                }
                break;
            case PARKER:
                for (int i = 0; i < buffer.getLength(); i++ ){
                    buffer.setHSV(i, ((i*180*2)/buffer.getLength() + (int)time/5)%180, 255, 255);
                }
                break;
            case RED:
                for (int i = 0; i < buffer.getLength(); i++ ){
                    buffer.setRGB(i, 255, 0, 0);
                }
                break;
            case SARAH:
                break;
            default:
                break;

        }
        led.setData(buffer);
    }

    public void setState(LEDStates states) {
        ledState = states;
    }

    public LEDStates getState() {
        return ledState;
    }

}
