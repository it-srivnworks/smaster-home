CREATE TABLE `clsn` (
  `clsnid` int NOT NULL,
  `clsnkey` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`clsnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `clsnval` (
  `clsn_valid` int NOT NULL,
  `clsn_val` varchar(255) DEFAULT NULL,
  `clsnid` int DEFAULT NULL,
  PRIMARY KEY (`clsn_valid`),
  KEY `FK21nwtaphp96xhhcnvmnuai5st` (`clsnid`),
  CONSTRAINT `FK21nwtaphp96xhhcnvmnuai5st` FOREIGN KEY (`clsnid`) REFERENCES `clsn` (`clsnid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `userinfo` (
  `userid` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `staffinfo` (
  `userid` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `current_status` int DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `in_date` date DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `out_date` date DEFAULT NULL,
  `profile_pic` tinyblob,
  `profdescription` varchar(255) DEFAULT NULL,
  `deptid` int DEFAULT NULL,
  `profile_title` int DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `FKkr18ski2uooysrir44n80jo3d` (`deptid`),
  KEY `FKm4wpv7vy3pi29rdu4h8vcsjee` (`profile_title`),
  CONSTRAINT `FKkr18ski2uooysrir44n80jo3d` FOREIGN KEY (`deptid`) REFERENCES `clsnval` (`clsn_valid`),
  CONSTRAINT `FKm4wpv7vy3pi29rdu4h8vcsjee` FOREIGN KEY (`profile_title`) REFERENCES `clsnval` (`clsn_valid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `studentinfo` (
  `userid` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `current_status` int DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `in_date` date DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `out_date` date DEFAULT NULL,
  `profile_pic` tinyblob,
  `rollno` int DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `guardianinfo` (
  `userid` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  CONSTRAINT `FKlvhutech6eqq2oancluv7f550` FOREIGN KEY (`userid`) REFERENCES `studentinfo` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `addressinfo` (
  `userid` bigint NOT NULL,
  `address_line02` varchar(255) DEFAULT NULL,
  `address_line01` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `post_code` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `country` int DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `FKhlp1ybj5kuq63ix5e2j8t9in2` (`country`),
  CONSTRAINT `FKhlp1ybj5kuq63ix5e2j8t9in2` FOREIGN KEY (`country`) REFERENCES `clsnval` (`clsn_valid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
