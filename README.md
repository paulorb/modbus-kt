[![](https://jitpack.io/v/paulorb/modbus-kt.svg)](https://jitpack.io/#paulorb/modbus-kt)
# Modbus-kt
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

Add the following dependency to your gradle (Gradle 4.10 or higher required)

Edit settings.gradle

```
sourceControl {
    gitRepository("https://github.com/paulorb/modbus-kt.git") {
        producesModule("org.modbuskt")
    }
}
```

Edit build.gradle
```
dependencies {
...

    implementation 'org.modbuskt'
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

