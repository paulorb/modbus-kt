import helpers.DataConverter
import kotlin.experimental.or

//TODO Untested function
class ModbusForceMultipleCoilsRequest(modbusPacket: ModbusPacket) :
    ModbusPacket(
        modbusPacket.transactionIdentifier,
        modbusPacket.protocolIdentifier,
        modbusPacket.lenght,
        modbusPacket.unitID,
        modbusPacket.functionCode,
        modbusPacket.byteVector
    ) {
    var address: Int = 0
    var numberOfCoilsToWritten = 0
    var numberOfDataBytesToFollow = 0
    var coilStatus: Boolean = false
    private var listIndexToCoilValue = mutableListOf<Pair<Int,Boolean>>()
    var coilCount: Int =0

    private fun getBit(value: Byte, position: Int): Boolean {
        return (value.toInt() shr position and 1) == 1
    }

    private fun setBit(value: Byte, position: Int): Byte {
        return value or ((1 shl position).toByte())
    }
    private fun setCoil(bitIndex: Int, value: Boolean){
        listIndexToCoilValue.add(Pair<Int, Boolean>(bitIndex, value))
    }

    fun getCoilList(): MutableList<Pair<Int, Boolean>> {
        return listIndexToCoilValue
    }

    init {
        if(!(ModbusPacket.FunctionCode.fromInt(functionCode.toInt()) == FunctionCode.FORCE_SINGLE_COIL ||
                    ModbusPacket.FunctionCode.fromInt(functionCode.toInt()) == FunctionCode.FORCE_MULTIPLE_COILS ||
                    ModbusPacket.FunctionCode.fromInt(functionCode.toInt()) == FunctionCode.PRESET_MULTIPLE_REGISTER ||
                    ModbusPacket.FunctionCode.fromInt(functionCode.toInt()) == FunctionCode.PRESET_SINGLE_REGISTER
                    )){
            throw Exception("Unsupported function code received $functionCode")
        }
        if(byteVector.size < 5) {
            throw Exception("Expected 4 bytes, found ${byteVector.size} bytes")
        }
        address = DataConverter.make_ushort(byteVector[1], byteVector[0]).toInt()
        numberOfCoilsToWritten = DataConverter.make_ushort(byteVector[3], byteVector[2]).toInt()
        numberOfDataBytesToFollow =  byteVector[4].toInt()
        for(byteIndex in 0 until numberOfDataBytesToFollow){
            val currentByte = byteVector[5 + byteIndex]
            for(currentBit in 0 until 7) {
                if(coilCount < numberOfCoilsToWritten){
                    setCoil(coilCount + address, getBit(currentByte, currentBit) )
                    coilCount++;
                }
            }
        }

    }

    override fun encode() {
        TODO("Not yet implemented, implement when doing the client, for the server this is not necessary")
    }


}