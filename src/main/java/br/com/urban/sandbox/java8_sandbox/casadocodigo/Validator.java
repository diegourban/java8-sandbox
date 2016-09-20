package br.com.urban.sandbox.java8_sandbox.casadocodigo;

@FunctionalInterface
interface Validador<T> {
	
	boolean valida(T t);
}