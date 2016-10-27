package br.com.urban.sandbox.java8_sandbox.casadocodigo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Capitulo7 {
	public static void main(String... args) throws Exception {

		Usuario u1 = new Usuario("Paulo Silveira", 150);
		Usuario u2 = new Usuario("Rodrigo Turini", 120);
		Usuario u3 = new Usuario("Guilherme Silveira", 90);

		List<Usuario> usuarios = Arrays.asList(u1, u2, u3);

		usuarios.sort(Comparator.comparing(Usuario::getPontos).reversed());
		usuarios.subList(0, 1).forEach(Usuario::tornaModerador);

		Collections.sort(usuarios, new Comparator<Usuario>() {
			@Override
			public int compare(Usuario u1, Usuario u2) {
				return u1.getPontos() - u2.getPontos();
			}
		});

		Collections.reverse(usuarios);
		List<Usuario> top10 = usuarios.subList(0, 1);
		for (Usuario usuario : top10) {
			usuario.tornaModerador();
		}

		Stream<Usuario> stream = usuarios.stream().filter(u -> u.getPontos() > 100);

		stream.forEach(System.out::println);

		Supplier<ArrayList<Usuario>> supplier = ArrayList::new;
		BiConsumer<ArrayList<Usuario>, Usuario> accumulator = ArrayList::add;
		BiConsumer<ArrayList<Usuario>, ArrayList<Usuario>> combiner = ArrayList::addAll;

		usuarios.stream().filter(u -> u.getPontos() > 100).collect(supplier, accumulator, combiner);

		usuarios.stream().filter(u -> u.getPontos() > 100).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

		List<Usuario> maisQue100 = usuarios.stream().filter(u -> u.getPontos() > 100).collect(Collectors.toList());
		System.out.println(maisQue100);

		ArrayList<Integer> pontos = new ArrayList<>();
		usuarios.forEach(u -> pontos.add(u.getPontos()));

		List<Integer> pontos2 = usuarios.stream().map(u -> u.getPontos()).collect(Collectors.toList());
		System.out.println(pontos2);

		List<Integer> pontos3 = usuarios.stream().map(Usuario::getPontos).collect(Collectors.toList());
		System.out.println(pontos3);

		Stream<Integer> stream2 = usuarios.stream().map(Usuario::getPontos);
		System.out.println(stream2);

		IntStream stream3 = usuarios.stream().mapToInt(Usuario::getPontos);
		System.out.println(stream3);

		double pontuacaoMedia = usuarios.stream().mapToInt(Usuario::getPontos).average().getAsDouble();
		System.out.println(pontuacaoMedia);

		usuarios.stream().mapToInt(Usuario::getPontos).average().orElseThrow(IllegalStateException::new);

		List<Usuario> vazia = Collections.emptyList();

		double media = vazia.stream().mapToInt(Usuario::getPontos).average().orElse(0.0);
		System.out.println(media);

		OptionalDouble media2 = usuarios.stream().mapToInt(Usuario::getPontos).average();

		double pontuacaoMedia2 = media2.orElse(0.0);
		System.out.println(pontuacaoMedia2);

		Optional<Usuario> max = usuarios.stream().max(Comparator.comparingInt(Usuario::getPontos));
		System.out.println(max);

		Optional<String> maxNome = usuarios.stream().max(Comparator.comparingInt(Usuario::getPontos))
				.map(u -> u.getNome());
		System.out.println(maxNome);
	}
}
