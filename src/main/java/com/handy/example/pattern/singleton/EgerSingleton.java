package com.handy.example.pattern.singleton;

/**
 * @author achark
 *
 *The disadvantage of this approach is that the instance is created irrespective of whether it is accessed or not. 
 *This is fine if the object is simple and does not hold any system resources. 
 *But can have performance implications if it allocates a large amount of system resources and remains unused.
 */
public class EgerSingleton {

	/**
	 * private constructor to stop initialization of the class from outside. 
	 */
	private EgerSingleton() {}
	
	/**
	 *  create instance of the class at the time of class loading
	 */
	private static final EgerSingleton instance = new EgerSingleton();
	
	//or static block can be used
	private static EgerSingleton block = null;
	
	/** Create the one-and-only instance in a static block */
	static {
		try {
			block = new EgerSingleton();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static EgerSingleton getBlockInstance() {
		return block;
	}
	
	/**
	 * public method to access the instance of the class
	 * @return
	 */
	public static EgerSingleton getInstance() {
		return instance;
	}
	
}
