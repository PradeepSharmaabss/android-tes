package developmental.warlocks.Shop;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.developmental.warlocks.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

import Spells.LoadOutInfo;
import Spells.SpellType;
import developmental.warlocks.Global;

/**
 * Created by Scott on 18/08/13.
 */
public class ShopActivity extends Activity {
    public ShopActivity() {
        super();

    }


    void Spells() {

        Global.spellList = new LoadOutInfo[9];
        Global.spellList[0] = new LoadOutInfo(SpellType.Fireball, 1);
        Global.spellList[1] = new LoadOutInfo(SpellType.Lightning, 1);
        Global.spellList[2] = new LoadOutInfo(SpellType.FireSpray, 1);
        Global.spellList[3] = new LoadOutInfo(SpellType.Meteor, 1);
        Global.spellList[4] = new LoadOutInfo(SpellType.Gravity, 1);
        Global.spellList[5] = new LoadOutInfo(SpellType.Bounce, 1);
        Global.spellList[6] = new LoadOutInfo(SpellType.Illusion, 1);
        Global.spellList[7] = new LoadOutInfo(SpellType.Illusion, 1);
        Global.spellList[8] = new LoadOutInfo(SpellType.Illusion, 1);


        loadState();

    }

    LoadOutInfo[] Slot1() {
        LoadOutInfo[] s = new LoadOutInfo[1];
        s[0] = new LoadOutInfo(SpellType.Fireball, 1);
        ShopActivity.SelectedIndex = 0;
        return s;
    }

    LoadOutInfo[] Slot2() {
        LoadOutInfo[] s = new LoadOutInfo[7];
        s[0] = new LoadOutInfo(SpellType.Lightning, 1);
        s[1] = new LoadOutInfo(SpellType.Homing, 1);
        s[2] = new LoadOutInfo(SpellType.Illusion, 1);
        s[3] = new LoadOutInfo(SpellType.Grenade, 1);
        s[4] = new LoadOutInfo(SpellType.Piercing, 1);
        s[5] = new LoadOutInfo(SpellType.Powerball, 1);
        s[6] = new LoadOutInfo(SpellType.Boomerang, 1);
        ShopActivity.SelectedIndex = 1;
        return s;
    }

    LoadOutInfo[] Slot3() {
        LoadOutInfo[] s = new LoadOutInfo[6];
        s[0] = new LoadOutInfo(SpellType.TrapMines, 1);
        s[1] = new LoadOutInfo(SpellType.IllusionBall, 1);
        s[2] = new LoadOutInfo(SpellType.Link, 1);
        s[3] = new LoadOutInfo(SpellType.Ice, 1);
        s[4] = new LoadOutInfo(SpellType.Gravity, 1);
        s[5] = new LoadOutInfo(SpellType.Meteor, 1);
        ShopActivity.SelectedIndex = 2;
        return s;
    }

    LoadOutInfo[] Slot4() {
        LoadOutInfo[] s = new LoadOutInfo[7];
        s[0] = new LoadOutInfo(SpellType.Absorb, 1);
        s[1] = new LoadOutInfo(SpellType.SonicWave, 1);
        s[2] = new LoadOutInfo(SpellType.FireSpray, 1);
        s[3] = new LoadOutInfo(SpellType.IceSpray, 1);
        s[4] = new LoadOutInfo(SpellType.Bounce, 1);
        s[5] = new LoadOutInfo(SpellType.Drain, 1);
        s[6] = new LoadOutInfo(SpellType.Splitter, 1);
        ShopActivity.SelectedIndex = 3;
        return s;
    }

    LoadOutInfo[] Slot5() {
        LoadOutInfo[] s = new LoadOutInfo[7];
        s[0] = new LoadOutInfo(SpellType.Teleport, 1);
        s[1] = new LoadOutInfo(SpellType.Swap, 1);
        s[2] = new LoadOutInfo(SpellType.Thrust, 1);
        s[3] = new LoadOutInfo(SpellType.JuggerNaught, 1);
        s[4] = new LoadOutInfo(SpellType.WindWalk, 1);
        s[5] = new LoadOutInfo(SpellType.Phase, 1);
        s[6] = new LoadOutInfo(SpellType.MiddleOfAction, 1);
        ShopActivity.SelectedIndex = 4;
        return s;
    }

