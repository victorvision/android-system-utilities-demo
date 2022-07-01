package com.victorvision.systemutilities;

public class ApplicationInstallOptions {
    private boolean _forwardLock = false;
    private boolean _reinstall = false;
    private boolean _allowTestApks = false;
    private boolean _installOnSharedMassStorage = false;
    private boolean _installOnInternalSystemMemory = false;
    private boolean _allowVersionCodeDowngrade = false;

    /**
     * Adds the flag -l to the installation options.
     * -l: Install the package with forward lock.
     * @param enabled Defines if the flag should be used or not.
     */
    public void setForwardLock(boolean enabled) {
        _forwardLock = enabled;
    }

    /**
     * Adds the flag -r to the installation options.
     * -r: Reinstall an existing app, keeping its data.
     * @param enabled Defines if the flag should be used or not.
     */
    public void setReinstall(boolean enabled) {
        _reinstall = enabled;
    }

    /**
     * Adds the flag -t to the installation options.
     * -t: Allow test APKs to be installed.
     * @param enabled Defines if the flag should be used or not.
     */
    public void setAllowTestApks(boolean enabled) {
        _allowTestApks = enabled;
    }

    /**
     * Adds the flag -s to the installation options.
     * -s: Install package on the shared mass storage (such as sdcard).
     * @param enabled Defines if the flag should be used or not.
     */
    public void setInstallOnSharedMassStorage(boolean enabled) {
        _installOnSharedMassStorage = enabled;
    }

    /**
     * Adds the flag -f to the installation options.
     * -f: Install package on the shared mass storage (such as sdcard).
     * @param enabled Defines if the flag should be used or not.
     */
    public void setInstallOnInternalSystemMemory(boolean enabled) {
        _installOnSharedMassStorage = enabled;
    }

    /**
     * Adds the flag -d to the installation options.
     * -d: Allow version code downgrade.
     * @param enabled Defines if the flag should be used or not.
     */
    public void setAllowVersionCodeDowngrade(boolean enabled) {
        _allowVersionCodeDowngrade = enabled;
    }

    private String _installerPackageName = "";

    /**
     * Adds the flag -i and the installer package name to the installation options.
     * -i <INSTALLER_PACKAGE_NAME>: Specify the installer package name.
     * @param packageName Installer package name.
     */
    public void setInstallerPackageName(String packageName) {
        _installerPackageName = packageName;
    }

    /**
     * Gets the flag(s) for the installation options.
     * @return String containing installation options flag(s).
     */
    public String getFlagString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");

        if (_forwardLock) {
            stringBuilder.append("-l ");
        }

        if (_reinstall) {
            stringBuilder.append("-r ");
        }

        if (_allowTestApks) {
            stringBuilder.append("-t ");
        }

        if (_installOnSharedMassStorage) {
            stringBuilder.append("-s ");
        }

        if (_installOnInternalSystemMemory) {
            stringBuilder.append("-f ");
        }

        if (_allowVersionCodeDowngrade) {
            stringBuilder.append("-d ");
        }

        if (_installerPackageName.trim() != "") {
            stringBuilder.append("-i " + _installerPackageName + " ");
        }

        return stringBuilder.toString();
    }

    /**
     * Creates the default options for application installation.
     * @return The default options.
     */
    public static ApplicationInstallOptions createDefaultOptions() {
        ApplicationInstallOptions defaultOptions = new ApplicationInstallOptions();
        return defaultOptions;
    }
}
