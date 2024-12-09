package org.example.UI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CurseraView extends JFrame {
    //COLORES
    Color gris= new Color(204, 203, 203, 50);
    Color verde= new Color(139, 218, 124);
    Color rojo= new Color(228, 132, 132);
    Color azul= new Color(132, 206, 228);




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
        btnHome.setFocusPainted(false);
        btnHome.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
        buttonsPanel.add(btnHome);
        JButton btnCursos = new JButton("Cursos");
        btnCursos.setFocusPainted(false);
        btnCursos.addActionListener(e -> cardLayout.show(mainPanel, "Courses"));
        buttonsPanel.add(btnCursos);
        JButton btnUsuarios = new JButton("Usuarios");
        btnUsuarios.setFocusPainted(false);
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
        JPanel coursesEditScreen= createCourseEditScreen();
        JPanel evaluationsScreen= createEvaluationScreen();


        // Agregar pantallas al CardLayout
        mainPanel.add(homeScreen, "Home");
        mainPanel.add(usersScreen, "Users");
        mainPanel.add(coursesScreen, "Courses");
        mainPanel.add(coursesEditScreen, "EditCourses");
        mainPanel.add(evaluationsScreen,"Evaluation");

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
        usersButton.setFocusPainted(false);
        usersButton.setBounds(150, 70, 300, 300); // Posición y tamaño del botón
        panel.add(usersButton);

        JButton coursesButton = new JButton("Cursos");
        coursesButton.setFocusPainted(false);
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
        addButton.setFocusPainted(false);
        JPanel header = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        header.add(addButton);

        JPanel coursesList = new JPanel();
        coursesList.setLayout(new GridLayout(4, 1, 10, 40));

        for (int i = 0; i < 4; i++) {
            coursesList.add(createCourseRow("Curso " + (i + 1)));
        }

        panel.add(header, BorderLayout.NORTH);
        panel.add(coursesList, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCourseRow(String courseName) {
        JPanel row = new JPanel(new BorderLayout()); // Espaciado horizontal y vertical
        row.setBorder(BorderFactory.createEmptyBorder(75, 10, 75, 10));

        // Panel para nombre del curso
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Alineación a la izquierda
        JLabel nameLabel = new JLabel(courseName);
        namePanel.setBackground(gris);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente estilizada
        namePanel.add(nameLabel);

        // Panel para botones
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Alineación a la derecha
        buttonsPanel.setBackground(gris);
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("X");

        // Estilizar botones
        editButton.setFocusPainted(false);
        editButton.setBackground(verde);
        deleteButton.setFocusPainted(false);
        deleteButton.setBackground(rojo);

        //Añadir botones a panel
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        // Agregar componentes al panel principal
        row.add(namePanel, BorderLayout.WEST);
        row.add(buttonsPanel, BorderLayout.EAST);

        // Fondo opcional para diferenciar filas
        row.setBackground(gris); // Gris claro

        editButton.addActionListener(e -> cardLayout.show(mainPanel,"EditCourses"));

        return row;
    }


    private JPanel createCourseEditScreen() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes

        // Título
        JLabel titleLabel = new JLabel("NOMBRE DEL CURSO");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Datos del curso
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Layout vertical
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel courseDataLabel = new JLabel("RESTO DE DATOS DEL CURSO");
        JButton professorButton = new JButton("SELECTOR DE PROFESOR");
        professorButton.setFocusPainted(false);

        centerPanel.add(courseDataLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaciado
        centerPanel.add(professorButton);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado

        // Sección de alumnos
        JLabel studentsLabel = new JLabel("ALUMNOS");
        JButton addStudentButton = new JButton("SELECTOR ALUMNO");
        addStudentButton.setBackground(verde);
        addStudentButton.setForeground(Color.WHITE);
        addStudentButton.setFocusPainted(false);

        centerPanel.add(studentsLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(addStudentButton);

        // Lista de alumnos
        JPanel studentsList = new JPanel(new GridLayout(0, 1, 5, 5));
        studentsList.setLayout(new GridLayout(4, 1, 10, 40));
        studentsList.setBorder(BorderFactory.createEmptyBorder(40, 10, 40, 10));
        studentsList.setBackground(new Color(205, 181, 154)); // Fondo para resaltar la sección

        // Crear un JScrollPane que contenga el JPanel
        JScrollPane scrollPane = new JScrollPane(studentsList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        for (int i = 0; i < 4; i++) { // Lista ejemplo
            JPanel row = new JPanel(new BorderLayout());
            row.setBorder(BorderFactory.createEmptyBorder(40, 10, 40, 10));
            JLabel studentName = new JLabel("Nombre " + (i + 1));
            studentName.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            JButton evaluationsButton = new JButton("Evaluaciones");
            evaluationsButton.setBackground(azul);
            evaluationsButton.setForeground(Color.WHITE);
            evaluationsButton.setFocusPainted(false);

            JButton deleteButton = new JButton("X");
            deleteButton.setBackground(rojo);

            row.add(studentName, BorderLayout.CENTER);
            row.add(evaluationsButton, BorderLayout.EAST);
            row.add(deleteButton, BorderLayout.WEST);

            studentsList.add(row);
        }

        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(scrollPane);

        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createUsersScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton addButton = new JButton("Añadir");
        addButton.setFocusPainted(false);
        JPanel header = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        header.add(addButton);

        JPanel usersList = new JPanel();
        usersList.setLayout(new GridLayout(4, 1, 10, 40));

        for (int i = 0; i < 4; i++) {
            usersList.add(createUserRow("Usuario " + (i + 1), "Tipo " + (i + 1)));
        }

        panel.add(header, BorderLayout.NORTH);
        panel.add(usersList, BorderLayout.CENTER);

        return panel;
    }




    private JPanel createUserRow(String userName, String userType) {

        JPanel row = new JPanel(new BorderLayout());
        row.setBorder(BorderFactory.createEmptyBorder(75, 10, 75, 10));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.setBackground(gris);// Alineación a la izquierda
        JLabel nameLabel = new JLabel(userName + " - " + userType);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Fuente estilizada
        namePanel.add(nameLabel);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBackground(gris);
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("X");

        // Estilizar botones
        editButton.setFocusPainted(false);
        editButton.setBackground(verde);
        deleteButton.setFocusPainted(false);
        deleteButton.setBackground(rojo);

        //Agregar botones al panel de botones
        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);

        // Agregar componentes al panel principal
        row.add(namePanel, BorderLayout.WEST);
        row.add(buttonsPanel, BorderLayout.EAST);

        // Fondo opcional para diferenciar filas
        row.setBackground(gris); // Gris claro
        return row;
    }




    private JPanel createEvaluationScreen() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes

        // Título
        JLabel titleLabel = new JLabel("EVALUACIONES ALUMNO NOMBRE EN CURSO X");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Botón de añadir nota
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addNoteButton = new JButton("AÑADIR NOTA");
        addNoteButton.setBackground(Color.GREEN);
        addNoteButton.setForeground(Color.WHITE);
        addNoteButton.setFocusPainted(false);
        headerPanel.add(addNoteButton);

        panel.add(headerPanel, BorderLayout.NORTH);

        // Tabla de evaluaciones
        JPanel tablePanel = new JPanel(new GridLayout(0, 4, 10, 10));
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.RED));

        // Encabezados de tabla
        tablePanel.add(new JLabel("NOTA"));
        tablePanel.add(new JLabel("FECHA"));
        tablePanel.add(new JLabel("APROBADO"));
        tablePanel.add(new JLabel(""));

        // Filas de ejemplo
        for (int i = 0; i < 3; i++) {
            JLabel noteLabel = new JLabel("Nota " + (i + 1));
            JLabel dateLabel = new JLabel("Fecha " + (i + 1));
            JLabel approvedLabel = new JLabel(i % 2 == 0 ? "Sí" : "No");

            JButton editButton = new JButton("EDITAR");
            editButton.setBackground(Color.GREEN);
            editButton.setForeground(Color.WHITE);
            editButton.setFocusPainted(false);

            tablePanel.add(noteLabel);
            tablePanel.add(dateLabel);
            tablePanel.add(approvedLabel);
            tablePanel.add(editButton);
        }

        panel.add(tablePanel, BorderLayout.CENTER);

        return panel;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurseraView app = new CurseraView();
            app.setVisible(true);
        });
    }
}
