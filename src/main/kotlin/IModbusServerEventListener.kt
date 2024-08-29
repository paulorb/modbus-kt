interface IModbusServerEventListener {
    fun readCoilStatus(startAddress: Int ,numberOfRegisters: Int) : List<Boolean>
    fun readInputStatus(startAddress: Int ,numberOfRegisters: Int) : List<Boolean>
    fun readHoldingRegister(startAddress: Int ,numberOfRegisters: Int) : List<Short>
    fun readInputRegister(startAddress: Int ,numberOfRegisters: Int) : List<Short>
    fun forceSingleCoil(address: Int, value: Boolean)
    fun presetSingleRegister(address: Int, value: Short)
    fun forceMultipleCoils(addressValueList: MutableList<Pair<Int, Boolean>>)
    fun presetMultipleRegisters(addressValueList: MutableList<Pair<Int, Short>>)

}