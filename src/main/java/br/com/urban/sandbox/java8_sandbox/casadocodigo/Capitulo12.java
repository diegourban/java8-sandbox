package br.com.urban.sandbox.java8_sandbox.casadocodigo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Capitulo12 {

	public static void main(String[] args) {

		Usuario u1 = new Usuario("Paulo Silveira", 150);
		Usuario u2 = new Usuario("Rodrigo Turini", 120);
		Usuario u3 = new Usuario("Guilherme Silveira", 190);

		List<Usuario> usuarios = Arrays.asList(u1, u2, u3);

		// limitacoes da inferencia

		// necessidade de tipagem para inferencia correta:
		usuarios.sort(Comparator.comparingInt((Usuario u) -> u.getPontos()).thenComparing(u -> u.getNome()));

		usuarios.sort(Comparator.<Usuario, Integer>comparing(u -> u.getPontos()).reversed());
	}
}