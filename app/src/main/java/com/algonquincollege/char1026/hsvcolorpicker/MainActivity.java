package com.algonquincollege.char1026.hsvcolorpicker;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;

/**
 *  HSV Color Picker
 *  To pick any color on the color wheel with 3 sliders with the values of Hue, Saturation and Brightness
 *  Also have the option to auto click 15 set colors if you would like
 *  Tyler Charlebois (char1026)
 */

public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {


    private static final String LOG_TAG = "HSV";
    private static final String ABOUT_DIALOG_TAG = "About Dialog";
    private TextView mColorSwatch;
    private HSVModel mModel;
    private SeekBar mHueSB;
    private SeekBar mSaturationSB;
    private SeekBar mBrightnessSB;
    private TextView mHueTV;
    private TextView mSaturationTV;
    private TextView mBrightnessTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mModel = new HSVModel();
        mModel.setHue(HSVModel.MIN_HUE);
        mModel.setSaturation(HSVModel.MIN_SATURATION);
        mModel.setBrightness(HSVModel.MIN_BRIGHTNESS);
        mModel.addObserver(this);

        mColorSwatch = findViewById(R.id.colorSwatch);
        mHueSB = findViewById(R.id.hueSB);
        mHueTV = findViewById(R.id.hue);
        mSaturationSB = findViewById(R.id.saturationSB);
        mSaturationTV = findViewById(R.id.saturation);
        mBrightnessSB = findViewById(R.id.brightnessSB);
        mBrightnessTV = findViewById(R.id.brightness);

        mHueSB.setMax(Math.round(HSVModel.MAX_HUE));
        mSaturationSB.setMax(Math.round(HSVModel.MAX_SATURATION));
        mBrightnessSB.setMax(Math.round(HSVModel.MAX_BRIGHTNESS));

        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mBrightnessSB.setOnSeekBarChangeListener(this);

        this.updateView();

        mColorSwatch.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(), "H: " + NumberFormat.getInstance().format(mModel.getHue())
                        + "\u00B0\nS: " + NumberFormat.getInstance().format(mModel.getSaturation())
                        + "%\nV: " + NumberFormat.getInstance().format(mModel.getBrightness()) + "%", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (!fromUser) {
            return;
        }

        switch (seekBar.getId()) {
            case R.id.hueSB:
                mModel.setHue((float) mHueSB.getProgress());
                mHueTV.setText(getResources().getString(R.string.hueProgress, progress).toUpperCase() + "\u00B0");
                break;

            case R.id.saturationSB:
                mModel.setSaturation((float) mSaturationSB.getProgress());
                mSaturationTV.setText(getResources().getString(R.string.saturationProgress, progress).toUpperCase() + "%");
                break;

            case R.id.brightnessSB:
                mModel.setBrightness((float) mBrightnessSB.getProgress());
                mBrightnessTV.setText(getResources().getString(R.string.brightnessProgress, progress).toUpperCase() + "%");
                break;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mHueTV.setText(getResources().getString(R.string.hue));
                break;

            case R.id.saturationSB:
                mSaturationTV.setText(getResources().getString(R.string.saturation));
                break;

            case R.id.brightnessSB:
                mBrightnessTV.setText(getResources().getString(R.string.brightness));
                break;
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.i(LOG_TAG, "The color (int) is: " + mModel.getColor() + "");

        this.updateView();
    }

    private void updateColorSwatch() {
        mColorSwatch.setBackgroundColor(Color.HSVToColor(new float[]{
                mModel.getHue(),
                mModel.getSaturation()/100,
                mModel.getBrightness()/100}
        ));

    }

    private void updateHueSB() {
        mHueSB.setProgress(Math.round(mModel.getHue()));
    }

    private void updateSaturationSB() {
        mSaturationSB.setProgress(Math.round(mModel.getSaturation()));
    }

    private void updateBrightnessSB() {
        mBrightnessSB.setProgress(Math.round(mModel.getBrightness()));
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.black:
                mModel.asBlack();
                break;
            case R.id.red:
                mModel.asRed();
                break;
            case R.id.lime:
                mModel.asLime();
                break;
            case R.id.blue:
                mModel.asBlue();
                break;
            case R.id.yellow:
                mModel.asYellow();
                break;
            case R.id.cyan:
                mModel.asCyan();
                break;
            case R.id.magenta:
                mModel.asMagenta();
                break;
            case R.id.silver:
                mModel.asSilver();
                break;
            case R.id.gray:
                mModel.asGray();
                break;
            case R.id.maroon:
                mModel.asMaroon();
                break;
            case R.id.olive:
                mModel.asOlive();
                break;
            case R.id.green:
                mModel.asGreen();
                break;
            case R.id.purple:
                mModel.asPurple();
                break;
            case R.id.teal:
                mModel.asTeal();
                break;
            case R.id.navy:
                mModel.asNavy();
                break;
            default:
                break;
        }
        Toast.makeText(this, "H: " + NumberFormat.getInstance().format(mModel.getHue())
                + "\u00B0\nS: " + NumberFormat.getInstance().format(mModel.getSaturation())
                + "%\nV: " + NumberFormat.getInstance().format(mModel.getBrightness()) + "%", Toast.LENGTH_SHORT).show();
        updateColorSwatch();

    }

    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateBrightnessSB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}