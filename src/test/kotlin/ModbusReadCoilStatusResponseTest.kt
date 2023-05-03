import kotlin.test.Test
import kotlin.test.assertEquals
internal class ModbusReadCoilStatusResponseTest {

    @Test
    fun read8CoilsAllTrue() {
        var response = ModbusReadCoilStatusResponse()
        response.setCoil(0, true)
        response.setCoil(1, true)
        response.setCoil(2, true)
        response.setCoil(3, true)
        response.setCoil(4, true)
        response.setCoil(5, true)
        response.setCoil(6, true)
        response.setCoil(7, true)
        response.transactionIdentifier = 1
        var buffer = response.toProto()
        assertEquals(10, buffer.size)
        assertEquals(0, buffer[0])  // Transaction ID
        assertEquals(1, buffer[1])  // Transaction ID
        assertEquals(0, buffer[2])  // Protocol Identifier
        assertEquals(0, buffer[3])  // Protocol Identifier
        assertEquals(0, buffer[4])  // Message length
        assertEquals(4, buffer[5])  // Message length
        assertEquals(0, buffer[6])  // Unit identifier
        assertEquals(1, buffer[7])  // Function code
        assertEquals(1, buffer[8])  // Number of bytes to follow
        assertEquals(0xFF, buffer[9].toUByte().toInt())  // Number of bytes to follow
    }

    @Test
    fun read8CoilsStartingFromReg100AllTrue() {
        var response = ModbusReadCoilStatusResponse()
        response.setCoil(104, true)
        response.setCoil(105, true)
        response.setCoil(106, true)
        response.setCoil(107, true)
        response.setCoil(108, true)
        response.setCoil(109, true)
        response.setCoil(110, true)
        response.setCoil(111, true)
        response.transactionIdentifier = 1
        var buffer = response.toProto()
        assertEquals(10, buffer.size)
        assertEquals(0, buffer[0])  // Transaction ID
        assertEquals(1, buffer[1])  // Transaction ID
        assertEquals(0, buffer[2])  // Protocol Identifier
        assertEquals(0, buffer[3])  // Protocol Identifier
        assertEquals(0, buffer[4])  // Message length
        assertEquals(4, buffer[5])  // Message length
        assertEquals(0, buffer[6])  // Unit identifier
        assertEquals(1, buffer[7])  // Function code
        assertEquals(1, buffer[8])  // Number of bytes to follow
        assertEquals(0xFF, buffer[9].toUByte().toInt())  // Number of bytes to follow
    }

    @Test
    fun read8CoilsAnotherStartingFromReg100AllTrue() {
        var response = ModbusReadCoilStatusResponse()
        response.setCoil(100, true)
        response.setCoil(101, true)
        response.setCoil(102, true)
        response.setCoil(103, true)
        response.setCoil(104, true)
        response.setCoil(105, true)
        response.setCoil(106, true)
        response.setCoil(107, true)
        response.transactionIdentifier = 1
        var buffer = response.toProto()
        assertEquals(10, buffer.size)
        assertEquals(0, buffer[0])  // Transaction ID
        assertEquals(1, buffer[1])  // Transaction ID
        assertEquals(0, buffer[2])  // Protocol Identifier
        assertEquals(0, buffer[3])  // Protocol Identifier
        assertEquals(0, buffer[4])  // Message length
        assertEquals(4, buffer[5])  // Message length
        assertEquals(0, buffer[6])  // Unit identifier
        assertEquals(1, buffer[7])  // Function code
        assertEquals(1, buffer[8])  // Number of bytes to follow
        assertEquals(0xFF, buffer[9].toUByte().toInt())  // Number of bytes to follow
    }


