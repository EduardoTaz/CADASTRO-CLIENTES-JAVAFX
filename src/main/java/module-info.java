module br.com.etec.aula20240906 {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.etec.aula20240906 to javafx.fxml;
    exports br.com.etec.aula20240906;
}