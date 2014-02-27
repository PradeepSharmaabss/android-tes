package com.developmental.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Spells.SpellInfo;
import Spells.SpellType;

/**
* Created by Scott on 18/08/13.
*/
public class ShopActivity extends BaseGameActivity {
    public ShopActivity() {
        super();

    }




Slots slots;
    ListView l2;
    ListView l3;
  enum Slots{One,Two,Three,Four,Five,Six,Seven,Eight,Nine,Ten}
    void Spells()
    {
        Global.spellList[0] = new SpellInfo(SpellType.Fireball,1);
        Global.spellList[1] = new SpellInfo(SpellType.Lightning,1);
        Global.spellList[2] = new SpellInfo(SpellType.FireSpray,1);
        Global.spellList[3] = new SpellInfo(SpellType.Meteor,1);
        Global.spellList[4] = new SpellInfo(SpellType.Gravity,1);
        Global.spellList[5] = new SpellInfo(SpellType.Bounce,1);
        Global.spellList[6] = new SpellInfo(SpellType.Swap,1);
        Global.spellList[7] = new SpellInfo(SpellType.FireExplode,1);
        Global.spellList[8] = new SpellInfo(SpellType.Ice,1);
        Global.spellList[9] = new SpellInfo(SpellType.Reflect,1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
Spells();
        setContentView(R.layout.shop);
        final View button;
        button = findViewById(R.id.btnStart);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RenderThread.renderThread.MakePlayers();
                RenderThread.UserInterface();
                Intent intent = new Intent(ShopActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });
        beginUserInitiatedSignIn();

        final ListView l = (ListView) findViewById(R.id.listView);
        l.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0:

                        ChooseListTwo(Slots.One);
                        break;
                    case 1:

                        ChooseListTwo(Slots.Two);
                        break;
                    case 2:

                        ChooseListTwo(Slots.Three);
                        break;
                    case 3:

                        ChooseListTwo(Slots.Four);
                        break;
                    case 4:

                        ChooseListTwo(Slots.Five);
                        break;
                    case 5:

                        ChooseListTwo(Slots.Six);
                        break;
                    case 6:

                        ChooseListTwo(Slots.Seven);
                        break;
                    case 7:
                        ChooseListTwo(Slots.Eight);
                        break;
                    case 8:
                        ChooseListTwo(Slots.Nine);
                        break;
                    case 9:
                        ChooseListTwo(Slots.Ten);
                        break;
                }
            }
        });
        ChooseListTwo(Slots.One);
Display();


    }
    void Display()
    {
        ArrayList ListTwo = new ArrayList<String>();
        l3 = (ListView) findViewById(R.id.listView3);
        for(SpellInfo s:Global.spellList)
        if(s!=null)
            ListTwo.add(s.toString());
        ArrayAdapter<String> a = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1,new ArrayList<String>());
        a.addAll(ListTwo);

        l3.setAdapter(a);
    }

