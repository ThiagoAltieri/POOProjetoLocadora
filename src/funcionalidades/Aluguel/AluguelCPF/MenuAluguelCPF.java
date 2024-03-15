package funcionalidades.Aluguel.AluguelCPF;

import java.util.Scanner;

public class MenuAluguelCPF {
    private final ValidaClienteEVeiculo validaClienteEVeiculo;
    private final AluguelCPF aluguelCPF;

    public MenuAluguelCPF() {
        this.validaClienteEVeiculo = new ValidaClienteEVeiculo();
        this.aluguelCPF = new AluguelCPF();
    }

    public void iniciarMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Menu de Aluguel de Carros");
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Iniciar Aluguel");
            System.out.println("2. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    iniciarAluguel();
                    break;
                case 2:
                    System.out.println("Saindo do Menu de Aluguel de Carros. Até mais!");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void iniciarAluguel() {
        System.out.println("Iniciando processo de aluguel...");
        validaClienteEVeiculo.iniciarAluguel();
        aluguelCPF.aluguelCPF();
    }

    public static void main(String[] args) {
        MenuAluguelCPF menuAluguelCPF = new MenuAluguelCPF();
        menuAluguelCPF.iniciarMenu();
    }
}