import com.sun.media.sound.InvalidFormatException
import helpers.DataConverter

class ModbusReadRequest(modbusPacket: ModbusPacket) :
    ModbusPacket(
        modbusPacket.transactionIdentifier,
        modbusPacket.protocolIdentifier,
        modbusPacket.lenght,
        modbusPacket.unitID,
        modbusPacket.functionCode,
        modbusPacket.byteVector
    ) {
        var address: Short = 0
        var numberOfRegisters: Short = 0
        init {
            if(!(ModbusPacket.FunctionCode.fromInt(functionCode.toInt()) == FunctionCode.READ_COIL_STATUS ||
                ModbusPacket.FunctionCode.fromInt(functionCode.toInt()) == FunctionCode.READ_INPUT_STATUS ||
                ModbusPacket.FunctionCode.fromInt(functionCode.toInt()) == FunctionCode.READ_INPUT_REGISTER ||
                ModbusPacket.FunctionCode.fromInt(functionCode.toInt()) == FunctionCode.READ_HOLDING_REGISTER
                )){
                throw InvalidFormatException("Unsupported function code received $functionCode")
            }
            if(byteVector.size != 4) {
                throw InvalidFormatException("Expected 4 bytes, found ${byteVector.size} bytes")
            }
            address = DataConverter.make_short(byteVector[1], byteVector[0])
            numberOfRegisters = DataConverter.make_short(byteVector[3], byteVector[2])
        }


}