void ChooseListTwo(Slots s)
{
    ArrayList ListTwo = new ArrayList<String>();
    l2 = (ListView) findViewById(R.id.listView2);
    l2.setOnItemClickListener(new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (slots) {

                case One:
                    Global.spellList[0].SetOrIncrement(SpellType.Fireball);

                    break;
                case Two:
                    switch (i) {
                        case 0:
                            Global.spellList[1].SetOrIncrement(SpellType.Homing);

                            break;
                        case 1:
                            Global.spellList[1].SetOrIncrement(SpellType.Lightning);

                            break;
                        case 2:
                            Global.spellList[1].SetOrIncrement(SpellType.Boomerang);

                            break;

                    }
                    break;
                case Three:
                    switch (i) {
                        case 0:
                            Global.spellList[2].SetOrIncrement(SpellType.Link);
                        break;
                        case 1:
                            Global.spellList[2].SetOrIncrement(SpellType.Ice);

                            break;
                        case 2:
                            Global.spellList[2].SetOrIncrement(SpellType.Gravity);

                            break;

                    }
                    break;
                case Four:
                    switch (i) {
                        case 0:
                            Global.spellList[3].SetOrIncrement(SpellType.Meteor);

                            break;
                        case 1:
                            Global.spellList[3].SetOrIncrement(SpellType.Drain);

                            break;
                        case 2:
                            Global.spellList[3].SetOrIncrement(SpellType.Absorb);

                            break;

                    }
                    break;
                case Five:
                    switch (i) {
                        case 0:
                            Global.spellList[4].SetOrIncrement(SpellType.Splitter);

                            break;
                        case 1:
                            Global.spellList[4].SetOrIncrement(SpellType.FireSpray);

                            break;
                        case 2:
                            Global.spellList[4].SetOrIncrement(SpellType.Splitter);

                            break;
                        case 3:
                            Global.spellList[4].SetOrIncrement(SpellType.Bounce);

                            break;

                    }
                    break;
                case Six:
                    switch (i) {
                        case 0:
                            Global.spellList[5].SetOrIncrement(SpellType.Teleport);

                            break;
                        case 1:
                            Global.spellList[5].SetOrIncrement(SpellType.Swap);

                            break;
                        case 2:
                            Global.spellList[5].SetOrIncrement(SpellType.Swap);

                            break;

                    }
                    break;
                case Seven:
                    switch (i) {
                        case 0:


                            Global.spellList[6].SetOrIncrement(SpellType.Reflect);

                            break;
                        case 1:

                            Global.spellList[6].SetOrIncrement(SpellType.Reflect);

                            break;
                        case 2:

                            Global.spellList[6].SetOrIncrement(SpellType.Reflect);
                            break;

                    }
                    break;
                case Eight:
                    switch (i) {
                        case 0:

                            Global.spellList[7].SetOrIncrement(SpellType.Reflect);
                            break;
                        case 1:

                            Global.spellList[7].SetOrIncrement(SpellType.Reflect);
                            break;
                        case 2:

                            Global.spellList[7].SetOrIncrement(SpellType.Reflect);
                            break;

                    }
                    break;
                case Nine:
                    switch (i) {
                        case 0:

                            Global.spellList[8].SetOrIncrement(SpellType.Reflect);
                            break;
                        case 1:

                            Global.spellList[8].SetOrIncrement(SpellType.Reflect);
                            break;
                        case 2:

                            Global.spellList[8].SetOrIncrement(SpellType.Reflect);
                            break;

                    }
                    break;
                case Ten:
                    switch (i) {
                        case 0:

                            Global.spellList[9].SetOrIncrement(SpellType.Reflect);
                            break;
                        case 1:

                            Global.spellList[9].SetOrIncrement(SpellType.Reflect);
                            break;
                        case 2:

                            Global.spellList[9].SetOrIncrement(SpellType.Reflect);
                            break;

                    }
                    break;
            }

            Display();
        }
    });
    slots = s;
    switch (s)
    {

        case One:
            ListTwo.add("FireBall");
            break;
        case Two:
            ListTwo.add("Homing");
            ListTwo.add("Lightning");
            ListTwo.add("Boomerang");

            break;
        case Three:
            ListTwo.add("Link");
            ListTwo.add("Ice");
            ListTwo.add("Tornado");
            break;
        case Four:
            ListTwo.add("Meteor");
            ListTwo.add("Drain");
            ListTwo.add("Absorption");
            break;
        case Five:
            ListTwo.add("Splitter");
            ListTwo.add("Fire Spray");
            ListTwo.add("Ice Spray");
            ListTwo.add("Bouncer");
            break;
        case Six:
            ListTwo.add("Teleport");
            ListTwo.add("Swap");
            ListTwo.add("Thrust");
            break;
        case Seven:
            ListTwo.add("Reflect");
            ListTwo.add("Orb-itals");
            ListTwo.add("Root-Self");
            break;
        case Eight:
            ListTwo.add("Juggernaut");
            ListTwo.add("Wind Walk");
            ListTwo.add("Phase");
            break;
        case Nine:
            ListTwo.add("Burn Aura");
            ListTwo.add("Freeze Aura");
            ListTwo.add("Drain Aura");
            ListTwo.add("Bezerk");
            ListTwo.add("Fervour");
            ListTwo.add("Boots");
            ListTwo.add("Health Stone");
            ListTwo.add("Shield");
            break;
        case Ten:
            ListTwo.add("Fire Explode");
            ListTwo.add("Burn Explode");
            ListTwo.add("Freeze Explode");
            ListTwo.add("Drain Explode");
            break;
    }
ArrayAdapter<String> a = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1,new ArrayList<String>());
    a.addAll(ListTwo);

  l2.setAdapter(a);
}


    @Override
    public void onSignInFailed() {
        Toast.makeText(this, "Signed In FAILED= " + isSignedIn(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignInSucceeded() {
        Toast.makeText(this, "Signed In SUCCEEEDED= " + isSignedIn(), Toast.LENGTH_LONG).show();
    }
}
