/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkgSlangWord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author VMT
 */
public class CardLayout implements ItemListener, ActionListener {

    JPanel cards;
    JTextField searchSlang;
    JTextField searchDefineTextField;
    JTextField createSlangTextField;
    JTextField createDefineTextField;
    JTextField editSlangTextField;
    JTextField editDefineTextField;
    JTextField deleteSlangTextField;

    JPanel FuntionPanel;
    JPanel slangLayout;
    JPanel defineLayout;
    JPanel historyLayout;
    JPanel createLayout;
    JPanel editLayout;
    JPanel deleteLayout;
    JPanel backupLayout;
    JPanel randomLayout;
    JPanel quiz1Layout;
    JPanel quiz2Layout;

    JPanel slangResultLayout = new JPanel();
    JPanel blankPanel = new JPanel();
    JPanel randomDefineLayout = new JPanel();
    JPanel defineResultLayout = new JPanel();
    ArrayList<String> historySlangList;
    JPanel quiz1QuestionLayout;
    JPanel quiz1AnswerLayout;
    JPanel quiz2QuestionLayout;
    JPanel quiz2AnswerLayout;

    JLabel randomSlang = new JLabel();

    JRadioButton quiz1RadioButton1;
    JRadioButton quiz1RadioButton2;
    JRadioButton quiz1RadioButton3;
    JRadioButton quiz1RadioButton4;

    JRadioButton quiz2RadioButton1;
    JRadioButton quiz2RadioButton2;
    JRadioButton quiz2RadioButton3;
    JRadioButton quiz2RadioButton4;

    ButtonGroup quiz1GB1;
    ButtonGroup quiz2GB1;

    String quiz1AnswerCorrec;
    String quiz2AnswerCorrec;
    String quiz1Answer;
    String quiz2Answer;
    static SlangFunction slangFunction = new SlangFunction();

    static CardLayout mainPane;
    static HashMap<String, ArrayList<String>> slangWordList;

    final static String SEARCHBYSLANG = "1. Search by slang word";
    final static String SEARCHBYDEFINE = "2. Search by definition";
    final static String HISTORY = "3. Search history";
    final static String CREATESLANGWORD = "4. Add new slang word";
    final static String EDITSLANGWORD = "5. Edit slang word";
    final static String DELETESALNGWORD = "6. Delete slang word";
    final static String RESET = "7. Reset";
    final static String RANDOM = "8. Random slang word";
    final static String QUIZ1 = "9. Quiz 1 random slang word";
    final static String QUIZ2 = "10. Quiz 2 definition";
    final static String CHOSSEFUNTION = "Menu: ";

    public void addComponentToPane(Container pane) {
        historySlangList = new ArrayList<String>();
        blankPanel.setSize(100, 10);
        JPanel comboBoxPane = new JPanel();
        String comboBoxItems[] = {SEARCHBYSLANG, SEARCHBYDEFINE, HISTORY, CREATESLANGWORD, EDITSLANGWORD, DELETESALNGWORD, RESET, RANDOM, QUIZ1, QUIZ2};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        FuntionPanel = new JPanel();
        JLabel ChooseLabel = new JLabel(CHOSSEFUNTION);
        FuntionPanel.add(ChooseLabel);
        FuntionPanel.add(cb);

        //Create slang layout
        slangLayout = new JPanel();
        slangLayout.setLayout(new GridLayout(0, 1, 5, 5));

        JPanel slangTopLayout = new JPanel();
        searchSlang = new JTextField("Input text search", 20);
        JButton searchButton = new JButton();

        searchButton.setActionCommand("searchButton");
        searchButton.addActionListener(this);

        searchButton.setPreferredSize(new Dimension(30, 30));
        try {
            ImageIcon icon = new ImageIcon("search.png");
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
            searchButton.setIcon(new ImageIcon(newimg));
        } catch (Exception ex) {
            System.out.println("file not found");
        }

        slangTopLayout.add(searchSlang);
        slangTopLayout.add(searchButton);

        slangLayout.add(slangTopLayout, BorderLayout.NORTH);

        //create search by define
        defineLayout = new JPanel();
        defineLayout.setLayout(new GridLayout(0, 1, 5, 5));
        JPanel defineTopLayout = new JPanel();
        searchDefineTextField = new JTextField("Input text search", 20);
        JButton searchDefineButton = new JButton();

        searchDefineButton.setActionCommand("searchDefineButton");
        searchDefineButton.addActionListener(this);

        searchDefineButton.setPreferredSize(new Dimension(30, 30));
        try {
            ImageIcon icon = new ImageIcon("search.png");
            Image img = icon.getImage();
            Image newimg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
            searchDefineButton.setIcon(new ImageIcon(newimg));
        } catch (Exception ex) {
            System.out.println("file not found");
        }

        defineTopLayout.add(searchDefineTextField);
        defineTopLayout.add(searchDefineButton);

        defineLayout.add(defineTopLayout, BorderLayout.NORTH);

        //create history layout
        historyLayout = new JPanel();

        //create  createlayout
        createLayout();

        //create  editLayout
        editLayout();

        //create  deleteLayout
        deleteLayout();

        //create  backupLayout
        backupLayout();

        //create  backupLayout
        randomLayout();

        //create  backupLayout
        quiz1Layout();

        //create  backupLayout
        quiz2Layout();
        //add component to card layout
        cards = new JPanel(new java.awt.CardLayout());
        cards.add(slangLayout, SEARCHBYSLANG);
        cards.add(defineLayout, SEARCHBYDEFINE);
        cards.add(createLayout, CREATESLANGWORD);
        cards.add(historyLayout, HISTORY);
        cards.add(editLayout, EDITSLANGWORD);
        cards.add(deleteLayout, DELETESALNGWORD);
        cards.add(backupLayout, RESET);
        cards.add(randomLayout, RANDOM);
        cards.add(quiz1Layout, QUIZ1);
        cards.add(quiz2Layout, QUIZ2);

        pane.add(FuntionPanel, BorderLayout.NORTH);
        pane.add(cards, BorderLayout.CENTER);
    }

