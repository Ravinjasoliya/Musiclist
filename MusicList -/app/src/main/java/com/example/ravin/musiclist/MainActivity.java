package com.example.ravin.musiclist;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Cursor cursor;
    ListView listView;
     MediaPlayer mp;
   static ArrayList<String> songs;
   static ArrayList<String> list;
   //  int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.lv1);
    //    mp = new MediaPlayer();

        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION
        };


        cursor = this.managedQuery(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,//projection
                null,//selection
                null,
                null);

          songs = new ArrayList<String>();
          list = new ArrayList<String>();
        while(cursor.moveToNext())
        {
            songs.add(cursor.getString(3) + "");
            list.add(cursor.getString(2) + "");
                   /* + cursor.getString(1) + "||"
                    + cursor.getString(2) + "||"
                    + cursor.getString(3) + "||"
                    + cursor.getString(4) + "||"
                    + cursor.getString(5));*/
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {

                try {
                   /* mp.reset();
                    mp.setDataSource(songs.get(position));
                    mp.prepare();
                    mp.start();*/

                    Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                    intent.putExtra("name",list.get(position));
                    intent.putExtra("path",songs.get(position));
                    intent.putExtra("pos",position);
                    startActivity(intent);

                 /*   mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {

                            try{

                                mp.reset();
                                mp.setDataSource(songs.get(position+1));
                                mp.prepare();
                                mp.start();


                            }catch (Exception e)
                            {

                            }
                        }
                    });*/
                    // Setup listener so next song starts automatically
                }catch (Exception e) {
                    Log.v(getString(R.string.app_name), e.getMessage());
                }

            }
        });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("main");
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("main"))
        {
            startActivity(new Intent(MainActivity.this,Main3Activity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
