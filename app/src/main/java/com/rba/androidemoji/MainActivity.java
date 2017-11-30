package com.rba.androidemoji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rba.emoji.EmojiTextView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    EmojiTextView tvEmoji;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvEmoji = (EmojiTextView) findViewById(R.id.tv_emoji);
        setSupportActionBar(toolbar);

        tvEmoji.setText(Constant.DEFAULT);
    }

}