package trico.android.wifi.test;

import trico.android.wifi.PowerStateReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.test.AndroidTestCase;

/**
 * @author nico.mainka
 * 
 */
public class PowerStateReceiverTest extends AndroidTestCase {

	private PowerStateReceiver receiver;

	private WifiManager wifiManager;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		receiver = new PowerStateReceiver();
		wifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
	}

	public void testOnReceive_wifiDisabledActionPowerConnected_success() throws Exception {
		receiver.onReceive(getContext(), new Intent(Intent.ACTION_POWER_CONNECTED));
	}

}
