package tirepressuremonitoringsystem;

public interface Notifier {
    default void extracted(String msg) {
        System.out.println(msg);
    }
}
