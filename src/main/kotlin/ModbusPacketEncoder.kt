import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelOutboundHandlerAdapter
import io.netty.channel.ChannelPromise
import io.netty.handler.codec.MessageToByteEncoder


class ModbusPacketEncoder: MessageToByteEncoder<ModbusPacket>() {

    override fun encode(ctx: ChannelHandlerContext?, msg: ModbusPacket?, out: ByteBuf?) {
        out!!.writeBytes(msg!!.toProto())
    }
}