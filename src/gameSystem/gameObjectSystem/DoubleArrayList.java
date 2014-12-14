package gameSystem.gameObjectSystem;

import java.util.ArrayList;

public class DoubleArrayList<E> {
	private ArrayList<E> own;
	private ArrayList<E> enemy;

	public DoubleArrayList() {
		super();
		own = new ArrayList<E>();
		enemy = new ArrayList<E>();
	}

	public void push(E object, IDType T) {
		if (T == IDType.O) {
			own.add(object);
		} else if (T == IDType.E) {
			enemy.add(object);
		}
	}

	public E seek(int index, IDType T) {

		if (T == IDType.O) {
			if (index > own.size() || index < 0 || own.size() == 0)
				return null;
			return own.get(index);
		} else if (T == IDType.E) {
			if (index > enemy.size() || index < 0 || enemy.size() == 0)
				return null;
			return enemy.get(index);
		}
		return null;

	}

	public int size(IDType T) {
		if (T == IDType.O) {
			return own.size();
		} else if (T == IDType.E) {
			return enemy.size();
		}
		return 0;
	}

	public void remove(int index, IDType T) {
		if (T == IDType.O) {
			if (index > own.size() || index < 0)
				return;
			own.remove(index);
		} else if (T == IDType.E) {
			if (index > enemy.size() || index < 0)
				return;
			enemy.remove(index);
		}
	}

	public int getIndexOf(E object, IDType T) {
		if (T == IDType.O) {
			return own.indexOf(object);
		} else if (T == IDType.E) {
			return enemy.indexOf(object);
		}
		return -1;
	}
}
