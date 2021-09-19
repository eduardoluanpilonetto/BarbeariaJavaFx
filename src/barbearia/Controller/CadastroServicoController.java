package barbearia.Controller;

import barbearia.Controller.DAO.ServicoDao;
import barbearia.model.Servico;
import static java.lang.Float.parseFloat;
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


public class CadastroServicoController implements Initializable {

    @FXML private Label idls;
    @FXML private Label labelBarber;
    @FXML private Label loginls;
    @FXML private Label senhals;
    @FXML private TextField nomes;
    @FXML private TextField ids;
    @FXML private TextField valors;
    @FXML private Button pesquisars;
    @FXML private Button excluirs;
    @FXML private Button novos;
    @FXML private Label labelShop;
    @FXML private Button editars;
    @FXML private Button atulizarTabela;
    @FXML private TableView<Servico> tableS;
    @FXML private TableColumn<Servico, Integer> IDC;
    @FXML private TableColumn<Servico, String> NOMEC;
    @FXML private TableColumn<Servico, Float> VALORC;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         novos.setOnMouseClicked((MouseEvent e) -> {
            ServicoDao servicoDao = new ServicoDao();
            Servico servico = new Servico(nomes.getText(),parseFloat(valors.getText()));
            try {
                servicoDao.incluir(servico);
                JOptionPane.showMessageDialog(null,"Cadastrado com sucesso");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Falha no cadastro");
            }
                
        });
        
        excluirs.setOnMouseClicked((MouseEvent e) -> {
            ServicoDao servicoDao = new ServicoDao();
            int id = parseInt(ids.getText());
            try {
                servicoDao.Excluir(id);
                JOptionPane.showMessageDialog(null,"Cadastro excluido com Sucesso");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Falha ao excluir cadastro");
            }
        });
        
        editars.setOnMouseClicked((MouseEvent e) -> {
            ServicoDao servicoDao = new ServicoDao();
            Servico servico = new Servico(parseInt(ids.getText()),nomes.getText(),parseFloat(valors.getText()));
            try {
                servicoDao.Editar(servico);
                JOptionPane.showMessageDialog(null,"Cadastro editado com Sucesso");
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Falha ao atualizar cadastro");
            }
        });
        try {PopularTabela();} catch (Exception ex) {}
        pesquisars.setOnMouseClicked((MouseEvent e) -> {
             try {PesquisarTabela();} catch (Exception ex) {}
        });
        
        atulizarTabela.setOnMouseClicked((MouseEvent e) -> {
            try {PopularTabela();} catch (Exception ex) {}
        });
    }    
    
    public void PopularTabela() throws Exception{
            ServicoDao servocoDao = new ServicoDao();
            List<Servico> servicos = servocoDao.getList();
            IDC.setCellValueFactory(new PropertyValueFactory<>("id"));
            NOMEC.setCellValueFactory(new PropertyValueFactory<>("nome"));
            VALORC.setCellValueFactory(new PropertyValueFactory<>("valor"));
            
            tableS.setItems(FXCollections.observableArrayList(servicos));
    }
    
    public void PesquisarTabela() throws Exception{
            ServicoDao servocoDao = new ServicoDao();
            List<Servico> servicos = servocoDao.Pesquisar(parseInt(ids.getText()));
            IDC.setCellValueFactory(new PropertyValueFactory<>("id"));
            NOMEC.setCellValueFactory(new PropertyValueFactory<>("nome"));
            VALORC.setCellValueFactory(new PropertyValueFactory<>("valor"));
            
            tableS.setItems(FXCollections.observableArrayList(servicos));
    }
    
}
