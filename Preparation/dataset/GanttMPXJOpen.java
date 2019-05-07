/*
 * file:       GanttMPXJOpen.java
 * author:     Jon Iles
 * copyright:  (c) Tapster Rock Limited 2005
 * date:       10/01/2005
 */

/*
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation; either version 2.1 of the License, or (at your
 * option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.
 */

package org.ganttproject.impex.msproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

import javax.swing.tree.DefaultMutableTreeNode;

import net.sourceforge.ganttproject.GPLogger;
import net.sourceforge.ganttproject.GanttCalendar;
import net.sourceforge.ganttproject.GanttProject;
import net.sourceforge.ganttproject.GanttTask;
import net.sourceforge.ganttproject.GanttTaskRelationship;
import net.sourceforge.ganttproject.GanttTree2;
import net.sourceforge.ganttproject.gui.GanttDialogInfo;
import net.sourceforge.ganttproject.language.GanttLanguage;
import net.sourceforge.ganttproject.resource.HumanResource;
import net.sourceforge.ganttproject.resource.HumanResourceManager;
import net.sourceforge.ganttproject.resource.ProjectResource;
import net.sourceforge.ganttproject.task.ResourceAssignment;
import net.sourceforge.ganttproject.task.TaskManager;
import net.sourceforge.ganttproject.task.TaskNode;
import net.sourceforge.ganttproject.task.dependency.TaskDependency;
import net.sourceforge.ganttproject.task.dependency.TaskDependencyException;

import com.tapsterrock.mpx.MPXCalendar;
import com.tapsterrock.mpx.MPXDuration;
import com.tapsterrock.mpx.MPXException;
import com.tapsterrock.mpx.MPXFile;
import com.tapsterrock.mpx.Priority;
import com.tapsterrock.mpx.Relation;
import com.tapsterrock.mpx.RelationList;
import com.tapsterrock.mpx.RelationType;
import com.tapsterrock.mpx.Resource;
import com.tapsterrock.mpx.Task;
import com.tapsterrock.mpx.TimeUnit;

/**
 * This class implements the mechanism used to import Microsoft Project data
 * into the GanttProject application using MPXJ.
 */
public abstract class GanttMPXJOpen {
    /**
     * Constructor.
     */
    public GanttMPXJOpen(GanttTree2 tasks, GanttProject project, Locale mpxLocale) {
        m_tasks = tasks;
        m_project = project;
        m_mpxImportLocale = mpxLocale;
    }

    /**
     * Constructor.
     */
    public GanttMPXJOpen(GanttTree2 tasks, GanttProject project) {
        m_tasks = tasks;
        m_project = project;
    }

    /**
     * This method opens a file based on the supplied filename.
     * 
     * @param filename
     *            filename
     * @return boolean flag
     * @throws MPXException 
     */
    public boolean load(String filename) throws MPXException {
        return (load(new File(filename)));
    }

    /**
     * Load the contents of a file.
     * 
     * @param file
     *            input file
     * @return boolean flag
     * @throws MPXException 
     */
    public abstract boolean load(File file) throws MPXException;

    /**
     * Load the contents of a project from an input stream.
     * 
     * @param is
     *            input stream
     * @return boolean flag
     * @throws MPXException 
     */
    public abstract boolean load(InputStream is) throws MPXException;

    /**
     * This method loads project data from an input stream. The type of data
     * expected in the stream is indicated by the class passed as the first
     * argument to this method. The file argument is used purely for error
     * reporting, if it is not null then error messages generated by this method
     * can refer to the source file. Finally, if the input stream argument is
     * not null, the data is loaded from the supplied input stream. If it is
     * null, then a new input stream is created from the supplied file object.
     * 
     * @param mpxjClass
     *            MPXJ class representing the type of file to be imported
     * @param file
     *            file instance
     * @param is
     *            input stream instance
     * @return boolean flag
     * @throws Exception 
     */
    protected boolean load(MPXFile mpx) throws MPXException {
            processResources(mpx);
            try {
				processTasks(mpx);
			} catch (Exception e) {
				throw new MPXException(e.getMessage(), e);
			}
            processRelationships(mpx);
            processResourceAssignments(mpx);
            return true;
    }

    /**
     * This method extracts all of the resources from the MPXFile instance and
     * creates the appropriate GanttProject resource representation.
     * 
     * @param mpx
     *            Current MPXFile instance
     */
    private void processResources(MPXFile mpx) {
        HumanResourceManager hrm = (HumanResourceManager) m_project
                .getHumanResourceManager();
        LinkedList resources = mpx.getAllResources();
        Iterator iter = resources.iterator();
        Resource resource;
        HumanResource people;

        while (iter.hasNext() == true) {
            resource = (Resource) iter.next();
            if (resource.getName() != null) {
                people = hrm.newHumanResource();
                people.setName(resource.getName());
                people.setMail(resource.getEmailAddress());
                hrm.add(people);

                m_resourceMap
                        .put(resource.getID(), new Integer(people.getId()));
            }
        }
    }

