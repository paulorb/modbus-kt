import helpers.DataConverter

class ModbusForceSingleCoilRequest (modbusPacket: ModbusPacket) :
    ModbusPacket(
        modbusPacket.transactionIdentifier,
        modbusPacket.protocolIdentifier,
        modbusPacket.lenght,
        modbusPacket.unitID,
        modbusPacket.functionCode,
        modbusPacket.byteVector
    ) {
    var address: Short = 0
    var coilStatus: Boolean = false
    init {
        if(!(ModbusPacket.FunctionCode.fromInt(functionCode.toInt()) == FunctionCode.FORCE_SINGLE_COIL ||
                    ModbusPacket.FunctionCode.fromInt(functionCode.toInt()) == FunctionCode.FORCE_MULTIPLE_COILS ||
                    ModbusPacket.FunctionCode.fromInt(functionCode.toInt()) == FunctionCode.PRESET_MULTIPLE_REGISTER ||
                    ModbusPacket.FunctionCode.fromInt(functionCode.toInt()) == FunctionCode.PRESET_SINGLE_REGISTER
                    )){
            throw Exception("Unsupported function code received $functionCode")
        }
        if(byteVector.size != 4) {
            throw Exception("Expected 4 bytes, found ${byteVector.size} bytes")
        }
        address = DataConverter.make_short(byteVector[1], byteVector[0])
        coilStatus = DataConverter.make_short(byteVector[3], byteVector[2]) == 1.toShort();
    }

    override fun encode() {
        TODO("Not yet implemented, implement when doing the client, for the server this is not necessary")
    }


}