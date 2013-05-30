package com.example.warlockgame;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Scott on 5/30/13.
 */
public class SoundHandler {
public SoundHandler(Context context)
{
    MediaPlayer mp;
    mp = MediaPlayer.create(context, 1/*R.raw.combo*/);
    mp.start();

}
}
