package Proyecto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class Dialogo extends JDialog{
  private JTextField jtfNombre, jtfUbicacion;
  private JLabel jLNombre, jlUbicacion;
  private JPanel p1;
  private JButton aceptar, btnElegir;
  public static String label, nombre="", ubicacion="", cadena;
  private TextPrompt nom, ub;


  public Dialogo(Frame parent, boolean modal, String accion, String cadena){
    super(parent, modal);
    this.cadena = cadena;
    setTitle(accion);
    setSize(400, 200);
    setLayout(new BorderLayout());
    this.setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    initComponents();
  }
  public String getNombre(){
    return this.nombre;
  }

  public void initComponents() {
    p1 = new JPanel();
    p1.setBackground(new Color(88, 214, 141));
    p1.setLayout(null);

    aceptar = new JButton("Aceptar");
    aceptar.setBounds(130,115,90,23);
    EventoAceptar ev = new EventoAceptar();
    aceptar.addActionListener(ev);

    btnElegir = new JButton("Elegir");
    btnElegir.setBounds(255,80,85,23);
    btnElegir.addActionListener(ev);

    jtfNombre = new JTextField();
    jtfNombre.setBounds(80,30,170,25);
    nom = new TextPrompt("MiArchivo", jtfNombre);

    jtfUbicacion = new JTextField();
    jtfUbicacion.setBounds(80,80,170,25);
    ub = new TextPrompt("C:/Users/Documents", jtfUbicacion);

    jLNombre = new JLabel("Nombre");
    jLNombre.setBounds(30,30, 80,28);

    jlUbicacion = new JLabel("Ubicacion");
    jlUbicacion.setBounds(20,80, 80,28);

    p1.add(aceptar);
    p1.add(jtfNombre);
    p1.add(jLNombre);
    p1.add(jtfUbicacion);
    p1.add(jlUbicacion);
    p1.add(btnElegir);
    add(p1);
  }
  public class EventoAceptar implements ActionListener{
    public void actionPerformed(ActionEvent ev){
      //System.out.println("Sip");
      String accion= ev.getActionCommand();

      switch(accion){
        case "Aceptar":
          String nombre = jtfNombre.getText()+".txt";
          String ubicacion = jtfUbicacion.getText();
          if (nombre.equals("")){
            JOptionPane.showMessageDialog(null, "Debe de escribir un nombre", "Error", JOptionPane.ERROR_MESSAGE);
          }else if(ubicacion.equals("")){
            JOptionPane.showMessageDialog(null, "<html><center>Debe darle una ubicacion al archivo", "Error", JOptionPane.ERROR_MESSAGE);
          }else{
            String aviso = crear(nombre, ubicacion);
            if(aviso.startsWith("Error")){
              JOptionPane.showMessageDialog(null, "<html><center>No se pudo crear el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }else if(aviso.startsWith("No creado")){
              JOptionPane.showMessageDialog(null, "<html><center>Archivo existente", "No creado", JOptionPane.ERROR_MESSAGE);
            }else{
              String archivo = ubicacion+'/'+nombre;
              String av = escribirEnArchivo(archivo);
              if (av.equals("hecho")){
                JOptionPane.showMessageDialog(null, "<html><center>Archivo escrito", "Exito", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
              }else{
                JOptionPane.showMessageDialog(null, "<html><center>Archivo creado pero no se pudo escribir en el", "Error", JOptionPane.ERROR_MESSAGE);
                setVisible(false);
              }
            }
          }
        break;
        case "Elegir":
          JFileChooser jf= new JFileChooser();
          jf.setDialogTitle("Seleccione ubicacion del archivo");
          jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          jf.setAcceptAllFileFilterUsed(true);
          int seleccion = jf.showOpenDialog(p1);
          File carpetaSeleccionada = jf.getSelectedFile();
          File aux = jf.getCurrentDirectory();
          jtfUbicacion.setText(carpetaSeleccionada.getPath());
        break;
      }
    }

    public String crear(String nom, String dir){
      String aviso="";
      File nuevo = null;
      try{
        nuevo = new File(dir+ '/'+nom);
        //si el archivo existe
        if (nuevo.exists()) {
          System.out.println("OJO: Archivo con el mismo nombre existente");
          aviso = "No creado: Archivo del mismo nombre existente";
        }else{
          System.out.println(dir+ '/' +nom);
          nuevo.createNewFile();
          aviso = "Archivo "+nom+ " creado correctamente";
        }
      }catch(IOException ex){
        System.out.println("\nError: Creacion de Archivo");
        aviso = "Error: Creacion de Archivo";
      }
      return aviso;
    }
    public String escribirEnArchivo(String archivo){
      String cad = cadena;

      String aviso ="" ;

      File miarchivo = null;
      PrintWriter escribir;
      miarchivo = new File(archivo);
      try{
        escribir = new PrintWriter(archivo, "utf-8");
        escribir.println(cad);
        escribir.close();
        aviso = "hecho";
      }catch (Exception ex) {
        System.out.println("Exepcion");
        aviso = "Error";
      }
      return aviso;
    }
  }//Action listener
}
