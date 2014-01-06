---------------------
Compiling and Running
---------------------

You should be able to compile the demo as a deployable WAR package with
the "package" phase.

You can also run it in the Jetty web server in port 8080 with the jetty:run
goal as follows:

$ mvn jetty:run

---------------------
Google App Engine
---------------------




DOC:
http://vaadin.com/download/jpacontainer-tutorial/
https://vaadin.com/book/-/page/datamodel.properties.html

https://vaadin.com/wiki/-/wiki/Main/Web+Fonts
http://www.google.com/fonts/#

https://vaadin.com/directory#addon/filteringtable
https://vaadin.com/directory#addon/filtertablestate
https://vaadin.com/directory#addon/wizards-for-vaadin
https://vaadin.com/directory#addon/activelink
https://vaadin.com/directory#addon/fancylayouts
http://jouni.virtuallypreinstalled.com/Animator

http://www.streamhead.com/wp-content/uploads/2010/02/pom.xml
http://www.streamhead.com/wp-content/uploads/2011/02/Vaadin_and_App_Engine_in_5_Minutes.pdf
https://vaadin.com/book/vaadin7/-/page/advanced.gae.html

http://wiki.eclipse.org/WTP_FAQ#How_do_I_install_WTP.3F


    Setting up JPA2.0:
- https://developers.google.com/appengine/docs/java/datastore/jpa/overview-dn2
- http://www.datanucleus.org/products/accessplatform/jdo/maven.html
- https://developers.google.com/appengine/docs/java/datastore/jpa/overview
- https://code.google.com/p/datanucleus-appengine/wiki/Compatibility
- https://developers.google.com/appengine/docs/java/tools/maven?hl=pl#using_maven_archetypes

datanucleus-core-*.jar,
datanucleus-jpa-*,
datanucleus-enhancer-*.jar,
asm-*.jar,
geronimo-jpa-*.jar

2.1:
Requires DataNucleus 3.1.x (core, api-jdo, api-jpa, enhancer).
Requires SDK 1.6.4+
Note that this release of Datanucleus is no longer supported by the DataNucleus project


1 asm-4.0.jar
2 datanucleus-api-jdo-3.1.3.jar
3 datanucleus-api-jpa-3.1.3.jar
4 datanucleus-core-3.1.3.jar
5 datanucleus-enhancer-3.1.1.jar
6 geronimo-jpa_2.0_spec-1.0.jar
7 jdo-api-3.0.1.jar
8 jta-1.1.jar
9 appengine-api.jar