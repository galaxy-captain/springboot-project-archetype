package me.galaxy.archetype.infra.thrift;

import generate.HelloSerivce;
import me.galaxy.ThriftClient;

/**
 * @Description
 * @Author duanxiaolei@bytedance.com
 * @Date 2020/4/17 9:21 下午
 **/
@ThriftClient(host = "localhost", port = "9000")
public interface HelloService extends HelloSerivce.Iface {

}