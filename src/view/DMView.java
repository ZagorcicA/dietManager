package view;

/**
 * This class represents the View. It is focused on generating a user interface
 * and updating its display so as to always display the requested and correct
 * information to the user. The class extends the JFrame class that adds support
 * for the JFC/Swing component architecture.
 *
 * The UI includes the calendar display, which allows the user to select a
 * specific date they wish to log to, food selection and quantity consumed on
 * that date, weight and calorie limit input. The log text area displays the
 * selected values. Below the text area are text fields which allow the user too
 * add another instance of basic food t be store in the application for later
 * use. The set calorie limit and the latest logged weight are displayed t the
 * user upon the application being started, and if no value has been given yet,
 * the UI displays the default values, which are '68' for weight and '2000' for
 * calorie limit.
 *
 * Finally, the UI displays the calories consumed on a particular date based on
 * the food and its quantity that was logged for that particular date.
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

import org.jdatepicker.impl.*;

import model.*;
import java.util.*;

import java.awt.event.*;

/**
 * This class deals only with GUI rendering - Presentation. Code that deals with
 * events triggered on user interaction is handled by the controller.
 *
 */
public class DMView extends JFrame {

    // UI components
    private UtilDateModel model = new UtilDateModel();
    private Properties p = new Properties();
    // container for panels
    private Border br = BorderFactory.createLineBorder(Color.black);
    // private Container c = getContentPane();

    private Container contentPane = this.getContentPane();
    private Box hBox = Box.createHorizontalBox();

    // Selection of food, log info, weight, calorie limit, calorie intake
    // over/under limit
    // Left Side of the GUI
    private JComboBox<String> selectFood = new JComboBox<>();
    private JButton jbNewFood = new JButton("New Food");
    private JDatePickerImpl datePicker;
    private JTextField jtfQuantity = new JTextField(5);
    private JTextField jtfCaloriesLimit = new JTextField(5);
    private JTextField jtfWeight = new JTextField(5);
    private TextArea taLogFood = new TextArea(5, 5);
    private JButton jbLog = new JButton("Log");

    // Right side of the GUI
    private JTextField jtfCaloriesCount = new JTextField(5);
    private JTextField jtfIfOverLimit = new JTextField(5);
    private JTextField jtfFatCount = new JTextField(5);
    private JTextField jtfCarbohydratesCount = new JTextField(5);
    private JTextField jtfProteinsCount = new JTextField(5);
    private JLabel jlFatPercentage = new JLabel("0");
    private JLabel jlCarbohydratesPercentage = new JLabel("0");
    private JLabel jlProteinsPercentage = new JLabel("0");

    // New Food Entry Frame
    private JFrame newFoodFrame = new JFrame();

    private Container newFoodFrameContentPane = newFoodFrame.getContentPane();
    private Box newFoodFrameHBox = Box.createHorizontalBox();

    // New Food Entry
    private JTextField jtfName = new JTextField(5);
    private JTextField jtfCalorie = new JTextField(5);
    private JTextField jtfCarbs = new JTextField(5);
    private JTextField jtfProteins = new JTextField(5);
    private JTextField jtfFat = new JTextField(5);
    private JButton jbSaveNewFood = new JButton("Save Food");

    // New Recipe Entry
    private JTextField jtfRecipeName = new JTextField(5);
    private JComboBox<String> selectIngredient = new JComboBox<>();
    private JTextField jtfIngredientCount = new JTextField(5);
    private TextArea taIngredients = new TextArea(5, 5);
    private JButton jbAddIngredient = new JButton("Add ingredient");
    private JButton jbSaveRecipe = new JButton("Save Recipe");

