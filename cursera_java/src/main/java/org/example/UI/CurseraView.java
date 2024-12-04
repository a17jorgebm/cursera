package org.example.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CurseraView extends JFrame {

    private CardLayout cardLayout; // Para alternar entre pantallas
    private JPanel mainPanel; // Contenedor principal para las pantallas

    public CurseraView() {
        //CREAR VENTANA INICIAL
        setTitle("Cursera");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null); // Centrar la ventana

        // ENCABEZADO
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde negro
        headerPanel.setPreferredSize(new Dimension(headerPanel.getWidth(), 60)); // Altura fija

        //titulo
        JPanel tittlePanel= new JPanel();
        JLabel titleLabel = new JLabel("Cursera");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        tittlePanel.add(titleLabel);
        headerPanel.add(tittlePanel,BorderLayout.WEST); // titulo alineado a la izquierda

        //Botones encabezado
        JPanel buttonsPanel= new JPanel();
        JButton btnHome = new JButton("Home");
        btnHome.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        buttonsPanel.add(btnHome);
        JButton btnCursos = new JButton("Cursos");
        btnCursos.addActionListener(e -> cardLayout.show(mainPanel, "Courses"));
        buttonsPanel.add(btnCursos);
        JButton btnUsuarios = new JButton("Usuarios");
        btnUsuarios.addActionListener(e -> cardLayout.show(mainPanel, "Users"));
        buttonsPanel.add(btnUsuarios);

        headerPanel.add(buttonsPanel,BorderLayout.EAST); // titulo alineado a la derecha


        // Contenedor central con CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // PANTALLAS
        JPanel homeScreen = createHomeScreen();
        JPanel coursesScreen = createCoursesScreen();
        JPanel usersScreen = createUsersScreen();
        //JPanel coursesEditScreen=null; //Temporal mentras nn se crea metodo
        //JPanel evaluationsScreen=null;//Temporal mentras nn se crea metodo


        // Agregar pantallas al CardLayout
        mainPanel.add(homeScreen, "Home");
        mainPanel.add(coursesScreen, "Courses");
        mainPanel.add(usersScreen, "Users");
        //mainPanel.add(coursesEditScreen, "EditCourses");
        //mainPanel.add(evaluationsScreen,"Evaluation");

        // Cambiar la disposición del frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        cardLayout.show(mainPanel, "Home"); // Mostrar la pantalla inicial
    }

    private JPanel createHomeScreen() {
        //Creacion de panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        //Creación de botones
        JButton usersButton = new JButton("Usuarios");
        usersButton.setBounds(150, 70, 300, 300); // Posición y tamaño del botón
        panel.add(usersButton);

        JButton coursesButton = new JButton("Cursos");
        coursesButton.setBounds(550, 70, 300, 300); // Posición y tamaño del botón
        panel.add(coursesButton);

        //Navegacion
        usersButton.addActionListener(e -> cardLayout.show(mainPanel, "Users"));
        coursesButton.addActionListener(e -> cardLayout.show(mainPanel, "Courses"));

        return panel;
    }

    private JPanel createCoursesScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton addButton = new JButton("Añadir");
        JPanel header = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        header.add(addButton);

        JPanel coursesList = new JPanel();
        coursesList.setLayout(new GridLayout(4, 1, 10, 10));

        for (int i = 0; i < 4; i++) {
            coursesList.add(createCourseRow("Curso " + (i + 1)));
        }

        panel.add(header, BorderLayout.NORTH);
        panel.add(coursesList, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createUsersScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton addButton = new JButton("Añadir");
        JPanel header = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        header.add(addButton);

        JPanel usersList = new JPanel();
        usersList.setLayout(new GridLayout(4, 1, 10, 10));

        for (int i = 0; i < 4; i++) {
            usersList.add(createUserRow("Usuario " + (i + 1), "Tipo " + (i + 1)));
        }

        panel.add(header, BorderLayout.NORTH);
        panel.add(usersList, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCourseRow(String courseName) {
        JPanel row = new JPanel(new BorderLayout());

        //Panel para nombre de curso
        JPanel namePanel= new JPanel();
        JLabel nameLabel = new JLabel(courseName);
        namePanel.add(nameLabel);

        //Panel para botones de curso
        JPanel buttonsPanel = new JPanel();
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("X");
        deleteButton.setForeground(Color.RED);

        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        row.add(nameLabel, BorderLayout.WEST);
        row.add(buttonsPanel, BorderLayout.EAST);

        return row;
    }

    private JPanel createUserRow(String userName, String userType) {
        JPanel row = new JPanel(new BorderLayout());

        JLabel nameLabel = new JLabel(userName + " - " + userType);
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("X");
        deleteButton.setForeground(Color.RED);

        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        row.add(nameLabel, BorderLayout.CENTER);
        row.add(buttonsPanel, BorderLayout.EAST);

        return row;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurseraView app = new CurseraView();
            app.setVisible(true);
        });
    }
}
