package com.handy.example.pattern.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author achark
 *
 *de-serialization step always creates a new instance of the class, which destroys the singleton pattern
 */
class MySerialSingleton implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static MySerialSingleton instance;
	
	private MySerialSingleton() {
		
	}
	
	public static synchronized MySerialSingleton getInstance() {
        if(instance == null) {
            instance = new MySerialSingleton();
        }
        return instance;
    }
	
	// implement readResolve method to return the existing instance. this is to Protection against Serialization
	protected Object readResolve() {
		return instance;
	}
	
}

public class SerializableSingleton {
	public static void main(String[] args) {
		MySerialSingleton instance = MySerialSingleton.getInstance();
		
		try {
			//serialize singleton object
			ObjectOutput out = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
			out.writeObject(instance);
			out.close();
			
			//Deserialize Object
			ObjectInput input = new ObjectInputStream(new FileInputStream("singleton.ser"));
			MySerialSingleton instance1 = (MySerialSingleton) input.readObject();
			input.close();
			
			System.out.println("serialized hashCode: " + instance.hashCode());
            System.out.println("deserialized hashCode: " + instance1.hashCode());
		} catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

	}
	
	
}

