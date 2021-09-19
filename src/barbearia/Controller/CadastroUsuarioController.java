package barbearia.Controller;

import barbearia.Controller.DAO.UsuarioDao;
import barbearia.model.Usuario;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class CadastroUsuarioController implements Initializable {

    @FXML
    private TextField senhau;
    @FXML
    private Button pesquisaru;
    @FXML
    private Label loginlu;
    @FXML
    private Label idlu;
    @FXML
    private Label labelBarber;
    @FXML
    private Button novoU;
    @FXML
    private Button excluiru;
    @FXML
    private Label senhalu;
    @FXML
    private TextField idu;
    @FXML
    private TextField loginu;
    @FXML
    private Label labelShop;
    @FXML
    private Button editaru;
    @FXML
    private TableView<Usuario> tableU;
    @FXML
    private TableColumn<Usuario, Integer> columnId;
    @FXML
    private TableColumn<Usuario, String> columnLogin;
    @FXML
    private TableColumn<Usuario, String> ColumnSenha;
    @FXML 
    private Button atulizarTabela;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        novoU.setOnMouseClicked((MouseEvent e) -> {
            UsuarioDao userDao = new UsuarioDao();
            Usuario user = new Usuario(loginu.getText(), senhau.getText());
            try {
                userDao.incluir(user);
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Falha no cadastro");
            }
        });

        excluiru.setOnMouseClicked((MouseEvent e) -> {
            UsuarioDao userDao = new UsuarioDao();
            int id = parseInt(idu.getText());
            try {
                userDao.Excluir(id);
                JOptionPane.showMessageDialog(null, "Cadastro excluido com Sucesso");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Falha ao excluir cadastro");
            }
        });

        editaru.setOnMouseClicked((MouseEvent e) -> {
            UsuarioDao userDao = new UsuarioDao();
            Usuario user = new Usuario(parseInt(idu.getText()), loginu.getText(), senhau.getText());
            try {
                userDao.Editar(user);
                JOptionPane.showMessageDialog(null, "Cadastro editado com Sucesso");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Falha ao atualizar cadastro");
            }
        });
        try {PopularTabela();} catch (Exception ex) {}
        
        pesquisaru.setOnMouseClicked((MouseEvent e) -> {
            try {PesquisarTabela();} catch (Exception ex) {}
        });
        
        atulizarTabela.setOnMouseClicked((MouseEvent e) -> {
            try {PopularTabela();} catch (Exception ex) {}
        });
    }

    public void PopularTabela() throws Exception {
        UsuarioDao userDao = new UsuarioDao();
        List<Usuario> users = userDao.getList();
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        ColumnSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));

        tableU.setItems(FXCollections.observableArrayList(users));
    }
    
    public void PesquisarTabela() throws Exception {
        UsuarioDao userDao = new UsuarioDao();
        List<Usuario> users = userDao.Pesquisar(parseInt(idu.getText()));
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        ColumnSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));

        tableU.setItems(FXCollections.observableArrayList(users));
    }


}
