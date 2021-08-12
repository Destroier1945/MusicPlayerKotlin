package com.kluge.musicplayer

import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCenter.start(
            application, "14f7c93d-414d-41d7-a62c-2ef922fed827",
            Analytics::class.java, Crashes::class.java
        )

        val mediaplayer = MediaPlayer.create(this, R.raw.music)

        val seekbar = findViewById<SeekBar>(R.id.seekbar)
        seekbar.progress = 0

        seekbar.max = mediaplayer.duration

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
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, changed: Boolean) {
                if (changed)
                    mediaplayer.seekTo(p1)

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        runnable = Runnable {
            seekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaplayer.setOnCompletionListener {
            findViewById<ImageButton>(R.id.play_btn).setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seekbar.progress = 0

        }

    }
}