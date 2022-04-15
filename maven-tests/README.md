# maven-tests

A simple projet used to test various maven features :
- Multi-module

## Project Initialization
-------------------------------

First, create the parent module :

	mvn archetype:generate \
		-DarchetypeGroupId=org.apache.maven.archetypes \
		-DarchetypeArtifactId=maven-archetype-quickstart \
		-DarchetypeVersion=1.1 \
		-DgroupId=io.github.francoisberger \
		-DartifactId=maven-tests \
		-Dversion=1.0-SNAPSHOT

Then edit the generated pom.xml to change the <packaging> from jar to pom. The JUnit dependency is also not required anymore. The src/ directory may also be removed as parent project does not require any source.

	<project>
	
		<groupId>io.github.francoisberger</groupId>
		<artifactId>maven-tests</artifactId>
		<version>1.0-SNAPSHOT</version>
		<packaging>pom</packaging>
		<dependencies>
		</dependencies>

	</project>

Then, create various modules (the -B switch is for batch and means the mvn command will run uninteractively). Also please note that we're using dash in our artefactIds but not in our module names as java packages do not support dash:

	mvn -B archetype:generate \
		-DarchetypeGroupId=org.apache.maven.archetypes \
		-DarchetypeArtifactId=maven-archetype-quickstart \
		-DarchetypeVersion=1.1 \
		-DgroupId=io.github.francoisberger \
		-DartifactId=a-module \
		-Dpackage=io.github.francoisberger.amodule
	
	mvn -B archetype:generate \
		-DarchetypeGroupId=org.apache.maven.archetypes \
		-DarchetypeArtifactId=maven-archetype-quickstart \
		-DarchetypeVersion=1.1 \
		-DgroupId=io.github.francoisberger \
		-DartifactId=another-module \
		-Dpackage=io.github.francoisberger.anothermodule

We're also adding a webapp module using the proper Maven archetype:

	mvn -B archetype:generate \
		-DarchetypeGroupId=org.apache.maven.archetypes \
		-DarchetypeArtifactId=maven-archetype-webapp \
		-DarchetypeVersion=1.4 \
		-DgroupId=io.github.francoisberger \
		-DartifactId=a-webapp-module \
		-Dpackage=io.github.francoisberger.awebappmodule


## Check parent pom.xml includes modules
---------------------------------------------

Edit the parent project pom.xml and check that creates modules are already included within the <modules></modules> tag:

	<project>

		...

		<groupId>io.github.francoisberger</groupId>
		<artifactId>maven-tests</artifactId>
		<version>1.0-SNAPSHOT</version>
		<packaging>pom</packaging>

		<dependencies>
		</dependencies>
		<modules>
			<module>a-module</module>
			<module>another-module</module>
			<module>a-webapp-module</module>
		</modules>
	</project>

## Add dependency management to parent module
---------------------------------------------

Edit the parent project pom.xml and add the dependency management tag. This will allow us to specify our modules version as well as other librairies dependencies version and those versions will be inherited within the child modules:


	<dependencyManagement>
		<dependencies>

			<!-- ===== Modules ===== -->
			<dependency>
				<groupId>io.github.francoisberger</groupId>
				<artifactId>a-module</artifactId>
				<version>1.0-SNAPSHOT</version>
			</dependency>
			<dependency>
				<groupId>io.github.francoisberger</groupId>
				<artifactId>another-module</artifactId>
				<version>1.0-SNAPSHOT</version>
			</dependency>
			<dependency>
				<groupId>io.github.francoisberger</groupId>
				<artifactId>a-webapp-module</artifactId>
				<version>1.0-SNAPSHOT</version>
			</dependency>

			<!-- ===== Other librairies ===== -->
			...

		</dependencies>
	</dependencyManagement>


## Add dependencies for sub-modules
---------------------------------------------

Our sub-modules may depend from one another. To specify that a given module depends from another one, just edit the sub-module pom.xml and add the dependency. Please note that there is no need to specify the other's module version as it is specified within the parent's pom.xml file. For instance, let's imagine our web-app module depends on a-module and another-module :

	<project>
	
		<dependencies>
			<dependency>
				<groupId>io.github.francoisberger</groupId>
				<artifactId>a-module</artifactId>
			</dependency>
			<dependency>
				<groupId>io.github.francoisberger</groupId>
				<artifactId>another-module</artifactId>
			</dependency>
			<dependency>
				<groupId>junit</groupId>
				<artifactId>junit</artifactId>
				<scope>test</scope>
			</dependency>
		</dependencies>
		<build>
		
		</build>
	</project>

You may have noticed that our a-module depends on junit for its tests but we do not need to specify which version because of dependencyManagement in parent's pom. Also if the parent's pom specifies the scope in the dependencyManagement the specified scope  will be used in modules.

## Minor fixes
---------------------------------------------

In the parent pom.xml add the java compiler version property to avoid the *Source option 5 is no longer supported. Use 7 or later.*:

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<maven.compiler.source>17</maven.compiler.source>
		<maven.compiler.target>${maven.compiler.source}</maven.compiler.target>
	</properties>

## Add dependencies for other librairies
---------------------------------------------

One may add dependencies to other librairies (JUnit, Appache Collections, log4j...). Those dependencies are, as a default, included in the generated jars, wars unless specifically excluded or used for a specific scope. For instance the JUnit dependency is specified with the test scope and will not be included in the runtime application. Another usage is, if we need a dependency for servlet to developpe and build our application, this dependency should not be included in the war file because the application server already provide this library. In such a case the *provided* scope should be used.
