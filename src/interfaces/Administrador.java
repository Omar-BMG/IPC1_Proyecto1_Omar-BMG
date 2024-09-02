/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import archivo.ArchivoBinarioInvestigador;
import archivo.ArchivoBinarioMuestra;
import ipc_quimik.Investigador;
import ipc_quimik.Muestra;
import ipc_quimik.RenderBotonVerInvestigador;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Omar
 */
public class Administrador extends javax.swing.JFrame {

    /**
     * Creates new form Administrador
     */
    public Administrador() {
        initComponents();
        setLocationRelativeTo(null);
        actualizarTabla(); //Actualizar tabla investigadores
        actualizarTablaMuestras(); //Actualizar la tabla de muestras
    }
    
    public void actualizarTablaMuestras() {
        
        //Obtenemos la columna 3 de la tabla de muestras y la gurdamos en una variable de tipo TableColumn que nos permitirá modificarla
        TableColumn columnaAcciones = tablaMuestras.getColumnModel().getColumn(3);
        columnaAcciones.setCellRenderer(new RenderBotonVerInvestigador()); //Implementamos un cellRender que dibujará valores en la columna de tipo "RenderBotonVerInvestigador".
        columnaAcciones.setCellEditor(new RenderBotonVerInvestigador()); //Implementamos un editor de los valores de las celdas de la columna de tipo "RenderBotonVerInvestigador"
        //El código anterior implementará el botón en la cuarta columna de la tabla
        
        //Ahora instanciamos el archivo binario de muestras, obtenemos las muestras y las enviamos a la tabla
        ArchivoBinarioMuestra archivo = new ArchivoBinarioMuestra();
        ArrayList<Muestra> muestras = archivo.obtenerContenido("muestras.bin"); //Recibimos en un ArrayList lo leído del binario
        
        //Lo colocamos en la tabla
        DefaultTableModel tablaModelo = (DefaultTableModel)tablaMuestras.getModel();
        tablaModelo.setRowCount(0); //Para evitar que se dupliquen las filas
        for (Muestra muestraTemp : muestras){
            tablaModelo.addRow(new Object[]{muestraTemp.getCodigo(), muestraTemp.getDescripcion(), muestraTemp.getEstado()});
        }
    }
    
    
    public void actualizarTabla(){
        ArchivoBinarioInvestigador archivoBinario = new ArchivoBinarioInvestigador();
        ArrayList<Investigador> investigadores = archivoBinario.obtenerContenido("investigadores.bin"); //Recibimos en un ArrayList lo leido del binario
        
        DefaultTableModel tablaModelo = (DefaultTableModel)tablaInvestigadores.getModel(); 
        tablaModelo.setRowCount(0); //Para evitar que se dupliquen las filas
        for (Investigador invest : investigadores) {
            tablaModelo.addRow(new Object[]{invest.getCodigo(), invest.getNombre(), invest.getGenero(), invest.getExperimentos()});
        } 
    }
    
    private void cargarArchivoCSV(){
         //Con el "JFileChooser" le damos la orden que se abra la ventana del archivo
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(this); //Con "showOpenDialog" Aparece un cuadro de diálogo de selección de archivos "cargar archivo".
        
        //Condición
        if(seleccion == JFileChooser.APPROVE_OPTION) {//Comparamos las acciones almacenadas en la variable selección, y sólo si escojió un arhivo ejecuta las siguientes instrucciones:
            String filePath = fileChooser.getSelectedFile().getAbsolutePath(); //Para obtener el Path, definimos de tipo string la variable "filePath"
            leerInvestigadoresCSV(filePath); //Llamamos la procedimiento leerInvestigadoresCSV 
            actualizarTabla(); //Llamamos al procedimiento actualizarTabla
        }
    }
    
