from sql import query


def main():

    # -------DROP-------
    query("""DROP TABLE `QUESTION`;""")
    query("""DROP TABLE `BEACON`;""")
    query("""DROP TABLE `USERS`;""")
    query("""DROP TABLE `ADMIN`;""")

    # -------CREATE-------
    query("""CREATE TABLE `QUESTION` (`ID`	INTEGER, `QUESTION`	TEXT, `ANSWER1`	 TEXT, `ANSWER2`	TEXT, `ANSWER3`	TEXT, `ANSWER4`	TEXT, `CORRECT`	TEXT, `USED`  INTEGER, PRIMARY KEY(`ID`));""")
    query("""CREATE TABLE `BEACON` (`ID`   INTEGER,`ID_BEACON`	TEXT, `LOCATION1`	TEXT,`LOCATION2`	TEXT,`LOCATION3`	TEXT,`LOCATION4`	TEXT,`CORRECT`	TEXT,`HINT`	TEXT, `USED`  INTEGER ,PRIMARY KEY(`ID`));""")
    query("""CREATE TABLE `USERS` (`ID`	INTEGER,`MAIL`	TEXT,`PASSWORD`	TEXT, `IS_BEACON` INTEGER, `POINTS_AMOUNT`  INTEGER, ID_BEACON   INTEGER, ID_QUESTION  INTEGER , PRIMARY KEY(`ID`));""")
    query("""CREATE TABLE `ADMIN` (`ID`	INTEGER,`MAIL`	TEXT,`PASSWORD`	TEXT, PRIMARY KEY(`ID`));""")
    # -------NEW RECORDS-------


if __name__ == '__main__':
    main()
