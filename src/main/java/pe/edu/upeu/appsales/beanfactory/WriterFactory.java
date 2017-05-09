package pe.edu.upeu.appsales.beanfactory;

public class WriterFactory {

	public JJWriter getWriter(String str) {
        switch (str) {
        case "file":
            return new JJFileWriter();
        case "db":
            return new JJDatabaseWriter();
        default:
            return null;
        }
    }
	
}
