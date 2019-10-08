package user_interface;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import model.Archivo;
import model.Clase;
import model.Directorio;
import model.Estadisticas;
import model.Metodo;
import service.DirectorioService;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JMenu;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import javax.swing.JFileChooser;
import javax.swing.GroupLayout;
import java.awt.Color;

import javax.swing.JScrollPane;
import java.awt.Dimension;

@SuppressWarnings({"serial", "unchecked", "rawtypes"})
public class UserInterface extends JFrame {

	// cantidad de variables y cantidad de operadores logicos --> agregar

    private JButton bt_elegir;
    private JTextField comentariosT;
    private JTextField complejidadT;
    private JTextField directorioT;
    private JTextField fanInT;
    private JTextField fanOutT;
    private JEditorPane jEditorPane1;
    private JLabel jLabel10;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JMenu jMenu1;
    private javax.swing.JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JLabel lb_clases;
    private JLabel lb_codMetodo;
    private JLabel lb_codMetodo1;
    private JLabel lb_metodos;
    private JLabel lb_titulo;
    private JLabel lb_ubicacion;
    private JTextField longitudT;
    private JList lt_clases;
    private JList lt_metodos;
    private JTextPane txt_codMetodo;
    private JTextField volumenT;
    private JTextField LineasComentarios;
    private JTextField LineasCodigo;
    Directorio directorio;

    public UserInterface() {
        super("Gestión de Testing");
        initComponents();
    }

