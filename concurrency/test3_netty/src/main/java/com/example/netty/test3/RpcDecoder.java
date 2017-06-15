package com.example.netty.test3;

import java.util.List;

import com.example.netty.SerializationUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class RpcDecoder extends ByteToMessageDecoder {

  private Class<?> genericClass;

  public RpcDecoder(Class<?> genericClass) {
      this.genericClass = genericClass;
  }

  @Override
  public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
      if (in.readableBytes() < 4) {
          return;
      }
      in.markReaderIndex();
      int dataLength = in.readInt();
      if (dataLength < 0) {
          ctx.close();
      }
      if (in.readableBytes() < dataLength) {
          in.resetReaderIndex();
          return;
      }
      byte[] data = new byte[dataLength];
      in.readBytes(data);

      Object obj = SerializationUtil.byte_to_obj(data, genericClass);
      out.add(obj);
  }
}