    LoadOutInfo[] Slot6() {
        LoadOutInfo[] s = new LoadOutInfo[7];
        s[0] = new LoadOutInfo(SpellType.FireExplode, 1);
        s[1] = new LoadOutInfo(SpellType.IceExplode, 1);
        s[2] = new LoadOutInfo(SpellType.MagnetExplode, 1);
        s[3] = new LoadOutInfo(SpellType.DrainExplode, 1);
        s[4] = new LoadOutInfo(SpellType.Reflect, 1);
        s[5] = new LoadOutInfo(SpellType.Orbitals, 1);
        s[6] = new LoadOutInfo(SpellType.Root, 1);
        ShopActivity.SelectedIndex = 5;
        return s;
    }

    LoadOutInfo[] Slot7() {
        LoadOutInfo[] s = new LoadOutInfo[7];
        s[0] = new LoadOutInfo(SpellType.BurnAura, 1);
        s[1] = new LoadOutInfo(SpellType.HealAura, 1);
        s[2] = new LoadOutInfo(SpellType.Bezerk, 1);
        s[3] = new LoadOutInfo(SpellType.Fervour, 1);
        s[4] = new LoadOutInfo(SpellType.Boots, 1);
        s[5] = new LoadOutInfo(SpellType.HealthStone, 1);
        s[6] = new LoadOutInfo(SpellType.Shield, 1);
        ShopActivity.SelectedIndex = 6;
        return s;
    }
    LoadOutInfo[] Slot8(){
        LoadOutInfo[] ss = Slot7();
        ShopActivity.SelectedIndex=7;
        return ss;
    }
    LoadOutInfo[] Slot9(){
        LoadOutInfo[] ss = Slot7();
        ShopActivity.SelectedIndex=8;
        return ss;
    }

    public static LoadOutInfo[] e;
    public static int SelectedIndex = 0;

    void setstuff(LoadOutInfo[] se) {
        ArrayList<LoadOutInfo> v = new ArrayList<LoadOutInfo>();
        SpellsAdapter a = new SpellsAdapter(this, v);
        ShopActivity.e = se;
        for (LoadOutInfo g : se)
            v.add(g);
        final ListView q = ((ListView) findViewById(R.id.listView3));

        q.setAdapter(a);
        this.storedposition = -1;
    }

