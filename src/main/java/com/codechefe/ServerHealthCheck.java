/*
 *
 *    Copyright (c) 2017-2018 Givantha Kalansuriya, This source is a part of
 *    ServerMonitor - An open source server monitoring framework.
 *
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.codechefe;

import com.codechefe.models.Service;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.DateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * Server Health Check Monitoring Class.
 *
 * @author <a href="mailto:prathapgivantha@gmail.com">Givantha Kalansuria</a>
 * @since 1.0.0
 */
class ServerHealthCheck implements Runnable {
    
    /** The Constant TIMEOUT. */
    private static final int TIMEOUT = 5000;
    
    /** The service. */
    private Service service;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHealthCheck.class);

    /**
     * Instantiates a new server health check.
     *
     * @param service the service
     */
    protected ServerHealthCheck(Service service) {
        this.service = service;
    }

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    public void run() {
        Socket connection = new Socket();
        boolean reachable;

        try {
            try {
                connection.connect(new InetSocketAddress(
                        InetAddress.getByName(service.getHost()), service.getPort()
                ), TIMEOUT);
            } finally {
                connection.close();
            }
            reachable = true;
        } catch (Exception e) {
            reachable = false;
        }

        if (reachable){
            LOGGER.debug( service.getServiceName() + " SERVICE" + String.format(
                    "%s:%d WAS UP AND RUNING ",
                    service.getHost(),
                    service.getPort()
            ) + DateFormat.getInstance().format(System.currentTimeMillis()));
            service.setRuning(true);
        } else {

            LOGGER.debug( service.getServiceName() + " SERVICE" + String.format(
                    "%s:%d WAS UNAVAILABLE  ",
                    service.getHost(),
                    service.getPort()
            ) + DateFormat.getInstance().format(System.currentTimeMillis()));
            service.setRuning(false);
        }

    }
}
