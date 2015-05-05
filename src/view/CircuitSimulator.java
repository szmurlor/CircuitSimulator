package view;

import java.util.prefs.Preferences;

/**
 * Created by GR6 on 14.04.15.
 */
public class CircuitSimulator {
    public static final int TERMINAL_R = 3;
    public static final double CONNECTION_DISTANCE = 4;

    public enum SIMULATION_TYPE {STATIC, DYNAMIC}

    public static String ngspiceExe;
    public static String workdir;

    public static Preferences prefs = Preferences.userNodeForPackage(CircuitSimulator.class);

    public static void savePreferences() {
        prefs.put("ngspiceExe", ngspiceExe);
        prefs.put("workdir", workdir);
    }

    public static void loadPreferences() {
        ngspiceExe = prefs.get("ngspiceExe", "undefined");
        workdir = prefs.get("workdir",  System.getProperty("user.dir"));
    }

}
