package view;

import java.util.prefs.Preferences;

/**
 * Created by GR6 on 14.04.15.
 */
public class CircuitSimulator {
    public static final int TERMINAL_R = 3;
    public static final double CONNECTION_DISTANCE = 3;
    public static String wrdataNgSpice = "ngspice";

    public static String ngSpiceExe;
    public static String workdir;

    public static Preferences prefs = Preferences.userNodeForPackage(CircuitSimulator.class);

    public static void loadPreferences() {
        ngSpiceExe = prefs.get("ngSpiceExe", "brak danych");
        workdir = prefs.get("workdir", System.getProperty("user.dir"));
    }

    public static void savePreferences() {
        prefs.put("ngSpiceExe", ngSpiceExe);
        prefs.put("workdir", workdir);
    }
}
