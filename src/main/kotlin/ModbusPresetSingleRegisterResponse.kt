import helpers.DataConverter
import ModbusPacket.FunctionCode.Companion.DEFAULT_PROTOCOL_IDENTIFIER
class ModbusPresetSingleRegisterResponse : ModbusPacket {
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
    private var currentAddress: Int = 0
    private var currentValue : Short = 0
    fun setAddress(address: Int)
    {
        currentAddress = address
    }
    fun setRegisterValue( value: Short){
        currentValue = value
    }

    //@Throws(ArrayIndexOutOfBoundsException)
    fun getRegisterValue(): Short {
        return currentValue
    }


    override fun encode() {
        functionCode = ModbusPacket.FunctionCode.PRESET_SINGLE_REGISTER.value.toByte()
        protocolIdentifier = DEFAULT_PROTOCOL_IDENTIFIER.toShort()
        val addressBytes = DataConverter.toBytes(currentAddress.toShort())
        byteVector = ByteArray(LEN_BYTES_METADATA_SIZE)
        byteVector[0] = addressBytes[1]
        byteVector[1] = addressBytes[0]
        val valueBytes = DataConverter.toBytes(currentValue)
        byteVector[2] = valueBytes[1]
        byteVector[3] = valueBytes[0]
    }

    //TODO when implement the client remember to extend this class to also support decoding
}