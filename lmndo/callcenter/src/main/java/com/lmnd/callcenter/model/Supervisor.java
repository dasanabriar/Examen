package com.lmnd.callcenter.model;
/**
 * Clase de empleado supervisor
 * @author David Sanabria
 * @version 01/03/2018
 */
public class Supervisor extends Employee {
	
	/**
	 * Constructor de la clase Supervisor
	 * @param idEmployee
	 * @param attentionLevel
	 */
	public Supervisor(int idEmployee, Integer attentionLevel) {
		super(idEmployee, attentionLevel);
		super.setPosition(Supervisor.class.getSimpleName());
	}



	

}
