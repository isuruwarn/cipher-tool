package org.warn.ciphertool.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

import org.warn.ciphertool.model.Encryption;
import org.warn.ciphertool.util.Constants;

public class MainWindow implements ActionListener {

	private JFrame mainFrame;
	private JTextField keyPath;
	private JTextPane input;
	private JTextPane output;
	private Encryption je;

	public MainWindow() {

		JPanel mainPanel = new JPanel();

		keyPath = new JTextField();
		keyPath.setPreferredSize(new Dimension(Constants.PREF_TEXTFIELD_WIDTH, Constants.PREF_TEXTFIELD_HEIGHT));
		keyPath.setMinimumSize(new Dimension(Constants.MIN_TEXTFIELD_HEIGHT, Constants.MIN_TEXTFIELD_HEIGHT));

		input = new JTextPane();
		input.setPreferredSize(new Dimension(Constants.PREF_TEXTAREA_WIDTH, Constants.PREF_TEXTAREA_HEIGHT));
		input.setMinimumSize(new Dimension(Constants.MIN_TEXTAREA_WIDTH, Constants.MIN_TEXTAREA_HEIGHT));
		input.setBorder( BorderFactory.createLineBorder( Color.LIGHT_GRAY, 1 ) );
		
		output = new JTextPane();
		output.setEditable(false);
		output.setPreferredSize(new Dimension(Constants.PREF_TEXTAREA_WIDTH, Constants.PREF_TEXTAREA_HEIGHT));
		output.setMinimumSize(new Dimension(Constants.MIN_TEXTAREA_WIDTH, Constants.MIN_TEXTAREA_HEIGHT));
		output.setBorder( BorderFactory.createLineBorder( Color.LIGHT_GRAY, 1 ) );
		
		JButton btnLoadKey = new JButton(Constants.LOAD_KEY);
		JButton btnEncrypt = new JButton(Constants.ENCRYPT);
		JButton btnDecrypt = new JButton(Constants.DECRYPT);
		JButton btnClear = new JButton(Constants.CLEAR);
		JButton btnCopyOutput = new JButton(Constants.COPY_OUTPUT);

		btnLoadKey.addActionListener(this);
		btnEncrypt.addActionListener(this);
		btnDecrypt.addActionListener(this);
		btnClear.addActionListener(this);
		btnCopyOutput.addActionListener(this);

		JLabel lblInput = new JLabel(Constants.INPUT);
		JLabel lblOutput = new JLabel(Constants.OUTPUT);

		// layout manager configurations
		mainPanel.setLayout(new GridBagLayout());

		GridBagConstraints keyBtnGridCons = new GridBagConstraints();
		keyBtnGridCons.gridx = 0;
		keyBtnGridCons.gridy = 0;
		keyBtnGridCons.anchor = GridBagConstraints.LINE_START;
		keyBtnGridCons.insets = new Insets(0, 0, 10, 10);

		GridBagConstraints keyTextGridCons = new GridBagConstraints();
		keyTextGridCons.gridx = 1;
		keyTextGridCons.gridy = 0;
		keyTextGridCons.gridwidth = 4;
		keyTextGridCons.insets = new Insets(0, 0, 10, 0);

		GridBagConstraints encBtnGridCons = new GridBagConstraints();
		encBtnGridCons.gridx = 1;
		encBtnGridCons.gridy = 1;
		encBtnGridCons.insets = new Insets(0, 20, 10, 10);
		encBtnGridCons.fill = GridBagConstraints.HORIZONTAL;

		GridBagConstraints decrBtnGridCons = new GridBagConstraints();
		decrBtnGridCons.gridx = 2;
		decrBtnGridCons.gridy = 1;
		decrBtnGridCons.insets = new Insets(0, 0, 10, 10);

		GridBagConstraints clearBtnGridCons = new GridBagConstraints();
		clearBtnGridCons.gridx = 3;
		clearBtnGridCons.gridy = 1;
		clearBtnGridCons.insets = new Insets(0, 0, 10, 10);

		GridBagConstraints copyOutputBtnGridCons = new GridBagConstraints();
		copyOutputBtnGridCons.gridx = 4;
		copyOutputBtnGridCons.gridy = 1;
		copyOutputBtnGridCons.insets = new Insets(0, 0, 10, 10);

		GridBagConstraints inputLblGridCons = new GridBagConstraints();
		inputLblGridCons.gridx = 0;
		inputLblGridCons.gridy = 2;

		GridBagConstraints inputGridCons = new GridBagConstraints();
		inputGridCons.gridx = 1;
		inputGridCons.gridy = 2;
		inputGridCons.gridwidth = 4;
		inputGridCons.insets = new Insets(0, 0, 20, 0);

		GridBagConstraints outputLblGridCons = new GridBagConstraints();
		outputLblGridCons.gridx = 0;
		outputLblGridCons.gridy = 3;

		GridBagConstraints outputGridCons = new GridBagConstraints();
		outputGridCons.gridx = 1;
		outputGridCons.gridy = 3;
		outputGridCons.gridwidth = 4;

		mainPanel.add(btnLoadKey, keyBtnGridCons);
		mainPanel.add(keyPath, keyTextGridCons);
		mainPanel.add(btnEncrypt, encBtnGridCons);
		mainPanel.add(btnDecrypt, decrBtnGridCons);
		mainPanel.add(btnClear, clearBtnGridCons);
		mainPanel.add(btnCopyOutput, copyOutputBtnGridCons);
		mainPanel.add(lblInput, inputLblGridCons);
		mainPanel.add(input, inputGridCons);
		mainPanel.add(lblOutput, outputLblGridCons);
		mainPanel.add(output, outputGridCons);

		// create file menu items
		JMenuItem createNewKey = new JMenuItem(Constants.NEW_KEY_ACTION);
		JMenuItem loadKey = new JMenuItem(Constants.LOAD_KEY_ACTION);
		JMenuItem exit = new JMenuItem(Constants.EXIT_ACTION);

		createNewKey.addActionListener(this);
		loadKey.addActionListener(this);
		exit.addActionListener(this);

		// create file menu
		JMenu fileMenu = new JMenu(Constants.FILE_MENU);
		fileMenu.add(createNewKey);
		fileMenu.add(loadKey);
		fileMenu.add(exit);

		// create edit menu items
		JMenuItem copyInput = new JMenuItem(Constants.COPY_INPUT_ACTION);
		JMenuItem copyOutput = new JMenuItem(Constants.COPY_OUTPUT_ACTION);
		JMenuItem clearAll = new JMenuItem(Constants.CLEAR_ALL_ACTION);

		copyInput.addActionListener(this);
		copyOutput.addActionListener(this);
		clearAll.addActionListener(this);

		// create edit menu
		JMenu editMenu = new JMenu(Constants.EDIT_MENU);
		editMenu.add(copyInput);
		editMenu.add(copyOutput);
		editMenu.add(clearAll);

		// create tool menu items
		JMenuItem encrypt = new JMenuItem(Constants.ENCRYPT_ACTION);
		JMenuItem decrypt = new JMenuItem(Constants.DECRYPT_ACTION);

		encrypt.addActionListener(this);
		decrypt.addActionListener(this);

		// create tools menu
		JMenu toolsMenu = new JMenu(Constants.TOOLS_MENU);
		toolsMenu.add(encrypt);
		toolsMenu.add(decrypt);

		// create help menu items
		JMenuItem insructions = new JMenuItem(Constants.INSTRUCTIONS);
		JMenuItem about = new JMenuItem(Constants.ABOUT);

		insructions.addActionListener(this);
		about.addActionListener(this);

		// create help menu
		JMenu helpMenu = new JMenu(Constants.HELP_MENU);
		helpMenu.add(insructions);
		helpMenu.add(about);

		// finally construct the menu bar and add menus
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(toolsMenu);
		menuBar.add(helpMenu);

		// get input mask for cntrl key / command key
		int controlCommandMask = ActionEvent.CTRL_MASK;
		String osType = System.getProperty(Constants.OS_PROP_NAME).toLowerCase();
		if (osType.startsWith(Constants.MAC_OS_NAME)) {
			controlCommandMask = ActionEvent.META_MASK;
		}

		// set keyboard short cuts
		createNewKey.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, controlCommandMask));
		loadKey.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, controlCommandMask));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, controlCommandMask));
		encrypt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, controlCommandMask));
		decrypt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, controlCommandMask));

		// frame for holding everything
		mainFrame = new JFrame(Constants.TITLE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setPreferredSize(new Dimension(Constants.PREF_FRAME_WIDTH, Constants.PREF_FRAME_HEIGHT));
		mainFrame.setMinimumSize(new Dimension(Constants.MIN_FRAME_WIDTH, Constants.MIN_FRAME_HEIGHT));
		mainFrame.add(mainPanel);
		mainFrame.setJMenuBar(menuBar);
		mainFrame.pack();
		mainFrame.setVisible(true);

		je = new Encryption();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

		if (command.equals(Constants.LOAD_KEY) || command.equals(Constants.LOAD_KEY_ACTION)) {

			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				keyPath.setText(file.getPath());
			}

		} else if (command.equals(Constants.ENCRYPT) || command.equals(Constants.ENCRYPT_ACTION)) {

			String keyFile = keyPath.getText();

			if (keyFile == null || keyFile.equals("")) {
				JOptionPane.showMessageDialog(mainFrame, Constants.LOAD_KEY_MESSAGE, Constants.ERROR,
						JOptionPane.ERROR_MESSAGE);

			} else {

				String msg = input.getText();

				if (msg != null && !msg.equals("")) {

					String cipherText = je.encrypt(msg, Constants.ALGO, keyFile);
					output.setText(cipherText);

				} else {
					output.setText(null);
				}

			}

		} else if (command.equals(Constants.DECRYPT) || command.equals(Constants.DECRYPT_ACTION)) {

			String keyFile = keyPath.getText();

			if (keyFile == null || keyFile.equals("")) {
				JOptionPane.showMessageDialog(mainFrame, Constants.LOAD_KEY_MESSAGE, Constants.ERROR,
						JOptionPane.ERROR_MESSAGE);

			} else {

				String cipherText = input.getText();

				if (cipherText != null && !cipherText.equals("")) {

					String decryptedText = je.decrypt(cipherText, Constants.ALGO, keyFile);
					output.setText(decryptedText);

				} else {
					output.setText(null);
				}
			}

		} else if (command.equals(Constants.NEW_KEY_ACTION)) {

			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				String newKeyFilePath = file.getPath();
				je.writeKey(newKeyFilePath, Constants.ALGO);
			}

		} else if (command.equals(Constants.EXIT_ACTION)) {
			System.exit(0);

		} else if (command.equals(Constants.COPY_INPUT_ACTION)) {
			input.selectAll();
			input.copy();
			input.requestFocusInWindow();

		} else if ( command.equals(Constants.COPY_OUTPUT_ACTION) || command.equals(Constants.COPY_OUTPUT) ) {
			output.selectAll();
			output.copy();
			output.requestFocusInWindow();

		} else if (command.equals(Constants.CLEAR_ALL_ACTION)) {
			input.setText(null);
			output.setText(null);

		} else if (command.equals(Constants.INSTRUCTIONS)) {
			JOptionPane.showMessageDialog(mainFrame, Constants.INSTRUCTIONS_MESSAGE, Constants.INSTRUCTIONS,
					JOptionPane.INFORMATION_MESSAGE);

		} else if (command.equals(Constants.ABOUT)) {
			JOptionPane.showMessageDialog(mainFrame, Constants.ABOUT_MESSAGE, Constants.ABOUT,
					JOptionPane.INFORMATION_MESSAGE);

		} else if (command.equals(Constants.CLEAR)) {
			input.setText(null);
			output.setText(null);
			input.requestFocusInWindow();
		}

	}

}
