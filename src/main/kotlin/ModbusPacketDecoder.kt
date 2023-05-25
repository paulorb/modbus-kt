import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

//./modpoll -t0 -r 100 -c 5 -1 -p 5002  127.0.0.1
//./modpoll -t4:float -r 100 -c 5 -1 -p 5002  127.0.0.1

// Don't release ByteBuf, ByteToMessageDecoder handles it internally
//https://github.com/netty/netty/issues/12360
class ModbusPacketDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext?, `in`: ByteBuf?, out: MutableList<Any>?) {
        if (`in`!!.readableBytes() < 6) {
            println("less than 6 bytes received, ignoring...")
            return
        }
        println("more than 6 bytes received...")
        val lenght = `in`.getShort(4)
        println("readable bytes ${`in`!!.readableBytes()} lenght ${lenght}")
        if(`in`.readableBytes() < lenght - 4){
            println("waiting ${lenght - 4} bytes...")
            return
        }

        val modbusPacket = GenericModbusPacket(
            `in`.readShort(),
            `in`.readShort(),
            `in`.readShort(),
            `in`.readByte(),
            `in`.readByte(),
            `in`.toByteArraySafe()
        )
        if(!modbusPacket.isValid()) {
            println("Invalid modbus packet")
            return
        }else{
            println("Valid modbus packet =)")
            out!!.add(modbusPacket)
        }


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

