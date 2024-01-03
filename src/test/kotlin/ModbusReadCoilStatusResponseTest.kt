import kotlin.test.Test
import kotlin.test.assertEquals
internal class ModbusReadCoilStatusResponseTest {

    @Test
    fun read37CoilsMixed() {
        //CD: Coils 27 - 20 (1100 1101)
        //6B: Coils 35 - 28 (0110 1011)
        //B2: Coils 43 - 36 (1011 0010)
        //0E: Coils 51 - 44 (0000 1110)
        //1B: 3 space holders & Coils 56 - 52 (0001 1011)
        var response = ModbusReadCoilStatusResponse()
        response.setCoil(20, true)
        response.setCoil(21, false)
        response.setCoil(22, true)
        response.setCoil(23, true)
        response.setCoil(24, false)
        response.setCoil(25, false)
        response.setCoil(26, true)
        response.setCoil(27, true)

        response.setCoil(28, true)
        response.setCoil(29, true)
        response.setCoil(30, false)
        response.setCoil(31, true)
        response.setCoil(32, false)
        response.setCoil(33, true)
        response.setCoil(34, true)
        response.setCoil(35, false)

        response.setCoil(36, false)
        response.setCoil(37, true)
        response.setCoil(38, false)
        response.setCoil(39, false)
        response.setCoil(40, true)
        response.setCoil(41, true)
        response.setCoil(42, false)
        response.setCoil(43, true)

        response.setCoil(44, false)
        response.setCoil(45, true)
        response.setCoil(46, true)
        response.setCoil(47, true)
        response.setCoil(48, false)
        response.setCoil(49, false)
        response.setCoil(50, false)
        response.setCoil(51, false)

        response.setCoil(52, true)
        response.setCoil(53, true)
        response.setCoil(54, false)
        response.setCoil(55, true)
        response.setCoil(56, true)

        response.transactionIdentifier = 1
        var buffer = response.toProto()
        assertEquals(14, buffer.size)
        assertEquals(0, buffer[0])  // Transaction ID
        assertEquals(1, buffer[1])  // Transaction ID
        assertEquals(0, buffer[2])  // Protocol Identifier
        assertEquals(0, buffer[3])  // Protocol Identifier
        assertEquals(0, buffer[4])  // Message length
        assertEquals(8, buffer[5])  // Message length
        assertEquals(0, buffer[6])  // Unit identifier
        assertEquals(1, buffer[7])  // Function code
        assertEquals(5, buffer[8])  // Number of bytes to follow
        assertEquals(0xCD, buffer[9].toUByte().toInt())
        assertEquals(0x6B, buffer[10].toUByte().toInt())
        assertEquals(0xB2, buffer[11].toUByte().toInt())
        assertEquals(0x0E, buffer[12].toUByte().toInt())
        assertEquals(0x1B, buffer[13].toUByte().toInt())
    }

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
        assertEquals(7, buffer[9].toUByte().toInt())  // Number of bytes to follow
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