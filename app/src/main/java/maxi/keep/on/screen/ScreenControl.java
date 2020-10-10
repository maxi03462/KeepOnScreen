package maxi.keep.on.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.widget.Toast;

import static android.content.Context.POWER_SERVICE;

public class ScreenControl extends BroadcastReceiver {
    private static WakeLock wakeLock;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            releaseWakeLock(context);
            System.out.println("BR SCREEN TURNED OFF");
        }
    }

    static public void activarPantallaEncendida(Context context) {
        PowerManager powerMgr = (PowerManager) context.getSystemService(POWER_SERVICE);
        obtainWakeLock(powerMgr, context);
    }

    static public void desactivarPantallaEncendida(Context context) {
        releaseWakeLock(context);
    }

    static private synchronized void obtainWakeLock(PowerManager powerMgr, Context context) {
        if (wakeLock == null) {
            System.out.println("Screen On");
            wakeLock = powerMgr.newWakeLock(
                    PowerManager.SCREEN_DIM_WAKE_LOCK,
                    "KeepOnScreen:wakelock");
            wakeLock.acquire();
            Toast.makeText(context.getApplicationContext(), "La pantalla se mantendra encendidad hasta que sea bloqueada manualmente", Toast.LENGTH_SHORT).show();
        }
    }

    static private synchronized void releaseWakeLock(Context context) {
        if (wakeLock != null) {
            if (wakeLock.isHeld()) {
                System.out.println("Screen Off");
                Toast.makeText(context.getApplicationContext(), "La pantalla se comporta normalmente", Toast.LENGTH_SHORT).show();
                try {
                    wakeLock.release();
                } catch (Exception e) {
                    System.out.println("Error mientras se soltaba");
                }
            }
            wakeLock = null;
        }
    }
}

