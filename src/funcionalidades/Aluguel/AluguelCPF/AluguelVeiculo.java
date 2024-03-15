package funcionalidades.Aluguel.AluguelCPF;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AluguelVeiculo {
    public void alugarVeiculo(String placa, String cpf) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira o local de retirada do veículo:");
        String localRetirada = scanner.nextLine();

        System.out.println("Insira a data de retirada do veículo (formato DD/MM/AAAA):");
        String dataRetirada = scanner.nextLine();

        System.out.println("Escolha o tipo de veículo (PEQUENO, MEDIO, SUV):");
        String tipoVeiculo = scanner.nextLine();

        salvarLocacao(placa, localRetirada, dataRetirada, tipoVeiculo, cpf);

        System.out.println("Veículo com placa " + placa + " alugado com sucesso!");
        System.out.println("Local de retirada: " + localRetirada);
        System.out.println("Data de retirada: " + dataRetirada);
        System.out.println("Tipo de veículo: " + tipoVeiculo);
    }

    private void salvarLocacao(String placa, String localRetirada, String dataRetirada, String tipoVeiculo, String cpf) {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataAtual.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("locacao.txt", true))) {
            writer.write("Placa: " + placa + ", Local de Retirada: " + localRetirada + ", Data de Retirada: " + dataRetirada + ", Tipo de Veículo: " + tipoVeiculo + ", CPF: " + cpf + ", Data da Locação: " + dataFormatada);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
