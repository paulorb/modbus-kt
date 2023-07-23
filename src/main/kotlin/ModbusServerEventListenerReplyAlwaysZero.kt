/**
 * ModbusServerEventListenerReplyAlwaysZero
 * Setup the server simulator to always return zero to any read command received, for write command
 * the value will be ignored
 */
class ModbusServerEventListenerReplyAlwaysZero: IModbusServerEventListener {
    override fun readCoilStatus(startAddress: Int, numberOfRegisters: Int): List<Boolean> {
        return List(numberOfRegisters) {false}
    }

    override fun readInputStatus(startAddress: Int, numberOfRegisters: Int): List<Boolean> {
        return List(numberOfRegisters) {false}
    }

    override fun readHoldingRegister(startAddress: Int, numberOfRegisters: Int): List<Short> {
        return List(numberOfRegisters) {0}
    }

    override fun readInputRegister(startAddress: Int, numberOfRegisters: Int): List<Short> {
        return List(numberOfRegisters) {0}
    }

    override fun forceSingleCoil(address: Int, value: Boolean) {
        return
    }

    override fun presetSingleRegister(address: Int, value: Boolean) {
       return
    }

    override fun forceMultipleCoils(addressValueList: MutableList<Pair<Int, Boolean>>) {
        return
    }

    override fun presetMultipleRegisters(addressValueList: MutableList<Pair<Int, Short>>) {
        return
    }
}