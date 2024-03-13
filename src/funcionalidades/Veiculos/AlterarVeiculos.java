package funcionalidades.Veiculos;

import classesCadastro.Veiculo;

import java.io.*;
import java.util.Scanner;

public class AlterarVeiculos {
    private static final String CAMINHO_ARQUIVO = "veiculos.txt";
    private Scanner scanner = new Scanner(System.in);

    public void alterarVeiculo() {
        System.out.println("Alterar veículo");

        System.out.println("Insira a placa do veículo que deseja alterar: ");
        String placa = scanner.nextLine();

        try {
            // Abrir o arquivo para leitura
            File arquivo = new File(CAMINHO_ARQUIVO);
            File arquivoTemporario = new File("temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemporario));

            String linha;
            boolean veiculoEncontrado = false;

            // Ler o arquivo linha por linha
            while ((linha = reader.readLine()) != null) {
                // Dividir a linha em partes
                String[] partes = linha.split(";");
                String placaCadastrada = partes[3]; // Supondo que a placa está na quarta posição

                // Se a placa na linha corresponder à placa fornecida pelo usuário, solicitar novas informações
                if (placaCadastrada.equals(placa)) {
                    veiculoEncontrado = true;
                    System.out.println("Veículo encontrado. Insira as novas informações:");

                    System.out.println("Insira o novo modelo do veículo: ");
                    String novoModelo = scanner.nextLine();

                    System.out.println("Insira a nova marca do veículo: ");
                    String novaMarca = scanner.nextLine();

                    System.out.println("Insira o novo ano do veículo: ");
                    int novoAno = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner

                    System.out.println("Insira o novo tipo de veículo (PEQUENO/MEDIO/SUV): ");
                    String novoTipoStr = scanner.nextLine();
                    Veiculo.TipoDeVeiculo novoTipo = Veiculo.TipoDeVeiculo.valueOf(novoTipoStr.toUpperCase());

                    // Escrever as novas informações no arquivo temporário
                    writer.write(novoModelo + ";" + novaMarca + ";" + novoAno + ";" +
                            placa + ";" + novoTipo);
                    writer.newLine();
                } else {
                    // Se não for a placa do veículo a ser alterado, copiar a linha original para o arquivo temporário
                    writer.write(linha);
                    writer.newLine();
                }
            }

            // Fechar os arquivos
            reader.close();
            writer.close();

            // Renomear o arquivo temporário para substituir o arquivo original
            if (veiculoEncontrado) {
                arquivo.delete();
                arquivoTemporario.renameTo(arquivo);
                System.out.println("Veículo alterado com sucesso.");
            } else {
                System.out.println("Veículo não encontrado com a placa fornecida.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler/escrever no arquivo: " + e.getMessage());
        }
    }
}
