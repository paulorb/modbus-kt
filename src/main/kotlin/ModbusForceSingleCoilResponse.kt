import kotlin.experimental.or
import kotlin.math.max
import ModbusPacket.FunctionCode.Companion.DEFAULT_PROTOCOL_IDENTIFIER
import helpers.DataConverter

class ModbusForceSingleCoilResponse : ModbusPacket {
    constructor(transactionIdentifier: Short,
                protocolIdentifier: Short,
                lenght: Short,
                unitID: Byte,
                functionCode: Byte,
                byteVector: ByteArray
    ): super(transactionIdentifier, protocolIdentifier, lenght, unitID, functionCode, byteVector)
    constructor() : super(0,0,0,0,0, ByteArray(0))

    companion object {
        const val LEN_BYTES_METADATA_SIZE = 4
    }
    private var currentAddress: Short = 0
    private var currentValue : Boolean = false
    fun setAddress(address: Short)
    {
        currentAddress = address
    }
    fun setCoil( value: Boolean){
        currentValue = value
    }

    //@Throws(ArrayIndexOutOfBoundsException)
    fun getCoil(): Boolean {
        return currentValue
    }


    override fun encode() {
        functionCode = ModbusPacket.FunctionCode.FORCE_SINGLE_COIL.value.toByte()
        protocolIdentifier = DEFAULT_PROTOCOL_IDENTIFIER.toShort()
        val addressBytes = DataConverter.toBytes(currentAddress)
        byteVector = ByteArray(LEN_BYTES_METADATA_SIZE)
        byteVector[0] = addressBytes[0]
        byteVector[1] = addressBytes[1]
        if(currentValue) {
            byteVector[2] = 0xFF.toByte()
            byteVector[3] = 0x00.toByte()
        }else{
            byteVector[2] =  0x00.toByte()
            byteVector[3] =  0x00.toByte()
        }
    }

    //TODO when implement the client remember to extend this class to also support decoding
}