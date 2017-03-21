# WebCalcNumComplejosIC
## Autor: Edwin Montoya - emontoya@eafit.edu.co, Universidad EAFIT

## Caso de estudio de Integración Continua, TDD, Maven, AppsWeb, QA, PaaS

# 1. Caso de Estudio:

Se desea desarrollar una app web que permita realizar operaciones básicas con números completos (suma, resta, multiplicación y división)

# 2. Esta aplicación Web se desarrollará en Java (1.8) con Servlets (3.1) 

Se utilizará el patrón MVC teniendo:

* Vista: Página HTML o JSP (index.html o index.jsp)

* Controlador: Clase CalcServlet.java

  Este controlador, básicamente realiza:
  
    * Lectura de parámetros de la forma del página HTML
    * Realiza algun procesamiento
    * Envia la respuesta HTML al browser.
  
 * Modelo: Se tendra un objeto NumComplejo.java que tendrá la lógica de negocio y datos.
 
# 3. Crear el proyecto en GitHub (este es el repo del proyecto):

[GitHub https://github.com/emontoyacursos/WebCalcNumComplejosIC.git](https://github.com/emontoyacursos/WebCalcNumComplejosIC.git)
 
# 4. Ambiente de Desarrollo:

* Se utilizará NetBeans 8.2 + tomcat 8.0.27 para pruebas locales del desarrollador
* Se utilizará MAVEN

se descarga de: [maven](http://maven.apache.org)

se descomprime en MAVEN_HOME=(dir)

    dir linux: /usr/local/apache-maven-3.3.9
    dir windows: c:\Program Files\apache-maven-3.3.9

tener en cuenta:

** actualizar el PATH, ej:linux,

    $ export PATH=$PATH:$MAVEN_HOME/bin
    
**  ej: windows, 

    $ c:\>PATH=%PATH%;%MAVEN_HOME%/bin
                        
se recomienda actualizar el PATH y MAVEN_HOME de forma permanente en el sistema
(ej: linux, actualizar el /etc/profile o windows: propiedades avanzadas del sistema->variables de entorno)

# 5. Instalar las herramientas de git

    $ sudo yum install git

# 6. Crear el esqueleto de la aplicación con Maven:

## Crear la app web:

    $ mvn archetype:generate -DgroupId=co.edu.eafit -DartifactId=webcalcnumcomplejosic -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false

Este esqueleto, crea una aplicación ejemplo en Java, con una página web "Hello, World".

pom.xml:

    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <groupId>co.edu.eafit</groupId>
      <artifactId>webcalcnumcomplejosic</artifactId>
      <packaging>war</packaging>
      <version>1.0-SNAPSHOT</version>
      <name>webcalcnumcomplejosic Maven Webapp</name>
      <url>http://maven.apache.org</url>
      <dependencies>
        <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>3.8.1</version>
          <scope>test</scope>
        </dependency>
      </dependencies>
      <build>
        <finalName>webcalcnumcomplejosic</finalName>
      </build>
    </project>

## Adicionar la dependencia de servlets en el pom.xml:

    <dependencies>
        <dependency>
            <groupId>javax</groupId>
            <artifactId>javaee-web-api</artifactId>
            <version>7.0</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

## Build de la app -> generar el war para ser desplegado en tomcat y heroku:

    $ cd webcalcnumcomplejosic
    $ mvn package
    
    target/webcalcnumcomplejosic.war

# 7.Deploy en Tomcat 8.0.27 #

Se tiene un servidor tomcat instalado en: 10.131.137.239:8080

Se tiene creado el usuario "tomcat", password "s3cret", con los roles: "manager-gui" y "manager-script"

    // file: TOMCAT_HOME/conf/tomcat-users.xml
    <role rolename="manager-gui"/>
    <role rolename="manager-script"/> 
    <user username="tomcat" password="s3cret" roles="manager-gui,manager-script"/>

Adicione las credenciales del servidor en MAVEN_HOME/conf/settings.xml:

    // file: MAVEN_HOME/conf/settings.xml
    <servers>
      ...
      <server>
          <id>TomcatServer</id>
          <username>tomcat</username>
          <password>s3cret</password>
      </server>
      ...
    </servers>

Se adiciona el plugin al pom.xml

      <plugin>
          <groupId>org.apache.tomcat.maven</groupId>
          <artifactId>tomcat7-maven-plugin</artifactId>
          <version>2.2</version>
          <configuration>
            <url>http://10.131.137.239:8080/manager/text</url>
            <server>TomcatServer</server> <!-- configuration on MAVEN_HOME/conf/settings.xml -->
            <path>/${project.build.finalName}</path>
          </configuration>
      </plugin>

Se realiza el despliegue:

    $ mvn clean tomcat7:deploy
    
pruebe desde un browser:

    http://10.131.137.239:8080/webcalnumcomplejosic
    
# 8. Despliegue en nube PaaS HEROKU#

## heroku-cli ##
    
descargar heroku-cli de: https://devcenter.heroku.com/articles/heroku-cli

## Crear una cuenta en heroku y crear la app para este proyecto ##

http://www.heroku.com, crear una cuenta

crear una app:

      $ heroku login
      Enter your Heroku credentials:
      email: edwin.montoya@gmail.com
      pass: ******
      
      $ heroku create webcalcnumcomplejosic
      Creating webcalcnumcomplejosic... done
      https://webcalcnumcomplejosic.herokuapp.com/ | https://git.heroku.com/webcalcnumcomplejosic.git
      

## Adicionar el pluging de heroku a Maven (al pom.xml) para desplegar la app en Heroku.

Ver documentación: https://devcenter.heroku.com/articles/deploying-java-applications-with-the-heroku-maven-plugin

## Adicionar el plugin al pom.xml y la ref a la aplicación en heroku:

    <project>
      ...
      <build>
        ...
        <plugins>
          <plugin>
            <groupId>com.heroku.sdk</groupId>
            <artifactId>heroku-maven-plugin</artifactId>
            <version>1.1.3</version>
            <configuration>
                <appName>webcalcnumcomplejosic</appName>
                <processTypes>
                    <web>java $JAVA_OPTS -cp target/classes:target/dependency/* Main</web>
                </processTypes>
            </configuration>
          </plugin>
        </plugins>
      </build>
    </project>

## Utilizar un java-runner para probar la app local como alternativa a un Tomcat local:

ver: https://devcenter.heroku.com/articles/java-webapp-runner

Debe agregar al pom.xml

    <build>
        ...
        <plugins>
            ...
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <version>2.3</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals><goal>copy</goal></goals>
                        <configuration>
                            <artifactItems>
                                <artifactItem>
                                    <groupId>com.github.jsimone</groupId>
                                    <artifactId>webapp-runner</artifactId>
                                    <version>8.5.11.2</version>
                                    <destFileName>webapp-runner.jar</destFileName>
                                </artifactItem>
                            </artifactItems>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

compilar, generar war:

    $ mvn package
    
Ejecuta el miniservidor local:

    $ java -jar target/dependency/webapp-runner.jar target/*.war
    
    desde un browser, conentese a: http://localhost:8080

Para desplegar en Heroku, debe crear un "Procfile":

Declare como quiere que su aplicación ejecute colocando el archivo "Procfile" en la raiz del proyecto. Cree una linea:

    web:    java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war

## realizar el Deployment a heroku:
 
    $ cd webcalcnumcomplejosic
    $ mvn clean heroku:deploy


# 9. TDD

## Escribir la prueba para suma de complejos:

	@Test
	public void testSumaComplejos() {
		NumComplejos c1 = new NumComplejos(10,2);
		NumComplejos c2 = new NumComplejos(3,-2);
		NumComplejos c3 = c1.add(c2);
		int r_expected = 13;
		int i_expected = 0;
		assertEquals(r_expected, c3.getR());
		assertEquals(i_expected, c3.getI());
	}

## con generación de código, la implementación queda asi:

	public class NumComplejos {

	public NumComplejos(int i, int j) {
		// TODO Auto-generated constructor stub
	}

	public NumComplejos add(NumComplejos c2) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getR() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getI() {
		// TODO Auto-generated method stub
		return null;
	}
	}

## con refactoring:

//TestNumComplejos

	public class TestNumComplejos {
	@Test
	public void testSumaComplejos() {
		NumComplejos c1 = new NumComplejos(10,2);
		NumComplejos c2 = new NumComplejos(3,-2);
		NumComplejos c3 = c1.add(c2);
		float r_expected = 13;
		float i_expected = 0;
		assertEquals(r_expected, c3.getR(),0);
		assertEquals(i_expected, c3.getI(),0);
	}
	}

//NumComplejos

	public class NumComplejos {
		float r, i;

	public NumComplejos(float r, float i) {
		this.r = r;
		this.i = i;
	}

	public NumComplejos add(NumComplejos c2) {
		rtmp = r + c2.getR();
		itmp = i + c2.getI();
		NumComplejos r = new NumComplejos(rtmp, itmp);
		return r;
	}

	public float getR() {
		return r;
	}

	public float getI() {
		return i
	}

	}
