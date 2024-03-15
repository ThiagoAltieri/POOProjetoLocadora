package funcionalidades.Veiculos;

import classesCadastro.Veiculo;

import java.io.*;
import java.util.Scanner;

public class CadastroVeiculos {
    private static final String CAMINHO_ARQUIVO = "veiculos.txt";
    private Scanner scanner = new Scanner(System.in);

    public void cadastrarVeiculo() {
        System.out.println("Cadastrar veículo");

        System.out.println("Insira o modelo do veículo: ");
        String modelo = scanner.nextLine();

        System.out.println("Insira a marca do veículo: ");
        String marca = scanner.nextLine();

        System.out.println("Insira o ano do veículo: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Insira a placa do veículo: ");
        String placa = scanner.nextLine();

        if (placaExiste(placa)) {
            System.out.println("Veículo com essa placa já cadastrado.");
            return;
        }


        System.out.println("Insira o tipo de veículo (PEQUENO/MEDIO/SUV): ");
        String tipoStr = scanner.nextLine();
        Veiculo.TipoDeVeiculo tipo = Veiculo.TipoDeVeiculo.valueOf(tipoStr.toUpperCase());


        Veiculo veiculo = new Veiculo(modelo, marca, ano, placa, tipo);


        salvarVeiculoNoArquivo(veiculo);
        System.out.println("Veículo cadastrado com sucesso.");
    }

    private boolean placaExiste(String placa) {
        try (Scanner arquivoScanner = new Scanner(new File(CAMINHO_ARQUIVO))) {
            while (arquivoScanner.hasNextLine()) {
                String linha = arquivoScanner.nextLine();
                String[] partes = linha.split(";");
                String placaCadastrada = partes[3];
                if (placaCadastrada.equals(placa)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return false;
    }

    private void salvarVeiculoNoArquivo(Veiculo veiculo) {

        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar arquivo: " + e.getMessage());
                return;
            }
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
            writer.write(veiculo.getModelo() + ";" + veiculo.getMarca() + ";" + veiculo.getAno() + ";" +
                    veiculo.getPlaca() + ";" + veiculo.getTipo());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao salvar veículo no arquivo: " + e.getMessage());
        }
    }
}
