import ModbusPacket.FunctionCode.Companion.DEFAULT_PROTOCOL_IDENTIFIER
import org.slf4j.LoggerFactory
import kotlin.experimental.or
import kotlin.math.max

class ModbusReadInputStatusResponse : ModbusPacket {
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
        val logger = LoggerFactory.getLogger("ModbusReadInputStatusResponse")
    }

    private var listIndexToCoilValue = mutableListOf<Pair<Int,Boolean>>()


    private fun getBit(value: Byte, position: Int): Boolean {
        return (value.toInt() shr position and 1) == 1
    }

    private fun setBit(value: Byte, position: Int): Byte {
        return value or ((1 shl position).toByte())
    }

    fun setDiscreteInput(bitIndex: Int, value: Boolean){
        listIndexToCoilValue.add(Pair<Int, Boolean>(bitIndex, value))
    }

    //@Throws(ArrayIndexOutOfBoundsException)
    fun getDiscreteInput(index: Int): Boolean {
        return getBit(byteVector[index / 8], index % 8)
    }

    fun extraByte(value: Int) : Int {
        return if(value % 8  == 0){
            0
        }else{
            1
        }
    }

    override fun encode() {
        functionCode = ModbusPacket.FunctionCode.READ_INPUT_STATUS.value.toByte()
        protocolIdentifier = DEFAULT_PROTOCOL_IDENTIFIER.toShort()

        var maxIndex = 0
        var minIndex = Short.MAX_VALUE.toInt()
        for(element in listIndexToCoilValue){
            if(element.first > maxIndex){
                maxIndex =  element.first
            }
            if(element.first < minIndex){
                minIndex = element.first
            }
        }
        logger.debug("min $minIndex max $maxIndex")
        val numberOfBytes = ((maxIndex.toInt() - minIndex.toInt() + 1)/8) + extraByte((maxIndex.toInt() - minIndex.toInt() + 1))
        val length = max(numberOfBytes, 1).toByte()
        logger.debug("modbus length $length")
        byteVector = ByteArray(LEN_BYTES_METADATA_SIZE + length )
        byteVector[0] = length
        for(element in listIndexToCoilValue){
            if(element.second) {
                logger.debug("index ${(element.first / 8) + 1 - minIndex} bit index ${element.first % 8}")
                byteVector[((element.first - minIndex)/8)+ 1] =
                    setBit(byteVector[((element.first - minIndex)/8)+ 1], element.first % 8)
            }
        }
    }

    //TODO when implement the client remember to extend this class to also support decoding
}