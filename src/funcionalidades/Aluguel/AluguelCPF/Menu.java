package funcionalidades.Aluguel.AluguelCPF;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private VerificadorCadastro verificadorCadastro;
    private VerificadorLocacao verificadorLocacao;
    private AluguelVeiculo aluguelVeiculo;

    public Menu() {
        scanner = new Scanner(System.in);
        verificadorCadastro = new VerificadorCadastro();
        verificadorLocacao = new VerificadorLocacao();
        aluguelVeiculo = new AluguelVeiculo();
    }

    public void executarMenu() {
        int opcao;
        do {
            System.out.println("MENU");
            System.out.println("Siga as verificações em ordem depois " +
                    "estará pronto para alugar um veículo");
            System.out.println("1. Verificar cadastro");
            System.out.println("2. Verificar locação");
            System.out.println("3. Alugar veículo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    verificadorCadastro.iniciarAluguel();
                    break;
                case 2:
                    verificadorLocacao.verificarLocacaoVeiculo();
                    break;
                case 3:
                    scanner.nextLine(); // Limpar o buffer
                    System.out.println("Por favor, insira a placa do veículo:");
                    String placa = scanner.nextLine();
                    System.out.println("Por favor, insira o seu CPF:");
                    String cpf = scanner.nextLine();
                    aluguelVeiculo.alugarVeiculo(placa, cpf);
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }

    public void fecharScanner() {
        scanner.close();
    }
}
