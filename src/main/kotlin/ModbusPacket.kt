class ModbusPacket(
    var transactionIdentifier: Short,
    var protocolIdentifier: Short,
    var lenght: Short,
    var unitID: Byte,
    var functionCode: Byte,
    var byteVector: ByteArray
    ) {
}