package developmental.warlocks.GL;

/**
 * Created by Scott on 5/01/14.
 */
/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.developmental.warlocks.R;

import developmental.warlocks.Shop.ShopActivity;

/**
 * Main entry point for the SpriteMethodTest application.  This application
 * provides a simple interface for testing the relative speed of 2D rendering
 * systems available on Android, namely the Canvas system and OpenGL ES.  It
 * also serves as an example of how SurfaceHolders can be used to create an
 * efficient rendering thread for drawing.
 */
public class SpriteMethodTest extends Activity {
    private static final int ACTIVITY_TEST = 0;
    private static final int RESULTS_DIALOG = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        // Sets up a click listener for the Run Test button.
        Button button;
        button = (Button) findViewById(R.id.runTest);
        button.setOnClickListener(mRunTestListener);

        // Turns on one item by default in our radio groups--as it should be!


    }

    /** Passes preferences about the test via its intent. */
    protected void initializeIntent(Intent i) {

        final EditText editText = (EditText) findViewById(R.id.spriteCount);
        final String spriteCountText = editText.getText().toString();
        final int stringCount = Integer.parseInt(spriteCountText);
        ShopActivity.loadState();
        i.putExtra("animate", true);
        i.putExtra("spriteCount", stringCount);

    }

    /**
     * Responds to a click on the Run Test button by launching a new test
     * activity.
     */
    OnClickListener mRunTestListener = new OnClickListener() {
        public void onClick(View v) {

            Intent i;

                i = new Intent(v.getContext(), OpenGLTestActivity.class);

            i.putExtra("useVerts", true);
            i.putExtra("useHardwareBuffers", true);
            initializeIntent(i);
            startActivityForResult(i, ACTIVITY_TEST);
        }
    };

    /**
     * Enables or disables OpenGL ES-specific settings controls when the render
     * method option changes.
     */



    /** Shows the results dialog when the test activity closes. */
   @Override
   protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
switch (requestCode)
{
    case ACTIVITY_TEST:
       if(resultCode==RESULT_OK)
       {

       }
        break;
    default:
    break;
}
    }

}