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
import com.codechefe.services.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * EmailNotification Class.
 *
 * @author <a href="mailto:prathapgivantha@gmail.com">Givantha Kalansuria</a>
 * @since 1.0.0
 */
public class EmailNotification implements Notification {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotification.class);

    /* (non-Javadoc)
     * @see com.codechefe.services.Notification#update(com.codechefe.models.Service)
     */
    public void update(Service service) {

        LOGGER.info( "\n" + " ************************ Email Notification ************************* ");

        String emailBody = "\n" + "   Service Status " + "\n" +
                "Service Name   :" + service.getServiceName() + "\n" +
                "Service Status :" + ((service.isRuning()) ? "Service is Started" : "Service is Down") + "\n" +
                "Service Host   :"  + service.getHost() + "\n" +
                "Service Port   :"  + service.getPort() + ".";
        LOGGER.info(emailBody);

        LOGGER.info( "\n" + " ********************************************************************* " );

    }
}
