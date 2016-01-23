package com.pronoia.test.osgi.consumer;

import java.util.Timer;
import java.util.TimerTask;

import com.pronoia.test.osgi.bean.BeanOne;
import com.pronoia.test.osgi.service.Echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServiceConsumer extends TimerTask{
    Logger log = LoggerFactory.getLogger(this.getClass());

    Timer serviceTimer;

    int echoInterval = 5000;

    Echo echoService;
    BeanOne beanOne;

    public void init() {
        log.info( "Initializing Timer");
        serviceTimer = new Timer(this.getClass().getSimpleName());
        serviceTimer.schedule(this, echoInterval, echoInterval);
    }

    public void destroy() {
        log.info( "Destroying Timer");
        if ( null != serviceTimer ) {
            serviceTimer.cancel();
            serviceTimer = null;
        }
    }

    @Override
    public void run() {
        if ( null != echoService ) {
            echoService.execute( "Echo Service: " + System.currentTimeMillis());
        } else {
            log.warn( "Service Reference is null");
        }

        if ( null != beanOne ) {
            beanOne.execute( "Bean: " + System.currentTimeMillis());
        } else {
            log.warn( "Bean is null");
        }
    }

    public Echo getEchoService() {
        return echoService;
    }

    public void setEchoService(Echo echoService) {
        this.echoService = echoService;
    }

    public BeanOne getBeanOne() {
        return beanOne;
    }

    public void setBeanOne(BeanOne beanOne) {
        this.beanOne = beanOne;
    }

    public int getEchoInterval() {
        return echoInterval;
    }

    public void setEchoInterval(int echoInterval) {
        this.echoInterval = echoInterval;
    }
}
