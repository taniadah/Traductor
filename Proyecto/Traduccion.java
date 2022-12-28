package Proyecto;
import java.util.*;

public class Traduccion{

  public static ArrayList<String> binInstr = new ArrayList<String>();
  public static ArrayList<String> binPar1, binPar2, norPar1, norPar2;
  public static ArrayList<String> instr = new ArrayList<String>();
  public ArrayList<String> parametros = new ArrayList<String>();
  private String[] linCod;
  //private ArrayList<Integer> indP = new ArrayList<Integer>()

  public Traduccion(String[] linCod){
    this.linCod = linCod;
    binPar1 = new ArrayList<String>();
    binPar2 = new ArrayList<String>();
    norPar1 = new ArrayList<String>();
    norPar2 = new ArrayList<String>();

  }
  public void defineInstruccion(String lineaCodi){
    binInstr = new ArrayList<String>();
  }

  public void separaInstruccion(){
    System.out.println("separaInstruccion");
    String[] lin = this.linCod;
    String instruccion;
    StringTokenizer token;

    for(int i = 0; i < lin.length; i++){
      token = new StringTokenizer(lin[i]);
      instruccion = token.nextToken();
      this.instr.add(instruccion);
      tradInsToBin(instruccion, i);
    }
  }

  public int separaParametros(int indIn, int iiP, int parAc, int bp1, int bp2){
    //String[] lin = this.linCod;
    String linea = this.linCod[indIn];
    String param = "";
    int ver;

    for(int i = iiP; i<linea.length(); i++){
      param += linea.charAt(i);
    }
    String paramsE = param.replaceAll("\\s+","");
    String[] split = paramsE.split(",");

    if(split.length!= parAc){
      ver = 0;
      System.out.println("ERROR");
      this.norPar1.add("-");
      this.norPar2.add("-");
      this.binPar1.add("-");
      this.binPar2.add("-");
    }else{
      ver = 1;
      for(int i = 0; i<split.length; i++){
        String pal = split[i].toLowerCase();
        verificarBase(pal, i, parAc,bp1, bp2);
      }
      if(parAc == 2){
        this.norPar1.add(split[0]);
        this.norPar2.add(split[1]);
      }else{
        this.norPar1.add("-");
        this.norPar2.add(split[0]);
      }
    }
    return ver;
  }

  public void verificarBase(String pal, int i, int parAc, int bp1, int bp2){
    if(parAc==2){
      if(pal.startsWith("0x")){
        if(i == 0){
          hexaToBin(pal, i, bp1);
        }else if(i ==1){
          hexaToBin(pal, i, bp2);
        }
      }else if(pal.startsWith("b'")){
        if(i == 0){
          binToBin(pal, i, bp1);
          //this.binPar1.add("BIN");
        }else if(i == 1){
          binToBin(pal, i, bp2);
        }
      }else if(pal.startsWith("d'")){
        //decToBin(pal, i, parAc,bp1, bp2);
        if(i == 0){
          decToBin(pal, i, bp1);
          //this.binPar1.add("DEC");
        }else if(i == 1){
          decToBin(pal, i, bp2);
          //this.binPar2.add("DEC");
        }
      }else if(pal.equalsIgnoreCase("w")){
        if(i == 0){
          regresarBin(i,"0", bp1);
          //this.binPar1.add("DEC");
        }else if(i == 1){
          regresarBin(i,"0", bp2);
          //this.binPar2.add("DEC");
        }
      }else{
        if(i == 0){
          System.out.println("Aqui");
          this.binPar1.add("Error Parametro");
        }else if(i == 1){
          System.out.println("Aqui2");
          this.binPar2.add("Error Parametro");
        }
      }
    }else if (parAc == 1){
      if(pal.startsWith("0x")){
        hexaToBin(pal, 1, bp2);
        this.binPar1.add("-");
        //this.binPar2.add("HEXA");
      }else if(pal.startsWith("b'")){
        binToBin(pal, 1, bp2);
        this.binPar1.add("-");
        //this.binPar2.add("BIN");
      }else if(pal.startsWith("d'")){
        decToBin(pal, 1, bp2);
        this.binPar1.add("-");
        //clsthis.binPar2.add("DEC");
      }else if(pal.equalsIgnoreCase("w")){
        regresarBin(i,"0", bp2);
        this.binPar1.add("-");

      }else{
        this.binPar2.add("Error Parametro");
        this.binPar1.add("-");
      }
    }
  }

