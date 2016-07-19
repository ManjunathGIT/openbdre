/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wipro.ats.bdre.md.api.airflow;

import com.wipro.ats.bdre.exception.MetadataException;
import com.wipro.ats.bdre.md.api.GetETLDriver;
import com.wipro.ats.bdre.md.api.oozie.OozieInitJob;
import com.wipro.ats.bdre.md.beans.GetETLDriverInfo;
import com.wipro.ats.bdre.util.AirflowUtil;
import com.wipro.ats.bdre.util.OozieUtil;
import org.apache.log4j.Logger;

/**
 * Created by pushpak on 18/07/2016.
 */
public class AirflowGetETLDriver {
    private static final Logger LOGGER = Logger.getLogger(AirflowInitJob.class);

    /**
     * default constructor
     */
    private AirflowGetETLDriver() {

    }

    /**
     * This method calls execute method and persist the output till runtime.
     *
     * @param args String array having minimum-batch-id, maximum-batch-id, environment with their command line notations.
     */
    public static void main(String[] args) {
        GetETLDriver fc = new GetETLDriver();
        GetETLDriverInfo getETLDriverInfo = fc.execute(args);
        AirflowUtil airflowUtil = new AirflowUtil();
        try {
            airflowUtil.persistBeanData(getETLDriverInfo, false);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new MetadataException(e);
        }
    }
}
