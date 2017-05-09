package pe.edu.upeu.appsales.beanfactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context.xml" })
public class WriterFactoryTest {

	String DB = "db";
	String FILE = "file";

	@Test
	public void testWriterFactory() {
		WriterFactory factory = new WriterFactory();

		JJWriter jjWriter = factory.getWriter(DB);
		System.out.println(">>>>>" + jjWriter);
		jjWriter.write();
	}

	@Autowired
	JJFileWriter jjFileWriter;

	@Test
	public void testJJFileWrier() {
		System.out.println(jjFileWriter);
	}

	@Autowired
	JJDatabaseWriter jjDatabaseWriter;

	@Test
	public void testJJDatabaseWriter() {
		System.out.println(jjDatabaseWriter);
	}

	@Test
	public void testBeanFactorySpring() {

		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("context.xml"));
		JJWriter obj = (JJWriter) factory.getBean("jjDatabaseWriter");
		System.out.println(obj);
		
	}

}
