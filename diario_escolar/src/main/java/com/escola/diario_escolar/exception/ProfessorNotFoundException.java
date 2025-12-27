package com.escola.diario_escolar.exception;

public class ProfessorNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfessorNotFoundException() {
        super("Professor não encontrado");
    }
}