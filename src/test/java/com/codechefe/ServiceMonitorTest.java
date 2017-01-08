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
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.After;
import org.junit.Before;

/**
 * Unit test for simple ServerMonitor.
 */
public class ServiceMonitorTest extends TestCase
{
    private ServerHealthCheck serverHealthCheck;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ServiceMonitorTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ServiceMonitorTest.class );
    }

    @Before
    public void setUp() throws Exception {

    }
    @After
    public void tearDown() throws Exception {
        serverHealthCheck = null;
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        Service downService = new Service("Database Service", "127.0.0.1", 27017);
        Service activeService = new Service("Database Service", "www.google.com", 443);

        serverHealthCheck = new ServerHealthCheck(downService);
        serverHealthCheck.run();

        assertNotNull(serverHealthCheck);
        assertNotNull(downService);

        assertFalse(downService.isRuning());
        assertEquals("127.0.0.1", downService.getHost());
        assertEquals(27017, downService.getPort());
        assertEquals("Database Service", downService.getServiceName());


        serverHealthCheck = new ServerHealthCheck(activeService);
        serverHealthCheck.run();
        assertNotNull(activeService);
        assertTrue(activeService.isRuning());

    }
}
