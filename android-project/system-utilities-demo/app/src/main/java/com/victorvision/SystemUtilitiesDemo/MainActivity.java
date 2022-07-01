package com.victorvision.SystemUtilitiesDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.EditText;


import com.victorvision.systemutilities.SystemUtilities;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText widthInput, heightInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        widthInput = findViewById(R.id.widthInput);
        heightInput = findViewById(R.id.heightInput);
    }

    public void hideBarsButtonClick(View view) {
        SystemUtilities.hideBars();
    }

    public void showBarsButtonClick(View view) {
        SystemUtilities.showBars();
    }

    public void showPointerButtonClick(View view) {
        SystemUtilities.showPointerLocation();
    }

    public void hidePointerButtonClick(View view) {
        SystemUtilities.hidePointerLocation();
    }

    public void disableAutoRotationButtonClick(View view) {
        SystemUtilities.disableAutoRotation();
    }

    public void enableAutoRotationButtonClick(View view) {
        SystemUtilities.enableAutoRotation();
    }

    public void setRotationLandscapeButtonClick(View view) {
        SystemUtilities.setRotation(SystemUtilities.Rotations.Landscape);
    }

    public void setRotationPortraitButtonClick(View view) {
        SystemUtilities.setRotation(SystemUtilities.Rotations.Portrait);
    }

    public void setRotationReversedLandscapeButtonClick(View view) {
        SystemUtilities.setRotation(SystemUtilities.Rotations.LandscapeReversed);
    }

    public void setRotationReversedPortraitButtonClick(View view) {
        SystemUtilities.setRotation(SystemUtilities.Rotations.PortraitReversed);
    }

    public void setScreenResolutionButtonClick(View view) {
        String width = widthInput.getText().toString();
        String height = heightInput.getText().toString();
        if(width.isEmpty() || height.isEmpty()){
            return;
        }
        int widthValue = Integer.parseInt(width);
        int heightValue = Integer.parseInt(height);
        SystemUtilities.setScreenResolution(widthValue, heightValue);
    }

    public void brightnessMinimumButtonClick(View view) {
        SystemUtilities.setBrightnessMinimum();
    }

    public void brightnessMaximumButtonClick(View view) {
        SystemUtilities.setBrightnessMaximum();
    }

    public void resetScreenResolutionButtonClick(View view) {
        SystemUtilities.resetScreenResolution();
    }

    public void shutdownButtonClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to shut down the system?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SystemUtilities.shutdown();
                    }
                });

        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog shutDownAlertDialog = builder.create();
        shutDownAlertDialog.show();
    }

    public void rebootButtonClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to reboot the system?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SystemUtilities.reboot();
                    }
                });

        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog rebootAlertDialog = builder.create();
        rebootAlertDialog.show();
    }

    public void turnBuzzerOffButtonClick(View view) {
        SystemUtilities.turnBuzzerOff();
    }

    public void turnBuzzerOnButtonClick(View view) {
        SystemUtilities.turnBuzzerOn();
    }

    public void soundBuzzerButtonClick(View view) {
        SystemUtilities.soundBuzzer(100);
    }
}