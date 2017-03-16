package com.example.ravin.musiclist;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    TextView  textView;
    MediaPlayer mediaPlayer;
    String path;
   static int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView=(TextView)findViewById(R.id.txt2);
        textView.setText(getIntent().getStringExtra("name"));
        path=getIntent().getStringExtra("path");
        pos= getIntent().getIntExtra("pos",0);
        mediaPlayer=new MediaPlayer();

      final  MainActivity mainActivity=new MainActivity();
        try {


            mediaPlayer.reset();
            mediaPlayer.setDataSource(mainActivity.songs.get(pos));
            mediaPlayer.prepare();
            mediaPlayer.start();

          //  Log.e("path=",mainActivity.songs.get(pos));

           mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    try
                    {

                        mediaPlayer.reset();
                        mp.reset();
                        pos++;
                        mp.setDataSource(mainActivity.songs.get(pos));
                        mp.prepare();
                        mp.start();
                        textView.setText(mainActivity.list.get(pos));

                    }catch (Exception e)
                    {

                        Toast.makeText(Main3Activity.this, "exceptin", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }catch (Exception e)
        {

        }

    }
}
