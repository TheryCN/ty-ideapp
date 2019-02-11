-- Populate User (The password is set to ChangeIt bcrypt encoded)
INSERT INTO User(USR_USERNAME, USR_PASSWORD, USR_CREATED_ON, USR_UPDATED_ON, USR_CREATED_BY, USR_MODIFIED_BY) VALUES('Thery', '$2a$12$bg2zu5s4Q5NlMapvuRAEy.J0Ai1tjHRU79XJs8rqr3O9A9pF8amhy', '2019-02-06 16:00:00.00', '2019-02-06 16:00:00.00', 'SYSTEM', 'SYSTEM');
INSERT INTO User(USR_USERNAME, USR_PASSWORD, USR_CREATED_ON, USR_UPDATED_ON, USR_CREATED_BY, USR_MODIFIED_BY) VALUES('TestUser', '$2a$12$bg2zu5s4Q5NlMapvuRAEy.J0Ai1tjHRU79XJs8rqr3O9A9pF8amhy', '2019-02-06 16:00:00.00', '2019-02-06 16:00:00.00', 'SYSTEM', 'SYSTEM');

-- Populate Workspace
INSERT INTO Workspace(WKS_NAME, WKS_USR_ID, WKS_CREATED_ON, WKS_UPDATED_ON, WKS_CREATED_BY, WKS_MODIFIED_BY) VALUES('MyWorkspace', 1, '2019-02-06 16:00:00.00', '2019-02-06 16:00:00.00', 'SYSTEM', 'SYSTEM');

-- Populate Ideas of MyWorkspace
INSERT INTO Idea(IDA_NAME, IDA_SUBTITLE, IDA_DESCRIPTION, IDA_RATING, IDA_FEASIBILITY, IDA_ACHIEVE, IDA_CREATED_ON, IDA_UPDATED_ON, IDA_CREATED_BY, IDA_MODIFIED_BY, IDA_WKS_ID) VALUES('Change it?#1', 'SubChange it?#1', 'DescChange it?#1', 3, 'NORMAL', 1, '2019-02-06 16:00:00.00', '2019-02-06 16:00:00.00', 'SYSTEM', 'SYSTEM', 1);
INSERT INTO Idea(IDA_NAME, IDA_SUBTITLE, IDA_DESCRIPTION, IDA_RATING, IDA_FEASIBILITY, IDA_ACHIEVE, IDA_CREATED_ON, IDA_UPDATED_ON, IDA_CREATED_BY, IDA_MODIFIED_BY, IDA_WKS_ID) VALUES('Change it?#2', 'SubChange it?#2', 'DescChange it?#2', 4, 'NORMAL', 0, '2019-02-06 16:00:00.00', '2019-02-06 16:00:00.00', 'SYSTEM', 'SYSTEM', 1);
INSERT INTO Idea(IDA_NAME, IDA_SUBTITLE, IDA_DESCRIPTION, IDA_RATING, IDA_FEASIBILITY, IDA_ACHIEVE, IDA_CREATED_ON, IDA_UPDATED_ON, IDA_CREATED_BY, IDA_MODIFIED_BY, IDA_WKS_ID) VALUES('Change it?#3', 'SubChange it?#3', 'DescChange it?#3', 5, 'NORMAL', 0, '2019-02-06 16:00:00.00', '2019-02-06 16:00:00.00', 'SYSTEM', 'SYSTEM', 1);

-- Populate Workspace
INSERT INTO Workspace(WKS_NAME, WKS_USR_ID, WKS_CREATED_ON, WKS_UPDATED_ON, WKS_CREATED_BY, WKS_MODIFIED_BY) VALUES('AnotherWorkspace', 1, '2019-02-06 16:00:00.00', '2019-02-06 16:00:00.00', 'SYSTEM', 'SYSTEM');

-- Populate Ideas of AnotherWorkspace
INSERT INTO Idea(IDA_NAME, IDA_SUBTITLE, IDA_DESCRIPTION, IDA_RATING, IDA_FEASIBILITY, IDA_ACHIEVE, IDA_CREATED_ON, IDA_UPDATED_ON, IDA_CREATED_BY, IDA_MODIFIED_BY, IDA_WKS_ID) VALUES('Change it?#4', 'SubChange it?#4', 'DescChange it?#4', 3, 'NORMAL', 0, '2019-02-06 16:00:00.00', '2019-02-06 16:00:00.00', 'SYSTEM', 'SYSTEM', 2);
INSERT INTO Idea(IDA_NAME, IDA_SUBTITLE, IDA_DESCRIPTION, IDA_RATING, IDA_FEASIBILITY, IDA_ACHIEVE, IDA_CREATED_ON, IDA_UPDATED_ON, IDA_CREATED_BY, IDA_MODIFIED_BY, IDA_WKS_ID) VALUES('Change it?#5', 'SubChange it?#5', 'DescChange it?#5', 4, 'NORMAL', 0, '2019-02-06 16:00:00.00', '2019-02-06 16:00:00.00', 'SYSTEM', 'SYSTEM', 2);
INSERT INTO Idea(IDA_NAME, IDA_SUBTITLE, IDA_DESCRIPTION, IDA_RATING, IDA_FEASIBILITY, IDA_ACHIEVE, IDA_CREATED_ON, IDA_UPDATED_ON, IDA_CREATED_BY, IDA_MODIFIED_BY, IDA_WKS_ID) VALUES('Change it?#6', 'SubChange it?#6', 'DescChange it?#6', 5, 'NORMAL', 0, '2019-02-06 16:00:00.00', '2019-02-06 16:00:00.00', 'SYSTEM', 'SYSTEM', 2);

-- Populate Workspace
INSERT INTO Workspace(WKS_NAME, WKS_USR_ID, WKS_CREATED_ON, WKS_UPDATED_ON, WKS_CREATED_BY, WKS_MODIFIED_BY) VALUES('MyWorkspace', 2, '2019-02-06 16:00:00.00', '2019-02-06 16:00:00.00', 'SYSTEM', 'SYSTEM');