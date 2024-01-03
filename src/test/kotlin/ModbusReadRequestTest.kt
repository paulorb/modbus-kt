import kotlin.test.Test
import kotlin.test.assertEquals
internal class ModbusReadRequestTest {
    //11 01 0013 0025 0E84
    //11: The Slave Address (11 hex = address17 )
    //01: The Function Code 1 (read Coil Status)
    //0013: The Data Address of the first coil to read.
    //( 0013 hex = 19 , + 1 offset = coil #20 )
    //0025: The total number of coils requested.  (25 hex = 37,  inputs 20 to 56 )
    //0E84: The CRC (cyclic redundancy check) for error checking.
    @Test
    fun request37Coils() {
        val modbusRequest = ModbusReadRequest( GenericModbusPacket(
            0,
            0,
            7,
            1,
            1,
            byteArrayOf(0x00,0x13,0x00,0x25)
        ))
        assertEquals(19, modbusRequest.address)
        assertEquals(37, modbusRequest.numberOfRegisters)
    }
}