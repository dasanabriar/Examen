package com.lmnd.callcenter.model;

/**
 * Clase de empleado
 * @author David Sanabria
 * @version 01/03/2018
 */
public class Employee implements Comparable<Employee> {
	
	private int idEmployee;
	/**
	 * Cargo del empleado
	 */
	private String position;
	/**
	 * Nivel de ateción de llamadas. (Atención de primer nivel, segúndo nivel, etc)  
	 */
	private Integer attentionLevel;
	
	/**
	 * Contructor de la clase empleado
	 * 
	 * @param charge
	 * @param attentionLevel
	 */
	public Employee(int idEmployee, Integer attentionLevel) {
		super();
		this.idEmployee = idEmployee;
		this.attentionLevel = attentionLevel;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getAttentionLevel() {
		return attentionLevel;
	}

	public void setAttentionLevel(Integer attentionLevel) {
		this.attentionLevel = attentionLevel;
	}

	

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	@Override
	public int compareTo(Employee o) {
		if(o.attentionLevel>this.attentionLevel){
			return 1;
		}
		if(o.attentionLevel<this.attentionLevel){
			return -1;
		}
		return 0;
	}


	
	
	
	


	

}
