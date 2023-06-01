import helpers.DataConverter
import kotlin.experimental.or

class ModbusPresetMultipleRegistersRequest (modbusPacket: ModbusPacket) :
    ModbusPacket(
        modbusPacket.transactionIdentifier,
        modbusPacket.protocolIdentifier,
        modbusPacket.lenght,
        modbusPacket.unitID,
        modbusPacket.functionCode,
        modbusPacket.byteVector
    ) {
    var address: Int = 0
    var numberOfRegistersToWritten = 0
    var numberOfDataBytesToFollow = 0
    private var listIndexToRegisterValue = mutableListOf<Pair<Int,Short>>()
    var coilCount: Int =0

    private fun setRegister(address: Int, value: Short){
        listIndexToRegisterValue.add(Pair<Int, Short>(address, value))
    }

    fun getRegisterList(): MutableList<Pair<Int, Short>> {
        return listIndexToRegisterValue
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
        numberOfRegistersToWritten = DataConverter.make_ushort(byteVector[3], byteVector[2]).toInt()
        numberOfDataBytesToFollow =  byteVector[4].toInt()

        var currentAddress = address
        for(byteIndex in 0 until numberOfDataBytesToFollow step 2){
            val currentShort = DataConverter.make_short(byteVector[5 + byteIndex + 1], byteVector[5 + byteIndex])
            setRegister(address++, currentShort)
        }
    }

    override fun encode() {
        TODO("Not yet implemented, implement when doing the client, for the server this is not necessary")
    }


}