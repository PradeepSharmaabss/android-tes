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

/**
 * Created by Scott on 18/08/13.
 */
public class ShopActivity extends BaseGameActivity {
    public ShopActivity() {
        super();

    }



     ListView l;

     ListView l2;
  enum Slots{One,Two,Three,Four,Five,Six,Seven,Eight,Nine,Ten}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        l = (ListView) findViewById(R.id.listView);
        l2 = (ListView) findViewById(R.id.listView2);
        setContentView(R.layout.shop);
        beginUserInitiatedSignIn();

        ChooseListTwo(Slots.One);



    }
void ChooseListTwo(Slots s)
{
    ArrayList ListTwo = new ArrayList<String>();
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
//    a.addAll(ListTwo);
  //  l2.setAdapter(a);
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
