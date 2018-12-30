package fluffy.mo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class Test2_ServerHandler extends ChannelInboundHandlerAdapter {
  int i = 1;

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    // 0.当客户端连接时触发
    print("0   channelActive " + i++);
    super.channelActive(ctx);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    // 1. 读取消息触发
    print("1   channelRead " + i++);
    super.channelRead(ctx, msg+"tag1");
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    // 2. 消息处理完毕触发
    print("2   channelReadComplete " + i++);
    super.channelReadComplete(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    // 3. 客户端断开连接时触发
    print("3   channelInactive " + i++);
    super.channelInactive(ctx);
  }
  
  static void print(Object ... ss){
    StringBuilder s = new StringBuilder("ch--1--");
    for (Object string : ss) {
      s.append(string);
    }
    System.out.println(s.toString());
  }
}