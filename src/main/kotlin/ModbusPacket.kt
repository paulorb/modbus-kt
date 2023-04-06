import com.sun.media.sound.InvalidFormatException
import com.sun.org.slf4j.internal.LoggerFactory
import java.util.logging.Logger

open class ModbusPacket(
    var transactionIdentifier: Short,
    var protocolIdentifier: Short,
    var lenght: Short,
    var unitID: Byte,
    var functionCode: Byte,
    var byteVector: ByteArray
    )  : IPacket {

    enum class FunctionCode(val value: Int) {
        READ_COIL_STATUS(0x01),
        READ_INPUT_STATUS(0x02),
        READ_HOLDING_REGISTER(0x03),
        READ_INPUT_REGISTER(0x04),
        FORCE_SINGLE_COIL(0x05),
        PRESET_SINGLE_REGISTER(0x06),
        FORCE_MULTIPLE_COILS(0x0F),
        PRESET_MULTIPLE_REGISTER(0x10);

        companion object {
            fun fromInt(value: Int) = FunctionCode.values().first { it.value == value }
        }
    }


    companion object {
        val logger = LoggerFactory.getLogger(ModbusPacket::class.java)
    }

    override fun isValid() : Boolean {
        logger.debug("Packet received: trans ind: $transactionIdentifier prot ind: $protocolIdentifier unitID $unitID functionCode $functionCode")
        if(lenght < byteVector.size + 2){
            logger.error("Malformed packet - Invalid size")
            return false
        }
        return true
    }
}