    public void itemStateChanged(ItemEvent evt) {
        slangSearchHistory();
        java.awt.CardLayout cl = (java.awt.CardLayout) (cards.getLayout());
        cl.show(cards, (String) evt.getItem());
    }

    public static void createAndShowGUI(HashMap<String, ArrayList<String>> SlangWordList) {
        slangWordList = SlangWordList;

        JFrame frame = new JFrame("Slang Word");
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        mainPane = new CardLayout();
        frame.setResizable(false);
        frame.setSize(550, 400);
        //frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                slangFunction.save(slangWordList);
                System.exit(0);

            }
        });
        
        mainPane.addComponentToPane(frame.getContentPane());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        if (action == "searchButton") {
            String keySlang = searchSlang.getText();
            System.out.println("Search definition by : " + searchSlang.getText());
            ArrayList<String> defineResult = slangWordList.get(searchSlang.getText());
            slangResultLayout.removeAll();
            
            if (defineResult != null) {
                try {
                    historySlangList.add(keySlang);
                    for (String define : defineResult) {
                        JLabel jText = new JLabel(define);
                        Border border = jText.getBorder();
                        Border margin = new EmptyBorder(10, 20, 10, 20);
                        jText.setBorder(new CompoundBorder(border, margin));
                        jText.setBackground(Color.lightGray);
                        jText.setOpaque(true);
                        slangResultLayout.add(jText);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                slangResultLayout.add(new JLabel("Not result..."));
            }
            
            slangLayout.add(slangResultLayout, BorderLayout.SOUTH);
            slangLayout.revalidate();
            slangLayout.repaint();
        } else if (action == "searchDefineButton") {
            String valueDefine = searchDefineTextField.getText();
            System.out.println("Search slang by: " + searchDefineTextField.getText());
            ArrayList<String> keyResult = new ArrayList<String>();
            defineResultLayout.removeAll();

            for (Map.Entry<String, ArrayList<String>> entry : slangWordList.entrySet()) {
                if (entry.getValue().contains(valueDefine)) {
                    keyResult.add(entry.getKey());
                    System.out.println("Result");
                    System.out.println(entry.getKey());
                }
            }

            if (keyResult != null) {
                try {
                    for (String define : keyResult) {
                        JLabel jText = new JLabel(define);
                        Border border = jText.getBorder();
                        Border margin = new EmptyBorder(10, 20, 10, 20);
                        jText.setBorder(new CompoundBorder(border, margin));
                        jText.setBackground(Color.lightGray);
                        jText.setOpaque(true);
                        defineResultLayout.add(jText);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                defineResultLayout.add(new JLabel("Not result.."));
                System.out.println("Not Result...");
            }

            defineLayout.add(defineResultLayout);
            defineLayout.revalidate();
            defineLayout.repaint();
        }
        if (action == "createButton") {
            System.out.println("Click create button");
            System.out.println(createSlangTextField.getText());
            addSlangWordFunction();
            System.out.println("add new slang comppleted");
        }
        if (action == "editButton") {
            eidtSlangFunction();
        }
        if (action == "deleteButton") {
            deleteSlang();
        }
        if (action == "resetButton") {
            backup();
        }
        if (action == "randomButton") {
            random();
        }
        if (action == "quiz1AButton") {
            quiz1();
        }
        if (action == "quiz2AButton") {
            quiz2();
        }
    }

    public void slangSearchHistory() 
    {
        historyLayout.removeAll();
        
        if (historySlangList == null) 
        {
            historyLayout.add(new JLabel("Not result.."));
            System.out.println("Not Result...");
        } else 
        {
            try 
            {
                for (String word : historySlangList) 
                {
                    JLabel jText = new JLabel(word);
                    
                    Border border = jText.getBorder();
                    Border margin = new EmptyBorder(10, 20, 10, 20);
                    
                    jText.setBorder(new CompoundBorder(border, margin));
                    jText.setBackground(Color.lightGray);
                    jText.setOpaque(true);
                    
                    historyLayout.add(jText);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        historyLayout.revalidate();
        historyLayout.repaint();
    }

    public void createLayout() 
    {
        createLayout = new JPanel();
        createLayout.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(10, 10, 10, 10);

        createSlangTextField = new JTextField("", 20);
        createDefineTextField = new JTextField("", 20);
        
        JLabel labelSlang = new JLabel("Enter slang: ");
        JLabel labelDefine = new JLabel("Enter define: ");
        JButton createButton = new JButton("Create");
        
        createButton.setActionCommand("createButton");
        createButton.addActionListener(this);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        createLayout.add(labelSlang, constraints);

        constraints.gridx = 1;
        createLayout.add(createSlangTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        createLayout.add(labelDefine, constraints);

        constraints.gridx = 1;
        createLayout.add(createDefineTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        createLayout.add(createButton, constraints);

        // set border for the panel
        createLayout.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Create a new slang word"));
    }

    public void addSlangWordFunction() {
        String slang = createSlangTextField.getText();
        String define = createDefineTextField.getText();
        if (slang != "" && define != "") 
        {
            System.out.println("add new slang");
            if (slangWordList.keySet().contains(slang))
            {
                String[] options = {"Duplicate", "Overwrite", "Cancel"};
                int result = JOptionPane.showOptionDialog(null, "This word already exists. Do you want to duplicate it?", "Message: " + "Add new slang word", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                
                if (result == JOptionPane.YES_OPTION) {
                    System.out.println("Duplicate word");
                    
                    for (Map.Entry<String, ArrayList<String>> entry : slangWordList.entrySet()) 
                    {
                        if (entry.getKey().contains(slang)) 
                        {
                            entry.getValue().add(define);
                            break;
                        }
                    }
                } else if (result == JOptionPane.NO_OPTION) 
                {
                    System.out.println("Overwrite word");
                    for (Map.Entry<String, ArrayList<String>> entry : slangWordList.entrySet()) 
                    {
                        if (entry.getKey().contains(slang)) 
                        {
                            entry.getValue().clear();
                            entry.getValue().add(define);
                            break;
                        }
                    }

                    Set<String> keySet = slangWordList.keySet();
                    for (String key : keySet) {
                        System.out.println(key + " " + slangWordList.get(key));
                    }
                }
            }
        }
    }

    public void editLayout() 
    {
        editLayout = new JPanel();
        editLayout.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(10, 10, 10, 10);

        editSlangTextField = new JTextField("", 20);
        editDefineTextField = new JTextField("", 20);

        JLabel editLabelSlang = new JLabel("Enter slang you want edit: ");
        JLabel editLabelDefine = new JLabel("Enter new define: ");
        JButton editButton = new JButton("Update");
        editButton.setActionCommand("editButton");
        editButton.addActionListener(this);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        editLayout.add(editLabelSlang, constraints);

        constraints.gridx = 1;
        editLayout.add(editSlangTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        editLayout.add(editLabelDefine, constraints);

        constraints.gridx = 1;
        editLayout.add(editDefineTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        editLayout.add(editButton, constraints);

        // set border for the panel
        editLayout.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Eidit slang word"));
    }

    public void eidtSlangFunction() 
    {
        System.out.println("Click edit button");
        System.out.println("Slang word edit " + editSlangTextField.getText());
        System.out.println("Define edit " + editDefineTextField.getText());
        String editslang = editSlangTextField.getText();
        String editdefine = editDefineTextField.getText();

        System.out.println("edit new slang");
        
        if (slangWordList.keySet().contains(editslang)) 
        {
            for (Map.Entry<String, ArrayList<String>> entry : slangWordList.entrySet()) 
            {
                if (entry.getKey().contains(editslang)) 
                {
                    entry.getValue().clear();
                    entry.getValue().add(editdefine);
                    break;
                }
            }
            
            JOptionPane.showConfirmDialog(null,
                    "Edit slang comppleted", "Be ok!", JOptionPane.DEFAULT_OPTION);
            System.out.println("edit  slang comppleted");
        } else 
        {
            JOptionPane.showConfirmDialog(null,
                    "Slang word doesn't exist", "Be ok!", JOptionPane.DEFAULT_OPTION);
        }
    }

    public void deleteLayout() 
    {
        deleteLayout = new JPanel();
        deleteLayout.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(10, 10, 10, 10);

        deleteSlangTextField = new JTextField("", 20);

        JLabel editLabelSlang = new JLabel("Enter slang you want delete: ");

        JButton deleteButton = new JButton("Delete");
        deleteButton.setActionCommand("deleteButton");
        deleteButton.addActionListener(this);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        deleteLayout.add(editLabelSlang, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        deleteLayout.add(deleteSlangTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        deleteLayout.add(deleteButton, constraints);

        // set border for the panel
        deleteLayout.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Delete slang word"));
    }

    public void deleteSlang() 
    {
        String deleteSlang = deleteSlangTextField.getText();
        System.out.println("delete  slang: " + deleteSlang);
        
        if (slangWordList.keySet().contains(deleteSlang.trim())) 
        {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure delete this word?", "Warning", JOptionPane.YES_NO_OPTION);
            
            if (dialogResult == JOptionPane.YES_OPTION) 
            {
                slangWordList.remove(deleteSlang);
            }
            System.out.println("edit  slang comppleted");
        } else 
        {
            JOptionPane.showConfirmDialog(null,
                    "Slang word doesn't exist", "Be ok!", JOptionPane.DEFAULT_OPTION);
        }
    }

    public void backupLayout() 
    {
        backupLayout = new JPanel();
        backupLayout.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel editLabelSlang = new JLabel("Click reset button if you want restore slang dictionary");

        JButton resetButton = new JButton("Reset");
        resetButton.setActionCommand("resetButton");
        resetButton.addActionListener(this);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        backupLayout.add(editLabelSlang, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        backupLayout.add(resetButton, constraints);

        // set border for the panel
        backupLayout.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Delete slang word"));
    }

    void backup() 
    {
        slangFunction.reset();
        slangWordList = slangFunction.getSlangWordList();
    }

    void randomLayout() 
    {
        randomLayout = new JPanel();
        randomLayout.setLayout(new GridBagLayout());

        JButton randomButton = new JButton("Click to receive random word");
        randomButton.setActionCommand("randomButton");
        randomButton.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel editLabelSlang = new JLabel("Click reset button if you want restore slang dictionary");
        // add components to the panel

        constraints.gridx = 0;
        constraints.gridy = 0;
        randomLayout.add(editLabelSlang, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        randomLayout.add(randomDefineLayout, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        randomLayout.add(randomButton, constraints);

        // set border for the panel
        randomLayout.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Random slang word"));

    }

    void random() {
        randomLayout.removeAll();
        
        Random rand = new Random();
        
        int int_random = rand.nextInt(slangWordList.size() - 1);
        Object[] news = slangWordList.keySet().toArray();
        randomSlang.setText(news[int_random].toString());

        for (Map.Entry<String, ArrayList<String>> entry : slangWordList.entrySet()) {

            if (entry.getKey().contains(news[int_random].toString())) 
            {
                System.out.println(entry.getKey());
                
                for (String definfe : entry.getValue()) 
                {
                    System.out.println(definfe);
                    
                    JLabel jText = new JLabel(definfe);
                    
                    Border border = jText.getBorder();
                    Border margin = new EmptyBorder(10, 20, 10, 20);
                    
                    jText.setBorder(new CompoundBorder(border, margin));
                    jText.setBackground(Color.lightGray);
                    jText.setOpaque(true);

                    randomDefineLayout.add(jText);
                }
                
                break;
            }
        }

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        randomLayout.add(randomSlang, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        randomDefineLayout.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Define"));
        randomLayout.add(randomDefineLayout, constraints);

        randomLayout.revalidate();
        randomLayout.repaint();
    }

    public void quiz1Layout() 
    {
        quiz1Layout = new JPanel();
        quiz1Layout.setLayout(new BorderLayout());
        quiz1QuestionLayout = new JPanel();
        quiz1AnswerLayout = new JPanel();

        quiz1RadioButton1 = new JRadioButton();
        quiz1RadioButton2 = new JRadioButton();
        quiz1RadioButton3 = new JRadioButton();
        quiz1RadioButton4 = new JRadioButton();

        quiz1GB1 = new ButtonGroup();

        quiz1AnswerLayout.setLayout(new GridBagLayout());

        //get random slang word
        Random rand = new Random();
        
        int int_random = rand.nextInt(slangWordList.size() - 1);
        
        Object[] news = slangWordList.keySet().toArray();
        String quizSring = "QUIZ, Choose define of the slang word \"" + news[int_random].toString() + "\":";

        ArrayList<String> answerList = new ArrayList<String>();

        boolean addFlag = false;
        for (Map.Entry<String, ArrayList<String>> entry : slangWordList.entrySet()) 
        {

            if (answerList.size() == 4) 
            {
                break;
            }
            if (entry.getKey().contains(news[int_random].toString())) 
            {
                System.out.println(entry.getKey());
                quiz1AnswerCorrec = entry.getValue().get(0);
                answerList.add(quiz1AnswerCorrec);
                addFlag = true;
            } else if (addFlag) 
            {
                if (!entry.getValue().isEmpty()) 
                {
                    answerList.add(entry.getValue().get(0));
                }
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        
        list = getRandomElement(list, 4);

        quiz1RadioButton1.setText(answerList.get(list.get(0)));
        quiz1RadioButton2.setText(answerList.get(list.get(1)));
        quiz1RadioButton3.setText(answerList.get(list.get(2)));
        quiz1RadioButton4.setText(answerList.get(list.get(3)));

        quiz1RadioButton1.setBounds(250, 30, 80, 50);
        quiz1RadioButton2.setBounds(250, 30, 80, 50);
        quiz1RadioButton3.setBounds(250, 30, 80, 50);
        quiz1RadioButton4.setBounds(250, 30, 80, 50);

        quiz1GB1.add(quiz1RadioButton1);
        quiz1GB1.add(quiz1RadioButton2);
        quiz1GB1.add(quiz1RadioButton3);
        quiz1GB1.add(quiz1RadioButton4);

        quiz1QuestionLayout.add(new JLabel(quizSring));

        JButton quiz1AButton = new JButton("Choose");
        quiz1AButton.setActionCommand("quiz1AButton");
        quiz1AButton.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        quiz1AnswerLayout.add(quiz1QuestionLayout, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        quiz1AnswerLayout.add(quiz1RadioButton1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        quiz1AnswerLayout.add(quiz1RadioButton2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        quiz1AnswerLayout.add(quiz1RadioButton3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        quiz1AnswerLayout.add(quiz1RadioButton4, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        quiz1AnswerLayout.add(quiz1AButton, constraints);

        System.out.println(getRandomElement(list, 4));
        quiz1Layout.add(quiz1QuestionLayout, BorderLayout.NORTH);
        quiz1Layout.add(quiz1AnswerLayout, BorderLayout.CENTER);
    }

    public void quiz1() 
    {
        if (quiz1RadioButton1.isSelected()) 
        {
            quiz1Answer = quiz1RadioButton1.getText();
        } else if (quiz1RadioButton2.isSelected()) 
        {
            quiz1Answer = quiz1RadioButton2.getText();
        } else if (quiz1RadioButton3.isSelected()) 
        {
            quiz1Answer = quiz1RadioButton3.getText();
        } else if (quiz1RadioButton4.isSelected()) 
        {
            quiz1Answer = quiz1RadioButton4.getText();
        } else 
        {
            JOptionPane.showMessageDialog(null, "Please, choose answer", "Message", JOptionPane.PLAIN_MESSAGE);
        }

        if (quiz1Answer == quiz1AnswerCorrec) 
        {
            JOptionPane.showMessageDialog(null, "Correct", "Result", JOptionPane.PLAIN_MESSAGE);
        } else 
        {
            JOptionPane.showMessageDialog(null, "Wrong", "Result", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public ArrayList<Integer> getRandomElement(ArrayList<Integer> list, int totalItems) 
    {
        Random rand = new Random();

        // create a temporary list for storing
        // selected element
        ArrayList<Integer> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {

            // take a raundom index between 0 to size
            // of given List
            int randomIndex = rand.nextInt(list.size());

            // add element in temporary list
            newList.add(list.get(randomIndex));

            // Remove selected element from orginal list
            list.remove(randomIndex);
        }
        return newList;
    }

    public void quiz2Layout() 
    {
        quiz2Layout = new JPanel();
        quiz2QuestionLayout = new JPanel();
        quiz2Layout.setLayout(new BorderLayout());
        
        //quiz1QuestionLayout.setBackground(Color.RED);       
        quiz2AnswerLayout = new JPanel();

        quiz2RadioButton1 = new JRadioButton();
        quiz2RadioButton2 = new JRadioButton();
        quiz2RadioButton3 = new JRadioButton();
        quiz2RadioButton4 = new JRadioButton();

        quiz2GB1 = new ButtonGroup();

        quiz2AnswerLayout.setLayout(new GridBagLayout());

        //get random slang word
        Random rand = new Random();
        int int_random = rand.nextInt(slangWordList.size() - 1);
        Object[] news = slangWordList.keySet().toArray();
        quiz2AnswerCorrec = news[int_random].toString();
        String define = "";

        ArrayList<String> answerList = new ArrayList<String>();
        answerList.add(quiz2AnswerCorrec);
        
        boolean addFlag = false;
        for (Map.Entry<String, ArrayList<String>> entry : slangWordList.entrySet()) {

            if (answerList.size() == 4) {
                break;
            }
            if (entry.getKey().contains(quiz2AnswerCorrec)) 
            {
                System.out.println(entry.getKey());
                if (!entry.getValue().isEmpty()) {
                    define = entry.getValue().get(0);
                }
                addFlag = true;
            } else if (addFlag && !entry.getKey().contains(quiz2AnswerCorrec)) 
            {
                if (!entry.getValue().isEmpty()) {
                    answerList.add(entry.getKey());
                }
            }
        }
        String quizSring = "QUIZ, Choose salng word of the define word \"" + define + "\":";
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list = getRandomElement(list, 4);

        quiz2RadioButton1.setText(answerList.get(list.get(0)));
        quiz2RadioButton2.setText(answerList.get(list.get(1)));
        quiz2RadioButton3.setText(answerList.get(list.get(2)));
        quiz2RadioButton4.setText(answerList.get(list.get(3)));

        quiz2RadioButton1.setBounds(250, 30, 80, 50);
        quiz2RadioButton2.setBounds(250, 30, 80, 50);
        quiz2RadioButton3.setBounds(250, 30, 80, 50);
        quiz2RadioButton4.setBounds(250, 30, 80, 50);

        quiz2GB1.add(quiz2RadioButton1);
        quiz2GB1.add(quiz2RadioButton2);
        quiz2GB1.add(quiz2RadioButton3);
        quiz2GB1.add(quiz2RadioButton4);

        quiz2QuestionLayout.add(new JLabel(quizSring));

        JButton quiz1AButton = new JButton("Choose");
        quiz1AButton.setActionCommand("quiz2AButton");
        quiz1AButton.addActionListener(this);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        quiz2AnswerLayout.add(quiz2QuestionLayout, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        quiz2AnswerLayout.add(quiz2RadioButton1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        quiz2AnswerLayout.add(quiz2RadioButton2, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        quiz2AnswerLayout.add(quiz2RadioButton3, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        quiz2AnswerLayout.add(quiz2RadioButton4, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        quiz2AnswerLayout.add(quiz1AButton, constraints);

        System.out.println(getRandomElement(list, 4));
        quiz2Layout.add(quiz2QuestionLayout, BorderLayout.NORTH);
        quiz2Layout.add(quiz2AnswerLayout, BorderLayout.CENTER);
    }

    public void quiz2() {
        if (quiz2RadioButton1.isSelected()) 
        {
            quiz2Answer = quiz2RadioButton1.getText();
        } else if (quiz2RadioButton2.isSelected()) 
        {
            quiz2Answer = quiz2RadioButton2.getText();
        } else if (quiz2RadioButton3.isSelected()) 
        {
            quiz2Answer = quiz2RadioButton3.getText();
        } else if (quiz2RadioButton4.isSelected()) 
        {
            quiz2Answer = quiz2RadioButton4.getText();
        }

        if (quiz2Answer == quiz2AnswerCorrec) {
            JOptionPane.showMessageDialog(null, "Correct", "Result", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Wrong", "Result", JOptionPane.PLAIN_MESSAGE);
        }
    }
}