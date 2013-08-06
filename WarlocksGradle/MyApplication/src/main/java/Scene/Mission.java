package Scene;

import java.util.ArrayList;
import java.util.List;

public class Mission extends Action {
	List<Action> ToDo;

	public Mission(ArrayList<Action> a) {
		super();
		this.ToDo = a;
	}

	@Override
	boolean Done() {
		for (Action a : this.ToDo)
			if (!a.Done())
				return false;
		return true;
	}
}
