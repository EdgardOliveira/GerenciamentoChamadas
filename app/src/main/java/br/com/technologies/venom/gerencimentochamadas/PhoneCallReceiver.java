package br.com.technologies.venom.gerencimentochamadas;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class PhoneCallReceiver extends BroadcastReceiver {

    private final String TAG = "Chamadas>>>";
    private Context context;
    private String status;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive: Broadcast iniciado...");

        this.context = context;
        status = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        switch (status) {
            case "RINGING":
                Log.d(TAG, "onReceive: você está recebendo chamada telefônica...");
                atenderChamada();
                break;
            case "OFFHOOK":
                Log.d(TAG, "onReceive: você está em uma chamada telefônica...");
                break;
            case "IDLE":
                Log.d(TAG, "onReceive: esperando chamadas...");
                break;
        }
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void atenderChamada(){
        Log.d(TAG, "atenderChamada: Atender chamada...");

        TelecomManager telecomManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
        }

        if (telecomManager == null) {
            throw new NullPointerException("tm == null");
        }

        //aceitar a ligação
        telecomManager.acceptRingingCall();
        Log.d(TAG, "atenderChamada: atendemos a ligação automaticamente quando o sensor atingiu o setpoint...");

        //exibir o gerenciador de chamadas na tela
        telecomManager.showInCallScreen(true);
    }
}
