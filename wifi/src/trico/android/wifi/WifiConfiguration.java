package trico.android.wifi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/**
 * @author nico.mainka
 * 
 */
public class WifiConfiguration extends Activity {

	private WifiPrefernces wifiPrefernces;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		wifiPrefernces = WifiPrefernces.createFrom(this);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.configuration);

		final CheckBox checkbox = (CheckBox) findViewById(R.id.checkBox1);
		listenCheckBox(checkbox);

		// initialize check-box with stored value
		checkbox.setChecked(wifiPrefernces.isAutoWifiOnPower());
	}

	void listenCheckBox(final CheckBox checkbox) {
		checkbox.setOnClickListener(new OnClickListener() {
			public void onClick(final View v) {
				final CheckBox checkBox = (CheckBox) v;
				wifiPrefernces.setAutoWifiOnPower(checkBox.isChecked());
			}
		});
	}
}
