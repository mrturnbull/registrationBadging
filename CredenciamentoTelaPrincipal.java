import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.*;
import java.lang.SecurityException;
import java.util.List;
import java.util.Scanner;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.*;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class CredenciamentoTelaPrincipal extends JFrame{

	private JTabbedPane colTabs;

	private Participante currentEntry;
	private ParticipanteQueries participanteQueries;
	private List<Participante> Participantes;
	private int numberOfEntries = 0;
	private int currentEntryIndex;
	
	private JButton btnNavega;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblNome;
	private JLabel lblEmpresa;
	private JTextField txtNome;
	//private JTextArea txtNome;
	private JTextField txtEmpresa;
	private JLabel lblID;
	private JTextField txtID;
	private JTextField txtIndice;
	//private JTextField txtMax;
	private JButton btnProximo;
	private JLabel lblDe;
	private JLabel lblConsulta;
	private JLabel lblCategoria;	
		
	//private JButton btnAnterior;
	private JButton btnConsulta;
	
	private JPanel panelConsulta;
	private JPanel navigatePanel;
	private JPanel displayPanel;
	private JTextField txtConsulta;
	private JButton btnSalvar;
	
	private JButton btnDummy;
	private JButton btnNome;
	private JButton btnEmpresa;
	private JButton btnEmail;
	private JButton btnCategoria;
	
	private JPanel panelCadastro;
	
	/////////////////////////////////////////////
	private JPanel panelExportacao;
	private JPanel panelImportacao;
	private JPanel panelImpressora;
	private JTable tabelaBusca;
	
	private JButton btnExportacao;
	private JButton btnNovo;
	private JTextField txtPathExportacao;	
	
	private JLabel lblFone;
	private JTextField txtFone;
	private JButton btnFone;
	
	private JLabel lblLocalTrabalho;
	private JTextField txtLocalTrabalho;
	private JButton btnLocalTrabalho;		
	
	private JLabel lblSupervisaoSaude;
	private JTextField txtSupervisaoSaude;
	private JButton btnSupervisaoSaude;		
	
	private ListSelectionModel listSelectionModel;
	
	private JComboBox cmbImpressora;
	private static final String[] Impressoras = {"Brother QL-570", "Brother QL-550", "Brother QL-5702"};
	
	private JComboBox cmbCategoria;
	private static final String[] Categorias = {"MEDICO", "ENFERMEIRO", "OUTRO"};
	
	public CredenciamentoTelaPrincipal(){
	
		super("Dragon Eventos - Credenciamento");
		
		try {		
			participanteQueries = new ParticipanteQueries();
		}
		catch (ClassNotFoundException classNotFoundException){
			System.err.printf("\nExcecao:%s\n", classNotFoundException);
			System.exit(0);
		}
		
		colTabs = new JTabbedPane();	
		/////////////////////////////////////////////////////////////////////////////////////
		// 
		// TAB CADASTRO
		// 
		/////////////////////////////////////////////////////////////////////////////////////		
		panelCadastro = new JPanel();
		
		navigatePanel = new JPanel();
		//btnAnterior = new JButton();
		txtIndice = new JTextField(2);
		lblDe = new JLabel();
		//txtMax = new JTextField(2);
		btnProximo = new JButton();
		
		displayPanel = new JPanel();
		
		lblID = new JLabel("ID:");
		txtID = new JTextField(10);
		btnDummy = new JButton("");
		
		lblNome = new JLabel("Nome:");
		txtNome = new JTextField(20);
		btnNome = new JButton("Editar");
		
		lblEmpresa = new JLabel("CRM/COREN:");
		txtEmpresa = new JTextField(20);
		btnEmpresa = new JButton("Editar");
		
		lblFone = new JLabel("Celular");
		txtFone = new JTextField(10);
		btnFone = new JButton("Editar");
		
		lblEmail = new JLabel("Email:");
		txtEmail = new JTextField(20);
		btnEmail = new JButton("Editar");		
		
		lblCategoria = new JLabel("Categoria:");
		cmbCategoria = new JComboBox(Categorias);
		btnCategoria = new JButton("Editar");
		
		lblLocalTrabalho = new JLabel("Local de trabalho:");
		txtLocalTrabalho = new JTextField(20);
		btnLocalTrabalho = new JButton("Editar");		
		
		lblSupervisaoSaude = new JLabel("Supervisao de Saude:");
		txtSupervisaoSaude = new JTextField(20);
		btnSupervisaoSaude = new JButton("Editar");		
		
		
		panelConsulta = new JPanel();
		lblConsulta = new JLabel();
		txtConsulta = new JTextField(30);
		btnConsulta = new JButton();
		btnNavega = new JButton();
		btnSalvar = new JButton();
		btnNovo = new JButton();
		
		panelCadastro.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
		setSize(800, 600);
		setResizable(true);
		
		/////////////////////////////////////////////////////////////////////////////////////
		// Display Panel
		/////////////////////////////////////////////////////////////////////////////////////
		displayPanel.setLayout(new GridLayout(8, 3, 10, 10));
		
		//lblID.setText("ID:");
		displayPanel.add(lblID);
		txtID.setEditable(false);
		displayPanel.add(txtID);
		displayPanel.add(btnDummy);
		btnDummy.setVisible(false);
				
		//Nome
		displayPanel.add(lblNome);				
		displayPanel.add(txtNome);
		btnNome.setVisible(false);
		btnNome.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					txtNome.setEditable(true);
					txtEmpresa.setEditable(false);
					txtEmail.setEditable(false);
					cmbCategoria.setEnabled(false);
					txtFone.setEditable(false);
					txtLocalTrabalho.setEditable(false);
					txtSupervisaoSaude.setEditable(false);				
				}		
			}		
		);
		displayPanel.add(btnNome);
			
		
		//Empresa
		displayPanel.add(lblEmpresa);
		txtEmpresa.setText("");
		displayPanel.add(txtEmpresa);
		btnEmpresa.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					txtEmpresa.setEditable(true);
					txtNome.setEditable(false);
					txtEmail.setEditable(false);
					cmbCategoria.setEnabled(false);
					txtFone.setEditable(false);
					txtLocalTrabalho.setEditable(false);
					txtSupervisaoSaude.setEditable(false);
				}		
			}		
		);
		btnEmpresa.setVisible(false);
		displayPanel.add(btnEmpresa);
		
		
		//Telefone		
		displayPanel.add(lblFone);				
		displayPanel.add(txtFone);
		btnFone.setVisible(false);
		btnFone.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					txtEmail.setEditable(false);
					txtNome.setEditable(false);
					txtEmpresa.setEditable(false);
					cmbCategoria.setEnabled(false);
					txtFone.setEditable(true);
					txtLocalTrabalho.setEditable(false);
					txtSupervisaoSaude.setEditable(false);
				}		
			}		
		);
		displayPanel.add(btnFone);
		
			
		//Email		
		displayPanel.add(lblEmail);				
		displayPanel.add(txtEmail);
		btnEmail.setVisible(false);
		btnEmail.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					txtEmail.setEditable(true);
					txtNome.setEditable(false);
					txtEmpresa.setEditable(false);
					cmbCategoria.setEnabled(false);
					txtFone.setEditable(false);
					txtLocalTrabalho.setEditable(false);
					txtSupervisaoSaude.setEditable(false);
				}		
			}		
		);
		displayPanel.add(btnEmail);
		
		//Categoria
		displayPanel.add(lblCategoria);
		displayPanel.add(cmbCategoria);
		btnCategoria.setVisible(false);
		btnCategoria.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					cmbCategoria.setEnabled(true);
					txtEmail.setEditable(false);
					txtNome.setEditable(false);
					txtEmpresa.setEditable(false);
					txtFone.setEditable(false);
					txtLocalTrabalho.setEditable(false);
					txtSupervisaoSaude.setEditable(false);
				}		
			}		
		);
		displayPanel.add(btnCategoria);
		
		//Local de trabalho		
		displayPanel.add(lblLocalTrabalho);				
		displayPanel.add(txtLocalTrabalho);
		btnLocalTrabalho.setVisible(false);
		btnLocalTrabalho.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					txtEmail.setEditable(false);
					txtNome.setEditable(false);
					txtEmpresa.setEditable(false);
					cmbCategoria.setEnabled(false);
					txtFone.setEditable(false);
					txtLocalTrabalho.setEditable(true);
					txtSupervisaoSaude.setEditable(false);
				}		
			}		
		);
		displayPanel.add(btnLocalTrabalho);
		
		//Supervisao saude		
		displayPanel.add(lblSupervisaoSaude);				
		displayPanel.add(txtSupervisaoSaude);
		btnSupervisaoSaude.setVisible(false);
		btnSupervisaoSaude.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					txtEmail.setEditable(false);
					txtNome.setEditable(false);
					txtEmpresa.setEditable(false);
					cmbCategoria.setEnabled(false);
					txtFone.setEditable(false);
					txtLocalTrabalho.setEditable(false);
					txtSupervisaoSaude.setEditable(true);
				}		
			}		
		);
		displayPanel.add(btnSupervisaoSaude);
				
		panelCadastro.add(displayPanel);	
		
		/////////////////////////////////////////////////////////////////////////////////////
		// Buttons
		/////////////////////////////////////////////////////////////////////////////////////
		btnNovo.setText("Novo");
		btnNovo.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					btnNovoActionPerformed(evt);					
				}		
			}		
		);
		btnNovo.setVisible(false);
		panelCadastro.add(btnNovo);
		
		btnSalvar.setText("Salvar");
		btnSalvar.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					btnSalvarActionPerformed(evt);
				}		
			}		
		);
		panelCadastro.add(btnSalvar);
		
		

		/////////////////////////////////////////////////////////////////////////////////////////
		// ??
		/////////////////////////////////////////////////////////////////////////////////////////
		/*
		addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent evt){
					participanteQueries.close();
					System.exit(0);
				}
			}
		);
		*/
		panelCadastro.setVisible(true);		
		
		colTabs.addTab("Cadastro", null, panelCadastro, "");
		
		/////////////////////////////////////////////////////////////
		//
		// TAB BUSCA
		//
		//////////////////////////////////////////////////////////////
		panelImportacao = new JPanel();
				
		panelConsulta.setLayout(new BoxLayout(panelConsulta, BoxLayout.X_AXIS));
		
		panelConsulta.setBorder(BorderFactory.createTitledBorder("Busca por nome"));
		
		//panelConsulta.setText("Nome:");
		panelConsulta.add(Box.createHorizontalStrut(5));
		panelConsulta.add(lblConsulta);
		panelConsulta.add(Box.createHorizontalStrut(10));
		panelConsulta.add(txtConsulta);
		panelConsulta.add(Box.createHorizontalStrut(10));
		
		btnConsulta.setText("Buscar");
		btnConsulta.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					btnConsultaActionPerformed(evt);			
				}		
			}		
		);
		panelConsulta.add(btnConsulta);
		panelConsulta.add(Box.createHorizontalStrut(200));
		panelImportacao.add(panelConsulta);

		/////////////////////////////////////////////////////////////////////////////////////////
		// Tabela com resultados da busca
		/////////////////////////////////////////////////////////////////////////////////////////
		tabelaBusca = new JTable();
		
		listSelectionModel = tabelaBusca.getSelectionModel();
		listSelectionModel.addListSelectionListener(new RowListener());
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaBusca.setSelectionModel(listSelectionModel);
		
		
		JScrollPane scrollPaneTabelaBusca = new JScrollPane(tabelaBusca);
		
		panelImportacao.add(scrollPaneTabelaBusca);
		
		scrollPaneTabelaBusca.setVisible(true);		
		
		colTabs.addTab("Busca", null, panelImportacao, "");
		
		/////////////////////////////////////////////////////////////
		//
		// TAB EXPORTACAO
		//
		//////////////////////////////////////////////////////////////
		panelExportacao = new JPanel();
		btnExportacao = new JButton();
		txtPathExportacao = new JTextField("C:", 30);
		
		panelExportacao.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
		
		
		panelExportacao.add(txtPathExportacao);
		
		btnExportacao.setText("Exportar no formato CSV");
		
		btnExportacao.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					btnExportacaoActionPerformed(evt);			
				}		
			}		
		);
		
		panelExportacao.add(btnExportacao);
		
		colTabs.addTab("Exportacao", null, panelExportacao, "");
		
		/////////////////////////////////////////////////////////////
		//
		// TAB IMPRESSORA
		//
		//////////////////////////////////////////////////////////////
		panelImpressora = new JPanel();
		cmbImpressora = new JComboBox(Impressoras);
		
		panelImpressora.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
		panelImpressora.add(cmbImpressora);
		colTabs.addTab("Impressora", null, panelImpressora, "");
		
		
		/////////////////////////////////////////////////////////////
		//
		// 
		//
		//////////////////////////////////////////////////////////////
		add(colTabs);
		txtNome.requestFocusInWindow();
		
	}
	
	private void btnNovoActionPerformed(ActionEvent evt){
	
		setEstadoNovoParticipante();			
	
	}
	
	private void btnExportacaoActionPerformed(ActionEvent evt){
	
		String path = txtPathExportacao.getText();
		Formatter output;
	
		if (path.length() == 0){
			JOptionPane.showMessageDialog(null, "Caminho de destino do CSV em branco", "Erro", JOptionPane.PLAIN_MESSAGE);
			txtPathExportacao.requestFocusInWindow();
			return;
		}
		
		path = path + "\\" + "Relatorio.csv";
		
		try{
			output = new Formatter(path);
		}
		catch(FileNotFoundException fileNotFoundException){
			JOptionPane.showMessageDialog(null, "Caminho invalido", "Erro", JOptionPane.PLAIN_MESSAGE);
			txtPathExportacao.requestFocusInWindow();
			return;
		}
		
		Participantes = participanteQueries.getTodosParticipantes();
			
		for (Participante p : Participantes) {
			output.format("%s;%s;%s;%s;%s;%s;%s;%s\r\n", p.getID(), p.getNome(), p.getEmpresa(), p.getEmail(), p.getCategoria(), p.getFone(), p.getLocalTrabalho(), p.getSupervisaoSaude());
		}
		
		if (output != null) output.close();
		
		JOptionPane.showMessageDialog(null, "Arquivo " + path + " gerado com sucesso", "Atencao", JOptionPane.PLAIN_MESSAGE);
	
	}
		
	private void btnConsultaActionPerformed(ActionEvent evt){
	
		Participantes = participanteQueries.getParticipantesPorNome(txtConsulta.getText());
		
		numberOfEntries = Participantes.size();
		
		DefaultTableModel aModel = new DefaultTableModel(){
				
			@Override
			public boolean isCellEditable(int row, int column){
				return (false);
			}
			
		};
		
		if (numberOfEntries > 0){
			
			Object[] tabelaNomesColunas = new Object[8];
			tabelaNomesColunas[0] = "ID";
			tabelaNomesColunas[1] = "Nome";
			tabelaNomesColunas[2] = "CRM/COREN";
			tabelaNomesColunas[3] = "Email";
			tabelaNomesColunas[4] = "Categoria";
			tabelaNomesColunas[5] = "Fone";
			tabelaNomesColunas[6] = "Local de Trabalho";
			tabelaNomesColunas[7] = "Supervisao de Saude";
			
			aModel.setColumnIdentifiers(tabelaNomesColunas);		
					
			int i = 0;
			Object[] objects = new Object[8];
			for (Participante p : Participantes){
				
				objects[0] = (p.getID() + " ");
				objects[1] = p.getNome();
				objects[2] = p.getEmpresa();
				objects[3] = p.getEmail();
				objects[4] = p.getCategoria();
				objects[5] = p.getFone();
				objects[6] = p.getLocalTrabalho();
				objects[7] = p.getSupervisaoSaude();			
				
				aModel.addRow(objects);			
				
			}
			
			tabelaBusca.setModel(aModel);
			
		}
		else{
			tabelaBusca.setModel(aModel);
			JOptionPane.showMessageDialog(null, "Nada encontrado", "Atencao", JOptionPane.PLAIN_MESSAGE);
		}
	
	}
		
	private void btnSalvarActionPerformed(ActionEvent evt){
	
		String strID = txtID.getText().trim();
		String nome = txtNome.getText();
		String empresa = txtEmpresa.getText();
		String email = txtEmail.getText();
		String categoria = (String)cmbCategoria.getSelectedItem();
		String fone = txtFone.getText();
		String localTrabalho = txtLocalTrabalho.getText();
		String supervisaoSaude = txtSupervisaoSaude.getText();		
		String nomeImpressora = (String)cmbImpressora.getSelectedItem();
		
	
		if (nome.length() == 0){
			JOptionPane.showMessageDialog(this, "Nome invalido", "Atencao", JOptionPane.PLAIN_MESSAGE);
			txtNome.requestFocusInWindow();
			return;	
		}
		
		/*
		if (empresa.length() == 0){
			JOptionPane.showMessageDialog(this, "Twitter invalido", "Atencao", JOptionPane.PLAIN_MESSAGE);
			txtEmpresa.requestFocusInWindow();
			return;
		}
		
		if (email.length() == 0){
			JOptionPane.showMessageDialog(this, "Email invalido", "Atencao", JOptionPane.PLAIN_MESSAGE);
			txtEmail.requestFocusInWindow();
			return;
		}
		*/		
		
		//String strCmd = "wscript BcdLabel.vbs " + strID + " " + nome.replace(' ', '?') + " " + empresa.replace(' ', '?');
		String strCmd = "wscript BcdLabel.vbs " + nome.replace(' ', '?') + " " + nomeImpressora.replace(' ', '?');
				
		if (strID.length() == 0){//Inserir
			int result = participanteQueries.insereParticipante(nome, empresa, email, categoria, fone, localTrabalho, supervisaoSaude);						
			if (result == 0) JOptionPane.showMessageDialog(this, "Participante nao foi adicionado", "Erro", JOptionPane.PLAIN_MESSAGE);
			
			try {
				Process p = Runtime.getRuntime().exec(strCmd);		
			}
			catch (IOException ioException){
				System.out.println("Impossivel executar o script BcdLabel.vbs");			
			}
			
		}
		else{//Atualizar
			int result = participanteQueries.atualizaParticipante(strID, nome, empresa, email, categoria, fone, localTrabalho, supervisaoSaude);
			if (result == 0) JOptionPane.showMessageDialog(this, "Participante nao foi atualizado", "Erro", JOptionPane.PLAIN_MESSAGE);
			Object arrOpcoes[] = { "Sim", "Nao" };
			int resposta = JOptionPane.showOptionDialog(this, "Deseja imprimir ?", "Atencao", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, arrOpcoes, arrOpcoes[0]);	
			if (resposta == JOptionPane.YES_OPTION){
						
				try {
					Process p = Runtime.getRuntime().exec(strCmd);		
				}
				catch (IOException ioException){
					System.out.println("Impossivel executar o script BcdLabel.vbs");			
				}
							
			}			
		}
		
		setEstadoNovoParticipante();
		
	}
	
	private void setEstadoNovoParticipante(){
	
		txtID.setText("");
		
		txtNome.setText("");
		txtNome.setEditable(true);
		btnNome.setVisible(false);
		
		txtEmpresa.setText("");
		txtEmpresa.setEditable(true);
		btnEmpresa.setVisible(false);
		
		txtFone.setText("");
		txtFone.setEditable(true);
		btnFone.setVisible(false);
		
		txtEmail.setText("");
		txtEmail.setEditable(true);
		btnEmail.setVisible(false);
		
		cmbCategoria.setEnabled(true);
		cmbCategoria.setSelectedIndex(0);
		btnCategoria.setVisible(false);
		
		txtLocalTrabalho.setText("");
		txtLocalTrabalho.setEditable(true);
		btnLocalTrabalho.setVisible(false);
		
		txtSupervisaoSaude.setText("");
		txtSupervisaoSaude.setEditable(true);
		btnSupervisaoSaude.setVisible(false);
		
		btnNovo.setVisible(false);	
		txtNome.requestFocusInWindow();		
	
	}
	
	private class RowListener implements ListSelectionListener {
	
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
			
			int linha = tabelaBusca.getSelectionModel().getLeadSelectionIndex();

			txtID.setText((String) tabelaBusca.getValueAt(linha, 0));
			txtNome.setText((String) tabelaBusca.getValueAt(linha, 1));
			txtEmpresa.setText((String) tabelaBusca.getValueAt(linha, 2));
			txtEmail.setText((String) tabelaBusca.getValueAt(linha, 3));			
			String categoriaSelecionada = (String) tabelaBusca.getValueAt(linha, 4);
			txtFone.setText((String) tabelaBusca.getValueAt(linha, 5));
			txtLocalTrabalho.setText((String) tabelaBusca.getValueAt(linha, 6));
			txtSupervisaoSaude.setText((String) tabelaBusca.getValueAt(linha, 7));			
			
			int idxCategoria = 0; //Medico
			if (categoriaSelecionada.equals("ENFERMEIRO")) idxCategoria = 1;
			if (categoriaSelecionada.equals("OUTRO")) idxCategoria = 2;
			
			cmbCategoria.setSelectedIndex(idxCategoria);			
			
			//txtEmail.setText("");
			btnNome.setVisible(true);
			btnEmpresa.setVisible(true);
			btnEmail.setVisible(true);
			btnCategoria.setVisible(true);
			btnFone.setVisible(true);
			btnLocalTrabalho.setVisible(true);
			btnSupervisaoSaude.setVisible(true);			
			
			txtNome.setEditable(false);
			txtEmpresa.setEditable(false);
			txtEmail.setEditable(false);
			cmbCategoria.setEnabled(false);
			txtFone.setEditable(false);
			txtLocalTrabalho.setEditable(false);
			txtSupervisaoSaude.setEditable(false);
			
			btnNovo.setVisible(true);
			
			colTabs.setSelectedIndex(0);
        }
    }		
}