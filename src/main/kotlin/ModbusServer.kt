import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import org.apache.logging.log4j.core.config.Configurator

class ModbusServer(modbusServerEventListener: IModbusServerEventListener) {

    private val bossGroup: EventLoopGroup = NioEventLoopGroup(1)
    private val workerGroup: EventLoopGroup = NioEventLoopGroup()
    private val serverHandler = ModbusServerHandler(modbusServerEventListener)
    private val serverChannelInitializer = ServerChannelInitializer(serverHandler)
    lateinit private var channel : ChannelFuture
    fun start(){
        try {
            Configurator.initialize(null, "log4j2.xml")
            val b = ServerBootstrap()
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .option(ChannelOption.SO_BACKLOG, 100)
                .handler(LoggingHandler(LogLevel.INFO))
                .childHandler(serverChannelInitializer)

            // Start the server.
            channel = b.bind(5002).sync()

            // Wait until the server socket is closed.
            //f.channel().closeFuture().sync()
        } catch(ex: Exception) {
            stop()
        }
    }

    fun block() {
        channel.channel().closeFuture().sync()
    }


    fun stop() {
        bossGroup.shutdownGracefully()
        workerGroup.shutdownGracefully()
    }

}