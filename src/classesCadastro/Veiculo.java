package classesCadastro;

import java.util.HashSet;
import java.util.Set;

public class Veiculo {
    private String modelo;
    private String marca;
    private Integer ano;
    private String placa;
    public enum TipoDeVeiculo {
        PEQUENO,
        MEDIO,
        SUV
    }
    public TipoDeVeiculo tipo;

    // Conjunto para manter o controle das placas dos veículos
    private static Set<String> placasRegistradas = new HashSet<>();

    public Veiculo(String modelo, String marca, Integer ano, String placa, TipoDeVeiculo tipo) {
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;

        // Verifica se a placa já está registrada
        if (placasRegistradas.contains(placa)) {
            throw new IllegalArgumentException("Placa já registrada para outro veículo.");
        }
        this.placa = placa;
        placasRegistradas.add(placa);

        // Valida e cadastra o tipo de veículo
        validarETipoDeVeiculo(tipo);
        this.tipo = tipo;
    }

    // Métodos getter e setter para os campos
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        // Remover a placa anterior do conjunto de placas registradas
        placasRegistradas.remove(this.placa);

        // Adicionar a nova placa ao conjunto de placas registradas
        placasRegistradas.add(placa);

        this.placa = placa;
    }

    public TipoDeVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeVeiculo tipo) {
        validarETipoDeVeiculo(tipo);
        this.tipo = tipo;
    }

    // Método para validar o tipo de veículo
    private void validarETipoDeVeiculo(TipoDeVeiculo tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de veículo não pode ser nulo.");
        }
        // Adicione aqui outras validações necessárias para o enum TipoDeVeiculo
    }
}
