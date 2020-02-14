package com.example.erdem.restoransiparis;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class KayitOl extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText uyeEmail,uyeParola;
    private String Mail,Sifre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        auth=FirebaseAuth.getInstance();

        uyeEmail=findViewById(R.id.editText2_kayitemail);
        uyeParola=findViewById(R.id.editText_kayitsifre);

        Button yeniUyeButton = findViewById(R.id.button2_kayitol);
        yeniUyeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String email = uyeEmail.getText().toString();
                String parola = uyeParola.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Lütfen emailinizi giriniz",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(parola)){
                    Toast.makeText(getApplicationContext(),"Lütfen parolanızı giriniz",Toast.LENGTH_SHORT).show();
                }
                if (parola.length()<6){
                    Toast.makeText(getApplicationContext(),"Parola en az 6 haneli olmalıdır",Toast.LENGTH_SHORT).show();
                }

                //FirebaseAuth ile email,parola parametrelerini kullanarak yeni bir kullanıcı oluşturuyoruz.
                auth.createUserWithEmailAndPassword(email,parola)
                        .addOnCompleteListener(KayitOl.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //İşlem başarısız olursa kullanıcıya bir Toast mesajıyla bildiriyoruz.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(KayitOl.this, "Yetkilendirme Hatası",
                                            Toast.LENGTH_SHORT).show();
                                }
                                //İşlem başarılı olduğu takdir de giriş yapılıp MainActivity e yönlendiriyoruz.
                                else {
                                    startActivity(new Intent(KayitOl.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });

    }

    //geri tuşuna basılma durumunu yakalıyoruz
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent i = new Intent(KayitOl.this,FirstScreen.class);
            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }


}
