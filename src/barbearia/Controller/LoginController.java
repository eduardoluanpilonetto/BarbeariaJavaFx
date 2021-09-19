package barbearia.Controller;

import barbearia.Controller.DAO.UsuarioDao;
import barbearia.RevisaoBd.Login;
import barbearia.RevisaoBd.TelaPrincipal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

    @FXML
    private TextField TextLogin;
    @FXML
    private PasswordField TextSenha;
    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnSair;
    @FXML
    private Label labelSenha;
    @FXML
    private Label labelLogin;
    @FXML
    private Label labelShop;
    @FXML
    private Label labelBarber;
    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSair.setOnMouseClicked((MouseEvent e) -> {
            System.exit(0);
        });
        btnEntrar.setOnMouseClicked((MouseEvent e) -> {
            UsuarioDao user = new UsuarioDao();
            try {
                if (user.logar(TextLogin.getText(), TextSenha.getText()) == true) {
                    TelaPrincipal novaTela = new TelaPrincipal();
                    try {
                        novaTela.start(new Stage());
                    } catch (IOException E) {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Login ou senha foram preenchidos incorretamente");
                }
            } catch (Exception ex) {}
        });
    }
}
