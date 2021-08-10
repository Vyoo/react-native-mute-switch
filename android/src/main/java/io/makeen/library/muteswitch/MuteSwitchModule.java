// MuteSwitchModule.java

package io.makeen.library.muteswitch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class MuteSwitchModule extends ReactContextBaseJavaModule {

    private AudioManager am;

    private final ReactApplicationContext reactContext;

    private String status = "unknown";

    public MuteSwitchModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }


    @Override
    public void initialize() {
        super.initialize();

        this.am = (AudioManager) reactContext.getSystemService(Context.AUDIO_SERVICE);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (am.getRingerMode()) {
                    case AudioManager.RINGER_MODE_SILENT:
                        updateStatus("silent");
                        break;
                    case AudioManager.RINGER_MODE_VIBRATE:
                        updateStatus("vibrate");
                        break;
                    case AudioManager.RINGER_MODE_NORMAL:
                        updateStatus("normal");
                        break;
                }
            }
        };
        IntentFilter filter = new IntentFilter(AudioManager.RINGER_MODE_CHANGED_ACTION);
        reactContext.registerReceiver(receiver, filter);
    }

    @Override
    public String getName() {
        return "MuteSwitch";
    }

    private void updateStatus(String status) {
        if (this.status.equals(status)) {
            return;
        }
        this.status = status;

        WritableMap params = Arguments.createMap();
        params.putString("status", status);

        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("MuteSwitch_change", params);
    }
}
