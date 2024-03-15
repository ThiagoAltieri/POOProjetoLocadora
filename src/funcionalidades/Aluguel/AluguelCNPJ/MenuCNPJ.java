package funcionalidades.Aluguel.AluguelCNPJ;

import java.util.Scanner;

public class MenuCNPJ {
    private static final Scanner scanner = new Scanner(System.in);
    private static final VerificadorCadastroCNPJ verificadorCadastro = new VerificadorCadastroCNPJ();
    private static final VerificadorLocacaoCNPJ verificadorLocacao = new VerificadorLocacaoCNPJ();
    private static final AluguelVeiculoCNPJ aluguelVeiculo = new AluguelVeiculoCNPJ();

    public static void executarMenu() {
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
                    verificadorLocacao.verificarLocacaoVeiculoCNPJ();
                    break;
                case 3:
                    System.out.println("Por favor, insira a placa do veículo:");
                    scanner.nextLine(); // Limpar o buffer
                    String placa = scanner.nextLine();
                    System.out.println("Por favor, insira o seu CNPJ:");
                    String cnpj = scanner.nextLine();
                    aluguelVeiculo.alugarVeiculoCNPJ(placa, cnpj);
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }

    public static void fecharScanner() {
        scanner.close();
    }
}
