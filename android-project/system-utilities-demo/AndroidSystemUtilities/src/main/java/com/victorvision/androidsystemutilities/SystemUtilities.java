package com.victorvision.androidsystemutilities;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

/**
 * Provides common utilities to interact with the operating system.
 */
public class SystemUtilities {
    /**
     * Executes a command on the environment in which the application is running.
     * @param command A specified system command.
     * @return The output from the command.
     */
    public static String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "n");
            }

        } catch (Exception e) {
            Log.d("System Utilities: ", e.getMessage());
            e.printStackTrace();
        }
        String response = output.toString();
        return response;
    }

    /**
     * Turns the buzzer on.
     */
    public static void turnBuzzerOn() {
        executeCommand("su -c echo none > /sys/class/leds/beep/trigger\n");
    }

    /**
     * Turns the buzzer off.
     */
    public static void turnBuzzerOff() {
        executeCommand("su -c echo default-on > /sys/class/leds/beep/trigger\n");
    }

    /**
     * Turns the buzzer on for the specified duration.
     * @param durationInMilliseconds Duration, in milliseconds. This is approximate, and is less accurate for shorter durations.
     */
    public static void soundBuzzer(int durationInMilliseconds) {
        turnBuzzerOn();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                turnBuzzerOff();
            }
        }, durationInMilliseconds);
    }

    /**
     * Hides the navigation and status bars.
     */
    public static void hideBars() {
        executeCommand("su -c wm overscan 0,-45,0,-45\n");
    }

    /**
     * Hides the navigation and status bars. Use this method when custom top and bottom offset are needed.
     * @param topOffset Pixel overscan offset from top border.
     * @param bottomOffset Pixel overscan offset from bottom border.
     */
    public static void hideBars(int topOffset, int bottomOffset) {
        executeCommand("su -c wm overscan 0," + topOffset + ",0," + bottomOffset + "\n");
    }

    /**
     * Shows the navigation and status bars.
     */
    public static void showBars() {
        executeCommand("su -c wm overscan reset\n");
    }

    /**
     * Shuts down the OS.
     */
    public static void shutdown() {
        executeCommand("setprop sys.powerctl shutdown");
    }

    /**
     * Reboots the OS.
     */
    public static void reboot(){
        executeCommand("su -c reboot");
    }

    /**
     * Disables auto rotation. Programmatic rotation by the {@link #setRotation(Rotations)} method is enabled.
     */
    public static void disableAutoRotation() {
        executeCommand("su -c settings put system accelerometer_rotation 0\n");
    }

    /**
     * Enables auto rotation. Programmatic rotation by the {@link #setRotation(Rotations)} method is disabled.
     */
    public static void enableAutoRotation() {
        executeCommand("su -c settings put system accelerometer_rotation 1\n");
    }

    /**
     * Specifies the screen rotation.
     */
    public static enum Rotations {
        /**
         * 0 degrees (Default orientation).
         */
        Landscape(0),
        /**
         * 90 degrees clockwise.
         */
        Portrait(1),
        /**
         * 180 degrees clockwise.
         */
        LandscapeReversed(2),
        /**
         * 270 degrees clockwise.
         */
        PortraitReversed(3);
        public int value;

        Rotations(int value) {
            this.value = value;
        }
    }

    /**
     * Sets the screen rotation. Won't work until auto rotation is disabled using the {@link #disableAutoRotation()} method.
     * @param rotation Screen rotation.
     */
    public static void setRotation(Rotations rotation) {
        disableAutoRotation();
        executeCommand("su -c settings put system user_rotation " + rotation.value + "\n");
    }

    /**
     * Sets the screen resolution.
     * @param width Screen width in pixels.
     * @param height Screen height in pixels.
     */
    public static void setScreenResolution(int width, int height){
        executeCommand("su -c wm size " + width + "x" + height + "\n");
    }

    /**
     * Resets the screen resolution.
     * @see #setScreenResolution(int, int) 
     */
    public static void resetScreenResolution(){
        executeCommand("su -c wm size reset\n");
    }

    /**
     * Gets the system date and time.
     * @return
     */
    public static Calendar getDateTime(){
        Calendar calendar = Calendar.getInstance();
        Log.d("System Utilities: ", calendar.toString());
        return calendar;
    }

    /**
     * Sets the screen brightness to minimum value.
     */
    public static void setBrightnessMinimum() {
        String result = executeCommand("su -c settings put system screen_brightness 0\n");
        Log.d("System Utilities: ", result);
    }

    /**
     * Sets the screen brightness to maximum value.
     */
    public static void setBrightnessMaximum() {
        String result = executeCommand("su -c settings put system screen_brightness 255\n");
        Log.d("System Utilities: ", result);
    }

    /**
     * Shows the pointer location. Used for debug purposes.
     * @see #hidePointerLocation()
     */
    public static void showPointerLocation() {
        String result = executeCommand("su -c settings put system pointer_location 1\n");
        Log.d("System Utilities: ", result);
    }

    /**
     * Hides the pointer location.
     * @see #showPointerLocation()
     */
    public static void hidePointerLocation() {
        String result = executeCommand("su -c settings put system pointer_location 0\n");
        Log.d("System Utilities: ", result);
    }

    /**
     * List the packages installed on the system.
     * @return The packages installed on the system.
     */
    public static String listPackages() {
        return executeCommand("pm list packages -f");
    }


}



