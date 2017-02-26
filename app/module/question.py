from app.module import sql

class Question:

    def __init__(self, idx, question_one, question_two, question_three, question_four, correct_question, event_id):
        self.idx = idx
        self.question_one = question_one
        self.question_two = question_two
        self.question_three = question_three
        self.question_four = question_four
        self.correct_question = correct_question
        self.event_id = event_id

    @classmethod
    def create_object(cls, idx, question_one, question_two, question_three, question_four, correct_question, event_id):
        """
        Create object of class Beacon
        :param idx: int
        :param question_one: string
        :param question_two: string
        :param question_three: string
        :param question_four: string
        :param correct_question: string
        :param event_id: string
        :return: Object of class Beacon
        """
        return cls(idx, question_one, question_two, question_three, question_four, correct_question, event_id)


    @staticmethod
    def random_question_id(user_idx, event_idx):
        """
        RANDOM IDX OF NON USED BEACON
        :return: RANDOM IDX OF NON USED BEACON or NONE IF NOT FOUND
        """
        random_question_query = """SELECT ID FROM QUESTION
                                     WHERE ID
                                     NOT IN(SELECT QUESTION_ID FROM USER_QUESTIONS
                                     WHERE USER_ID = ?
                                     AND ID_EVENT = ? )
                                     AND QUESTION._ROWID_ >= (abs(random()) %
                                     (SELECT max(QUESTION._ROWID_) FROM QUESTION) + 1 )
                                     LIMIT 1;"""
        random_question = sql.query(random_question_query, [event_idx, user_idx])
        return random_question[0][0]

    @classmethod
    def create_question_object_by_id(cls, idx):
        """
        return object of class question created by id
        :param idx: int idx of question
        :return: object of class BEACON or None if question not found
        """
        question_query = """SELECT * FROM QUESTION
                          WHERE ID = ?"""
        question = sql.query(question_query,[idx])
        if question:
            question = cls(question[0][0], question[0][1], question[0][2], question[0][3], question[0][4], question[0][5], question[0][6])
            return question
        return None

    @staticmethod
    def used_question(user_idx, question_idx):
        sql.query("""INSERT INTO USER_QUESTIONS (USER_ID, QUESTION_ID) VALUES (?, ?)""", [user_idx, question_idx])