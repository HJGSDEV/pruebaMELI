package co.com.personalsoft.pruebaml.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.personalsoft.pruebaml.model.MutantEntity;
import co.com.personalsoft.pruebaml.utils.Funciones;

@Repository
public class MutantImpl implements IMutantImpl {

	/**
	 * Variable encargada de almacenar los ADN mutantes encontrados
	 */
	private List<String> cadenasMutantesEncontradas;
	
	@Autowired
	private IRegistrosImpl registro;

	/**
	 * Metodo encargado de validar la cadena de ADN
	 * 
	 * @param dna
	 * @return
	 */
	@Override
	public boolean isMutant(String[] dna) {
		Boolean respuesta = false;
		cadenasMutantesEncontradas = new ArrayList<>();
		String[][] matrizDna = Funciones.generarMatriz.apply(dna);
		if (matrizDna == null) {
			System.out.println("Datos de ADN no validos");
			return false;
		}
		
		buscarCadenaOblicuaMatriz(matrizDna);
		buscarCadenaHorizontalMatriz(matrizDna);
		buscarCadenaVerticalMatriz(matrizDna);
		
		if (!cadenasMutantesEncontradas.isEmpty() && cadenasMutantesEncontradas.size() >= 2) {
			cadenasMutantesEncontradas.stream().forEach(x -> System.out.println(x));
			respuesta = true;
		}
		guardarRegistro(dna,respuesta);
		return respuesta;
	}

	/**
	 * Metodo encargado de buscar las cadena de ADN mutante de forma Horizontal en
	 * una matriz
	 *
	 * @param matriz
	 */
	private void buscarCadenaHorizontalMatriz(String[][] matriz) {
		int tamanioMatriz = matriz.length;

		for (int fila = 0; fila < tamanioMatriz; fila++) {
			String[] filaArray = matriz[fila];
			for (int columna = 0; columna < filaArray.length; columna++) {
				String cadenaConcatenada = "";
				String letraIni = filaArray[columna];
				int nextColumna = columna + 1;
				if ((nextColumna < filaArray.length) && (filaArray.length - nextColumna >= 3)) {
					cadenaConcatenada = letraIni;
					for (int columna2 = nextColumna; columna2 < filaArray.length; columna2++) {
						if (letraIni.equals(filaArray[columna2])) {
							cadenaConcatenada = cadenaConcatenada + filaArray[columna2];
						} else {
							break;
						}
					}
					if (cadenaConcatenada.length() >= 4) {
						cadenasMutantesEncontradas.add(cadenaConcatenada);
					}
				}
			}
		}
	}

	/**
	 * Metodo encargado de buscar las cadena de ADN mutante de forma Oblicua
	 * "Diagonal" en una matriz
	 *
	 * @param matrizDna
	 */
	private void buscarCadenaOblicuaMatriz(String[][] matrizDna) {
		int tamanioMatriz = matrizDna.length;
		int nextFila;
		int nextColumna;
		String concatenado, letrainicial;
		for (int filaInicio = 0; filaInicio < tamanioMatriz; filaInicio++) {
			String[] arrayFila1 = matrizDna[filaInicio];
			nextFila = filaInicio + 1;
			concatenado = "";
			if (tamanioMatriz - nextFila < 3) {
				return;
			}
			for (int columna = 0; columna < arrayFila1.length; columna++) {
				nextColumna = columna + 1;
				letrainicial = arrayFila1[columna];
				concatenado = letrainicial;

				for (int fila2 = nextFila; fila2 < matrizDna.length; fila2++) {
					String[] arrayfila2 = matrizDna[fila2];
					if (nextColumna < arrayfila2.length) {
						if (letrainicial.equals(arrayfila2[nextColumna])) {
							concatenado = concatenado + matrizDna[fila2][nextColumna];
						}

						nextColumna = nextColumna + 1;
					}
				}
				if (concatenado.length() >= 4) {
					cadenasMutantesEncontradas.add(concatenado);
				}
			}
		}
	}

	/**
	 * Metodo encargado de buscar las cadena de ADN mutante de forma verical en una
	 * matriz
	 * 
	 * @param matriz
	 */
	private void buscarCadenaVerticalMatriz(String[][] matriz) {
		int tamañoMatriz = matriz.length;
		String letraInicial;
		String cadena = "";
		for (int filaInicial = 0; filaInicial < tamañoMatriz; filaInicial++) {
			String[] filaArray1 = matriz[filaInicial];
			int nextFila = filaInicial + 1;
			// SI el resultado de esta operacion es menor a 3 se finaliza la busqueda del
			// ADN mutante de manera vertical
			if (tamañoMatriz - nextFila < 3) {
				return;
			}
			for (int columna = 0; columna < filaArray1.length; columna++) {
				letraInicial = filaArray1[columna];
				cadena = letraInicial;
				for (int fila2 = nextFila; fila2 < matriz.length; fila2++) {
					String[] filaArray2 = matriz[fila2];
					if (columna < filaArray2.length) {
						if (letraInicial.equals(filaArray2[columna])) {
							cadena = cadena + filaArray2[columna];
						} else {
							break;
						}
					}
				}
				if (cadena.length() >= 4) {
					cadenasMutantesEncontradas.add(cadena);
				}
			}
		}
	}
	/**
	 * metodo encargado de guardar el registro en la base de datos.
	 * @param dna
	 * @param respuesta
	 */
	private void guardarRegistro(String[] dna, Boolean respuesta) {
		MutantEntity entidad = new MutantEntity();
		entidad.setAdn(Arrays.toString(dna));
		entidad.setMutante(respuesta?(short)1:(short)0);
		entidad.setFechaValidacion(new Date());
		String cadena = "";
		for(String rec : cadenasMutantesEncontradas) {
			cadena = cadena + "," +rec;
		}
		entidad.setAdnMutant(cadena);
		registro.save(entidad);
	}
}
