package pe.edu.upeu.appsales.beanfactory;

public class JJDatabaseWriter implements JJWriter{

    public void write() {
        System.out.println("Writing to Database!!!");
    }
    
    @Override
    public String toString(){
        return "Writing to Database!!!";
    }
    
}