    // view constructor
    public DMView() {

        // initialize components
        // create the interface
        this.p.put("text.today", "Today");
        this.p.put("text.month", "Month");
        this.p.put("text.year", "Year");

        // adding the display and input fields
        // setting them to be editable so as to receive the user's input
        this.taLogFood.setEditable(false);
        this.taIngredients.setEditable(false);
        this.jbLog.setEnabled(false);
        this.jbAddIngredient.setEnabled(false);
        this.jbSaveRecipe.setEnabled(false);
        this.jbSaveNewFood.setEnabled(false);

        // instantiating a JDatePanel and a JDatePicker for the calendar
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        this.datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // initialize the JPanel
        JPanel jpContent = new JPanel();
        jpContent.setBorder(br);

        // set the layout for the components
        jpContent.setLayout(new BoxLayout(jpContent, BoxLayout.Y_AXIS));
        jpContent.setBorder(new EmptyBorder(10, 10, 10, 10));

        // add components to the created panel's specified layout
        jpContent.add(new JLabel("Select food"));
        jpContent.add(Box.createVerticalStrut(20));
        jpContent.add(this.selectFood);
        jpContent.add(Box.createVerticalStrut(5));
        jpContent.add(this.jbNewFood);
        jpContent.add(Box.createVerticalStrut(5));
        jpContent.add(new JLabel("Select date"));
        jpContent.add(this.datePicker);
        jpContent.add(Box.createVerticalStrut(5));
        jpContent.add(new JLabel("Quantity:"));
        jpContent.add(this.jtfQuantity);
        jpContent.add(Box.createVerticalStrut(5));
        jpContent.add(new JLabel("Calories Limit:"));
        jpContent.add(this.jtfCaloriesLimit);
        jpContent.add(Box.createVerticalStrut(5));
        jpContent.add(new JLabel("Weight:"));
        jpContent.add(this.jtfWeight);
        jpContent.add(Box.createVerticalStrut(5));
        jpContent.add(new JLabel("Log:"));

        // adding the text area for display
        jpContent.add(this.taLogFood);

        // adding the components to enable requests to log and save the input
        jpContent.add(this.jbLog);

        // initialize the JPanel
        JPanel jpLogStats = new JPanel();
        // jpLogStats.setBounds(10, 10, 300, 600);
        jpLogStats.setBorder(br);

        jpLogStats.setLayout(new BoxLayout(jpLogStats, BoxLayout.Y_AXIS));
        jpLogStats.setBorder(new EmptyBorder(10, 10, 10, 10));

        jpLogStats.add(new JLabel("Log statistics"));
        jpLogStats.add(Box.createVerticalStrut(20));
        jpLogStats.add(new JLabel("Daily Fat Intake"));
        jpLogStats.add(this.jtfFatCount);
        jpLogStats.add(this.jlFatPercentage);
        jpLogStats.add(Box.createVerticalStrut(15));
        jpLogStats.add(new JLabel("Daily Carbohydrate Intake"));
        jpLogStats.add(this.jtfCarbohydratesCount);
        jpLogStats.add(this.jlCarbohydratesPercentage);
        jpLogStats.add(Box.createVerticalStrut(15));
        jpLogStats.add(new JLabel("Daily Protein Intake"));
        jpLogStats.add(this.jtfProteinsCount);
        jpLogStats.add(this.jlProteinsPercentage);
        jpLogStats.add(Box.createVerticalStrut(15));
        jpLogStats.add(new JLabel("Daily Calorie Intake"));
        jpLogStats.add(this.jtfCaloriesCount);
        jpLogStats.add(new JLabel("Over/Under calories limit"));
        jpLogStats.add(this.jtfIfOverLimit);

        this.hBox.add(jpContent);
        this.hBox.add(jpLogStats);
        this.hBox.setPreferredSize(new Dimension(854, 480));
        this.contentPane.add(this.hBox, BorderLayout.NORTH);

        this.jtfCaloriesCount.setEditable(false);
        this.jtfIfOverLimit.setEditable(false);
        this.jtfFatCount.setEditable(false);
        this.jtfCarbohydratesCount.setEditable(false);
        this.jtfProteinsCount.setEditable(false);

        // initialize JPanel for food addition
        JPanel jpAddFood = new JPanel();
        jpAddFood.setBorder(br);

        // initialize JPanel for Recipe addition
        JPanel jpAddRecipe = new JPanel();
        jpAddRecipe.setBorder(br);

        jpAddFood.setLayout(new BoxLayout(jpAddFood, BoxLayout.Y_AXIS));
        jpAddFood.setBorder(new EmptyBorder(10, 10, 10, 10));

        jpAddRecipe.setLayout(new BoxLayout(jpAddRecipe, BoxLayout.Y_AXIS));
        jpAddRecipe.setBorder(new EmptyBorder(10, 10, 10, 10));

        // adding components to enter a new basic food entry
        // name oof the item + all data related to it that is necessary for further
        // calculations
        jpAddRecipe.add(new JLabel("New Recipe Entry:"));
        jpAddRecipe.add(Box.createVerticalStrut(20));
        jpAddRecipe.add(new JLabel("Recipe Name"));
        jpAddRecipe.add(this.jtfRecipeName);
        jpAddRecipe.add(Box.createVerticalStrut(5));
        jpAddRecipe.add(new JLabel("Select Ingredient"));
        jpAddRecipe.add(this.selectIngredient);
        jpAddRecipe.add(Box.createVerticalStrut(5));
        jpAddRecipe.add(new JLabel("Select Ingredient Count"));
        jpAddRecipe.add(this.jtfIngredientCount);
        jpAddRecipe.add(Box.createVerticalStrut(5));
        jpAddRecipe.add(new JLabel("All ingredients"));
        jpAddRecipe.add(this.taIngredients);
        jpAddRecipe.add(Box.createVerticalStrut(5));
        jpAddRecipe.add(this.jbAddIngredient);
        jpAddRecipe.add(Box.createVerticalStrut(5));
        jpAddRecipe.add(this.jbSaveRecipe);

        // adding components to enter a new basic food entry
        // name oof the item + all data related to it that is necessary for further
        // calculations
        jpAddFood.add(new JLabel("New Food Entry:"));
        jpAddFood.add(Box.createVerticalStrut(20));
        jpAddFood.add(new JLabel("Food Name"));
        jpAddFood.add(this.jtfName);
        jpAddFood.add(Box.createVerticalStrut(5));
        jpAddFood.add(new JLabel("Calories"));
        jpAddFood.add(this.jtfCalorie);
        jpAddFood.add(Box.createVerticalStrut(5));
        jpAddFood.add(new JLabel("Carbs"));
        jpAddFood.add(this.jtfCarbs);
        jpAddFood.add(Box.createVerticalStrut(5));
        jpAddFood.add(new JLabel("Proteins"));
        jpAddFood.add(this.jtfProteins);
        jpAddFood.add(Box.createVerticalStrut(5));
        jpAddFood.add(new JLabel("Fat"));
        jpAddFood.add(this.jtfFat);
        jpAddFood.add(Box.createVerticalStrut(5));

        // adding the component to enable requests to save the input
        jpAddFood.add(this.jbSaveNewFood);

        this.newFoodFrameHBox.add(jpAddFood);
        this.newFoodFrameHBox.add(jpAddRecipe);
        this.newFoodFrameContentPane.add(this.newFoodFrameHBox, BorderLayout.NORTH);

        // set the title of teh interface
        this.setTitle("Diet Manager");

        // The window closing event should probably be passed to the
        // Controller in a real program, but this is a short example.
        // this.setPreferredSize(new Dimension(854, 480));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // Set the background and foreground colors for the UI components
        Color bg = new Color(232, 235, 239); // blue-grey background
        Color fg = new Color(45, 52, 54); // blue-grey foreground
        Color white = new Color(255, 255, 255); // white foreground

        // Set the font for the UI components
        Font font = new Font("Arial", Font.PLAIN, 14);

        // Set the background and foreground colors for the labels and text fields
        java.util.List<Component> components = this.getAllComponents(this);

        for (Component component : components) {
            if (component instanceof Label) {
                component.setForeground(fg);
            } else if (component instanceof TextField) {
                component.setForeground(fg);
                component.setBackground(white);
            }

            component.setFont(font);
        }

        // Set the background and foreground colors for the panels
        jpContent.setBackground(bg);
        jpAddFood.setBackground(bg);
        jpAddRecipe.setBackground(bg);
        jpLogStats.setBackground(bg);

        jpContent.setForeground(fg);
        jpAddFood.setForeground(fg);
        jpAddRecipe.setForeground(fg);
        jpLogStats.setForeground(fg);

        // Set the font for the panels
        jpContent.setFont(font);
        jpAddFood.setFont(font);
        jpAddRecipe.setFont(font);
        jpLogStats.setFont(font);

        // Set the background and foreground colors for the text areas
        taLogFood.setForeground(fg);
        taLogFood.setBackground(white);
        taLogFood.setFont(font);

        taIngredients.setForeground(fg);
        taIngredients.setBackground(white);
        taIngredients.setFont(font);

        // Set the background and foreground colors for the buttons
        JButton[] buttons = {jbNewFood, jbLog, jbSaveNewFood, jbAddIngredient, jbSaveRecipe,
            jbNewFood};
        for (JButton button : buttons) {
            button.setForeground(white);
            button.setBackground(fg);
            button.setFont(font);
        }
    }

