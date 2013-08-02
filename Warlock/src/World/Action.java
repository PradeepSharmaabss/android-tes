package World;

import Input.Pointer;
import Tools.Vector;

/**
 * Created by Scott on 6/19/13.
 */
public class Action {
    Vector Location;
    int SenderID;
    int actionID;
    public Action(Vector _loc,int _actionID, int  _sender)
    {
        Location=_loc;
        actionID=_actionID;
        SenderID= _sender;
    }
}
