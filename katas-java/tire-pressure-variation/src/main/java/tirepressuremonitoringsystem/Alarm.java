package tirepressuremonitoringsystem;

public class Alarm {
    private final double LowPressureThreshold = 17;
    private final double HighPressureThreshold = 21;

    private Sensor sensor = new Sensor();

    private boolean alarmOn = false;
    private PressureSensor pressureSensor;
    private Notifier notifier;

    public Alarm() {

    }

    public Alarm(PressureSensor pressureSensor, Notifier notifier) {

        this.pressureSensor = pressureSensor;
        this.notifier = notifier;
    }

    public void check() {
        double psiPressureValue = getPsiPressureValue();

        if (psiPressureValue < LowPressureThreshold || HighPressureThreshold < psiPressureValue) {
            if(!isAlarmOn()) {
                alarmOn = true;
                sendMessage("Alarm activated!");
            }
        } else {
            if(isAlarmOn()) {
                alarmOn = false;
                sendMessage("Alarm deactivated!");
            }
        }
    }

    protected void sendMessage(String msg) {
        notifier.extracted(msg);
    }

    protected double getPsiPressureValue() {
        return sensor.popNextPressurePsiValue();
    }

    private boolean isAlarmOn() {
        return alarmOn;
    }
}