    //Este procedimiento lee el archivo CSV y almacena en el binario los nuevos investigadores
    private void leerInvestigadoresCSV(String filePath) {
        boolean codigoExiste = false; //Inicializamos esta variable que más adelante servirá para validaciones
        ArchivoBinarioInvestigador archivo = new ArchivoBinarioInvestigador(); //instanciamos el arhivo binario
        ArrayList<Investigador> investigadores_actuales = archivo.obtenerContenido("investigadores.bin"); //Creamos un arreglo para guardar los invest que ya tenga el binario
        ArrayList<Investigador> investigadores = new ArrayList<>(); //Creamos un arreglo vacío donde se almacenarán los nuevos investigadoress 
        //Haremos un bufferReader porque al final es similar al archivo binario, también debe ser leído el CSV
        try(BufferedReader br = new BufferedReader (new FileReader(filePath))) {
            String linea; //Variable que representará cada línea del CSV
            br.readLine(); //Saltamos la primera línea por ser el encabezado
            while ((linea = br.readLine()) != null) {  //A línea le asignamos lo que lea el buffer del CSV mientras no esté vacía la línea se ejecutarán las acciones.
                String [] partes = linea.split(","); //Creamos un array de Strings llamado "partes",le asginamos lo leìdo en "línea" y con el "slipt" indicamos que el separador es una coma. Cada dato se posicionarà en el arreglo iniciando el la posición cero.
                String codigo = partes[0].trim();//El codigo del Investigador será la posición 0 del arreglo
                String nombre = partes[1].trim();//El nombre del Investigador es la posición 1 del arreglo.
                String genero = partes[2].trim();// El genero del Investigador es la posición 2 del arreglo
                int experimentos = Integer.parseInt(partes[3].trim());//La cantidad de experimentos será la posición 3 del arreglo, lo convertimos de String a entero.
                String contrasenia = partes[4].trim();//La contraseña será la posición 4 del arreglo.
                
                
                if(investigadores_actuales.size()==0) { //Si el binario no tiene investigadores, ejecuta esto
                    //Ahora usamos el arraylist "investigadores" y almacenamos un nuevo investigador y le enviamos al constructor los datos obtenidos.
                    investigadores.add(new Investigador(codigo, nombre, genero, contrasenia, experimentos)); //Respetamos el orden del constructor de la clase Investigador
                }
                else if((codigoExiste = validarCodigoCSV(investigadores_actuales,codigo))){    
                } else{
                    investigadores.add(new Investigador(codigo, nombre, genero, contrasenia, experimentos)); //Respetamos el orden del constructor de la clase Investigador
                } 
            }
            
            //Ahora recorremos el arreglo "investigadores" y finalmente vamos almacenando en el binario todos los investigadores obtenidos
            for (Investigador invest:investigadores) {
                archivo.agregarContenido("investigadores.bin", new Investigador(invest.getCodigo(), invest.getNombre(), invest.getGenero(), invest.getContrasenia(), invest.getExperimentos()));
            }
            
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }
    //Creamos una función para validar que  el código contenido en el CSV no exista ya en el binario
    private boolean validarCodigoCSV (ArrayList<Investigador> investigadores_actuales, String codigo) {
        boolean respuesta = false;
        for (Investigador invest: investigadores_actuales){
            if(invest.getCodigo().equals(codigo)){
                respuesta = true;
            }
        }
        return respuesta;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelInvestigadores = new javax.swing.JPanel();
        btnCrearInvestigadores = new javax.swing.JButton();
        btnActualizarInvestigadores = new javax.swing.JButton();
        btnCargarInvestigadores = new javax.swing.JButton();
        btnEliminarInvestigadores = new javax.swing.JButton();
        panelTablaInvestigadores = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInvestigadores = new javax.swing.JTable();
        panelMuestras = new javax.swing.JPanel();
        panelTablaMuestras = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaMuestras = new javax.swing.JTable();
        btnCrearMuestras = new javax.swing.JButton();
        btnCargarMuestras = new javax.swing.JButton();
        panelAsignarExperimentos = new javax.swing.JPanel();
        labelAsignarInvestigador = new javax.swing.JLabel();
        labelAsignarMuestra = new javax.swing.JLabel();
        comboboxInvestigadores = new javax.swing.JComboBox<>();
        comboboxMuestras = new javax.swing.JComboBox<>();
        btnAsignarExperimento = new javax.swing.JButton();
        panelPatrones = new javax.swing.JPanel();
        panelTablaPatrones = new javax.swing.JPanel();
        btnCrearPatrones = new javax.swing.JButton();
        btnCargarPatrones = new javax.swing.JButton();
        btnEliminarPatrones = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador - IPC Quimik");

        panelInvestigadores.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnCrearInvestigadores.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnCrearInvestigadores.setText("Crear");
        btnCrearInvestigadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearInvestigadoresActionPerformed(evt);
            }
        });

        btnActualizarInvestigadores.setFont(new java.awt.Font("Segoe UI Black", 1, 10)); // NOI18N
        btnActualizarInvestigadores.setText("Actualizar");
        btnActualizarInvestigadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarInvestigadoresActionPerformed(evt);
            }
        });

        btnCargarInvestigadores.setFont(new java.awt.Font("Segoe UI Black", 1, 15)); // NOI18N
        btnCargarInvestigadores.setText("Cargar");
        btnCargarInvestigadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarInvestigadoresActionPerformed(evt);
            }
        });

        btnEliminarInvestigadores.setFont(new java.awt.Font("Segoe UI Black", 1, 13)); // NOI18N
        btnEliminarInvestigadores.setText("Eliminar");
        btnEliminarInvestigadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarInvestigadoresActionPerformed(evt);
            }
        });

        tablaInvestigadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Género", "Experimentos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaInvestigadores);

        javax.swing.GroupLayout panelTablaInvestigadoresLayout = new javax.swing.GroupLayout(panelTablaInvestigadores);
        panelTablaInvestigadores.setLayout(panelTablaInvestigadoresLayout);
        panelTablaInvestigadoresLayout.setHorizontalGroup(
            panelTablaInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaInvestigadoresLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        panelTablaInvestigadoresLayout.setVerticalGroup(
            panelTablaInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaInvestigadoresLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelInvestigadoresLayout = new javax.swing.GroupLayout(panelInvestigadores);
        panelInvestigadores.setLayout(panelInvestigadoresLayout);
        panelInvestigadoresLayout.setHorizontalGroup(
            panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInvestigadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTablaInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnActualizarInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrearInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCargarInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelInvestigadoresLayout.setVerticalGroup(
            panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInvestigadoresLayout.createSequentialGroup()
                .addGroup(panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInvestigadoresLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(panelTablaInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInvestigadoresLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCargarInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnActualizarInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Investigadores", panelInvestigadores);

        panelMuestras.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablaMuestras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descripción", "Estado", "Acciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaMuestras);

        javax.swing.GroupLayout panelTablaMuestrasLayout = new javax.swing.GroupLayout(panelTablaMuestras);
        panelTablaMuestras.setLayout(panelTablaMuestrasLayout);
        panelTablaMuestrasLayout.setHorizontalGroup(
            panelTablaMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 452, Short.MAX_VALUE)
            .addGroup(panelTablaMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTablaMuestrasLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        panelTablaMuestrasLayout.setVerticalGroup(
            panelTablaMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(panelTablaMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelTablaMuestrasLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        btnCrearMuestras.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        btnCrearMuestras.setText("Crear");
        btnCrearMuestras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearMuestrasActionPerformed(evt);
            }
        });

        btnCargarMuestras.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        btnCargarMuestras.setText("Cargar");

        javax.swing.GroupLayout panelMuestrasLayout = new javax.swing.GroupLayout(panelMuestras);
        panelMuestras.setLayout(panelMuestrasLayout);
        panelMuestrasLayout.setHorizontalGroup(
            panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMuestrasLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(panelTablaMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrearMuestras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCargarMuestras, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelMuestrasLayout.setVerticalGroup(
            panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMuestrasLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnCrearMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCargarMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(280, Short.MAX_VALUE))
            .addGroup(panelMuestrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTablaMuestras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Muestras", panelMuestras);

        panelAsignarExperimentos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelAsignarInvestigador.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        labelAsignarInvestigador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAsignarInvestigador.setText("Investigador");

        labelAsignarMuestra.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        labelAsignarMuestra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAsignarMuestra.setText("Muestra");

        comboboxInvestigadores.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        comboboxInvestigadores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        comboboxMuestras.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        comboboxMuestras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAsignarExperimento.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnAsignarExperimento.setText("Asignar");

        javax.swing.GroupLayout panelAsignarExperimentosLayout = new javax.swing.GroupLayout(panelAsignarExperimentos);
        panelAsignarExperimentos.setLayout(panelAsignarExperimentosLayout);
        panelAsignarExperimentosLayout.setHorizontalGroup(
            panelAsignarExperimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignarExperimentosLayout.createSequentialGroup()
                .addGroup(panelAsignarExperimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAsignarExperimentosLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(panelAsignarExperimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAsignarExperimentosLayout.createSequentialGroup()
                                .addComponent(labelAsignarInvestigador, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboboxInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAsignarExperimentosLayout.createSequentialGroup()
                                .addComponent(labelAsignarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboboxMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelAsignarExperimentosLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(btnAsignarExperimento, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        panelAsignarExperimentosLayout.setVerticalGroup(
            panelAsignarExperimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignarExperimentosLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(panelAsignarExperimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAsignarInvestigador, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelAsignarExperimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAsignarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAsignarExperimento, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Asignación de Experimentos", panelAsignarExperimentos);

        panelPatrones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panelTablaPatronesLayout = new javax.swing.GroupLayout(panelTablaPatrones);
        panelTablaPatrones.setLayout(panelTablaPatronesLayout);
        panelTablaPatronesLayout.setHorizontalGroup(
            panelTablaPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 424, Short.MAX_VALUE)
        );
        panelTablaPatronesLayout.setVerticalGroup(
            panelTablaPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 254, Short.MAX_VALUE)
        );

        btnCrearPatrones.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnCrearPatrones.setText("Crear");

        btnCargarPatrones.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnCargarPatrones.setText("Cargar");

        btnEliminarPatrones.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnEliminarPatrones.setText("Eliminar");

        javax.swing.GroupLayout panelPatronesLayout = new javax.swing.GroupLayout(panelPatrones);
        panelPatrones.setLayout(panelPatronesLayout);
        panelPatronesLayout.setHorizontalGroup(
            panelPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPatronesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(panelTablaPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrearPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargarPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelPatronesLayout.setVerticalGroup(
            panelPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPatronesLayout.createSequentialGroup()
                .addGroup(panelPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPatronesLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(panelTablaPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPatronesLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(btnCrearPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCargarPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Patrones", panelPatrones);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearInvestigadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearInvestigadoresActionPerformed
        CrearInvestigador ventanaCrearInvestigador = new CrearInvestigador(this);
        ventanaCrearInvestigador.setVisible(true);
    }//GEN-LAST:event_btnCrearInvestigadoresActionPerformed

    private void btnActualizarInvestigadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarInvestigadoresActionPerformed
        EditarInvestigador ventanaActualizar = new EditarInvestigador(this);
        ventanaActualizar.setVisible(true);
    }//GEN-LAST:event_btnActualizarInvestigadoresActionPerformed

    private void btnEliminarInvestigadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarInvestigadoresActionPerformed
        EliminarInvestigador ventanaEliminarInvestigador = new EliminarInvestigador(this);
        ventanaEliminarInvestigador.setVisible(true);
    }//GEN-LAST:event_btnEliminarInvestigadoresActionPerformed

    private void btnCargarInvestigadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarInvestigadoresActionPerformed
        cargarArchivoCSV(); //Llamamos al procedimiento "cargarArchivoCSV" que realiza todo el proceso
    }//GEN-LAST:event_btnCargarInvestigadoresActionPerformed

    private void btnCrearMuestrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearMuestrasActionPerformed
        CrearMuestra ventanaCrearMuestra = new CrearMuestra(this);
        ventanaCrearMuestra.setVisible(true);
    }//GEN-LAST:event_btnCrearMuestrasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarInvestigadores;
    private javax.swing.JButton btnAsignarExperimento;
    private javax.swing.JButton btnCargarInvestigadores;
    private javax.swing.JButton btnCargarMuestras;
    private javax.swing.JButton btnCargarPatrones;
    private javax.swing.JButton btnCrearInvestigadores;
    private javax.swing.JButton btnCrearMuestras;
    private javax.swing.JButton btnCrearPatrones;
    private javax.swing.JButton btnEliminarInvestigadores;
    private javax.swing.JButton btnEliminarPatrones;
    private javax.swing.JComboBox<String> comboboxInvestigadores;
    private javax.swing.JComboBox<String> comboboxMuestras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelAsignarInvestigador;
    private javax.swing.JLabel labelAsignarMuestra;
    private javax.swing.JPanel panelAsignarExperimentos;
    private javax.swing.JPanel panelInvestigadores;
    private javax.swing.JPanel panelMuestras;
    private javax.swing.JPanel panelPatrones;
    private javax.swing.JPanel panelTablaInvestigadores;
    private javax.swing.JPanel panelTablaMuestras;
    private javax.swing.JPanel panelTablaPatrones;
    private javax.swing.JTable tablaInvestigadores;
    private javax.swing.JTable tablaMuestras;
    // End of variables declaration//GEN-END:variables
}
