
package interfaces;

import archivo.ArchivoBinarioAsignacionExp;
import archivo.ArchivoBinarioInvestigador;
import archivo.ArchivoBinarioMuestra;
import archivo.ArchivoBinarioPatron;
import archivo.ManejoArchivotxtPlanoPatron;
import ipc_quimik.AsignacionExperimento;
import ipc_quimik.Investigador;
import ipc_quimik.Muestra;
import ipc_quimik.Patron;
import ipc_quimik.RenderBotonVerMuestra;
import ipc_quimik.RenderBotonVerPatron;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

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
        this.getContentPane().setBackground(Color.BLACK);
        actualizarTabla(); //Actualizar tabla investigadores
        actualizarTablaMuestras(); //Actualizar la tabla de muestras
        actualizarTablaPatrones();//Actualizar tabla Patrones
        
    }
    
    public void actualizarGrafica() {
        //Primero instanciamos el archivo binario donde se encuentran todos los investigadores para obtener los tres investigadores con más experimentos
        ArchivoBinarioInvestigador archivoInvestigador = new ArchivoBinarioInvestigador();
        ArrayList<Investigador> listaInvestigadores = archivoInvestigador.obtenerContenido("investigadores.bin");
        
        //Declaramos las variables donde se almacenarán los 3 investigadores con más experimentos
        Investigador Investigador1;
        Investigador Investigador2;
        Investigador Investigador3;
        
        if (listaInvestigadores.size() >= 3) { //Validamos el tamaño de la lista
            Investigador1 = listaInvestigadores.get(0); //Suponemos que el mayor investigador está en la posición cero
            int posicionInvest = 0;
            //Primer recorrido para encontrar al primer investigador con más experimentos
            for (int i = 0; i < listaInvestigadores.size(); i++) {
                if(listaInvestigadores.get(i).getExperimentos() > Investigador1.getExperimentos() ) {
                    Investigador1 = listaInvestigadores.get(i);
                    posicionInvest = i;
                }
            }//Al finalizar el ciclo se habrá obtenido al investigador con más experimentos en la lista y su posición.

            //Ahora lo eliminamos de la lista
            listaInvestigadores.remove(posicionInvest);

            //Segundo recorrido para encontrar al segundo investigador con más experimentos
            Investigador2 = listaInvestigadores.get(0); //Suponemos que el mayor investigador está en la posición cero
            posicionInvest = 0;
            for (int i = 0; i < listaInvestigadores.size(); i++) {
                if(listaInvestigadores.get(i).getExperimentos() > Investigador2.getExperimentos() ) {
                    Investigador2 = listaInvestigadores.get(i);
                    posicionInvest = i;
                }
            }//Al finalizar el ciclo se habrá obtenido al segundo investigador con más experimentos en la lista y su posición.

            //Ahora lo eliminamos de la lista
            listaInvestigadores.remove(posicionInvest);

            //Tercer recorrido para encontrar al tercer investigador con más experimentos
            Investigador3 = listaInvestigadores.get(0); //Suponemos que el mayor investigador está en la posición cero
            posicionInvest = 0;
            for (int i = 0; i < listaInvestigadores.size(); i++) {
                if(listaInvestigadores.get(i).getExperimentos() > Investigador3.getExperimentos() ) {
                    Investigador3 = listaInvestigadores.get(i);
                    posicionInvest = i;
                }
            }//Al finalizar el ciclo se habrá obtenido al tercer investigador con más experimentos en la lista y su posición.

            //---------Desarrollo del código para la gráfica ------
            DefaultCategoryDataset datos = new DefaultCategoryDataset(); //Se utilizará para establecer los valores que llevará el gráfico

            datos.setValue(Investigador1.getExperimentos(), "Experimentos", Investigador1.getCodigo()); //Datos que se colocarán en la gráfica
            datos.setValue(Investigador2.getExperimentos(), "Experimentos", Investigador2.getCodigo());
            datos.setValue(Investigador3.getExperimentos(), "Experimentos", Investigador3.getCodigo());

            //Ya teniendo estos valores en el objeto datos, crearemos la gráfica
            JFreeChart graficaTopInvestigadores = ChartFactory.createBarChart3D ( //Para que sean barras en 3d usamos el metodo createBarChart3D
            "Top 3 Investigadores ",      //Nombre del grafico
            "Investigadores",                                 //nombre de las barras o columnas
            "Experimentos",                                     //nombre de la numeracion
            datos,                                          //datos del grafico
            PlotOrientation.VERTICAL,                       //orientacion
            true,                                       //leyenda de varras individuales por color
            true,                                       //herramientas
            false                                       //url del grafico
            );

            //El gráfico ya está creado, ahora debemos poner en el panel correspondiente
            ChartPanel panel = new ChartPanel(graficaTopInvestigadores);
            panel.setMouseWheelEnabled(true); //Que se habilite la rueda del ratón
            panel.setPreferredSize(new Dimension(290,185));

            panelGrafica.setLayout(new BorderLayout());
            panelGrafica.add(panel,BorderLayout.NORTH);

            pack();
        } else if(listaInvestigadores.size() == 2) {
                Investigador1 = listaInvestigadores.get(0); //Suponemos que el mayor investigador está en la posición cero
                int posicionInvest = 0;
                //Primer recorrido para encontrar al primer investigador con más experimentos
                for (int i = 0; i < listaInvestigadores.size(); i++) {
                if(listaInvestigadores.get(i).getExperimentos() > Investigador1.getExperimentos() ) {
                    Investigador1 = listaInvestigadores.get(i);
                    posicionInvest = i;
                }
                }//Al finalizar el ciclo se habrá obtenido al investigador con más experimentos en la lista y su posición.

                //Ahora lo eliminamos de la lista
                listaInvestigadores.remove(posicionInvest);

                //segundo investigador con más experimentos
                Investigador2 = listaInvestigadores.get(0); 
                
                

                //---------Desarrollo del código para la gráfica ------
                DefaultCategoryDataset datos = new DefaultCategoryDataset(); //Se utilizará para establecer los valores que llevará el gráfico

                datos.setValue(Investigador1.getExperimentos(), "Experimentos", Investigador1.getCodigo()); //Datos que se colocarán en la gráfica
                datos.setValue(Investigador2.getExperimentos(), "Experimentos", Investigador2.getCodigo());

                //Ya teniendo estos valores en el objeto datos, crearemos la gráfica
                JFreeChart graficaTopInvestigadores = ChartFactory.createBarChart3D ( //Para que sean barras en 3d usamos el metodo createBarChart3D
                "Top 2 Investigadores ",      //Nombre del grafico
                "Investigadores",                                 //nombre de las barras o columnas
                "Experimentos",                                     //nombre de la numeracion
                datos,                                          //datos del grafico
                PlotOrientation.VERTICAL,                       //orientacion
                true,                                       //leyenda de varras individuales por color
                true,                                       //herramientas
                false                                       //url del grafico
                );

                //El gráfico ya está creado, ahora debemos poner en el panel correspondiente
                ChartPanel panel = new ChartPanel(graficaTopInvestigadores);
                panel.setMouseWheelEnabled(true); //Que se habilite la rueda del ratón
                panel.setPreferredSize(new Dimension(290,185));

                panelGrafica.setLayout(new BorderLayout());
                panelGrafica.add(panel,BorderLayout.NORTH);

                pack();
            } else if(listaInvestigadores.size()==1) {
                Investigador1 = listaInvestigadores.get(0); //Suponemos que el mayor investigador está en la posición cero
                

                //---------Desarrollo del código para la gráfica ------
                DefaultCategoryDataset datos = new DefaultCategoryDataset(); //Se utilizará para establecer los valores que llevará el gráfico

                datos.setValue(Investigador1.getExperimentos(), "Experimentos", Investigador1.getCodigo()); //Datos que se colocarán en la gráfica
                

                //Ya teniendo estos valores en el objeto datos, crearemos la gráfica
                JFreeChart graficaTopInvestigadores = ChartFactory.createBarChart3D ( //Para que sean barras en 3d usamos el metodo createBarChart3D
                "Top 1 Investigador ",      //Nombre del grafico
                "Investigadores",                                 //nombre de las barras o columnas
                "Experimentos",                                     //nombre de la numeracion
                datos,                                          //datos del grafico
                PlotOrientation.VERTICAL,                       //orientacion
                true,                                       //leyenda de varras individuales por color
                true,                                       //herramientas
                false                                       //url del grafico
                );

                //El gráfico ya está creado, ahora debemos poner en el panel correspondiente
                ChartPanel panel = new ChartPanel(graficaTopInvestigadores);
                panel.setMouseWheelEnabled(true); //Que se habilite la rueda del ratón
                panel.setPreferredSize(new Dimension(290,185));

                panelGrafica.setLayout(new BorderLayout());
                panelGrafica.add(panel,BorderLayout.NORTH);

                pack();
            }
    }
    
    
    public void actualizarVentanaAsignacionExp() {
        //Intanciamos los archivos binarios de los investigadores y las muestras y guardamos en ArrayLists las listas de ambos
        ArchivoBinarioInvestigador archivoInvestigador = new ArchivoBinarioInvestigador();
        ArrayList<Investigador> listaInvestigadores = archivoInvestigador.obtenerContenido("investigadores.bin");
        ArchivoBinarioMuestra archivoMuestra = new ArchivoBinarioMuestra();
        ArrayList<Muestra> listaMuestra = archivoMuestra.obtenerContenido("muestras.bin");
        
        //Recorremos el ArrayList de investigadores actuales y los añadimos en el combobox correspondiente
        for(Investigador tempInvest: listaInvestigadores) {
            if (validarContenidoComboboxInvestigadores(tempInvest.getCodigo())) { //Sólo en caso el código no exista ya en el combobox lo agregará
                comboboxInvestigadores.addItem(tempInvest.getCodigo());
            }
        }
        
        //Recorremos el ArrayList de muestras actuales y las añadimos en el combobox correspondiente
        for (Muestra tempMuest : listaMuestra){
            if (validarContenidoComboboxMuestras(tempMuest.getCodigo())) {
                comboboxMuestras.addItem(tempMuest.getCodigo());
            }
        }
    }
    
    public boolean validarContenidoComboboxInvestigadores(String codigo) {
        boolean respuesta = true;
        for (int i = 0; i < comboboxInvestigadores.getItemCount(); i++) {
            if (codigo.equals(comboboxInvestigadores.getItemAt(i))) {
                respuesta = false;
            }
        }
        return respuesta;
    }
    
    public boolean validarContenidoComboboxMuestras(String codigo) {
        boolean respuesta = true;
        for (int i = 0; i < comboboxMuestras.getItemCount(); i++) {
            if (codigo.equals(comboboxMuestras.getItemAt(i))) {
                respuesta = false;
            }
        }
        return respuesta;
    }
    
    public void eliminarContenidoComboboxInvestigador(String codigo) {
        for (int i = 0; i < comboboxInvestigadores.getItemCount(); i++) {
            if (codigo.equals(comboboxInvestigadores.getItemAt(i))) {
                comboboxInvestigadores.removeItemAt(i);
            }
        }
    }
    
    public void actualizarTablaPatrones() {
        //Obtenemos la columna 2 de la tabla de muestras y la gurdamos en una variable de tipo TableColumn que nos permitirá modificarla
        TableColumn columnaAcciones = tablaPatrones.getColumnModel().getColumn(2);
        columnaAcciones.setCellRenderer(new RenderBotonVerPatron()); //Implementamos un cellRender que dibujará valores en la columna de tipo "RenderBotonVerPatron".
        columnaAcciones.setCellEditor(new RenderBotonVerPatron()); //Implementamos un editor de los valores de las celdas de la columna de tipo "RenderBotonVerPatron"
        //El código anterior implementará el botón en la tercera columna de la tabla
        
        //Ahora instanciamos el archivo binario de muestras, obtenemos las muestras y las enviamos a la tabla
        ArchivoBinarioPatron archivo = new ArchivoBinarioPatron();
        ArrayList<Patron> patrones = archivo.obtenerContenido("patrones.bin"); //Recibimos en un ArrayList lo leído del binario
        
        //Lo colocamos en la tabla
        DefaultTableModel tablaModelo = (DefaultTableModel)tablaPatrones.getModel();
        tablaModelo.setRowCount(0); //Para evitar que se dupliquen las filas
        for (Patron patronTemp : patrones){
            tablaModelo.addRow(new Object[]{patronTemp.getCodigo(), patronTemp.getNombre()});
        }
        
    }
    
    public void actualizarTablaMuestras() {
        
        //Obtenemos la columna 3 de la tabla de muestras y la gurdamos en una variable de tipo TableColumn que nos permitirá modificarla
        TableColumn columnaAcciones = tablaMuestras.getColumnModel().getColumn(3);
        columnaAcciones.setCellRenderer(new RenderBotonVerMuestra()); //Implementamos un cellRender que dibujará valores en la columna de tipo "RenderBotonVerMuestra".
        columnaAcciones.setCellEditor(new RenderBotonVerMuestra()); //Implementamos un editor de los valores de las celdas de la columna de tipo "RenderBotonVerMuestra"
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
        
        actualizarVentanaAsignacionExp(); //Actualizamos los combobox de la pestaña Asignacion de experimentos
    }
    
    
    public void actualizarTabla(){
        ArchivoBinarioInvestigador archivoBinario = new ArchivoBinarioInvestigador();
        ArrayList<Investigador> investigadores = archivoBinario.obtenerContenido("investigadores.bin"); //Recibimos en un ArrayList lo leido del binario
        
        DefaultTableModel tablaModelo = (DefaultTableModel)tablaInvestigadores.getModel(); 
        tablaModelo.setRowCount(0); //Para evitar que se dupliquen las filas
        for (Investigador invest : investigadores) {
            tablaModelo.addRow(new Object[]{invest.getCodigo(), invest.getNombre(), invest.getGenero(), invest.getExperimentos()});
        }
        
        actualizarVentanaAsignacionExp(); //Actualizamos los combobox de la pestaña Asignacion de experimentos
        actualizarGrafica(); //Actualizamos la gráfica cada vez que se actualice la tabla de investigadores
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
        panelInvestigadores = new FondoPanel();
        btnCrearInvestigadores = new javax.swing.JButton();
        btnActualizarInvestigadores = new javax.swing.JButton();
        btnCargarInvestigadores = new javax.swing.JButton();
        btnEliminarInvestigadores = new javax.swing.JButton();
        panelTablaInvestigadores = new FondoTablaInvest();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaInvestigadores = new javax.swing.JTable();
        panelGrafica = new javax.swing.JPanel();
        panelMuestras = new FondoPanelMuestras();
        panelTablaMuestras = new FondoTablaInvest();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaMuestras = new javax.swing.JTable();
        btnCrearMuestras = new javax.swing.JButton();
        btnCargarMuestras = new javax.swing.JButton();
        panelAsignarExperimentos = new FondoPanelAsignaciones();
        labelAsignarInvestigador = new javax.swing.JLabel();
        labelAsignarMuestra = new javax.swing.JLabel();
        comboboxInvestigadores = new javax.swing.JComboBox<>();
        comboboxMuestras = new javax.swing.JComboBox<>();
        btnAsignarExperimento = new javax.swing.JButton();
        panelPatrones = new FondoPanelPatrones();
        panelTablaPatrones = new FondoTablaInvest();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPatrones = new javax.swing.JTable();
        btnCrearPatrones = new javax.swing.JButton();
        btnCargarPatrones = new javax.swing.JButton();
        btnEliminarPatrones = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador - IPC Quimik");
        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(51, 51, 51));
        setResizable(false);

        jTabbedPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Showcard Gothic", 1, 15)); // NOI18N

        btnCrearInvestigadores.setBackground(new java.awt.Color(204, 204, 204));
        btnCrearInvestigadores.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnCrearInvestigadores.setForeground(new java.awt.Color(0, 0, 0));
        btnCrearInvestigadores.setText("Crear");
        btnCrearInvestigadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearInvestigadoresActionPerformed(evt);
            }
        });

        btnActualizarInvestigadores.setBackground(new java.awt.Color(204, 204, 204));
        btnActualizarInvestigadores.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        btnActualizarInvestigadores.setForeground(new java.awt.Color(0, 0, 0));
        btnActualizarInvestigadores.setText("Actualizar");
        btnActualizarInvestigadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarInvestigadoresActionPerformed(evt);
            }
        });

        btnCargarInvestigadores.setBackground(new java.awt.Color(204, 204, 204));
        btnCargarInvestigadores.setFont(new java.awt.Font("Segoe UI Black", 1, 15)); // NOI18N
        btnCargarInvestigadores.setForeground(new java.awt.Color(0, 0, 0));
        btnCargarInvestigadores.setText("Cargar");
        btnCargarInvestigadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarInvestigadoresActionPerformed(evt);
            }
        });

        btnEliminarInvestigadores.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminarInvestigadores.setFont(new java.awt.Font("Segoe UI Black", 1, 16)); // NOI18N
        btnEliminarInvestigadores.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminarInvestigadores.setText("Eliminar");
        btnEliminarInvestigadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarInvestigadoresActionPerformed(evt);
            }
        });

        tablaInvestigadores.setBackground(new java.awt.Color(102, 102, 102));
        tablaInvestigadores.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tablaInvestigadores.setForeground(new java.awt.Color(255, 255, 255));
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelGraficaLayout = new javax.swing.GroupLayout(panelGrafica);
        panelGrafica.setLayout(panelGraficaLayout);
        panelGraficaLayout.setHorizontalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 185, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelInvestigadoresLayout = new javax.swing.GroupLayout(panelInvestigadores);
        panelInvestigadores.setLayout(panelInvestigadoresLayout);
        panelInvestigadoresLayout.setHorizontalGroup(
            panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInvestigadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTablaInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInvestigadoresLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInvestigadoresLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCrearInvestigadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnActualizarInvestigadores, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCargarInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminarInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        panelInvestigadoresLayout.setVerticalGroup(
            panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInvestigadoresLayout.createSequentialGroup()
                .addGroup(panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInvestigadoresLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCrearInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCargarInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInvestigadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnActualizarInvestigadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarInvestigadores, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInvestigadoresLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(panelTablaInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Investigadores", panelInvestigadores);

        panelMuestras.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablaMuestras.setBackground(new java.awt.Color(102, 102, 102));
        tablaMuestras.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tablaMuestras.setForeground(new java.awt.Color(255, 255, 255));
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
            .addGroup(panelTablaMuestrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTablaMuestrasLayout.setVerticalGroup(
            panelTablaMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaMuestrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        btnCrearMuestras.setBackground(new java.awt.Color(204, 204, 204));
        btnCrearMuestras.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        btnCrearMuestras.setForeground(new java.awt.Color(0, 0, 0));
        btnCrearMuestras.setText("Crear");
        btnCrearMuestras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearMuestrasActionPerformed(evt);
            }
        });

        btnCargarMuestras.setBackground(new java.awt.Color(204, 204, 204));
        btnCargarMuestras.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        btnCargarMuestras.setForeground(new java.awt.Color(0, 0, 0));
        btnCargarMuestras.setText("Cargar");
        btnCargarMuestras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarMuestrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMuestrasLayout = new javax.swing.GroupLayout(panelMuestras);
        panelMuestras.setLayout(panelMuestrasLayout);
        panelMuestrasLayout.setHorizontalGroup(
            panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMuestrasLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(panelTablaMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMuestrasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCrearMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMuestrasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                        .addComponent(btnCargarMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panelMuestrasLayout.setVerticalGroup(
            panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMuestrasLayout.createSequentialGroup()
                .addGroup(panelMuestrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMuestrasLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnCrearMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCargarMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMuestrasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelTablaMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Muestras", panelMuestras);

        panelAsignarExperimentos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelAsignarInvestigador.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        labelAsignarInvestigador.setForeground(new java.awt.Color(255, 255, 255));
        labelAsignarInvestigador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAsignarInvestigador.setText("Investigador");

        labelAsignarMuestra.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        labelAsignarMuestra.setForeground(new java.awt.Color(255, 255, 255));
        labelAsignarMuestra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAsignarMuestra.setText("Muestra");

        comboboxInvestigadores.setBackground(new java.awt.Color(102, 102, 102));
        comboboxInvestigadores.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        comboboxInvestigadores.setForeground(new java.awt.Color(255, 255, 255));

        comboboxMuestras.setBackground(new java.awt.Color(102, 102, 102));
        comboboxMuestras.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        comboboxMuestras.setForeground(new java.awt.Color(255, 255, 255));

        btnAsignarExperimento.setBackground(new java.awt.Color(204, 204, 204));
        btnAsignarExperimento.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        btnAsignarExperimento.setForeground(new java.awt.Color(0, 0, 0));
        btnAsignarExperimento.setText("Asignar");
        btnAsignarExperimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarExperimentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAsignarExperimentosLayout = new javax.swing.GroupLayout(panelAsignarExperimentos);
        panelAsignarExperimentos.setLayout(panelAsignarExperimentosLayout);
        panelAsignarExperimentosLayout.setHorizontalGroup(
            panelAsignarExperimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignarExperimentosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboboxInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(comboboxMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAsignarExperimentosLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(labelAsignarInvestigador, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelAsignarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
            .addGroup(panelAsignarExperimentosLayout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(btnAsignarExperimento, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAsignarExperimentosLayout.setVerticalGroup(
            panelAsignarExperimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAsignarExperimentosLayout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addGroup(panelAsignarExperimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAsignarMuestra, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAsignarInvestigador, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAsignarExperimentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboboxInvestigadores, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboboxMuestras, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAsignarExperimento, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Asignación de Experimentos", panelAsignarExperimentos);

        panelPatrones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tablaPatrones.setBackground(new java.awt.Color(102, 102, 102));
        tablaPatrones.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        tablaPatrones.setForeground(new java.awt.Color(255, 255, 255));
        tablaPatrones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Acciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaPatrones);

        javax.swing.GroupLayout panelTablaPatronesLayout = new javax.swing.GroupLayout(panelTablaPatrones);
        panelTablaPatrones.setLayout(panelTablaPatronesLayout);
        panelTablaPatronesLayout.setHorizontalGroup(
            panelTablaPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaPatronesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTablaPatronesLayout.setVerticalGroup(
            panelTablaPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaPatronesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        btnCrearPatrones.setBackground(new java.awt.Color(204, 204, 204));
        btnCrearPatrones.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnCrearPatrones.setForeground(new java.awt.Color(0, 0, 0));
        btnCrearPatrones.setText("Crear");
        btnCrearPatrones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPatronesActionPerformed(evt);
            }
        });

        btnCargarPatrones.setBackground(new java.awt.Color(204, 204, 204));
        btnCargarPatrones.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnCargarPatrones.setForeground(new java.awt.Color(0, 0, 0));
        btnCargarPatrones.setText("Cargar");
        btnCargarPatrones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarPatronesActionPerformed(evt);
            }
        });

        btnEliminarPatrones.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminarPatrones.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        btnEliminarPatrones.setForeground(new java.awt.Color(0, 0, 0));
        btnEliminarPatrones.setText("Eliminar");
        btnEliminarPatrones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPatronesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPatronesLayout = new javax.swing.GroupLayout(panelPatrones);
        panelPatrones.setLayout(panelPatronesLayout);
        panelPatronesLayout.setHorizontalGroup(
            panelPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPatronesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTablaPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPatronesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panelPatronesLayout.createSequentialGroup()
                        .addGroup(panelPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPatronesLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCrearPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelPatronesLayout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(btnCargarPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(107, Short.MAX_VALUE))))
        );
        panelPatronesLayout.setVerticalGroup(
            panelPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPatronesLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(panelPatronesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelPatronesLayout.createSequentialGroup()
                        .addComponent(btnCrearPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCargarPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPatronesLayout.createSequentialGroup()
                        .addComponent(panelTablaPatrones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );

        jTabbedPane1.addTab("Patrones", panelPatrones);

        btnCerrarSesion.setBackground(new java.awt.Color(102, 102, 102));
        btnCerrarSesion.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSesion.setText("Cerrar sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jTabbedPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
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

    private void btnCargarMuestrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarMuestrasActionPerformed
        //Con el "JFileChooser" le damos la orden que se abra la ventana del archivo
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(this); //Con "showOpenDialog" Aparece un cuadro de diálogo de selección de archivos "cargar archivo".
        
        //Condición
        if(seleccion == JFileChooser.APPROVE_OPTION) {//Comparamos las acciones almacenadas en la variable selección, y sólo si escojió un arhivo ejecuta las siguientes instrucciones:
            String filePath = fileChooser.getSelectedFile().getAbsolutePath(); //Para obtener el Path, definimos de tipo string la variable "filePath"
            leerMuestrasMasivasCSV(filePath); //Llamamos la procedimiento leerInvestigadoresCSV, le enviamos la ruta del archivo
            actualizarTablaMuestras(); //Llamamos al procedimiento actualizarTabla
        }
        
    }//GEN-LAST:event_btnCargarMuestrasActionPerformed

    private void btnCrearPatronesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPatronesActionPerformed
        CrearPatron ventanaCrearPatron = new CrearPatron(this);
        ventanaCrearPatron.setVisible(true);
    }//GEN-LAST:event_btnCrearPatronesActionPerformed
    
    private void btnCargarPatronesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarPatronesActionPerformed
        //Con el "JFileChooser" le damos la orden que se abra la ventana del archivo
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(this); //Con "showOpenDialog" Aparece un cuadro de diálogo de selección de archivos "cargar archivo".
        
        //Condición
        if(seleccion == JFileChooser.APPROVE_OPTION) {//Comparamos las acciones almacenadas en la variable selección, y sólo si escojió un arhivo ejecuta las siguientes instrucciones:
            String filePath = fileChooser.getSelectedFile().getAbsolutePath(); //Para obtener el Path, definimos de tipo string la variable "filePath"
            leerPatronesMasivosCSV(filePath); //Llamamos la procedimiento leerPatronesMasivosCSV, le enviamos la ruta del archivo
            actualizarTablaPatrones(); //Llamamos al procedimiento actualizarTablaPatrones
        }
    }//GEN-LAST:event_btnCargarPatronesActionPerformed

    private void btnEliminarPatronesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPatronesActionPerformed
        EliminarPatron ventanaEliminarPatron = new EliminarPatron(this);
        ventanaEliminarPatron.setVisible(true);
    }//GEN-LAST:event_btnEliminarPatronesActionPerformed

    private void btnAsignarExperimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarExperimentoActionPerformed
        //Intanciamos el archivo binario de la clase "AsignacionExperimento" y guardamos en un arrayList las asignaciones existentes
        ArchivoBinarioAsignacionExp archivoAsignaciones = new ArchivoBinarioAsignacionExp();
        ArrayList<AsignacionExperimento> asignacionesActuales = archivoAsignaciones.obtenerContenido("asignaciones.bin");
        
        //Instanciamos el archivo binario de las muestras
        ArchivoBinarioMuestra archivoMuestras = new ArchivoBinarioMuestra();
        
        boolean resultado = false;
        
        //Guardamos las selecciones de los combobox, las obtendremos de tipo "Objeto" por lo que debemos castear a String
        String codigoMuestraSelect = (String)comboboxMuestras.getSelectedItem();
        String codigoInvestigadorSelect = (String)comboboxInvestigadores.getSelectedItem();
        if (asignacionesActuales.size()==0) {
            archivoAsignaciones.agregarContenido("asignaciones.bin", new AsignacionExperimento(codigoMuestraSelect, codigoInvestigadorSelect ) ); //Agregamos la nueva asignación al archivo binario
            archivoMuestras.modificarEstadoMuestra("muestras.bin", codigoMuestraSelect, "En proceso"); //Modificamos el estado de la muestra
            actualizarTablaMuestras(); //Actualizamos la tabla de las muestras
            JOptionPane.showMessageDialog(null, "Asignación realizada correctamente");
        } else {
            for (AsignacionExperimento tempAsignacion: asignacionesActuales) {
                if(validarMuestraDisponible(archivoMuestras, codigoMuestraSelect)) {
                  //anomalías en esta línea, los mensajes se repetían en bucle.  
                } else {
                    archivoAsignaciones.agregarContenido("asignaciones.bin", new AsignacionExperimento(codigoMuestraSelect, codigoInvestigadorSelect ) ); //Agregamos la nueva asignación al archivo binario
                    archivoMuestras.modificarEstadoMuestra("muestras.bin", codigoMuestraSelect, "En proceso"); //Modificamos el estado de la muestra
                    actualizarTablaMuestras(); //Actualizamos la tabla de las muestras
                    resultado = true; //Sólo asigna verdadero cuando se ejecuta esta parte, es decir, el código no existe.
                }
            }
            if(resultado){
                JOptionPane.showMessageDialog(null, "Asignación realizada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "Muestra no disponible, ya fue asignada");
            }  
        }
    }//GEN-LAST:event_btnAsignarExperimentoActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        Login ventanaLogin = new Login();
        ventanaLogin.setVisible(true);
        dispose(); //Cerramos esta ventna
    }//GEN-LAST:event_btnCerrarSesionActionPerformed
    
    public boolean validarMuestraDisponible(ArchivoBinarioMuestra archivoMuestras, String codigoMuestraSelect) {
        boolean respuesta = false;
        String EstadoBuscado= "En proceso";
        ArrayList<Muestra> muestrasActuales = archivoMuestras.obtenerContenido("muestras.bin");
        for (Muestra tempMuestra: muestrasActuales){
            if(tempMuestra.getCodigo().equals(codigoMuestraSelect)){
                if(tempMuestra.getEstado().equals(EstadoBuscado)){
                    respuesta = true;
                }
            }
        }
        
        return respuesta;
    }
    
    private void leerMuestrasMasivasCSV(String filePath) {
       boolean codigoExiste = false; //Inicializamos esta variable que más adelante servirá para validaciones
        ArchivoBinarioMuestra archivo = new ArchivoBinarioMuestra(); //instanciamos el arhivo binario
        ArrayList<Muestra> muestras_actuales = archivo.obtenerContenido("muestras.bin"); //Creamos un arreglo para guardar las muestras que ya tenga el binario
        ArrayList<Muestra> newMuestras = new ArrayList<>(); //Creamos un arreglo vacío donde se almacenarán las nuevas muestras 
        //Haremos un bufferReader porque al final es similar al archivo binario, también debe ser leído el CSV
        try(BufferedReader br = new BufferedReader (new FileReader(filePath))) {
            String linea; //Variable que representará cada línea del CSV
            br.readLine(); //Saltamos la primera línea por ser el encabezado
            while ((linea = br.readLine()) != null) {  //A línea le asignamos lo que lea el buffer del CSV mientras no esté vacía la línea se ejecutarán las acciones.
                String [] partes = linea.split(","); //Creamos un array de Strings llamado "partes",le asginamos lo leìdo en "línea" y con el "slipt" indicamos que el separador es una coma. Cada dato se posicionarà en el arreglo iniciando el la posición cero.
                String codigo = partes[0].trim();//El codigo del Investigador será la posición 0 del arreglo
                String descripcion = partes[1].trim();//El nombre del Investigador es la posición 1 del arreglo.
                String [] patron = partes[2].split(";");//Guardamos en un arreglo el patrón que se encuentra en la posición dos, añadimos un slpit ";"
                
                if(muestras_actuales.size()==0) { //Si el binario no tiene muestras, ejecuta esto
                    //Ahora usamos el arraylist "newMuestras" y almacenamos una nueva muestra y le enviamos al constructor los datos obtenidos.
                    newMuestras.add(new Muestra(codigo, descripcion, "Ingreso")); //Respetamos el orden del constructor de la clase Muestra
                    
                    //También creamos el archivo del patrón con el nombre del código de la muestra
                    crearArchivoPatronMuestra(codigo, patron); //El código servirá para nombrar el arhivo
                }
                else if((codigoExiste = validarCodigoMuestrasCSV(muestras_actuales,codigo))){    
                } else{
                    newMuestras.add(new Muestra(codigo, descripcion, "Ingreso")); //Respetamos el orden del constructor de la clase Muestra
                    crearArchivoPatronMuestra(codigo, patron);
                } 
            }
            
            //Ahora recorremos el arreglo "muestras" y finalmente vamos almacenando en el binario todas las muestras obtenidas
            for (Muestra muest: newMuestras) {
                archivo.agregarContenido("muestras.bin", new Muestra(muest.getCodigo(), muest.getDescripcion(), muest.getEstado()));
            }
            
        } catch(IOException e) {
            e.printStackTrace();
        } 
    }
    
    private void crearArchivoPatronMuestra(String codigo, String[] patron){
        String rutaPatronMuestra = "";
        rutaPatronMuestra = ("Muestra_"+codigo+".html");  //Definimos el nombre que tendrá el archivo del patrón que enviaremos al CSV
        ManejoArchivotxtPlanoPatron archivo = new ManejoArchivotxtPlanoPatron(); //Instanciamos un manejador de archivos de texto plano que creará el patrón.
        archivo.crearArchivo(rutaPatronMuestra); //Creamos el archivo vacío del patrón de la muestra
        
        int columMax = (int) sqrt(patron.length);
        String contenido = "";
        int columna = 0;
        for (int elemento = 0; elemento <= patron.length; elemento++){
            if(columna < columMax) {    //validamos que no se haya completado una fila
                if(columna != (columMax-1)) {
                    contenido = contenido+patron[elemento]+",";
                }else {
                    contenido = contenido+patron[elemento];
                }
            } else { //Si ya se completó una fila ejecuta esto
                columna = 0;
                archivo.agregarContenido(contenido, rutaPatronMuestra); //Guardamos en el archivo la fila completada
                contenido = "";
                    if(elemento != patron.length) { //Sólo si aún hay datos en el arreglo los agregamos
                    contenido = contenido+patron[elemento]+",";
                    }
            }
            columna++;
        }
    }
    
    private boolean validarCodigoMuestrasCSV (ArrayList<Muestra> muestras_actuales, String codigo) {
        boolean respuesta = false;
        for (Muestra muest: muestras_actuales){
            if(muest.getCodigo().equals(codigo)){
                respuesta = true;
            }
        }
        return respuesta;
    }
    
    private void leerPatronesMasivosCSV(String filePath) {
        boolean codigoExiste = false; //Inicializamos esta variable que más adelante servirá para validaciones
        ArchivoBinarioPatron archivo = new ArchivoBinarioPatron(); //instanciamos el arhivo binario
        ArrayList<Patron> patrones_actuales = archivo.obtenerContenido("patrones.bin"); //Creamos un arreglo para guardar los patrones que ya tenga el binario
        ArrayList<Patron> newPatrones = new ArrayList<>(); //Creamos un arreglo vacío donde se almacenarán los nuevos patrones
        //Haremos un bufferReader porque al final es similar al archivo binario, también debe ser leído el CSV
        try(BufferedReader br = new BufferedReader (new FileReader(filePath))) {
            String linea; //Variable que representará cada línea del CSV
            br.readLine(); //Saltamos la primera línea por ser el encabezado
            while ((linea = br.readLine()) != null) {  //A línea le asignamos lo que lea el buffer del CSV mientras no esté vacía la línea se ejecutarán las acciones.
                String [] partes = linea.split(","); //Creamos un array de Strings llamado "partes",le asginamos lo leìdo en "línea" y con el "slipt" indicamos que el separador es una coma. Cada dato se posicionarà en el arreglo iniciando el la posición cero.
                String codigo = partes[0].trim();//El codigo del patron será la posición 0 del arreglo
                String nombre = partes[1].trim();//El nombre del patron es la posición 1 del arreglo.
                String [] patron = partes[2].split(";");//Guardamos en un arreglo el patrón que se encuentra en la posición dos, añadimos un slpit ";"
                
                if(patrones_actuales.size()==0) { //Si el binario no tiene patrones, ejecuta esto
                    //Ahora usamos el arraylist "newPatrones" y almacenamos un nuevo patron y le enviamos al constructor los datos obtenidos.
                    newPatrones.add(new Patron(codigo, nombre)); //Respetamos el orden del constructor de la clase Patron
                    
                    //También creamos el archivo del patrón con el nombre del código del mismo
                    crearArchivoPatron(codigo, patron); //El código servirá para nombrar el arhivo
                }
                else if((codigoExiste = validarCodigoPatronCSV(patrones_actuales,codigo))){    
                } else{
                    newPatrones.add(new Patron(codigo, nombre)); //Respetamos el orden del constructor de la clase Patron
                    crearArchivoPatron(codigo, patron);
                } 
            }
            
            //Ahora recorremos el arreglo "newPatrones" y finalmente vamos almacenando en el binario todos los patrones obtenidos
            for (Patron tempPatron: newPatrones) {
                archivo.agregarContenido("patrones.bin", new Patron(tempPatron.getCodigo(), tempPatron.getNombre()));
            }
            
        } catch(IOException e) {
            e.printStackTrace();
        } 
    }
    
    private void crearArchivoPatron(String codigo, String[] patron){
        String rutaPatron = "";
        rutaPatron = ("Patrón_"+codigo+".html");  //Definimos el nombre que tendrá el archivo del patrón que enviaremos al CSV
        ManejoArchivotxtPlanoPatron archivo = new ManejoArchivotxtPlanoPatron(); //Instanciamos un manejador de archivos de texto plano que creará el patrón.
        archivo.crearArchivo(rutaPatron); //Creamos el archivo vacío del patrón de la muestra
        
        int columMax = (int) sqrt(patron.length);
        String contenido = "";
        int columna = 0;
        for (int elemento = 0; elemento <= patron.length; elemento++){
            if(columna < columMax) {    //validamos que no se haya completado una fila
                if(columna != (columMax-1)) {
                    contenido = contenido+patron[elemento]+",";
                }else {
                    contenido = contenido+patron[elemento];
                }
            } else { //Si ya se completó una fila ejecuta esto
                columna = 0;
                archivo.agregarContenido(contenido, rutaPatron); //Guardamos en el archivo la fila completada
                contenido = "";
                    if(elemento != patron.length) { //Sólo si aún hay datos en el arreglo los agregamos
                    contenido = contenido+patron[elemento]+",";
                    }
            }
            columna++;
        }
    }
    
    private boolean validarCodigoPatronCSV (ArrayList<Patron> patrones_actuales, String codigo) {
        boolean respuesta = false;
        for (Patron tempPatron: patrones_actuales){
            if(tempPatron.getCodigo().equals(codigo)){
                respuesta = true;
            }
        }
        return respuesta;
        
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarInvestigadores;
    private javax.swing.JButton btnAsignarExperimento;
    private javax.swing.JButton btnCargarInvestigadores;
    private javax.swing.JButton btnCargarMuestras;
    private javax.swing.JButton btnCargarPatrones;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnCrearInvestigadores;
    private javax.swing.JButton btnCrearMuestras;
    private javax.swing.JButton btnCrearPatrones;
    private javax.swing.JButton btnEliminarInvestigadores;
    private javax.swing.JButton btnEliminarPatrones;
    private javax.swing.JComboBox<String> comboboxInvestigadores;
    private javax.swing.JComboBox<String> comboboxMuestras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelAsignarInvestigador;
    private javax.swing.JLabel labelAsignarMuestra;
    private javax.swing.JPanel panelAsignarExperimentos;
    private javax.swing.JPanel panelGrafica;
    private javax.swing.JPanel panelInvestigadores;
    private javax.swing.JPanel panelMuestras;
    private javax.swing.JPanel panelPatrones;
    private javax.swing.JPanel panelTablaInvestigadores;
    private javax.swing.JPanel panelTablaMuestras;
    private javax.swing.JPanel panelTablaPatrones;
    private javax.swing.JTable tablaInvestigadores;
    private javax.swing.JTable tablaMuestras;
    private javax.swing.JTable tablaPatrones;
    // End of variables declaration//GEN-END:variables

    
    //Clase interna que nos servirá para dibujar imagenes en el fondo 
    class FondoPanel extends JPanel {
        private Image imagen;
        
        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondoAdminInvest.png")).getImage();
            
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this); //Dibujamos la imagen
            
            setOpaque(false); //Evita que dibuje el fondo por defecto que tiene el panel
            super.paint(g); //Dibuja los componentes por encima de la imagen para que no se pierdan
        }
    }
    
    class FondoTablaInvest extends JPanel {
        private Image imagen;
        
        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondoTablaInvest.png")).getImage();
            
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this); //Dibujamos la imagen
            
            setOpaque(false); //Evita que dibuje el fondo por defecto que tiene el panel
            super.paint(g); //Dibuja los componentes por encima de la imagen para que no se pierdan
        }
    }
    
    class FondoPanelMuestras extends JPanel {
        private Image imagen;
        
        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondoPanelMuestras.png")).getImage();
            
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this); //Dibujamos la imagen
            
            setOpaque(false); //Evita que dibuje el fondo por defecto que tiene el panel
            super.paint(g); //Dibuja los componentes por encima de la imagen para que no se pierdan
        }
    }
    
    class FondoPanelAsignaciones extends JPanel {
        private Image imagen;
        
        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondoPanelAsignaciones.png")).getImage();
            
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this); //Dibujamos la imagen
            
            setOpaque(false); //Evita que dibuje el fondo por defecto que tiene el panel
            super.paint(g); //Dibuja los componentes por encima de la imagen para que no se pierdan
        }
    }
    
    class FondoPanelPatrones extends JPanel {
        private Image imagen;
        
        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/imagenes/fondoPanelPatrones.png")).getImage();
            
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this); //Dibujamos la imagen
            
            setOpaque(false); //Evita que dibuje el fondo por defecto que tiene el panel
            super.paint(g); //Dibuja los componentes por encima de la imagen para que no se pierdan
        }
    }
}
