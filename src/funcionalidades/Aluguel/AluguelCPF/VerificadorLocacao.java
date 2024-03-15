package funcionalidades.Aluguel.AluguelCPF;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class VerificadorLocacao {
    public VerificadorLocacao() {

    }

    public void verificarLocacaoVeiculo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a placa do veículo:");
        String placa = scanner.nextLine();

        if (verificarLocacao(placa)) {
            System.out.println("Veículo com placa " + placa + " está indisponível.");
        } else {
            System.out.println("Veículo com placa " + placa + " está disponível.");
        }
    }

    private boolean verificarLocacao(String placa) {
        try (BufferedReader br = new BufferedReader(new FileReader("locacao.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.contains("Placa: " + placa)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
