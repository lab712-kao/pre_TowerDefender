package gameSystem.gameObjectSystem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Vector;


public class DoubleDeque<E> {

	private Deque<E> DequeF;
	private Deque<E> DequeB;
	public DoubleDeque() {
		// TODO Auto-generated constructor stub	
		 DequeF = new ArrayDeque<E>();
		 DequeB = new ArrayDeque<E>();
	}
	public void pushF(E object){
		DequeF.addLast(object);
		
		
	}

	public E popF() {
			return DequeF.pollFirst();
	}
	
	public void pushB(E object){
		DequeF.addLast(object);
	}

	public E popB() {
		return DequeB.pollFirst();
	}
	public Deque<E> getDequeF() {
		return DequeF;
	}
	public Deque<E> getDequeB() {
		return DequeB;
	}
}
