package urban.sandbox.java8.casadocodigo;

@FunctionalInterface
interface Validador<T> {

	boolean valida(T t);
}