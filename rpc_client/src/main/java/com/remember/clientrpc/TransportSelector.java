package com.remember.clientrpc;

import com.remember.proprc.Peer;
import com.remember.transportrpc.TransportClient;

import java.util.List;

/**
  * @author remember
  * @date 2020/2/13 18:37
 * 路由选择
 *
  */
public interface TransportSelector {

    /*
    初始化selector
     peers:可以连接的peer的信息
     count:client可以与server建立多少个连接
     clazz:client实现类
     */
    void init(List<Peer> peers,int count,Class<? extends TransportClient> clazz) throws InstantiationException, IllegalAccessException;

    /*
    选择一个Client与Server去交互
     */
    TransportClient select();

    /*
    释放Client
     */
    void release(TransportClient client);

    void close();

}
