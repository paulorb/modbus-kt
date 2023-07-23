import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Handler implementation for the echo server.
 */
@Sharable
class ModbusServerHandler(private val modbusServerEventListener: IModbusServerEventListener) : ChannelInboundHandlerAdapter() {
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any?) {
        val modbusPacket: ModbusPacket = msg as ModbusPacket
        when(ModbusPacket.FunctionCode.fromInt(modbusPacket.functionCode.toInt())){
            ModbusPacket.FunctionCode.READ_COIL_STATUS -> {
                val modbusRequest = ModbusReadRequest(modbusPacket)
                println("address ${modbusRequest.address} number of registers ${modbusRequest.numberOfRegisters}")
                val modbusReadCoilStatusResponse = ModbusReadCoilStatusResponse()
                modbusReadCoilStatusResponse.unitID = modbusRequest.unitID
                modbusReadCoilStatusResponse.transactionIdentifier = modbusRequest.transactionIdentifier
                var listCoils = modbusServerEventListener.readCoilStatus(modbusRequest.address.toInt(), modbusRequest.numberOfRegisters.toInt())
                for(i in 0 until modbusRequest.numberOfRegisters){
                    modbusReadCoilStatusResponse.setCoil(modbusRequest.address + i, listCoils[i])
                }
                ctx.write(modbusReadCoilStatusResponse)
            }
            ModbusPacket.FunctionCode.READ_INPUT_STATUS -> {
                val modbusRequest = ModbusReadRequest(modbusPacket)
                println("address ${modbusRequest.address} number of registers ${modbusRequest.numberOfRegisters}")
                val modbusReadInputStatusResponse = ModbusReadInputStatusResponse()
                modbusReadInputStatusResponse.unitID = modbusRequest.unitID
                modbusReadInputStatusResponse.transactionIdentifier = modbusRequest.transactionIdentifier
                var listInputStatus = modbusServerEventListener.readInputStatus(modbusRequest.address.toInt(), modbusRequest.numberOfRegisters.toInt())
                for(i in 0 until modbusRequest.numberOfRegisters){
                    modbusReadInputStatusResponse.setDiscreteInput(modbusRequest.address + i, listInputStatus[i])
                }
                ctx.write(modbusReadInputStatusResponse)
            }
            ModbusPacket.FunctionCode.READ_HOLDING_REGISTER -> {
                val modbusRequest = ModbusReadRequest(modbusPacket)
                println("address ${modbusRequest.address} number of registers ${modbusRequest.numberOfRegisters}")
                val modbusReadHoldingRegisterResponse = ModbusReadHoldingRegisterResponse()
                modbusReadHoldingRegisterResponse.unitID = modbusRequest.unitID
                modbusReadHoldingRegisterResponse.transactionIdentifier = modbusRequest.transactionIdentifier
                //TODO(there is an issue with holding register)
                //to replicate try with
                //modpoll -t4 -r 100 -c 20 -1 -p 5002  127.0.0.1
                //TODO(the simulator needs to be smart in terms of datatypes, a holding registers form client perspective can be a float32 or word this needs to be part of the ModbusServerEventListener)
                var listHoldingRegisters = modbusServerEventListener.readHoldingRegister(modbusRequest.address.toInt(), modbusRequest.numberOfRegisters.toInt())
                for(i in 0 until modbusRequest.numberOfRegisters){
                    modbusReadHoldingRegisterResponse.setRegister(modbusRequest.address + i, listHoldingRegisters[i])
                }
                ctx.write(modbusReadHoldingRegisterResponse)
            }
            ModbusPacket.FunctionCode.READ_INPUT_REGISTER -> {
                val modbusRequest = ModbusReadRequest(modbusPacket)
                println("address ${modbusRequest.address} number of registers ${modbusRequest.numberOfRegisters}")
                val modbusReadInputRegisterResponse = ModbusReadInputRegisterResponse()
                modbusReadInputRegisterResponse.unitID = modbusRequest.unitID
                modbusReadInputRegisterResponse.transactionIdentifier = modbusRequest.transactionIdentifier
                //TODO(there is an issue with input register)
                //to replicate try with
                //modpoll -t3 -r 100 -c 20 -1 -p 5002  127.0.0.1
                var listInputRegisters = modbusServerEventListener.readInputRegister(modbusRequest.address.toInt(), modbusRequest.numberOfRegisters.toInt())
                for(i in 0 until modbusRequest.numberOfRegisters){
                    modbusReadInputRegisterResponse.setRegister(modbusRequest.address + i, listInputRegisters[i])
                }
                ctx.write(modbusReadInputRegisterResponse)
            }
            ModbusPacket.FunctionCode.FORCE_SINGLE_COIL -> {
               val modbusWriteRequest = ModbusForceSingleCoilRequest(modbusPacket)
                if(modbusWriteRequest.coilStatus) {
                    println("address ${modbusWriteRequest.address} status ON")
                }else{
                    println("address ${modbusWriteRequest.address} status OFF")
                }
                val modusForceSingleCoilResponse = ModbusForceSingleCoilResponse()
                modusForceSingleCoilResponse.unitID = modbusWriteRequest.unitID
                modbusServerEventListener.forceSingleCoil(modbusWriteRequest.address, modbusWriteRequest.coilStatus)
                modusForceSingleCoilResponse.transactionIdentifier = modbusWriteRequest.transactionIdentifier
                modusForceSingleCoilResponse.setAddress(modbusWriteRequest.address.toShort())
                modusForceSingleCoilResponse.setCoil(modbusWriteRequest.coilStatus)
                ctx.write(modusForceSingleCoilResponse)
            }
            ModbusPacket.FunctionCode.PRESET_SINGLE_REGISTER -> {
                val modbusWriteRequest = ModbusPresetSingleRegisterRequest(modbusPacket)
                val modbusWritePresetSingleRegisterResponse = ModbusPresetSingleRegisterResponse()
                modbusWritePresetSingleRegisterResponse.unitID = modbusWriteRequest.unitID
                modbusServerEventListener.presetSingleRegister(modbusWriteRequest.address, modbusWriteRequest.singleRegisterValue.toInt() == 1)
                modbusWritePresetSingleRegisterResponse.transactionIdentifier = modbusWriteRequest.transactionIdentifier
                modbusWritePresetSingleRegisterResponse.setAddress(modbusWriteRequest.address)
                modbusWritePresetSingleRegisterResponse.setRegisterValue(modbusWriteRequest.singleRegisterValue)
                ctx.write(modbusWritePresetSingleRegisterResponse)
            }
            ModbusPacket.FunctionCode.FORCE_MULTIPLE_COILS -> {
                val modbusWriteRequest = ModbusForceMultipleCoilsRequest(modbusPacket)
                val modbusForceMultipleCoilsResponse = ModbusForceMultipleCoilsResponse()
                modbusForceMultipleCoilsResponse.unitID = modbusWriteRequest.unitID
                modbusServerEventListener.forceMultipleCoils(modbusWriteRequest.getCoilList())
                println("received FORCE_MULTIPLE_COILS")
                for(coil in modbusWriteRequest.getCoilList()){
                    println("address: ${coil.first} value: ${coil.second}")
                }
                modbusForceMultipleCoilsResponse.transactionIdentifier = modbusWriteRequest.transactionIdentifier
                modbusForceMultipleCoilsResponse.setAddress(modbusWriteRequest.address.toShort())
                modbusForceMultipleCoilsResponse.setNumberofCoilsWritten(modbusWriteRequest.numberOfCoilsToWritten.toShort())
                ctx.write(modbusForceMultipleCoilsResponse)
            }
            ModbusPacket.FunctionCode.PRESET_MULTIPLE_REGISTER -> {
                val modbusWriteRequest = ModbusPresetMultipleRegistersRequest(modbusPacket)
                val modbusPresetMultipleRegistersResponse = ModbusPresetMultipleRegistersResponse()
                modbusPresetMultipleRegistersResponse.unitID = modbusWriteRequest.unitID
                modbusServerEventListener.presetMultipleRegisters(modbusWriteRequest.getRegisterList())
                println("received PRESET_MULTIPLE_REGISTER")
                for(register in modbusWriteRequest.getRegisterList()){
                    println("address: ${register.first} value: ${register.second}")
                }
                modbusPresetMultipleRegistersResponse.transactionIdentifier = modbusWriteRequest.transactionIdentifier
                modbusPresetMultipleRegistersResponse.setAddress(modbusWriteRequest.address.toShort())
                modbusPresetMultipleRegistersResponse.setNumberofRegistersWritten(modbusWriteRequest.numberOfRegistersToWritten.toShort())
                ctx.write(modbusPresetMultipleRegistersResponse)
            }
        }

      //  ctx.close()
    }

    @Deprecated("Deprecated in Java")
    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        ctx.close()
    }


    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        ctx.flush()
    }
}