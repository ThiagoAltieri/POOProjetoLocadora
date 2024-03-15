package funcionalidades.Devolucao;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DevolucaoVeiculo {

    private static final Map<String, Double> PRECOS_VEICULOS = new HashMap<>();

    static {
        PRECOS_VEICULOS.put("PEQUENO", 100.0);
        PRECOS_VEICULOS.put("MEDIO", 150.0);
        PRECOS_VEICULOS.put("SUV", 200.0);
    }

    public void devolverVeiculo(String placa, String tipoCliente) {
        try {
            File inputFile = new File("locacao.txt");
            File tempFile = new File("locacao_temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String linha;
            boolean veiculoEncontrado = false;
            double valorPago = 0.0;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("Placa: " + placa)) {
                    veiculoEncontrado = true;
                    String[] partes = linha.split(", ");
                    for (String parte : partes) {
                        if (parte.startsWith("Data da Locação:")) {
                            String dataLocacaoStr = parte.replace("Data da Locação: ", "");
                            String tipoVeiculo = partes[3].replace("Tipo de Veículo: ", "");
                            valorPago = calcularValorAluguel(tipoVeiculo, dataLocacaoStr);
                            System.out.println("Preço pago pelo aluguel: R$" + valorPago);
                            break;
                        }
                    }
                    continue;
                }
                writer.write(linha + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();

            if (!veiculoEncontrado) {
                System.out.println("Veículo com placa " + placa + " não encontrado nos registros de locação.");
                return;
            }

            inputFile.delete();
            tempFile.renameTo(inputFile);

            System.out.println("Veículo com placa " + placa + " devolvido com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double calcularValorAluguel(String tipoVeiculo, String dataLocacaoStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dataLocacao = LocalDateTime.parse(dataLocacaoStr, formatter);
        LocalDate dataAtual = LocalDate.now();
        long diasAlugados = calcularDiasAtraso(dataLocacaoStr);

        double valorDiaria = PRECOS_VEICULOS.getOrDefault(tipoVeiculo, 0.0);
        double valorTotal = diasAlugados * valorDiaria;

        return valorTotal;
    }

    private long calcularDiasAtraso(String dataLocacaoStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dataLocacao = LocalDateTime.parse(dataLocacaoStr, formatter);
        LocalDateTime dataAtual = LocalDateTime.now();

        long horasAtraso = dataLocacao.until(dataAtual, java.time.temporal.ChronoUnit.HOURS);
        long diasAtraso = horasAtraso / 24;
        if (horasAtraso % 24 != 0) {
            diasAtraso++;
        }
        return diasAtraso;
    }
}
