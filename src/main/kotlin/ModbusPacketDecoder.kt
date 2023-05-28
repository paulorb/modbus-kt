import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

//./modpoll -t0 -r 100 -c 5 -1 -p 5002  127.0.0.1
//./modpoll -t4:float -r 100 -c 5 -1 -p 5002  127.0.0.1

// Don't release ByteBuf, ByteToMessageDecoder handles it internally
//https://github.com/netty/netty/issues/12360
class ModbusPacketDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext?, `in`: ByteBuf?, out: MutableList<Any>?) {
        val bufferSize = `in`!!.readableBytes()
        print("\nRX:")
        for(i in 0 until bufferSize){
            val st = String.format("%02X", `in`.getByte(i))
            print(st)
        }
        println()

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
        val payloadSize = lenght - 2
        println("payload size: ${payloadSize}")
        val modbusPacket = GenericModbusPacket(
            `in`.readShort(),
            `in`.readShort(),
            `in`.readShort(),
            `in`.readByte(),
            `in`.readByte(),
            `in`.toByteArraySafe(payloadSize)
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

fun ByteBuf.toByteArraySafe(payloadSize: Int): ByteArray {
    //if (this.hasArray()) {
    //    return this.array()
    //}

    if(payloadSize > this.readableBytes()){
        throw Exception("Invalid error size on byteBuffer")
    }

    val bytes = ByteArray(payloadSize)
    this.getBytes(this.readerIndex(), bytes)
    this.readerIndex(this.readerIndex() + payloadSize)
    return bytes
}

