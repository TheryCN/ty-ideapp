-- Populate Workspace
INSERT INTO Workspace(WKS_NAME) VALUES('MyWorkspace');

-- Populate Ideas of MyWorkspace
INSERT INTO Idea(IDA_NAME, IDA_SUBTITLE, IDA_DESCRIPTION, IDA_RATING, IDA_FEASIBILITY, IDA_WKS_ID) VALUES('Change it?#1', 'SubChange it?#1', 'DescChange it?#1', 3, 'NORMAL', 1);
INSERT INTO Idea(IDA_NAME, IDA_SUBTITLE, IDA_DESCRIPTION, IDA_RATING, IDA_FEASIBILITY, IDA_WKS_ID) VALUES('Change it?#2', 'SubChange it?#2', 'DescChange it?#2', 4, 'NORMAL', 1);
INSERT INTO Idea(IDA_NAME, IDA_SUBTITLE, IDA_DESCRIPTION, IDA_RATING, IDA_FEASIBILITY, IDA_WKS_ID) VALUES('Change it?#3', 'SubChange it?#3', 'DescChange it?#3', 5, 'NORMAL', 1);

-- Populate Workspace
INSERT INTO Workspace(WKS_NAME) VALUES('AnotherWorkspace');

-- Populate Ideas of AnotherWorkspace
INSERT INTO Idea(IDA_NAME, IDA_SUBTITLE, IDA_DESCRIPTION, IDA_RATING, IDA_FEASIBILITY, IDA_WKS_ID) VALUES('Change it?#4', 'SubChange it?#4', 'DescChange it?#4', 3, 'NORMAL', 2);
INSERT INTO Idea(IDA_NAME, IDA_SUBTITLE, IDA_DESCRIPTION, IDA_RATING, IDA_FEASIBILITY, IDA_WKS_ID) VALUES('Change it?#5', 'SubChange it?#5', 'DescChange it?#5', 4, 'NORMAL', 2);
INSERT INTO Idea(IDA_NAME, IDA_SUBTITLE, IDA_DESCRIPTION, IDA_RATING, IDA_FEASIBILITY, IDA_WKS_ID) VALUES('Change it?#6', 'SubChange it?#6', 'DescChange it?#6', 5, 'NORMAL', 2);