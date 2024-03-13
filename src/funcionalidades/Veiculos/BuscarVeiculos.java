package funcionalidades.Veiculos;

import java.io.*;
import java.util.Scanner;

public class BuscarVeiculos {
    private static final String CAMINHO_ARQUIVO = "Veiculos.txt";
    private Scanner scanner = new Scanner(System.in);

    public void buscarVeiculosPorNome() {
        System.out.println("Digite parte do nome do veículo a ser buscado: ");
        String busca = scanner.nextLine().toLowerCase();

        System.out.println("Veículos encontrados:");
        boolean encontrado = false;

        try (Scanner arquivoScanner = new Scanner(new File(CAMINHO_ARQUIVO))) {
            while (arquivoScanner.hasNextLine()) {
                String linha = arquivoScanner.nextLine();
                String[] partes = linha.split(";");
                String modelo = partes[0].toLowerCase();
                String marca = partes[1].toLowerCase();

                if (modelo.contains(busca) || marca.contains(busca)) {
                    String ano = partes[2];
                    String placa = partes[3];
                    String tipo = partes[4];

                    System.out.println("Modelo: " + modelo);
                    System.out.println("Marca: " + marca);
                    System.out.println("Ano: " + ano);
                    System.out.println("Placa: " + placa);
                    System.out.println("Tipo: " + tipo);
                    System.out.println("------------------------");
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("Nenhum veículo encontrado com a busca '" + busca + "'.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de veículos não encontrado.");
        }
    }
}
