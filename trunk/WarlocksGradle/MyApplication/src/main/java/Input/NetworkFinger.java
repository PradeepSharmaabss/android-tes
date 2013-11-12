package Input;

import java.io.Serializable;
import java.util.ArrayList;

import Tools.iVector;

/**
 * Created by Scott on 10/08/13.
 */
public class NetworkFinger implements Serializable {
    public int Step = 0;
    public int SelectedSpell;
    public int id = 0;
    public ArrayList<iVector> finger;

    public NetworkFinger(int _s, ArrayList<iVector> _f, int _id, int ss) {
        finger = _f;
        Step = _s;
        id = _id;
        SelectedSpell = ss;
    }

}
