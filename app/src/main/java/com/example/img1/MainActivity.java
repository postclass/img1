package com.example.img1;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private static final int MAX = 2;
    private int sps[] = new int[MAX];
    private SoundPool sp;
    private int current = 0;


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

        /*
        try (AssetFileDescriptor fd =getResources().getAssets().openFd("danse.mid")) {
            MediaPlayer mp = new MediaPlayer();
            mp.setDataSource(fd.getFileDescriptor());
            mp.prepare();
            mp.setLooping(false);
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        AudioAttributes aa = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        this.sp = new SoundPool.Builder()
                .setAudioAttributes(aa)
                .setMaxStreams(MAX)
                .build();

        this.sps[0] = this.sp.load(getApplicationContext(), R.raw.c, 1);
        this.sps[1] = this.sp.load(getApplicationContext(), R.raw.danse, 1);



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


        this.sp.play(this.sps[current++], 1,1,0,0,1);
        if (current == MAX) {
            current = 0;
        }
    }
}