    @Test
    fun read8CoilsAllFalse() {
        var response = ModbusReadCoilStatusResponse()
        response.setCoil(0, false)
        response.setCoil(1, false)
        response.setCoil(2, false)
        response.setCoil(3, false)
        response.setCoil(4, false)
        response.setCoil(5, false)
        response.setCoil(6, false)
        response.setCoil(7, false)
        response.transactionIdentifier = 1
        var buffer = response.toProto()
        assertEquals(10, buffer.size)
        assertEquals(0, buffer[0])  // Transaction ID
        assertEquals(1, buffer[1])  // Transaction ID
        assertEquals(0, buffer[2])  // Protocol Identifier
        assertEquals(0, buffer[3])  // Protocol Identifier
        assertEquals(0, buffer[4])  // Message length
        assertEquals(4, buffer[5])  // Message length
        assertEquals(0, buffer[6])  // Unit identifier
        assertEquals(1, buffer[7])  // Function code
        assertEquals(1, buffer[8])  // Number of bytes to follow
        assertEquals(0x00, buffer[9].toUByte().toInt())  // Number of bytes to follow
    }

    @Test
    fun read8CoilsMixTrueAndFalse() {
        var response = ModbusReadCoilStatusResponse()
        response.setCoil(0, true)
        response.setCoil(1, false)
        response.setCoil(2, true)
        response.setCoil(3, false)
        response.setCoil(4, true)
        response.setCoil(5, false)
        response.setCoil(6, true)
        response.setCoil(7, false)
        response.transactionIdentifier = 1
        var buffer = response.toProto()
        assertEquals(10, buffer.size)
        assertEquals(0, buffer[0])  // Transaction ID
        assertEquals(1, buffer[1])  // Transaction ID
        assertEquals(0, buffer[2])  // Protocol Identifier
        assertEquals(0, buffer[3])  // Protocol Identifier
        assertEquals(0, buffer[4])  // Message length
        assertEquals(4, buffer[5])  // Message length
        assertEquals(0, buffer[6])  // Unit identifier
        assertEquals(1, buffer[7])  // Function code
        assertEquals(1, buffer[8])  // Number of bytes to follow
        assertEquals(0x55, buffer[9].toUByte().toInt())  // Number of bytes to follow
    }

    @Test
    fun read8CoilsAnotherMixTrueAndFalse() {
        var response = ModbusReadCoilStatusResponse()
        response.setCoil(0, true)
        response.setCoil(1, true)
        response.setCoil(2, true)
        response.setCoil(3, false)
        response.setCoil(4, false)
        response.setCoil(5, false)
        response.setCoil(6, false)
        response.setCoil(7, false)
        response.transactionIdentifier = 1
        var buffer = response.toProto()
        assertEquals(10, buffer.size)
        assertEquals(0, buffer[0])  // Transaction ID
        assertEquals(1, buffer[1])  // Transaction ID
        assertEquals(0, buffer[2])  // Protocol Identifier
        assertEquals(0, buffer[3])  // Protocol Identifier
        assertEquals(0, buffer[4])  // Message length
        assertEquals(4, buffer[5])  // Message length
        assertEquals(0, buffer[6])  // Unit identifier
        assertEquals(1, buffer[7])  // Function code
        assertEquals(1, buffer[8])  // Number of bytes to follow
        assertEquals(0x7, buffer[9].toUByte().toInt())  // Number of bytes to follow
    }


    @Test
    fun read16CoilsAllTrue() {
        var response = ModbusReadCoilStatusResponse()
        response.setCoil(0, true)
        response.setCoil(1, true)
        response.setCoil(2, true)
        response.setCoil(3, true)
        response.setCoil(4, true)
        response.setCoil(5, true)
        response.setCoil(6, true)
        response.setCoil(7, true)
        response.setCoil(8, true)
        response.setCoil(9, true)
        response.setCoil(10, true)
        response.setCoil(11, true)
        response.setCoil(12, true)
        response.setCoil(13, true)
        response.setCoil(14, true)
        response.setCoil(15, true)
        response.transactionIdentifier = 1
        var buffer = response.toProto()
        assertEquals(11, buffer.size)
        assertEquals(0, buffer[0])  // Transaction ID
        assertEquals(1, buffer[1])  // Transaction ID
        assertEquals(0, buffer[2])  // Protocol Identifier
        assertEquals(0, buffer[3])  // Protocol Identifier
        assertEquals(0, buffer[4])  // Message length
        assertEquals(5, buffer[5])  // Message length
        assertEquals(0, buffer[6])  // Unit identifier
        assertEquals(1, buffer[7])  // Function code
        assertEquals(2, buffer[8])  // Number of bytes to follow
        assertEquals(0xFF, buffer[9].toUByte().toInt())  // Number of bytes to follow
        assertEquals(0xFF, buffer[10].toUByte().toInt())  // Number of bytes to follow
    }

