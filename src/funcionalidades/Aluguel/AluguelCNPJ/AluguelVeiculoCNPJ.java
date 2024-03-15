package funcionalidades.Aluguel.AluguelCNPJ;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AluguelVeiculoCNPJ {
    public void alugarVeiculoCNPJ(String placa, String cnpj) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira o local de retirada do veículo:");
        String localRetirada = scanner.nextLine();

        System.out.println("Insira a data de retirada do veículo (formato DD/MM/AAAA):");
        String dataHoraRetirada = scanner.nextLine();

        System.out.println("Escolha o tipo de veículo (PEQUENO, MEDIO, SUV):");
        String tipoVeiculo = scanner.nextLine();

        salvarLocacaoCNPJ(placa, localRetirada, dataHoraRetirada, tipoVeiculo, cnpj);

        System.out.println("Veículo com placa " + placa + " alugado com sucesso!");
        System.out.println("Local de retirada: " + localRetirada);
        System.out.println("Data e hora de retirada: " + dataHoraRetirada);
        System.out.println("Tipo de veículo: " + tipoVeiculo);
    }

    private void salvarLocacaoCNPJ(String placa, String localRetirada, String dataHoraRetirada, String tipoVeiculo, String cnpj) {
        LocalDateTime dataAtual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataHoraFormatada = dataAtual.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("locacao.txt", true))) {
            writer.write("Placa: " + placa + ", Local de Retirada: " + localRetirada + ", Data e Hora de Retirada: " + dataHoraRetirada + ", Tipo de Veículo: " + tipoVeiculo + ", CNPJ: " + cnpj + ", Data da Locação: " + dataHoraFormatada);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
