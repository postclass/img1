package com.example.img1;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        try (InputStream is = getResources().getAssets().open("sv1.svg")) {
//            Drawable d = getApplicationContext().getDrawable(R.drawable.sv1);
            Drawable d = getResources().getDrawable(R.drawable.ic_sv1);
//            Bitmap bm = BitmapFactory.decodeStream(is);
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageDrawable(d);

        } catch (IOException e) {
            Log.d("Assets", "Error");
        }

//        MediaPlayer mp = MediaPlayer.create(this, R.raw.danse);

        try (AssetFileDescriptor fd =getResources().getAssets().openFd("danse.mid")) {
            MediaPlayer mp = new MediaPlayer();
            mp.setDataSource(fd.getFileDescriptor());
            mp.prepare();
            mp.setLooping(false);
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);


//        Button btn = (Button) findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                TextView t = (TextView) findViewById(R.id.textView);
//                t.setText("bbbb");
//            }
//        });


    }


    public void aaa(View view) {
        TextView t = (TextView) findViewById(R.id.textView);
        t.setText("aaaaa");

        try (InputStream is = getResources().getAssets().open("bb.png")) {
            Bitmap bm = BitmapFactory.decodeStream(is);
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(bm);
        } catch (IOException e) {
            Log.d("Assets", "Error");
        }
    }
}
