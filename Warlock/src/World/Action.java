package World;

import Input.Pointer;

/**
 * Created by Scott on 6/19/13.
 */
public class Action {
    Pointer Location;
    int SenderID;
    public Action(Pointer _loc, int  _sender)
    {
        Location=_loc;
        SenderID= _sender;
    }
}
