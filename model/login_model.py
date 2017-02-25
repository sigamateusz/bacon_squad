import sqlite3


class LoginModel:

    def __init__(self):
        self.conn = sqlite3.connect('bacon_squad.db')
        self.cur = self.conn.cursor()

    def check_user_in_db_by_mail_model(self, mail, password):
        """
        Check if user exists in database.
        :param mail, password:
        :return: boolean
        """
        try:
            self.cur.execute("SELECT id, mail FROM users WHERE mail=(?) AND password=(?)", (mail, password))
            self.conn.commit()
            answer = self.cur.fetchall()
            return answer
        except sqlite3.OperationalError as w:
            print("Can't check if student exists {}".format(w))