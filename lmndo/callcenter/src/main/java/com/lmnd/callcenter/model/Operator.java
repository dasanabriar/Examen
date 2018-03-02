package com.lmnd.callcenter.model;
/**
 * Clase de empleado operador
 * @author David Sanabria
 * @version 01/03/2018
 */
public class Operator extends Employee {
	/**
	 * Constructor de la clase Operator
	 * @param idEmployee
	 * @param attentionLevel
	 */
	public Operator(int idEmployee, Integer attentionLevel) {
		super(idEmployee, attentionLevel);
		super.setPosition(Operator.class.getSimpleName());
	}



}
