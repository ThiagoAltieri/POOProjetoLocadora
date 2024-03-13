package funcionalidades.Clientes;

import classesCadastro.Cliente;
import classesCadastro.Cliente.TipoDeDocumento;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CadastroClientes {
    private static final String CAMINHO_ARQUIVO = "clientes.txt";
    private Scanner scanner = new Scanner(System.in);

    public void salvarCliente() {
        System.out.println("Cadastrar cliente");
        System.out.println("Insira o nome a ser cadastrado: ");
        String nome = scanner.nextLine();
        System.out.println("Qual documento utilizará para o cadastro: CPF ou CNPJ (insira apenas o nome do documento em letras maiúsculas): ");
        String tipoDocStr = scanner.nextLine();
        TipoDeDocumento tipoDoc = null;
        if (tipoDocStr.equals("CPF")) {
            tipoDoc = TipoDeDocumento.CPF;
        } else if (tipoDocStr.equals("CNPJ")) {
            tipoDoc = TipoDeDocumento.CNPJ;
        } else {
            System.out.println("Tipo de documento inválido");
            return;
        }

        String numeroDeCadastro;
        while (true) {
            System.out.println("Insira o número do documento: ");
            numeroDeCadastro = scanner.nextLine();
            if ((tipoDoc == TipoDeDocumento.CPF && numeroDeCadastro.length() != 11) || (tipoDoc == TipoDeDocumento.CNPJ && numeroDeCadastro.length() != 14)) {
                System.out.println("Número inserido é inválido");
            } else if (numeroDeCadastroExiste(numeroDeCadastro)) {
                System.out.println("Cliente já cadastrado");
                return; // Sai do método se o cliente já estiver cadastrado
            } else {
                break; // Sai do loop se o número do documento for válido e não existir no arquivo
            }
        }

        Cliente cliente = new Cliente(nome, numeroDeCadastro, tipoDoc);
        salvarClienteNoArquivo(cliente);
        System.out.println("Cadastro concluído");
    }

    private boolean numeroDeCadastroExiste(String numeroDeCadastro) {
        try (Scanner arquivoScanner = new Scanner(new File(CAMINHO_ARQUIVO))) {
            while (arquivoScanner.hasNextLine()) {
                String linha = arquivoScanner.nextLine();
                String[] partes = linha.split(";");
                String numeroCadastrado = partes[1]; // Supondo que o número de cadastro está na segunda posição
                if (numeroCadastrado.equals(numeroDeCadastro)) {
                    return true; // Retorna true se o número de cadastro já existir no arquivo
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return false; // Retorna false se o número de cadastro não for encontrado
    }

    private void salvarClienteNoArquivo(Cliente cliente) {
        // Verifica se o arquivo já existe e cria se necessário
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Erro ao criar arquivo: " + e.getMessage());
                return;
            }
        }

        // Salva o cliente no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
            writer.write(cliente.getNome() + ";" + cliente.getNumeroDeCadastro() + ";" + cliente.getTipoDoc());
            writer.newLine();
            System.out.println("Cliente salvo no arquivo com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar cliente no arquivo: " + e.getMessage());
        }
    }
}
