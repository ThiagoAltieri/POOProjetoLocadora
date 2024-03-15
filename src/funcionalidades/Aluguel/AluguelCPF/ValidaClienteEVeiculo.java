package funcionalidades.Aluguel.AluguelCPF;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ValidaClienteEVeiculo {
    private static final String CLIENTES_FILE = "clientes.txt";
    private static final String VEICULOS_FILE = "veiculos.txt";

    public void iniciarAluguel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o seu CPF:");
        String cpf = scanner.nextLine();

        if (clienteCadastrado(cpf)) {
            System.out.println("Cliente cadastrado!");
            System.out.println("Digite a placa do veículo que deseja alugar:");
            String placa = scanner.nextLine();

            if (veiculoCadastrado(placa)) {
                System.out.println("Veículo disponível para aluguel. Pronto para começar!");
            } else {
                System.out.println("Veículo não cadastrado. Não é possível prosseguir com o aluguel.");
            }
        } else {
            System.out.println("Cliente não cadastrado. Não é possível prosseguir com o aluguel.");
        }
    }

    private boolean clienteCadastrado(String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CLIENTES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].trim().equals(cpf)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean veiculoCadastrado(String placa) {
        try (BufferedReader reader = new BufferedReader(new FileReader(VEICULOS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].trim().equals(placa)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

