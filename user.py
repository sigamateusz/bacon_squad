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