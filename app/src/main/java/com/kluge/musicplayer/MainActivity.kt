package com.kluge.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaplayer = MediaPlayer.create(this, R.raw.music)

      val play = findViewById<ImageButton>(R.id.play_btn)
        play.setOnClickListener{
            if(!mediaplayer.isPlaying){
                mediaplayer.start()
                play.setImageResource(R.drawable.ic_baseline_pause_24)
            }else{
                mediaplayer.pause()
                play.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }
      }

    }
}