    public java.util.List<Component> getAllComponents(final Container c) {
        Component[] comps = c.getComponents();
        java.util.List<Component> compList = new ArrayList<>();
        for (Component comp : comps) {
            compList.add(comp);
            if (comp instanceof Container) {
                compList.addAll(this.getAllComponents((Container) comp));
            }
        }
        return compList;
    }

    public String getDate() {
        // TOOD: DatePicker bug where it returns back a different date than the one
        return datePicker.getModel().getYear() + ","
                + String.format("%02d", datePicker.getModel().getMonth()) + ","
                + String.format("%02d", datePicker.getModel().getDay());
    }

    public int checkDate(int date) {
        if (date != 12) {
            return date + 1;
        } else {
            return 1;
        }
    }

    public void addDateListener(ActionListener dal) {
        this.datePicker.addActionListener(dal);
    }

    public void addLogListener(ActionListener cal) {
        this.jbLog.addActionListener(cal);
    }
    public void addNewFoodListener(ActionListener cal){
        this.jbNewFood.addActionListener(cal);
    }
    public void addSaveNewFoodListener(ActionListener cal) {
        this.jbSaveNewFood.addActionListener(cal);
    }

    public void addIngredientListener(ActionListener cal) {
        this.jbAddIngredient.addActionListener(cal);
    }

