package com.algonquincollege.char1026.hsvcolorpicker;

import android.graphics.Color;

import java.util.Observable;

/**
 * Created by tylercharlebois on 2017-11-21.
 */

public class HSVModel extends Observable {

    public static final float MIN_HUE = 0;
    public static final float MAX_HUE = 359;
    public static final float MIN_SATURATION = 0;
    public static final float MAX_SATURATION = 100;
    public static final float MIN_BRIGHTNESS = 0;
    public static final float MAX_BRIGHTNESS = 100;

    private float hue;
    private float saturation;
    private float brightness;

    public HSVModel() {
        this(MAX_HUE, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public HSVModel(Float hue, Float saturation, Float brightness) {
        super();

        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
    }

    public void asBlack() {
        this.setHSV(MIN_HUE, MIN_SATURATION, MIN_BRIGHTNESS);
    }

    public void asRed() {
        this.setHSV(MIN_HUE, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void asLime() {
        this.setHSV(120f, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void asBlue() {
        this.setHSV(240f, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void asYellow() {
        this.setHSV(60f, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void asCyan() {
        this.setHSV(180f, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void asMagenta() {
        this.setHSV(300f, MAX_SATURATION, MAX_BRIGHTNESS);
    }

    public void asSilver() {
        this.setHSV(MIN_HUE, MIN_SATURATION, 75f);
    }

    public void asGray() {
        this.setHSV(MIN_HUE, MIN_SATURATION, 50f);
    }

    public void asMaroon() {
        this.setHSV(MIN_HUE, MAX_SATURATION, 50f);
    }

    public void asOlive() {
        this.setHSV(60f, MAX_SATURATION, 50f);
    }

    public void asGreen() {
        this.setHSV(120f, MAX_SATURATION, 50f);
    }

    public void asPurple() {
        this.setHSV(300f, MAX_SATURATION, 50f);
    }

    public void asTeal() {
        this.setHSV(180f, MAX_SATURATION, 50f);
    }

    public void asNavy() {
        this.setHSV(240f, MAX_SATURATION, 50f);
    }

    // GETTERS
    public Float getHue() {
        return hue;
    }

    public Float getSaturation() {
        return saturation;
    }

    public Float getBrightness() {
        return brightness;
    }

    public int getColor() {
        return Color.HSVToColor(new float[]{hue, saturation, brightness});
    }

    // SETTERS
    public void setHue(Float hue) {
        this.hue = hue;

        this.updateObservers();
        this.getColor();
    }

    public void setSaturation(Float saturation) {
        this.saturation = saturation;

        this.updateObservers();
        this.getColor();
    }

    public void setBrightness(Float brightness) {
        this.brightness = brightness;

        this.updateObservers();
        this.getColor();
    }

    // Convenient setter:
    public void setHSV(Float hue, Float saturation, Float brightness) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;

        this.updateObservers();
    }

    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public String toString() {
        return "HSV: [Hue=" + this.hue + "\u00B0\nSaturation=" + this.saturation + "%\nValue=" + this.brightness + "%]";
    }

}