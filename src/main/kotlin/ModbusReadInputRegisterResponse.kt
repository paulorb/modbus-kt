import helpers.DataConverter
import kotlin.math.max
import ModbusPacket.FunctionCode.Companion.DEFAULT_PROTOCOL_IDENTIFIER
import org.slf4j.LoggerFactory

class ModbusReadInputRegisterResponse: ModbusPacket {
    constructor(transactionIdentifier: Short,
                protocolIdentifier: Short,
                lenght: Short,
                unitID: Byte,
                functionCode: Byte,
                byteVector: ByteArray
    ): super(transactionIdentifier, protocolIdentifier, lenght, unitID, functionCode, byteVector)
    constructor() : super(0,0,0,0,0, ByteArray(0))

    companion object {
        const val LEN_BYTES_METADATA_SIZE = 1
        val logger = LoggerFactory.getLogger("ModbusReadInputRegisterResponse")
    }

    private var listIndexToRegisterValue = mutableListOf<Pair<Int,Short>>()

    fun setRegister(address: Int, value: Short){
        listIndexToRegisterValue.add(Pair<Int, Short>(address, value))
    }

    override fun encode() {
        functionCode = ModbusPacket.FunctionCode.READ_INPUT_REGISTER.value.toByte()
        protocolIdentifier = DEFAULT_PROTOCOL_IDENTIFIER.toShort()

        var maxIndex = 0
        var minIndex = Short.MAX_VALUE.toInt()
        for(element in listIndexToRegisterValue){
            if(element.first > maxIndex){
                maxIndex =  element.first
            }
            if(element.first < minIndex){
                minIndex = element.first
            }
        }

        val numberOfBytes = (maxIndex.toInt() - minIndex.toInt() + 1) * 2
        val length = max(numberOfBytes, 1).toByte()
        logger.debug("modbus length $length")
        byteVector = ByteArray(LEN_BYTES_METADATA_SIZE + length )
        byteVector[0] = length
        var i = 1
        for(element in listIndexToRegisterValue){
            logger.debug("byte[${i}]=${DataConverter.toBytes(element.second)[1]}")
            byteVector[i++] = DataConverter.toBytes(element.second)[1]
            logger.debug("byte[${i}]=${DataConverter.toBytes(element.second)[0]}")
            byteVector[i++] = DataConverter.toBytes(element.second)[0]
        }
    }
}