    public void addQuantityIngredientListener(KeyListener kl) {
        this.jtfIngredientCount.addKeyListener(kl);
    }

    public void addRecipeListener(ActionListener cal) {
        this.jbSaveRecipe.addActionListener(cal);
    }

    public void addRecipeNameListener(KeyListener kl) {
        this.jtfRecipeName.addKeyListener(kl);
    }

    public void addQuantityListener(KeyListener kl) {
        this.jtfQuantity.addKeyListener(kl);
    }

    public void addNewFoodEntryListener(KeyListener kl) {
        this.jtfName.addKeyListener(kl);
        this.jtfCalorie.addKeyListener(kl);
        this.jtfCarbs.addKeyListener(kl);
        this.jtfProteins.addKeyListener(kl);
        this.jtfFat.addKeyListener(kl);
    }

    public void updateComboBoxes(ArrayList<String> foodNameList) {
        this.selectFood.removeAllItems();
        this.selectIngredient.removeAllItems();
        for (String foodName : foodNameList) {
            selectFood.addItem(foodName);
            selectIngredient.addItem(foodName);
        }
    }

    public void updateComboBoxesWithFood(ArrayList<Food> foodList) {
        this.selectFood.removeAllItems();
        this.selectIngredient.removeAllItems();
        for (Food food : foodList) {
            selectFood.addItem(food.getName());
            selectIngredient.addItem(food.getName());
        }
    }

    public void setDatePickerDate(String date) {
        String[] dateInfo = date.split(",");
        int year = Integer.parseInt(dateInfo[0]);
        int month = Integer.parseInt(dateInfo[1]);
        int day = Integer.parseInt(dateInfo[2]);
        this.datePicker.getModel().setDate(year, month, day);
        this.datePicker.getJFormattedTextField().setText(date + "");
    }

    public void setDatePickerDate(int year, int month, int day) {
        this.datePicker.getModel().setDate(year, month, day);
        String monthString = String.format(Locale.US, "%02d", month);
        String dayString = String.format(Locale.US, "%02d", day);
        this.datePicker.getJFormattedTextField().setText(year + "-" + monthString + "-" + dayString);
    }

