package unit_tests;

import org.junit.jupiter.api.Test;
import tirepressuremonitoringsystem.Alarm;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AlarmTest {

    class AlarmSpy extends Alarm {
        private String msg;
        private double psi;

        @Override
        protected void sendMessage(String msg) {
            this.msg = msg;
        }

        @Override
        protected double getPsiPressureValue() {
            return this.psi;
        }
    }

    @Test
    public void alarm_activated() {
        AlarmSpy alarmSpy = new AlarmSpy();
        alarmSpy.check();
        alarmSpy.psi = 16.;
        assertEquals("Alarm activated!", alarmSpy.msg);
    }
}

