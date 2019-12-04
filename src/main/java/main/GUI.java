package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class GUI {
	
	private Application app;
	private JFrame frame = new JFrame("Aplica��o");
	private JTextField filePath;
	private JButton longMethod;
	private JButton featureEnvyButton;
	private JButton defectDetection;
	private JTextField DCIOutput;
	private JTextField DIIOutput;
	private JTextField ADCIOutput;
	private JTextField ADIIOutput;

	public GUI(Application app) {
		this.app = app;
		createFrame();
		frame.setVisible(true);
	}

	private void createFrame() {

		frame.setLayout(new GridLayout(7, 10));
		addFields();

		frame.setSize(720, 576);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void addFields() {
		//Parte de cima 
		JPanel panel_aux1 = new JPanel();
		
		filePath = new JTextField("Please Load An Exel file");
		filePath.setHorizontalAlignment(JTextField.CENTER);
        filePath.setEditable(false);
        filePath.setPreferredSize(new Dimension(328, 50));
        panel_aux1.add(filePath, BorderLayout.LINE_START);
        
        JButton loadFileButton = new JButton("Load File");
        loadFileButton.setHorizontalAlignment(JButton.CENTER);
        loadFileButton.setPreferredSize(new Dimension(300, 50));
        panel_aux1.add(loadFileButton);
        loadFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					app.setPath(chooser.getSelectedFile().getAbsolutePath());
					filePath.setText(chooser.getSelectedFile().getName());
					defectDetection.setEnabled(true);
					featureEnvyButton.setEnabled(true);
					longMethod.setEnabled(true);
                }
			}
		});
        
        frame.add(panel_aux1);
        //Fim da Parte de cima
       
        //Depois da Parte de cima
        Border border = BorderFactory.createLineBorder(Color.BLUE, 1);
        
        JPanel panel_aux2 = new JPanel();
        
        JLabel linesOfCode = new JLabel("Lines of code");
        linesOfCode.setHorizontalAlignment(JLabel.CENTER);
        linesOfCode.setBorder(border);
        linesOfCode.setPreferredSize(new Dimension(115, 30));
		panel_aux2.add(linesOfCode);
        
		final JTextField loc = new JTextField("80");
		loc.setHorizontalAlignment(JTextField.CENTER);
		loc.setPreferredSize(new Dimension(30, 30));
		panel_aux2.add(loc);
		loc.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent keyEvent) {
				char c = keyEvent.getKeyChar();
				if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
					keyEvent.consume();
				}
			}
		});
		
		JLabel cyclomaticComplexity = new JLabel("Cyclomatic Complexity");
		cyclomaticComplexity.setHorizontalAlignment(JLabel.CENTER);
		cyclomaticComplexity.setBorder(border);
		cyclomaticComplexity.setPreferredSize(new Dimension(140, 30));
		panel_aux2.add(cyclomaticComplexity);

		final JTextField cyclo = new JTextField("10");
		cyclo.setHorizontalAlignment(JTextField.CENTER);
		cyclo.setPreferredSize(new Dimension(30, 30));
		panel_aux2.add(cyclo);
		cyclo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent keyEvent) {
				char c = keyEvent.getKeyChar();
				if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
					keyEvent.consume();
				}
			}
		});
		
		longMethod = new JButton("Long Method");
		longMethod.setPreferredSize(new Dimension(300, 50));
		longMethod.setEnabled(false);
		panel_aux2.add(longMethod);
		longMethod.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loc.getText() != "" && cyclo.getText() != "") {
					int locValue = Integer.parseInt(loc.getText());
					int cycloValue = Integer.parseInt(cyclo.getText());

					app.setLocCycloThresholds(locValue, cycloValue);
					app.longMethod();
				}
			}
		});
		
		frame.add(panel_aux2);
		
		//Antes da parte de baixo
		
		JPanel panel_aux3 = new JPanel();
		
		
		JLabel featureEnvy = new JLabel("Feature Envy");
		featureEnvy.setHorizontalAlignment(JLabel.CENTER);
		featureEnvy.setBorder(border);
		featureEnvy.setPreferredSize(new Dimension(600, 50));
		panel_aux3.add(featureEnvy);
		
		frame.add(panel_aux3);
		
		JPanel panel_aux4 = new JPanel();
		
		JLabel atfd = new JLabel("ATFD >");
		atfd.setHorizontalAlignment(JLabel.CENTER);
		atfd.setBorder(border);
		atfd.setPreferredSize(new Dimension(75, 30));
		panel_aux4.add(atfd);
		
		JTextField atfd_txt = new JTextField("0");
		atfd_txt.setHorizontalAlignment(JTextField.CENTER);
		atfd_txt.setPreferredSize(new Dimension(30, 30));
		panel_aux4.add(atfd_txt);
		
		String[] option = { "and", "or"};
		JComboBox optionList = new JComboBox(option);
		optionList.setSelectedIndex(1);
		optionList.setPreferredSize(new Dimension(108, 30));
		panel_aux4.add(optionList);
		optionList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JComboBox cb = (JComboBox)e.getSource();
			     String optionSelected = (String)cb.getSelectedItem();
			     if(optionSelected.equals("and")) {
			    	 //and things
			    	 
			     }
			     if(optionSelected.equals("or")) {
			    	 //or things
			    	 
			     }
			}
		});
		
		JLabel laa = new JLabel("LAA <");
		laa.setHorizontalAlignment(JLabel.CENTER);
		laa.setBorder(border);
		laa.setPreferredSize(new Dimension(75, 30));
		panel_aux4.add(laa);
		
		JTextField laa_txt = new JTextField("0");
		laa_txt.setHorizontalAlignment(JTextField.CENTER);
		laa_txt.setPreferredSize(new Dimension(30, 30));
		panel_aux4.add(laa_txt);
		
		featureEnvyButton = new JButton("Feature Envy Start");
		featureEnvyButton.setHorizontalAlignment(JButton.CENTER);
		featureEnvyButton.setPreferredSize(new Dimension(300, 50));
		featureEnvyButton.setEnabled(false);
        panel_aux4.add(featureEnvyButton);
        featureEnvyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				app.feature_envy(Integer.parseInt(atfd_txt.getText()), "and", Integer.parseInt());
