package com.example.erdem.restoransiparis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        Button giris = findViewById(R.id.giris_yol);

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FirstScreen.this,Login.class);
                startActivity(i);
                finish();
            }
        });

        Button kayitol = findViewById(R.id.kayitol_yol);

        kayitol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FirstScreen.this,KayitOl.class);
                startActivity(i);
                finish();
            }
        });


    }

    //geri tuşuna basılma durumunu yakalıyoruz
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(FirstScreen.this,AlertDialog.THEME_HOLO_DARK);
            alert.setTitle("Çıkmak İstediğinizden Emin misiniz ?");
            alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    finish();
                }
            });
            alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
            alert.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
