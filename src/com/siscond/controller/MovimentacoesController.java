package com.siscond.controller;

import java.net.URL;
import java.sql.SQLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
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
		String msg = "";
		Lancamentos l = new Lancamentos();
		MovimentacoesDao mD = new MovimentacoesDao();
		if(Util.stringVaziaOuNula(this.dtPic.getPromptText())){
			msg += "\nInforme uma Data de Reserva";
		}
		if(Util.stringVaziaOuNula(this.txtValor.getText())){
			msg += "\nInforme o Valor";
		}
		if(Util.stringVaziaOuNula(this.txtNumDoc.getText())){
			msg += "\nInforme o Número do Documento";
		}
		if(this.cmbNumApto.getPromptText().equals("Apartamento")){
			msg += "\nInforme o Número do Apartamento";
		}
		if(this.cmbLan.getPromptText().equals("Selecione")){
			msg += "\nInforme um Lançamento";
		}
		if(msg.equals("")){
			Movimentacoes m = new Movimentacoes();
			m.setCod_lancamento(Integer.parseInt(this.txtCodMov.getText()));
			m.setData_movimentacao(Util.dataF(this.dtPic.getPromptText()));
			m.setValor(Float.parseFloat(this.txtValor.getText()));
			m.setNum_documento(Integer.parseInt(this.txtNumDoc.getText()));
			m.setNum_apto(Integer.parseInt(this.cmbNumApto.getPromptText()));
			String s = this.cmbLan.getSelectionModel().getSelectedItem();
			if (s == null){
				s = this.cmbLan.getPromptText();
			}
			l = this.objLcto(s);
			m.setCod_lancamento(l.getCod_lancamento());
			boolean retorno = mD.alteraMov(m);
			if(retorno){
				Util.mensagemInformacao("Alteração realizada com sucesso!");
				this.limpaTela();
			}else if(retorno){
				Util.mensagemErro("Erro, alteração não pôde ser feita!");
			}
		}else{
			Util.mensagemErro(msg);
		}
	}

	@FXML
	void onActionBtCadastrar(ActionEvent event) {
		String msg = "";
		Lancamentos l = new Lancamentos();
		MovimentacoesDao mD = new MovimentacoesDao();
		if(Util.stringVaziaOuNula(this.dtPic.getPromptText())){
			msg += "\nInforme uma Data de Reserva";
		}
		if(Util.stringVaziaOuNula(this.txtValor.getText())){
			msg += "\nInforme o Valor";
		}
		if(Util.stringVaziaOuNula(this.txtNumDoc.getText())){
			msg += "\nInforme o Número do Documento";
		}
		if(this.cmbNumApto.getPromptText().equals("Apartamento")){
			msg += "\nInforme o Número do Apartamento";
		}
		if(this.cmbLan.getPromptText().equals("Selecione")){
			msg += "\nInforme um Lançamento";
		}
		if(msg.equals("")){
			Movimentacoes m = new Movimentacoes();
			m.setData_movimentacao(Util.dataF(this.dtPic.getPromptText()));
			m.setValor(Float.parseFloat(this.txtValor.getText()));
			m.setNum_documento(Integer.parseInt(this.txtNumDoc.getText()));
			m.setNum_apto(Integer.parseInt(this.cmbNumApto.getPromptText()));
			String s = this.cmbLan.getSelectionModel().getSelectedItem();
			if (s == null){
				s = this.cmbLan.getPromptText();
			}
			l = this.objLcto(s);
			m.setCod_lancamento(l.getCod_lancamento());
			int retorno = mD.incluiMov(m);
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
		if(Util.stringVaziaOuNula(this.txtCodMov.getText())){
			Util.mensagemErro("Informe o código da Movimentação a ser excluída!");
		}else{
			Movimentacoes m = new Movimentacoes();
			MovimentacoesDao mD = new MovimentacoesDao();
			if(!Util.stringVaziaOuNula(this.txtCodMov.getText())){
				m.setCod_movimentacao(Integer.parseInt(this.txtCodMov.getText()));
			}
			boolean retorno = mD.excluiMov(m);
			if(retorno){
				Util.mensagemInformacao("Exclusão efetuada com sucesso!");
			}else{
				Util.mensagemErro("Erro, exclusão não pôde ser realizada!");
			}
		}
	}

	@FXML
	void onActionCmbPesq(ActionEvent event) {
		this.preencheCmbPesq();
		String s = cmbPesq.getSelectionModel().getSelectedItem().toString();
		if(s == null||s.isEmpty()){
			s = "Selecione";
		}else{
			if(s.equals("Por Data")){
				this.txtPesq.setPromptText("Informe uma Data de Movimentação no formato (dd/mm/aaaa)");
				this.btPesq.setDisable(false);
			}else if(s.equals("Código Lançamento")){
				this.txtPesq.setPromptText("Informe o Código do Lançamento");
				this.btPesq.setDisable(false);
			}else if(s.equals("Número do Apartamento")){
				this.txtPesq.setPromptText("Informe o Número do Apartamento");
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
			ArrayList<Movimentacoes> al = new ArrayList<Movimentacoes>();
			ObservableList<Movimentacoes> ob = FXCollections.observableArrayList(al);
			this.tabView.setItems(ob);
			MovimentacoesDao mD = new MovimentacoesDao();
			String s = cmbPesq.getSelectionModel().getSelectedItem().toString();
			if(s.equals("Por Data")){
				al = mD.consultaData(Util.dataF(consulta));
			}else if(s.equals("Código Lançamento")){
				al = mD.consultaCodLcto(Integer.parseInt(consulta));
			}else if(s.equals("Número do Apartamento")){
				al = mD.consultaMovNumApto(Integer.parseInt(consulta));
			}
			ob = FXCollections.observableArrayList(al);
			this.tabView.setItems(ob);
			this.colDtMov.setCellValueFactory(new PropertyValueFactory<Movimentacoes, Date>("data_movimento"));
			this.colNumApto.setCellValueFactory(new PropertyValueFactory<Movimentacoes, Integer>("num_apto"));
			this.colDescLan.setCellValueFactory(new PropertyValueFactory<Movimentacoes, String>("cod_lancamento"));
			this.colVal.setCellValueFactory(new PropertyValueFactory<Movimentacoes, Float>("valor"));
			if(al.size() == 0){
				Util.mensagemInformacao("Item pesquisado não foi encontrado!");
			}
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.preencheCmbBoxLcto();
		this.preencheCmbBoxNumApto();
		this.preencheCmbPesq();
		
		//Preenche as ComboBox da Aba Cadastro
		this.tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				if(newValue == tabCad){
					preencheCmbBoxLcto();
					preencheCmbBoxNumApto();
				}
			}
		});
		
		//Preenche a ComboBox da Aba Pesquisa
		this.tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
				if(newValue == tabPesq){
					preencheCmbPesq();
				}
			}
		});
		
		this.cmbLan.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Lancamentos l = new Lancamentos();
				LancamentosDao lD = new LancamentosDao();
				try{
					l = lD.consultaPorLcto(newValue);
				}catch (Exception e){
					Util.mensagemErro("Erro: "+e.getMessage());
				}
				if (l != null){
					
				}
			}
		});
		
		//Alimenta a Aba Cadastro
		this.tabView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
				if(tabView.getSelectionModel().selectedItemProperty() != null){
					Movimentacoes m = new Movimentacoes();
					m.setCod_lancamento(tabView.getSelectionModel().getSelectedItem().getCod_movimentacao());
					m.setData_movimentacao(tabView.getSelectionModel().getSelectedItem().getData_movimentacao());
					m.setValor(tabView.getSelectionModel().getSelectedItem().getValor());
					m.setNum_documento(tabView.getSelectionModel().getSelectedItem().getNum_documento());
					m.setNum_apto(tabView.getSelectionModel().getSelectedItem().getNum_apto());
					m.setCod_lancamento(tabView.getSelectionModel().getSelectedItem().getCod_lancamento());
					try{
						preencheTabCadastro(m);
					}catch (Exception e){
						Util.mensagemErro("Erro: "+e.getMessage());
					}
				}
				
			}
		});

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
	
	//Metodo para preencher a ComboBox Pesquisa
	public void preencheCmbPesq(){
		ObservableList<String> op = FXCollections.observableArrayList("Por Data", "Código Lançamento", "Número do Apartamento");
		cmbPesq.setItems(op);
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
	
	//Metodo para limpar dados da tela
	public void limpaTela(){
		this.txtCodMov.setText("");
		this.dtPic.setPromptText("");
		this.txtValor.setText("");
		this.txtNumDoc.setText("");
		this.txtPesq.setText("");
		this.cmbLan.getItems().clear();
		this.cmbNumApto.getItems().clear();
		this.cmbPesq.getItems().clear();
		ArrayList<Movimentacoes> al = new ArrayList<Movimentacoes>();
		ObservableList<Movimentacoes> ob = FXCollections.observableArrayList(al);
		this.tabView.setItems(ob);
	}


}
