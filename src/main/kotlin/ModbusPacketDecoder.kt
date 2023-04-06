import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class ModbusPacketDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext?, `in`: ByteBuf?, out: MutableList<Any>?) {
        if (`in`!!.readableBytes() < 6) {
            return
        }
        val lenght = `in`.getShort(5)
        if(`in`.readableBytes() < lenght){
            return
        }

        val modbusPacket = ModbusPacket(
            `in`.readShort(),
            `in`.readShort(),
            `in`.readShort(),
            `in`.readByte(),
            `in`.readByte(),
            `in`.readBytes(`in`.readableBytes()).array()
        )
        if(!modbusPacket.isValid()) {
            `in`.release()
            return
        }

        out!!.add(modbusPacket)
    }
}

