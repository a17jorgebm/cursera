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
    Color marron= new Color(205, 181, 154);




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

    //Pantalla de edicion de cada curso
    private JPanel createCourseEditScreen() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes

        JScrollPane scrollPane1 = new JScrollPane(panel);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Título
        JLabel titleLabel = new JLabel("NOMBRE DEL CURSO");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Datos del curso
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Layout vertical
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel courseDataLabel = new JLabel("RESTO DE DATOS DEL CURSO");

        centerPanel.add(courseDataLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaciado
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaciado

        // Sección de profesores
        JLabel teachersLabel = new JLabel("PROFESORES");
        JButton addTeacherButton = new JButton("SELECTOR PROFESOR");
        addTeacherButton.setBackground(rojo);
        addTeacherButton.setForeground(Color.WHITE);
        addTeacherButton.setFocusPainted(false);

        centerPanel.add(teachersLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(addTeacherButton);

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
        studentsList.setBackground(marron); // Fondo para resaltar la sección

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

    //Panel de usuarios
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

    //Crear fila para cada usuario en pantalla de usuarios
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
        editButton.addActionListener(e -> cardLayout.show(mainPanel,"Evaluation"));
        return row;
    }



    // Pantalla de editar evaluación de cada alumno
    private JPanel createEvaluationScreen() {
        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes


        // Panel de encabezado con el botón "Añadir Nota"
        JPanel headerPanel = new JPanel(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("EVALUACIONES ALUMNO NOMBRE EN CURSO X", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        //Boton añadir nota
        JButton addNoteButton = new JButton("AÑADIR NOTA");
        addNoteButton.setBackground(verde); // Color verde
        addNoteButton.setForeground(Color.WHITE);
        addNoteButton.setFocusPainted(false);
        addNoteButton.setPreferredSize(new Dimension(120, 30)); // Ajustar tamaño


        headerPanel.add(addNoteButton, BorderLayout.EAST);

        panel.add(headerPanel);

        // Tabla de evaluaciones
        JPanel tablePanel = new JPanel(new GridLayout(0, 1, 10, 10)); // Una columna de filas
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tablePanel.setBackground(marron); // Fondo de la tabla

        // Filas de ejemplo
        for (int i = 0; i < 4; i++) {
            // Crear panel para cada fila
            JPanel rowPanel = new JPanel(new GridLayout(1, 4, 10, 10));
            rowPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
            )); // Borde externo y márgenes internos

            // Elementos de la fila
            JLabel noteLabel = new JLabel("Nota " + (i + 1), JLabel.CENTER);
            JLabel dateLabel = new JLabel("Fecha " + (i + 1), JLabel.CENTER);
            JLabel approvedLabel = new JLabel(i % 2 == 0 ? "Aprobado" : "No aprobado", JLabel.CENTER);

            JButton editButton = new JButton("EDITAR");
            editButton.setBackground(verde); // Color verde
            editButton.setForeground(Color.WHITE);
            editButton.setFocusPainted(false);
            editButton.setPreferredSize(new Dimension(80, 30)); // Tamaño del botón

            // Añadir componentes a la fila
            rowPanel.add(noteLabel);
            rowPanel.add(dateLabel);
            rowPanel.add(approvedLabel);
            rowPanel.add(editButton);

            // Añadir fila al panel de tabla
            tablePanel.add(rowPanel);
        }

        // Añadir la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Sin bordes extra

        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CurseraView app = new CurseraView();
            app.setVisible(true);
        });
    }
}
