create table USER (
   id BIGINT NOT NULL AUTO_INCREMENT,
   userName VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   isActive BIT NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (userName)
);
   
create table ROLE(
   id BIGINT NOT NULL AUTO_INCREMENT,
   roleName VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (roleName)
);
   

CREATE TABLE USER_ROLE (
    userID BIGINT NOT NULL,
    roleID BIGINT NOT NULL,
    PRIMARY KEY (userID, roleID),
    CONSTRAINT FK_USER FOREIGN KEY (userID) REFERENCES USER (id),
    CONSTRAINT FK_ROLE FOREIGN KEY (roleID) REFERENCES ROLE (id)
);
  


INSERT INTO user(id,userName,passWord,isActive)
VALUES (1,'Supervis0r','$2a$10$tigNVl4SRYdOsZGBh428e.0V2NTEWTM/K0t.mglTQ9oWyhXUYNJ8e',1);

INSERT INTO ROLE(id,roleName)
VALUES (2,'USER');
INSERT INTO ROLE(id,roleName)
VALUES (3,'MODERATOR');
INSERT INTO ROLE(id,roleName)
VALUES (4,'ADMIN');

INSERT INTO USER_ROLE (userID,roleID)
  SELECT user.id, role.id FROM user, role