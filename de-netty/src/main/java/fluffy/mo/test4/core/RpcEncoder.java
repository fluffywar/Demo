package fluffy.mo.test4.core;

import java.util.List;

import fluffy.mo.SerializationUtil;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class RpcEncoder extends MessageToMessageEncoder<Object>{

  @Override
  protected void encode(ChannelHandlerContext ctx, Object msg, List<Object> out) throws Exception {
    RpcMessage message = new RpcMessage();
    if (msg instanceof RpcResponse) {
      message.setRpcRespose((RpcResponse) msg);
    }
    if (msg instanceof RpcRequest) {
      message.setRequest((RpcRequest) msg);
    }
    if (null != msg) {
      byte[] data = SerializationUtil.obj_To_Byte(message);
      out.add(Unpooled.copiedBuffer(data));
    }
  }

  
}