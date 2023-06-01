import helpers.DataConverter
import ModbusPacket.FunctionCode.Companion.DEFAULT_PROTOCOL_IDENTIFIER
class ModbusPresetMultipleRegistersResponse : ModbusPacket {
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
    private var numberOfRegistersWritten : Short = 0
    fun setAddress(address: Short)
    {
        currentAddress = address
    }
    fun setNumberofRegistersWritten( numberCoilsWritten: Short){
        numberOfRegistersWritten = numberCoilsWritten
    }

    override fun encode() {
        functionCode = ModbusPacket.FunctionCode.PRESET_MULTIPLE_REGISTER.value.toByte()
        protocolIdentifier = DEFAULT_PROTOCOL_IDENTIFIER.toShort()
        val addressBytes = DataConverter.toBytes(currentAddress)
        val numberOfBytesWrittenBytes = DataConverter.toBytes((numberOfRegistersWritten.toInt() * 2).toShort() )

        byteVector = ByteArray(LEN_BYTES_METADATA_SIZE)
        byteVector[0] = addressBytes[1]
        byteVector[1] = addressBytes[0]
        byteVector[2] = numberOfBytesWrittenBytes[1]
        byteVector[3] = numberOfBytesWrittenBytes[0]
    }

    //TODO when implement the client remember to extend this class to also support decoding
}