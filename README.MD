# GQM+Strategies Phase 3
## Project Configuration
Phase 3 needs essentially four components to work correctly:
* The ERMES-QIP Bus
* Integrazione Project
* A NoSQL MongoDB
* Activiti Explorer and Rest

#### Bus and Integrazione
Bus and Integrazione must be deployed to allow the correct execution of Phase 3 and 4. You can create 
a war for each one of them and deploy them using Tomcat. Pay attention that the Bus needs to know
where Integrazione is placed. In the Bus project, find the *hsqldb.sql* file in which, for each operation
define the Integrazione endpoint. For example, if Integrazione is on **localhost:8080/Integrazione**
then every line of the file must be modified to point to Integrazione.For example, one line of the file should be:

```
INSERT INTO t (tag, content, originAdress, resolvedAdress, id) VALUES 
('level3Direct', 'create', 'origin', 'http://localhost:8080/Integrazione/level3Direct/storage/',null);
```

Integrazione is the key point of message exchanging between phases. See its documentation for further details.

#### MongoDB
A mongo db should be available on your machine. For simplicity a Docker container could be used.
You can use the same Mongo container for both Phase 3 and 4 but both phases need to have different databases
since they are different entities and communicate using the bus.

#### Activiti
Activiti Explorer and Rest wars can be downloaded [here](https://www.activiti.org/download-links). To deploy the wars you need
a Tomcat Server on your machine. Place the wars in (path/to/tomcat)/webapps and start the server. 
Next make Activiti Rest and Explorer share the same SQL database since when they are deployed as separate wars they use separated DBs. A guide can be found [here](https://stackoverflow.com/questions/29051834/how-to-configure-a-mysql-database-in-activiti).
Once done, access Activiti with a browser at
```
http://localhost:port/activiti-explorer
```
where port is the local Tomcat port (default is 8080).In Activiti , go to Manage tab -> Groups and create the following group 
(respect the spaces in the name!):
* "GQM Expert"
* "Workflow Developer"
* "Strategic Planner"
* "Measurement Planner"
* "Validator"
* "Data Collector"

These are the main roles involved in GQM+S Phase 3 and 4 and are also the Groups to which the MetaWorkflow's
tasks are assigned to.

**Without these groups on Activiti the apps won't work correctly!!**

Next assign a User in Activiti (e.g. kermit) to each of the newly created groups. You can also assign 
a single user to all groups.
The project contains 3 *BPMN20.xml* files containing the metaworkflow definition.
* **metaworkflow_generico_14.bpmn20.xml** is the original metaworflow defined by sub-phases 3.1 and 4.1 group
* **businessProcessManagementId.bpmn20.xml** is the modified metawokflow for phases 3 and 4
* **businessProcessManagementId_rev2.bpmn20.xml** is the final version of the latter

**IMPORTANT!** Some business logic is in the descriptions of the metaworkflow tasks. If you import the *bpmn20.xml* file 
 in Activiti you can read the the tasks description to understand better how the web app interacts with phases 3 and 4.
 
 #### Phase 3 and 4 Configuration
 Once all previous components are defined, modify the *application.properties* file in both projects to correctly point to them.
 All endpoints in the project are taken from that file, no hard-coded URLs are present.
 
 ## Notes for Developers
 The original projects divided each Phase 3 and 4 into two sub-phases. Two group worked on different halves of 
 the same phase. One group worked on 3.1 and 4.1 and the other one on 3.2 and 4.2.
 Our job was to merge together these sub-phases and form the final Phases 3 and 4 and later improve them
 integrating with the other GQM+S phases 2 and 5. If you find some comments with "TODO REMOVE PHASE 3" it means that
 in the original projects those functionalities were need while in the merged phases, it was no longer needed since
 the other phase used it. You can ignore those parts.
 
 Good Luck,
 
 GQM+S Phase 3 and 4 Team
 
 ISSSR 16/17