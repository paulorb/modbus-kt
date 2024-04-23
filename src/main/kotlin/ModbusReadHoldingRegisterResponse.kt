import ModbusPacket.FunctionCode.Companion.DEFAULT_PROTOCOL_IDENTIFIER
import helpers.DataConverter
import java.net.Inet4Address
import kotlin.math.max

class ModbusReadHoldingRegisterResponse: ModbusPacket{
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
    }

    private var listIndexToRegisterValue = mutableListOf<Pair<Int,Short>>()

    fun setRegister(address: Int, value: Short){
        listIndexToRegisterValue.add(Pair<Int, Short>(address, value))
    }

    override fun encode() {
        functionCode = ModbusPacket.FunctionCode.READ_HOLDING_REGISTER.value.toByte()
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
        val length = max(numberOfBytes, 1).toUByte()
        println("modbus length $length")
        byteVector = ByteArray(LEN_BYTES_METADATA_SIZE + length.toInt() )
        byteVector[0] = length.toByte()
        var i = 1
        for(element in listIndexToRegisterValue){
                println("byte[${i}]=${DataConverter.toBytes(element.second)[1]}")
                byteVector[i++] = DataConverter.toBytes(element.second)[1]
                println("byte[${i}]=${DataConverter.toBytes(element.second)[0]}")
                byteVector[i++] = DataConverter.toBytes(element.second)[0]
        }
    }

}