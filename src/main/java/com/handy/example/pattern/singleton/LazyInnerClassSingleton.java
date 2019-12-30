package com.handy.example.pattern.singleton;

public class LazyInnerClassSingleton {
	
	private LazyInnerClassSingleton() {}
	
	/** This inner class is loaded only after getInstance() is called for the first time. */
	private static class SingletonHelper {
		private static final LazyInnerClassSingleton instance = new LazyInnerClassSingleton();
	}

	/**
	 * @return
	 * Note that, the inner class is not loaded until the getInstance() method is invoked for the first time. 
	 * This solution is thread-safe and doesn’t require any synchronization. 
	 * It is the most efficient approach among all the singleton design pattern implementations.
	 */
	public static LazyInnerClassSingleton getInstance() {
		return SingletonHelper.instance;
	}
}