    /**
     * This method is used to extract all tasks from an MPXFile instance, and
     * create the equivalent data structures in GanttProject.
     * 
     * @param mpx
     *            Current MPXFile instance
     * @throws Exception
     */
    private void processTasks(MPXFile mpx) throws Exception {
        TaskManager tm = m_project.getTaskManager();
        MPXCalendar cal = mpx.getBaseCalendar("Standard");
        LinkedList tasks = mpx.getChildTasks();
        Iterator iter = tasks.iterator();

        while (iter.hasNext() == true) {
            processTask(tm, cal, (Task) iter.next(), null);
        }
    }

    /**
     * This method is called recursively to process the task hierarchy and
     * create the equivalent data structure in GanttProject.
     * 
     * @param defaultCalendar
     *            BaseCalendar instance
     * @param task
     *            Parent task
     * @param node
     *            Parent node
     * @throws Exception
     */
    private void processTask(TaskManager tm, MPXCalendar defaultCalendar,
            Task task, DefaultMutableTreeNode node) throws Exception {
        //
        // Calculate the duration in days
        //
        MPXCalendar taskCalendar = task.getCalendar();
        MPXCalendar cal;
        if (taskCalendar != null) {
            cal = taskCalendar;
        } else {
            cal = defaultCalendar;
        }

        final MPXDuration duration;
        boolean milestone = task.getMilestoneValue();
        if (milestone == true) {
            duration = MILESTONE_DURATION;
        } else {
            Date taskStart = task.getStart();
            Date taskFinish = task.getFinish();
            if (taskStart != null && taskFinish != null) {
                //duration = cal.getDuration(taskStart, taskFinish);
                duration = task.getDuration();
            } else {
                duration = task.getDuration();
            }
        }

        //
        // Create the new task object
        //
        GanttTask gtask = tm.createTask();
        // gtask.setChecked();
        // gtask.setColor();
        gtask.setCompletionPercentage((int) task.getPercentageCompleteValue());
        // gtask.setExpand()
        // gtask.setLength();
        gtask.setMilestone(milestone);
        gtask.setName(task.getName() == null ? "-" : task.getName());
        gtask.setNotes(task.getNotes());
        Priority prio = task.getPriority();
        if (prio != null) {
            int priority = prio.getValue();
            int p;
            switch (priority) {
            case Priority.HIGHEST:
            case Priority.HIGHER:
            case Priority.VERY_HIGH:
                p = 2;
                break;
            case Priority.LOWEST:
            case Priority.LOWER:
            case Priority.VERY_LOW:
                p = 0;
                break;
            default:
                p = 1;
            }

            gtask.setPriority(p);
        }
        // gtask.setShape();
        // gtask.setStartFixed()
        // gtask.setTaskID()
        gtask.setWebLink(task.getHyperlink());
        Date taskStart = task.getStart();
        assert taskStart!=null : "Task="+task+" has null start";
        gtask.setStart(new GanttCalendar(taskStart));
//        gtask.setDuration(tm.createLength((long) duration.getDuration()));
        long longDuration = (long) Math.ceil(duration.convertUnits(TimeUnit.DAYS).getDuration());
        if (longDuration > 0) {
        	gtask.setDuration(tm.createLength(longDuration));
        }
        else {
        	System.err.println("Task "+task.getName()+" has duration="+duration+" which is 0 as long integer. This duration has been ignored, task has got the default duration");
        }
        // gtask.setEnd(new GanttCalendar(task.getFinish()));

        //
        // Add the task and process any child tasks
        //
        tm.registerTask(gtask);

        m_tasks.addObject(gtask, (TaskNode) node, -1);

        m_taskMap.put(task.getID(), new Integer(gtask.getTaskID()));

        LinkedList children = task.getChildTasks();

        if (children.size() != 0) {
            node = m_tasks.getNode(gtask.getTaskID());

            Iterator iter = children.iterator();

            while (iter.hasNext() == true) {
                processTask(tm, defaultCalendar, (Task) iter.next(), node);
            }
        }
    }

