package com.siscond.controller;

import java.net.URL;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.siscond.dao.ApartamentosDao;
import com.siscond.dao.LancamentosDao;
import com.siscond.dao.MovimentacoesDao;
import com.siscond.modelo.Apartamentos;
import com.siscond.modelo.Lancamentos;
import com.siscond.modelo.Movimentacoes;
import com.siscond.util.Util;
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
	private ComboBox<String> cmbLan;

	@FXML
	private ComboBox<String> cmbNumApto;

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
	private ComboBox<String> cmbPesq;

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
	
	//Metodo de preenchimento da ComboBox Numero Apartamento
	public void preencheCmbBoxNumApto() {
		this.cmbNumApto.getItems().remove(0, this.cmbNumApto.getItems().size());
		ApartamentosDao aD = new ApartamentosDao();
		ArrayList<Apartamentos> alP = new ArrayList<Apartamentos>();
		try{
			alP = aD.listaNumApto();
		}catch (Exception e) {
			
			Util.mensagemErro("Erro ao alimentar combobox: "+e.getMessage());
		}
		for(int i=0; i<alP.size(); i++){
			this.cmbNumApto.getItems().add(String.valueOf(alP.get(i).getNum_apto()));
		}
	}
	
	//Metodo de preenchimento da ComboBox Lancamento
	public void preencheCmbBoxLcto() {
		this.cmbLan.getItems().remove(0, this.cmbLan.getItems().size());
		LancamentosDao lD = new LancamentosDao();
		ArrayList<Lancamentos> alP = new ArrayList<Lancamentos>();
		try{
			alP = lD.listaLcto();
		}catch (Exception e) {

			Util.mensagemErro("Erro ao alimentar combobox: "+e.getMessage());
		}
		for(int i=0; i<alP.size(); i++){
			this.cmbLan.getItems().add(alP.get(i).getDescricao_lancamento());
		}
	}
	
	//Metodo de preenchimento da aba Cadastro com os dados buscados
	public void preencheTabCadastro(Movimentacoes m)throws SQLException{
		Lancamentos l = new Lancamentos();
		LancamentosDao lD = new LancamentosDao();
		l = lD.consultaPorIdLcto(m.getCod_lancamento());
		String descricao = "*";
		if(l != null){
			descricao = l.getDescricao_lancamento();
		}
		for(int i=0; i<this.cmbLan.getItems().size(); i++){
			String s = this.cmbLan.getItems().get(i).toString();
			if(s.equals(descricao)){
				this.cmbLan.setPromptText(descricao);
			}
		}
		Apartamentos a = new Apartamentos();
		ApartamentosDao aD = new ApartamentosDao();
		a = aD.consultaPorNumApto(m.getNum_apto());
		int numero = 0;
		if(a != null){
			numero = a.getNum_apto();
		}
		for(int i=0; i<this.cmbNumApto.getItems().size(); i++){
			int n = Integer.parseInt(this.cmbNumApto.getItems().get(i).toString());
			if(n == numero){
				this.cmbNumApto.setPromptText(String.valueOf(numero));
			}
		}
		this.txtCodMov.setText(Integer.toString(m.getCod_movimentacao()));
		this.dtPic.setPromptText(Util.rsDataBD(m.getData_movimentacao()));
		this.txtValor.setText(Float.toString(m.getValor()));
		this.txtNumDoc.setText(Integer.toString(m.getNum_documento()));
		SingleSelectionModel<Tab> selectionModel = this.tabPane.getSelectionModel();
		selectionModel.select(this.tabCad);
	}
	
	public Lancamentos objLcto(String s){
		Lancamentos l = new Lancamentos();
		LancamentosDao lD = new LancamentosDao();
		try{
			l = lD.consultaPorLcto(s);
		}catch (Exception e) {
			Util.mensagemErro("Erro: "+e.getMessage());
		}
		return l;
	}

}
