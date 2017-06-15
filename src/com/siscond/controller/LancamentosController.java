package com.siscond.controller;

import java.net.URL;

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

import com.siscond.modelo.Lancamentos;

import javafx.fxml.Initializable;

public class LancamentosController implements Initializable{
	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabCad;

	@FXML
	private Pane paneCad;

	@FXML
	private TextField txtCodLan;

	@FXML
	private TextField txtDesc;

	@FXML
	private ComboBox<?> cmbTipo;

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
	private Button btPesq;

	@FXML
	private TextField txtPesq;

	@FXML
	private TableView<Lancamentos> tabView;

	@FXML
	private TableColumn<Lancamentos, Integer> colCod;

	@FXML
	private TableColumn<Lancamentos, String> colDesc;

	@FXML
	private TableColumn<Lancamentos, String> colTipo;
	

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
	void onActionCmbTipo(ActionEvent event) {

	}

	@FXML
	void onActionLimpar(ActionEvent event) {

	}

	@FXML
	void onActionPesq(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
