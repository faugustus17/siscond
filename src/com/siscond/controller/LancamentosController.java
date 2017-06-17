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
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.ResourceBundle;

import com.siscond.dao.LancamentosDao;

import com.siscond.modelo.Lancamentos;
import com.siscond.util.Util;

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
	private ComboBox<String> cmbTipo;

	@FXML
	private ComboBox<String> cmbPesq;

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
		String msg = "";
		LancamentosDao lD = new LancamentosDao();
		if(Util.stringVaziaOuNula(this.txtDesc.getText())){
			msg += "\nInforme a DESCRICÃO";
		}
		if(this.cmbTipo.getPromptText().equals("Selecione")){
			msg += "\nInforme o TIPO DO LANÇAMENTO";
		}
		if(msg.equals("")){
			Lancamentos l = new Lancamentos();
			l.setCod_lancamento(Integer.parseInt(this.txtCodLan.getText()));
			l.setDescricao_lancamento(this.txtDesc.getText());
			l.setTipo_lancamento(this.cmbTipo.getSelectionModel().getSelectedItem().toString());
			boolean retorno = lD.alteraLcto(l);
			if(retorno){
				Util.mensagemInformacao("ALteração realizada com sucesso!");
				this.limpaTela();
			}else{
				Util.mensagemErro("Erro, alteração não pôde ser feita!");
			}
		}else{
			Util.mensagemErro(msg);
		}
	}

	@FXML
	void onActionBtCadastrar(ActionEvent event) {
		String msg = "";
		LancamentosDao lD = new LancamentosDao();
		if(Util.stringVaziaOuNula(this.txtDesc.getText())){
			msg += "\nInforme a DESCRICÃO";
		}
		if(this.cmbTipo.getPromptText().equals("Selecione")){
			msg += "\nInforme o TIPO DO LANÇAMENTO";
		}
		if(msg.equals("")){
			Lancamentos l = new Lancamentos();
			l.setDescricao_lancamento(this.txtDesc.getText());
			l.setTipo_lancamento(this.cmbTipo.getSelectionModel().getSelectedItem().toString());
			int retorno = lD.incluiLcto(l);
			if(retorno == 1){
				Util.mensagemInformacao("Inclusão realizada com sucesso!");
				this.limpaTela();
			}else if(retorno == 0){
				Util.mensagemErro("Erro, inclusão não pôde ser feita!");
			}else if(retorno == 2){
				Util.mensagemInformacao("Lançamento já cadastrado!");
			}
		}else{
			Util.mensagemErro(msg);
		}
	}

	@FXML
	void onActionBtExcluir(ActionEvent event) {
		if(Util.stringVaziaOuNula(this.txtCodLan.getText())){
			Util.mensagemErro("Informe o código do lançamento a ser excluído!");
		}else{
			Lancamentos l = new Lancamentos();
			LancamentosDao lD = new LancamentosDao();
			if(!Util.stringVaziaOuNula(this.txtCodLan.getText())){
				l.setCod_lancamento(Integer.parseInt(this.txtCodLan.getText()));
			}
			boolean retorno = lD.excluiLcto(l);
			if(retorno){
				Util.mensagemInformacao("Exclusão efetuada com sucesso!");
			}else{
				Util.mensagemErro("Erro, exclusão não pôde ser realizada!");
			}
		}
	}

	@FXML
	void onActionCmbTipo(ActionEvent event) {
		this.preencheCmbTipo();
	}

	@FXML
	void onActionCmbPesq(ActionEvent event) {
		this.preencheCmbPesq();
		String s = cmbPesq.getSelectionModel().getSelectedItem().toString();
		if(s == null||s.isEmpty()){
			s = "Selecione";
		}else{
			if(s.equals("Descrição")){
				this.txtPesq.setPromptText("Informe a Descrição");
				this.btPesq.setDisable(false);
			}else if(s.equals("Tipo")){
				this.txtPesq.setPromptText("Informe o Tipo (A Pagar ou A Receber)");
				this.btPesq.setDisable(false);
			}else{
				this.txtPesq.setPromptText("Selecione o tipo de pesquisa");
				this.btPesq.setDisable(true);
			}
		}
	}

	@FXML
	void onActionLimpar(ActionEvent event) {
		this.limpaTela();
	}

	@FXML
	void onActionPesq(ActionEvent event) {
		String consulta = this.txtPesq.getText();
		if (consulta == null){
			consulta = "";
		}else{
			ArrayList<Lancamentos> al = new ArrayList<Lancamentos>();
			ObservableList<Lancamentos> ob = FXCollections.observableArrayList(al);
			this.tabView.setItems(ob);
			LancamentosDao lD = new LancamentosDao();
			String s = cmbPesq.getSelectionModel().getSelectedItem().toString();
			if(s.equals("Descrição")){
				al = lD.consultaDescLcto(consulta);
			}else if(s.equals("Tipo")){
				al = lD.consultaTipoLcto(consulta);
			}
			ob = FXCollections.observableArrayList(al);
			this.tabView.setItems(ob);
			this.colCod.setCellValueFactory(new PropertyValueFactory<Lancamentos, Integer>("cod_lancamento"));
			this.colDesc.setCellValueFactory(new PropertyValueFactory<Lancamentos, String>("descricao_lancamento"));
			this.colTipo.setCellValueFactory(new PropertyValueFactory<Lancamentos, String>("tipo_lancamento"));
			if(al.size() == 0){
				Util.mensagemInformacao("Item pesquisado não foi encontrado!");
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Populando as ComboBox Tipo
		this.tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				if(newValue == tabCad){
					preencheCmbTipo();
				}
			}
		});

		//Populando as ComboBox Pesq
		this.tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				if(newValue == tabPesq){
					preencheCmbPesq();
				}
			}
		});

		//Preencher a aba Cadastro ao clicar no item da TableView
		this.tabView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				if(tabView.getSelectionModel().selectedItemProperty() != null){
					Lancamentos l = new Lancamentos();
					l.setCod_lancamento(tabView.getSelectionModel().getSelectedItem().getCod_lancamento());
					l.setDescricao_lancamento(tabView.getSelectionModel().getSelectedItem().getDescricao_lancamento());
					l.setTipo_lancamento(tabView.getSelectionModel().getSelectedItem().getTipo_lancamento());
					try{
						preencheAbaCad(l);
					}catch (Exception e){
						Util.mensagemErro("Erro: "+e.getMessage());
					}
				}	
			}
		});

	}

	//Metodo de preenchimento da aba Cadastro com os dados buscados
	private void preencheAbaCad(Lancamentos l) {
		this.txtCodLan.setText(Integer.toString(l.getCod_lancamento()));
		this.txtDesc.setText(l.getDescricao_lancamento());
		this.cmbTipo.setPromptText(l.getTipo_lancamento());
		SingleSelectionModel<Tab> sl = tabPane.getSelectionModel();
		sl.select(tabCad);
	}

	//Metodo para limpar dados da tela
	public void limpaTela(){
		this.txtCodLan.setText("");
		this.txtDesc.setText("");
		this.cmbTipo.getItems().clear();
		ArrayList<Lancamentos> al = new ArrayList<Lancamentos>();
		ObservableList<Lancamentos> ob = FXCollections.observableArrayList(al);
		this.tabView.setItems(ob);
	}

	//Metodo para preencher a ComboBox Tipo
	public void preencheCmbTipo(){
		ObservableList<String> op = FXCollections.observableArrayList("A Pagar", "A Receber");
		cmbTipo.setItems(op);
	}

	//Metodo para preencher a ComboBox Pesquisa
	public void preencheCmbPesq(){
		ObservableList<String> op = FXCollections.observableArrayList("Descrição", "Tipo");
		cmbPesq.setItems(op);
	}

}
