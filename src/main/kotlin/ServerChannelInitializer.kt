import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelPipeline
import io.netty.channel.socket.SocketChannel

class ServerChannelInitializer(private val serverHandler: ModbusServerHandler): ChannelInitializer<SocketChannel>()  {
    override fun initChannel(ch: SocketChannel?) {
        val p: ChannelPipeline = ch!!.pipeline()
        p.addLast(serverHandler)
    }
}

