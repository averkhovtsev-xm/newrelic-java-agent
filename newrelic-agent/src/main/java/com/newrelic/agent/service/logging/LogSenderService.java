/*
 *
 *  * Copyright 2020 New Relic Corporation. All rights reserved.
 *  * SPDX-License-Identifier: Apache-2.0
 *
 */

package com.newrelic.agent.service.logging;

import com.newrelic.agent.config.AgentConfig;
import com.newrelic.agent.model.LogEvent;
import com.newrelic.agent.service.EventService;
import com.newrelic.api.agent.Insights;

/**
 * LogSenderService interface
 *
 * Extending Insights makes the recordCustomEvent(...) API available to implementing classes
 */
public interface LogSenderService extends EventService, Insights {

    /**
     * Returns an insights instance used to track events created during a transaction. The events will be reported to
     * the Transaction's application, or to the default application if not in a transaction.
     *
     */
    Insights getTransactionInsights(AgentConfig config);

    /**
     * Store event into Reservoir following usual sampling using the given appName. Preference should be given to
     * storing the event in TransactionInsights instead of this.
     * @param appName application name
     * @param event log event
     */
    void storeEvent(String appName, LogEvent event);

    /**
     *
     * @param appName application name
     */
    void addHarvestableToService(String appName);

}