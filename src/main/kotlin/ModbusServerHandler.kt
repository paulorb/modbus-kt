import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handler implementation for the echo server.
 */
@Sharable
class ModbusServerHandler: ChannelInboundHandlerAdapter() {
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any?) {
        val modbusPacket: ModbusPacket = msg as ModbusPacket
        when(ModbusPacket.FunctionCode.fromInt(modbusPacket.functionCode.toInt())){
            ModbusPacket.FunctionCode.READ_COIL_STATUS -> {

            }
            ModbusPacket.FunctionCode.READ_INPUT_STATUS -> TODO()
            ModbusPacket.FunctionCode.READ_HOLDING_REGISTER -> TODO()
            ModbusPacket.FunctionCode.READ_INPUT_REGISTER -> TODO()
            ModbusPacket.FunctionCode.FORCE_SINGLE_COIL -> TODO()
            ModbusPacket.FunctionCode.PRESET_SINGLE_REGISTER -> TODO()
            ModbusPacket.FunctionCode.FORCE_MULTIPLE_COILS -> TODO()
            ModbusPacket.FunctionCode.PRESET_MULTIPLE_REGISTER -> TODO()
        }

        ctx.close()
    }


    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        ctx.flush()
    }
}