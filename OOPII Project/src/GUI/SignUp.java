package GUI;

import static DB.DataBaseConnection.*;
import design.Design;
import java.awt.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import org.apache.commons.net.util.Base64;

public class SignUp extends javax.swing.JFrame {

    private BufferedImage profilePicture = null;
    private String extension;

    public SignUp() {
        initComponents();
        editForm();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        titleLabel = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        firstNameLabel = new javax.swing.JLabel();
        firstName = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        lastName = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        confirmPasswordLabel = new javax.swing.JLabel();
        confirmPassword = new javax.swing.JPasswordField();
        genderLabel = new javax.swing.JLabel();
        femaleButton = new javax.swing.JRadioButton();
        maleButton = new javax.swing.JRadioButton();
        dateLabel = new javax.swing.JLabel();
        dateOfBirth = new com.toedter.calendar.JDateChooser();
        lastNameErrorLabel = new javax.swing.JLabel();
        firstNameErrorLabel = new javax.swing.JLabel();
        emailErrorLabel = new javax.swing.JLabel();
        passwordErrorLabel = new javax.swing.JLabel();
        confirmPasswordErrorLabel = new javax.swing.JLabel();
        genderErrorLabel = new javax.swing.JLabel();
        dateErrorLabel = new javax.swing.JLabel();
        signUpError = new javax.swing.JLabel();
        chooseBtn = new javax.swing.JButton();
        pictureLabel = new javax.swing.JLabel();
        profilePictureError = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        submitButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titleLabel.setBackground(new java.awt.Color(255, 255, 255));
        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Sign Up");
        getContentPane().add(titleLabel, java.awt.BorderLayout.PAGE_START);

        firstNameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        firstNameLabel.setText("First Name:");

        firstName.setToolTipText("");
        firstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameActionPerformed(evt);
            }
        });
        firstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                firstNameKeyReleased(evt);
            }
        });

        lastNameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lastNameLabel.setText("Last Name:");

        lastName.setToolTipText("");
        lastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameActionPerformed(evt);
            }
        });
        lastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lastNameKeyPressed(evt);
            }
        });

        emailLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        emailLabel.setText("Email:");

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emailKeyPressed(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        passwordLabel.setText("Password:");

        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });

        confirmPasswordLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        confirmPasswordLabel.setText("Confirm Password:");

        confirmPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                confirmPasswordKeyPressed(evt);
            }
        });

        genderLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        genderLabel.setText("Gender:");

        buttonGroup1.add(femaleButton);
        femaleButton.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        femaleButton.setForeground(new java.awt.Color(255, 51, 255));
        femaleButton.setText("Female");
        femaleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                femaleButtonMouseClicked(evt);
            }
        });
        femaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(maleButton);
        maleButton.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        maleButton.setForeground(new java.awt.Color(51, 51, 255));
        maleButton.setText("Male");
        maleButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                maleButtonKeyPressed(evt);
            }
        });

        dateLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dateLabel.setText("Date Of Birth:");

        dateOfBirth.setForeground(new java.awt.Color(255, 0, 153));
        dateOfBirth.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateOfBirthPropertyChange(evt);
            }
        });

        lastNameErrorLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lastNameErrorLabel.setForeground(new java.awt.Color(255, 0, 0));

        firstNameErrorLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        firstNameErrorLabel.setForeground(new java.awt.Color(255, 0, 0));

        emailErrorLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        emailErrorLabel.setForeground(new java.awt.Color(255, 0, 0));

        passwordErrorLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        passwordErrorLabel.setForeground(new java.awt.Color(255, 0, 0));

        confirmPasswordErrorLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        confirmPasswordErrorLabel.setForeground(new java.awt.Color(255, 0, 0));

        genderErrorLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        genderErrorLabel.setForeground(new java.awt.Color(255, 0, 0));

        dateErrorLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dateErrorLabel.setForeground(new java.awt.Color(255, 0, 0));

        signUpError.setText("signUpError");
        signUpError.setToolTipText("");

        chooseBtn.setText("Choose");
        chooseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseBtnActionPerformed(evt);
            }
        });

        pictureLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pictureLabel.setText("Profile Picture:");

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(signUpError)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lastNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(firstNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmPasswordLabel)
                            .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pictureLabel))
                        .addGap(26, 26, 26)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(contentPanelLayout.createSequentialGroup()
                                .addComponent(femaleButton)
                                .addGap(18, 18, 18)
                                .addComponent(maleButton))
                            .addComponent(lastName)
                            .addComponent(firstName)
                            .addComponent(email)
                            .addComponent(password)
                            .addComponent(confirmPassword)
                            .addComponent(dateOfBirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chooseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(firstNameErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(lastNameErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(emailErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(confirmPasswordErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(genderErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profilePictureError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameErrorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lastNameErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(emailLabel)
                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(emailErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordLabel)
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(passwordErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(confirmPasswordLabel)
                        .addComponent(confirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(confirmPasswordErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(genderLabel)
                        .addComponent(femaleButton)
                        .addComponent(maleButton))
                    .addComponent(genderErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateOfBirth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateLabel)
                    .addComponent(dateErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(profilePictureError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chooseBtn)
                        .addComponent(pictureLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(signUpError)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        getContentPane().add(contentPanel, java.awt.BorderLayout.CENTER);

        submitButton.setBackground(new java.awt.Color(153, 255, 153));
        submitButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        submitButton.setText("Submit");
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                submitButtonMousePressed(evt);
            }
        });
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(submitButton);

        resetButton.setBackground(new java.awt.Color(255, 0, 0));
        resetButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(resetButton);

        getContentPane().add(buttonsPanel, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void femaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_femaleButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        try {
            if (validateForm()) {
                createAccount();
            }
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        resetForm();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void editForm() {
        //Setting the Frame icon
        this.setIconImage(Design.ICON.getImage());

        this.buttonsPanel.setLayout(new FlowLayout());
        this.buttonsPanel.setBackground(Design.FONT_COLOR);

        this.resetButton.setBackground(Design.BACK_COLOR);
        this.submitButton.setBackground(Design.BACK_COLOR);
        this.resetButton.setForeground(Design.FONT_COLOR);
        this.submitButton.setForeground(Design.FONT_COLOR);
        this.resetButton.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.submitButton.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.titleLabel.setBackground(Design.FONT_COLOR);
        this.titleLabel.setForeground(Design.BACK_COLOR);
        this.titleLabel.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_LARGE));

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Design.FONT_COLOR);
        titlePanel.add(this.titleLabel);
        this.add(titlePanel, BorderLayout.NORTH);

        this.contentPanel.setBackground(Design.BACK_COLOR);

        this.firstNameLabel.setForeground(Design.FONT_COLOR);
        this.firstNameLabel.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.lastNameLabel.setForeground(Design.FONT_COLOR);
        this.lastNameLabel.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.emailLabel.setForeground(Design.FONT_COLOR);
        this.emailLabel.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.passwordLabel.setForeground(Design.FONT_COLOR);
        this.passwordLabel.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.confirmPasswordLabel.setForeground(Design.FONT_COLOR);
        this.confirmPasswordLabel.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.genderLabel.setForeground(Design.FONT_COLOR);
        this.genderLabel.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.dateLabel.setForeground(Design.FONT_COLOR);
        this.dateLabel.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.maleButton.setBackground(Design.BACK_COLOR);
        this.maleButton.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.femaleButton.setBackground(Design.BACK_COLOR);
        this.femaleButton.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.contentPanel.setBorder(BorderFactory.createTitledBorder(null, "Sign Up", TitledBorder.CENTER,
                TitledBorder.TOP, new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL), Design.FONT_COLOR));

        this.pictureLabel.setForeground(Design.FONT_COLOR);
        this.pictureLabel.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.chooseBtn.setBackground(Design.FONT_COLOR);
        this.chooseBtn.setForeground(Design.BACK_COLOR);
        this.chooseBtn.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.chooseBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.signUpError.setFont(new Font(Design.FONT_FAMILY, 1, Design.FONT_SIZE_SMALL));
        this.signUpError.setText("");

        this.profilePictureError.setForeground(Color.red);

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle("Sign Up");
    }

    private boolean validateFirstName() {
        if (firstName.getText().length() < 1) {
            firstName.setBackground(Color.red);
            firstNameErrorLabel.setText("Enter your First Name!");
            return false;
        } else {
            firstName.setBackground(Color.white);
            firstNameErrorLabel.setText("");
            return true;
        }
    }

    private boolean validateLastName() {
        if (lastName.getText().length() < 1) {
            lastName.setBackground(Color.red);
            lastNameErrorLabel.setText("Enter your Last Name!");
            return false;
        } else {
            lastName.setBackground(Color.white);
            lastNameErrorLabel.setText("");
            return true;
        }
    }

    private boolean validateEmail() {
        if (email.getText().length() < 1) {
            email.setBackground(Color.red);
            emailErrorLabel.setText("Enter your Email!");
            return false;
        } else {
            if (email.getText().contains("@")) {
                String[] atParts = email.getText().split("@");
                if (atParts.length == 2) {
                    if (atParts[1].contains(".")) {
                        String[] dotParts = atParts[1].split("\\.");
                        if (dotParts.length == 2) {
                            if (atParts[0].length() > 1 && dotParts[0].length() > 1 && dotParts[1].length() > 1) {
                                email.setBackground(Color.white);
                                emailErrorLabel.setText("");
                                return true;

                            } else {
                                email.setBackground(Color.red);
                                emailErrorLabel.setText("Enter your Email correctly!");
                                return false;
                            }
                        } else {
                            email.setBackground(Color.red);
                            emailErrorLabel.setText("Enter your Email correctly!");
                            return false;
                        }
                    } else {
                        email.setBackground(Color.red);
                        emailErrorLabel.setText("Enter your Email correctly!");
                        return false;
                    }
                } else {
                    email.setBackground(Color.red);
                    emailErrorLabel.setText("Enter your Email correctly!");
                    return false;
                }
            } else {
                email.setBackground(Color.red);
                emailErrorLabel.setText("Enter your Email correctly!");
                return false;
            }
        }
    }

    private boolean validatePassword() {
        if (password.getText().length() < 1) {
            password.setBackground(Color.red);
            passwordErrorLabel.setText("Enter your Password!");
            return false;
        } else {
            password.setBackground(Color.white);
            passwordErrorLabel.setText("");
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        if (confirmPassword.getText().length() < 1) {
            confirmPassword.setBackground(Color.red);
            confirmPasswordErrorLabel.setText("Enter your Password!");
            return false;
        } else if (!confirmPassword.getText().equals(password.getText())) {
            confirmPassword.setBackground(Color.red);
            confirmPasswordErrorLabel.setText("Password did not match!");
            return false;
        } else {
            confirmPassword.setBackground(Color.white);
            confirmPasswordErrorLabel.setText("");
            return true;
        }
    }

    private boolean validateGender() {
        if (!femaleButton.isSelected() && !maleButton.isSelected()) {
            genderErrorLabel.setText("Choose a gender!");
            return false;
        } else {
            genderErrorLabel.setText("");
            return true;
        }
    }

    private boolean validateDOB() {
        if (dateOfBirth.getDate() == null) {
            dateErrorLabel.setBackground(Color.red);
            dateErrorLabel.setText("Precise your Date Of Birth!");
            return false;
        } else {
            dateErrorLabel.setText("");
            dateErrorLabel.setBackground(Color.white);
            return true;
        }
    }

    private boolean validateForm() {
        return validateFirstName()
                && validateLastName()
                && validateEmail()
                && validatePassword()
                && validateConfirmPassword()
                && validateGender()
                && validateDOB();
    }

    private void resetForm() {
        firstName.setText("");
        firstName.setBackground(Color.white);
        firstNameErrorLabel.setText("");

        lastName.setText("");
        lastName.setBackground(Color.white);
        lastNameErrorLabel.setText("");

        email.setText("");
        email.setBackground(Color.white);
        emailErrorLabel.setText("");

        password.setText("");
        password.setBackground(Color.white);
        passwordErrorLabel.setText("");

        confirmPassword.setText("");
        confirmPassword.setBackground(Color.white);
        confirmPasswordErrorLabel.setText("");

        buttonGroup1.clearSelection();
        genderErrorLabel.setText("");

        dateOfBirth.setDate(null);
        dateErrorLabel.setText("");
        dateErrorLabel.setBackground(Color.white);
    }

    public static String encrypt(String texto) {
        return new String(Base64.encodeBase64(texto.getBytes()));
    }

    public static String decrypt(String texto) {
        return new String(Base64.decodeBase64(texto.getBytes()));
    }

    public void createAccount() throws ClassNotFoundException, SQLException, IOException {
        if (emailExists(email.getText())) {
            this.signUpError.setForeground(Color.RED);
            this.signUpError.setText("This email has already an account");
        } else {

            String gender, pictureName;
            File file;

            // Checking the gender selected
            if (femaleButton.isSelected()) {
                gender = "female";
            } else {
                gender = "male";
            }

            if (this.profilePicture == null) {
                pictureName = "user.jpg";
            } else {
                int leftLimit = 48; // numeral '0'
                int rightLimit = 122; // letter 'z'
                int targetStringLength = 64;
                Random random = new Random();

                String generatedString = random.ints(leftLimit, rightLimit + 1)
                        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                        .limit(targetStringLength)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
                pictureName = generatedString + "." + this.extension;
                file = new File("images/" + pictureName);
                ImageIO.write(this.profilePicture, this.extension, file);
            }

            boolean insertComplete = insertAccount(firstName.getText(), lastName.getText(), email.getText(),
                    encrypt(password.getText()), gender, new java.sql.Date(dateOfBirth.getDate().getTime()), pictureName);
            if (insertComplete) {
                this.signUpError.setForeground(Color.GREEN);
                this.signUpError.setText("Account created Successfully");
            } else {
                this.signUpError.setForeground(Color.RED);
                this.signUpError.setText("Account creation Failed");
            }
        }

    }

    private void submitButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMousePressed
    }//GEN-LAST:event_submitButtonMousePressed

    private void firstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameActionPerformed

    private void firstNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstNameKeyReleased
        firstName.setBackground(Color.white);
        firstNameErrorLabel.setText("");
    }//GEN-LAST:event_firstNameKeyReleased

    private void lastNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameKeyPressed
        lastName.setBackground(Color.white);
        lastNameErrorLabel.setText("");
    }//GEN-LAST:event_lastNameKeyPressed

    private void emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyPressed
        email.setBackground(Color.white);
        emailErrorLabel.setText("");
    }//GEN-LAST:event_emailKeyPressed

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        password.setBackground(Color.white);
        passwordErrorLabel.setText("");
    }//GEN-LAST:event_passwordKeyPressed

    private void confirmPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmPasswordKeyPressed
        confirmPassword.setBackground(Color.white);
        confirmPasswordErrorLabel.setText("");
    }//GEN-LAST:event_confirmPasswordKeyPressed

    private void femaleButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_femaleButtonMouseClicked
        genderErrorLabel.setText("");
    }//GEN-LAST:event_femaleButtonMouseClicked

    private void maleButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_maleButtonKeyPressed
        genderErrorLabel.setText("");
    }//GEN-LAST:event_maleButtonKeyPressed

    private void dateOfBirthPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateOfBirthPropertyChange
        dateErrorLabel.setText("");
    }//GEN-LAST:event_dateOfBirthPropertyChange

    private void chooseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseBtnActionPerformed

        JFileChooser chooser = new JFileChooser();
        int values = chooser.showOpenDialog(null);
        if (values == 0) {
            File file = chooser.getSelectedFile();
            if (file.exists()) {
                try {
                    String fileName = file.getName();
                    String[] parts = fileName.split("\\.");
                    this.extension = parts[parts.length - 1];
                    extension = extension.toLowerCase();
                    if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {

                        this.profilePicture = ImageIO.read(file);

                        this.profilePictureError.setText("");

                        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                        Image img = icon.getImage();
                        int ratio = this.profilePicture.getHeight() / this.profilePictureError.getHeight();
                        Image imgScale = img.getScaledInstance(this.profilePicture.getWidth() / ratio,
                                this.profilePicture.getHeight() / ratio, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(imgScale);

                        this.profilePictureError.setIcon(scaledIcon);
                    } else {
                        this.profilePictureError.setIcon(null);
                        this.profilePictureError.setText("Image extension must be jpg or png");
                        this.profilePicture = null;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_chooseBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton chooseBtn;
    private javax.swing.JPasswordField confirmPassword;
    private javax.swing.JLabel confirmPasswordErrorLabel;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel dateErrorLabel;
    private javax.swing.JLabel dateLabel;
    private com.toedter.calendar.JDateChooser dateOfBirth;
    private javax.swing.JTextField email;
    private javax.swing.JLabel emailErrorLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JRadioButton femaleButton;
    private javax.swing.JTextField firstName;
    private javax.swing.JLabel firstNameErrorLabel;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel genderErrorLabel;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JTextField lastName;
    private javax.swing.JLabel lastNameErrorLabel;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JRadioButton maleButton;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordErrorLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel pictureLabel;
    private javax.swing.JLabel profilePictureError;
    private javax.swing.JButton resetButton;
    private javax.swing.JLabel signUpError;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
