package Final_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class SimpleFrame extends JFrame {
    // Constructor
    public SimpleFrame() {
        setTitle("Simple JFrame Example");
        setLayout(new FlowLayout());

        add(createInitialPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        new MenuItem("Burger", "Delicious beef burger", 8.99);
 	   	new MenuItem( "Pizza", "Cheese and tomato pizza", 12.99);
 	   new MenuItem( "Salad", "Fresh garden salad", 6.99);
    }
    
    private void showConfirmationScreen(String text, customer theCustomer) {
        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setLayout(new BoxLayout(confirmationPanel, BoxLayout.Y_AXIS));
        confirmationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel confirmationLabel = new JLabel(text);
        confirmationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton mainMenuButton = new JButton("Back to Main Menu");
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the main menu
                SimpleFrame.this.setContentPane(createCustomerPanel(theCustomer));
                SimpleFrame.this.validate();
            }
        });

        confirmationPanel.add(confirmationLabel);
        confirmationPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        confirmationPanel.add(mainMenuButton);

        this.setContentPane(confirmationPanel);
        this.validate();
    }

    private JPanel createInitialPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton customerPanelButton = new JButton("Customer Panel");
        JButton adminPanelButton = new JButton("Admin Panel");

        customerPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(customerLogin());
                validate();
            }
        });

        adminPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(adminLogin());
                validate();
                repaint(); // This ensures the UI is properly updated
            }
        });

        panel.add(customerPanelButton);
        panel.add(adminPanelButton);
        
        

        return panel;
    }
    
    private JPanel adminLogin() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        JTextField nameField = new JTextField(20);
        loginPanel.add(new JLabel("Name:"));
        loginPanel.add(nameField);

        JTextField roleField = new JTextField(20);
        loginPanel.add(new JLabel("Role:"));
        loginPanel.add(roleField);

        JTextField IDField = new JTextField(20);
        loginPanel.add(new JLabel("ID:")); // Fixed label from "Role" to "ID"
        loginPanel.add(IDField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String role = roleField.getText();

                int ID;
                try {
                    ID = Integer.parseInt(IDField.getText());
                } catch (NumberFormatException ex) {
                    // Handle invalid number format
                    JOptionPane.showMessageDialog(loginPanel, "ID must be a number", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Admin admin = new Admin(ID, role, name);
                setContentPane(createAdminPanel(admin));
                validate();
                repaint();
            }
        });

        loginPanel.add(loginButton);

        return loginPanel;
    }

    
    private JPanel customerLogin() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        // Name field
        JTextField nameField = new JTextField(20);
        loginPanel.add(new JLabel("Name:"));
        loginPanel.add(nameField);

        // Age field
        JTextField ageField = new JTextField(20);
        loginPanel.add(new JLabel("Age:"));
        loginPanel.add(ageField);

        // Phone number field
        JTextField phoneField = new JTextField(20);
        loginPanel.add(new JLabel("Phone Number:"));
        loginPanel.add(phoneField);

        // Payment method dropdown
        String[] paymentMethods = {"Cash", "Credit", "Debit"};
        JComboBox<String> paymentDropdown = new JComboBox<>(paymentMethods);
        loginPanel.add(new JLabel("Payment Method:"));
        loginPanel.add(paymentDropdown);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate inputs
                if (nameField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(loginPanel, "Please enter your name.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int age;
                try {
                    age = Integer.parseInt(ageField.getText().trim());
                    if (age <= 0) {
                        JOptionPane.showMessageDialog(loginPanel, "Please enter a valid age.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(loginPanel, "Age must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (phoneField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(loginPanel, "Please enter your phone number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Create a Customer object with the entered information
                String name = nameField.getText().trim();
                String phoneNumber = phoneField.getText().trim();
                String paymentMethod = (String) paymentDropdown.getSelectedItem();

                customer customerCreated = new customer(name, age, phoneNumber, paymentMethod);

                // Switch to createCustomerPanel with the customer object
                setContentPane(createCustomerPanel(customerCreated));
                validate();
                repaint();
            }
        });

        
        loginPanel.add(loginButton);

        return loginPanel;
    }

    
    private JPanel createAdminPanel(Admin currentAdmin) {
        JPanel adminPanel = new JPanel();
        // Set the panel's layout to BoxLayout aligning components top to bottom
        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));

        // Create the welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + currentAdmin.getName());
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        adminPanel.add(welcomeLabel);

        // Create the buttons
        JButton updateMenuButton = new JButton("Update Menu Items");
        JButton manageTablesButton = new JButton("Show Reservations");
        JButton viewSalesButton = new JButton("View Sales");
        JButton showFeedbackButton = new JButton("Show Customer Feedback");
        updateMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel menuUpdatePanel = updateMenuPanel(currentAdmin);
//                // Assuming 'frame' is your JFrame
                  setContentPane(menuUpdatePanel);
                  validate();
                  repaint();
            }
        });

        manageTablesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(createReservationDisplayPanel(currentAdmin));
                validate();
                repaint(); // Ensures the UI is updated
            }
        });

        viewSalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(createViewSalesPanel(currentAdmin));
                validate();
                repaint();
            }
        });
        
        showFeedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(createFeedbackDisplayPanel(currentAdmin));
                validate();
                repaint();
            }
        });
        
        JButton backButton = new JButton("Back To Main Menu");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(createInitialPanel());
                validate();
            }
        });
        

        // Add buttons to the panel
        adminPanel.add(updateMenuButton);
        adminPanel.add(manageTablesButton);
        adminPanel.add(viewSalesButton);
        adminPanel.add(showFeedbackButton);
        adminPanel.add(backButton);


        return adminPanel;
    }
    
    private JPanel createViewSalesPanel(Admin theAdmin) {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        List<Order> allOrders = Order.getAllOrders();

        for (Order order : allOrders) {
            JPanel orderPanel = new JPanel();
            orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
            orderPanel.setBorder(BorderFactory.createTitledBorder("Order ID: " + order.getOrderID()));

            // Add order details
            JLabel nameLabel = new JLabel("Name: " + order.getName());
            orderPanel.add(nameLabel);

            // Add a sub-panel for items
            JPanel itemsPanel = new JPanel();
            itemsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            JLabel itemsLabel = new JLabel("Items: ");
            itemsPanel.add(itemsLabel);

            StringBuilder itemsText = new StringBuilder();
            double total = 0;
            for (MenuItem item : order.getItems()) {
                itemsText.append(item.getName()).append(" ($").append(item.getPrice()).append("), ");
                total += item.getPrice();
            }
            // Remove the last comma and space
            if (itemsText.length() > 0) {
                itemsText = new StringBuilder(itemsText.substring(0, itemsText.length() - 2));
            }
            JLabel itemListLabel = new JLabel(itemsText.toString());
            itemsPanel.add(itemListLabel);

            orderPanel.add(itemsPanel);

            // Add a label for total sale
            JLabel totalSaleLabel = new JLabel("Total Sale: $" + total);
            orderPanel.add(totalSaleLabel);
            
            
            

            // Add the order panel to the content panel
            contentPanel.add(orderPanel);
        }

        JButton backButton = new JButton("Back To Admin Menu");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(createAdminPanel(theAdmin));
                validate();
            }
        });
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(backButton);

        return containerPanel;
    }




    
    private JScrollPane createFeedbackDisplayPanel(Admin theAdmin) {
        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setLayout(new BoxLayout(feedbackPanel, BoxLayout.Y_AXIS));

        // Assuming allFeedback is a static variable in the Feedback class
        for (Feedback feedback : Feedback.getAllFeedback()) {
            JLabel feedbackLabel = new JLabel("ID: " + feedback.getFeedbackId() + " - " + feedback.getFeedbackText());
            feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            feedbackPanel.add(feedbackLabel);
        }
        
        // Create a horizontal box to center the button
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalGlue()); // Glue before the button to push it to center
        JButton mainMenuButton = new JButton("Back to Admin Menu");
        buttonBox.add(mainMenuButton);
        buttonBox.add(Box.createHorizontalGlue()); // Glue after the button for symmetrical spacing

        // Add the horizontal box (with the button) to the reservation panel
        feedbackPanel.add(buttonBox);

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the main menu
                SimpleFrame.this.setContentPane(createAdminPanel(theAdmin));
                SimpleFrame.this.validate();
                SimpleFrame.this.repaint(); // Refresh the frame
            }
        });

        // Optionally, add a scroll pane if the list is long
        JScrollPane scrollPane = new JScrollPane(feedbackPanel);
        return scrollPane; // Return the scroll pane instead of the panel directly
    }

    
    
    private JPanel updateMenuPanel(Admin theAdmin) {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Make a copy of the menu items to avoid concurrent modification issues
        List<MenuItem> itemsCopy = new ArrayList<>(MenuItem.getMenuItems());

        for (MenuItem menuItem : itemsCopy) {
            // Create a panel for each menu item and its remove button
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            // Label for the menu item
            JLabel menuItemLabel = new JLabel(menuItem.getName() + " - $" + menuItem.getPrice());
            itemPanel.add(menuItemLabel);

            // Remove button for the menu item
            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                // Remove the item from the menu list
                MenuItem.getMenuItems().remove(menuItem);

                // Refresh the menu panel to reflect the removal
                refreshMenuPanel(theAdmin);
            });
            

            
            itemPanel.add(removeButton);

            // Add the item panel to the main menu panel
            menuPanel.add(itemPanel);
        }
        
        

        // Add button for adding new items
        JButton addButton = new JButton("Add New Item");
        addButton.addActionListener(e -> {
            JPanel addMenuItemPanel = createAddMenuItemPanel(theAdmin);
            // Assuming 'frame' is a reference to the JFrame
            setContentPane(addMenuItemPanel);
           validate();
            repaint();
        });
        menuPanel.add(addButton);
        
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalGlue()); // Glue before the button to push it to center
        JButton mainMenuButton = new JButton("Back to Main Menu");
        buttonBox.add(mainMenuButton);
        buttonBox.add(Box.createHorizontalGlue()); // Glue after the button for symmetrical spacing

        // Add the horizontal box (with the button) to the reservation panel
        menuPanel.add(buttonBox);

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the main menu
                SimpleFrame.this.setContentPane(createAdminPanel(theAdmin));
                SimpleFrame.this.validate();
                SimpleFrame.this.repaint(); // Refresh the frame
            }
        });

        return menuPanel;
    }

    private void refreshMenuPanel(Admin theAdmin) {
        // Replace the content pane with the updated menu panel
      setContentPane(updateMenuPanel(theAdmin));
        validate();
        repaint();
    }
    
    
    private JPanel createAddMenuItemPanel(Admin theAdmin) {
        final JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));

        final JTextField nameField = new JTextField(20);
        final JTextField descriptionField = new JTextField(20);
        final JTextField priceField = new JTextField(20);

        addPanel.add(new JLabel("Name of Food:"));
        addPanel.add(nameField);
        addPanel.add(new JLabel("Description:"));
        addPanel.add(descriptionField);
        addPanel.add(new JLabel("Price:"));
        addPanel.add(priceField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String description = descriptionField.getText().trim();
                String priceText = priceField.getText().trim();

                if (name.isEmpty() || description.isEmpty() || priceText.isEmpty()) {
                    JOptionPane.showMessageDialog(addPanel, "All fields must be filled out", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double price = Double.parseDouble(priceText);
                    MenuItem newMenuItem = new MenuItem(name, description, price);

                    // Switch back to the main menu panel
                    // Assuming 'frame' is accessible here and createInitialPanel() is your method to create the main menu panel
                    setContentPane(createAdminPanel(theAdmin));
                    validate();
                    repaint();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addPanel, "Price must be a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        addPanel.add(submitButton);

        return addPanel;
    }


  


    
   
    private JPanel createReservationDisplayPanel(Admin theAdmin) {
        JPanel reservationPanel = new JPanel();
        reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.Y_AXIS));

        // Iterate through all reservations and add them to the panel
        for (Reservation reservation : Reservation.getInstances()) {
            JLabel reservationLabel = new JLabel(reservation.toString());
            reservationPanel.add(reservationLabel);
        }

        // Create a horizontal box to center the button
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalGlue()); // Glue before the button to push it to center
        JButton mainMenuButton = new JButton("Back to Admin Menu");
        buttonBox.add(mainMenuButton);
        buttonBox.add(Box.createHorizontalGlue()); // Glue after the button for symmetrical spacing

        // Add the horizontal box (with the button) to the reservation panel
        reservationPanel.add(buttonBox);

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Return to the main menu
                SimpleFrame.this.setContentPane(createAdminPanel(theAdmin));
                SimpleFrame.this.validate();
                SimpleFrame.this.repaint(); // Refresh the frame
            }
        });

        return reservationPanel;
    }


    private JPanel createCustomerPanel(customer theCustomer) {
        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new BoxLayout(customerPanel, BoxLayout.Y_AXIS));

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + theCustomer.getName());
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerPanel.add(welcomeLabel);

        JButton reserveTableButton = new JButton("Reserve Table");
        JButton showMenuButton = new JButton("Show Menu");
        JButton orderButton = new JButton("Place Order");
        JButton feedbackButton = new JButton("Give Feedback");

        reserveTableButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        showMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedbackButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        reserveTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showReservationPage(theCustomer);
            }
        });

        showMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMenu(theCustomer);
            }
        });
        
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel menuOrderPanel = createMenuOrderPanel(theCustomer);
                // Assuming 'frame' is your JFrame
                setContentPane(menuOrderPanel);
                validate();
                repaint();
            }
        });
        
        feedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel feedbackPanel = createFeedbackPanel(theCustomer);
                // Assuming 'frame' is your JFrame
                setContentPane(feedbackPanel);
                validate();
                repaint();
            }
        });
        
        JButton backButton = new JButton("Back To Main Menu");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(createInitialPanel());
                validate();
            }
        });
        
        
        


        customerPanel.add(reserveTableButton);
        customerPanel.add(showMenuButton);
        customerPanel.add(orderButton);
        customerPanel.add(feedbackButton);
        
        customerPanel.add(backButton);



        return customerPanel;
    }
    
    private JPanel createFeedbackPanel(customer theCustomer) {
        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setLayout(new BoxLayout(feedbackPanel, BoxLayout.Y_AXIS));

        // Feedback text field
        JTextArea feedbackTextArea = new JTextArea(3, 15);
        feedbackTextArea.setLineWrap(true);
        feedbackTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(feedbackTextArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Submit button for feedback
        JButton submitButton = new JButton("Submit Feedback");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Feedback(feedbackTextArea.getText());
                setContentPane(createCustomerPanel(theCustomer));
                validate();
            }
        });
        
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(createCustomerPanel(theCustomer));
                validate();
            }
        });
        
        


        // Add components to the panel
        feedbackPanel.add(scrollPane);
        feedbackPanel.add(submitButton);
        feedbackPanel.add(backButton);

        return feedbackPanel;
    }
    
    
    private JPanel createMenuOrderPanel(customer theCustomer) {
        JPanel menuOrderPanel = new JPanel();
        menuOrderPanel.setLayout(new BoxLayout(menuOrderPanel, BoxLayout.Y_AXIS));

        // Create a new Order object here
        final Order currentOrder = new Order("Test"); // Fill in tableID and status as required

        for (final MenuItem menuItem : MenuItem.getMenuItems()) {
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel menuItemLabel = new JLabel(menuItem.getName() + " - $" + menuItem.getPrice());
            JButton addButton = new JButton("Add");

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentOrder.addItem(menuItem);
                    // Optional: Update UI or Show Confirmation
                }
            });

            itemPanel.add(menuItemLabel);
            itemPanel.add(addButton);
            menuOrderPanel.add(itemPanel);
        }

        // Submit Order button
        JButton submitOrderButton = new JButton("Submit Order");
        submitOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Here, handle the submission of the currentOrder
                // For example, adding it to a list of orders
                // OrderList.addOrder(currentOrder);

                // Optional: Show order confirmation or reset UI
                JOptionPane.showMessageDialog(menuOrderPanel, "Order submitted successfully!");

                // Switch back to the main menu
                setContentPane(createCustomerPanel(theCustomer));
                validate();
                repaint();
            }
        });

        menuOrderPanel.add(submitOrderButton);

        return menuOrderPanel;
    }



    private void showReservationPage(customer theCustomer) {
        JPanel reservationPanel = new JPanel();
        reservationPanel.setLayout(new BoxLayout(reservationPanel, BoxLayout.Y_AXIS));
        reservationPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Fields for the reservation form
        SpinnerNumberModel capacityModel = new SpinnerNumberModel(1, 1, 20, 1);
        final JSpinner capacitySpinner = new JSpinner(capacityModel);
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);

        // Adding form fields to the panel
        reservationPanel.add(new JLabel("Capacity:"));
        reservationPanel.add(capacitySpinner);
        reservationPanel.add(new JLabel("Time of Reservation:"));
        reservationPanel.add(timeSpinner);

        // Submit button
        JButton submitButton = new JButton("Submit Reservation");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Extract data from form fields
                // Create instances of Customer and Reservation
                // Optionally, handle the created reservation
                 int numberOfGuests = (Integer) capacitySpinner.getValue();
                // Display confirmation screen
            	Reservation instance = new Reservation(  theCustomer.getName(),  numberOfGuests);
                showConfirmationScreen("Reservation Complete!", theCustomer);
            }
        });

        reservationPanel.add(submitButton);
        this.setContentPane(reservationPanel);
        this.validate();
    }
    
    

    private void showMenu(customer theCustomer) {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (MenuItem item : MenuItem.getMenuItems()) {
            JLabel menuItemLabel = new JLabel(item.getName() + " - $" + item.getPrice());
            menuItemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuPanel.add(menuItemLabel);
        }

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(createCustomerPanel(theCustomer));
                validate();
            }
        });

        menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacer
        menuPanel.add(backButton);

        setContentPane(menuPanel);
        validate();
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SimpleFrame frame = new SimpleFrame();
                frame.setVisible(true);
            }
        });
    }
}




