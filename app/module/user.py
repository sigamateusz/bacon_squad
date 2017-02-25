from app.module.sql import query


class User():
    def __init__(self, idx, mail):
        """

        :param idx:
        :param mail:
        :param event_idx:
        :param beacon_idx:
        :param amount_awr:
        :param points:
        """
        self.idx = idx
        self.mail = mail
        self.event_idx = None
        self.beacon_idx = None
        self.amount_awr = None
        self.points = None

    @classmethod
    def create(self, idx, mail):
        """

        :param idx:
        :param mail:
        :return:
        """
        return User(idx, mail)

    def get_event_idx(self):
        """

        :return:
        """
        return self.event_idx

    def get_user_idx(self):
        """

        :return:
        """
        return self.idx

    def is_beacon_rdy(self):
        """

        :return:
        """
        if self.points == 3:
            return True
        return False

    def add_point(self):
        """

        :return:
        """
        if self.points == None:
            self.points = 1
        else:
            self.points += 1

    def amount_awr_to_zero(self):
        """

        :return:
        """
        self.amount_awr = 0

    def add_amount_awr(self):
        """

        :return:
        """
        if self.amount_awr == None:
            self.amount_awr = 1
        else:
            self.amount_awr += 1

    def add_to_event(self, event_idx):
        """

        :return: True - when add record to database table user_events False - when record exist
        """
        self.event_idx = event_idx
        if not query("""SELECT * FROM USER_EVENTS WHERE USER_ID=? and EVENT_ID=?)""", [self.idx, event_idx]):
            query("""INSERT INTO USER_EVENTS (USER_ID, EVENT_ID) VALUES (?,?)""", [self.idx, event_idx])
            return True
        return False

    @staticmethod
    def add_user(mail, password, account_type):
        """

        :param mail:
        :param password:
        :param account_type:
        :return: True - when insert user to data base False - when that user exist in users database
        """
        if not query("""SELECT * FROM USERS WHERE MAIL=?""", [mail]):
            query("""INSERT INTO USERS (MAIL, PASSWORD, ACCOUNT_TYPE) VALUES (?, ?, ?)""",
                  [mail, password, account_type])
            return True
        return False
