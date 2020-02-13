package com.remember.clientrpc;

import com.remeber.rpccommon.utils.ReflectionUtil;
import com.remember.proprc.Peer;
import com.remember.transportrpc.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class RandomTransportSelector implements TransportSelector {
    /*
    存储已连接的client
     */
    List<TransportClient> clients;

    public RandomTransportSelector(){
        clients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) throws InstantiationException, IllegalAccessException {
        count = Math.max(1,count);
        for(Peer peer : peers){
            for(int i=0;i<count;i++){
               TransportClient client = ReflectionUtil.newInstance(clazz);
               client.connect(peer);
               clients.add(client);
            }
            log.info("connect server:{}",peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int randomNum = new Random().nextInt(clients.size());
        return clients.remove(randomNum);
    }

    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    @Override
    public synchronized void close() {
        for(TransportClient client : clients){
            client.close();
        }
        clients.clear();
    }
}
