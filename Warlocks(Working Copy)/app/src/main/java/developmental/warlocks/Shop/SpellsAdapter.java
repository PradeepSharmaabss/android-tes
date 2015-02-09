package developmental.warlocks.Shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.developmental.warlocks.R;

import java.util.ArrayList;

import Spells.LoadOutInfo;

/**
 * Created by Scott on 23/10/2014.
 */
public class SpellsAdapter extends ArrayAdapter<LoadOutInfo> {
    public SpellsAdapter(Context context, ArrayList<LoadOutInfo> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        LoadOutInfo user = getItem(position);
        if(user!=null) {
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.spell_display, parent, false);
            }
            //  Lookup view for data population
            TextView SpellID = (TextView) convertView.findViewById(R.id.SpellID);
            TextView SpellRank = (TextView) convertView.findViewById(R.id.SpellRank);
            ImageView SpellImage = (ImageView) convertView.findViewById(R.id.imageView1);

            SpellImage.setBackgroundResource(user.Resource);
            // Populate the data into the template view using the data object
            SpellID.setText(String.valueOf(user.Rank));
            SpellRank.setText(String.valueOf(user.spellType));
            // Return the completed view to render on screen
        }
        return convertView;
    }
}