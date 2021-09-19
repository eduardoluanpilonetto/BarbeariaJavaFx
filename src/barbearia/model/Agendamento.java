package barbearia.model;
public class Agendamento {
    private int id;
    private String NomeCliente;
    private String NomeServico;
    private String data;

    public Agendamento() {
    }

    public Agendamento(String NomeCliente, String NomeServico, String data) {
        this.NomeCliente = NomeCliente;
        this.NomeServico = NomeServico;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return NomeCliente;
    }

    public void setNomeCliente(String NomeCliente) {
        this.NomeCliente = NomeCliente;
    }

    public String getNomeServico() {
        return NomeServico;
    }

    public void setNomeServico(String NomeServico) {
        this.NomeServico = NomeServico;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Agendamento{" + "id=" + id + ", NomeCliente=" + NomeCliente + ", NomeServico=" + NomeServico + ", data=" + data + '}';
    }
    
}   