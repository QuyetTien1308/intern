CREATE TABLE position_tbl (
  id bigint NOT NULL AUTO_INCREMENT,
  base_salary decimal(38,2) NOT NULL,
  name varchar(255) DEFAULT NULL,

  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE employee_tbl (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  username varchar(255) DEFAULT NULL,
  position_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKfpycypl88hjo1inetia50nscb (position_id),
  CONSTRAINT FKfpycypl88hjo1inetia50nscb FOREIGN KEY (position_id) REFERENCES position_tbl (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE salary_tbl (
  id bigint NOT NULL AUTO_INCREMENT,
  days_worked int DEFAULT NULL,
  overtime_salary decimal(38,2) NOT NULL,
  employee_id bigint DEFAULT NULL,
  position_id bigint DEFAULT NULL,
  days_overtime int DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKcso71aq6sfw3tmkr1ohems7hr (employee_id),
  KEY FK3dl6nay8f77i428te6sx8u85g (position_id),
  CONSTRAINT FK3dl6nay8f77i428te6sx8u85g FOREIGN KEY (position_id) REFERENCES position_tbl (id),
  CONSTRAINT FKcso71aq6sfw3tmkr1ohems7hr FOREIGN KEY (employee_id) REFERENCES employee_tbl (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE role (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;