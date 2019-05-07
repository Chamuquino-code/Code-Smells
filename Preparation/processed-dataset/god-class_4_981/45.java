/**
     * @see org.quartz.spi.JobStore#schedulerStarted()
     */
public void schedulerStarted() throws SchedulerException {
    if (isClustered()) {
        clusterManagementThread = new ClusterManager();
        if (initializersLoader != null)
            clusterManagementThread.setContextClassLoader(initializersLoader);
        clusterManagementThread.initialize();
    } else {
        try {
            recoverJobs();
        } catch (SchedulerException se) {
            throw new SchedulerConfigException("Failure occured during job recovery.", se);
        }
    }
    misfireHandler = new MisfireHandler();
    if (initializersLoader != null)
        misfireHandler.setContextClassLoader(initializersLoader);
    misfireHandler.initialize();
}
