import org.apache.tools.ant.taskdefs.condition.Os
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


// This is a gradle build script.
// Since this is a tutorial project meant to showcase the usage of the MagicLib combatgui module, you probably don't
// need to build it at all and can simply ignore this file.

// If you want to build this project, you can use this script by either changing the value of starsectorDirectory below
// to the place where you installed Starsector, or by setting the gradle property starsector.dir.
// You can of course also simply build it using your preferred method, such as e.g. setting up dependencies in your IDE.

// this file is a modified version of <https://github.com/wispborne/starsector-mod-template/blob/master/build.gradle.kts>

val modName = "TestCombatGui"

/**
 * Where your Starsector game is installed to.
 * Note: On Linux, if you installed Starsector into your home directory, you have to write /home/<user>/ instead of ~/
 * Either change the path at the end of this line, or set the gradle property starsector.dir
 */
val starsectorDirectory = if(providers.gradleProperty("starsector.dir").isPresent) providers.gradleProperty("starsector.dir").get() else "F:/Games/Starsector0.96a"

val modFolderName = modName.replace(" ", "-")

val shouldAutomaticallyCreateMetadataFiles = false

val starsectorCoreDirectory = if(Os.isFamily(Os.FAMILY_WINDOWS)) "${starsectorDirectory}/starsector-core" else starsectorDirectory
val starsectorModDirectory = "${starsectorDirectory}/mods"
val modInModsFolder = File("$starsectorModDirectory/${modFolderName}")

val jarFileName = "TestCombatGui.jar"


dependencies {

    implementation("com.thoughtworks.xstream:xstream:1.4.10")
    implementation("org.lwjgl.lwjgl:lwjgl:2.9.3")
    implementation("org.lwjgl.lwjgl:lwjgl_util:2.9.3")
    implementation("log4j:log4j:1.2.9")
    implementation("org.json:json:20090211")
    implementation("net.java.jinput:jinput:2.0.7")
    implementation("org.codehaus.janino:janino:3.0.7")
    implementation("starfarer:starfarer-api:1.0.0") // This grabs local files from the /libs folder, see `repositories` block.

    // If the above fails, uncomment this line to use the dependencies in starsector-core instead of getting them from The Internet.
    // compileOnly(fileTree(starsectorCoreDirectory) { include("**/*.jar") })

    if (File(starsectorModDirectory).exists()) {
        compileOnly(fileTree(starsectorModDirectory) {
            include("**/*.jar")
            exclude("**/$jarFileName", "**/lib/*", "**/libs/*")
        })
    } else {
        println("$starsectorModDirectory did not exist, not adding mod folder dependencies.")
    }

    // Add any specific library dependencies needed by uncommenting and modifying the below line to point to the folder of the .jar files.
    // All mods in the /mods folder are already included, so this would be for anything outside /mods.
    // compileOnly(fileTree("$starsectorModDirectory/modfolder") { include("*.jar") })

    val kotlinVersionInLazyLib = "1.5.31"
    // Get kotlin sdk from LazyLib during runtime, only use it here during compile time
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersionInLazyLib")
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersionInLazyLib")
}

tasks {

    named<Jar>("jar")
    {
        // Tells Gradle to put the .jar file in the /jars folder.
        destinationDirectory.set(file("$rootDir/jars"))
        // Sets the name of the .jar file.
        archiveFileName.set(jarFileName)
    }

}

sourceSets.main {
    // Add new folder names here, with the path, if your Java source code isn't in /src.
    java.setSrcDirs(listOf("src"))
}
kotlin.sourceSets.main {
    // Add new folder names here, with the path, if your Kotlin source code isn't in /src.
    kotlin.setSrcDirs(listOf("src"))
    // List of where resources (the "data" folder) are.
    resources.setSrcDirs(listOf("data"))
}

// ================
// ==== DANGER ====
// -----DON'T TOUCH STUFF BELOW THIS LINE UNLESS YOU KNOW WHAT YOU'RE DOING  -------------------
plugins {
    kotlin("jvm") version "1.5.0"
    java
}

repositories {
    maven(url = uri("$projectDir/libs"))
    mavenCentral()
}

// Compile Kotlin to Java 6 bytecode so that Starsector can use it (options are only 6 or 8)
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.6"
}
// Compile Java to Java 7 bytecode so that Starsector can use it
java.sourceCompatibility = JavaVersion.VERSION_1_7
java.targetCompatibility = JavaVersion.VERSION_1_7
