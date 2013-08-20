package com.developmental.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Scott on 18/08/13.
 */
public class ShopActivity extends BaseGameActivity {
    public ShopActivity() {
        super();

    }
    ArrayAdapter<String> arrayAdapter;
    private int menu1 = 0;
    private int menu2 = 0;

    ArrayAdapter<String> arrayAdapter2;
    ArrayList<String> secSpells;
    ArrayList<String> primSpells;
    ArrayList<String>[] Slots;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
Slots=new ArrayList[7];
            Slots[0] = new ArrayList<String>();
            Slots[0].add("Fireball");
        Slots[1] = new ArrayList<String>();
        Slots[1].addAll(Arrays.asList("Lightning","Homing","Boomerang"));

        Slots[2] = new ArrayList<String>();
        Slots[2].addAll(Arrays.asList("Swap","Teleport","Thrust"));
        Slots[3] = new ArrayList<String>();
        Slots[3].addAll(Arrays.asList("Swap","Teleport","Thrust"));
        Slots[4] = new ArrayList<String>();
        Slots[4].addAll(Arrays.asList("Meteor","Freeze"));
        Slots[5] = new ArrayList<String>();
        Slots[5].addAll(Arrays.asList("Shield","Time"));
        Slots[6] = new ArrayList<String>();
        Slots[6].addAll(Arrays.asList("Gravity","Link"));
        setContentView(R.layout.shop);
       beginUserInitiatedSignIn();

       primSpells = new ArrayList<String>();
        primSpells.addAll(Arrays.asList("Slot One", "Slot Two","Slot Three","Slot Four"));
       secSpells = new ArrayList<String>();
        secSpells.addAll(Arrays.asList("Slot Five", "Slot Six","Slot Seven"));
        this.l = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        menu1 = i;
            switch (i)
            {
                case 0:

                    ShopActivity.this.arrayAdapter.setNotifyOnChange(true);

                    ShopActivity.this.arrayAdapter.clear();
                    ShopActivity.this.arrayAdapter.addAll(primSpells);

                    ShopActivity.this.arrayAdapter2.clear();

                    break;
                case 1:

                    ShopActivity.this.arrayAdapter.setNotifyOnChange(true);
                    ShopActivity.this.arrayAdapter.clear();
                    ShopActivity.this.arrayAdapter.addAll(secSpells);

                    ShopActivity.this.arrayAdapter2.clear();
                    break;

            }
        }
    };
        this.l2 = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                menu2 = i;



                                switch (menu1)
                                {
                                    case 0:
                                        ShopActivity.this.arrayAdapter2.setNotifyOnChange(true);

                                        ShopActivity.this.arrayAdapter2.clear();
                                        ShopActivity.this.arrayAdapter2.addAll(Slots[i]);
                                        break;
                                    case 1:
                                        ShopActivity.this.arrayAdapter2.setNotifyOnChange(true);

                                        ShopActivity.this.arrayAdapter2.clear();
                                        ShopActivity.this.arrayAdapter2.addAll(Slots[i+4]);

                                        break;
                                }







            }
        };

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new ArrayList());
        arrayAdapter.addAll(primSpells);
        arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new ArrayList());
        final ListView l=  (ListView)findViewById(R.id.listView);

               final ListView l2=  (ListView)findViewById(R.id.listView2);

        l2.setAdapter(this.arrayAdapter);
                final ListView l3=  (ListView)findViewById(R.id.listView3);

        l3.setAdapter(this.arrayAdapter2);
                l2.setOnItemClickListener(this.l2);

        l.setOnItemClickListener(this.l);
    }
    private AdapterView.OnItemClickListener l ;
    private AdapterView.OnItemClickListener l2 ;

    @Override
    public void onSignInFailed() {
        Toast.makeText(this, "Signed In FAILED= " + isSignedIn(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignInSucceeded() {
        Toast.makeText(this, "Signed In SUCCEEEDED= " + isSignedIn(), Toast.LENGTH_LONG).show();
    }
}
