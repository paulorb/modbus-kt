# Modbus-kt
[![](https://jitpack.io/v/paulorb/modbus-kt.svg)](https://jitpack.io/#paulorb/modbus-kt)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=paulorb_modbus-kt&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=paulorb_modbus-kt)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=paulorb_modbus-kt&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=paulorb_modbus-kt)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=paulorb_modbus-kt&metric=bugs)](https://sonarcloud.io/summary/new_code?id=paulorb_modbus-kt)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=paulorb_modbus-kt&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=paulorb_modbus-kt)


High performance modbus-tcp client and server implemented in kotlin 

> :warning: **This is a work in progress**: currently only server support is implemented


## Supported functions
* Read Coil Status
* Read Input Status
* Read Holding Registers
* Read Input Registers
* Force Single Coil 
* Preset Single Register
* Force Multiple Coils
* Preset Multiple Registers


## Getting Started

Add the following dependency to your gradle 


Edit build.gradle
```
repositories {
    maven{
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation ("com.github.paulorb:modbus-kt:1.0.0")
}
```

Use the following code to start the server
```
var modbusServer = ModbusServer(ModbusServerEventListenerReplyRandomNumbers())
try {
    modbusServer.start()
    modbusServer.block()
}catch (ex: Exception){
    modbusServer.stop()
}
```

