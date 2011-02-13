package trico.android.wifi;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;

/**
 * @author nico.mainka
 * 
 */
class WifiPrefernces {

	private static final String AUTO_WIFI_POWER_PROP = "auto.wifi.power";

	private static final String TRICO_ANDROID_WIFI_PREFS = "trico.android.wifi.prefs";

	private SharedPreferences sharedPreferences;

	private WifiPrefernces(final SharedPreferences sharedPreferences) {
		this.sharedPreferences = sharedPreferences;
	}

	static WifiPrefernces createFrom(final ContextWrapper contextWrapper) {
		final WifiPrefernces prefernces = new WifiPrefernces(contextWrapper.getSharedPreferences(
				TRICO_ANDROID_WIFI_PREFS, Context.MODE_WORLD_WRITEABLE));
		return prefernces;
	}

	static WifiPrefernces createFrom(final Context context) {
		final WifiPrefernces prefernces = new WifiPrefernces(context.getSharedPreferences(TRICO_ANDROID_WIFI_PREFS,
				Context.MODE_WORLD_WRITEABLE));
		return prefernces;
	}

	boolean isAutoWifiOnPower() {
		return sharedPreferences.getBoolean(AUTO_WIFI_POWER_PROP, false);
	}

	void setAutoWifiOnPower(final boolean auto) {
		sharedPreferences.edit().putBoolean(AUTO_WIFI_POWER_PROP, auto).commit();
	}

}
