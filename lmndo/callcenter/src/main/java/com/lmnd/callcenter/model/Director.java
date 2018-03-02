package com.lmnd.callcenter.model;
/**
 * Clase que representa a un empleado director
 * @author David Sanabria
 * @version 01/03/2018
 */
public class Director extends Employee {
	/**
	 * Cosntructor de la clase Director
	 * @param idEmployee
	 * @param attentionLevel
	 */
	public Director(int idEmployee, Integer attentionLevel) {
		super(idEmployee, attentionLevel);
		super.setPosition(Director.class.getSimpleName());
		
	}

	

}
