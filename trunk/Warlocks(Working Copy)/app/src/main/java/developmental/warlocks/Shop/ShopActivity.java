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

import java.io.ByteArrayInputStream;
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

import Spells.SpellInfo;
import Spells.SpellType;
import developmental.warlocks.Global;

/**
 * Created by Scott on 18/08/13.
 */
public class ShopActivity extends Activity {
    public ShopActivity() {
        super();

    }




    void Spells()
    {

        Global.spellList = new SpellInfo[7];
        Global.spellList[0] = new SpellInfo(SpellType.Fireball,1);
        Global.spellList[1] = new SpellInfo(SpellType.Lightning,1);
        Global.spellList[2] = new SpellInfo(SpellType.FireSpray,1);
        Global.spellList[3] = new SpellInfo(SpellType.Meteor,1);
        Global.spellList[4] = new SpellInfo(SpellType.Gravity,1);
        Global.spellList[5] = new SpellInfo(SpellType.Bounce,1);
        Global.spellList[6] = new SpellInfo(SpellType.Illusion,1);



        loadState();


    }
    SpellInfo[] Slot1()
    {
        SpellInfo[] s= new SpellInfo[1];
        s[0] = new SpellInfo(SpellType.Fireball,1);
        ShopActivity.SelectedIndex =0;
        return s;
    }
    SpellInfo[] Slot2()
    {
        SpellInfo[] s= new SpellInfo[6];
        s[0] = new SpellInfo(SpellType.Lightning,1);
        s[1] = new SpellInfo(SpellType.Homing,1);
        s[2] = new SpellInfo(SpellType.Illusion,1);
        s[3] = new SpellInfo(SpellType.Grenade,1);
        s[4] = new SpellInfo(SpellType.Piercing,1);
        s[5] = new SpellInfo(SpellType.Powerball,1);
        ShopActivity.SelectedIndex =1;
        return s;
    }
    SpellInfo[] Slot3()
    {
        SpellInfo[] s= new SpellInfo[6];
        s[0] = new SpellInfo(SpellType.TrapMines,1);
        s[1] = new SpellInfo(SpellType.IllusionBall,1);
        s[2] = new SpellInfo(SpellType.Link,1);
        s[3] = new SpellInfo(SpellType.Ice,1);
        s[4] = new SpellInfo(SpellType.Gravity,1);
        s[5] = new SpellInfo(SpellType.Meteor,1);
        ShopActivity.SelectedIndex =2;
        return s;
    }
    SpellInfo[] Slot4()
    {
        SpellInfo[] s= new SpellInfo[6];
        s[0] = new SpellInfo(SpellType.Splitter,1);
        s[1] = new SpellInfo(SpellType.SonicWave,1);
        s[2] = new SpellInfo(SpellType.FireSpray,1);
        s[3] = new SpellInfo(SpellType.IceSpray,1);
        s[4] = new SpellInfo(SpellType.Bounce,1);
        s[5] = new SpellInfo(SpellType.Drain,1);
        ShopActivity.SelectedIndex =3;
        return s;
    }
    SpellInfo[] Slot5()
    {
        SpellInfo[] s= new SpellInfo[6];
        s[0] = new SpellInfo(SpellType.Teleport,1);
        s[1] = new SpellInfo(SpellType.Swap,1);
        s[2] = new SpellInfo(SpellType.Thrust,1);
        s[3] = new SpellInfo(SpellType.JuggerNaught,1);
        s[4] = new SpellInfo(SpellType.WindWalk,1);
        s[5] = new SpellInfo(SpellType.Phase,1);
        ShopActivity.SelectedIndex =4;
        return s;
    }
    SpellInfo[] Slot6()
    {
        SpellInfo[] s= new SpellInfo[6];
        s[0] = new SpellInfo(SpellType.FireExplode,1);
        s[1] = new SpellInfo(SpellType.IceExplode,1);
        s[2] = new SpellInfo(SpellType.MagnetExplode,1);
        s[3] = new SpellInfo(SpellType.DrainExplode,1);
        s[4] = new SpellInfo(SpellType.Reflect,1);
        s[5] = new SpellInfo(SpellType.Orbitals,1);
        ShopActivity.SelectedIndex =5;
        return s;
    }
    SpellInfo[] Slot7()
    {
        SpellInfo[] s= new SpellInfo[6];
        s[0] = new SpellInfo(SpellType.BurnAura,1);
        s[1] = new SpellInfo(SpellType.HealAura,1);
        s[2] = new SpellInfo(SpellType.Bezerk,1);
        s[3] = new SpellInfo(SpellType.Fervour,1);
        s[4] = new SpellInfo(SpellType.Boots,1);
        s[5] = new SpellInfo(SpellType.Illusion,1);
        ShopActivity.SelectedIndex =6;
        return s;
    }
    public static SpellInfo [] e;
    public static int SelectedIndex=0;
    void setstuff(SpellInfo[] se)
    {
        ArrayList<SpellInfo>v = new ArrayList<SpellInfo>();
        SpellsAdapter a = new SpellsAdapter(this,v);
        ShopActivity.e = se;
        for(SpellInfo g : se)
            v.add(g);
        final ListView q = ((ListView)findViewById(R.id.listView3));
        q.setAdapter(a);
        this.storedposition= -1;
    }
int storedposition = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_shop);
        Spells();

        ArrayList<SpellInfo> v = new ArrayList<SpellInfo>();

        SpellsAdapter a = new SpellsAdapter(this, v);
        setstuff(Slot1());
        for (SpellInfo g : e)
            v.add(g);
        final ListView q = ((ListView) findViewById(R.id.listView3));
        q.setAdapter(a);
        q.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            storedposition = position;

            }
        });
        for (int i = 0; i < 7; i++)
        {
            SelectedIndex =i;
            changeIcon(i);
        }
        SelectedIndex = 0;
        View.OnClickListener j = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((android.widget.ImageButton) findViewById(R.id.button9)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.button2)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.button3)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.button4)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.button5)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.button6)).setBackgroundResource(R.drawable.shop_button);
                ((android.widget.ImageButton) findViewById(R.id.button7)).setBackgroundResource(R.drawable.shop_button);
                v.setBackgroundResource(R.drawable.shop_button_selected);
                switch (v.getId()) {
                    case R.id.button9:
                        setstuff(Slot1());
                        break;
                    case R.id.button2:

                        setstuff(Slot2());
                        break;
                    case R.id.button3:
                        setstuff(Slot3());
                        break;
                    case R.id.button4:
                        setstuff(Slot4());
                        break;
                    case R.id.button5:
                        setstuff(Slot5());
                        ;
                        break;
                    case R.id.button6:
                        setstuff(Slot6());
                        break;
                    case R.id.button7:
                        setstuff(Slot7());
                        break;
                }
            }
        };
        ((ImageButton) findViewById(R.id.button9)).setOnClickListener(j);
        ((android.widget.ImageButton) findViewById(R.id.button2)).setOnClickListener(j);
        findViewById(R.id.button3).setOnClickListener(j);
        findViewById(R.id.button4).setOnClickListener(j);
        findViewById(R.id.button5).setOnClickListener(j);
        findViewById(R.id.button6).setOnClickListener(j);
        findViewById(R.id.button7).setOnClickListener(j);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(storedposition!=-1)
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
        ((Button) findViewById(R.id.button10)).setOnClickListener(new View.OnClickListener() {
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
    public static void loadState()
    {
        SpellInfo[] s =null;
        FileInputStream inStream = null;
        try {
            File f = new File(Environment.getExternalStorageDirectory(), "/data.dat");
            inStream = new FileInputStream(f);
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);

             s = ((SpellInfo[]) objectInStream.readObject());
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
        if(s!=null)
        for(int i = 0; i< s.length&&i<7;i++)
        {
            Global.spellList[i] = s[i];
        }
        else
        {
            Log.e("FAILED TO LOAD", "YOU SUCK!!!!");
        }
    }

    private void SaveLoadout() {
       saveState();
    }
    public static byte[] SerializetoBytes(SpellInfo[] gameObjects) {

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
    void changeIcon(int i)
    {
        ImageView b=null;
        TextView t= null;
        switch (i)
        {
            case 0:
                t=(TextView)findViewById(R.id.textView1);
                b=  (ImageView)findViewById(R.id.imageView1);
                break;
            case 1:
                t=(TextView)findViewById(R.id.textView2);
                b=  (ImageView)findViewById(R.id.imageView2);
                break;
            case 2:
                t=(TextView)findViewById(R.id.textView3);
                b=  (ImageView)findViewById(R.id.imageView3);
                break;
            case 3:
                t=(TextView)findViewById(R.id.textView4);
                b=  (ImageView)findViewById(R.id.imageView4);
                break;
            case 4:
                t=(TextView)findViewById(R.id.textView5);
                b=  (ImageView)findViewById(R.id.imageView5);
                break;
            case 5:
                t=(TextView)findViewById(R.id.textView6);
                b=  (ImageView)findViewById(R.id.imageView6);
                break;
            case 6:
                t=(TextView)findViewById(R.id.textView7);
                b=  (ImageView)findViewById(R.id.imageView7);
                break;


        }


            b.setBackgroundResource(SpellInfo.setResource(Global.spellList[SelectedIndex].spellType));
            t.setText("Rank:" + Global.spellList[SelectedIndex].Rank);

    }




}
