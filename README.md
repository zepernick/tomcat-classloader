<pre>
<b>Overview</b>

**This project has been Forked and modified for Tomcat 9.

The project once installed onto the tomcat server allows the tomcat
administrator to specify a centeral location for all web-applications
properties files. Web-applications that load their properties using the
classpath can take advantage this project to change their logging/database
connection details without the need to re-deploy the web application. Web-
applications that dont use the class loader to load properties files and or
configuration files can be easily be modified to use LoadResources or
configured depending on the framework to look for resources on the default
classpath.

DynamicVirtualWebappLoader under the hood works by taking the context-path of
the web-application when it is loaded by the servlet container (in this
situation tomcat) and appending it to the specified virtualClasspath. This
creates a new virtualClassPath that is unique to each web-application. The new
virtualClassPath is searched first before the Servlet Container classpath.

Some examples are given bellow.

eg... 
Web Application context: ntflora
virtualClasspath=/root/pswd

LoadResources first searches the virtualclasspath for the requested resource
before using the default classpath.
<b>/root/pswd/ntflora</b>

eg.. 
Web Application context: key-server
virtualClasspath=/root/pswd

LoadResources first searches the virtualclasspath for the requested resource
before using the default classpath.
<b>/root/pswd/key-server</b>

<b>Installation</b>

Download the DynamicVirtualWebappLoader.jar file and place it into your
<b>$CATALINA_BASE/lib</b> directory.

Apply the following modification to <b>$CATALINA_BASE/conf/context.xml</b>.
Change the <b>virtualClasspath</b> to the required path on the server.

&lt;Context>
  &lt;Resources>
    &lt;PostResources className="rds.DynamicVirtualWebappLoader"
                   webAppMount="/WEB-INF/classes" base="/apache/virtual_classpath" />
  &lt;/Resources>
&lt;/Context>

<b>Restart tomcat instance</b>
</pre>