	private void initComponents() {

        jScrollPane4 = new JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jMenu1 = new JMenu();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new JScrollPane();
        jScrollPane3.setBounds(23, 355, 638, 220);
        txt_codMetodo = new JTextPane();
        lb_codMetodo = new JLabel();
        lb_codMetodo.setBounds(23, 334, 329, 15);
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setBounds(23, 163, 307, 165);
        lt_clases = new JList();
        lb_clases = new JLabel();
        lb_clases.setBounds(23, 138, 50, 19);
        lb_ubicacion = new JLabel();
        lb_ubicacion.setBounds(23, 59, 76, 24);
        directorioT = new JTextField();
        directorioT.setBounds(23, 89, 590, 33);
        jScrollPane2 = new JScrollPane();
        jScrollPane2.setBounds(349, 163, 318, 165);
        lt_metodos = new JList();
        lb_metodos = new JLabel();
        lb_metodos.setBounds(349, 138, 67, 19);
        bt_elegir = new javax.swing.JButton();
        bt_elegir.setBounds(619, 85, 42, 37);
        lb_titulo = new JLabel();
        lb_titulo.setBounds(121, 8, 460, 41);
        volumenT = new JTextField();
        volumenT.setBounds(375, 745, 61, 26);
        longitudT = new JTextField();
        longitudT.setBounds(375, 707, 61, 26);
        fanOutT = new JTextField();
        fanOutT.setBounds(375, 669, 61, 26);
        fanInT = new JTextField();
        fanInT.setBounds(375, 631, 61, 26);
        complejidadT = new JTextField();
        complejidadT.setBounds(38, 745, 61, 26);
        comentariosT = new JTextField();
        comentariosT.setColumns(10);
        comentariosT.setBounds(38, 707, 61, 26);
        jLabel5 = new JLabel();
        jLabel5.setBounds(111, 710, 241, 19);
        jLabel5.setForeground(new Color(255, 255, 255));
        jLabel6 = new JLabel();
        jLabel6.setBounds(108, 748, 214, 19);
        jLabel6.setForeground(new Color(255, 255, 255));
        jLabel7 = new JLabel();
        jLabel7.setForeground(new Color(255, 255, 255));
        jLabel7.setBounds(448, 636, 50, 15);
        jLabel8 = new JLabel();
        jLabel8.setBounds(448, 672, 215, 19);
        jLabel8.setForeground(new Color(255, 255, 255));
        jLabel9 = new JLabel();
        jLabel9.setBounds(448, 710, 63, 19);
        jLabel9.setForeground(new Color(255, 255, 255));
        jLabel10 = new JLabel();
        jLabel10.setBounds(448, 748, 105, 19);
        jLabel10.setForeground(new Color(255, 255, 255));
        lb_codMetodo1 = new JLabel();
        lb_codMetodo1.setBounds(205, 595, 286, 24);

        jScrollPane4.setViewportView(jEditorPane1);

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel2.setBackground(new Color(46, 139, 87));
        jPanel2.setPreferredSize(new Dimension(682, 750));

        txt_codMetodo.setEditable(false);
        txt_codMetodo.setContentType("text/html");
        jScrollPane3.setViewportView(txt_codMetodo);

        lb_codMetodo.setFont(new java.awt.Font("Tahoma", 1, 12));
        lb_codMetodo.setText("Código del método ");

        lt_clases.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lt_clasesMouseClicked(evt);
            }
        });
        lt_clases.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lt_clasesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lt_clasesKeyTyped(evt);
            }
        });
        lt_clases.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lt_clasesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lt_clases);

        lb_clases.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        lb_clases.setText("Clases");

        lb_ubicacion.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        lb_ubicacion.setText("Ubicación");

        directorioT.setHorizontalAlignment(JTextField.LEFT);
        directorioT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        directorioT.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        directorioT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directorioTActionPerformed(evt);
            }
        });
        directorioT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                directorioTKeyPressed(evt);
            }
        });

        lt_metodos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lt_metodosMouseClicked(evt);
            }
        });
        lt_metodos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lt_metodosKeyReleased(evt);
            }
        });
        lt_metodos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lt_metodosValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lt_metodos);

        lb_metodos.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        lb_metodos.setText("Métodos");

        bt_elegir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow.png")));
        bt_elegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_elegirActionPerformed(evt);
            }
        });

        lb_titulo.setBackground(new java.awt.Color(0, 153, 153));
        lb_titulo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 30));
        lb_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_titulo.setText("GESTIÓN DE TESTING");

        volumenT.setFont(new java.awt.Font("Tahoma", 1, 13));
        volumenT.setHorizontalAlignment(JTextField.CENTER);
        volumenT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        volumenT.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        volumenT.setEnabled(false);
        volumenT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volumenTActionPerformed(evt);
            }
        });

        longitudT.setFont(new java.awt.Font("Tahoma", 1, 13));
        longitudT.setHorizontalAlignment(JTextField.CENTER);
        longitudT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        longitudT.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        longitudT.setEnabled(false);
        longitudT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                longitudTActionPerformed(evt);
            }
        });

        fanOutT.setFont(new java.awt.Font("Tahoma", 1, 13));
        fanOutT.setHorizontalAlignment(JTextField.CENTER);
        fanOutT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        fanOutT.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        fanOutT.setEnabled(false);
        fanOutT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fanOutTActionPerformed(evt);
            }
        });

        fanInT.setFont(new java.awt.Font("Tahoma", 1, 13));
        fanInT.setHorizontalAlignment(JTextField.CENTER);
        fanInT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        fanInT.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        fanInT.setEnabled(false);
        fanInT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fanInTActionPerformed(evt);
            }
        });

        complejidadT.setEditable(false);
        complejidadT.setBackground(new java.awt.Color(255, 255, 255));
        complejidadT.setFont(new java.awt.Font("Tahoma", 1, 13));
        complejidadT.setHorizontalAlignment(JTextField.CENTER);
        complejidadT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        complejidadT.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        complejidadT.setEnabled(false);
        complejidadT.setSelectionColor(new java.awt.Color(0, 153, 153));
        complejidadT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                complejidadTActionPerformed(evt);
            }
        });

        comentariosT.setFont(new java.awt.Font("Tahoma", 1, 13));
        comentariosT.setHorizontalAlignment(JTextField.CENTER);
        comentariosT.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        comentariosT.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        comentariosT.setEnabled(false);
        comentariosT.setSelectionColor(new java.awt.Color(0, 153, 153));
        comentariosT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comentariosTActionPerformed(evt);
            }
        });

        jLabel5.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Porcentaje de Lineas Comentadas");

        jLabel6.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Complejidad ciclomática");

        jLabel7.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Fan In");

        jLabel8.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Fan Out");

        jLabel9.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Longitud");

        jLabel10.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Volúmen");

        lb_codMetodo1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        lb_codMetodo1.setText("Análisis del Método Seleccionado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
        			.addContainerGap())
        );

        jPanel2.setLayout(null);
        jPanel2.add(lb_titulo);
        jPanel2.add(jScrollPane3);
        jPanel2.add(lb_codMetodo);
        jPanel2.add(lb_codMetodo1);
        jPanel2.add(lb_ubicacion);
        jPanel2.add(directorioT);
        jPanel2.add(bt_elegir);
        jPanel2.add(lb_clases);
        jPanel2.add(lb_metodos);
        jPanel2.add(jScrollPane1);
        jPanel2.add(jScrollPane2);
        jPanel2.add(comentariosT);
        jPanel2.add(complejidadT);
        jPanel2.add(jLabel6);
        jPanel2.add(jLabel5);
        jPanel2.add(longitudT);
        jPanel2.add(volumenT);
        jPanel2.add(fanOutT);
        jPanel2.add(fanInT);
        jPanel2.add(jLabel7);
        jPanel2.add(jLabel8);
        jPanel2.add(jLabel10);
        jPanel2.add(jLabel9);

        LineasComentarios = new JTextField();
        LineasComentarios.setDisabledTextColor(new Color(0, 0, 0));
        LineasComentarios.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        LineasComentarios.setHorizontalAlignment(SwingConstants.CENTER);
        LineasComentarios.setFont(new Font("Tahoma", Font.BOLD, 13));
        LineasComentarios.setSelectionColor(new Color(0, 153, 153));
        LineasComentarios.setBounds(38, 669, 61, 26);
        jPanel2.add(LineasComentarios);
        LineasComentarios.setColumns(10);

        LineasCodigo = new JTextField();
        LineasCodigo.setSelectionColor(new Color(0, 153, 153));
        LineasCodigo.setHorizontalAlignment(SwingConstants.CENTER);
        LineasCodigo.setFont(new Font("Tahoma", Font.BOLD, 13));
        LineasCodigo.setDisabledTextColor(Color.BLACK);
        LineasCodigo.setColumns(10);
        LineasCodigo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        LineasCodigo.setBounds(38, 631, 61, 26);
        jPanel2.add(LineasCodigo);

        JLabel lblLineasDeComentarios = new JLabel();
        lblLineasDeComentarios.setText("Lineas de Comentarios");
        lblLineasDeComentarios.setHorizontalAlignment(SwingConstants.LEFT);
        lblLineasDeComentarios.setForeground(Color.WHITE);
        lblLineasDeComentarios.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblLineasDeComentarios.setBounds(108, 672, 241, 19);
        jPanel2.add(lblLineasDeComentarios);

        JLabel lblLineasDeCdigo = new JLabel();
        lblLineasDeCdigo.setText("Lineas de Código");
        lblLineasDeCdigo.setHorizontalAlignment(SwingConstants.LEFT);
        lblLineasDeCdigo.setForeground(Color.WHITE);
        lblLineasDeCdigo.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
        lblLineasDeCdigo.setBounds(108, 636, 241, 19);
        jPanel2.add(lblLineasDeCdigo);
        getContentPane().setLayout(layout);

        pack();
    }

    private void bt_elegirActionPerformed(java.awt.event.ActionEvent evt) {
		JFileChooser chooser = new JFileChooser( new File(".") )
        {
            @Override
            public void approveSelection()
            {
                if (getSelectedFile().isFile() && !getSelectedFile().getName().endsWith(".java")){
                    JOptionPane.showMessageDialog(this, "Sólo pueden seleccionarse archivos java o directorios ", getTitle(), JOptionPane.ERROR_MESSAGE);
                }
                else{
                    super.approveSelection();
                }
            }

            private String getTitle() {
                throw new UnsupportedOperationException("Aun no soportado");
            }
        };
        chooser.setFileSelectionMode( JFileChooser.FILES_AND_DIRECTORIES );
        int devuelto = chooser.showOpenDialog(this);
        if( devuelto == JFileChooser.APPROVE_OPTION ){
            directorioT.setText(chooser.getSelectedFile().getPath());
        }
        if( devuelto != JFileChooser.CANCEL_OPTION ){
            elegirDirectorio();
        }
    }

    private void lt_clasesMouseClicked(java.awt.event.MouseEvent evt) {
        evaluarCambioClase();
    }

    private void lt_clasesKeyTyped(java.awt.event.KeyEvent evt) { }

    private void lt_clasesKeyReleased(java.awt.event.KeyEvent evt) {
        evaluarCambioClase();
    }

    private void lt_metodosMouseClicked(java.awt.event.MouseEvent evt) {
        evaluarCambioMetodo();
    }

    private void lt_metodosKeyReleased(java.awt.event.KeyEvent evt) {
        evaluarCambioMetodo();
    }

    private void comentariosTActionPerformed(java.awt.event.ActionEvent evt) { }

    private void directorioTKeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            elegirDirectorio();
        }
    }

    private void complejidadTActionPerformed(java.awt.event.ActionEvent evt) { }

    private void fanInTActionPerformed(java.awt.event.ActionEvent evt) { }

    private void fanOutTActionPerformed(java.awt.event.ActionEvent evt) { }

    private void lt_clasesValueChanged(javax.swing.event.ListSelectionEvent evt) {
        this.evaluarCambioClase();
    }

    private void lt_metodosValueChanged(javax.swing.event.ListSelectionEvent evt) {
        this.evaluarCambioMetodo();
    }

    private void longitudTActionPerformed(java.awt.event.ActionEvent evt) { }

    private void volumenTActionPerformed(java.awt.event.ActionEvent evt) { }

    private void directorioTActionPerformed(java.awt.event.ActionEvent evt) { }

    //tratamiento de la eleccion del directorio elegido
    private void elegirDirectorio(){
        String path = directorioT.getText();
        List<String> nombres = new ArrayList<>();
        try{
            directorio = DirectorioService.leerDirectorio(path);
            for( Archivo archivo : DirectorioService.getAllArchivos(directorio) ){
                for( Clase clase : archivo.getClases() ){
                    nombres.add(clase.getNombre());
                }
            }
            lt_clases.requestFocus();
            if(nombres.isEmpty()){
                JOptionPane.showMessageDialog(this, "El directorio seleccionado no contiene ningún archivo de código Java", getTitle(), JOptionPane.ERROR_MESSAGE);
                directorioT.requestFocus();
            }
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(this, "El directorio seleccionado no es válido", getTitle(), JOptionPane.ERROR_MESSAGE);
            directorioT.requestFocus();
        }
        cargarListaClases(nombres);
        if(nombres.isEmpty()){
            resetCodigo();
            cargarListamMetodos(new ArrayList());
        }
    }

    private void evaluarCambioMetodo(){
        Metodo metodoSeleccionado = getMetodoSeleccionado();
        if( metodoSeleccionado != null ){
            mostrarMetodo(metodoSeleccionado);
        }else{
            resetCodigo();
        }
    }

    //mostrar metodo
    private void mostrarMetodo( Metodo metodoSeleccionado ){
        txt_codMetodo.setText(Auxiliares.getFormatedCode(metodoSeleccionado.getCodigo().getCodigo()));
        txt_codMetodo.setCaretPosition(0);
        setTituloCodigo(metodoSeleccionado.getNombre());
        Estadisticas estadisticas = metodoSeleccionado.getEstadisticas();
        DecimalFormat df = new DecimalFormat("0.00");
        comentariosT.setText(df.format(estadisticas.getPorcentajeComentarios()));
        complejidadT.setText(estadisticas.getComplejidad() + "");
        fanInT.setText(estadisticas.getFanIn() + "");
        fanOutT.setText(estadisticas.getFanOut() + "");
        longitudT.setText(estadisticas.getLongitud()+ "");
        LineasCodigo.setText(estadisticas.getLineasTotales() + "");
        LineasComentarios.setText(estadisticas.getLineasComentadas() + "");
        volumenT.setText(String.format("%.2f",estadisticas.getVolumen()));
    }

    private void setTituloCodigo( String titulo ){
        lb_codMetodo.setText("Código del método " + titulo);
    }

    private void resetCodigo(){
        txt_codMetodo.setText("");
        comentariosT.setText("");
        complejidadT.setText("");
        fanInT.setText("");
        fanOutT.setText("");
        LineasCodigo.setText("");
        LineasComentarios.setText("");
        setTituloCodigo("");
    }

    private void evaluarCambioClase(){
        Clase claseSeleccionada = getClaseSeleccionada();
        List<Metodo> nombres = new ArrayList<>();
        if( claseSeleccionada != null ){
            for( Metodo metodo : claseSeleccionada.getMetodos() ){
                nombres.add(metodo);
            }
        }
        resetCodigo();
        cargarListamMetodos(nombres);
    }

    private Metodo getMetodoSeleccionado(){
        Metodo metodoSeleccionado = (Metodo) lt_metodos.getSelectedValue();
        if( metodoSeleccionado == null ){
            return null;
        }
        Clase claseSeleccionada = getClaseSeleccionada();
        if( claseSeleccionada == null ){
            return null;
        }
        for( Metodo metodo : claseSeleccionada.getMetodos() ){
            if( metodo.getId() == metodoSeleccionado.getId() ){
                return metodo;
            }
        }
        return null;
    }

    private Clase getClaseSeleccionada(){
        String claseSeleccionada = (String) lt_clases.getSelectedValue();
        if( claseSeleccionada == null ){
            return null;
        }
        for( Archivo archivo : DirectorioService.getAllArchivos(directorio) ){
            for( Clase clase : archivo.getClases() ){
                if( clase.getNombre().equals(claseSeleccionada) ){
                    return clase;
                }
            }
        }
        return null;
    }

    private void cargarListamMetodos( List<Metodo> nombres){
        Auxiliares.cargarLista(nombres, lt_metodos);
    }

    private void cargarListaClases( List<String> nombres){
        Auxiliares.cargarLista(nombres, lt_clases);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UserInterface ui = new UserInterface();
                ui.setLocationRelativeTo(null);
                ui.setVisible(true);
            }
        });
    }
}
