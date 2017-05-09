package pe.edu.upeu.appsales.beanfactory;

public class JJFileWriter implements JJWriter{

    public void write() {
        System.out.println("Writing to File!!!");
    }
    
    @Override
    public String toString(){
        return "Writing to File!!!";
    }
    
}
