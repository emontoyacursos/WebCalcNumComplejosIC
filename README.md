# WebCalcNumComplejosIC
# Autor: Edwin Montoya - emontoya@eafit.edu.co, Universidad EAFIT

## Caso de estudio de Integración Continua, TDD, Maven, AppsWeb, QA, PaaS

# 1. Caso de Estudio: 

Se desea desarrollar una app web que permita realizar operaciones básicas con números completos (suma, resta, multiplicación y división)

# 2. Esta aplicación Web se desarrollará en Java (1.8) con Servlets (3.1)

Se utilizará el patrón MVC teniendo:

**Vista:** Página HTML o JSP (index.html o index.jsp)

**Controlador:** Clase CalcServlet.java

  Este controlador, básicamente realiza:
  
  . Lectura de parámetros de la forma del página HTML
  . Realiza algun procesamiento
  . Envia la respuesta HTML al browser.
  
 **Modelo:** Se tendra un objeto NumComplejo.java que tendrá la lógica de negocio y datos.
 
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

# 5. Instalar las herramientas de git y heroku-cli

    $ sudo yum install git
    
descargar heroku-cli de: https://devcenter.heroku.com/articles/heroku-cli

# 6. Crear una cuenta en heroku y crear la app para este proyecto

http://www.heroku.com, crear una cuenta

crear una app:

      $ heroku login
      Enter your Heroku credentials:
      email: edwin.montoya@gmail.com
      pass: ******
      
      $ heroku create webcalcnumcomplejosic
      Creating webcalcnumcomplejosic... done
      https://webcalcnumcomplejosic.herokuapp.com/ | https://git.heroku.com/webcalcnumcomplejosic.git
      
# 7. Crear el esqueleto de la aplicación con Maven:

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

# 8. Build de la app -> generar el war para ser desplegado en tomcat y heroku:

    $ cd webcalcnumcomplejosic
    $ mvn install
    
    target/webcalcnumcomplejosic.war

# 8. Adicionar el pluging de heroku a Maven (al pom.xml) para desplegar la app en Heroku.

Ver documentación: https://devcenter.heroku.com/articles/deploying-java-applications-with-the-heroku-maven-plugin

## Adicionar el plugin al pom.xml:

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
                __<appName>webcalcnumcomplejosic</appName>__
            </configuration>
          </plugin>
        </plugins>
      </build>
    </project>
      
      
    
