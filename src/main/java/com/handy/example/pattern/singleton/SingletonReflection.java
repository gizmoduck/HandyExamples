package com.handy.example.pattern.singleton;

import java.lang.reflect.Constructor;

/**
 * @author achark
 *
 *Java’s Reflection API is very powerful. You can use Reflection to instantiate a class even if the class’s constructor is private.
 */

class MySingleton {
    private static final MySingleton instance = new MySingleton();

    private MySingleton() {
    	if(instance!=null) {
    		throw new IllegalStateException("Singleton already initialized");
    	}
    }

    public static MySingleton getInstance() {
        return instance;
    }
}

/**
 * @author achark
 *
 *Notice how we created a new instance of the Singleton using constructor.newInstance(). This destroys the singleton pattern.
 *Protection against Reflection
 *To protect your singleton class against instantiation via reflection,
 *you can throw an exception from the private constructor if the instance is already created like this -
 */
public class SingletonReflection {

	public static void main(String[] args) {
		MySingleton instance = MySingleton.getInstance();
		
		MySingleton reflectionInstance = null;
		
		try {
			Constructor[] constructors = MySingleton.class.getDeclaredConstructors();
			for (Constructor constructor : constructors) {
				constructor.setAccessible(true);
				reflectionInstance = (MySingleton) constructor.newInstance();
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		System.out.println("instance hascode ::  "+instance.hashCode());
		System.out.println("reflection hascode ::  "+reflectionInstance.hashCode());
	}
}
