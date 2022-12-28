import java.util.*;

public class SpecialFunctionRegisters{
  public ArrayList<String> sfr = new ArrayList<String>();
  public ArrayList<String> sfrh = new ArrayList<String>();

  public SpecialFunctionRegisters(){
    llenarReg();
  }

  public void llenarReg(){
    sfr.add("STATUS"); sfrh.add("0x3");
    sfr.add("PCL"); sfrh.add("0x82");

    sfr.add("PORTA"); sfrh.add("0x5");
    sfr.add("PORTB"); sfrh.add("0x6");
    sfr.add("PORTC"); sfrh.add("0x7");
    sfr.add("PORTD"); sfrh.add("0x8");
    sfr.add("PORTE"); sfrh.add("0x9");
    sfr.add("TRISA"); sfrh.add("0x85");
    sfr.add("TRISB"); sfrh.add("0x86");
    sfr.add("TRISC"); sfrh.add("0x87");
    sfr.add("TRISD"); sfrh.add("0x88");
    sfr.add("TRISE"); sfrh.add("0x89");
    sfr.add("ANSEL"); sfrh.add("0x188");
    sfr.add("ANSELH"); sfrh.add("0x189");
  }

  public boolean verParEspReg(String par){
    boolean ver=false;
    for(String special: sfr){
      if(par.equals(special)){
        ver = true;
        break;
      }
    }
    return ver;
  }

}
