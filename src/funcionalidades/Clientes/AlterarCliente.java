package funcionalidades.Clientes;

import classesCadastro.Cliente;
import classesCadastro.Cliente.TipoDeDocumento;

import java.io.*;
import java.util.Scanner;

public class AlterarCliente {
    private static final String CAMINHO_ARQUIVO = "clientes.txt";
    private Scanner scanner = new Scanner(System.in);

    public void alterarClientePorCPF(String cpf) {
        try {
            File arquivo = new File(CAMINHO_ARQUIVO);
            if (!arquivo.exists()) {
                System.out.println("Arquivo de clientes não encontrado.");
                return;
            }

            File arquivoTemp = new File("clientes_temp.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoTemp));

            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                String numeroDeCadastro = partes[1]; // Supondo que o CPF está na segunda posição
                if (numeroDeCadastro.equals(cpf)) {
                    // Cliente encontrado, solicite as novas informações e escreva no arquivo temporário
                    System.out.println("Cliente encontrado. Insira as novas informações:");
                    System.out.println("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    System.out.println("Novo número de cadastro: ");
                    String novoNumeroDeCadastro = scanner.nextLine();

                    linha = novoNome + ";" + novoNumeroDeCadastro + ";" + partes[2]; // Mantém o tipo de documento

                    System.out.println("Cliente alterado com sucesso.");
                }
                writer.write(linha);
                writer.newLine();
            }

            reader.close();
            writer.close();

            // Renomeia o arquivo temporário para o nome original
            arquivo.delete();
            arquivoTemp.renameTo(arquivo);

        } catch (IOException e) {
            System.out.println("Erro ao alterar cliente: " + e.getMessage());
        }
    }
}
