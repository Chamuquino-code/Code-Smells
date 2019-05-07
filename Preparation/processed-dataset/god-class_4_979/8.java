/**
     * Remove the given group to the list of job groups that will never be
     * deleted by this processor, even if a pre-processing-command to
     * delete the group is encountered.
     * 
     * @param group
     */
public boolean removeJobGroupToNeverDelete(String group) {
    if (group != null)
        return jobGroupsToNeverDelete.remove(group);
    return false;
}
