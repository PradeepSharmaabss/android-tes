package com.developmental.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Spells.AbsorptionSpell;
import Spells.BoomerangSpell;
import Spells.DrainSpell;
import Spells.FirespraySpell;
import Spells.GravitySpell;
import Spells.HomingSpell;
import Spells.IceSpell;
import Spells.InstantCastSpell;
import Spells.LightningSpell;
import Spells.LinkSpell;
import Spells.MeteorSpell;
import Spells.SplitterSpell;
import Spells.SwapSpell;
import Spells.TeleportSpell;

/**
 * Created by Scott on 18/08/13.
 */
public class ShopActivity extends BaseGameActivity {
    public ShopActivity() {
        super();

    }




Slots slots;
     ListView l2;
  enum Slots{One,Two,Three,Four,Five,Six,Seven,Eight,Nine,Ten}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shop);
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



    }
void ChooseListTwo(Slots s)
{
    ArrayList ListTwo = new ArrayList<String>();
    l2 = (ListView) findViewById(R.id.listView2);
    l2.setOnItemClickListener(new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (slots)
            {

                case One:
                    break;
                case Two:
                    switch (i)
                    {
                        case 0:

                           RenderThread.archie.Spells[1] = new HomingSpell(RenderThread.archie);
                            break;
                        case 1:

                            RenderThread.archie.Spells[1] = new LightningSpell(RenderThread.archie);
                            break;
                        case 2:

                            RenderThread.archie.Spells[1] = new BoomerangSpell(RenderThread.archie);
                            break;

                    }
                    break;
                case Three:
                    switch (i)
                    {
                        case 0:

                            RenderThread.archie.Spells[2] = new LinkSpell(RenderThread.archie);
                            break;
                        case 1:

                            RenderThread.archie.Spells[2] = new IceSpell(RenderThread.archie);
                            break;
                        case 2:
                            RenderThread.archie.Spells[2] = new GravitySpell(RenderThread.archie);
                            break;

                    }
                    break;
                case Four:
                    switch (i)
                    {
                        case 0:


                            RenderThread.archie.Spells[3] = new MeteorSpell(RenderThread.archie);
                            break;
                        case 1:

                            RenderThread.archie.Spells[3] = new DrainSpell(RenderThread.archie);
                            break;
                        case 2:

                            RenderThread.archie.Spells[3] = new AbsorptionSpell(RenderThread.archie);
                            break;

                    }
                    break;
                case Five:
                    switch (i)
                    {
                        case 0:
                            RenderThread.archie.Spells[4] = new SplitterSpell(RenderThread.archie);
                            break;
                        case 1:
                            RenderThread.archie.Spells[4] = new FirespraySpell(RenderThread.archie);
                            break;
                        case 2:
                            RenderThread.archie.Spells[4] = new SplitterSpell(RenderThread.archie);
                            break;
                        case 3:
                            break;

                    }
                    break;
                case Six:
                    switch (i)
                    {
                        case 0:

                            RenderThread.archie.Spells[5] = new TeleportSpell(RenderThread.archie);
                            break;
                        case 1:
                            RenderThread.archie.Spells[5] = new SwapSpell(RenderThread.archie);
                            break;
                        case 2:
                            RenderThread.archie.Spells[5] = new SwapSpell(RenderThread.archie);
                            break;

                    }
                    break;
                case Seven:
                    switch (i)
                    {
                        case 0:


                            RenderThread.archie.Spells[6] = new InstantCastSpell(RenderThread.archie);
                            break;
                        case 1:

                            RenderThread.archie.Spells[6] = new InstantCastSpell(RenderThread.archie);
                            break;
                        case 2:

                            RenderThread.archie.Spells[6] = new InstantCastSpell(RenderThread.archie);
                            break;

                    }
                    break;
                case Eight:
                    switch (i)
                    {
                        case 0:

                            RenderThread.archie.Spells[7] = new InstantCastSpell(RenderThread.archie);
                            break;
                        case 1:

                            RenderThread.archie.Spells[7] = new InstantCastSpell(RenderThread.archie);
                            break;
                        case 2:

                            RenderThread.archie.Spells[7] = new InstantCastSpell(RenderThread.archie);
                            break;

                    }
                    break;
                case Nine:
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

                    }
                    break;
                case Ten:
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

                    }
                    break;
            }

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
