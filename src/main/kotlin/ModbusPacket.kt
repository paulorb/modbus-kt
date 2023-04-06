import com.sun.media.sound.InvalidFormatException
import com.sun.org.slf4j.internal.LoggerFactory
import java.util.logging.Logger

class ModbusPacket(
    var transactionIdentifier: Short,
    var protocolIdentifier: Short,
    var lenght: Short,
    var unitID: Byte,
    var functionCode: Byte,
    var byteVector: ByteArray
    ) {

    companion object {
        val logger = LoggerFactory.getLogger(ModbusPacket::class.java)
    }

    init {
        logger.debug("Packet received: trans ind: $transactionIdentifier prot ind: $protocolIdentifier unitID $unitID functionCode $functionCode")
        if(lenght < byteVector.size + 2){
            throw InvalidFormatException("Invalid size")
        }
    }
}