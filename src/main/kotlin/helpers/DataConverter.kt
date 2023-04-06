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