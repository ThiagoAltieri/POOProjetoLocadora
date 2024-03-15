package funcionalidades;

import funcionalidades.Clientes.AlterarCliente;
import funcionalidades.Clientes.CadastroClientes;
import funcionalidades.Veiculos.AlterarVeiculos;
import funcionalidades.Veiculos.BuscarVeiculos;
import funcionalidades.Veiculos.CadastroVeiculos;
import funcionalidades.Aluguel.AluguelCPF.Menu;
import funcionalidades.Aluguel.AluguelCNPJ.MenuCNPJ;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CadastroClientes cadastroClientes = new CadastroClientes();
        AlterarCliente alterarCliente = new AlterarCliente();
        CadastroVeiculos cadastroVeiculos = new CadastroVeiculos();
        BuscarVeiculos buscarVeiculos = new BuscarVeiculos();
        AlterarVeiculos alterarVeiculos = new AlterarVeiculos();

        Menu menuCPF = new Menu();
        MenuCNPJ menuCNPJ = new MenuCNPJ();

        int opcao;
        do {
            System.out.println("=== ALUGUEL VEÍCULOS ===");
            System.out.println("1. Cadastrar veículo");
            System.out.println("2. Alterar um veículo cadastrado");
            System.out.println("3. Buscar um veículo por parte do nome");
            System.out.println("4. Cadastrar cliente (pessoa física e jurídica)");
            System.out.println("5. Alterar o cliente (pessoa física e jurídica)");
            System.out.println("6. Alugar um veículo para pessoa física e jurídica");
            System.out.println("7. Devolver um veículo para pessoa física e jurídica");
            System.out.println("8. Sair do programa");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastroVeiculos.cadastrarVeiculo();
                    break;
                case 2:
                    alterarVeiculos.alterarVeiculo();
                    break;
                case 3:
                    buscarVeiculos.buscarVeiculosPorNome();
                    break;
                case 4:
                    cadastroClientes.salvarCliente();
                    break;
                case 5:
                    System.out.println("Informe o CPF do cliente que deseja alterar:");
                    String cpf = scanner.nextLine();
                    alterarCliente.alterarClientePorCPF(cpf);
                    break;
                case 6:
                    System.out.println("Você é uma pessoa física ou pessoa jurídica?");
                    System.out.println("1. Pessoa Física");
                    System.out.println("2. Pessoa Jurídica");
                    int tipoCliente = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoCliente == 1) {
                        menuCPF.executarMenu();
                    } else if (tipoCliente == 2) {
                        menuCNPJ.executarMenu();
                    } else {
                        System.out.println("Opção inválida. Escolha novamente.");
                    }
                    break;
                case 7:
                    // Chamar método para devolver veículo
                    break;
                case 8:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
            }
        } while (opcao != 8);

        scanner.close();
    }
}