    @Test
    fun read16CoilsMixTrueAndFalse() {
        var response = ModbusReadCoilStatusResponse()
        response.setCoil(0, true)
        response.setCoil(1, false)
        response.setCoil(2, true)
        response.setCoil(3, false)
        response.setCoil(4, true)
        response.setCoil(5, false)
        response.setCoil(6, true)
        response.setCoil(7, false)
        response.setCoil(8, true)
        response.setCoil(9, false)
        response.setCoil(10, true)
        response.setCoil(11, false)
        response.setCoil(12, true)
        response.setCoil(13, false)
        response.setCoil(14, true)
        response.setCoil(15, false)
        response.transactionIdentifier = 1
        var buffer = response.toProto()
        assertEquals(11, buffer.size)
        assertEquals(0, buffer[0])  // Transaction ID
        assertEquals(1, buffer[1])  // Transaction ID
        assertEquals(0, buffer[2])  // Protocol Identifier
        assertEquals(0, buffer[3])  // Protocol Identifier
        assertEquals(0, buffer[4])  // Message length
        assertEquals(5, buffer[5])  // Message length
        assertEquals(0, buffer[6])  // Unit identifier
        assertEquals(1, buffer[7])  // Function code
        assertEquals(2, buffer[8])  // Number of bytes to follow
        assertEquals(0x55, buffer[9].toUByte().toInt())  // Number of bytes to follow
        assertEquals(0x55, buffer[10].toUByte().toInt())  // Number of bytes to follow
    }

    @Test
    fun read80CoilsMixTrueAndFalse() {
        var response = ModbusReadCoilStatusResponse()
        var bitIndex = 0
        for(i in 0..9) {
            response.setCoil(bitIndex++, true)
            response.setCoil(bitIndex++, false)
            response.setCoil(bitIndex++, true)
            response.setCoil(bitIndex++, false)
            response.setCoil(bitIndex++, true)
            response.setCoil(bitIndex++, false)
            response.setCoil(bitIndex++, true)
            response.setCoil(bitIndex++, false)
        }
        response.transactionIdentifier = 1
        var buffer = response.toProto()
        assertEquals(19, buffer.size)
        assertEquals(0, buffer[0])  // Transaction ID
        assertEquals(1, buffer[1])  // Transaction ID
        assertEquals(0, buffer[2])  // Protocol Identifier
        assertEquals(0, buffer[3])  // Protocol Identifier
        assertEquals(0, buffer[4])  // Message length
        assertEquals(13, buffer[5])  // Message length
        assertEquals(0, buffer[6])  // Unit identifier
        assertEquals(1, buffer[7])  // Function code
        assertEquals(10, buffer[8])  // Number of bytes to follow
        assertEquals(0x55, buffer[9].toUByte().toInt())  // Number of bytes to follow
        assertEquals(0x55, buffer[10].toUByte().toInt())  // Number of bytes to follow
        assertEquals(0x55, buffer[11].toUByte().toInt())  // Number of bytes to follow
        assertEquals(0x55, buffer[12].toUByte().toInt())  // Number of bytes to follow
        assertEquals(0x55, buffer[13].toUByte().toInt())  // Number of bytes to follow
        assertEquals(0x55, buffer[14].toUByte().toInt())  // Number of bytes to follow
        assertEquals(0x55, buffer[15].toUByte().toInt())  // Number of bytes to follow
        assertEquals(0x55, buffer[16].toUByte().toInt())  // Number of bytes to follow
        assertEquals(0x55, buffer[17].toUByte().toInt())  // Number of bytes to follow
        assertEquals(0x55, buffer[18].toUByte().toInt())  // Number of bytes to follow

    }

}