    public void updateLogTextFields(double weight, double calorieLimit, double calorieIntake, double fatIntake,
            double carbohydrateIntake, double proteinIntake) {
        this.updateWeightTextField(weight);
        this.updateCalorieLimitTextField(calorieLimit);
        this.updateCalorieIntakeTextField(calorieIntake);

        this.updateProteinIntakeTextField(proteinIntake);
        this.updateFatIntakeTextField(fatIntake);
        this.updateCarbsIntakeTextField(carbohydrateIntake);
        this.updatePercentage();
    }

    public void updatePercentage() {
        double proteinIntake = Double.parseDouble(this.jtfProteinsCount.getText());
        double fatIntake = Double.parseDouble(this.jtfFatCount.getText());
        double carbohydrateIntake = Double.parseDouble(this.jtfCarbohydratesCount.getText());
        double allIntake = proteinIntake + fatIntake + carbohydrateIntake;
        double proteinPercentage = (proteinIntake / allIntake) * 100;
        double fatPercentage = (fatIntake / allIntake) * 100;
        double carbohydratePercentage = (carbohydrateIntake / allIntake) * 100;
        if (allIntake > 0) {
            jlProteinsPercentage.setText(String.format(Locale.US, "%.2f", proteinPercentage) + "%");
            jlFatPercentage.setText(String.format(Locale.US, "%.2f", fatPercentage) + "%");
            jlCarbohydratesPercentage.setText(String.format(Locale.US, "%.2f", carbohydratePercentage) + "%");
        } else {
            jlProteinsPercentage.setText(0 + "%");
            jlFatPercentage.setText(0 + "%");
            jlCarbohydratesPercentage.setText(0 + "%");
        }
    }

    public void updateCarbsIntakeTextField(double carbohydrateIntake) {
        this.jtfCarbohydratesCount.setText("" + String.format(Locale.US, "%.2f", carbohydrateIntake));
    }

    public void updateFatIntakeTextField(double fatIntake) {
        this.jtfFatCount.setText("" + String.format(Locale.US, "%.2f", fatIntake));
    }

    public void updateProteinIntakeTextField(double proteinIntake) {
        this.jtfProteinsCount.setText("" + String.format(Locale.US, "%.2f", proteinIntake));
    }

    public void updateWeightTextField(double weight) {
        this.jtfWeight.setText(String.format(Locale.US, "%.1f", weight));
    }

    public void updateCalorieLimitTextField(double calorieLimit) {
        this.jtfCaloriesLimit.setText(String.format(Locale.US, "%.1f", calorieLimit));
    }

    public void updateCalorieIntakeTextField(double calorieIntake) {
        this.jtfCaloriesCount.setText(String.format(Locale.US, "%.1f", calorieIntake));
        this.overUnderCalorieQuota(calorieIntake);
    }

    private void overUnderCalorieQuota(double calories) {
        double limit = Double.parseDouble(jtfCaloriesLimit.getText());
        if (calories > limit) {
            this.jtfIfOverLimit.setText("OVER LIMIT");
        } else {
            this.jtfIfOverLimit.setText("UNDER LIMIT");
        }
    }

    public void updateLogFood(ArrayList<String> foodList) {
        this.taLogFood.setText("");
        for (String foodNameQuantity : foodList) {
            this.taLogFood.append(foodNameQuantity + "\n");
        }
    }

    public void addLogFood(String foodName) {
        this.taLogFood.append(foodName + "\n");
    }

    public void addRecipeFood(String foodName) {
        this.taIngredients.append(foodName + "\n");
    }

    public String getFoodQuantityText() {
        return jtfQuantity.getText();
    }

    public String getIngredientQuantityText() {
        return jtfIngredientCount.getText();
    }

    public void enableLogButton() {
        this.jbLog.setEnabled(true);
    }

    public void disableLogButton() {
        this.jbLog.setEnabled(false);
    }

    public void enableAddIngredientButton() {
        this.jbAddIngredient.setEnabled(true);
    }

