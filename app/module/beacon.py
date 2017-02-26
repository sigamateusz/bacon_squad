from app.module import sql

class Beacon:

    def __init__(self, idx, location_one, location_two, location_three, location_four, correct_location, hint):
        self.idx = idx
        self.location_one = location_one
        self.location_two = location_two
        self.location_three = location_three
        self.location_four = location_four
        self.correct_location = correct_location
        self.hint = hint

    @classmethod
    def create_object(cls, idx, location_one, location_two, location_three, location_four, correct_location, hint):
        """
        Create object of class Beacon
        :param idx: int
        :param location_one: string
        :param location_two: string
        :param location_three: string
        :param location_four: string
        :param correct_location: string
        :param hint: string
        :return: Object of class Beacon
        """
        return Beacon(idx, location_one, location_two, location_three, location_four, correct_location, hint)


    @staticmethod
    def random_beacon_id():
        """
        RANDOM IDX OF NON USED BEACON
        :return: RANDOM IDX OF NON USED BEACON or NONE IF NOT FOUND
        """
        random_beacon_query = """SELECT BEACON_ID FROM BEACON_EVENT
                                 WHERE BEACON_EVENT.BEACON_ID
                                 NOT IN(SELECT BEACON_ID FROM USER_BEACONS
                                 WHERE USER_BEACONS.EVENT_ID = ?
                                 AND USER_BEACONS.USER_ID = ? )
                                 AND BEACON_EVENT._ROWID_ >= (abs(random()) %
                                 (SELECT max(BEACON_EVENT._ROWID_) FROM BEACON_EVENT) + 1 )
                                 LIMIT 1;"""
        random_beacon = sql.query(random_beacon_query, [1, 1])
        return random_beacon[0][0]

    @classmethod
    def create_beacon_object_by_id(cls, idx):
        """
        return object of class beacon created by id
        :param idx: int idx of beacon
        :return: object of class BEACON or None if beacon not found
        """
        beacon_query = """SELECT * FROM BEACON_LOCATION
                          WHERE ID_BEACON = ?"""
        beacon = sql.query(beacon_query,[idx])
        if beacon:
            beacon = cls(beacon[0][1], beacon[0][2], beacon[0][3], beacon[0][4], beacon[0][5], beacon[0][6], beacon[0][7])
            return beacon
        return None

    @staticmethod
    def get_beacon():
        beacon = sql.query("""SELECT * FROM BEACON WHERE USED=0""")
        beacon = beacon[0][0]
        sql.query("""UPDATE BEACON SET USED=1 WHERE ID=?""", [beacon[0]])
        return "||".join([str(i) for i in beacon])


