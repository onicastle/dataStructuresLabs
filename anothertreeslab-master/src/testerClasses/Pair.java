package testerClasses;

public class Pair<T1, T2> {
	
	T1 first;
	T2 second;

	public Pair(T1 first, T2 second) {
		super();
		this.first = first;
		this.second = second;
	}
	
	public Pair() {}

	public T1 first() {
		return first;
	}

	public T2 second() {
		return second;
	}
	
	public String toString() {
		return "Pair [Level :" + first + ", Count :" + second +"]";
	}
}