    public void disableAddIngredientButton() {
        this.jbAddIngredient.setEnabled(false);
    }

    public void showError(String content) {
        JOptionPane.showMessageDialog(this,
                content,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String content) {
        JOptionPane.showMessageDialog(this,
                content,
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public String getFoodComboBoxSelectedItem() {
        return this.selectFood.getSelectedItem().toString();
    }

    public String getIngredientComboBoxSelectedItem() {
        return this.selectIngredient.getSelectedItem().toString();
    }

    public String getNewFoodFoodNameText() {
        return this.jtfName.getText();
    }

    public String getNewFoodCalorieText() {
        return this.jtfCalorie.getText();
    }

    public String getNewFoodCarbsText() {
        return this.jtfCarbs.getText();
    }

    public String getNewFoodProteinsText() {
        return this.jtfProteins.getText();
    }

    public String getNewFoodFatText() {
        return this.jtfFat.getText();
    }

    public String getRecipeNameText() {
        return this.jtfRecipeName.getText();
    }

    public void enableNewFoodButton() {
        this.jbSaveNewFood.setEnabled(true);
    }

    public void disableNewFoodButton() {
        this.jbSaveNewFood.setEnabled(false);
    }

    public void enableNewRecipeButton() {
        this.jbSaveRecipe.setEnabled(true);
    }

    public void disableNewRecipeButton() {
        this.jbSaveRecipe.setEnabled(false);
    }

    public String getIngredientTextAreaText() {
        return this.taIngredients.getText();
    }

    public void updateCaloriesCount(double value) {
        double oldCount = Double.parseDouble(this.jtfCaloriesCount.getText());
        this.jtfCaloriesCount.setText("" + (value + oldCount));
    }

    public void updateCaloriesLimit(double limit) {
        if (limit > Double.parseDouble(this.jtfCaloriesCount.getText())) {
            this.jtfIfOverLimit.setText("UNDER");
        } else {
            this.jtfIfOverLimit.setText("OVER");
        }
    }

    public String getFoodName() {
        return this.selectFood.getSelectedItem().toString();
    }

    public String getCaloriesLimit() {
        return this.jtfCaloriesLimit.getText();
    }

    public String getCount() {
        return this.jtfQuantity.getText();
    }

    public String getNewName() {
        return this.jtfName.getText();
    }

    public String getWeight() {
        return this.jtfWeight.getText();
    }

    public void updateIngredients(Food recipe) {
        this.taIngredients.setText(recipe.toString());
    }

    public String getIngredientName() {
        return this.selectIngredient.getSelectedItem().toString();
    }

    public String getIngredientCount() {
        return this.jtfIngredientCount.getText();
    }

    public void enableUI() {
        this.jbLog.setEnabled(true);
        this.jbSaveRecipe.setEnabled(true);
        this.jbAddIngredient.setEnabled(true);
        this.jbSaveNewFood.setEnabled(true);
    }

    public void updateProteinsCount(double proteinIntake) {
        this.jtfProteinsCount.setText("" + String.format(Locale.US, "%.2f",
                (proteinIntake + Double.parseDouble(this.jtfProteinsCount.getText()))));
    }

    public void updateCarbsCount(double carbohydrateIntake) {
        this.jtfCarbohydratesCount
                .setText("" + String.format(Locale.US, "%.2f",
                        (carbohydrateIntake + Double.parseDouble(this.jtfCarbohydratesCount.getText()))));
    }

    public void updateFatCount(double fatIntake) {
        this.jtfFatCount.setText(
                "" + String.format(Locale.US, "%.2f", (fatIntake + Double.parseDouble(this.jtfFatCount.getText()))));
    }
 public void askNewFood() {

        this.jbNewFood.setEnabled(false);

        this.newFoodFrame.setTitle("Please enter new food information");

        this.newFoodFrameHBox.setPreferredSize(new Dimension(640, 360));

        this.newFoodFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.newFoodFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                jbNewFood.setEnabled(true);
            }
        });

        this.newFoodFrame.pack();
        this.newFoodFrame.setLocationRelativeTo(this);
        this.newFoodFrame.setVisible(true);

    }
}
