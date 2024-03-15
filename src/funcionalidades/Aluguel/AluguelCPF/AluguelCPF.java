package funcionalidades.Aluguel.AluguelCPF;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AluguelCPF {
    private static final String LOCACAO_FILE = "locacao.txt";
    private static final String DISPONIVEL = "disponivel";
    private static final String INDISPONIVEL = "indisponivel";

    public void aluguelCPF() {
        File locacaoFile = new File(LOCACAO_FILE);

        // Verifica se o arquivo de locação existe, caso contrário, cria um novo arquivo
        if (!locacaoFile.exists()) {
            try {
                locacaoFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        try (Scanner scanner = new Scanner(System.in);
             BufferedWriter writer = new BufferedWriter(new FileWriter(locacaoFile, true))) {

            System.out.println("Digite o seu CPF:");
            String cpf = scanner.nextLine();

            System.out.println("Digite a placa do veículo que deseja alugar:");
            String placa = scanner.nextLine();

            if (veiculoDisponivel(placa)) {
                // Escreve no arquivo de locação: CPF, placa do veículo e status "indisponivel"
                writer.write(cpf + "," + placa + "," + INDISPONIVEL);
                writer.newLine();
                System.out.println("Locação registrada com sucesso! O veículo agora está indisponível.");
            } else {
                System.out.println("Veículo já está alugado. Não é possível realizar a locação.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean veiculoDisponivel(String placa) {
        try (BufferedReader reader = new BufferedReader(new FileReader(LOCACAO_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[1].trim().equals(placa) && parts[2].trim().equals(INDISPONIVEL)) {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

