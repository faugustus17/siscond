package com.siscond.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.siscond.dao.Conexao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class RelatoriosController {
	@FXML
    private Button btnGerarRelReservas;

    @FXML
    void onActionRelReservas(ActionEvent event) throws SQLException, JRException {
    	this.relReservas();
    }

	
	Conexao conexao = new Conexao();
	Connection conn = conexao.abreConexaoBD();
	ResultSet rs = null;
	Statement st = null;
	
	public void relReservas() throws SQLException, JRException{
		st = conn.createStatement();
		rs = st.executeQuery("SELECT * FROM tb_reservas ORDER BY num_apto");
		JRResultSetDataSource relResult = new JRResultSetDataSource(rs);
		Map<String, Object> parameters = new HashMap<String, Object>();
		JasperReport jasperReport = JasperCompileManager.compileReport("C:/Users/Nando/workspace/siscond/src/com/siscond/relatorios/RelReservas.jrxml");
		JasperPrint jpPrint = JasperFillManager.fillReport(jasperReport, parameters, relResult);
		JasperViewer jv = new JasperViewer(jpPrint, false);
		jv.setVisible(true);
	}
	

}
