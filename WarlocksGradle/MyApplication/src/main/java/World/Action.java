package World;

import Tools.Vector;

/**
 * Created by Scott on 6/19/13.
 */
class Action {
    private Vector Location;
    private int SenderID;
    private int actionID;

    public Action(Vector _loc, int _actionID, int _sender) {
        Location = _loc;
        actionID = _actionID;
        SenderID = _sender;
    }
}
