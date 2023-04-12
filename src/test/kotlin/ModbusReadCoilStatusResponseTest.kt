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
        assertEquals(0xaa, buffer[9].toUByte().toInt())  // Number of bytes to follow
    }
}