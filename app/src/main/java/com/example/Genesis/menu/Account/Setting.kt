package com.example.Genesis.menu.Account

import android.media.MediaPlayer
import android.media.ToneGenerator.MAX_VOLUME
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.Genesis.R


class Setting : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_screen)
        var musicBar = findViewById<SeekBar>(R.id.musicBar)
        var saveButton = findViewById<Button>(R.id.saveButton)
        mediaPlayer = MediaPlayer.create(this, R.raw.yousayrun);
        mediaPlayer.start();
        val log1 = (Math.log((Setting.maxVolume - Setting.currentVolume).toDouble()) / Math.log(Setting.maxVolume.toDouble())).toFloat()
        mediaPlayer.setVolume(log1, log1) //set volume takes two paramater

        saveButton.setOnClickListener(){
                Setting.currentVolume  =  musicBar.progress
                val log1 = (Math.log((Setting.maxVolume - (100 - Setting.currentVolume) + 1).toDouble()) / Math.log(Setting.maxVolume.toDouble())).toFloat()
                mediaPlayer.setVolume(log1, log1) //set volume takes two paramater
        }
        musicBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                val log1 = (Math.log((Setting.maxVolume - (100 - progress) + 1).toDouble()) / Math.log(Setting.maxVolume.toDouble())).toFloat()
                mediaPlayer.setVolume(log1, log1) //set volume takes two paramater
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    companion object{
        var currentVolume : Int = 0
        var maxVolume : Int = 100
    }
}
