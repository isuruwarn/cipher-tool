package org.warn.ciphertool.util;

public class Constants {

	public static final String TITLE = "Cipher Tool";
	public static final int PREF_FRAME_WIDTH = 600;
	public static final int PREF_FRAME_HEIGHT = 320;
	public static final int MIN_FRAME_WIDTH = 600;
	public static final int MIN_FRAME_HEIGHT = 320;
	
	public static final String LOAD_KEY = "Load Key";
	public static final String ENCRYPT = "Encrypt";
	public static final String DECRYPT = "Decrypt";
	public static final String INPUT = "Input";
	public static final String OUTPUT = "Output";
	public static final String CLEAR = "Clear";
	public static final String COPY_OUTPUT = "Copy Output";
	
	public static final int PREF_TEXTFIELD_WIDTH = 400;
	public static final int PREF_TEXTFIELD_HEIGHT = 25;
	public static final int MIN_TEXTFIELD_WIDTH = 400;
	public static final int MIN_TEXTFIELD_HEIGHT = 25;
	public static final int PREF_TEXTAREA_WIDTH = 400;
	public static final int PREF_TEXTAREA_HEIGHT = 70;
	public static final int MIN_TEXTAREA_WIDTH = 400;
	public static final int MIN_TEXTAREA_HEIGHT = 70;
	
	public static final String FILE_MENU = "File";
	public static final String NEW_KEY_ACTION = "Create New Key";
	public static final String LOAD_KEY_ACTION = "Load Key";
	public static final String EXIT_ACTION = "Exit";
	public static final String EDIT_MENU = "Edit";
	public static final String COPY_INPUT_ACTION = "Copy Input";
	public static final String COPY_OUTPUT_ACTION = "Copy Output";
	public static final String CLEAR_ALL_ACTION = "Clear All";
	public static final String TOOLS_MENU = "Tools";
	
	public static final String ERROR = "Error";
	public static final String LOAD_KEY_MESSAGE = "Please choose a key first!";
	
	public static final String OS_PROP_NAME = "os.name";
	public static final String MAC_OS_NAME = "mac";
	public static final String ALGO = "DESede";
	public static final String ENCRYPT_ACTION = "Encrypt";
	public static final String DECRYPT_ACTION = "Decrypt";
	
	public static final String HELP_MENU = "Help";
	public static final String INSTRUCTIONS = "Instructions";
	public static final String INSTRUCTIONS_MESSAGE = 	" 1. Create a new private key. If you already have a key skip this step. \n" +
													"       - Go to File menu and click Create New Key. \n" +
													"       - Choose a location and save. \n" +
												
													"       - This is your private encryption/decryption key. Keep it in a safe, hard to find place. \n" +
													"       - If you loose the key you will not be able to decrypt old data that was encrypted with it. \n" +
													"\n" +
													" 2. Load private key. \n" +
													"\n" +
													" 3. Enter the string you want to encrypt or decrypt into the Input box. \n" +
													"\n" +
													" 4. Press Encrypt or Decrypt \n";
	public static final String ABOUT = "About";
	public static final String ABOUT_MESSAGE = "This program uses symmetric key encryption system Triple DES for encryption and decryption of strings";

}
