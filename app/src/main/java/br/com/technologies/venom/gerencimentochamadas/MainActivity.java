package br.com.technologies.venom.gerencimentochamadas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PHONE_STATUS = 1000;
    private static final int REQUEST_CODE_ANSWER_PHONE_CALL = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verificarPermissoes();
    }

    private void verificarPermissoes() {
        //Permissão para ler os status do telefone
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {

                //A permissão ainda não foi concedida, então irá perguntar ao usuários se ele deseja dar a permissão
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        REQUEST_CODE_PHONE_STATUS);
            }
        }

        //Permissão para responder chamadas
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.ANSWER_PHONE_CALLS)
                    != PackageManager.PERMISSION_GRANTED) {
                //A permissão ainda não foi concedida, então irá perguntar ao usuários se ele deseja dar a permissão
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ANSWER_PHONE_CALLS},
                        REQUEST_CODE_ANSWER_PHONE_CALL);
            }
        }
    }
}