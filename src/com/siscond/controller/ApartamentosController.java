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
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.converter.IntegerStringConverter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.siscond.dao.ApartamentosDao;
import com.siscond.modelo.Apartamentos;
import com.siscond.util.Util;
import javafx.fxml.Initializable;

public class ApartamentosController implements Initializable{

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
	private ComboBox<String> cmbPesq;

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
		String msg = "";
		ApartamentosDao aD = new ApartamentosDao();
		if(Util.stringVaziaOuNula(this.txtNumApto.getText())){
			msg = "Informe o NUMERO DO APARTAMENTO";
		}
		if(Util.stringVaziaOuNula(this.txtNomeProp.getText())){
			msg += "\nInforme o NOME DO PROPRIETARIO";
		}
		if(Util.stringVaziaOuNula(this.txtTelefone.getText())){
			msg += "\nInforme o TELEFONE";
		}
		if(msg.equals("")){
			Apartamentos a = new Apartamentos();
			a.setNum_apto(Integer.parseInt(this.txtNumApto.getText()));
			a.setNome_titular(this.txtNomeProp.getText());
			a.setTelefone_titular(this.txtTelefone.getText());
			boolean retorno = aD.alteraApto(a);
			if(retorno){
				Util.mensagemInformacao("Alteração realizada com sucesso!");
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
		ApartamentosDao aD = new ApartamentosDao();
		if(Util.stringVaziaOuNula(this.txtNumApto.getText())){
			msg = "Informe o NUMERO DO APARTAMENTO";
		}
		if(Util.stringVaziaOuNula(this.txtNomeProp.getText())){
			msg += "\nInforme o NOME DO PROPRIETARIO";
		}
		if(Util.stringVaziaOuNula(this.txtTelefone.getText())){
			msg += "\nInforme o TELEFONE";
		}
		if(aD.consultaApto(Integer.parseInt(this.txtNumApto.getText())) == 1){
			msg += "\nApartamento já Cadastrado";
		}
		if(msg.equals("")){
			Apartamentos a = new Apartamentos();
			a.setNum_apto(Integer.parseInt(this.txtNumApto.getText()));
			a.setNome_titular(this.txtNomeProp.getText());
			a.setTelefone_titular(this.txtTelefone.getText());
			int retorno = aD.incluiApto(a);
			if(retorno == 1){
				Util.mensagemInformacao("Inclusão realizada com sucesso!");
				this.limpaTela();
			}else if(retorno == 0){
				Util.mensagemErro("Erro, inclusão não pôde ser feita!");
			}else if(retorno == 2){
				Util.mensagemInformacao("Apartamento já cadastrado!");
			}
		}else{
			Util.mensagemErro(msg);
		}
	}

	@FXML
	void onActionBtExcluir(ActionEvent event) {
		if(Util.stringVaziaOuNula(this.txtNumApto.getText())){
			Util.mensagemErro("Informe o número do apartamento a ser excluído!");
		}else{
			Apartamentos a = new Apartamentos();
			ApartamentosDao aD = new ApartamentosDao();
			if(!Util.stringVaziaOuNula(this.txtNumApto.getText())){
				a.setNum_apto(Integer.parseInt(this.txtNumApto.getText()));
			}
			boolean retorno = aD.excluiApto(a);
			if(retorno){
				Util.mensagemInformacao("Exclusão efetuada com sucesso!");
			}else{
				Util.mensagemErro("Erro, exclusão não pôde ser realizada!");
			}
		}
	}

	@FXML
	void onActionCmbPesq(ActionEvent event) {
		String s = cmbPesq.getSelectionModel().getSelectedItem().toString();
		if(s == null||s.isEmpty()){
			s = "Selecione";
		}else{
			if(s.equals("Número do Apartamento")){
				this.txtPesq.setPromptText("Informe o número do apartamento");
				this.btPesq.setDisable(false);
			}else if(s.equals("Nome do Proprietário")){
				this.txtPesq.setPromptText("Informe o nome do proprietário");
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
			ArrayList<Apartamentos> al = new ArrayList<Apartamentos>();
			ObservableList<Apartamentos> ob = FXCollections.observableArrayList(al);
			this.tabView.setItems(ob);
			ApartamentosDao aD = new ApartamentosDao();
			String s = cmbPesq.getSelectionModel().getSelectedItem().toString();
			if(s.equals("Número do Apartamento")){
				al = aD.consultaNumApto(Integer.parseInt(consulta));
			}else if(s.equals("Nome do Proprietário")){
				al = aD.consultaNomeTitular(consulta);
			}
			ob = FXCollections.observableArrayList(al);
			this.tabView.setItems(ob);
			this.colApto.setCellValueFactory(new PropertyValueFactory<Apartamentos, Integer>("num_apto"));
			this.colProp.setCellValueFactory(new PropertyValueFactory<Apartamentos, String>("nome_titular"));
			if(al.size() == 0){
				Util.mensagemInformacao("Item pesquisado não foi encontrado!");
			}
		}
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
					ObservableList<String> op =
							FXCollections.observableArrayList(
									"Número do Apartamento",
									"Nome do Proprietário");
					cmbPesq.setItems(op);
					tabView.getItems().clear();
					txtPesq.setPromptText("Selecione o tipo de pesquisa");
					btPesq.setDisable(true);
				}
			}
		});
		
		//Preencher a aba Cadastro ao clicar no item da TableView
		this.tabView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				if(tabView.getSelectionModel().selectedItemProperty() != null){
					Apartamentos a = new Apartamentos();
					a.setNum_apto(tabView.getSelectionModel().getSelectedItem().getNum_apto());
					a.setNome_titular(tabView.getSelectionModel().getSelectedItem().getNome_titular());
					a.setTelefone_titular(tabView.getSelectionModel().getSelectedItem().getTelefone_titular());
					try{
						preencheAbaCad(a);
					}catch (Exception e){
						Util.mensagemErro("Erro: "+e.getMessage());
					}
				}	
			}
		});
		
		//Metodo para aceitar somente numeros
		TextFormatter<Integer> textFormatter = new TextFormatter<Integer>(new IntegerStringConverter());
		this.txtNumApto.textFormatterProperty().setValue(textFormatter);
		
		//Cria mascara de entrada para o telefone
		this.txtTelefone.lengthProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				String mascara = "(##)#####-####";
				String alphaAndDigits = txtTelefone.getText().replaceAll("[\\(\\)\\-]", "");
				StringBuilder resultado = new StringBuilder();
				int i = 0;
				int quant = 0;
				if(arg2.intValue() > arg1.intValue()){
					if(txtTelefone.getText().length() <= mascara.length()){
						while(i<mascara.length()){
							if(quant < alphaAndDigits.length()){
								if("#".equals(mascara.substring(i, i+1))){
									resultado.append(alphaAndDigits.substring(quant, quant+1));
									quant++;
								}else{
									resultado.append(mascara.substring(i, i+1));
								}
							}
							i++;
						}
						txtTelefone.setText(resultado.toString());
					}
					if(txtTelefone.getText().length() > mascara.length()){
						txtTelefone.setText(txtTelefone.getText(0, mascara.length()));
					}
				}
			}
		});
		
	}
	
	//Metodo de preenchimento da aba Cadastro com os dados buscados
	private void preencheAbaCad(Apartamentos a) {
		this.txtNumApto.setText(Integer.toString(a.getNum_apto()));
		this.txtNomeProp.setText(a.getNome_titular());
		this.txtTelefone.setText(a.getTelefone_titular());
		SingleSelectionModel<Tab> sl = tabPane.getSelectionModel();
		sl.select(tabCad);
	}
	
	//Metodo para limpar dados da tela
	public void limpaTela(){
		this.txtNumApto.setText("");
		this.txtNomeProp.setText("");
		this.txtTelefone.setText("");
		ArrayList<Apartamentos> al = new ArrayList<Apartamentos>();
		ObservableList<Apartamentos> ob = FXCollections.observableArrayList(al);
		this.tabView.setItems(ob);
	}

}
