import org.slf4j.LoggerFactory
import kotlin.random.Random

class ModbusServerEventListenerReplyRandomNumbers : IModbusServerEventListener {

    companion object {
        val logger = LoggerFactory.getLogger("ModbusServerEventListenerReplyRandomNumbers")
    }

    private fun generateRandomLogicList(numberOfRegisters: Int):  List<Boolean> {
        val mutableList = mutableListOf<Boolean>()
        for(i in 0..numberOfRegisters)
            mutableList.add(Random.nextBoolean())
        logger.debug(mutableList.toString())
        return mutableList
    }

    private fun generateRandomNumericList(numberOfRegisters: Int):  List<Short> {
        val mutableList = mutableListOf<Short>()
        for(i in 0..numberOfRegisters)
            mutableList.add(Random.nextInt(Short.MAX_VALUE.toInt()).toShort())
        logger.debug(mutableList.toString())
        return mutableList
    }


    override fun readCoilStatus(startAddress: Int, numberOfRegisters: Int): List<Boolean> {
        return generateRandomLogicList(numberOfRegisters)
    }

    override fun readInputStatus(startAddress: Int, numberOfRegisters: Int): List<Boolean> {
        return generateRandomLogicList(numberOfRegisters)
    }

    override fun readHoldingRegister(startAddress: Int, numberOfRegisters: Int): List<Short> {
        return generateRandomNumericList(numberOfRegisters)
    }

    override fun readInputRegister(startAddress: Int, numberOfRegisters: Int): List<Short> {
        return generateRandomNumericList(numberOfRegisters)
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