from app.module.sql import query


class Admin():
    def __init__(self, idx, mail):
        """

        :param idx:
        :param mail:
        """
        self.idx = idx
        self.mail = mail

    @staticmethod
    def add_user(mail, password):
        """

        :param mail:
        :param password:
        :return:
        """
        query("""INSERT INTO USERS (MAIL, PASSWORD, ACCOUNT_TYPE) VALUES (?, ?, ?)""", [mail, password, "user"])

    @staticmethod
    def add_event(event_name, location):
        """

        :param event_name:
        :param location:
        :return:
        """
        query("""INSERT INTO EVENT (EVENT_NAME, LOCATION) VALUES (?, ?)""", [event_name, location])

    @staticmethod
    def add_question(question, answer1, answer2, answer3, answer4, correct_ans, event_idx):
        """

        :param question:
        :param answer1:
        :param answer2:
        :param answer3:
        :param answer4:
        :param correct_ans:
        :param event_idx:
        :return:
        """
        query(
            """INSERT INTO QUESTION (QUESTION, ANSWER1, ANSWER2, ANSWER3, ANSWER4, CORRECT, ID_EVENT) VALUES (?, ?, ?, ?, ?, ?, ?)""",
            [question, answer1, answer2, answer3, answer4, correct_ans, event_idx])

    @staticmethod
    def add_beacon(beacon_idx, location1, location2, location3, location4, correct, hint, event_idx):
        """

        :param beacon_idx:
        :param location1:
        :param location2:
        :param location3:
        :param location4:
        :param correct:
        :param hint:
        :param event_idx:
        :return:
        """
        query(
            """INSERT INTO BEACON_LOCATION (ID_BEACON, LOCATION1, LOCATION2, LOCATION3, LOCATION4, CORRECT, HINT) VALUES (?, ?, ?, ?, ?, ?, ?, ?)""",
            [beacon_idx, location1, location2, location3, location4, correct, hint, event_idx])

        query("""INSERT INTO BEACON_EVENT (EVENT_ID, BEACON_ID) """, [event_idx, beacon_idx])
