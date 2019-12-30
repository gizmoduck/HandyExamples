package com.handy.example.comparatorVSComparable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author achark
 * 
 * The Comparable interface has a single method called compareTo() that you need to implement in order to define 
 * how an object compares with the supplied object -
 * negative, if this object is less than the supplied object.
 * zero, if this object is equal to the supplied object.
 * positive, if this object is greater than the supplied object.
 *
 *
 *Many predefined Java classes like String, Date, LocalDate, 
 *LocalDateTime etc implement the Comparable interface to define the ordering of their instances.
 */
public class EmployeeComparable implements Comparable<EmployeeComparable> {

	private int id;
	private String name;
	private double salary;
	private LocalDate dateOfJoining;
	
    // Compare Two Employees based on their ID
    /**
     * @param   anotherEmployee - The Employee to be compared.
     * @return  A negative integer, zero, or a positive integer as this employee
     *          is less than, equal to, or greater than the supplied employee object.
    */
	public int compareTo(EmployeeComparable anotherEmployee) {
		
		return this.getId() - anotherEmployee.getId();
	}

	// Two employees are equal if their IDs are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeComparable employee = (EmployeeComparable) o;
        return id == employee.id;
    }
	public EmployeeComparable() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EmployeeComparable(int id, String name, double salary, LocalDate dateOfJoining) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.dateOfJoining = dateOfJoining;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}


	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(id);
    }

	@Override
	public String toString() {
		return "EmployeeComparable [id=" + id + ", name=" + name + ", salary=" + salary + ", dateOfJoining="
				+ dateOfJoining + "]";
	}
	
	
	public static void main(String[] args) {
		List<EmployeeComparable> employees = new ArrayList<EmployeeComparable>();
		employees.add(new EmployeeComparable(101,"test1", 1000.0, LocalDate.of(2000, 01, 15)));
		employees.add(new EmployeeComparable(156,"test2", 1001.0, LocalDate.of(2000, 01, 15)));
		employees.add(new EmployeeComparable(134,"test3", 1002.0, LocalDate.of(2000, 01, 15)));
		employees.add(new EmployeeComparable(105,"test4", 1003.0, LocalDate.of(2000, 01, 15)));
		employees.add(new EmployeeComparable(102,"test5", 1004.0, LocalDate.of(2000, 01, 15)));
		
		System.out.println("print Employee list before sort :: " +employees.toString());
		
		// This will use the `compareTo()` method of the `Employee` class to compare two employees and sort them.
		//Any class that implements the Comparable interface works out of the box with Sorted Sets and Sorted Maps.
		Collections.sort(employees);
		
		System.out.println("print Employee list After sort :: " +employees.toString());
		
								//****************COMPARATOR**************************
		// using Comparator we can sort objects based on the desired filed if object to be sorted resides in third party jar.
		
		Comparator<EmployeeComparable> empComparator = new Comparator<EmployeeComparable>() {
			public int compare(EmployeeComparable o1, EmployeeComparable o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
		
		//above can be written using lambda JAVA 8
		Comparator<EmployeeComparable> empComparator1 = (e1,e2) -> e1.getName().compareTo(e2.getName());
		
								//or
		Comparator<EmployeeComparable> empComparator2 = Comparator.comparing(EmployeeComparable::getName);
		
		
		Collections.sort(employees, empComparator);
		
		System.out.println("print Employee list sorted using comparator Name :: " +employees.toString());
		
		//JAVA 8
		//comparator by salary
		Collections.sort(employees, Comparator.comparingDouble(EmployeeComparable::getSalary));
		
		//comparator by dateofJoing
		Collections.sort(employees, Comparator.comparing(EmployeeComparable::getDateOfJoining));

		//sortbyName in descending order
		Collections.sort(employees, Comparator.comparing(EmployeeComparable::getName).reversed());
		System.out.println("\nEmployees (Sorted by Name in descending order) : " + employees);

		// Chaining multiple Comparators
        // Sort by Salary. If Salary is same then sort by Name
		Collections.sort(employees, Comparator.comparingDouble(EmployeeComparable::getSalary).thenComparing(EmployeeComparable::getName));
		System.out.println("\nEmployees (Sorted by Salary and Name) : " + employees);

	}
	
}
