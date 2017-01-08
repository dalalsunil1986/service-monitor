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

package com.codechefe.models;

import com.codechefe.services.Notification;
import com.codechefe.services.NotificationListener;

import java.util.ArrayList;

/**
 * Service Class.
 *
 * @author <a href="mailto:prathapgivantha@gmail.com">Givantha Kalansuria</a>
 * @since 1.0.0
 */
public class Service implements NotificationListener {

    /** The service name. */
    private String serviceName;

    /** The host. */
    private String host;

    /** The port. */
    private int port;

    /** The description. */
    private String description;

    /** The polling frequency. */
    private long pollingFrequency;

    /** The service outage start. */
    private long serviceOutageStart;

    /** The get service outage end. */
    private long getServiceOutageEnd;

    /** The is runing. */
    private boolean isRuning;

    /** The notifications. */
    private ArrayList<Notification> notifications = new ArrayList<Notification>();

    /**
     * Instantiates a new service.
     *
     * @param serviceName the service name
     * @param host the host
     * @param port the port
     */
    public Service(String serviceName, String host, int port) {
        this.serviceName = serviceName;
        this.host = host;
        this.port = port;
    }


    /**
     * Gets the service name.
     *
     * @return the service name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets the service name.
     *
     * @param serviceName the new service name
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Gets the host.
     *
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the host.
     *
     * @param host the new host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Gets the port.
     *
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the port.
     *
     * @param port the new port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the polling frequency.
     *
     * @return the polling frequency
     */
    public long getPollingFrequency() {
        return pollingFrequency;
    }

    /**
     * Sets the polling frequency.
     *
     * @param pollingFrequency the new polling frequency
     */
    public void setPollingFrequency(long pollingFrequency) {
        this.pollingFrequency = pollingFrequency;
    }

    /**
     * Gets the service outage start.
     *
     * @return the service outage start
     */
    public long getServiceOutageStart() {
        return serviceOutageStart;
    }

    /**
     * Sets the service outage start.
     *
     * @param serviceOutageStart the new service outage start
     */
    public void setServiceOutageStart(long serviceOutageStart) {
        this.serviceOutageStart = serviceOutageStart;
    }

    /**
     * Gets the gets the service outage end.
     *
     * @return the gets the service outage end
     */
    public long getGetServiceOutageEnd() {
        return getServiceOutageEnd;
    }

    /**
     * Sets the gets the service outage end.
     *
     * @param getServiceOutageEnd the new gets the service outage end
     */
    public void setGetServiceOutageEnd(long getServiceOutageEnd) {
        this.getServiceOutageEnd = getServiceOutageEnd;
    }

    /**
     * Checks if is service outage.
     *
     * @param currentTimestamp the current timestamp
     * @return true, if is service outage
     */
    synchronized public boolean isServiceOutage(long currentTimestamp) {
        if(serviceOutageStart > 0 && getServiceOutageEnd > 0) {
            if(currentTimestamp >= serviceOutageStart && currentTimestamp <= getServiceOutageEnd){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if is runing.
     *
     * @return true, if is runing
     */
    public boolean isRuning() {
        return isRuning;
    }

    /**
     * Sets the runing.
     *
     * @param runing the new runing
     */
    synchronized public void setRuning(boolean runing) {
        isRuning = runing;
        notifyListners();
    }

    
    /* (non-Javadoc)
     * @see com.codechefe.services.NotificationListener#registerNotification(com.codechefe.services.Notification)
     */
    public void registerNotification(Notification notification) {
        notifications.add(notification);

    }

    
    /* (non-Javadoc)
     * @see com.codechefe.services.NotificationListener#removeNotification(com.codechefe.services.Notification)
     */
    public void removeNotification(Notification notification) {
        notifications.remove(notification);

    }


	/* (non-Javadoc)
	 * @see com.codechefe.services.NotificationListener#notifyListners()
	 */
	public void notifyListners() {
		 for (Notification notification : notifications) {
	            notification.update(this);
	        }		
	}
}
