package com.siscond.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.siscond.application.Main;
import com.siscond.util.Util;
import javafx.fxml.Initializable;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

public class MenuController implements Initializable{
	 @FXML
	    private Pane pane;

	    @FXML
	    private MenuItem mIApto;

	    @FXML
	    private MenuItem mILan;

	    @FXML
	    private MenuItem mIMov;

	    @FXML
	    private MenuItem mIRes;

	    @FXML
	    private MenuItem mIRelGer;

	    @FXML
	    private MenuItem mIRelRes;

	    @FXML
	    private MenuItem menuItemSair;

	    @FXML
	    private ImageView img01;

	    @FXML
	    private Button btnSair;
	    
	    //Acessa opcao Sair
	    @FXML
	    void onActionBtnSair(ActionEvent event) {
	    	Platform.exit();
	    }
	    
	    //Acessa Apartamentos
	    @FXML
	    void onActionMIApto(ActionEvent event) {
	    	try{
				AnchorPane rootx = new AnchorPane();
				rootx = FXMLLoader.load(getClass().getResource("/com/siscond/view/Apartamentos.fxml"));
				Scene scenex = new Scene(rootx);
				final Stage stagex = new Stage();
				stagex.setScene(scenex);
				stagex.setTitle("Cadastro de Apartamentos");
				stagex.initModality(Modality.APPLICATION_MODAL);
				Main main = new Main();
				stagex.initOwner(main.stage);
				stagex.show();
			}catch (Exception e) {
				Util.mensagemErro("Erro: "+e.getMessage());
			}
	    }
	    
	    //Acessa Lançamentos
	    @FXML
	    void onActionMILan(ActionEvent event) {
	    	try{
				AnchorPane rootx = new AnchorPane();
				rootx = FXMLLoader.load(getClass().getResource("/com/siscond/view/Lancamentos.fxml"));
				Scene scenex = new Scene(rootx);
				final Stage stagex = new Stage();
				stagex.setScene(scenex);
				stagex.setTitle("Cadastro de Lançamentos");
				stagex.initModality(Modality.APPLICATION_MODAL);
				Main main = new Main();
				stagex.initOwner(main.stage);
				stagex.show();
			}catch (Exception e) {
				Util.mensagemErro("Erro: "+e.getMessage());
			}
	    }
	    
	    //Acessa Movimentações
	    @FXML
	    void onActionMIMov(ActionEvent event) {
	    	try{
				AnchorPane rootx = new AnchorPane();
				rootx = FXMLLoader.load(getClass().getResource("/com/siscond/view/Movimentacoes.fxml"));
				Scene scenex = new Scene(rootx);
				final Stage stagex = new Stage();
				stagex.setScene(scenex);
				stagex.setTitle("Cadastro de Movimentações");
				stagex.initModality(Modality.APPLICATION_MODAL);
				Main main = new Main();
				stagex.initOwner(main.stage);
				stagex.show();
			}catch (Exception e) {
				Util.mensagemErro("Erro: "+e.getMessage());
			}
	    }
	    
	    //Acessa Relatório Geral
	    @FXML
	    void onActionMIRelGer(ActionEvent event) {
	    	
	    }
	    
	    //Acessa Relatorio de Reservas
	    @FXML
	    void onActionMIRelRes(ActionEvent event) throws SQLException, JRException {
	    	try{
				AnchorPane rootx = new AnchorPane();
				rootx = FXMLLoader.load(getClass().getResource("/com/siscond/view/Relatorios.fxml"));
				Scene scenex = new Scene(rootx);
				final Stage stagex = new Stage();
				stagex.setScene(scenex);
				stagex.setTitle("Relatório de Reservas");
				stagex.initModality(Modality.APPLICATION_MODAL);
				Main main = new Main();
				stagex.initOwner(main.stage);
				stagex.show();
			}catch (Exception e) {
				Util.mensagemErro("Erro: "+e.getMessage());
			}
	    }
	    
	    //Acessa Reservas
	    @FXML
	    void onActionMIRes(ActionEvent event) {
	    	try{
				AnchorPane rootx = new AnchorPane();
				rootx = FXMLLoader.load(getClass().getResource("/com/siscond/view/Reservas.fxml"));
				Scene scenex = new Scene(rootx);
				final Stage stagex = new Stage();
				stagex.setScene(scenex);
				stagex.setTitle("Cadastro de Reservas");
				stagex.initModality(Modality.APPLICATION_MODAL);
				Main main = new Main();
				stagex.initOwner(main.stage);
				stagex.show();
			}catch (Exception e) {
				Util.mensagemErro("Erro: "+e.getMessage());
			}
	    }
	    
	    //Botão Sair
	    @FXML
	    void onActionMenuSair(ActionEvent event) {
	    	Platform.exit();
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
