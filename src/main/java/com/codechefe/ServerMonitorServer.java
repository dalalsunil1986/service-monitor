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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Service Monitoring Server Class.
 *
 * @author <a href="mailto:prathapgivantha@gmail.com">Givantha Kalansuria</a>
 * @since 1.0.0
 */
public class ServerMonitorServer {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerMonitorServer.class);

    /** The service check frequent. */
    public final int SERVICE_CHECK_FREQUENT = 1000;

    /** The registerd services. */
    public List<Service> registerdServices = new ArrayList<Service>();

    /**
     * Start server.
     */
    public void startServer(){
        LOGGER.info("################ SERVICE MONITOR START #######################");

        LOGGER.info("Initializing ServiceMonitoring Server...");
        LOGGER.info("Current Registerd Service Count  " + getRegisterdServices().size());
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Server Startup Error", e);

        }
    }
    
    /**
     * Stop server.
     */
    public void stopServer(){

        LOGGER.info("Stop ServiceMonitoring Server...");

    }

    /**
     * Gets the registerd services.
     *
     * @return the registerd services
     */
    public List<Service> getRegisterdServices() {
        return registerdServices;
    }

    /**
     * Sets the registerd services.
     *
     * @param registerdServices the new registerd services
     */
    public void setRegisterdServices(List<Service> registerdServices) {
        this.registerdServices = registerdServices;
    }


    /**
     * Inits the.
     *
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
    private void init() throws InterruptedException, ExecutionException {

        BlockingQueue<Runnable> work = new ArrayBlockingQueue<Runnable>(100);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(50,100,5000,TimeUnit.MILLISECONDS, work);
        pool.prestartAllCoreThreads();

        Collection<Future<?>> futures = new LinkedList<Future<?>>();

        while (true) {
            for (Service service : registerdServices) {

                if(!service.isServiceOutage(System.currentTimeMillis())){
                    futures.add(pool.submit(new ServerHealthCheck(service)));
                } else {
                    LOGGER.info( service.getServiceName() + " Currently in service outage. " + DateFormat.getInstance().format(System.currentTimeMillis()));
                }
                Thread.sleep(SERVICE_CHECK_FREQUENT);
            }
            for (Future<?> future : futures) {

                future.get();
            }
        }
    }
}