    int storedposition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_shop);
        Spells();

        ArrayList<LoadOutInfo> v = new ArrayList<LoadOutInfo>();

        SpellsAdapter a = new SpellsAdapter(this, v);
        setstuff(Slot1());
        for (LoadOutInfo g : e)
            v.add(g);
        final ListView q = ((ListView) findViewById(R.id.listView3));
        q.setAdapter(a);
        q.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                storedposition = position;
                TextView t = ((TextView) findViewById(R.id.spellContent));
                int i = LoadOutInfo.setDescription(ShopActivity.e[position].spellType);

                t.setText(i);
                TextView t2 = ((TextView) findViewById(R.id.Title));
                t2.setText(ShopActivity.e[position].spellType.toString());
            }
        });
        for (int i = 0; i < 9; i++) {
            SelectedIndex = i;
            changeIcon(i);
        }
        SelectedIndex = 0;
        View.OnClickListener j = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((android.widget.ImageButton) findViewById(R.id.spellslot1)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.spellslot2)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.spellslot3)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.spellslot4)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.spellslot5)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.spellslot6)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.spellslot7)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.spellslot8)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.spellslot9)).setBackgroundResource(R.drawable.shop_button);
                v.setBackgroundResource(R.drawable.shop_button_selected);
                switch (v.getId()) {
                    case R.id.spellslot1:
                        setstuff(Slot1());
                        break;
                    case R.id.spellslot2:

                        setstuff(Slot2());
                        break;
                    case R.id.spellslot3:
                        setstuff(Slot3());
                        break;
                    case R.id.spellslot4:
                        setstuff(Slot4());
                        break;
                    case R.id.spellslot5:
                        setstuff(Slot5());
                        ;
                        break;
                    case R.id.spellslot6:
                        setstuff(Slot6());
                        break;
                    case R.id.spellslot7:
                        setstuff(Slot7());
                        break;
                    case R.id.spellslot8:
                        setstuff(Slot8());
                        break;
                    case R.id.spellslot9:
                        setstuff(Slot9());
                        break;
                }
            }
        };
        ((ImageButton) findViewById(R.id.spellslot1)).setOnClickListener(j);
        ((android.widget.ImageButton) findViewById(R.id.spellslot2)).setOnClickListener(j);
        findViewById(R.id.spellslot3).setOnClickListener(j);
        findViewById(R.id.spellslot4).setOnClickListener(j);
        findViewById(R.id.spellslot5).setOnClickListener(j);
        findViewById(R.id.spellslot6).setOnClickListener(j);
        findViewById(R.id.spellslot7).setOnClickListener(j);
        findViewById(R.id.spellslot8).setOnClickListener(j);
        findViewById(R.id.spellslot9).setOnClickListener(j);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storedposition != -1)
                    Global.spellList[SelectedIndex].SetOrIncrement(ShopActivity.e[storedposition].spellType);
                changeIcon(SelectedIndex);
            }
        });
        ((Button) findViewById(R.id.button8)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((Button) findViewById(R.id.confirmbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveLoadout();
                //  Intent intent = new Intent(ShopActivity.this,OpenGLTestActivity.class);
                finish();
            }
        });
    }

    private void saveState() {
        FileOutputStream outStream = null;
        try {
            File f = new File(Environment.getExternalStorageDirectory(), "/data.dat");
            outStream = new FileOutputStream(f);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);


            objectOutStream.writeObject(Global.spellList);
            objectOutStream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public static void loadState() {
        LoadOutInfo[] s = null;
        FileInputStream inStream = null;
        try {
            File f = new File(Environment.getExternalStorageDirectory(), "/data.dat");
            inStream = new FileInputStream(f);
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);

            s = ((LoadOutInfo[]) objectInStream.readObject());
            objectInStream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (OptionalDataException e1) {
            e1.printStackTrace();
        } catch (StreamCorruptedException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Log.e("LOADING LOADOUT",s.length+"");
        if (s != null)
            for (int i = 0; i < s.length && i < 9; i++) {
                Global.spellList[i] = s[i];
                Log.e("LOADING LOADOUT",i+" " + s[i].spellType+"");

            }
        else {
            Log.e("FAILED TO LOAD", "YOU SUCK!!!!");
        }
        for(LoadOutInfo l:Global.spellList)
        {
            Log.e("LOADING LOADOUT",l.spellType+"");
        }
    }

    private void SaveLoadout() {
        for(LoadOutInfo l:Global.spellList)
        {
            Log.e(" SAVING LOADOUT",l.spellType+"");
        }
        saveState();
    }

    public static byte[] SerializetoBytes(LoadOutInfo[] gameObjects) {

        try {

            ByteArrayOutputStream fileOut =
                    new ByteArrayOutputStream();
            ObjectOutputStream out =
                    new ObjectOutputStream(fileOut);

            out.writeObject(gameObjects);
            return fileOut.toByteArray();

        } catch (IOException i) {
            i.printStackTrace();
        }
        return null;
    }

    void changeIcon(int i) {
        ImageView b = null;
        TextView t = null;
        switch (i) {
            case 0:
                t = (TextView) findViewById(R.id.textView1);
                b = (ImageView) findViewById(R.id.imageView1);
                break;
            case 1:
                t = (TextView) findViewById(R.id.textView2);
                b = (ImageView) findViewById(R.id.imageView2);
                break;
            case 2:
                t = (TextView) findViewById(R.id.textView3);
                b = (ImageView) findViewById(R.id.imageView3);
                break;
            case 3:
                t = (TextView) findViewById(R.id.textView4);
                b = (ImageView) findViewById(R.id.imageView4);
                break;
            case 4:
                t = (TextView) findViewById(R.id.textView5);
                b = (ImageView) findViewById(R.id.imageView5);
                break;
            case 5:
                t = (TextView) findViewById(R.id.textView6);
                b = (ImageView) findViewById(R.id.imageView6);
                break;
            case 6:
                t = (TextView) findViewById(R.id.textView7);
                b = (ImageView) findViewById(R.id.imageView7);
                break;
            case 7:
                t = (TextView) findViewById(R.id.textView9);
                b = (ImageView) findViewById(R.id.imageView);
                break;
            case 8:
                t = (TextView) findViewById(R.id.textView10);
                b = (ImageView) findViewById(R.id.imageView8);
                break;

        }


        b.setBackgroundResource(LoadOutInfo.setResource(Global.spellList[SelectedIndex].spellType));
        t.setText("Rank:" + Global.spellList[SelectedIndex].Rank);

    }

}
