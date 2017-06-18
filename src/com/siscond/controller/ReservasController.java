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
import com.siscond.dao.ReservasDao;
import com.siscond.modelo.Reservas;
import com.siscond.util.Util;
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
	private ComboBox<String> cmbPesq;

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
		String msg = "";
		ReservasDao rD = new ReservasDao();
		if(Util.stringVaziaOuNula(this.dtPic.getPromptText())){
			msg += "\nInforme uma Data de Reserva";
		}
		if(Util.stringVaziaOuNula(this.txtHrIn.getText())){
			msg += "\nInforme a Hora Inicial da Reserva";
		}
		if(Util.stringVaziaOuNula(this.txtHrFi.getText())){
			msg += "\nInforme a Hora Final da Reserva";
		}
		if(Util.stringVaziaOuNula(this.txtNumApto.getText())){
			msg += "\nInforme o Número do Apartamento";
		}
		if(msg.equals("")){
			Reservas r = new Reservas();
			r.setCod_reserva(Integer.parseInt(this.txtCodRes.getText()));
			r.setData_reserva(Util.dataF(this.dtPic.getPromptText()));
			r.setHorario_inicial(this.txtHrIn.getText());
			r.setHorario_inicial(this.txtHrFi.getText());
			r.setNum_apto(Integer.parseInt(this.txtNumApto.getText()));
			boolean retorno = rD.alteraReserva(r);
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
		ReservasDao rD = new ReservasDao();
		if(Util.stringVaziaOuNula(this.dtPic.getPromptText())){
			msg += "\nInforme uma Data de Reserva";
		}
		if(Util.stringVaziaOuNula(this.txtHrIn.getText())){
			msg += "\nInforme a Hora Inicial da Reserva";
		}
		if(Util.stringVaziaOuNula(this.txtHrFi.getText())){
			msg += "\nInforme a Hora Final da Reserva";
		}
		if(Util.stringVaziaOuNula(this.txtNumApto.getText())){
			msg += "\nInforme o Número do Apartamento";
		}
		if(rD.consultaDtRes(Util.dataF(this.dtPic.getPromptText())) == 1 
				&& (rD.consultaHoraInRes(this.txtHrIn.getText()) == 1
				|| rD.consultaHoraFimRes(this.txtHrFi.getText()) == 1)){
			msg += "\nReserva já feita para essa Dia/Horário";
		}
		if(msg.equals("")){
			Reservas r = new Reservas();
			r.setData_reserva(Util.dataF(this.dtPic.getPromptText()));
			r.setHorario_inicial(this.txtHrIn.getText());
			r.setHorario_inicial(this.txtHrFi.getText());
			r.setNum_apto(Integer.parseInt(this.txtNumApto.getText()));
			int retorno = rD.incluiReserva(r);
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
		if(Util.stringVaziaOuNula(this.txtCodRes.getText())){
			Util.mensagemErro("Informe o código do lançamento a ser excluído!");
		}else{
			Reservas r = new Reservas();
			ReservasDao rD = new ReservasDao();
			if(!Util.stringVaziaOuNula(this.txtCodRes.getText())){
				r.setCod_reserva(Integer.parseInt(this.txtCodRes.getText()));
			}
			boolean retorno = rD.excluiRervas(r);
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
			if(s.equals("Data da Reserva")){
				this.txtPesq.setPromptText("Informe uma Data de Reserva no formato (dd/mm/aaaa)");
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
			ArrayList<Reservas> al = new ArrayList<Reservas>();
			ObservableList<Reservas> ob = FXCollections.observableArrayList(al);
			this.tabView.setItems(ob);
			ReservasDao rD = new ReservasDao();
			String s = cmbPesq.getSelectionModel().getSelectedItem().toString();
			if(s.equals("Data da Reserva")){
				al = rD.consultaData(Util.dataF(consulta));
			}else if(s.equals("Número do Apartamento")){
				al = rD.consultaResNumApto(Integer.parseInt(consulta));
			}
			ob = FXCollections.observableArrayList(al);
			this.tabView.setItems(ob);
			this.colApto.setCellValueFactory(new PropertyValueFactory<Reservas, Integer>("cod_lancamento"));
			this.colRes.setCellValueFactory(new PropertyValueFactory<Reservas, Date>("data_reserva"));
			this.colHrIn.setCellValueFactory(new PropertyValueFactory<Reservas, String>("horario_inicial"));
			this.colHrFi.setCellValueFactory(new PropertyValueFactory<Reservas, String>("horario_final"));
			if(al.size() == 0){
				Util.mensagemInformacao("Item pesquisado não foi encontrado!");
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
					Reservas r = new Reservas();
					r.setCod_reserva(tabView.getSelectionModel().getSelectedItem().getCod_reserva());
					r.setData_reserva(tabView.getSelectionModel().getSelectedItem().getData_reserva());
					r.setHorario_inicial(tabView.getSelectionModel().getSelectedItem().getHorario_inicial());
					r.setHorario_final(tabView.getSelectionModel().getSelectedItem().getHorario_final());
					r.setNum_apto(tabView.getSelectionModel().getSelectedItem().getNum_apto());
					try{
						preencheAbaCad(r);
					}catch (Exception e){
						Util.mensagemErro("Erro: "+e.getMessage());
					}
				}	
			}
		});
		
		//Cria mascara de entrada para o horario inicial
		this.txtHrIn.lengthProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				String mascara = "##:##";
				String alphaAndDigits = txtHrIn.getText().replaceAll("[\\:]", "");
				StringBuilder resultado = new StringBuilder();
				int i = 0;
				int quant = 0;
				if(arg2.intValue() > arg1.intValue()){
					if(txtHrIn.getText().length() <= mascara.length()){
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
						txtHrIn.setText(resultado.toString());
					}
					if(txtHrIn.getText().length() > mascara.length()){
						txtHrIn.setText(txtHrIn.getText(0, mascara.length()));
					}
				}
			}
		});
		
		//Cria mascara de entrada para o horario final
		this.txtHrFi.lengthProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				String mascara = "##:##";
				String alphaAndDigits = txtHrFi.getText().replaceAll("[\\:]", "");
				StringBuilder resultado = new StringBuilder();
				int i = 0;
				int quant = 0;
				if(arg2.intValue() > arg1.intValue()){
					if(txtHrFi.getText().length() <= mascara.length()){
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
						txtHrFi.setText(resultado.toString());
					}
					if(txtHrFi.getText().length() > mascara.length()){
						txtHrFi.setText(txtHrFi.getText(0, mascara.length()));
					}
				}
			}
		});

	}

	//Metodo de preenchimento da aba Cadastro com os dados buscados
	private void preencheAbaCad(Reservas r) {
		this.txtCodRes.setText(Integer.toString(r.getCod_reserva()));
		this.dtPic.setPromptText(Util.rsDataBD(r.getData_reserva()));
		this.txtHrIn.setText(r.getHorario_inicial());
		this.txtHrFi.setText(r.getHorario_final());
		this.txtNumApto.setText(Integer.toString(r.getNum_apto()));
		SingleSelectionModel<Tab> sl = tabPane.getSelectionModel();
		sl.select(tabCad);
	}
	
	//Metodo para preencher a ComboBox Pesquisa
	public void preencheCmbPesq(){
		ObservableList<String> op = FXCollections.observableArrayList("Data da Reserva", "Número do Apartamento");
		cmbPesq.setItems(op);
	}

	//Metodo para limpar dados da tela
	public void limpaTela(){
		this.txtCodRes.setText("");
		this.txtHrIn.setText("");
		this.txtHrFi.setText("");
		this.cmbPesq.getItems().clear();
		ArrayList<Reservas> al = new ArrayList<Reservas>();
		ObservableList<Reservas> ob = FXCollections.observableArrayList(al);
		this.tabView.setItems(ob);
	}

}
