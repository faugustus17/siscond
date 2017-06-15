package com.siscond.controller;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.Date;
import java.util.ResourceBundle;

import com.siscond.modelo.Movimentacoes;

import javafx.fxml.Initializable;

public class MovimentacoesController implements Initializable{
	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabCad;

	@FXML
	private Pane paneCad;

	@FXML
	private TextField txtCodMov;

	@FXML
	private TextField txtValor;

	@FXML
	private TextField txtNumDoc;

	@FXML
	private DatePicker dtPic;

	@FXML
	private ComboBox<?> cmbLan;

	@FXML
	private ComboBox<?> cmbNumApto;

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
	private ComboBox<?> cmbPesq;

	@FXML
	private Button btPesq;

	@FXML
	private TextField txtPesq;

	@FXML
	private TableView<Movimentacoes> tabView;

	@FXML
	private TableColumn<Movimentacoes, Date> colDtMov;

	@FXML
	private TableColumn<Movimentacoes, Integer> colNumApto;

	@FXML
	private TableColumn<Movimentacoes, String> colDescLan;

	@FXML
	private TableColumn<Movimentacoes, Float> colVal;

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
	void onActionCmbLan(ActionEvent event) {

	}

	@FXML
	void onActionCmbPesq(ActionEvent event) {

	}

	@FXML
	void onActionLimpar(ActionEvent event) {

	}

	@FXML
	void onActionNumApto(ActionEvent event) {

	}

	@FXML
	void onActionPesq(ActionEvent event) {

	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
