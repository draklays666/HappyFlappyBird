package io.github.some_example_name.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameSettings {
    private static final String PREFS_NAME = "HappyFlappyBirdPrefs";
    private static final String KEY_SELECTED_SKIN = "selectedSkin";

    private static Preferences prefs = Gdx.app.getPreferences(PREFS_NAME);

    public static void setSelectedSkin(String skinName) {
        prefs.putString(KEY_SELECTED_SKIN, skinName);
        prefs.flush();
    }

    public static String getSelectedSkin() {
        return prefs.getString(KEY_SELECTED_SKIN, "default");
    }
}