    /**
     * This method extracts all of the inter-task relationships from and MPXFile
     * instance, and creates the equivalent data structures in the GanttProject
     * application.
     * 
     * @param mpx
     *            Currenct MPXFile instance
     */
    private void processRelationships(MPXFile mpx) {
        TaskManager tm = m_project.getTaskManager();
        Iterator taskIter = mpx.getAllTasks().iterator();
        Task task;
        RelationList rels;
        Iterator relIter;
        Relation rel;

        GanttTask gTask1;
        int gTaskNumber1;
        GanttTask gTask2;
        int gTaskNumber2;
        TaskDependency gTaskDependency;
        int gConstraintType;
        while (taskIter.hasNext() == true) {
            task = (Task) taskIter.next();
            gTaskNumber1 = mapTaskNumber(task.getID());

            if (gTaskNumber1 == -1) {
                continue;
            }

            rels = (RelationList)task.getPredecessors();

            if (rels != null) {
                relIter = rels.iterator();

                while (relIter.hasNext() == true) {
                    rel = (Relation) relIter.next();

                    gTaskNumber2 = mapTaskNumber(new Integer(rel
                            .getTaskIDValue()));

                    if (gTaskNumber2 != -1) {
                        gTask1 = tm.getTask(gTaskNumber1);
                        gTask2 = tm.getTask(gTaskNumber2);

                        switch (rel.getType().getType()) {
                        case RelationType.FINISH_FINISH_VALUE: {
                            gConstraintType = GanttTaskRelationship.FF;
                            break;
                        }

                        case RelationType.START_FINISH_VALUE: {
                            gConstraintType = GanttTaskRelationship.SF;
                            break;
                        }

                        case RelationType.START_START_VALUE: {
                            gConstraintType = GanttTaskRelationship.SS;
                            break;
                        }

                        default:
                        case RelationType.FINISH_START_VALUE: {
                            gConstraintType = GanttTaskRelationship.FS;
                            break;
                        }
                        }

                        try {
                            gTaskDependency = tm
                                    .getDependencyCollection()
                                    .createDependency(
                                            gTask1,
                                            gTask2,
                                            tm
                                                    .createConstraint(gConstraintType));
                            gTaskDependency.setConstraint(tm
                                    .createConstraint(gConstraintType));
                        }

                        catch (TaskDependencyException e) {
                        	if (!GPLogger.log(e)) {
                        		e.printStackTrace(System.err);
                        	}
                        }
                    }
                }
            }
        }
    }

    /**
     * This method extracts all resource assignment data from an MPXFile
     * instance and creates the equivalent GanttProject data structures.
     * 
     * @param mpx
     *            Currenct MPXFile instance
     */
    private void processResourceAssignments(MPXFile mpx) {
        TaskManager tm = m_project.getTaskManager();
        HumanResourceManager hrm = (HumanResourceManager) m_project
                .getHumanResourceManager();
        LinkedList assignments = mpx.getAllResourceAssignments();
        Iterator iter = assignments.iterator();
        com.tapsterrock.mpx.ResourceAssignment assignment;
        int gTaskID;
        int gResourceID;
        GanttTask gTask;
        ProjectResource gResource;
        ResourceAssignment gAssignment;

        while (iter.hasNext() == true) {
            assignment = (com.tapsterrock.mpx.ResourceAssignment) iter.next();
            gTaskID = mapTaskNumber(assignment.getTask().getID());
            gResourceID = mapResourceNumber(assignment.getResource().getID());

            if ((gTaskID != -1) && (gResourceID != -1)) {
                gTask = tm.getTask(gTaskID);
                gResource = hrm.getById(gResourceID);

                gAssignment = gTask.getAssignmentCollection().addAssignment(
                        gResource);
                gAssignment.setLoad((float) assignment.getUnitsValue());
                gAssignment.setCoordinator(false);
                if (gResource instanceof HumanResource)
                    gAssignment
                            .setRoleForAssignment(((HumanResource) gResource)
                                    .getRole());
            }
        }
    }

    /**
     * Convenience function used to map an MPXFile task ID to a GanttProject
     * task number.
     * 
     * @param taskID
     *            MPXFile task ID
     * @return GanttProject task number
     */
    private int mapTaskNumber(Integer taskID) {
        int result = -1;

        Integer taskNumber = (Integer) m_taskMap.get(taskID);

        if (taskNumber != null) {
            result = taskNumber.intValue();
        }

        return (result);
    }

    /**
     * Convenience function used to map an MPXFile resource ID to a GanttProject
     * resource number.
     * 
     * @param taskID
     *            MPXFile task ID
     * @return GanttProject task number
     */
    private int mapResourceNumber(Integer resourceID) {
        int result = -1;

        Integer resourceNumber = (Integer) m_resourceMap.get(resourceID);

        if (resourceNumber != null) {
            result = resourceNumber.intValue();
        }

        return (result);
    }

    private GanttTree2 m_tasks;

    private GanttProject m_project;

    private HashMap m_taskMap = new HashMap();

    private HashMap m_resourceMap = new HashMap();

    private Locale m_mpxImportLocale = Locale.ENGLISH;

    private static final MPXDuration MILESTONE_DURATION = MPXDuration.getInstance(1,
            TimeUnit.DAYS);
}
