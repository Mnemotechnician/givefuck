plugins {
	id("org.jetbrains.kotlin.jvm") version "1.6.10"
	application
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

/*
testing {
	suites {
		// Configure the built-in test suite
		val test by getting(JvmTestSuite::class) {
			// Use Kotlin Test test framework
			useKotlinTest()
		}
	}
}
*/

application {
	mainClass.set("com.github.mnemotechnician.givefuck.MainKt")
}

tasks.jar {
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
	
	manifest {
		attributes["Main-Class"] = "com.github.mnemotechnician.givefuck.MainKt"
	}
	
	from(*configurations.runtimeClasspath.files.map { if (it.isDirectory()) it else zipTree(it) }.toTypedArray())
}

tasks.withType(JavaExec::class.java) {
	standardInput = System.`in`
}

tasks.register("publish") {
	dependsOn(tasks.jar)
	
	//create an executable file
	doLast {
		val file = project.file("build/libs/givefuck")
		
		file.outputStream().use {
			it.write("#!/usr/bin/java -jar\n".toByteArray()) //specify file interpreter
			it.write(project.file("build/libs/app.jar").readBytes()) //include the jar file
		}
	}
}
