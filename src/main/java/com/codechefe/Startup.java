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

/**
 * Startup Class.
 *
 * @author <a href="mailto:prathapgivantha@gmail.com">Givantha Kalansuria</a>
 * @since 1.0.0
 */
public class Startup {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        ServerMonitorServer server = new ServerMonitorServer();
        EmailNotification email = new EmailNotification();


        Service applicationService = new Service("Application Service", "127.0.0.1", 8080);
        applicationService.setServiceOutageStart(50);
        applicationService.setGetServiceOutageEnd(30);

        Service databaseService = new Service("Database Service", "127.0.0.1", 27017);

        server.getRegisterdServices().add(applicationService);
        server.getRegisterdServices().add(databaseService);

        applicationService.registerNotification(email);
        databaseService.registerNotification(email);

        server.startServer();
    }
}
