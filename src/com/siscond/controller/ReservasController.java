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

import com.siscond.modelo.Reservas;

import javafx.fxml.Initializable;

public class ReservasController implements Initializable{
	@FXML
	private TabPane tabPane;

	@FXML
	private Tab tabCad;

	@FXML
	private Pane paneCad;

	@FXML
	private TextField txtCodRes;

	@FXML
	private TextField txtHrIn;

	@FXML
	private TextField txtHrFi;

	@FXML
	private TextField txtNumApto;

	@FXML
	private DatePicker dtPic;

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
	private TableView<Reservas> tabView;

	@FXML
	private TableColumn<Reservas, Integer> colApto;

	@FXML
	private TableColumn<Reservas, Date> colRes;

	@FXML
	private TableColumn<Reservas, String> colHrIn;

	@FXML
	private TableColumn<Reservas, String> colHrFi;

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