//				app.feature_envy(ATFDThreshold, "or", LAAThreshold);
				
			}
		});
		
		frame.add(panel_aux4);
		// parte de baixo
		JPanel panel_aux9 = new JPanel();
		defectDetection = new JButton("Defect Defection");
        defectDetection.setHorizontalAlignment(JButton.CENTER);
        defectDetection.setEnabled(false);
        defectDetection.setPreferredSize(new Dimension(600, 50));
        panel_aux9.add(defectDetection);
        defectDetection.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.defectDetection();
            }
        });
        
        frame.add(panel_aux9);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 4));
		Dimension dim = new Dimension(200, 50);
		
		JLabel DCI = new JLabel("DCI");
		DCI.setHorizontalAlignment(JTextField.CENTER);
		DCI.setBorder(border);
		DCI.setPreferredSize(dim);
		panel.add(DCI);
		
		JLabel DII = new JLabel("DII");
		DII.setHorizontalAlignment(JTextField.CENTER);
		DII.setBorder(border);
		DII.setPreferredSize(dim);
		panel.add(DII);
		
		JLabel ADCI = new JLabel("ADCI");
		ADCI.setHorizontalAlignment(JTextField.CENTER);
		ADCI.setBorder(border);
		ADCI.setPreferredSize(dim);
		panel.add(ADCI);
		
		JLabel ADII = new JLabel("ADII");
		ADII.setHorizontalAlignment(JTextField.CENTER);
		ADII.setBorder(border);
		ADII.setPreferredSize(dim);
		panel.add(ADII);

		DCIOutput = new JTextField("Load File");
		DCIOutput.setHorizontalAlignment(JTextField.CENTER);
		DCIOutput.setEditable(false);
		DCIOutput.setBorder(border);
		panel.add(DCIOutput);
		
		DIIOutput = new JTextField("Load File");
		DIIOutput.setHorizontalAlignment(JTextField.CENTER);
		DIIOutput.setEditable(false);
		DIIOutput.setBorder(border);
		panel.add(DIIOutput);
		
		ADCIOutput = new JTextField("Load File");
		ADCIOutput.setHorizontalAlignment(JTextField.CENTER);
		ADCIOutput.setEditable(false);
		ADCIOutput.setBorder(border);
		panel.add(ADCIOutput);
		
		ADIIOutput = new JTextField("Load File");
		ADIIOutput.setHorizontalAlignment(JTextField.CENTER);
		ADIIOutput.setEditable(false);
		ADIIOutput.setBorder(border);
		panel.add(ADIIOutput);
		
		frame.add(panel);

	}

	public void receiveOutputLongMethod(List<Method> longMethods, List<Method> nonLongMethods) {
		// mostra na gui os resultados da execucao do long method
		JFrame longMethodFrame = new JFrame("Long Method Results");//nova frame para os resultados
		String [] col = {"MethodId", "MethodName"};
		DefaultTableModel tableModel = new DefaultTableModel(col,0);
		JTable table = new JTable(tableModel);
		
		for (Method m : longMethods) {//adicionar os longMethod � tabela
			String [] aux = {""+m.getMethodId(),m.getName()};
			tableModel.addRow(aux);
		}
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setEnabled(false);//para nao poder ser editada
		
		longMethodFrame.add(table);
		longMethodFrame.pack();
		longMethodFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		longMethodFrame.setVisible(true);
       
	}

	public void receiveOutputDefectDetection(int DCIValue, int DIIValue, int ADCIValue, int ADIIValue) {
		DCIOutput.setText("" + DCIValue);
		DIIOutput.setText("" + DIIValue);
		ADCIOutput.setText("" + ADCIValue);
		ADIIOutput.setText("" + ADIIValue);
	}
	
	public void receiveOutputFeatureEnvy(List<Method> feature_envy, List<Method> nonfeature_envy) {
		
	}

}
