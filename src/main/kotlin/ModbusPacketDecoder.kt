import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

//./modpoll -t4:float -r 100 -c 5 -1 -p 5002  127.0.0.1
class ModbusPacketDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext?, `in`: ByteBuf?, out: MutableList<Any>?) {
        if (`in`!!.readableBytes() < 6) {
            return
        }
        val lenght = `in`.getShort(4)
        println("readable bytes ${`in`!!.readableBytes()} lenght ${lenght}")
        if(`in`.readableBytes() < lenght - 4){
            return
        }

        val modbusPacket = ModbusPacket(
            `in`.readShort(),
            `in`.readShort(),
            `in`.readShort(),
            `in`.readByte(),
            `in`.readByte(),
            `in`.toByteArraySafe()
        )
        if(!modbusPacket.isValid()) {
            `in`.release()
            return
        }

        out!!.add(modbusPacket)
    }
}

fun ByteBuf.toByteArraySafe(): ByteArray {
    if (this.hasArray()) {
        return this.array()
    }

    val bytes = ByteArray(this.readableBytes())
    this.getBytes(this.readerIndex(), bytes)

    return bytes
}

