package Proyecto;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;


public class Traductor extends JFrame{

  //Atributos
  private JPanel jpl1;
  private JLabel jlE1, jlTr, etqSep, etqIcono, etqPro, etqProN, etqMat1; private JLabel etqMat2, etqEsc1, etqEsc2, etqLE, etqLM, etqInt, etqI2;
//  private JLabel etqI2, etqI3, etqI4;
  private JButton btnTraducir, btnLimpiar, btnGenerar;
  private ImageIcon img1;
  private TextPrompt txtEx;
  private JTextArea jtaLE, jtaLM;
  private JScrollPane jspLE, jspLM;
  private Dialogo pideNombre;

  public Traductor(){
    setTitle("LENGUAJE ENSAMBLADOR - LENGUAJE MAQUINA");
    setSize(1000, 640);
    setResizable(false); //no se expande o se hace más pequeño
    setLocationRelativeTo(null); // posicion centrada
    initComponents(); //iniciar componentes
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void initComponents(){
    Color colorFondo = new Color(72,162,40);
    Color colorLetra = new Color(30,97,68);
    Color colorArea = new Color(205,205,205);
    Color colorDat = new Color(40,66,55);
    Font letraDat = new Font("Tw Cen MT", 1, 14);

    Font letraDat2 = new Font("Californian FB", 2, 14);
    Font letraTit = new Font("Arial Black", 0, 26 );
    Font letraTit2 = new Font("Arial Black", 0, 20);
    Font letraLe = new Font("Arial Black", 0, 20);

    img1 = new ImageIcon(this.getClass().getResource("icono.png"));

    //PANEL
    jpl1 = new JPanel();
    this.getContentPane().add(jpl1);
    jpl1.setBackground(colorFondo);
    jpl1.setLayout(null);

    //ETIQUETAS
    etqIcono = new JLabel();
    etqIcono.setBounds(65,40,140,140);
    etqIcono.setIcon( imgEscala(img1, etqIcono) );

    jlE1 = new JLabel("Traductor");
    jlE1.setBounds(270, 30, 250, 35);
    jlE1.setOpaque(true);
    jlE1.setBackground(colorFondo);
    jlE1.setForeground(colorLetra);
    jlE1.setFont(letraTit);

    jlTr = new JLabel("Ensamblador - lenguaje maquina");
    jlTr.setBounds(270, 40, 400, 70);
    //jlTr.setOpaque(true);
    jlTr.setBackground(colorFondo);
    jlTr.setForeground(Color.WHITE);
    jlTr.setFont(letraTit2);

    etqSep = new JLabel();
    etqSep = new JLabel();
    etqSep.setBounds(250,90,680,4);
    etqSep.setBorder(BorderFactory.createLineBorder(colorLetra));
    etqSep.setOpaque(true);
    etqSep.setBackground(colorLetra);

    etqLE = new JLabel("Ensamblador");
    etqLE.setBounds(330,150, 200, 70);
    //etqPrcolorLetra
    etqLE.setBackground(colorFondo);
    etqLE.setForeground(new Color(41,59,49));
    etqLE.setFont(letraTit2);

    etqLM = new JLabel("Maquina");
    etqLM.setBounds(710,150, 120, 70);
    //etqLM.setOpaque(true);
    etqLM.setBackground(colorFondo);
    etqLM.setForeground(new Color(41,59,49));
    etqLM.setFont(letraTit2);

    etqPro = new JLabel("<html><center>Profesor");
    etqPro.setBounds(55,200,85,20);
    etqPro.setOpaque(true);
    etqPro.setBackground(colorFondo);
    etqPro.setForeground(colorDat);
    etqPro.setFont(letraDat);

    //jlViejo = new JLabel("<html><center>Nombre<P>del archivo");
    etqProN = new JLabel("<html><center>Ing. Alejandro Quintero<P>Garcia");
    etqProN.setBounds(55,220,175,40);
    etqProN.setOpaque(true);
    etqProN.setBackground(colorFondo);
    etqProN.setForeground(Color.BLACK);
    etqProN.setFont(letraDat2);

    etqMat1 = new JLabel("U. de Aprendizaje");
    etqMat1.setBounds(55,265,130,20);
    etqMat1.setOpaque(true);
    etqMat1.setBackground(colorFondo);
    etqMat1.setForeground(colorDat);
    etqMat1.setFont(letraDat);

    etqMat2 = new JLabel("<html><center>Arquitectura<P>de Computadoras");
    etqMat2.setBounds(55,285,130,40);
    etqMat2.setOpaque(true);
    etqMat2.setBackground(colorFondo);
    etqMat2.setForeground(Color.BLACK);
    etqMat2.setFont(letraDat2);

    etqInt = new JLabel("Alumno:");
    etqInt.setBounds(55,330,140,20);
    etqInt.setOpaque(true);
    etqInt.setBackground(colorFondo);
    etqInt.setForeground(colorDat);
    etqInt.setFont(letraDat);


    etqI2 = new JLabel("<html><center>Tania<P>Del Angel");
    etqI2.setBounds(55,390,150,40); //usar  num*85/8
    etqI2.setOpaque(true);
    etqI2.setBackground(colorFondo);
    etqI2.setForeground(Color.BLACK);
    etqI2.setFont(letraDat2);



    etqEsc1 = new JLabel("Universidad");
    etqEsc1.setBounds(55,510,120,20);
    etqEsc1.setOpaque(true);
    etqEsc1.setBackground(colorFondo);
    etqEsc1.setForeground(colorDat);
    etqEsc1.setFont(letraDat);

    etqEsc2 = new JLabel("CU UAEM Zumpango");
    etqEsc2.setBounds(55,530,170,20);
    etqEsc2.setOpaque(true);
    etqEsc2.setBackground(colorFondo);
    etqEsc2.setForeground(Color.BLACK);
    etqEsc2.setFont(letraDat2);

    //BOTONES
    btnTraducir = new JButton("Traducir");
    btnTraducir.setBounds(540, 540, 100, 25);
    btnTraducir.setForeground(Color.WHITE);
    btnTraducir.setBackground(new Color(41,143,99));
    btnTraducir.setBorder(BorderFactory.createLineBorder(colorFondo));
    btnTraducir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnTraducir.setFont(new Font("Yu Gothic UI Semibold", 1, 14));

    btnLimpiar = new JButton("Limpiar");
    btnLimpiar.setBounds(700, 540, 100, 25);
    btnLimpiar.setForeground(Color.WHITE);
    btnLimpiar.setBackground(new Color(41,143,99));
    btnLimpiar.setBorder(BorderFactory.createLineBorder(colorFondo));
    btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnLimpiar.setFont(new Font("Yu Gothic UI Semibold", 1, 14));

    btnGenerar = new JButton("Generar txt");
    btnGenerar.setBounds(375, 540, 100, 25);
    btnGenerar.setForeground(Color.WHITE);
    btnGenerar.setBackground(new Color(41,143,99));
    btnGenerar.setBorder(BorderFactory.createLineBorder(colorFondo));
    btnGenerar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    btnGenerar.setFont(new Font("Yu Gothic UI Semibold", 1, 14));
    btnGenerar.setEnabled(false);

    EventoBoton evtBtn = new EventoBoton();
    btnTraducir.addActionListener(evtBtn);
    btnLimpiar.addActionListener(evtBtn);
    btnGenerar.addActionListener(evtBtn);

    //JTextArea
    jtaLE = new JTextArea();
    jtaLE.setLineWrap(false);
    jtaLE.setWrapStyleWord(true);
    jtaLE.setBackground(colorArea);
    jtaLE.setBorder(BorderFactory.createLineBorder(colorArea));
    jspLE = new JScrollPane(jtaLE);
    jspLE.setBounds(270,200, 325,325);
    txtEx = new TextPrompt("<html>Ejemplo:<P>MOVLW  0x00<P>GOTO 0XFF<P>	RETLW 0XFC",jtaLE);
    txtEx.changeStyle(Font.ITALIC);
    txtEx.setForeground(new Color(146,138,136));

    jtaLM = new JTextArea();
    jtaLM.setLineWrap(false);
    jtaLE.setBackground(colorArea);
    jtaLM.setWrapStyleWord(true);
    jtaLM.setBorder(BorderFactory.createLineBorder(colorArea));
    jtaLM.setEditable(false);
    jspLM = new JScrollPane(jtaLM);
    jspLM.setBounds(600,200, 325,325);

    jpl1.add(jlE1);
    jpl1.add(jlTr);
    jpl1.add(etqSep);
    jpl1.add(etqIcono);
    jpl1.add(etqPro);
    jpl1.add(etqProN);
    jpl1.add(etqMat1);
    jpl1.add(etqMat2);
    jpl1.add(etqEsc1);
    jpl1.add(etqEsc2);
    jpl1.add(etqLE);
    jpl1.add(etqLM);
    jpl1.add(etqInt);
    // jpl1.add(etqI1);
    jpl1.add(etqI2);
    // jpl1.add(etqI3);
    // jpl1.add(etqI4);
    jpl1.add(btnTraducir);
    jpl1.add(btnGenerar);
    jpl1.add(btnLimpiar);
    jpl1.add(jspLE);
    jpl1.add(jspLM);
  }
  public ImageIcon imgEscala(ImageIcon img, javax.swing.JComponent comp){
      return new ImageIcon(img.getImage().getScaledInstance( comp.getWidth(), comp.getHeight(), Image.SCALE_SMOOTH ));
  }

  public class EventoBoton implements ActionListener{
    public void actionPerformed(ActionEvent ev){
      String accion =ev.getActionCommand();
      if(accion.equals("Traducir") && "".equals(jtaLE.getText())){
        System.out.println("vacio");
        JOptionPane.showMessageDialog(null, "No hay codigo para traducir!","Hey!", JOptionPane.ERROR_MESSAGE);
      }else if (accion.equals("Limpiar")) {
        btnGenerar.setEnabled(false);
        jtaLE.setText("");
        jtaLM.setText("");
      }else if(accion.equals("Generar txt")){
        System.out.println("Generando txt");
        String lm =  jtaLM.getText();
        if(lm.contains("Error") || lm.contains("No existe") || lm.contains("no valido")){
          JOptionPane.showMessageDialog(null, "<html><center>No se puede generar txt <P>Corregir errores!","Hey!", JOptionPane.ERROR_MESSAGE);
        }else{
          //JOptionPane.showMessageDialog(null, "<html>Archivo txt generado<P>","Hey!", JOptionPane.INFORMATION_MESSAGE);
          String cadena = jtaLM.getText();
          pideNombre = new Dialogo(new JFrame(), true, "Datos para el nuevo archivo", cadena);
          pideNombre.setVisible(true);

        }
      }else{
        btnGenerar.setEnabled(true);
        jtaLM.setText("");
        String codigo = jtaLE.getText();
        //System.out.println(codigo);
        String[] lineas = codigo.split("\n");
        Traduccion t = new Traduccion(lineas);
        t.separaInstruccion();

        for(int i=0; i<t.binInstr.size(); i++){
          if(t.binInstr.get(i).equals("Error de sintaxis") || t.binInstr.get(i).equals("No existe la instruccion")){
            System.out.println("Primer");
            jtaLM.append(t.binInstr.get(i)+"\n");
          }else{
            if(t.binPar1.get(i).equals("-")){
              if(t.binPar2.get(i).equals("-")){
                jtaLM.append(t.binInstr.get(i)+"\n");
                System.out.println("Segundo");
              }else{
                jtaLM.append(t.binInstr.get(i)+t.binPar2.get(i)+"\n");
                System.out.println("Tecero");
              }
            }else{
              jtaLM.append(t.binInstr.get(i)+t.binPar2.get(i)+ t.binPar1.get(i)+"\n");
              System.out.println("Cuarto");
            }
          }
        }
        t.binInstr.clear();
        t.binPar1.clear();
        t.binPar2.clear();
        t.norPar1.clear();
        t.norPar2.clear();
      }
    }
  }
}
