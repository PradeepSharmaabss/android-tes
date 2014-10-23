package developmental.warlocks.Shop;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.developmental.warlocks.R;

import java.util.ArrayList;

import Spells.Spell;
import Spells.SpellInfo;
import Spells.SpellType;
import developmental.warlocks.Global;

/**
 * Created by Scott on 18/08/13.
 */
public class NewShopActivity extends Activity {
    public NewShopActivity() {
        super();

    }





    void Spells()
    {
        Global.spellList[0] = new SpellInfo(SpellType.Fireball,1);
        Global.spellList[1] = new SpellInfo(SpellType.Lightning,1);
        Global.spellList[2] = new SpellInfo(SpellType.FireSpray,1);
        Global.spellList[3] = new SpellInfo(SpellType.Meteor,1);
        Global.spellList[4] = new SpellInfo(SpellType.Gravity,1);
        Global.spellList[5] = new SpellInfo(SpellType.Bounce,1);
        Global.spellList[6] = new SpellInfo(SpellType.Illusion,1);
        Global.spellList[7] = new SpellInfo(SpellType.FireExplode,1);
        Global.spellList[8] = new SpellInfo(SpellType.Ice,1);
        Global.spellList[9] = new SpellInfo(SpellType.Reflect,1);
    }
    SpellInfo[] Slot1()
    {
        SpellInfo[] s= new SpellInfo[1];
        s[0] = new SpellInfo(SpellType.Fireball,1);
        NewShopActivity.SelectedIndex =0;
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
        NewShopActivity.SelectedIndex =1;
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
        NewShopActivity.SelectedIndex =2;
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
        NewShopActivity.SelectedIndex =3;
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
        NewShopActivity.SelectedIndex =4;
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
        NewShopActivity.SelectedIndex =5;
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
        NewShopActivity.SelectedIndex =6;
        return s;
    }
    public static SpellInfo [] e;
    public static int SelectedIndex=0;
    void setstuff(SpellInfo[] se)
    {
        ArrayList<SpellInfo>v = new ArrayList<SpellInfo>();
        SpellsAdapter a = new SpellsAdapter(this,v);
        NewShopActivity.e = se;
        for(SpellInfo g : se)
            v.add(g);
        final ListView q = ((ListView)findViewById(R.id.listView3));
        q.setAdapter(a);

    }

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
                Global.spellList[SelectedIndex].SetOrIncrement(NewShopActivity.e[position].spellType);
                Log.e("SPELLS", Global.spellList[1].toString());
                changeIcon(SelectedIndex);
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
        ((android.widget.ImageButton) findViewById(R.id.button3)).setOnClickListener(j);
        ((android.widget.ImageButton) findViewById(R.id.button4)).setOnClickListener(j);
        ((android.widget.ImageButton) findViewById(R.id.button5)).setOnClickListener(j);
        ((android.widget.ImageButton) findViewById(R.id.button6)).setOnClickListener(j);
        ((android.widget.ImageButton) findViewById(R.id.button7)).setOnClickListener(j);

    }
    void changeIcon(int i)
    {
        ImageView b=null;
        switch (i)
        {
            case 0:
                b=  (ImageView)findViewById(R.id.imageView1);
                break;
            case 1:
                b=  (ImageView)findViewById(R.id.imageView2);
                break;
            case 2:
                b=  (ImageView)findViewById(R.id.imageView3);
                break;
            case 3:
                b=  (ImageView)findViewById(R.id.imageView4);
                break;
            case 4:
                b=  (ImageView)findViewById(R.id.imageView5);
                break;
            case 5:
                b=  (ImageView)findViewById(R.id.imageView6);
                break;
            case 6:
                b=  (ImageView)findViewById(R.id.imageView7);
                break;


        }
        b.setBackgroundResource(SpellInfo.setResource(Global.spellList[SelectedIndex].spellType));
    }




}