  public int verNoPar(int indIn, int tam){
    String linea = this.linCod[indIn];
    String lineaSE = linea.replaceAll("\\s+","");

    int ver = 1;
    if(lineaSE.length()>tam){
      ver = 0;
    }
    this.norPar1.add("-");
    this.norPar2.add("-");
    return ver;
  }

  public void hexaToBin(String par, int i, int bp){
    System.out.println("hexaToBin");
    boolean ver;
    ver = verSintaxPar(par, "HEXA");

    if(ver){
      String htb = "";
      String paux="";
      int max;
      int dec;
      max = (int)(Math.pow(2, bp));
      // System.out.println("Parametro: "+par);
      for(int j = 2; j<par.length();j++){
        paux+=par.charAt(j);
        // System.out.println("paux "+paux);
        htb+= auxHexa(paux);
        paux ="";
      }
      dec = verBin(htb);
      // System.out.println("hexaToBin: "+htb);
      if(dec<max){
        regresarBin(i, htb, bp);
      }else{
        if(i==0){
          this.binPar1.add("Error >"+max);
        }else if(i==1){
          this.binPar2.add("Error >"+max);
        }
      }
    }else{
      if(i==0){
        this.binPar1.add("digito no valido");
      }else if(i==1){
        this.binPar2.add("digito no valido");
      }
    }

  }
  public void regresarBin(int i, String htb, int bp){
    int iaux = htb.length()- bp;
    String of="";
    if (iaux<0){
      iaux *=-1;
      for(int j = 0; j<iaux; j++){
        of+="0";
      }
      iaux = 0;
    }
    for(int j=iaux; j<htb.length(); j++){
      of+=htb.charAt(j);
    }
    System.out.println("iaux"+iaux);
    System.out.println("of"+of);
    if(i==0){
      this.binPar1.add(of);
    }else if(i==1){
      this.binPar2.add(of);
    }
  }
  public String auxHexa(String pal){
    String bin = "";
    switch (pal) {
      case "0":
      System.out.println("case 0: "+pal);
      bin+= "0000";
      break;
      case "1":
      bin+="0001";
      break;
      case "2":
      bin+="0010";
      break;
      case "3":
      bin+="0011";
      break;
      case "4":
      bin+="0100";
      break;
      case "5":
      bin+="0101";
      break;
      case "6":
      bin+="0110";
      break;
      case "7":
      bin+="0111";
      break;
      case "8":
      bin+="1000";
      break;
      case "9":
      bin+="1001";
      break;
      case "a":
      bin+="1010";
      break;
      case "b":
      bin+="1011";
      break;
      case "c":
      bin+="1100";
      break;
      case "d":
      bin+="1101";
      break;
      case "e":
      bin+="1110";
      break;
      case "f":
      System.out.println("case f");
      bin+="1111";
      break;
    }
    return bin;
  }
  public int verBin(String bin){
    String num = "";
    int pot = 0;
    double n = 0;
    for(int i = bin.length()-1; i>=0; i--){
      num+=bin.charAt(i);
      n+=Math.pow(2,pot)*(Integer.parseInt(num));
      pot+=1;
      num="";
    }
    System.out.println("n "+n);
    return (int) n;
  }
  public void decToBin(String par, int i, int bp){
    boolean ver;
    System.out.println("decToBin");
    ver = verSintaxPar(par, "DEC");
    //System.out.println(ver);

    if(ver){
      String dtb = "";
      int max,dec, modulo;
      String dec1 = "";
      max = (int)(Math.pow(2, bp));
      for(int j = 2; j<par.length()-1;j++){
        dec1+=par.charAt(j);
      }
      dec = Integer.parseInt(dec1);
      System.out.println("max: " + max +"dec "+dec);

      while(dec>0){
        modulo = (dec%2);
        dtb = modulo + dtb;
        dec = dec/2;
      }
      dec = Integer.parseInt(dec1);

      if(dec<max){
        regresarBin(i, dtb, bp);
      }else{
        System.out.println("Error");
        if(i==0){
          this.binPar1.add("Error > o ="+max);
        }else if(i==1){
          this.binPar2.add("Error > o ="+max);
        }
      }
    }else{
      if(i==0){
        this.binPar1.add("digito no valido");
      }else if(i==1){
        this.binPar2.add("digito no valido");
      }
    }

  }
  public void binToBin(String par, int i, int bp){
    boolean ver;
    System.out.println("binToBin");
    ver = verSintaxPar(par, "BIN");
    System.out.println(ver);

    if(ver){
      String btb = "";
      int max,dec, modulo;
      max = (int)(Math.pow(2, bp));
      for(int j = 2; j<par.length()-1;j++){
        btb+=par.charAt(j);
      }
      dec = verBin(btb);

      System.out.println("max: " + max +"dec "+dec);

      if(dec<max){
        regresarBin(i, btb, bp);
      }else{
        System.out.println("Error");
        if(i==0){
          this.binPar1.add("Error > o ="+max);
        }else if(i==1){
          this.binPar2.add("Error > o ="+max);
        }
      }
    }else{
      if(i==0){
        this.binPar1.add("digito no valido");
      }else if(i==1){
        this.binPar2.add("digito no valido");
      }
    }
  }
  public boolean verSintaxPar(String par, String base){
    boolean ver = true;
    if(par.endsWith("'")){
      switch(base){
        case "DEC":
          int cont = 0;
          String decimal = "0123456789";
          String aux="";
          for(int i = 2; i<par.length()-1; i++){
            aux += par.charAt(i);
            if(decimal.contains(aux)){
              cont+=1;
            }
            aux="";
          }
          if(cont == (par.length()-3)){
            ver = true;
          }else
            ver = false;
          break;
        case "BIN":
          cont =0;
          for(int i = 2; i<par.length()-1; i++){
            if(par.charAt(i) == '1' || par.charAt(i)=='0'){
              cont+=1;
            }
          }
          System.out.println("cont "+ cont + "comp "+ Integer.toString((par.length()-3)));
          if(cont == (par.length()-3)){
            ver = true;
          }else
            ver = false;
          break;
        default:
          ver = false;
          break;
      }
      return ver;
    }else if(base.equals("HEXA")){
      int cont = 0;
      System.out.println("entra a exa");
      String hexadecimal = "0123456789abcdef";
      String aux="";
      for(int i = 2; i<par.length(); i++){
        aux+=par.charAt(i);
        if(hexadecimal.contains(aux)){
          cont+=1;
        }
        aux = "";
      }
      System.out.println("HEXA cont "+ cont + "comp "+ Integer.toString((par.length()-2)));
      if(cont == (par.length()-2)){
        ver = true;
      }else
        ver = false;
      return ver;
      }else
      return false;
  }
  public void tradInsToBin(String instruccion, int i){
    //System.out.println("tradInsToBin");
    String instr = instruccion.toLowerCase();
    String bin="";
    System.out.println(instruccion);
    int v = 1;
    switch (instr) {
      case "addwf":
        bin = "000111";
        v = separaParametros(i, 6, 2, 7,1);
        break;
      case "andwf":
        bin = "000101";
        v = separaParametros(i, 6, 2, 7,1);
        break;
      case "clrf":
        bin = "0000011";
        v = separaParametros(i, 5, 1,0,7);
        break;
      case "clrw":
        bin = "00000100000000";
        this.binPar1.add("-");
        this.binPar2.add("-");

        v = verNoPar(i,4);
        break;
      case "comf":
        bin = "001001";
        v = separaParametros(i, 5, 2,7,1);
        break;
      case "decf":
        bin = "000011";
        v = separaParametros(i, 5, 2,7,1);
        break;
      case "decfsz":
        bin = "001011";
        v = separaParametros(i, 7, 2,7,1);
        break;
      case "incf":
        bin = "001010";
        v = separaParametros(i, 5, 2,7,1);
        break;
      case "incfsz":
        bin = "001111";
        v = separaParametros(i, 8, 2,7,1);
        break;
      case "iorwf":
        bin = "000100";
        v = separaParametros(i, 6, 2, 7, 1);
        break;
      case "movf":
        bin = "001000";
        v = separaParametros(i, 5, 2,7,1);
        break;
      case "movwf":
        bin = "000000 1";
        v = separaParametros(i, 6, 1,0,7);
        break;
      case "nop":
        bin = "00000000000000";
        this.binPar1.add("-");
        this.binPar2.add("-");
        v = verNoPar(i,3);
        break;
      case "rlf":
        bin = "001101";
        v = separaParametros(i, 4, 2,7,1);
        break;
      case "rrf":
        bin = "001100";
        v = separaParametros(i, 4, 2,7,1);
        break;
      case "subwf":
        bin = "000010";
        separaParametros(i, 6, 2,7,1);
        break;
      case "swapf":
        bin = "001110";
        v = separaParametros(i, 6, 2,7,1);
        break;
      case "xorwf":
        bin = "000110";
        v = separaParametros(i, 6, 2,7,1);
        break;
      case "bcf":
        bin = "0100";
        v = separaParametros(i, 4, 2,7,3);
        break;
      case "bsf":
        bin = "0101";
        v = separaParametros(i, 4, 2,7,3);
        break;
      case "btfsc":
        bin = "0110";
        v = separaParametros(i, 6, 2,7,3);
        break;
      case "btfss":
        bin = "0111";
        v = separaParametros(i, 6, 2,7,3);
        break;
      case "addlw":
        bin = "111110";
        v = separaParametros(i, 6, 1,0,8);
        break;
      case "andlw":
        bin = "111001";
        v = separaParametros(i, 6, 1,0,8);
        break;
      case "call":
        bin = "100";
        v = separaParametros(i, 5, 1,0,11);
        break;
      // case "clrwdt":
      //   bin = "00000001100100";
      //   this.binPar1.add("-");
      //   this.binPar2.add("-");
      //   v = verNoPar(i,6);
      //   break;
      case "goto":
        bin = "101";
        v = separaParametros(i, 5, 1,0,11);
        break;
      case "iorlw":
        bin = "111000";
        v = separaParametros(i, 6, 1,0,8);
        break;
      case "movlw":
        bin = "110000";
        v = separaParametros(i, 6, 1,0,8);
        break;
      // case "retfie":
      //   bin = "00000000001001";
      //   this.binPar1.add("-");
      //   this.binPar2.add("-");
      //   v = verNoPar(i,6);
      //   break;
      case "retlw":
        bin = "110100";
        v = separaParametros(i, 6, 1,0,8);
        break;
      case "return":
        bin = "00000000001000";
        this.binPar1.add("-");
        this.binPar2.add("-");
        v = verNoPar(i,6);
        break;
      // case "sleep":
      //   bin = "00000001100011";
      //   this.binPar1.add("-");
      //   this.binPar2.add("-");
      //   v = verNoPar(i,5);
      //   break;
      case "sublw":
        bin = "111100";
        v = separaParametros(i, 5, 1,0,8);
        break;
      case "xorl":
        bin = "111010";
        v = separaParametros(i, 5, 1,0,8);
        break;
      default:
        //bin = "No existe";
        v = 2;
        break;
    }
    System.out.println("ver "+v);
    if(v==1){
      this.binInstr.add(bin);
    }else if(v==0){
      this.binInstr.add("Error de sintaxis");
    }else{
      this.binInstr.add("No existe la instruccion");
      this.binPar1.add("-");
      this.binPar2.add("-");
    }
  }
}
