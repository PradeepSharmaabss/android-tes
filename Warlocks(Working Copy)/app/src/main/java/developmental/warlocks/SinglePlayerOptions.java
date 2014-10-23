package developmental.warlocks;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.developmental.warlocks.R;

import developmental.warlocks.Shop.NewShopActivity;
import World.Level;
import developmental.warlocks.GL.Mover;
import developmental.warlocks.GL.SimpleGLRenderer;

public class SinglePlayerOptions extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleplayeroption);

        Button B7 = (Button) findViewById(R.id.singleplayerbeginbutton);
        B7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                RadioGroup r = (RadioGroup) findViewById(R.id.radioOptions);
                Level.LevelShape l = null;
                switch (r.getCheckedRadioButtonId()) {
                    case R.id.radioButton:
                        //  SimpleGLRenderer.gameObjects.clear();

                        l = Level.LevelShape.Ellipse;
                        //  SimpleGLRenderer.loaded = false;
                        break;
                    case R.id.radioButton2:
                        //   SimpleGLRenderer.gameObjects.clear();

                        l = Level.LevelShape.Rectangle;
                        // SimpleGLRenderer.loaded = false;
                        break;
                    case R.id.radioButton3:
                        //   SimpleGLRenderer.gameObjects.clear();

                        l = Level.LevelShape.Donut;
                        //  SimpleGLRenderer.loaded = false;
                        break;
                }
                Switch s = (Switch) findViewById(R.id.debug);
                Global.DEBUG_MODE = s.isChecked();
                s = (Switch) findViewById(R.id.lefthandmode);
                Global.LEFT_HAND_MODE = s.isChecked();

                Log.d("STARTING SINGLE PLAYER GAME!", " ");
                Global.Multiplayer = false;
                startGame(l);
            }
        });
    }
    void startGame(Level.LevelShape _l) {
        Mover.Gamestep=0;

        // set our MainGamePanel as the
        Log.e("TESTING PURPOSES",_l + " ");
        SimpleGLRenderer.SetLevelShape(_l);


         Intent intent = new Intent(SinglePlayerOptions.this, NewShopActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.single_player_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_single_player_options, container, false);
            return rootView;
        }
    }

}
