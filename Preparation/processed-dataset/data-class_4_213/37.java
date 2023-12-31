/**
     * @see org.webcurator.core.harvester.coordinator.HarvestCoordinator#resumeAll()
     */
public void resumeAll() {
    HarvestAgentStatusDTO agentDTO = null;
    HarvestAgent agent = null;
    Iterator it = harvestAgents.values().iterator();
    while (it.hasNext()) {
        agentDTO = (HarvestAgentStatusDTO) it.next();
        if (agentDTO.getHarvesterStatus() != null && !agentDTO.getHarvesterStatus().isEmpty()) {
            agent = harvestAgentFactory.getHarvestAgent(agentDTO.getHost(), agentDTO.getPort());
            agent.resumeAll();
        }
    }
}
