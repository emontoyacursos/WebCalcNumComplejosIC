# WebCalcNumComplejosIC
# Autor: Edwin Montoya - emontoya@eafit.edu.co, Universidad EAFIT

## Caso de estudio de Integración Continua, TDD, Maven, AppsWeb, QA, PaaS

# Caso de Estudio: 

Se desea desarrollar una app web que permita realizar operaciones básicas con números completos (suma, resta, multiplicación y división)

# Esta aplicación Web se desarrollará en Java (1.8) con Servlets (3.1)

Se utilizará el patrón MVC teniendo:

**Vista:** Página HTML o JSP (index.html o index.jsp)

**Controlador:** Clase CalcServlet.java

  Este controlador, básicamente realiza:
  
  . Lectura de parámetros de la forma del página HTML
  . Realiza algun procesamiento
  . Envia la respuesta HTML al browser.
  
 **Modelo:** Se tendra un objeto NumComplejo.java que tendrá la lógica de negocio y datos.
 
# Crear el proyecto en GitHub (este es el repo del proyecto):

[GitHub https://github.com/emontoyacursos/WebCalcNumComplejosIC.git](https://github.com/emontoyacursos/WebCalcNumComplejosIC.git)
 
# Ambiente de Desarrollo:

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

