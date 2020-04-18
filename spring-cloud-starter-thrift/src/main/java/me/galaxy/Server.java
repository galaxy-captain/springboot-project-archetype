package me.galaxy;

import generate.HelloSerivce;
import me.galaxy.thrift.HelloServiceImpl;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/4/17 6:49 下午
 **/
public class Server {

    public static void main(String[] args) throws TTransportException {
        new Server().run();
    }

    public void run() throws TTransportException {

        HelloSerivce.Processor<HelloSerivce.Iface> processor = new HelloSerivce.Processor<HelloSerivce.Iface>(new HelloServiceImpl());

        TNonblockingServerSocket transport = new TNonblockingServerSocket(9000);

        TServer server = new TNonblockingServer(new THsHaServer.Args(transport).processor(processor));

        server.serve();

    }

}