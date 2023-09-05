import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelOption
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler

fun main(args: Array<String>) {
    var modbusServer = ModbusServer(ModbusServerEventListenerReplyRandomNumbers())
    try {
        modbusServer.start()
        modbusServer.block()
    }catch (ex: Exception){
        println("modbus server terminated")
        modbusServer.stop()
    }
//    // Configure the server.
//    val bossGroup: EventLoopGroup = NioEventLoopGroup(1)
//    val workerGroup: EventLoopGroup = NioEventLoopGroup()
//    val serverHandler = ModbusServerHandler(ModbusServerEventListenerReplyAlwaysZero())
//    val serverChannelInitializer = ServerChannelInitializer(serverHandler)
//    try {
//        val b = ServerBootstrap()
//        b.group(bossGroup, workerGroup)
//            .channel(NioServerSocketChannel::class.java)
//            .option(ChannelOption.SO_BACKLOG, 100)
//            .handler(LoggingHandler(LogLevel.INFO))
//            .childHandler(serverChannelInitializer)
//
//        // Start the server.
//        val f: ChannelFuture = b.bind(5002).sync()
//
//        // Wait until the server socket is closed.
//        f.channel().closeFuture().sync()
//    } finally {
//        // Shut down all event loops to terminate all threads.
//        bossGroup.shutdownGracefully()
//        workerGroup.shutdownGracefully()
//    }
}