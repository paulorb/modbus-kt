import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import org.slf4j.LoggerFactory

//./modpoll -t0 -r 100 -c 5 -1 -p 5002  127.0.0.1
//./modpoll -t1 -r 100 -c 5 -1 -p 5002  127.0.0.1
//./modpoll -t3:float -r 100 -c 5 -1 -p 5002  127.0.0.1
//./modpoll -t4:float -r 100 -c 5 -1 -p 5002  127.0.0.1

// Don't release ByteBuf, ByteToMessageDecoder handles it internally
//https://github.com/netty/netty/issues/12360
class ModbusPacketDecoder : ByteToMessageDecoder() {

    companion object {
        val logger = LoggerFactory.getLogger("ModbusPacketDecoder")
    }


    override fun decode(ctx: ChannelHandlerContext?, `in`: ByteBuf?, out: MutableList<Any>?) {
        val bufferSize = `in`!!.readableBytes()
        print("\nRX:")
        for(i in 0 until bufferSize){
            val st = String.format("%02X", `in`.getByte(i))
            print(st)
        }

        if (`in`!!.readableBytes() < 6) {
            logger.debug("less than 6 bytes received, ignoring...")
            return
        }
        logger.debug("more than 6 bytes received...")
        val lenght = `in`.getShort(4)
        logger.debug("readable bytes ${`in`!!.readableBytes()} lenght ${lenght}")
        if(`in`.readableBytes() < lenght - 4){
            logger.debug("waiting ${lenght - 4} bytes...")
            return
        }
        val payloadSize = lenght - 2
        logger.debug("payload size: ${payloadSize}")
        val modbusPacket = GenericModbusPacket(
            `in`.readShort(),
            `in`.readShort(),
            `in`.readShort(),
            `in`.readByte(),
            `in`.readByte(),
            `in`.toByteArraySafe(payloadSize)
        )
        if(!modbusPacket.isValid()) {
            logger.error("Invalid modbus packet")
            return
        }else{
            logger.debug("Valid modbus packet =)")
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

