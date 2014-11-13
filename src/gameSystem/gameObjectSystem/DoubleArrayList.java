package gameSystem.gameObjectSystem;

import java.io.PushbackInputStream;
import java.util.ArrayList;

import android.R.integer;

public class DoubleArrayList<E> {
	private ArrayList<E> own;
	private ArrayList<E> enemy;
	
	public DoubleArrayList() {
		super();
	}
	
	public void push(E object, IDType T){
				if(T == IDType.O){
					own.add(object);
				}else if(T== IDType.E){
					enemy.add(object);
				}
	}
	public E seek(int index,IDType T){
		
		if(T == IDType.O){
			if(index>own.size()||index<0) return null;
			return own.get(index);
		}else if(T== IDType.E){
			if(index>enemy.size()||index<0) return null;
			return enemy.get(index);
		}
		return null;
		
	}
	public void remove(int index,IDType T){
		if(T == IDType.O){
			if(index>own.size()||index<0) return ;
			own.remove(index);
		}else if(T== IDType.E){
			if(index>enemy.size()||index<0) return ;
			enemy.remove(index);
		}
	}
	public int getIndexOf(E object ,IDType T){
		if(T == IDType.O){
			return own.indexOf(object);
		}else if(T== IDType.E){
			return enemy.indexOf(object);
		}
		return -1;
	}
}
