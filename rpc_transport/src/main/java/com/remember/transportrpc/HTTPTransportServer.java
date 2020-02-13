package com.remember.transportrpc;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
  * @author remember
  * @date 2020/2/13 13:37
 * HTTPServer
  */

@Slf4j
public class HTTPTransportServer implements TransportServer {
    private RequestHandler handler;
    private Server server;

    @Override
    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        this.server = new Server(port);

        //Servlet接受请求
        ServletContextHandler sch = new ServletContextHandler();
        sch.setHandler(sch);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        sch.addServlet(holder,"/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }


    class RequestServlet extends HttpServlet{
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("servlet connect");
            ServletInputStream inputStream = req.getInputStream();
            ServletOutputStream outputStream = resp.getOutputStream();

            if(handler != null){
                handler.onRequest(inputStream,outputStream);
            }

        }
    }
}
