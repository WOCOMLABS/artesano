@file:OptIn(org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl::class)

kotlin {

    sourceSets {

        commonMain.dependencies {

        }

        commonTest.dependencies {

        }

        androidMain.dependencies {

        }

        jvmMain.dependencies {

        }

        jsMain.dependencies {

        }

        when {
            osdetector.os.startsWith("osx") -> {
                iosMain.dependencies {

                }

                macosMain.dependencies {

                }
            }
        }

        linuxMain.dependencies {

        }

        mingwMain.dependencies {

        }

    }

}


kotlin {


}