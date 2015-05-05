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
    public static String logNgSpiceFile = "ngspice.log";
    public static String rawNgSpiceFile = "ngspice.raw";
    public static String scriptNgSpiceFile = "ngspice.cir";
    public static boolean showTerminalIdx = false;

    public static void loadPreferences() {
        ngSpiceExe = prefs.get("ngSpiceExe", "brak danych");
        workdir = prefs.get("workdir", System.getProperty("user.dir"));
        showTerminalIdx = prefs.getBoolean("showTerminalIdx", false);
    }

    public static void savePreferences() {
        prefs.put("ngSpiceExe", ngSpiceExe);
        prefs.put("workdir", workdir);
        prefs.putBoolean("showTerminalIdx", showTerminalIdx);
    }
}
