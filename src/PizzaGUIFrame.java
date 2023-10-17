import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PizzaGUIFrame extends JFrame
{
    Toolkit kit = Toolkit.getDefaultToolkit();

    //Panels
    JPanel mainPanel;
    JPanel topPanel;
    JPanel radioButtonsPanel;
    JPanel checkBoxPanel;
    JPanel comboBoxPanel;
    JPanel midPanel;
    JPanel orderPanel;

    //Title Label
    JLabel titleLabel;

    //JButtons
    JButton quit;
    JButton order;
    JButton clear;

    JTextArea orderDisplay;
    JScrollPane scroller;

    JComboBox pizzaSize;
    JRadioButton thin;
    JRadioButton regular;
    JRadioButton deepDish;
    ButtonGroup crustTypeGroup;

    JCheckBox pepperoni;
    JCheckBox olives;
    JCheckBox onions;
    JCheckBox sausage;
    JCheckBox bacon;
    JCheckBox mushrooms;





    public PizzaGUIFrame()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,1));

        createTopPanel();
        mainPanel.add(topPanel);

        createMidPanel();
        mainPanel.add(midPanel);

        createOrderPanel();
        mainPanel.add(orderPanel);

        add(mainPanel);

        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        // center frame in screen
        setSize(screenWidth * 3/4 , screenHeight * 3/4);
        setLocation(screenWidth / 8, screenHeight / 8);
        // set frame icon and title
        setTitle("Pizza Order");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void createTopPanel()
    {
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1,3));
        createRadioButtonsPanel();
        createPizzaSizeBox();
        createCheckBoxPanel();


        topPanel.add(comboBoxPanel);
        topPanel.add(radioButtonsPanel);
        topPanel.add(checkBoxPanel);

    }
    private void createPizzaSizeBox()
    {
        comboBoxPanel = new JPanel();
        comboBoxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Choose pizza Size"));

        pizzaSize = new JComboBox<>();
        pizzaSize.addItem("Small");
        pizzaSize.addItem("Medium");
        pizzaSize.addItem("Large");
        pizzaSize.addItem("Super");

        comboBoxPanel.add(pizzaSize);

    }
    private void createRadioButtonsPanel()
    {
        radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(new GridLayout(1,3));
        radioButtonsPanel.setBorder(new TitledBorder(new EtchedBorder(), "Choose pizza crust"));

        thin = new JRadioButton("Thin");
        regular = new JRadioButton("Regular");
        deepDish = new JRadioButton("Deep-Dish");

        radioButtonsPanel.add(thin);
        radioButtonsPanel.add(regular);
        radioButtonsPanel.add(deepDish);
        regular.setSelected(true);

        crustTypeGroup = new ButtonGroup();

        crustTypeGroup.add(thin);
        crustTypeGroup.add(regular);
        crustTypeGroup.add(deepDish);


    }
    private void createCheckBoxPanel()
    {
        checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(2,3));
        checkBoxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Choose your pizza ingredients"));

        pepperoni = new JCheckBox("Pepperoni");
        mushrooms = new JCheckBox("Mushrooms");
        onions = new JCheckBox("Onions");
        sausage = new JCheckBox("Sausage");
        olives = new JCheckBox("Olives");
        bacon = new JCheckBox("Bacon");

        checkBoxPanel.add(pepperoni);
        checkBoxPanel.add(mushrooms);
        checkBoxPanel.add(onions);
        checkBoxPanel.add(sausage);
        checkBoxPanel.add(olives);
        checkBoxPanel.add(bacon);
    }
    private void createMidPanel()
    {
        midPanel = new JPanel();
        orderDisplay = new JTextArea(10,100);
        orderDisplay.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        orderDisplay.setEditable(false);
        scroller = new JScrollPane(orderDisplay);
        midPanel.add(scroller);

    }
    private void createOrderPanel()
    {
        orderPanel = new JPanel();
        orderPanel.setLayout(new GridLayout(2,6));
        orderPanel.setBorder(new TitledBorder(new EtchedBorder(), "Controls"));

        quit = new JButton("Quit");
        quit.addActionListener((ActionEvent ae) ->
        {
            int input = JOptionPane.showConfirmDialog(null, "Do you want to quit?", "Select an Option...",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if(input == 0)
            {
                System.exit(0);
            }


        });

        order = new JButton("Order");
        order.addActionListener((ActionEvent ae) ->
        {
            String crust = "";
            String ingredients = "";
            String pizzaSizeString;
            String res = "";
            double pizzaSizePrice = 0;
            double ingredientsPrice = 0;
            double subtotal;
            double taxedPrice;
            double total;


            pizzaSizeString = (String) pizzaSize.getSelectedItem();
            if(pizzaSizeString.equals("Small"))
            {
                pizzaSizePrice = 8.00;
            }
            else if(pizzaSizeString.equals("Medium"))
            {
                pizzaSizePrice = 12.00;
            }
            else if(pizzaSizeString.equals("Large"))
            {
                pizzaSizePrice = 16.00;
            }
            else if(pizzaSizeString.equals("Super"))
            {
                pizzaSizePrice = 20.00;
            }

            if(thin.isSelected())
            {
                crust = "Thin";
            }
            else if (regular.isSelected())
            {
                crust = "Regular";
            }
            else if (deepDish.isSelected())
            {
                crust = "Deep-Dish";
            }
            if (pepperoni.isSelected())
            {
                ingredients += "Pepperoni ";
                ingredientsPrice++;
            }

            if (mushrooms.isSelected())
            {
                ingredients += "Mushrooms ";
                ingredientsPrice++;
            }

            if (olives.isSelected())
            {
                ingredients += "Olives ";
                ingredientsPrice++;
            }
            if (bacon.isSelected())
            {
                ingredients += "Bacon ";
                ingredientsPrice++;
            }

            if (onions.isSelected())
            {
                ingredients += "Onions ";
                ingredientsPrice++;
            }

            if (sausage.isSelected())
            {
                ingredients += "Sausage ";
                ingredientsPrice++;
            }
            subtotal = pizzaSizePrice + ingredientsPrice;
            taxedPrice = subtotal * 0.07;
            total = taxedPrice + subtotal;

            res += "===============================================================\n";
            res += String.format("%-80s %-5s \n",pizzaSizeString + " " + crust ,Double.toString(pizzaSizePrice));
            res += String.format("%-80s %-5s \n",ingredients ,Double.toString(ingredientsPrice));
            res += String.format("%-80s %-5s \n","Sub-Total: ",Double.toString(subtotal));
            res += String.format("%-80s %-5s \n","Tax: ",Double.toString(taxedPrice));
            res += "--------------------------------------------------------------------------------\n";
            res += String.format("%-80s %-5s \n","Total: ",Double.toString(total));

            orderDisplay.append(res);
        });
        clear = new JButton("Clear");
        clear.addActionListener((ActionEvent ae) ->
        {
            regular.setSelected(true);
            pepperoni.setSelected(false);
            mushrooms.setSelected(false);
            onions.setSelected(false);
            olives.setSelected(false);
            bacon.setSelected(false);
            sausage.setSelected(false);
            orderDisplay.setText(" ");
        });

        orderPanel.add(order);
        orderPanel.add(clear);
        orderPanel.add(quit);
    }
}
