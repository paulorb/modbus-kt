import com.sun.org.slf4j.internal.LoggerFactory

class ModbusReadCoilStatusRequest(modbusPacket: ModbusPacket) {
    companion object {
        val logger = LoggerFactory.getLogger(ModbusPacket::class.java)
    }
    init {
     logger.debug("ModbusReadCoilStatusRequest ${modbusPacket.functionCode}")
    }
}