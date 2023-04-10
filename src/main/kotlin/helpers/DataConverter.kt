package helpers

import java.nio.ByteBuffer
import java.nio.ByteOrder

class DataConverter {
    companion object {
        fun make_short(firstByte: Byte, secondByte: Byte): Short {
            var bb = ByteBuffer.allocate(2);
            bb.order(ByteOrder.LITTLE_ENDIAN);
            bb.put(firstByte);
            bb.put(secondByte);
            return bb.getShort (0);
        }

    }
}

@ExperimentalUnsignedTypes // just to make it clear that the experimental unsigned types are used
fun ByteArray.toHexString() = asUByteArray().joinToString("") { it.toString(16).padStart(2, '0') }