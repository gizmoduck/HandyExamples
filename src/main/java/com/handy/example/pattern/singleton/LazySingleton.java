package com.handy.example.pattern.singleton;

public class LazySingleton {
	
	private static volatile LazySingleton instance;

	private LazySingleton() {}
	
	
	
	/**
	 * @return
	 * synchronized required to prevent race condition. drawback entire method is blocked to prevent race condition.
	 * lazily created when method is accessed first time.
	 */
	public static synchronized LazySingleton getInstance() {
		if (instance==null) {
			instance =  new LazySingleton();
		}
			return instance;
	}
	
	public static LazySingleton getInstance2() {
		if (instance==null) {
			synchronized (LazySingleton.class) {
				if(instance==null)
					instance = new LazySingleton();
			}
		}
		return instance;
	}
}
