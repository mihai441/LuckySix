package com.luckysix.Saved;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by AsX on 10/2/2017.
 */

public class PreferenceMenu {

        private Preferences preference;
        private static final String PREF_SOUND_ENABLED = "soundenabled";
        private static final String PREFS_NAME = "luckySix";

        public PreferenceMenu() {
            getPrefs();
        }

        protected Preferences getPrefs() {
            if(preference==null){
                preference = Gdx.app.getPreferences(PREFS_NAME);
            }
            return preference;
        }

        public int getCredit(){
            return preference.getInteger("Credit",5000);
        }

        public void setCredit(int x){
            preference.putInteger("Credit",x);
            preference.flush();

    }
        public boolean isSoundEffectsEnabled() {
            return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
        }

        public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
            getPrefs().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
            getPrefs().flush();
        }
}
