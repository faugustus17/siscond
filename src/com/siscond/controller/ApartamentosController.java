package com.siscond.controller;

import java.net.URL;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.util.ResourceBundle;
import com.siscond.modelo.Apartamentos;
import com.siscond.util.MenuAuxiliar;
import com.siscond.util.MenuAuxiliar.TipoPesquisaAp;
import javafx.fxml.Initializable;

public class ApartamentosController implements Initializable{
	
	ObservableList<String> itens;

	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabCad;
	
	@FXML
    private TableView<Apartamentos> tabView;

	@FXML
	private Pane paneCad;

	@FXML
	private TextField txtNumApto;

	@FXML
	private TextField txtTelefone;

	@FXML
	private TextField txtNomeProp;

	@FXML
	private Button btCadastrar;

	@FXML
	private Button btAlterar;

	@FXML
	private Button btExcluir;

	@FXML
	private Button btLimpar;

	@FXML
	private Tab tabPesq;

	@FXML
	private ComboBox<MenuAuxiliar.TipoPesquisaAp> cmbPesq;

	@FXML
	private Button btPesq;

	@FXML
	private TextField txtPesq;

	@FXML
	private TableColumn<Apartamentos, Integer> colApto;

	@FXML
	private TableColumn<Apartamentos, String> colProp;

	@FXML
	void onActionBtAlterar(ActionEvent event) {

	}

	@FXML
	void onActionBtCadastrar(ActionEvent event) {

	}

	@FXML
	void onActionBtExcluir(ActionEvent event) {

	}

	@FXML
	void onActionCmbPesq(ActionEvent event) {
		String s = cmbPesq.getSelectionModel().getSelectedItem().toString();
		if(s.equals("Número do Apartamento")){
			this.txtPesq.setPromptText("Informe o número do apartamento");
			this.btPesq.setDisable(false);
		}else if(s.equals("Nome do Proprietário")){
			this.txtPesq.setPromptText("Informe o nome do proprietário");
			this.btPesq.setDisable(false);
		}
	}

	@FXML
	void onActionLimpar(ActionEvent event) {
		this.limpaTela();
	}

	@FXML
	void onActionPesq(ActionEvent event) {
		this.btPesq.setDisable(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Populando a comboBox
		this.tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				String s = observable.getValue().getText();
				if (s.equals("Pesquisa")){
					cmbPesq.getItems().clear();
					cmbPesq.setItems(FXCollections.observableArrayList(TipoPesquisaAp.values()));
					tabView.getItems().clear();
					btPesq.setDisable(true);
					//cmbPesq.setSelectionModel(null);
					//cmbPesq.setId("Selecione");
				}
			}
		});

	}
	
	//Metodo para limpar dados da tela
	public void limpaTela(){
		this.txtNumApto.setText("");
		this.txtNomeProp.setText("");
		this.txtTelefone.setText("");
	}

}
