package com.notif.app;

import static android.graphics.Color.RED;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button;
    String CHANNEL_ID = "default";
    String CHANNEL_NOTIF = "Your Phone linging";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.notif);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notif();
            }

            private void notif() {
                // menampilkan notifikasi dasar
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("Tes Notif")
                        .setContentText("Isi Notif")
                        // text panjang
                        .setStyle(new NotificationCompat.BigTextStyle(). bigText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce mollis sollicitudin mi tincidunt bibendum. Fusce at ornare arcu, non vehicula justo. Morbi id dui leo. Sed in orci enim. Morbi consequat et metus a lacinia. Praesent porta iaculis porta. Donec dolor lorem, pellentesque at iaculis et, viverra sit amet eros. Quisque quis pellentesque tellus.\n" +
                                "\n" +
                                "Nunc at magna congue arcu porta congue. Proin sit amet luctus velit. Etiam sit amet leo eros. Donec felis eros, fermentum eu ultricies at, condimentum sed leo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nulla at tempus erat. Quisque nisi tortor, tempus a tortor sed, semper dictum eros. In vulputate, augue ac cursus efficitur, leo lectus consequat est, at pellentesque dui felis vel nunc. Aliquam a pellentesque dui. Aenean auctor tincidunt metus ut interdum."))
                        // set prioritas
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                // kode yg harus digunakan pada android versi Oreo ke atas
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_HIGH;
                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_NOTIF, "contoh channel", importance);
                    notificationChannel.enableLights(true);
                    builder.setChannelId(CHANNEL_NOTIF);
                    assert notificationManager != null;
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                assert notificationManager != null;
                notificationManager.notify((int) System.currentTimeMillis(), builder.build());
            }
        });
    }
}