package barbearia.Controller;

import barbearia.Controller.DAO.AgendamentoDao;
import barbearia.Controller.DAO.ClienteDao;
import barbearia.Controller.DAO.ServicoDao;
import barbearia.RevisaoBd.Cliente;
import barbearia.RevisaoBd.Servico;
import barbearia.RevisaoBd.Usuario;
import barbearia.model.Agendamento;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author dudup
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    private MenuItem AcessarCliente;
    @FXML
    private Menu Menusevico;
    @FXML
    private TextField data;
    @FXML
    private TextField textid;
    @FXML
    private Menu Menuusuario;
    @FXML
    private TableView<Agendamento> tableView;
    @FXML
    private ChoiceBox<String> boxCliente;
    @FXML
    private ChoiceBox<String> boxServico;
    @FXML
    private MenuBar menu;
    @FXML
    private MenuItem AcessarServico;
    @FXML
    private MenuItem AcessarUsuario;
    @FXML
    private Menu Menucliente;
    @FXML
    private Button Agendar;
    @FXML
    private Button Sair;
    @FXML
    private Button cancelarA;
    @FXML
    private Button pesquisar;
    @FXML
    private TableColumn<Agendamento, Integer> um;
    @FXML
    private TableColumn<Agendamento, String> dois;
    @FXML
    private TableColumn<Agendamento, String> tres;
    @FXML
    private TableColumn<Agendamento, String> quatro;
    @FXML 
    private Button atulizarTabela;
    private ObservableList<String> clientesList;
    private ObservableList<String> servicosList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            popularBoxCliente();
            popularBoxServicos();
            PopularTabela();
        } catch (Exception ex) {
        }
        cancelarA.setOnMouseClicked((MouseEvent e) -> {
            AgendamentoDao agendamentoDAO = new AgendamentoDao();
            int id = parseInt(textid.getText());
            try {
                agendamentoDAO.Excluir(id);
                JOptionPane.showMessageDialog(null, "Cadastro excluido com Sucesso");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Falha ao excluir cadastro");
            }
        });
        Agendar.setOnMouseClicked((MouseEvent e) -> {
            AgendamentoDao ageDao = new AgendamentoDao();
            String cliente = boxCliente.getValue();
            String servico = boxServico.getValue();
            Agendamento age = new Agendamento(cliente, servico, data.getText());
            try {
                ageDao.incluir(age);
            } catch (Exception ex) {
            }
        });

        Sair.setOnMouseClicked((MouseEvent e) -> {
            System.exit(0);
        });
        
        pesquisar.setOnMouseClicked((MouseEvent e) -> {
            try {PesquisarTabela();} catch (Exception ex) {}
        });
        atulizarTabela.setOnMouseClicked((MouseEvent e) -> {
            try {PopularTabela();} catch (Exception ex) {}
        });
    }

    @FXML
    private void handlerTelaCliente(javafx.event.ActionEvent event) throws IOException {
        Cliente novaTela = new Cliente();
        try {
            novaTela.start(new Stage());
        } catch (Exception ex) {
        }
    }

    @FXML
    private void handlerTelaServico(javafx.event.ActionEvent event) throws IOException {
        Servico novaTela = new Servico();
        try {
            novaTela.start(new Stage());
        } catch (Exception ex) {
        }
    }

    @FXML
    private void handlerTelaUsuario(javafx.event.ActionEvent event) throws IOException {
        Usuario novaTela = new Usuario();
        try {
            novaTela.start(new Stage());
        } catch (Exception ex) {
        }
    }

    public void popularBoxCliente() throws Exception {
        ClienteDao clienteDao = new ClienteDao();
        List<String> clientes = clienteDao.listarNomeClientes();
        clientesList = FXCollections.observableArrayList(clientes);
        boxCliente.setItems(clientesList);
    }

    public void popularBoxServicos() throws Exception {
        ServicoDao servicoDao = new ServicoDao();
        List<String> sevicos = servicoDao.listarNomeServico();
        servicosList = FXCollections.observableArrayList(sevicos);
        boxServico.setItems(servicosList);
    }

    public void PopularTabela() throws Exception {
        AgendamentoDao ageDao = new AgendamentoDao();
        List<Agendamento> age = ageDao.getList();
        um.setCellValueFactory(new PropertyValueFactory<>("id"));
        dois.setCellValueFactory(new PropertyValueFactory<>("NomeCliente"));
        tres.setCellValueFactory(new PropertyValueFactory<>("NomeServico"));
        quatro.setCellValueFactory(new PropertyValueFactory<>("data"));

        tableView.setItems(FXCollections.observableArrayList(age));
    }
    
    public void PesquisarTabela() throws Exception {
        AgendamentoDao ageDao = new AgendamentoDao();
        List<Agendamento> age = ageDao.Pesquisa(parseInt(textid.getText()));
        um.setCellValueFactory(new PropertyValueFactory<>("id"));
        dois.setCellValueFactory(new PropertyValueFactory<>("NomeCliente"));
        tres.setCellValueFactory(new PropertyValueFactory<>("NomeServico"));
        quatro.setCellValueFactory(new PropertyValueFactory<>("data"));

        tableView.setItems(FXCollections.observableArrayList(age));
    }
}
