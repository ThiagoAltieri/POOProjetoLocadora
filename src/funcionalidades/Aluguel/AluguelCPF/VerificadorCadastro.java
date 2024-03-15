package funcionalidades.Aluguel.AluguelCPF;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class VerificadorCadastro {
    private boolean verificarCPF(String cpf) {
        try (BufferedReader br = new BufferedReader(new FileReader("clientes.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.contains(cpf)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean verificarPlaca(String placa) {
        try (BufferedReader br = new BufferedReader(new FileReader("veiculos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.contains(placa)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void iniciarAluguel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, insira o seu CPF: ");
        String cpf = scanner.nextLine();

        if (!verificarCPF(cpf)) {
            System.out.println("Cliente não cadastrado. Por favor, cadastre-se primeiro.");
            return;
        }

        System.out.println("Por favor, insira a placa do veículo que deseja alugar: ");
        String placa = scanner.nextLine();

        if (!verificarPlaca(placa)) {
            System.out.println("Veículo não cadastrado. Por favor, cadastre-o primeiro.");
            return;
        }

        System.out.println("Tudo pronto para próxima verificação");
    }
}
