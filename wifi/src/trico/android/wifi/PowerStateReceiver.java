package trico.android.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * This class acts as {@link BroadcastReceiver} for device power connection.
 * 
 * @author nico.mainka
 * 
 */
public class PowerStateReceiver extends BroadcastReceiver {

	/** tab for logging. */
	private static final String TAG = PowerStateReceiver.class.getSimpleName();

	private WifiManager wifiManager;

	@Override
	public void onReceive(final Context context, final Intent intent) {
		if (!WifiPrefernces.createFrom(context).isAutoWifiOnPower()) {
			return;
		}
		ensureWifiManager(context);

		switch (PowerConnectedType.fromIntent(intent)) {
		case DISCONNECTED:
			wifiManager.setWifiEnabled(false);
			Log.d(TAG, "disabling wifi...");
			break;
		case CONNECTED:
			wifiManager.setWifiEnabled(true);
			Log.d(TAG, "enabling wifi...");
			break;
		default:
			Log.i(TAG, "unkown/unhandled action: " + intent.getAction());
			break;
		}
	}

	private void ensureWifiManager(final Context context) {
		if (wifiManager == null) {
			wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		}
	}

	boolean isPowerDisconnected(final String action) {
		return Intent.ACTION_POWER_DISCONNECTED.equalsIgnoreCase(action);
	}

	boolean isPowerConnected(final String action) {
		return Intent.ACTION_POWER_CONNECTED.equalsIgnoreCase(action);
	}

	private static enum PowerConnectedType {
		/** */
		CONNECTED(Intent.ACTION_POWER_CONNECTED),

		/** */
		DISCONNECTED(Intent.ACTION_POWER_DISCONNECTED),

		/** */
		UNKNOWN("unkown");

		private String intentAction;

		private PowerConnectedType(final String intentAction) {
			this.intentAction = intentAction;
		}

		static PowerConnectedType fromIntent(final Intent intent) {
			final String action = intent.getAction();
			for (final PowerConnectedType type : values()) {
				if (String.valueOf(action).equalsIgnoreCase(type.intentAction)) {
					return type;
				}
			}
			return UNKNOWN;
		}

	}
}
