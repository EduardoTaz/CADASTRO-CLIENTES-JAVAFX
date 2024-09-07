package br.com.etec.aula20240906;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private Button btnCadastrar;

    @FXML
    private CheckBox chkCasado;

    @FXML
    private ToggleGroup grpSexo;

    @FXML
    private RadioButton rbFeminino;

    @FXML
    private RadioButton rbMasculino;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtAreaDados;


    private Cliente cliente;
    private List<Cliente> listaClientes = new ArrayList<>(); // uma lista de Cliente chamada listaClientes

    @FXML
    protected void onClickCadastrar() { // cria metodo onClickCadastrar
        if(txtNome.getText().equals("")) {
            campoVazio("Nome em branco");
            txtNome.requestFocus();
        } else if (txtEmail.getText().equals("")) {
            campoVazio("Email em branco");
            txtEmail.requestFocus();
        } else if(txtTelefone.getText().equals("")) {
            campoVazio("Telefone em branco");
            txtTelefone.requestFocus();
        }

        String sexo = rbMasculino.isSelected()? "Masculino" : "Feminino"; // se rbMasculino estiver selecionado, masculino, se nao feminino
        int id = listaClientes.size() + 1; // tamanho do array listaClientes para id
        cliente = new Cliente(id, txtNome.getText(), txtEmail.getText(), txtTelefone.getText(), sexo, chkCasado.isSelected()); // pega os dados
        listaClientes.add(cliente);
        txtAreaDados.setText(listaClientes.toString());
        limparCampos();

        /*
        alternativa do sexo
        if(rbMasculino.isSelected()) {
            sexo = "Masculino";
        } else {
            sexo = "Feminino";
        }
        */
    }

    @FXML
    private void limparCampos() {
        txtNome.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        rbMasculino.setSelected(false);
        rbFeminino.setSelected(false);
        chkCasado.setSelected(false);
        txtNome.requestFocus();
    }

    @FXML
    private void campoVazio(String msg) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("ERRO");
        alerta.setHeaderText("Campo em branco");
        alerta.setContentText(msg);
        alerta.show();
        return;
    }

}